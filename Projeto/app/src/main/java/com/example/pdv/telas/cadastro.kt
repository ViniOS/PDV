package com.example.pdv.telas

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.pdv.R

class cadastro : AppCompatActivity() {
    lateinit var et_email: EditText
    lateinit var et_senha: EditText
    lateinit var et_nome: EditText
    lateinit var et_cpf: EditText
    lateinit var et_cnpj: EditText
    lateinit var et_empresa: EditText
    lateinit var bt_cadastrar: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        et_email = findViewById(R.id.et_TC_email)
        et_senha = findViewById(R.id.et_TC_senha)
        et_nome = findViewById(R.id.et_TC_nomefull)
        et_cpf = findViewById(R.id.et_TC_cpf)
        et_cnpj = findViewById(R.id.et_TC_cnpj)
        et_empresa = findViewById(R.id.et_TC_nomeEmpresa)
        bt_cadastrar = findViewById(R.id.bt_TC_cadastrar)
    }
}