package com.example.absolutegame.presentation.blur

import android.app.Application
import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.absolutegame.R
import com.example.absolutegame.helper.toUriOrNull
import com.example.absolutegame.helper.worker.BlurWorker
import com.example.absolutegame.helper.worker.IMAGE_MANIPULATION_WORK_NAME
import com.example.absolutegame.helper.worker.KEY_IMAGE_URI
import com.example.absolutegame.helper.worker.TAG_OUTPUT
import com.example.domain.repository.ProfileRepository
import com.example.presentation.blur.ApplyBlurUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BlurViewModel @Inject constructor(
    private val applyBlurUseCase: ApplyBlurUseCase,
    private val accountRepository: ProfileRepository,
) : ViewModel() {

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val _profilePhoto = MutableLiveData<String?>()
    val profilePhoto: LiveData<String?> = _profilePhoto

    private var imageUri: Uri? = null
    private var outputUri: Uri? = null

    internal fun setOutputUri(outputImageUri: String?) {
        outputUri = outputImageUri.toUriOrNull()
    }

    internal fun setImageUri(imageUri: String?) {
        this.imageUri = imageUri.toUriOrNull()
    }

    fun saveProfilePhoto(profilePhoto: String) {
        viewModelScope.launch(Dispatchers.IO) {
            accountRepository.saveProfilePhoto(profilePhoto)
        }
    }

    fun applyBlur() {
        applyBlurUseCase.invoke(imageUri)
    }

    fun getOutputWorkerInfo(): LiveData<List<WorkInfo>> {
        return applyBlurUseCase.getWorkManagerLiveData()
    }

    fun loadProfilePhoto() {
        viewModelScope.launch(Dispatchers.IO) {
            accountRepository.loadProfilePhoto()
                .catch { throwable ->
                    withContext(Dispatchers.Main) {
                        _error.value = throwable.message
                    }
                }
                .collectLatest { profilePhoto ->
                    withContext(Dispatchers.Main) {
                        _profilePhoto.value = profilePhoto
                    }
                }
        }
    }
}
