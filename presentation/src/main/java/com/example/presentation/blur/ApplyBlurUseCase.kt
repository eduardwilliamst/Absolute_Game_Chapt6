package com.example.presentation.blur

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.presentation.helper.IMAGE_MANIPULATION_WORK_NAME
import com.example.presentation.helper.KEY_IMAGE_URI
import com.example.presentation.helper.TAG_OUTPUT
import com.example.presentation.workmanager.BlurWorker
import javax.inject.Inject

class ApplyBlurUseCase @Inject constructor(
    private val workManager: WorkManager,
) {
    operator fun invoke(imageUri: Uri?) {
        workManager.beginUniqueWork(
            IMAGE_MANIPULATION_WORK_NAME,
            ExistingWorkPolicy.REPLACE,
            OneTimeWorkRequestBuilder<BlurWorker>()
                .setInputData(setInputDataForUri(imageUri))
                .addTag(TAG_OUTPUT)
                .build()
        ).enqueue()
    }

    private fun setInputDataForUri(imageUri: Uri?): Data {
        return Data.Builder().apply {
            putString(KEY_IMAGE_URI, imageUri?.toString())
        }.build()
    }

    fun getWorkManagerLiveData(): LiveData<List<WorkInfo>> {
        return workManager.getWorkInfosByTagLiveData(TAG_OUTPUT)
    }
}