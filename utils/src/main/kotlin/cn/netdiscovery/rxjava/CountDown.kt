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
 * @version: V1.0 倒计时相关的函数
 */

/**
 * 倒计时函数
 * @param delay     从什么时候开始倒计时
 * @param unit      时间的单位
 * @param scheduler 线程调度器（默认使用 computation 线程，当然也可以使用 io 等线程）
 * @param func      倒计时的 block
 * @param failure   倒计时失败的 block
 */
fun countDown(
    delay: Long,
    unit: TimeUnit,
    scheduler: Scheduler = Schedulers.computation(),
    func: Action,
    failure: onError? = null
): Disposable {
    return Observable.timer(delay, unit, scheduler)
        .subscribe({
            func.invoke()
        }, {
            failure?.invoke(it)
        })
}