package com.example.pdv.telas

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.pdv.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.util.Locale

class Mostrar : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar)

        val linearLayout = findViewById<LinearLayout>(R.id.linearLayout)

        val userId = Firebase.auth.currentUser?.uid

        val db = FirebaseFirestore.getInstance()
        val productsRef = db.collection("products").whereEqualTo("userId",userId)
        productsRef.get()
            .addOnSuccessListener { documents ->
                if (documents.isEmpty) {
                    val noProductsTextView = TextView(this)
                    noProductsTextView.text = "Sem produtos"
                    linearLayout.addView(noProductsTextView)
                } else {
                    for (document in documents) {
                        val productName = document.getString("NomeProduto")
                        val productCode = document.getString("ID")
                        val productQuantity = document.getLong("Quantidade")
                        val productDescription = document.getString("Descrição")
                        val productImageUrl = document.getString("imageUrl")

                        /* var dateString: String? = null
                        try {
                            val productDate = document.getTimestamp("dataInsercao")
                            if (productDate != null) {
                                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                                dateString = dateFormat.format(productDate.toDate())
                            }
                        } catch (e: Exception) {
                            Log.e("ERRU", "ERRU DATAFORM", e)
                        }

                        if (dateString != null) {
                            val dateTextView = TextView(this)
                            dateTextView.text = "Data: $dateString"
                            linearLayout.addView(dateTextView)
                        }
                        */

                        val productDate = document.getTimestamp("dataInsercao")?.toDate()
                        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                        val formattedDate = dateFormat.format(productDate)

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

                        val dateTextView = TextView(this)
                        dateTextView.text = "Data: $formattedDate"
                        linearLayout.addView(dateTextView)

                        val imageView = ImageView(this)
                        Glide.with(this)
                            .load(productImageUrl)
                            .into(imageView)
                        linearLayout.addView(imageView)
                    }
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "Deu ruim para MOSTRAR!", Toast.LENGTH_LONG).show()
            }
    }
}
