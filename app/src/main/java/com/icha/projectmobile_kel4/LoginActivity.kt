package com.icha.projectmobile_kel4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance();

        login_button.setOnClickListener {
            if(eusername.text.trim().toString().isNotEmpty() || epassword.text.trim().toString().isNotEmpty()){
                signUser(eusername.text.trim().toString(), epassword.text.trim().toString())
            }else{
                Toast.makeText(this, "Input Required", Toast.LENGTH_SHORT).show()
            }
        }
        btnRegis.setOnClickListener{
            val intent = Intent(this, RegistrasiActivity::class.java)
            startActivity(intent)
        }
    }
    fun signUser(email:String, password:String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){ task ->
                if(task.isSuccessful){
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "Error"+task.exception, Toast.LENGTH_SHORT).show()
                }
            }
    }
}