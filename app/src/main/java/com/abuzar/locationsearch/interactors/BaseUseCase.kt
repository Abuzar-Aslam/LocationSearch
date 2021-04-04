package com.abuzar.locationsearch.interactors

import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

abstract class BaseUseCase<T>{
    private val subscribeOn: Scheduler = Schedulers.io()
    private val observeOn: Scheduler = AndroidSchedulers.mainThread()
    private val disposables = CompositeDisposable()

    fun <O> execute(disposableObserver: O) where O : Disposable, O : Observer<T> {
        disposables.add(
            buildUseCaseObservable()
                .compose(applySchedulers())
                .subscribeWith(disposableObserver)
        )
    }

    fun unsubscribe() {
        disposables.clear()
    }

    public abstract fun buildUseCaseObservable(): Observable<T>
    fun buildCacheObservable(): Observable<T> {
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