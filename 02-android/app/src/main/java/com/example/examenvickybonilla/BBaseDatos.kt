package com.example.examenvickybonilla

class BBaseDatos {
    companion object{
        val arregloFarmacia= arrayListOf<BFarmacia>()
        val arregloMedicamentos= arrayListOf<BMedicamento>()
        init {
            arregloMedicamentos.add(
                BMedicamento("xanax",2.3,789))
            arregloFarmacia.add(
                BFarmacia("Sana Sana","Carapungo","0987584629", arregloMedicamentos)
            )

        }

    }

}