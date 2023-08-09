package com.example.lucafate.login

import com.example.lucafate.di.ActivityScope
import dagger.Subcomponent

@ActivityScope
// Definition of a Dagger subcomponent
@Subcomponent
interface LoginComponent {

//    // Factory to create instances of LoginComponent
//    @Subcomponent.Factory
//    interface Factory {
//        fun create(): LoginComponent
//    }
//
//    // Classes that can be injected by this Component
//    fun inject(activity: LoginFragment)
}