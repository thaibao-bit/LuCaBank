package com.example.lucafate.login

open class LoginPresenter(private val loginInterface: LoginInterface) {
    fun login(account: String, password: String){
        if (account == "1" && password == "1"){
            loginInterface.loginSuccess()
        }
        else{
            loginInterface.loginFailed()
        }
    }
}