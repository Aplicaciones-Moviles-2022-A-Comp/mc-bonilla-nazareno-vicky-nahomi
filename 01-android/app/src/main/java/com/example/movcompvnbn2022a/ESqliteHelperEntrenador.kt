package com.example.movcompvnbn2022a

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESqliteHelperEntrenador (contexto: Context?,
) : SQLiteOpenHelper(contexto, "moviles", null,1 ){
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaUsuario=
            """
                CREATE TABLE ENTRENADOR(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre VARCHAR (50),
                descripcion VARCHAR(50)
                )
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaUsuario)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //se puede dejar vacío
    }
    fun crearEntrenador(
        nombre:String,
        descripcion:String
    ): Boolean{
        /*DOS TIPOS DE BASE DE DATOS
        this.readableDatabase //lectura
        this.writableDatabase //escritura*/
        val basedatosEscritura= writableDatabase
        val valoresAGuardar=ContentValues()
        valoresAGuardar.put("nombre",nombre)
        valoresAGuardar.put("descripcion",descripcion)
        val resultadoGuardar=basedatosEscritura
            .insert(
                "ENTRENADOR",
                null,
                valoresAGuardar
            )
        basedatosEscritura.close()
        return if (resultadoGuardar.toInt() == -1) false else true
    }
}