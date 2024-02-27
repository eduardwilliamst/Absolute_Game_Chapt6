package com.example.presentation.workmanager

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.presentation.helper.KEY_IMAGE_URI
import com.example.presentation.helper.blurBitmap
import com.example.presentation.helper.makeStatusNotification
import com.example.presentation.helper.writeBitmapToFile

class BlurWorker(
    private val context: Context,
    params: WorkerParameters,
) : Worker(context, params) {
    override fun doWork(): Result {
        makeStatusNotification("Blurring image", context)

        return try {
            val resourceUri = inputData.getString(KEY_IMAGE_URI)
            val picture = BitmapFactory.decodeStream(
                context.contentResolver.openInputStream(Uri.parse(resourceUri))
            )
            val output = blurBitmap(picture, context)
            val outputUri = writeBitmapToFile(context, output)

            makeStatusNotification("Output is $outputUri", context)

            Result.success(workDataOf(KEY_IMAGE_URI to outputUri.toString()))
        } catch (throwable: Throwable) {
            Result.failure()
        }
    }
}