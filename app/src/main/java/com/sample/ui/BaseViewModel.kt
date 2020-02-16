package com.sample.ui

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import com.sample.utils.SafeLiveData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel(
    backgroundDispatcher: CoroutineDispatcher
) : ViewModel(), CoroutineScope, SafeLiveData.LiveDataContainer {
    override val coroutineContext: CoroutineContext by lazy { backgroundDispatcher + Job() }

    @CallSuper
    override fun onCleared() {
        coroutineContext.cancel()
    }
}
