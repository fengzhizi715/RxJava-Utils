package cn.netdiscovery.rxjava

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 *
 * @FileName:
 *          cn.netdiscovery.rxjava.AutoRefresh
 * @author: Tony Shen
 * @date: 2021-10-08 11:51
 * @version: V1.0 自动刷新相关的函数
 */

/**
 * 自动刷新的函数
 * @param initialDelay 从什么时候开始
 * @param period       每隔一定的时间，执行刷新的功能
 * @param unit         时间的单位
 * @param scheduler    线程调度器（默认使用 computation 线程，当然也可以使用 io 等线程）
 * @param func         刷新的 block
 * @param failure      刷新失败的 block
 */
fun refresh(
    initialDelay: Long,
    period: Long,
    timeUnit: TimeUnit,
    scheduler: Scheduler = Schedulers.computation(),
    func: () -> Unit,
    failure: ((Throwable) -> Unit)? = null
): Disposable = Observable.interval(initialDelay,period, timeUnit, scheduler)
    .subscribe({
        func.invoke()
    }, {
        failure?.invoke(it)
    })