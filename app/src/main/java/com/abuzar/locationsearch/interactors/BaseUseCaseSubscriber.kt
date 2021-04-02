package com.abuzar.locationsearch.interactors

import android.content.Context
import android.text.TextUtils
import io.reactivex.observers.DisposableObserver

abstract class BaseUseCaseSubscriber<T>: DisposableObserver<T>() {


    override fun onComplete() {}
    override fun onError(e: Throwable) {}
    override fun onNext(t: T) {}

}
