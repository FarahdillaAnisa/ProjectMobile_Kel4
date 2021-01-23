package com.icha.projectmobile_kel4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.navigation.ui.AppBarConfiguration
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth;
    private lateinit var regisButton: Button
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance();

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

    override fun onStart() {
        super.onStart()
        val user = auth.currentUser;

        if(user != null){
            var intent = Intent(this, HomeActivity::class.java);
            startActivity(intent);
        }
    }
}