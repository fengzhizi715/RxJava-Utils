package cn.netdiscovery.rxjava

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
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
 * 轮询函数，直到某个条件结束轮询
 * @param initialDelay 从什么时候开始倒计时
 * @param period       每隔一定的时间，执行倒计时
 * @param unit         时间的单位
 * @param scheduler    线程调度器（默认使用 computation 线程，当然也可以使用 io 等线程）
 * @param func         轮询的 block
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