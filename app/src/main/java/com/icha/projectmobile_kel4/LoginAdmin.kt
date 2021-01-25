package com.icha.projectmobile_kel4

import android.app.ProgressDialog
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.register_button
import kotlinx.android.synthetic.main.activity_register.username
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.login_admin.*
import java.util.regex.Pattern

class LoginAdmin : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_admin)

        login_button_admin.setOnClickListener {
            val username = username_admin.text.toString()
            val password = password_admin.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please Insert Username and Password", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            if (username == "admin" || password == "admin") {
                val progressDialog = ProgressDialog(
                    this,
                    R.style.Theme_MaterialComponents_Light_Dialog
                )
                progressDialog.isIndeterminate = true
                progressDialog.setMessage("Loading...")
                progressDialog.show()
                val intent = Intent(this, HomeAdminActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}