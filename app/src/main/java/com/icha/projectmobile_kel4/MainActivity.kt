package com.icha.projectmobile_kel4

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.navigation.ui.AppBarConfiguration
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_login.*

class MainActivity : AppCompatActivity() {

    private lateinit var regisButton: Button
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        regisButton = findViewById(R.id.regis)
        loginButton = findViewById(R.id.login)

        loginButton.setOnClickListener{view ->
            val intent = Intent(view.context, LoginActivity::class.java)
            view.context.startActivity(intent)
        }
        regisButton.setOnClickListener{view ->
            val intent = Intent(view.context, RegistrasiActivity::class.java)
            view.context.startActivity(intent)
        }
    }
}