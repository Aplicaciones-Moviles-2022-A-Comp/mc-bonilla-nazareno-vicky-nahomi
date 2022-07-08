package com.example.movcompvnbn2022a

class BBaseDatosMemoria {
    companion object{
        val arrgloBEntrenador= arrayListOf<BEntrenador>()
        init {
            arrgloBEntrenador
                .add(
                    BEntrenador(1,"vicky","a@a.com")
                )
            arrgloBEntrenador
                .add(
                    BEntrenador(2,"nahomi","n@n.com")
                )
            arrgloBEntrenador
                .add(
                    BEntrenador(3,"Bonilla","b@b.com")
                )
        }
    }
}