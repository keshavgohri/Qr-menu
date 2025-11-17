package com.vms.qrmenu.set_password

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.vms.qrmenu.R
import com.vms.qrmenu.databinding.ActivitySetPasswordBinding
import com.vms.qrmenu.login.LoginActivity

class SetPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySetPasswordBinding
    private lateinit var editPassword: EditText
    private lateinit var editConfirmPassword: EditText
    private lateinit var imgVisiblePassword: ImageView
    private lateinit var imgInvisiblePassword: ImageView
    private lateinit var imgVisibleConfirmPassword: ImageView
    private lateinit var imgInvisibleConfirmPassword: ImageView
    private lateinit var submit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        editPassword = binding.editPassword
        editConfirmPassword = binding.editConfirmPassword
        imgVisiblePassword = binding.imgVisibilityOn1
        imgInvisiblePassword = binding.imgVisibilityOff1
        imgVisibleConfirmPassword = binding.imgVisibilityOn2
        imgInvisibleConfirmPassword = binding.imgVisibilityOff2
        submit = binding.btnSubmit

        imgInvisiblePassword.setOnClickListener {
            imgInvisiblePassword.visibility = View.GONE
            imgVisiblePassword.visibility = View.VISIBLE
            editPassword.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            editPassword.setSelection(editPassword.text?.length ?: 0)
        }

        imgVisiblePassword.setOnClickListener {
            imgVisiblePassword.visibility = View.GONE
            imgInvisiblePassword.visibility = View.VISIBLE

            editPassword.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            editPassword.setSelection(editPassword.text?.length ?: 0)
        }

        imgInvisibleConfirmPassword.setOnClickListener {
            imgInvisibleConfirmPassword.visibility = View.GONE
            imgVisibleConfirmPassword.visibility = View.VISIBLE
            editConfirmPassword.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            editConfirmPassword.setSelection(editConfirmPassword.text?.length ?: 0)
        }

        imgVisibleConfirmPassword.setOnClickListener {
            imgVisibleConfirmPassword.visibility = View.GONE
            imgInvisibleConfirmPassword.visibility = View.VISIBLE

            editConfirmPassword.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            editConfirmPassword.setSelection(editConfirmPassword.text?.length ?: 0)
        }

        submit.setOnClickListener {
            val dialogView = layoutInflater.inflate(R.layout.dialog_password_change, null)

            val dialog = AlertDialog.Builder(this, R.style.TransparentAlertDialog)
                .setView(dialogView)
                .setCancelable(false)
                .create()

            dialogView.findViewById<TextView>(R.id.btn_login).setOnClickListener {
                dialog.dismiss()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
                // Navigate to login screen here
            }

            dialog.show()

        }



    }
}