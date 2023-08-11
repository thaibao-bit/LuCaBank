package com.example.lucafate.login

interface LoginInterface {
    interface View{
        fun loginSuccess()
        fun loginFailed()
        fun loginError()

    }
    interface Presenter{
        fun login(account: String, password: String)
    }
}