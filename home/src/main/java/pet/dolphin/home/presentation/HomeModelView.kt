package pet.dolphin.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withPermit
import kotlinx.coroutines.time.withTimeout
import kotlinx.coroutines.withTimeout
import kotlin.runCatching

class HomeModelView(

): ViewModel() {
    private val mutex = Mutex()

    fun onStartText(){
        viewModelScope.launch {
            if(!mutex.tryLock()) return@launch

            try {
                loadUrl()
            }
            finally {
                mutex.unlock()
            }
        }
    }

    suspend fun loadUrl() {
        supervisorScope {
            val semaphore = Semaphore(4)

            (0..100).map { fakeUrl ->
                async(Dispatchers.IO) {
                    runCatching {
                        withTimeout(5_000){
                            retry(3) {
                                semaphore.withPermit {
                                    delay(1000)
                                }
                            }
                        }
                    }
                }
            }.awaitAll()
        }
    }

    suspend fun <T> retry(
        times: Int,
        initDelay: Long = 500,
        block: suspend () -> T
    ): T {
        var currentDelay = initDelay
        repeat(times - 1) {
            try {
                return block()
            }
            catch (e: Exception) {
                delay(currentDelay)
                currentDelay *= 2
            }
        }

        return block()
    }

}