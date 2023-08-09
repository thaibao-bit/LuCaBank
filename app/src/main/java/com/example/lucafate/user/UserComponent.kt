package com.example.lucafate.user

import com.example.lucafate.MainActivity
import com.example.lucafate.SplashActivity
import dagger.Subcomponent

interface UserComponent {

    @Subcomponent.Factory
    interface Factory{
        fun create(): UserComponent
    }

    fun inject(activity: MainActivity)

    fun inject(activity: SplashActivity)


}