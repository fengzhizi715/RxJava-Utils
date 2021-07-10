package cn.netdiscovery.rxjava.extension

import io.reactivex.rxjava3.disposables.Disposable

/**
 *
 * @FileName:
 *          cn.netdiscovery.rxjava.extension.`Disposable+Extension`
 * @author: Tony Shen
 * @date: 2021-07-10 23:02
 * @version: V1.0 <描述当前版本功能>
 */
fun Disposable?.safeDispose() {

    this?.takeIf { !it.isDisposed }?.let {
        it.dispose()
    }
}