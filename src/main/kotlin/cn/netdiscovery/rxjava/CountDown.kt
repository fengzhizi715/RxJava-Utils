package cn.netdiscovery.rxjava

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 *
 * @FileName:
 *          cn.netdiscovery.rxjava.CountDown
 * @author: Tony Shen
 * @date: 2021-07-10 21:46
 * @version: V1.0 倒计时的函数
 */
fun countDown(
    delay: Long,
    unit: TimeUnit,
    scheduler: Scheduler = Schedulers.computation(),
    func: () -> Unit,
    failure: ((Throwable) -> Unit)? = null
): Disposable {
    return Observable.timer(delay, unit, scheduler)
        .subscribe({
            func.invoke()
        }, {
            failure?.invoke(it)
        })
}