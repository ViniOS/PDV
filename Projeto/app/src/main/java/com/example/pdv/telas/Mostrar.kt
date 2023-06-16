package com.example.pdv.telas

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.pdv.R
import com.google.firebase.firestore.FirebaseFirestore

class Mostrar : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar)

        val linearLayout = findViewById<LinearLayout>(R.id.linearLayout)

        val db = FirebaseFirestore.getInstance()
        val productsRef = db.collection("products")
        productsRef.get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val productName = document.getString("NomeProduto")
                    val productCode = document.getString("ID")
                    val productQuantity = document.getLong("Quantidade")
                    val productDescription = document.getString("Descrição")
                    val productImageUrl = document.getString("imageUrl")

                    val nameTextView = TextView(this)
                    nameTextView.text = "Nome: $productName"
                    linearLayout.addView(nameTextView)

                    val codeTextView = TextView(this)
                    codeTextView.text = "Código: $productCode"
                    linearLayout.addView(codeTextView)

                    val quantityTextView = TextView(this)
                    quantityTextView.text = "Quantidade: $productQuantity"
                    linearLayout.addView(quantityTextView)

                    val descriptionTextView = TextView(this)
                    descriptionTextView.text = "Descrição: $productDescription"
                    linearLayout.addView(descriptionTextView)

                    val imageView = ImageView(this)
                    Glide.with(this)
                        .load(productImageUrl)
                        .into(imageView)
                    linearLayout.addView(imageView)
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "Deu ruim para MOSTRAR!", Toast.LENGTH_LONG).show()
            }
    }
}
