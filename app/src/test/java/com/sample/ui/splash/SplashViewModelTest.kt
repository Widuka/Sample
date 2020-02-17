package com.sample.ui.splash

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.verify
import com.sample.navigation.NavDispatcher
import com.sample.navigation.NavRequest
import com.sample.ui.InstantDelayCoroutineDispatcher
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@InternalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SplashViewModelTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var navDispatcher: NavDispatcher

    private lateinit var sut: SplashViewModel

    private var coroutineDispatcher = InstantDelayCoroutineDispatcher()

    @Before
    fun setUp() {
        sut = SplashViewModel(coroutineDispatcher, navDispatcher)
    }

    @Test
    fun `opens user select screen after splash delay`() = runBlocking {
        sut.openUserSelectFragment()
        verify(navDispatcher).navigate(NavRequest.Main.Landing)
    }
}