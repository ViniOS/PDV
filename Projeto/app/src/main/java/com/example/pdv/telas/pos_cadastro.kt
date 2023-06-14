package com.example.pdv.telas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.pdv.R

class pos_cadastro : AppCompatActivity() {
    lateinit var bt_login: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pos_cadastro)

        bt_login = findViewById(R.id.botao_login)

        bt_login.setOnClickListener {
            val intent = Intent(this, login::class.java)
            startActivity(intent)
        }
    }
}