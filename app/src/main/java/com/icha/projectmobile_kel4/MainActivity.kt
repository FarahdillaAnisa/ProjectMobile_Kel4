package com.icha.projectmobile_kel4

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.password
import kotlinx.android.synthetic.main.activity_register.register_button
import kotlinx.android.synthetic.main.activity_register.username
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.login.login_button
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*login_button.setOnClickListener{
            Intent(this@MainActivity, LoginActivity::class.java).also {
                startActivity(it)
            }
        }*/

        auth = FirebaseAuth.getInstance()

        register_button.setOnClickListener{
            val email = username.text.toString().trim()
            val password = epassword.text.toString().trim()

            if (email.isEmpty()){
                username.error = "Email Harus Diisi"
                username.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                username.error = "Email tidak valid"
                username.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty() || password.length < 6){
                epassword.error = "Password harus lebih dari 6 karakter"
                epassword.requestFocus()
                return@setOnClickListener
            }

            registerUser(email, password)
            }
        }
    private fun registerUser(email:String, password:String){
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                if(it.isSuccessful){
                    Intent(this@MainActivity,HomeActivity::class.java).also {
                        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(it)
                    }
                    }else{
                    Toast.makeText(this,it.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
    }
}