package cn.netdiscovery.rxjava

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 *
 * @FileName:
 *          cn.netdiscovery.rxjava.Polling
 * @author: Tony Shen
 * @date: 2021-07-10 23:08
 * @version: V1.0 轮询相关的函数
 */

/**
 * 轮询函数，当触发某个条件时轮询结束。下游会不停地收到订阅，需要自行判断
 * @param initialDelay 从什么时候开始倒计时
 * @param period       每隔一定的时间，执行倒计时
 * @param unit         时间的单位
 * @param scheduler    线程调度器（默认使用 computation 线程，当然也可以使用 io 等线程）
 * @param func         轮询的条件
 */
fun pollingToTakeUntil(
    initialDelay: Long,
    period: Long,
    unit: TimeUnit,
    scheduler: Scheduler = Schedulers.computation(),
    func: () -> Boolean
): Observable<Boolean> {
    return Observable.interval(initialDelay, period, unit, scheduler)
        .flatMap {
            return@flatMap Observable.create<Boolean> { emitter ->

                emitter.onNext(func.invoke())
            }
        }.takeUntil {
            it == true
        }
}

/**
 * 轮询函数，当触发某个条件时轮询结束。轮询结束时，下游才开始收到订阅
 * @param initialDelay 从什么时候开始倒计时
 * @param period       每隔一定的时间，执行倒计时
 * @param unit         时间的单位
 * @param scheduler    线程调度器（默认使用 computation 线程，当然也可以使用 io 等线程）
 * @param func         轮询的条件
 */
fun pollingWhenItEnd(
    initialDelay: Long,
    period: Long,
    unit: TimeUnit,
    scheduler: Scheduler = Schedulers.computation(),
    func: () -> Boolean
): Observable<Boolean> {
    return Observable.interval(initialDelay, period, unit, scheduler)
        .flatMap {
            return@flatMap Observable.create<Boolean> { emitter ->

                emitter.onNext(func.invoke())
            }
        }.takeUntil {
            it == true
        }.filter {
            it
        }
}

/**
 * 轮询函数，当触发某个条件时轮询结束，该函数会限制轮询的次数，达到轮询的次数后便不再进行轮询。
 * 在规定的次数内，达到轮询结束的条件，则会触发 succ 回调，
 * 在规定的次数内，没有达到轮询结束的条件，则会调用 failure 回调。
 * @param initialDelay 从什么时候开始倒计时
 * @param period       每隔一定的时间，执行倒计时
 * @param unit         时间的单位
 * @param scheduler    线程调度器（默认使用 computation 线程，当然也可以使用 io 等线程）
 * @param func         轮询的条件
 * @param succ         轮询正常结束的回调
 * @param failure      没有达到轮询结束条件的回调
 */
fun pollingWithLimitedNumber(
    initialDelay: Long,
    period: Long,
    unit: TimeUnit,
    scheduler: Scheduler = Schedulers.computation(),
    count:Long,
    func: () -> Boolean,
    succ: Action,
    failure: Action
): Disposable {

    var failureCount:Long = 0
    return Observable.interval(initialDelay, period, unit, scheduler)
        .flatMap {
            return@flatMap Observable.create<Boolean> { emitter ->

                emitter.onNext(func.invoke())
            }
        }.takeUntil {
            it == true
        }
        .take(count)
        .subscribe ({
            if (it) {
                succ.invoke()
            } else {
                failureCount++

                if (failureCount == count) {
                    failure.invoke()
                }
            }
        }, {
            failure.invoke()
        })
}