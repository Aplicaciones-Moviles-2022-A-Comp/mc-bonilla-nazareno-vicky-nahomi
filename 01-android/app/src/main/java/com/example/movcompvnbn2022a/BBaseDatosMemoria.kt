package com.example.movcompvnbn2022a

class BBaseDatosMemoria {
    companion object{
        val arrgloBEntrenador= arrayListOf<BEntrenador>()
        init {
            arrgloBEntrenador
                .add(
                    BEntrenador("vicky","a@a.com")
                )
            arrgloBEntrenador
                .add(
                    BEntrenador("nahomi","n@n.com")
                )
            arrgloBEntrenador
                .add(
                    BEntrenador("Bonilla","b@b.com")
                )
        }
    }
}