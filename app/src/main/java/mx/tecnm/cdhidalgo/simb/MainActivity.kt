package mx.tecnm.cdhidalgo.simb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var btnCrearCuenta:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCrearCuenta = findViewById(R.id.btnNewCountLogin)

        btnCrearCuenta.setOnClickListener {
            val intent = Intent(this, crearcuentaInterfaz::class.java)
            startActivity(intent)
        }
    }
}