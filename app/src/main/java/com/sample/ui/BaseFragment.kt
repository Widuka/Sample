package com.sample.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlin.coroutines.CoroutineContext

abstract class BaseFragment : Fragment(), CoroutineScope {
    protected abstract val layoutRes: Int
    abstract val viewModel: BaseViewModel

    override val coroutineContext: CoroutineContext = Dispatchers.Main + SupervisorJob()

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(layoutRes, container, false)

    @CallSuper
    override fun onDestroyView() {
        coroutineContext.cancelChildren()
        super.onDestroyView()
    }

    open fun onBackPressed() = false
}