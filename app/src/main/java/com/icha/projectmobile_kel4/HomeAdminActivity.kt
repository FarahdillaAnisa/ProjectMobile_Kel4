package com.icha.projectmobile_kel4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_home_admin.*

class HomeAdminActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_admin)

        val gg = findViewById<ImageView>(R.id.btnMenu)
        gg.setOnClickListener{
            val intent1 = Intent(this, admin_tambahmenu::class.java)
            startActivity(intent1)
        }
    }
}