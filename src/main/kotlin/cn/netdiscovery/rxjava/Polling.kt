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
 * @version: V1.0 轮询
 */
fun polling(
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