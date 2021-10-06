package cn.netdiscovery.rxjava.extension

import io.reactivex.rxjava3.disposables.Disposable

/**
 *
 * @FileName:
 *          cn.netdiscovery.rxjava.extension.`Disposable+Extension`
 * @author: Tony Shen
 * @date: 2021-07-10 23:02
 * @version: V1.0 Disposable 的扩展函数
 */

/**
 * 安全释放 Disposable
 */
fun Disposable?.safeDispose() {

    this?.takeIf { !it.isDisposed }?.let {
        it.dispose()
    }
}

/**
 * 判断 Disposable 是否已经 isDisposed
 */
fun Disposable?.isDisposed(): Boolean = this?.isDisposed ?: true