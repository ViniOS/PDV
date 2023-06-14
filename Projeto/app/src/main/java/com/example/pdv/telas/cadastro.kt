package com.example.pdv.telas

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.pdv.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.FirebaseUser

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

        fun updateUI(user: FirebaseUser?) {
            if (user != null) {
                Toast.makeText(this, "Bem-vindo ${user.email}!", Toast.LENGTH_LONG).show()
                val intent = Intent(this, pos_cadastro::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Falha na autenticação.", Toast.LENGTH_LONG).show()
            }
        }

        fun CadastroDEuser() {
            var password: String = et_senha.getText().toString()
            var email: String = et_email.getText().toString()

            val auth = FirebaseAuth.getInstance()
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        try {
                            throw task.exception!!
                        } catch (e: FirebaseAuthWeakPasswordException) {
                            Toast.makeText(this, "A senha é muito fraca.", Toast.LENGTH_LONG).show()
                        } catch (e: FirebaseAuthUserCollisionException) {
                            Toast.makeText(
                                this,
                                "Já existe uma conta com este endereço de e-mail.",
                                Toast.LENGTH_LONG
                            ).show()
                        } catch (e: Exception) {
                            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
                        } catch (e: FirebaseAuthInvalidCredentialsException) {
                            Toast.makeText(
                                this,
                                "As credenciais fornecidas são inválidas.",
                                Toast.LENGTH_LONG
                            ).show()
                            updateUI(null)
                        }
                    }
                }
        }

        bt_cadastrar.setOnClickListener {

            var nome: String = et_nome.getText().toString()
            var senha: String = et_senha.getText().toString()
            var email: String = et_email.getText().toString()
            var cpf: String = et_cpf.getText().toString()
            var cnpj: String = et_cnpj.getText().toString()
            var empresa: String = et_empresa.getText().toString()

            if (nome.isEmpty() || senha.isEmpty() || email.isEmpty() || cpf.isEmpty() || cnpj.isEmpty() || empresa.isEmpty()) {
                var ok = Toast.makeText(this, "Preencha todas as informações", Toast.LENGTH_LONG)
                ok.show();
            } else {
                CadastroDEuser()
            }
        }
    }
}
