package com.sample.ui

import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Delay
import kotlinx.coroutines.InternalCoroutinesApi
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.resume

/**
 * Resumes delay() function invocation instantly, for test purposes
 */
@InternalCoroutinesApi
class InstantDelayCoroutineDispatcher : CoroutineDispatcher(), Delay {

    override fun scheduleResumeAfterDelay(
        timeMillis: Long,
        continuation: CancellableContinuation<Unit>
    ) {
        continuation.resume(Unit)
    }

    override fun dispatch(context: CoroutineContext, block: Runnable) {
        block.run()  // dispatch on calling thread
    }
}
