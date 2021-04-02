package com.abuzar.locationsearch.interactors

import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

abstract class BaseUseCase<T>{


    val disposables = CompositeDisposable()
    private val subscribeOn =Schedulers.io()
    private val observeOn =AndroidSchedulers.mainThread()

    open fun <O> execute(disposableObserver: O) where O : Disposable, O : Observer<T> {
        disposables.add(
            buildUseCaseObservable()
                .compose(applySchedulers())
                .subscribeWith(disposableObserver)
        )
    }

    open fun unsubscribe() {
        disposables.clear()
    }

    protected abstract fun buildUseCaseObservable(): Observable<T>

    open fun buildCacheObservable(): Observable<T>? {
        return Observable.empty()
    }

    /***
     * Transforms the any observable to have this UseCase's schedulers applied.
     * Useful when combining/ziping multiple observables you want to run concurrently
     */
    private fun <X> applySchedulers(): ObservableTransformer<X, X> {
        return ObservableTransformer { observable: Observable<X> ->
            observable
                .subscribeOn(subscribeOn)
                .unsubscribeOn(subscribeOn)
                .observeOn(observeOn)
        }
    }

}