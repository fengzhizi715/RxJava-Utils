package cn.netdiscovery.rxjava.value

/**
 *
 * @FileName:
 *          cn.netdiscovery.rxjava.value.RxValueMutable
 * @author: Tony Shen
 * @date: 2021/12/10 3:52 PM
 * @version: V1.0 <描述当前版本功能>
 */
class RxValueMutable<T : Any>(defaultValue: T) : RxValue<T>(defaultValue) {

    fun set(newValue: T) {
        subject.onNext(newValue)
    }

    fun asImmutable(): RxValue<T> = this

    companion object {
        fun <T : Any> of(defaultValue: T) = RxValueMutable(defaultValue)
    }
}