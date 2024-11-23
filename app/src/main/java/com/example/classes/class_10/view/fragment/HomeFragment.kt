package com.example.classes.class_10.view.fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.classes.databinding.Class10HomeBinding
import com.example.classes.class_10.model.Item
import com.example.classes.class_10.view.MainActivity
import com.example.classes.class_10.view.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class HomeFragment : Fragment() {
    private lateinit var binding: Class10HomeBinding
    private lateinit var sharedPreferences: SharedPreferences
    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = Class10HomeBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences = requireActivity().getSharedPreferences("shared", Context.MODE_PRIVATE)
        setup()
        listarProducto()
    }

    private fun setup() {
        binding.btnLogOut.setOnClickListener {
            logOut()
        }
        binding.btnGuardarArticulo.setOnClickListener {
            guardarProducto()
        }
        binding.tvTitleEmail.text = sharedPreferences.getString("email",null)
    }
    private fun guardarProducto() {
        val codigo = binding.etCodigo.text.toString()
        val nombre = binding.etNombreArticulo.text.toString()
        val precio = binding.etPrecio.text.toString()

        if (codigo.isNotEmpty() && nombre.isNotEmpty() && precio.isNotEmpty()) {
            val item = Item(codigo.toInt(), nombre, precio.toInt())

            db.collection("articulo").document(item.code.toString()).set(
                hashMapOf(
                    "codigo" to item.code,
                    "nombre" to item.name,
                    "precio" to item.price
                )
            )

            Toast.makeText(context, "Articulo guardado", Toast.LENGTH_SHORT).show()
            limpiarCampos()
            listarProducto()
        } else {
            Toast.makeText(context, "Llene los campos", Toast.LENGTH_SHORT).show()
        }
    }



    private fun listarProducto(){
        db.collection("articulo").get().addOnSuccessListener {
            var data = ""
            for (document in it.documents) {
                // Aquí puedes personalizar cómo deseas mostrar cada artículo en la lista
                data += "Código: ${document.get("codigo")} " +
                        "Nombre: ${document.get("nombre")} " +
                        "Precio: ${document.get("precio")}\n\n"

            }
            binding.tvListProducto.text = data
        }
    }

    private fun dataLogin() {
        val bundle = requireActivity().intent.extras
        val email = bundle?.getString("email")
        binding.tvTitleEmail.text = email ?: ""
        sharedPreferences.edit().putString("email",email).apply()
    }

    private fun logOut() {
        sharedPreferences.edit().clear().apply()
        FirebaseAuth.getInstance().signOut()
        (requireActivity() as MainActivity).apply {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun limpiarCampos() {
        binding.etCodigo.setText("")
        binding.etNombreArticulo.setText("")
        binding.etPrecio.setText("")
    }
}