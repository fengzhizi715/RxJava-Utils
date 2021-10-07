package cn.netdiscovery.rxjava

/**
 *
 * @FileName:
 *          cn.netdiscovery.rxjava.TypeAliases
 * @author: Tony Shen
 * @date: 2021-07-22 14:52
 * @version: V1.0 <描述当前版本功能>
 */
typealias Action = () -> Unit

typealias OnNext<T> = (T) -> Unit

typealias onError = (Throwable) -> Unit