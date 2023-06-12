package com.example.pdv.telas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import com.example.pdv.R

class Unidades : AppCompatActivity() {
    lateinit var nome_unidade: EditText
    lateinit var cnpj_unidade: EditText
    lateinit var endereco_unidade: EditText
    lateinit var botao_cadastro: Button
    lateinit var checkbox: CheckBox
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_unidade)

        nome_unidade = findViewById(R.id.et_TU_nomeUnidade)
        cnpj_unidade = findViewById(R.id.et_TU_cnpj)
        endereco_unidade = findViewById(R.id.et_TU_endereco)
        checkbox = findViewById(R.id.cb_TU_virtual)
        botao_cadastro = findViewById(R.id.bt_TU_cadastrar)
    }
}