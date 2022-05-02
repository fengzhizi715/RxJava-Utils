# RxJava-Utils
[![@Tony沈哲 on weibo](https://img.shields.io/badge/weibo-%40Tony%E6%B2%88%E5%93%B2-blue.svg)](http://www.weibo.com/fengzhizi715)
[![License](https://img.shields.io/badge/license-Apache%202-lightgrey.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)
[![](https://jitpack.io/v/fengzhizi715/RxJava-Utils.svg)](https://jitpack.io/#fengzhizi715/RxJava-Utils)

在日常开发实践中，本人所积累的 RxJava 相关的工具类，持续更新ing

## 下载

将它添加到项目的 root build.gradle 中：

```groovy
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

然后在项目或者在 module 中添加：

```groovy
implementation 'com.github.fengzhizi715.RxJava-Utils:utils:v0.19'
```

## Feature

1. RetryWithDelay

   RxJava 的重连机制，需要配合 retryWhen 操作符一起使用


2. Debounce
* debounce()
* debounceIf()
* debounceAndSubscribe()

3. CountDown
* countDown() 倒计时函数

4. Delay
* delay() 延时函数

5. Polling
* pollingToTakeUntil() 轮询函数，直到某个条件结束轮询。下游会不停收到订阅，需要自行判断
* pollingWhenItEnd() 轮询函数，直到某个条件，轮询真正结束。此时下游才收到订阅

6. AutoRefresh
* refresh() 自动刷新的函数

7. Extension 
   
   为 Disposable 提供扩展函数
* safeDispose() 安全释放 Disposable
* isDisposed() 判断 Disposable 是否已经 Disposed

8. RxValue、RxValueMutable