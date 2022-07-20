package com.example.examenvickybonilla

import kotlin.collections.ArrayList

class BFarmacia(
    var nombreF:String?,
    var direccionF:String?,
    var numeroTelefonicoF:String?,
    var meds: ArrayList<BMedicamento> = arrayListOf<BMedicamento>()
)
{
    override fun toString(): String {
        return "${nombreF}"
    }
}