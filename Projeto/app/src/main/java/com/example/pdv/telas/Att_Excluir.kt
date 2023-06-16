package com.example.pdv.telas

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pdv.R
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage


class Att_Excluir : AppCompatActivity() {

    private val PICK_IMAGE_REQUEST = 77
    private var filePath: Uri? = null

    lateinit var et_nome: EditText
    lateinit var et_id: EditText
    lateinit var et_quantidade: EditText
    lateinit var et_descricao: EditText
    lateinit var bt_Att: Button
    lateinit var bt_Excluir: Button
    lateinit var bt_imagem: Button
    fun limpa_tela() {
        et_nome.text.clear()
        et_id.text.clear()
        et_quantidade.text.clear()
        et_descricao.text.clear()
    }

    fun chooseImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_PICK
        startActivityForResult(Intent.createChooser(intent, "Selecione uma imagem"), PICK_IMAGE_REQUEST)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            filePath = data.data
        }
    }
    fun uploadImage(imageUri: Uri, onSuccess: (String) -> Unit) {
        val storageRef = Firebase.storage.reference
        val imageRef = storageRef.child("images/${imageUri.lastPathSegment}")
        val uploadTask = imageRef.putFile(imageUri)

        uploadTask.continueWithTask { task ->
            if (!task.isSuccessful) {
                task.exception?.let { throw it }
            }
            imageRef.downloadUrl
        }.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val downloadUri = task.result
                onSuccess(downloadUri.toString())
            } else {
                // Handle failures
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_att_excluir)

        et_nome = findViewById(R.id.et_ATT_EX_produto)
        et_id = findViewById(R.id.et_ATT_EX_id)
        et_quantidade = findViewById(R.id.et_ATT_EX_qtd)
        et_descricao = findViewById(R.id.et_ATT_EX_descricao)
        bt_Att = findViewById(R.id.bt_ATT_EX_att)
        bt_Excluir = findViewById(R.id.bt_ATT_EX_excluir)
        bt_imagem = findViewById(R.id.bt_ATT_EX_imagem)

        val productName = et_nome.text.toString()
        val productCode = et_id.text.toString()
        val aux = et_quantidade.text.toString()
        val descricao = et_descricao.text.toString()

        fun updateProduct() {
            val productName = et_nome.text.toString()
            val productCode = et_id.text.toString()
            val aux = et_quantidade.text.toString()
            val descricao = et_descricao.text.toString()

            if (productName.isEmpty() || productCode.isEmpty() || descricao.isEmpty() || aux.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_LONG).show()
                return
            }

            val quantidade = aux.toInt()

            if (filePath == null) {
                Toast.makeText(this, "Por favor, selecione uma imagem.", Toast.LENGTH_LONG).show()
                return
            } else {
                filePath?.let { imageUri ->
                    uploadImage(imageUri) { imageUrl ->
                        val db = FirebaseFirestore.getInstance()
                        val productRef = db.collection("products").document(productCode)
                        productRef.update(
                            "NomeProduto", productName,
                            "Quantidade", quantidade,
                            "Descrição", descricao,
                            "imageUrl", imageUrl
                        ).addOnSuccessListener {
                            Toast.makeText(this, "Produto atualizado com sucesso!", Toast.LENGTH_LONG).show()
                            limpa_tela()
                            finish()
                        }.addOnFailureListener { e ->
                            Toast.makeText(this, "Deu ruim ao atualizar o produto!", Toast.LENGTH_LONG).show()
                            Log.e("ATUALIZACAO", "Erro ao atualizar o produto", e)
                        }
                    }
                }
            }
        }

        fun deleteProduct() {
            val productCode = et_id.text.toString()

            if (productCode.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha o campo ID.", Toast.LENGTH_LONG).show()
                return
            }

            val db = FirebaseFirestore.getInstance()
            val productRef = db.collection("products").document(productCode)
            productRef.delete()
                .addOnSuccessListener {
                    Toast.makeText(this, "Produto excluído com sucesso!", Toast.LENGTH_LONG).show()
                    limpa_tela()
                    finish()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Deu ruim ao excluir o produto!", Toast.LENGTH_LONG).show()
                    Log.e("EXCLUSAO", "Erro ao excluir o produto", e)
                }
        }

        bt_imagem.setOnClickListener(){
            chooseImage()
        }

        bt_Att.setOnClickListener(){
            updateProduct()
        }

        bt_Excluir.setOnClickListener(){
            deleteProduct()
        }
    }
}