package com.example.examenvickybonilla

class BBaseDatos {
    companion object{
        val arregloFarmacia= arrayListOf<BFarmacia>()
        init {
            arregloFarmacia.add(
                BFarmacia("Sana Sana","Carapungo","0987584629", arrayListOf<BMedicamento>())
            )
        }
        val arregloMedicamentos= arrayListOf<BMedicamento>()
        init {
            arregloMedicamentos.add(
                BMedicamento("xanax",2.3,789)
            )
        }
    }

}