package com.example.examenvickybonilla

class BBaseDatos {
    companion object{
        var TablaFarmacia: SQLiteHelperFarmacia? = null
        val arregloFarmacia= arrayListOf<BFarmacia>()
        val arregloMedicamentos= arrayListOf<BMedicamento>()
        init {
            arregloMedicamentos.add(
                BMedicamento(0,"xanax"))
            arregloFarmacia.add(
                BFarmacia(0,"Sana Sana", arregloMedicamentos)
            )
        }
    }

}