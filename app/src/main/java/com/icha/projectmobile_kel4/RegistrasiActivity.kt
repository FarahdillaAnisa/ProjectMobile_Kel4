package com.icha.projectmobile_kel4

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
import java.util.regex.Pattern

class RegistrasiActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance();

        register_button.setOnClickListener {

            if (username.text.trim().toString().isNotEmpty() || epassword.text.trim().toString().isNotEmpty()) {
                createUser(username.text.trim().toString(), epassword.text.trim().toString())
            } else {
                Toast.makeText(this, "Input Required", Toast.LENGTH_SHORT).show()
            }
        }
        btnLogin.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
    fun createUser(email:String, password:String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){ task ->
                if(task.isSuccessful){
                    Log.e("Task Message", "Succesful ...");
                    var intent = Intent(this, HomeActivity::class.java);
                    startActivity(intent);
                }else{
                    Log.e("Task Message", "Failed ..."+task.exception)
                }
            }
    }
}