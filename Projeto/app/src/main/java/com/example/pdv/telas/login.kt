package com.example.pdv.telas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.pdv.R
import com.google.firebase.auth.FirebaseAuth

class login : AppCompatActivity() {

    lateinit var et_email : EditText
    lateinit var et_senha : EditText
    lateinit var bt_entrar : Button
    lateinit var bt_cadastrar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        et_email = findViewById(R.id.ed_TL_email)
        et_senha = findViewById(R.id.ed_TL_senha)
        bt_entrar = findViewById(R.id.bt_TL_entrar)
        bt_cadastrar = findViewById(R.id.bt_TL_cadastro)

        bt_cadastrar.setOnClickListener {
            val intent = Intent(this, cadastro::class.java)
            startActivity(intent)
        }

        bt_entrar.setOnClickListener {
            val auth = FirebaseAuth.getInstance()

            var password: String = et_senha.getText().toString()
            var email: String = et_email.getText().toString()

            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Login bem-sucedido
                    val user = auth.currentUser
                    val tela_nova= Intent(this, Principio::class.java)
                    startActivity(tela_nova)
                } else {
                    Toast.makeText(this, "Deu ruim seu login!!", Toast.LENGTH_LONG).show()
                }
            }
        }

    }
}