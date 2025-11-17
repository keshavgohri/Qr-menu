package com.vms.qrmenu.forgot_password

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.vms.qrmenu.databinding.ActivityForgotBinding
import com.vms.qrmenu.login.LoginActivity
import com.vms.qrmenu.verify_otp.VerifyOtpActivity

class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityForgotBinding
    private lateinit var login: TextView
    private lateinit var btnOtp: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotBinding.inflate(layoutInflater)
        setContentView(binding.root)

        login = binding.txtLogin
        btnOtp = binding.btnOtp

        login.setOnClickListener {
            finish()
        }

        btnOtp.setOnClickListener {
            startActivity(Intent(this, VerifyOtpActivity::class.java))
        }
    }
}
