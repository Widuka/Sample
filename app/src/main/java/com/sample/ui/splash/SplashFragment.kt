package com.sample.ui.splash

import android.os.Bundle
import android.view.View
import com.sample.R
import com.sample.ui.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment() {
    override val layoutRes = R.layout.fragment_splash
    override val viewModel: SplashViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun creator(): () -> SplashFragment = ::SplashFragment
    }
}