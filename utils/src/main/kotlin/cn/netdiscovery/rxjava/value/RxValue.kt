package cn.netdiscovery.rxjava.value

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject

/**
 *
 * @FileName:
 *          cn.netdiscovery.rxjava.value.RxValue
 * @author: Tony Shen
 * @date: 2021/12/10 3:51 PM
 * @version: V1.0 <描述当前版本功能>
 */
open class RxValue<T : Any>(defaultValue: T) {

    protected val subject: BehaviorSubject<T> = BehaviorSubject.createDefault(defaultValue)

    val value: T?
        get() = subject.value

    val stream: Observable<T>
        get() = subject
}