package mx.tecnm.cdhidalgo.simb

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import org.json.JSONObject

class Cuenta : AppCompatActivity() {
    private lateinit var editarbtn : Button
    private lateinit var NombreCuenta : TextView
    private lateinit var TelefonoCuenta : TextView
    private lateinit var CorreoCuenta : TextView
    private lateinit var ContraseñaCuenta : TextView
    private lateinit var sesion: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cuenta)

        NombreCuenta = findViewById(R.id.tvNombreCuentaEdit)
        TelefonoCuenta = findViewById(R.id.tvTelefonoCuentaEdit)
        CorreoCuenta = findViewById(R.id.tvMailCuentaEdit)
        ContraseñaCuenta = findViewById(R.id.tvPassCuentaEdit)

        editarbtn = findViewById(R.id.tveditarCuentaEdit)

        sesion = getSharedPreferences("sesion", Context.MODE_PRIVATE)

        editarbtn.setOnClickListener {
            startActivity(Intent(this, EditCuenta::class.java))
        }

        obtenerDatos()
    }

    private fun obtenerDatos() {
        val url = Uri.parse(Config.URL + "ruta_para_obtener_datos")//poner la direccion correcta gay
            .buildUpon()
            .build().toString()

        val peticion = object: StringRequest(Request.Method.GET, url, { response ->
            val datosUsuario = JSONObject(response)

            NombreCuenta.text = datosUsuario.getString("nombre")
            TelefonoCuenta.text = datosUsuario.getString("telefono")
            ContraseñaCuenta.text = datosUsuario.getString("correo")
            ContraseñaCuenta.text = datosUsuario.getString("contraseña")
        }, { error ->
            Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show()
            Log.e("DATOS_USUARIO", error.stackTraceToString())
        }) {
            override fun getHeaders(): Map<String, String> {
                val headers: MutableMap<String, String> = HashMap()
                headers["Authorization"] = "Bearer " + sesion.getString("jwt", "")
                return headers
            }
        }

        MySingleton.getInstance(applicationContext).addToRequestQueue(peticion)
    }
}
