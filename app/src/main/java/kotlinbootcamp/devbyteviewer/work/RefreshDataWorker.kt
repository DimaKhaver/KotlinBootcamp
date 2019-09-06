package kotlinbootcamp.devbyteviewer.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinbootcamp.devbyteviewer.database.getDatabase
import kotlinbootcamp.devbyteviewer.repository.VideosRepository
import retrofit2.HttpException
import timber.log.Timber

class RefreshDataWorker(appContext: Context, params: WorkerParameters): CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "kotlinbootcamp.devbyteviewer.work.RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val repository = VideosRepository(database)

        try {
            repository.refreshVideos()
            Timber.d("work request for sync is run")
        } catch (exception: HttpException) {
            return Result.retry()
        }

        return Result.success()
    }
}