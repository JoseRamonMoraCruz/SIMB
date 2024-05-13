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

        btnEntrar.setOnClickListener {
            val intent = Intent(this, Inicio::class.java)
            startActivity(intent)
        }
        btnCrearCuenta.setOnClickListener {
            val intent = Intent(this, crearcuentaInterfaz::class.java)
            startActivity(intent)
        }

    }



}
