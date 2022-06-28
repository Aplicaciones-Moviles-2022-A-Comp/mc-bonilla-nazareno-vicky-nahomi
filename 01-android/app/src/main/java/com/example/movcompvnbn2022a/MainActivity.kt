package com.example.movcompvnbn2022a

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import io.sentry.Sentry
import io.sentry.SentryLevel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Sentry.captureMessage("testing SDK setup",SentryLevel.INFO)
        //Sentry.captureMessage("ESTO ES UNA ADVERTENCIA",SentryLevel.WARNING)

        val botonACicloVida=findViewById<Button>(R.id.btn_ciclo_vida)
        botonACicloVida
            .setOnClickListener{
                irActividad(ACicloVida::class.java)
            }
        val botonActividadContador=findViewById<Button>(R.id.btn_a_contador)
        botonActividadContador
            .setOnClickListener{
                irActividad(Acontador2::class.java)
            }
        val botonListView=findViewById<Button>(R.id.btn_ir_list_view)
        botonListView
            .setOnClickListener{
                irActividad(BListView::class.java)
            }

    }
    fun irActividad(
        clase: Class<*>
    ){
        val intent= Intent(this,clase)
        startActivity(intent) //definido en la clase AppCompatActivity() heredada
    }
}