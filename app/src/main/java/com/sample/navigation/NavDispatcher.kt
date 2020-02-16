package com.sample.navigation

import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commitNow
import com.sample.MainActivity
import com.sample.utils.SafeLiveData
import com.sample.utils.extensions.removeOldFragments
import com.sample.utils.extensions.swap
import kotlinx.coroutines.*
import kotlin.coroutines.resume

class NavDispatcher : SafeLiveData.LiveDataContainer {
    private val mainBackStack = BackStack()
    private val nestedBackStacks = mutableMapOf<String, BackStack>()
    val navigationChanges = SafeLiveData<Navigation>()

    private var currentStack: BackStack = mainBackStack

    suspend fun navigate(
        navRequest: NavRequest,
        navigationMode: NavigationMode? = null,
        transactionViews: List<View>? = null
    ) = withContext(Dispatchers.Main) {
        navigationMode?.apply { navRequest.navigationMode = this }
        when (navRequest) {
            is NavRequest.Main -> navigateMain(navRequest, transactionViews)
        }
    }

    private suspend fun navigateMain(navRequest: NavRequest, transactionViews: List<View>? = null) {
        postNavigation(mainBackStack, navRequest, transactionViews) {
            supportFragmentManager
        }
    }

    private suspend fun postNavigation(
        backStack: BackStack,
        navRequest: NavRequest,
        transactionViews: List<View>? = null,
        fragmentManagerProvider: MainActivity.() -> FragmentManager?
    ) {
        currentStack = backStack
        suspendCancellableCoroutine<Unit> { continuation ->
            navigationChanges.postValue(Navigation { mainActivity ->
                with(mainActivity) {
                    val fragmentTagsToRemove =
                        backStack.push(navRequest, navRequest.navigationMode).map { it.tag }
                    fragmentManagerProvider()?.let {
                        performFragmentTransaction(
                            it,
                            navRequest,
                            fragmentTagsToRemove,
                            transactionViews
                        )
                    }
                    continuation.resume(kotlin.Unit)
                }
            })
        }
    }

    private fun performFragmentTransaction(
        fragmentManager: FragmentManager,
        navRequest: NavRequest,
        fragmentTagsToRemove: List<String>,
        transactionViews: List<View>? = null
    ) {
        for (tag in fragmentTagsToRemove) {
            nestedBackStacks.remove(tag)
        }
        fragmentManager.commitNow {
            swap(
                navRequest.tag,
                navRequest.toFragmentCreator(),
                fragmentManager,
                navRequest.containerId,
                transactionViews
            )
            removeOldFragments(fragmentTagsToRemove, navRequest.tag, fragmentManager)
        }
    }

    fun navigateBack(
        coroutineScope: CoroutineScope,
        transactionViews: List<View>? = null
    ): Boolean {
        return when {
            currentStack.size > 1 -> coroutineScope.navigateBackWith(currentStack, transactionViews)
            mainBackStack.size > 1 -> navigateBackMain(coroutineScope, transactionViews)
            else -> false
        }
    }

    fun navigateBackMain(
        coroutineScope: CoroutineScope,
        transactionViews: List<View>? = null
    ): Boolean {
        nestedBackStacks.remove(mainBackStack.peek()?.tag)
        return coroutineScope.navigateBackWith(mainBackStack, transactionViews)
    }

    private fun CoroutineScope.navigateBackWith(
        backStack: BackStack,
        transactionViews: List<View>? = null
    ): Boolean {
        backStack.peekPrevious()?.let {
            launch {
                postBackNavigation(backStack, transactionViews) {
                    if (backStack == mainBackStack) {
                        supportFragmentManager
                    } else {
                        getNestedFragmentManager()
                    }
                }
            }
            return true
        }
        return false
    }

    private suspend fun postBackNavigation(
        backStack: BackStack,
        transactionViews: List<View>? = null,
        fragmentManagerProvider: MainActivity.() -> FragmentManager?
    ) {
        suspendCancellableCoroutine<Unit> { continuation ->
            navigationChanges.postValue(Navigation { mainActivity ->
                with(mainActivity) {
                    val fragmentTagsToRemove = kotlin.collections.listOf(backStack.peek()!!.tag)
                    fragmentManagerProvider()?.let {
                        performFragmentTransaction(
                            it,
                            backStack.peekPrevious()!!,
                            fragmentTagsToRemove,
                            transactionViews
                        )
                        backStack.pop()
                        continuation.resume(kotlin.Unit)
                    }
                }
            })
        }
    }
}