package mx.tecnm.cdhidalgo.simb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Cuenta : AppCompatActivity() {
    private lateinit var editarbtn : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cuenta)

        editarbtn = findViewById(R.id.tveditarCuentaEdit)

        editarbtn.setOnClickListener {
            startActivity(Intent(this, EditCuenta::class.java))
        }
    }
}