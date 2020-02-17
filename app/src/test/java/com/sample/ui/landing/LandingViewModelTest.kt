package com.sample.ui.landing

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.verify
import com.sample.navigation.NavDispatcher
import com.sample.navigation.NavRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LandingViewModelTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var navDispatcher: NavDispatcher
    private var coroutineDispatcher = Dispatchers.Unconfined
    private lateinit var sut: LandingViewModel

    @Before
    fun setUp() {
        sut = LandingViewModel(coroutineDispatcher, navDispatcher)
    }

    @Test
    fun `opens doctor login fragment`() = runBlocking {
        sut.openDoctorLogInFragment()
        verify(navDispatcher).navigate(NavRequest.Main.DoctorLogIn)
    }

    @Test
    fun `opens select patient fragment`() = runBlocking {
        sut.openPatientSelectFragment()
        verify(navDispatcher).navigate(NavRequest.Main.PatientSelect)
    }
}