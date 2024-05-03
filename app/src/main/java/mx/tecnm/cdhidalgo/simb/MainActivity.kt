package mx.tecnm.cdhidalgo.simb

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest

class MainActivity : AppCompatActivity() {
    // Declaración de variables para los elementos de la vista
    private lateinit var etMail: EditText
    private lateinit var etPass: EditText
    private lateinit var btnEntrar: Button
    private lateinit var btnCrearCuenta: Button
    private lateinit var sesion: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Inicialización de las variables con los elementos
        etMail = findViewById(R.id.etLoginEmail)
        etPass = findViewById(R.id.etLoginPass)
        btnEntrar = findViewById(R.id.btnEntrarLogin)
        btnCrearCuenta = findViewById(R.id.btnNewCountLogin)

        // Inicialización de la variable de sesión
        sesion = getSharedPreferences(Config.PREF_NAME, 0)

        btnCrearCuenta.setOnClickListener { login() }
    }

    private fun login() {
        // URL de la petición de login utilizando Config.URL_BASE
        val url = Uri.parse(Config.URL_BASE + "login")
            .buildUpon()
            .build().toString()
        // Debe crearse una petición de tipo StringRequest con el método POST para la URL de login
        // debido a que el servidor siempre devuelve un string con el token
        val peticion = object : StringRequest(Request.Method.POST, url, { response ->
            // Si la petición es exitosa, el token está en la respuesta
            with(sesion.edit()) {
                putString(Config.PREF_KEY_JWT, response)
                putString(Config.PREF_KEY_USERNAME, etMail.text.toString())
                apply()
            }
            // Se inicia la actividad MainActivity2
            startActivity(Intent(this, crearcuentaInterfaz::class.java))
        }, { error ->
            // Si la petición falla, se muestra un mensaje de error
            Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show()
            // Se muestra la traza completa del error en el Logcat
            Log.e("LOGIN", error.stackTraceToString())
        }) {
            // Se envían los datos de la petición usuario y contraseña para el post
            override fun getParams(): Map<String, String> {
                val body: MutableMap<String, String> = HashMap()
                body["username"] = etMail.text.toString()
                body.put("password", etPass.text.toString())
                return body
            }
        }
        // Se agrega la petición a la cola de peticiones para que sea procesada
        RequestManager.getInstance(applicationContext).addToRequestQueue(peticion)
    }
}
