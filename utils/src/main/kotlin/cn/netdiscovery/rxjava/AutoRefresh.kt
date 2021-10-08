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

fun refresh(
    period: Long,
    timeUnit: TimeUnit,
    scheduler: Scheduler = Schedulers.computation(),
    func: () -> Unit,
    failure: ((Throwable) -> Unit)? = null
): Disposable = Observable.interval(0, period, timeUnit, scheduler)
    .subscribe({
        func.invoke()
    }, {
        failure?.invoke(it)
    })