package com.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sample.ui.BaseFragment
import com.sample.utils.extensions.observe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {
    override val coroutineContext: CoroutineContext = Dispatchers.Main + SupervisorJob()
    private val viewModel: MainViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        observeNavigationChanges()
        viewModel.openSplashScreen()
    }

    private fun observeNavigationChanges() {
        viewModel.navigationChanges.observe(this) { it.executeIfNotConsumed(this) }
    }

    fun getNestedFragmentManager() =
        supportFragmentManager.findFragmentById(R.id.container)?.childFragmentManager

    private fun getCurrentFragment() =
        supportFragmentManager.findFragmentById(R.id.container) as? BaseFragment

    override fun onBackPressed() {
        if (getCurrentFragment()?.onBackPressed() != true) {
            super.onBackPressed()
        }
    }
}
