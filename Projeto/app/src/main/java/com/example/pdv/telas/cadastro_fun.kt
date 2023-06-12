package com.example.pdv.telas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Spinner
import com.example.pdv.R

class cadastro_fun : AppCompatActivity() {

    lateinit var et_email: EditText
    lateinit var et_senha: EditText
    lateinit var et_nome: EditText
    lateinit var et_cpf: EditText
    lateinit var spin_cargoFun: Spinner
    lateinit var spin_unidades: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_fun)

        et_email = findViewById(R.id.et_CF_email)
        et_senha = findViewById(R.id.et_CF_senha)
        et_nome = findViewById(R.id.et_CF_cpf)
        et_cpf = findViewById(R.id.et_CF_nome)
        spin_cargoFun = findViewById(R.id.spin_CF_cargoFun)
        spin_unidades = findViewById(R.id.spin_CF_unidade)
    }
}