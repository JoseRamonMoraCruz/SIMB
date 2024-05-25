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
    private lateinit var tengoCuentaButton: Button
    private lateinit var crearCuentaButton: Button
    private lateinit var nombreEditText: EditText
    private lateinit var telefonoEditText: EditText
    private lateinit var correoEditText: EditText
    private lateinit var contraseñaEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crearcuenta_interfaz)

        tengoCuentaButton = findViewById(R.id.btnTengocuentalogout)
        crearCuentaButton = findViewById(R.id.btnCrearlogout)
        nombreEditText = findViewById(R.id.etNombreCompletologout)
        telefonoEditText = findViewById(R.id.etTelefonologout)
        correoEditText = findViewById(R.id.etEmaillogout)
        contraseñaEditText = findViewById(R.id.etPasswordlogout)

        tengoCuentaButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        crearCuentaButton.setOnClickListener {
            val nombreComp = nombreEditText.text.toString()
            val telefono = telefonoEditText.text.toString()
            val correo = correoEditText.text.toString()
            val password = contraseñaEditText.text.toString()

            // Cambia la URL a la correcta para la creación de cuentas
            val url = Uri.parse(Config.URL + "users")
                .buildUpon()
                .build().toString()

            val request = object : StringRequest(Request.Method.POST, url,
                { response ->
                    // Si la creación de cuenta es exitosa, redirige al usuario
                    val intent = Intent(this, Cuenta::class.java)
                    intent.putExtra("correo", correo)
                    intent.putExtra("password", password)
                    intent.putExtra("nombreComp", nombreComp)
                    intent.putExtra("telefono", telefono)
                    startActivity(intent)
                },
                { error ->
                    // Manejo de errores al crear la cuenta
                    Toast.makeText(this, "Error al crear la cuenta: ${error.message}", Toast.LENGTH_LONG).show()
                }) {
                override fun getParams(): Map<String, String> {
                    val params = HashMap<String, String>()
                    params["correo"] = correo
                    params["password"] = password
                    params["nombreComp"] = nombreComp
                    params["telefono"] = telefono
                    return params
                }
            }

            Volley.newRequestQueue(this).add(request)
        }
    }
}
