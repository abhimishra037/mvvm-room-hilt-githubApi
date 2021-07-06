package c.com.githubrepolist.worker

import android.content.Context
import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.work.WorkerInject
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import c.com.githubrepolist.repository.MainRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.onEach

class LoopApi @WorkerInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    val repository: MainRepository
) : CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {

        withContext(Dispatchers.IO) {
            repository.getGitRepoWorker()
        }
        return Result.success()
    }
}
