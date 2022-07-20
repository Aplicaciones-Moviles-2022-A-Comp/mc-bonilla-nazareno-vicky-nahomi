package com.example.examenvickybonilla

class BMedicamento(
    var nombreM:String,
    var precioM:Double?,
    var cantidadM:Int?
){

    override fun toString(): String {
        return "${nombreM}"
    }
}