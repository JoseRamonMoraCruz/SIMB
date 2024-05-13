package mx.tecnm.cdhidalgo.simb

import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class Inicio : AppCompatActivity(){

    // Declaración de variables para los elementos de la vista
    private lateinit var btnMenu: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        // Inicialización de las variables con los elementos

         btnMenu = findViewById(R.id.btnMenu)

        btnMenu.setOnClickListener {
            val intent = Intent(this, MainActivityMenu::class.java)
            startActivity(intent)
        }

    }
}
