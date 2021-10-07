# RxJava-Utils

在日常开发实践中，本人所积累的 RxJava 相关的工具类

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
implementation 'com.github.fengzhizi715.RxJava-Utils:utils:v0.14'
```