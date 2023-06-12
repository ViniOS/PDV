package com.example.pdv.telas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.pdv.R

class MainActivity : AppCompatActivity() {
    lateinit var bt_inicial: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bt_inicial =findViewById(R.id.bt_inicial)

        bt_inicial.setOnClickListener {
            val intent = Intent(this, login::class.java)
            startActivity(intent)
        }


    }
}