package com.example.examenvickybonilla

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelperFarmacia (contexto: Context?,
) :SQLiteOpenHelper(contexto, "farmacia", null,1 ){
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaFarmacia=
            """
                CREATE TABLE FARMACIA(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre VARCHAR (50)
                )
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaFarmacia)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun crearFarmacia(
        nombre:String
    ): Boolean{
        /*DOS TIPOS DE BASE DE DATOS
        this.readableDatabase //lectura
        this.writableDatabase //escritura*/
        val basedatosEscritura= writableDatabase
        val valoresAGuardar= ContentValues()
        valoresAGuardar.put("nombre",nombre)
        val resultadoGuardar=basedatosEscritura
            .insert(
                "FARMACIA",
                null,
                valoresAGuardar
            )
        basedatosEscritura.close()
        return if (resultadoGuardar.toInt() == -1) false else true
    }
/*arraylist de BFarmacia
  quitar idInt

  */
    fun consultarfarmaciaPorId(id: Int): BFarmacia{
        // val baseDatosLectura = this.readableDatabase
        val baseDatosLectura = readableDatabase
        val scriptConsultarFarmacia = "SELECT * FROM FARMACIA WHERE ID = ${id}"
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarFarmacia,
            null
        )
        var existeFarmacia = resultadoConsultaLectura.moveToFirst()
        var FarmaciaEncontrado = BFarmacia(0, "")
        if(existeFarmacia){
            do{
                var id = resultadoConsultaLectura.getInt(0) // columna indice 0 -> ID
                val nombre = resultadoConsultaLectura.getString(1) // Columna indice 1 -> NOMBRE
                if(id!=null){
                    FarmaciaEncontrado.idF = id
                    FarmaciaEncontrado.nombreF= nombre
                }
            }while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return FarmaciaEncontrado
    }
    fun mostrarFarmacias(): ArrayList<BFarmacia> {
        var arregegloFarm : ArrayList<BFarmacia> = ArrayList<BFarmacia>()
        val baseDatosLectura = readableDatabase
        val scriptConsultarFarmacia = "SELECT * FROM FARMACIA"
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarFarmacia,
            null
        )
        var existeFarmacia = resultadoConsultaLectura.moveToFirst()
        //var FarmaciaEncontrado = BFarmacia(0, "")
        if(existeFarmacia){
            do{
                var id = resultadoConsultaLectura.getInt(0) // columna indice 0 -> ID
                val nombre = resultadoConsultaLectura.getString(1) // Columna indice 1 -> NOMBRE
                if(id!=null){
                   // FarmaciaEncontrado.idF = id
                    //FarmaciaEncontrado.nombreF= nombre
                    arregegloFarm.add(
                        BFarmacia(id, nombre)
                    )
                }
            }while (resultadoConsultaLectura.moveToNext())
        }else{

        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        //return FarmaciaEncontrado
        return arregegloFarm
    }
    fun eliminarFarmaciaFormulario(id: Int): Boolean{
        //        val conexionEscritura = this.writableDatabase
        val conexionEscritura = writableDatabase
        val resultadoEliminacion = conexionEscritura
            .delete(
                "FARMACIA",
                "id=?",
                arrayOf(
                    id.toString()
                )
            )
        conexionEscritura.close()
        return if (resultadoEliminacion.toInt() == -1) false else true
    }
    /*fun eliminarFarmaciaFormulario(nombre: String): Boolean{
        //        val conexionEscritura = this.writableDatabase
        val conexionEscritura = writableDatabase
        val resultadoEliminacion = conexionEscritura
            .delete(
                "FARMACIA",
                "nombre=${nombre}",
                arrayOf(
                    nombre.toString()
                )
            )
        conexionEscritura.close()
        return if (resultadoEliminacion.toInt() == -1) false else true
    }*/
    fun actualizarFarmaciaFormulario(
        nombre: String,
        idActualizar: Int
    ): Boolean {
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombre", nombre)
        val resultadoActualizacion = conexionEscritura
            .update(
                "FARMACIA", // Nombre tabla
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