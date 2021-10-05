package cn.netdiscovery.rxjava

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 *
 * @FileName:
 *          cn.netdiscovery.rxjava.Delay
 * @author: Tony Shen
 * @date: 2021-07-19 00:24
 * @version: V1.0 延时相关的函数
 */

/**
 * 延时函数
 * @param delay     从什么时候开始
 * @param unit      时间的单位
 * @param scheduler 线程调度器（默认使用 computation 线程，当然也可以使用 io 等线程）
 * @param action    延时的 block
 */
fun delay(
    delay: Long,
    unit: TimeUnit,
    scheduler: Scheduler = Schedulers.computation(),
    action: Action
): Disposable = Observable.timer(delay, unit, scheduler)
    .subscribe {
        action.invoke()
    }

/**
 * 延时函数
 * @param delay              从什么时候开始
 * @param unit               时间的单位
 * @param subscribeScheduler 线程调度器（默认使用 computation 线程，当然也可以使用 io 等线程）
 * @param observeScheduler   下游的线程调度器，如果下游需要切换线程可以使用
 * @param action             延时的 block
 */
fun delay(
    delay: Long,
    unit: TimeUnit,
    subscribeScheduler: Scheduler = Schedulers.computation(),
    observeScheduler: Scheduler,
    action: Action
): Disposable = Observable.timer(delay, unit, subscribeScheduler)
    .observeOn(observeScheduler)
    .subscribe {
        action.invoke()
    }
