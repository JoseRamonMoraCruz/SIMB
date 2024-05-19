package mx.tecnm.cdhidalgo.simb

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class crearcuentaInterfaz : AppCompatActivity() {
    private lateinit var TengoCuenta : Button
    private lateinit var CrearCuenta : Button
    private lateinit var Nombre : EditText
    private lateinit var Telefono : EditText
    private lateinit var Correo : EditText
    private lateinit var Contraseña : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crearcuenta_interfaz)

        TengoCuenta = findViewById(R.id.btnTengocuentalogout)
        CrearCuenta = findViewById(R.id.btnCrearlogout)
        Nombre = findViewById(R.id.etNombreCompletologout)
        Telefono = findViewById(R.id.etTelefonologout)
        Correo = findViewById(R.id.etEmaillogout)
        Contraseña= findViewById(R.id.etPasswordlogout)

        TengoCuenta.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        CrearCuenta.setOnClickListener {
            val nombre = Nombre.text.toString()
            val telefono = Telefono.text.toString()
            val correo = Correo.text.toString()
            val contraseña = Contraseña.text.toString()

            val url = Uri.parse(Config.URL + "crear-cuenta") // Usamos Config.URL + "crear-cuenta"
                .buildUpon()
                .build().toString()

            val peticion = object: StringRequest(Request.Method.POST, url, { response ->
                val intent = Intent(this, Cuenta::class.java)
                intent.putExtra("nombre", nombre)
                intent.putExtra("telefono", telefono)
                intent.putExtra("correo", correo)
                intent.putExtra("contraseña", contraseña)
                startActivity(intent)
            }, { error ->
                Toast.makeText(this, "Error al crear la cuenta: ${error.message}", Toast.LENGTH_LONG).show()
            }) {
                override fun getParams(): Map<String, String> {
                    val params = HashMap<String, String>()
                    params["nombre"] = nombre
                    params["telefono"] = telefono
                    params["correo"] = correo
                    params["contraseña"] = contraseña
                    return params
                }
            }

            Volley.newRequestQueue(this).add(peticion)
        }
    }
}
