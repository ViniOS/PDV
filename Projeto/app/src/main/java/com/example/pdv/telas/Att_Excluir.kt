package com.example.pdv.telas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.pdv.R


class Att_Excluir : AppCompatActivity() {

    lateinit var et_nome: EditText
    lateinit var et_id: EditText
    lateinit var et_quantidade: EditText
    lateinit var et_descricao: EditText
    lateinit var bt_Att: Button
    lateinit var bt_Excluir: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_att_excluir)

        et_nome = findViewById(R.id.et_ATT_EX_produto)
        et_id = findViewById(R.id.et_ATT_EX_id)
        et_quantidade = findViewById(R.id.et_ATT_EX_qtd)
        et_descricao = findViewById(R.id.et_ATT_EX_descricao)
        bt_Att = findViewById(R.id.bt_ATT_EX_att)
        bt_Excluir = findViewById(R.id.bt_ATT_EX_excluir)

    }
}