package mx.tecnm.cdhidalgo.simb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class crearcuentaInterfaz : AppCompatActivity() {
    private lateinit var TengoCuenta : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crearcuenta_interfaz)

        TengoCuenta = findViewById(R.id.btnTengocuentalogout)


        TengoCuenta.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}