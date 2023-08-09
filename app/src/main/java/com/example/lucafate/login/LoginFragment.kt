package com.example.lucafate.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.lucafate.CustomAlertClass
import com.example.lucafate.R
import javax.inject.Inject

class LoginFragment : Fragment() {
    @Inject
    lateinit var loginViewModel: LoginViewModel
    private lateinit var errorTextView: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view =  inflater.inflate(R.layout.fragment_login, container, false)
        errorTextView = view.findViewById<TextView>(R.id.loginErrorTxt)

//        println(loginViewModel)
//        loginViewModel.loginState.observe(this.requireActivity(), Observer<LoginViewState> { state ->
//            when(state){
//                is LoginSuccess -> {
//                    authenticateSuccess()
//                }
//                is LoginError -> {
//                    errorTextView.visibility = View.VISIBLE
//                }
//            }
//        })

        setupView(view)
        return view
    }


    private fun setupView(view : View) {
        val usernameEditText = view.findViewById<EditText>(R.id.accountEdtTxt)
        val passwordEditText = view.findViewById<EditText>(R.id.passwordEdtTxt)

        val loginByPass = view.findViewById<Button>(R.id.loginByPass)
        val backBtn = view.findViewById<Button>(R.id.backBtn)
        val loginByFingerPrint = view.findViewById<Button>(R.id.loginByFingerPrint)

        loginByPass.setOnClickListener {
            CustomAlertClass(
                this.requireActivity(),
                "Error",
                "Quý khách vui lòng quét vân tay để đăng nhập. (Lưu ý: Quý khách có thể sử dụng các vân tay đã cài đặt thành công trên thiết bị.)"
            ).show()
        }
        backBtn.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }
        if (isBiometricSupported()) {
            showBiometricPrompt(::authenticateSuccess)
        }
        loginByFingerPrint.setOnClickListener {
            if (isBiometricSupported()) {
                showBiometricPrompt(::authenticateSuccess)
            } else {
                showMessage("Thiết bị của bạn không hỗ trợ bảo mật vân tay.")
            }
        }
    }
    private fun showBiometricPrompt(onSuccess: () -> (Unit)) {
        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Vân tay")
            .setSubtitle("Quý khách vui lòng quét vân tay để đăng nhập. (Lưu ý: Quý khách có thể sử dụng các vân tay đã cài đặt thành công trên thiết bị)")
            .setNegativeButtonText("Hủy")
            .build()

        val biometricPrompt = BiometricPrompt(this, ContextCompat.getMainExecutor(this.requireActivity()),
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    // Handle authentication error
                    showMessage("Lỗi xác minh: $errString")
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    // Handle authentication success
                    showMessage("Xác minh thành công.")
                    onSuccess()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    // Handle authentication failure
                    showMessage("Xác minh thất bại.")
                }
            })

        biometricPrompt.authenticate(promptInfo)
    }


    private fun showMessage(message: String) {
        Toast.makeText(this.requireActivity(), message, Toast.LENGTH_SHORT).show()
    }

    private fun isBiometricSupported(): Boolean {
        val biometricManager = BiometricManager.from(this.requireActivity())
        when (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_WEAK)) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                // The user can authenticate with biometrics, continue with the authentication process
                return true
            }

            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE, BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE, BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                // Handle the error cases as needed in your app
                return false
            }

            else -> {
                // Biometric status unknown or another error occurred
                return false
            }
        }
    }

    private fun authenticateSuccess() {
        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
    }

}

sealed class LoginViewState
object LoginSuccess : LoginViewState()
object LoginError : LoginViewState()