package com.example.absolutegame.di

import android.app.Application
import com.example.absolutegame.presentation.auth.login.LoginActivity
import com.example.absolutegame.presentation.auth.register.RegisterActivity
import com.example.absolutegame.presentation.blur.BlurActivity
import com.example.absolutegame.presentation.home.HomeActivity
import com.example.absolutegame.presentation.profile.ProfileActivity
import com.example.di.data.DataModule
import com.example.di.domain.DomainModule
import com.example.di.domain.UseCaseModule
import com.example.di.presentation.PresentationModule
import dagger.Component

@Component(
    modules = [
        PresentationModule::class,
        DomainModule::class,
        UseCaseModule::class,
        DataModule::class,
    ]
)
interface ApplicationComponent {

    val application: Application

    fun inject(activity: LoginActivity)
    fun inject(activity: RegisterActivity)
    fun inject(activity: BlurActivity)
    fun inject(activity: HomeActivity)
    fun inject(activity: ProfileActivity)
}