package com.example.lucafate

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    private fun showBiometricPrompt(onSuccess: () -> (Unit)) {
        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Sinh trắc học")
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view =  inflater.inflate(R.layout.fragment_login, container, false)
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

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}