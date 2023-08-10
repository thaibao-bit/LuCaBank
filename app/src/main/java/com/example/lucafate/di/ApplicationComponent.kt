package com.example.lucafate.di

import com.example.lucafate.MainActivity

interface ApplicationComponent {
    fun inject(activity: MainActivity)
}