package com.vms.qrmenu.verify_otp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.vms.qrmenu.databinding.ActivityVerifyOtpBinding
import com.vms.qrmenu.set_password.SetPasswordActivity

class VerifyOtpActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVerifyOtpBinding
    private lateinit var txtBack: TextView
    private lateinit var btnVerify: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerifyOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        txtBack = binding.txtBack
        btnVerify = binding.btnVerify

        txtBack.setOnClickListener {
            finish()
        }

        btnVerify.setOnClickListener {
            startActivity(Intent(this, SetPasswordActivity::class.java))
            finish()
        }
    }
}