package com.example.examenvickybonilla

import kotlin.collections.ArrayList

class BFarmacia(
    var idF:Int?,
    var nombreF:String?,
    var meds: ArrayList<BMedicamento> = arrayListOf<BMedicamento>()
)
{
    override fun toString(): String {
        return "${nombreF}"
    }
}