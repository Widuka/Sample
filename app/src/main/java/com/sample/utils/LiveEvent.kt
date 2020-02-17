package com.sample.utils

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

open class LiveEvent<T> : SafeLiveData<T>() {

    init {
        value = null
    }

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner, EventObserver(observer))
    }

    private class EventObserver<K>(private val observer: Observer<K>) : Observer<K> {

        private val ignoreCall = AtomicBoolean(true)

        override fun onChanged(t: K) {
            if (ignoreCall.compareAndSet(false, false)) {
                observer.onChanged(t)
            }
            ignoreCall.set(false)
        }

        override fun equals(other: Any?): Boolean {
            return observer == other
        }

        override fun hashCode(): Int {
            return observer.hashCode()
        }
    }

    class Empty : LiveEvent<Unit>() {
        fun post() = postValue(Unit)
    }
}