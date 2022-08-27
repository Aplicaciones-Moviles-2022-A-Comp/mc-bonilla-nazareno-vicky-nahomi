package com.example.examen02vb


class BMedicamento(
    var idM:Long?,
    var nombreM:String?
){

    override fun toString(): String {
        return "${nombreM}"
    }
}