package com.example.examenvickybonilla

class BFarmacia (
    var nombreF:String,
    var direccionF:String?,
    var numeroTelefonicoF:String?,
    var meds: ArrayList<BMedicamento>  = arrayListOf<BMedicamento>()
    )
{
    override fun toString(): String {
        return "${nombreF}"
    }
}