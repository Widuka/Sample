package com.sample.utils.extensions

import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.sample.ui.BaseFragment
import timber.log.Timber

fun FragmentTransaction.swap(
    tag: String,
    createFragment: () -> BaseFragment,
    fragmentManager: FragmentManager,
    containerId: Int,
    transactionViews: List<View>? = null
) {
    with(fragmentManager) {
        val currentFragment = findFragmentById(containerId)
        val fragmentToAttach = findFragmentByTag(tag)
        if (currentFragment != fragmentToAttach) {
            currentFragment?.let(::detach)
        }
        transactionViews?.forEach { addSharedElement(it, it.transitionName) }
        fragmentToAttach?.let(::attach) ?: add(containerId, createFragment(), tag)
        Timber.i("Swap: %s, isFreshFragment: %b", tag, fragmentToAttach == null)
    }
}

fun FragmentTransaction.removeOldFragments(
    fragmentsToRemove: List<String>,
    tag: String,
    fragmentManager: FragmentManager
) {
    fragmentsToRemove
        .filter { it != tag }
        .mapNotNull(fragmentManager::findFragmentByTag)
        .forEach { remove(it) }
    Timber.i("Removed fragments: %s", fragmentsToRemove)
}