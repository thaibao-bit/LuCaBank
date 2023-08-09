package com.example.lucafate.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.lucafate.user.UserManager
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val userManager: UserManager) {

    private val _loginState = MutableLiveData<LoginViewState>()
    val loginState: LiveData<LoginViewState>
        get() = _loginState

    fun login(username: String, password: String){
        if (userManager.loginUser(username, password)){
            _loginState.value = LoginSuccess
        }
        else{
            _loginState.value = LoginError
        }
    }

    fun unregister() {
        userManager.unregister()
    }

    fun getUsername(): String = userManager.username

}




