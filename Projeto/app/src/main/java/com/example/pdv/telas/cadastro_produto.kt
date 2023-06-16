package com.example.pdv.telas

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.pdv.R
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class cadastro_produto : AppCompatActivity() {

    private val PICK_IMAGE_REQUEST = 77
    private var filePath: Uri? = null

    lateinit var et_nome: EditText
    lateinit var et_id: EditText
    lateinit var et_quantidade: EditText
    lateinit var et_descricao: EditText
    lateinit var bt_CP_imagem: Button
    lateinit var bt_CP_cadastrar: Button

    fun limpa_tela() {
        et_nome.text.clear()
        et_id.text.clear()
        et_quantidade.text.clear()
        et_descricao.text.clear()
    }
    fun chooseImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
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
        setContentView(R.layout.activity_cadastro_produto)

        et_nome = findViewById(R.id.et_CP_nome_produto)
        et_id = findViewById(R.id.et_CP_id)
        et_quantidade = findViewById(R.id.et_CP_quantidade)
        et_descricao = findViewById(R.id.et_CP_descricao)
        bt_CP_imagem = findViewById(R.id.bt_CP_imagem)
        bt_CP_cadastrar = findViewById(R.id.bt_CP_cadastro)

        bt_CP_imagem.setOnClickListener {
            chooseImage()
        }

        bt_CP_cadastrar.setOnClickListener {
            saveProduct()
        }

        val REQUEST_CODE = 1

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
            // Permissão não concedida, solicitar permissão
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),REQUEST_CODE)
        } else {
            Toast.makeText(this, "Permissão concedida para escolher imagem", Toast.LENGTH_LONG).show()
        }
    }
    fun saveProduct() {
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
                    val product = hashMapOf(
                        "NomeProduto" to productName,
                        "ID" to productCode,
                        "Quantidade" to quantidade,
                        "Descrição" to descricao,
                        "imageUrl" to imageUrl
                    )
                    productRef.set(product)
                        .addOnSuccessListener { Toast.makeText(this, "Produto criado com sucesso!", Toast.LENGTH_LONG).show()
                            limpa_tela()
                            finish()
                        }
                        .addOnFailureListener { e ->
                            Toast.makeText(this, "Deu ruim ao criar o produto!", Toast.LENGTH_LONG).show()
                        }
                }
            }
        }
    }
}