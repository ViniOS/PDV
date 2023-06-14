package com.example.pdv.telas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.pdv.R

class Acoes : AppCompatActivity() {

    lateinit var bt_inserir : Button
    lateinit var bt_att_del : Button
    lateinit var bt_mostrar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acoes)

        bt_inserir = findViewById(R.id.bt_AC_inserir)
        bt_att_del = findViewById(R.id.bt_AC_att_del)
        bt_mostrar = findViewById(R.id.bt_AC_mostra)
    }
}