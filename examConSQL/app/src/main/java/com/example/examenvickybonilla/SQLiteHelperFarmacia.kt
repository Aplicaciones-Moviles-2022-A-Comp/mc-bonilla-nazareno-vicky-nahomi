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

        val scriptSQLCrearTablaMedicamento=
            """
                CREATE TABLE MEDICAMENTOS(
                idf INTEGER,
                idm INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre VARCHAR (50)
                )
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaFarmacia)
        db?.execSQL(scriptSQLCrearTablaMedicamento)
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun crearMedicamento(
        nombre:String,
        idf:Int
    ): Boolean{
        /*DOS TIPOS DE BASE DE DATOS
        this.readableDatabase //lectura
        this.writableDatabase //escritura*/
        val basedatosEscritura= writableDatabase
        val valoresAGuardar= ContentValues()
        valoresAGuardar.put("nombre",nombre)
        valoresAGuardar.put("idf",idf)
        val resultadoGuardar=basedatosEscritura
            .insert(
                "MEDICAMENTOS",
                null,
                valoresAGuardar
            )
        basedatosEscritura.close()
        return if (resultadoGuardar.toInt() == -1) false else true
    }

    fun consultarMedicamentoPorId(id: Int): BMedicamento{
        // val baseDatosLectura = this.readableDatabase
        val baseDatosLectura = readableDatabase
        val scriptConsultarFarmacia = "SELECT * FROM MEDICAMENTOS WHERE IDM = ${id}"
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarFarmacia,
            null
        )
        var existeMedicamento= resultadoConsultaLectura.moveToFirst()
        var medicamentoEncontrado = BMedicamento(0,0, "")
        if(existeMedicamento){
            do{
                var idf = resultadoConsultaLectura.getInt(0) // columna indice 0 -> IDf
                var idm = resultadoConsultaLectura.getInt(1) // columna indice 1 -> IDm
                val nombre = resultadoConsultaLectura.getString(2) // Columna indice 2 -> NOMBRE
                if(id!=null){
                    medicamentoEncontrado.idM = id
                    medicamentoEncontrado.nombreM= nombre
                }
            }while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return medicamentoEncontrado
    }

    fun mostrarMedicamentos(idf: Int): ArrayList<BMedicamento> {
        var arregegloMedicamento : ArrayList<BMedicamento> = ArrayList<BMedicamento>()
        val baseDatosLectura = readableDatabase
        val scriptConsultarMedicamento = "SELECT * FROM MEDICAMENTOS WHERE IDF = ${idf}"
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarMedicamento,
            null
        )
        var existeMedicamento = resultadoConsultaLectura.moveToFirst()
        //var FarmaciaEncontrado = BFarmacia(0, "")
        if(existeMedicamento){
            do{
                var idf = resultadoConsultaLectura.getInt(0) // columna indice 0 -> IDf
                var idm = resultadoConsultaLectura.getInt(1) // columna indice 1 -> IDm
                val nombre = resultadoConsultaLectura.getString(2) // Columna indice 2 -> NOMBRE
                if(idm!=null){
                    arregegloMedicamento.add(
                        BMedicamento(idf,idm, nombre)
                    )
                }
            }while (resultadoConsultaLectura.moveToNext())
        }else{

        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
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
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombre", nombre)
        val resultadoActualizacion = conexionEscritura
            .update(
                "MEDICAMENTO", // Nombre tabla
                valoresAActualizar,  // Valores a actualizar
                "id=?", // Clausula Where
                arrayOf(
                    idActualizar.toString()
                ) // Parametros clausula Where
            )
        conexionEscritura.close()
        return if (resultadoActualizacion == -1) false else true

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