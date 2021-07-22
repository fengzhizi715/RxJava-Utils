package cn.netdiscovery.rxjava

import io.reactivex.rxjava3.core.Completable
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
 * @version: V1.0 <描述当前版本功能>
 */
fun delay(
    delay: Long,
    unit: TimeUnit,
    scheduler: Scheduler = Schedulers.computation(),
    action: Action
) {
    Observable.timer(delay, unit, scheduler)
        .subscribe{
            action.invoke()
        }
}

fun delay(
    delay: Long,
    unit: TimeUnit,
    subscribeScheduler: Scheduler = Schedulers.computation(),
    observeScheduler: Scheduler,
    action: Action
): Disposable {
    return Observable.timer(delay, unit, subscribeScheduler)
        .observeOn(observeScheduler)
        .subscribe {
            action.invoke()
        }
}
