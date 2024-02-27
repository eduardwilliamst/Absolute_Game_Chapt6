package com.example.absolutegame

import android.app.Application
import com.example.absolutegame.di.Provider
import com.example.di.presentation.PresentationModule
import com.example.absolutegame.di.ApplicationComponent
import com.example.absolutegame.di.DaggerApplicationComponent

class Application: Application() {

    val appComponent = DaggerApplicationComponent.builder()
        .presentationModule(PresentationModule(this))
        .build()

//    lateinit var provider: Provider

    override fun onCreate() {
        super.onCreate()

//        provider = Provider(this)

    }
}