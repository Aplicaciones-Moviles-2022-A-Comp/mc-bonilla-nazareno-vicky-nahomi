package com.example.examenvickybonilla

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Funciones (contexto: Context?,
) :SQLiteOpenHelper(contexto, "farmacia", null,1 ){
    override fun onCreate(db: SQLiteDatabase?) {
       /* val scriptSQLCrearTablaFarmacia=
            """
                CREATE TABLE FARMACIA(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre VARCHAR (50)
                )
            """.trimIndent()

        val scriptSQLCrearTablaMedicamento=
            """
                CREATE TABLE MEDICAMENTOS(
                idf INTEGER,
                idm INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre VARCHAR (50)
                )
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaFarmacia)
        db?.execSQL(scriptSQLCrearTablaMedicamento)*/
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun crearMedicamento(
        nombre:String,
        idf:Int
    ): Boolean{
       return true
    }

    fun consultarMedicamentoPorId(id: Int): BMedicamento{

        var medicamentoEncontrado = BMedicamento(0,0, "")

        return medicamentoEncontrado
    }

    fun mostrarMedicamentos(idf: Int): ArrayList<BMedicamento> {
        var arregegloMedicamento : ArrayList<BMedicamento> = ArrayList<BMedicamento>()

        return arregegloMedicamento
    }
    fun eliminarMedicamentoFormulario(id: Int): Boolean{
        //        val conexionEscritura = this.writableDatabase
        val conexionEscritura = writableDatabase
        val resultadoEliminacion = conexionEscritura
            .delete(
                "MEDICAMENTOS",
                "idm=?",
                arrayOf(
                    id.toString()
                )
            )
        conexionEscritura.close()
        return if (resultadoEliminacion.toInt() == -1) false else true
    }
    fun actualizarMedicamentoFormulario(
        nombre: String,
        idActualizar: Int
    ): Boolean {

        return  true

    }



    fun crearFarmacia(
        nombre:String
    ): Boolean{

        return true
    }
/*arraylist de BFarmacia
  quitar idInt

  */
    fun consultarfarmaciaPorId(id: Int): BFarmacia{
       var FarmaciaEncontrado = BFarmacia(0, "")

        return FarmaciaEncontrado
    }
    fun mostrarFarmacias(): ArrayList<BFarmacia> {
        var arregegloFarm : ArrayList<BFarmacia> = ArrayList<BFarmacia>()

        return arregegloFarm
    }
    fun eliminarFarmaciaFormulario(id: Int): Boolean{

        return false
    }

    fun actualizarFarmaciaFormulario(
        nombre: String,
        idActualizar: Int
    ): Boolean {

        return true

    }

}