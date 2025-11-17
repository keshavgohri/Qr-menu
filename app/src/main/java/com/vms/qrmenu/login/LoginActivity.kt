package com.vms.qrmenu.login

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.vms.qrmenu.databinding.ActivityLoginBinding
import com.vms.qrmenu.forgot_password.ForgotPasswordActivity
import com.vms.qrmenu.home.HomeActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var imgEyeVisible: ImageView
    private lateinit var imgEyeInvisible: ImageView
    private lateinit var forgotPassword: TextView
    private lateinit var login: Button
    private lateinit var editEmail: EditText
    private lateinit var editPassword: EditText
    private lateinit var checkRemember: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imgEyeVisible = binding.imgVisibilityOn
        imgEyeInvisible = binding.imgVisibilityOff
        forgotPassword = binding.txtForgotPassword
        login = binding.btnLogin
        editEmail = binding.editEmail
        editPassword = binding.editPassword
        checkRemember = binding.checkRememberMe

        imgEyeInvisible.setOnClickListener {
            imgEyeInvisible.visibility = View.GONE
            imgEyeVisible.visibility = View.VISIBLE
            editPassword.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            editPassword.setSelection(editPassword.text?.length ?: 0)
        }

        imgEyeVisible.setOnClickListener {
            imgEyeVisible.visibility = View.GONE
            imgEyeInvisible.visibility = View.VISIBLE

            editPassword.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            editPassword.setSelection(editPassword.text?.length ?: 0)
        }

        forgotPassword.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }

        login.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}