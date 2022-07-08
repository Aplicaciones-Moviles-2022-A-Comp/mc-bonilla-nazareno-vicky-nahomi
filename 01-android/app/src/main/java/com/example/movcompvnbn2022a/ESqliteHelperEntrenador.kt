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


    fun consultarUsuarioPorId(id: Int): BEntrenador{
        // val baseDatosLectura = this.readableDatabase
        val baseDatosLectura = readableDatabase
        val scriptConsultarUsuario = "SELECT * FROM ENTRENADOR WHERE ID = ${id}"
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarUsuario,
            null
        )
        val existeUsuario = resultadoConsultaLectura.moveToFirst()
        var usuarioEncontrado = BEntrenador( "", "")
        if(existeUsuario){
            do{
                val id = resultadoConsultaLectura.getInt(0) // columna indice 0 -> ID
                val nombre = resultadoConsultaLectura.getString(0) // Columna indice 1 -> NOMBRE
                val descripcion =
                    resultadoConsultaLectura.getString(1) // Columna indice 2 -> DESCRIPCION
                if(id!=null){
                   // usuarioEncontrado.id = id
                    usuarioEncontrado.nombre= nombre
                    usuarioEncontrado.descripcion = descripcion
                }
            }while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return usuarioEncontrado
    }
    fun eliminarUsuarioFormulario(id: Int): Boolean{
        //        val conexionEscritura = this.writableDatabase
        val conexionEscritura = writableDatabase
        // "SELECT * FROM USUARIO WHERE ID = ?"
        // arrayOf(
        //    id.toString()
        // )
        val resultadoEliminacion = conexionEscritura
            .delete(
                "ENTRENADOR",
                "id=?",
                arrayOf(
                    id.toString()
                )
            )
        conexionEscritura.close()
        return if (resultadoEliminacion.toInt() == -1) false else true
    }
    fun actualizarUsuarioFormulario(
        nombre: String,
        descripcion: String,
        idActualizar: Int
    ): Boolean {
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombre", nombre)
        valoresAActualizar.put("descripcion", descripcion)
        val resultadoActualizacion = conexionEscritura
            .update(
                "USUARIO", // Nombre tabla
                valoresAActualizar,  // Valores a actualizar
                "id=?", // Clausula Where
                arrayOf(
                    idActualizar.toString()
                ) // Parametros clausula Where
            )
        conexionEscritura.close()
        return if (resultadoActualizacion == -1) false else true

    }

}