package cn.netdiscovery.rxjava

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 *
 * @FileName:
 *          cn.netdiscovery.rxjava.Debounce
 * @author: Tony Shen
 * @date: 2021-10-07 13:57
 * @version: V1.0 <描述当前版本功能>
 */

fun <T> Observable<T>.debounce(
    timeout: Long,
    scheduler: Scheduler = Schedulers.computation()
): Observable<T> = this.throttleFirst(timeout, TimeUnit.MILLISECONDS, scheduler)

fun <T> Observable<T>.debounceIf(
    predicate: (T) -> Boolean,
    timeout: Long,
    unit: TimeUnit,
    scheduler: Scheduler = Schedulers.computation(),
): Observable<T> {
    return this.publish { sharedSrc ->
        Observable.merge(
            sharedSrc.debounce(timeout, unit, scheduler)
                .filter { predicate.invoke(it) },
            sharedSrc.filter { !predicate.invoke(it) }
        )
    }
}

fun <T> Observable<T>.debounceAndSubscribe(
    timeout: Long,
    scheduler: Scheduler = Schedulers.computation(),
    onNext: OnNext<T>,
    onError: onError,
): Disposable {
    return this
        .debounce(timeout,scheduler)
        .flatMapSingle {
            Single.fromCallable { onNext(it) }
                .onErrorReturn(onError)
        }
        .subscribe()
}