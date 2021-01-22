package com.icha.projectmobile_kel4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.icha.projectmobile_kel4.menufragment.RegisterActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.password
import kotlinx.android.synthetic.main.activity_register.register_button
import kotlinx.android.synthetic.main.activity_register.username
import kotlinx.android.synthetic.main.login.*
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        register_button.setOnClickListener {
            Intent(this@LoginActivity,RegisterActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}