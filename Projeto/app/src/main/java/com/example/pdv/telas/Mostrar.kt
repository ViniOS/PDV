package com.example.pdv.telas

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.pdv.R

class Mostrar : AppCompatActivity() {

    lateinit var lv_produtos : ListView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar)
        lv_produtos = findViewById(R.id.lv_MT_listview)

    }
}