//package com.example.absolutegame.di
//
//import android.content.Context
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.example.absolutegame.presentation.auth.login.LoginViewModel
//import com.example.absolutegame.presentation.auth.register.RegisterViewModel
//import com.example.absolutegame.presentation.blur.BlurViewModel
//import com.example.absolutegame.presentation.favorite.FavoriteViewModel
//import com.example.absolutegame.presentation.genre.GenreViewModel
//import com.example.absolutegame.presentation.home.HomeViewModel
//import com.example.absolutegame.presentation.profile.ProfileViewModel
//
//class ViewModelFactory(
//    private val provider: Provider
//) : ViewModelProvider.Factory {
//
//    companion object {
//
//        @Volatile
//        private var INSTANCE : ViewModelFactory? = null
//
//        fun getInstance(provider: Provider) = synchronized(ViewModelFactory::class.java) {
//            INSTANCE ?: ViewModelFactory(provider).also { INSTANCE = it }
//        }
//    }
//
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return when (modelClass) {
//            LoginViewModel::class.java -> LoginViewModel(
//                 = provider.localRepository,
//            )
//            HomeViewModel::class.java -> HomeViewModel(
//                homeRepository = provider.remoteRepository,
//                localRepository = provider.localRepository
//            )
//            GenreViewModel::class.java -> GenreViewModel(
//                genreRepository = provider.remoteRepository,
//            )
//            RegisterViewModel::class.java -> RegisterViewModel(
//                registerRepository = provider.localRepository,
//            )
//            ProfileViewModel::class.java -> ProfileViewModel(
//                profileRepository = provider.localRepository,
//                workManager = provider.workManager
//            )
//            BlurViewModel::class.java -> BlurViewModel(
//                application = provider.context,
//                workManager = provider.workManager,
//                profileRepository = provider.localRepository
//            )
//            else -> throw UnsupportedOperationException()
//        } as T
//    }
//}