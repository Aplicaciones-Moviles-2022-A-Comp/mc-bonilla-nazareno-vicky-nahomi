import java.io.*
import kotlin.collections.ArrayList
fun main() {
    var arrayFarmacia : ArrayList<Farmaciia> = arrayListOf<Farmaciia>()
    var arrFarmIndex:ArrayList<Int> = arrayListOf<Int>()
    leerArchivo(arrayFarmacia)
    do{
        println("RED INTEGRADA DE FARMACIAS\n")
        println("1. Listar Farmacias\n" +
                "2. Insertar Farmacia\n" +
                "3. Modificar datos de Farmacia\n" +
                "4. Borrar Farmacia\n"+
                "5. Ingresar a una farmacia\n"+
                "6. Salir")
        var opcion= readLine()!!
        when (opcion) {
            "1" -> {
                printFarm(arrayFarmacia)
            }
            "2" -> {
                var continuar = "y"
                do {
                    insertarFarm(arrayFarmacia)
                    printFarm(arrayFarmacia)
                    println("Seguir insertando? y/n")
                    continuar = readLine()!!
                } while (continuar == "y")
            }
            "3" -> {
                modificarFarm(arrayFarmacia)
                printFarm(arrayFarmacia)
            }
            "4" -> {
                borrarFarm(arrayFarmacia)
                printFarm(arrayFarmacia)
            }
            "5" -> {
                    printFarm(arrayFarmacia)
                    println("Seleccione el ID d e la farmacia: ")
                    var idFarm = readLine()!!.toInt()
                do {
                        printnombreFarmacia(arrayFarmacia,idFarm)
                        println(
                                "1. Listar Medicamentos\n" +
                                        "2. Insertar Medicamento\n" +
                                        "3. Modificar datos de Medicamento\n" +
                                        "4. Borrar Medicamento\n" +
                                        "5. Salir"
                        )
                        var opcionMed = readLine()!!
                        when (opcionMed) {
                            "1" -> {
                                printMed(arrayFarmacia, idFarm)
                            }
                            "2" -> {
                                var continuar = "y"
                                do {
                                    insertarMedicamento(arrayFarmacia, idFarm)
                                    printMed(arrayFarmacia, idFarm)
                                    println("Seguir insertando? y/n")
                                    continuar = readLine()!!
                                }while (continuar=="y")
                            }
                            "3" -> {
                                    println("Seleccione el ID del medicamento: ")
                                    var idMed = readLine()!!.toInt()
                                    modificarMed(arrayFarmacia, idFarm,idMed)
                                    printMed(arrayFarmacia, idFarm)
                            }
                            "4" -> {
                                println("Seleccione el ID del medicamento: ")
                                var idMed = readLine()!!.toInt()
                                borrarMed(arrayFarmacia, idFarm,idMed)
                                printMed(arrayFarmacia, idFarm)
                            }
                            else -> {
                                println("Adios")
                                break
                                }
                            }
                    } while (opcionMed!="5")
            }
            else -> {
                println("Adios")
                break
            }
        }
    }while (opcion!="6")
    saveFile(arrayFarmacia)
}

fun insertarFarm(arrayFarmacia:ArrayList<Farmaciia>){
    println("Ingrese el nombre de la farmacia")
    var nombre=readLine()!!
    println("Ingrese la dirección de la farmacia")
    var direccion=readLine()!!
    println("Ingrese el numero telefonico de la farmacia")
    var numeroTelefonico=readLine()!!
    if(arrayFarmacia.isEmpty()){
        arrayFarmacia.add(Farmaciia(1,nombre,direccion,
            numeroTelefonico, arrayListOf<Mediicamento>()) )
    }else{
        arrayFarmacia.add(Farmaciia(arrayFarmacia.get(arrayFarmacia.size - 1).id +1,nombre,direccion,
            numeroTelefonico, arrayListOf<Mediicamento>()) )
    }
}

fun borrarFarm(arrayFarmacia:ArrayList<Farmaciia>){
    println("Borrar farmacia :seleccionar ID: ")
    var idFborrar= readLine()!!.toInt()
    for (element in arrayFarmacia) {
        if (element.id==idFborrar) {
            println("el index:"+arrayFarmacia.indexOf(element))
            arrayFarmacia.removeAt(arrayFarmacia.indexOf(element))
            break
        }
    }
}

fun modificarFarm(arrayFarmacia: ArrayList<Farmaciia>){
    println("Seleccione el ID de la farmacia")
    var idFmodificar= readLine()!!.toInt()
    for (element in arrayFarmacia) {
        if (element.id==idFmodificar) {
            println("Ingrese el nombre de la farmacia")
            var nombre=readLine()!!
            println("Ingrese la dirección de la farmacia")
            var direccion=readLine()!!
            println("Ingrese el numero telefonico de la farmacia")
            var numeroTelefonico=readLine()!!
            println("el index:"+arrayFarmacia.indexOf(element))
            arrayFarmacia.set(arrayFarmacia.indexOf(element),Farmaciia(element.id,nombre,direccion,
                numeroTelefonico,element.meds))
        }
    }
}

fun printFarm(arrayFarmacia: ArrayList<Farmaciia>){
    println("ID\tNombreFarm\tDireccion\tNumero\n" )

    for (farm in arrayFarmacia)
    {
        println(""+farm.id+"\t"+farm.nombre+"\t"+
                farm.direccion+"\t"+farm.numeroTelefonico+"\t"+"\t"+farm.meds)
    }
}
fun printnombreFarmacia(arrayFarmacia: ArrayList<Farmaciia>,idFarm: Int){
    var idFaniadirM= idFarm
    for (element in arrayFarmacia) {
        if (element.id == idFaniadirM) {
            println("FARMACIA " + element.nombre.uppercase())
        }
    }
}
fun printMed(arrayFarmacia: ArrayList<Farmaciia>,idFarm: Int){
    var idFaniadirM= idFarm
    for (element in arrayFarmacia) {
        if (element.id==idFaniadirM) {
            println("FARMACIA " + element.nombre.uppercase())
            println("\nIDF\tID\tNombre\tprecio\tCantidad\n")
            for (med in element.meds)
            {
                println(""+med.idFarmacia+"\t"+med.idMedicamento+"\t"+med.nombre+"\t"+
                        med.precio+"\t"+med.cantidad)
            }
        }}
}
fun insertarMedicamento(arrayFarmacia: ArrayList<Farmaciia>,idFarm: Int){
    println("llenar medicamento")
    var idFaniadirM= idFarm
    for (element in arrayFarmacia) {
        if (element.id==idFaniadirM) {
            println("Ingrese el nombre: ")
            var nombre = readLine()!!
            println("Ingrese el precio: ")
            var precio = readLine()!!.toDouble()
            println("Ingrese la cantidad: ")
            var cantidad = readLine()!!.toInt()
            //println("Ingrese la fecha de caducidad: ")
            //var fechaCaducidad: Date? = null
            if(element.meds.isEmpty()) {
                element.meds.add(Mediicamento(idFaniadirM, 1, nombre, precio, cantidad))
            }else
            {//arrayFarmacia.get(arrayFarmacia.size - 1).id +1
                element.meds.add(Mediicamento(idFaniadirM, element.meds.get(element.meds.size - 1).idMedicamento +1, nombre, precio, cantidad))
            }
            println("\nIDF\tID\tNombre\tprecio\tCantidad\tfecha\n")
            for (med in element.meds)
            {
                println(""+med.idFarmacia+"\t"+med.idMedicamento+"\t"+med.nombre+"\t"+
                        med.precio+"\t"+med.cantidad)
            }
        }}
}


fun modificarMed(arrayFarmacia: ArrayList<Farmaciia>,idFarm: Int,idMed:Int)
{
    println("modificar medicamento")
    var idFaniadirM= idFarm
    for (element in arrayFarmacia) {
        if (element.id==idFaniadirM) {
            for (med in element.meds){
                if (med.idMedicamento==idMed){
                    println("Ingrese el nombre: ")
                    var nombre = readLine()!!
                    println("Ingrese el precio: ")
                    var precio = readLine()!!.toDouble()
                    println("Ingrese la cantidad: ")
                    var cantidad = readLine()!!.toInt()
                    element.meds.set(element.meds.indexOf(med),Mediicamento(idFaniadirM, idMed , nombre, precio, cantidad))
                }
            }

        }}
}

fun borrarMed(arrayFarmacia: ArrayList<Farmaciia>,idFarm: Int,idMed:Int)
{
    println("borrar medicamento")
    var idFaniadirM= idFarm
    for (element in arrayFarmacia) {
        if (element.id==idFaniadirM) {
            for (med in element.meds){
                if (med.idMedicamento==idMed){
                    element.meds.removeAt(element.meds.indexOf(med))
                    break
                }
            }
        }}
}

fun saveFile(arrayFarmacia: ArrayList<Farmaciia>){
    val nombre="out/Resultados/Farmacias.txt"
    val archivo=File(nombre)
    if (!archivo.exists()){
        archivo.createNewFile()
    }
    val fileWriter = FileWriter(archivo)
    val bufferedWriter = BufferedWriter(fileWriter)
    var salida:String=""
    for(element in arrayFarmacia){
        salida+=("${element.toString()}")
    }
    println(salida)
    bufferedWriter.write("$salida")
    bufferedWriter.close()
}
fun leerArchivo(arrayFarmacia: ArrayList<Farmaciia>){
    val fr = FileReader("out/Resultados/Farmacias.txt")
    var fileText = ""
    var i: Int
    while (fr.read().also { i = it } != -1) {
        fileText += i.toChar()
    }
    var count = 0
    for (i in 0 until fileText.length) {
        val letter: Char = fileText[i]
        if (letter == '\n') ++count
    }
    var elemntosArchive=ArrayList<String>(fileText.split("\n"))
    for(i in 0..count-1){
        var (id, nombre, direccion, numeroTelefonico, meds)=elemntosArchive[i].split("|")
        var medicinas=ArrayList<String>((meds.substring(1,meds.length-1)).split(","))
        var medis=ArrayList<Mediicamento>()
        if(medicinas[0].length>0){
            for(medicina in medicinas) {
                var (idFarmacia, idMedicamento, nombreMed, precio, cantidad) = medicina.split(";")
                idFarmacia=idFarmacia.replace("\\s".toRegex(),"")
                var medsi =Mediicamento(idFarmacia.toInt(), idMedicamento.toInt(), nombreMed, precio.toDouble(), cantidad.toInt())
                medis.add(medsi)
            }
        } else {
            println("")
        }
        var farmacia= Farmaciia(id.toInt(),nombre, direccion, numeroTelefonico,medis)
        arrayFarmacia.add(farmacia)
    }
}
data class Farmaciia(
/*
* idFarmacia
* NombreFarmacia
* direccionFarmacia
* NumeroTelefonicoFarmacia
* EnOpreacion -> boolean
* cantidadsucursalesFarmacia
* */
var id:Int,
var nombre:String,
var direccion:String,
var numeroTelefonico:String,
var meds: ArrayList<Mediicamento>  = arrayListOf<Mediicamento>()
) {
    override fun toString(): String {
        return "$id|$nombre|$direccion|$numeroTelefonico|${meds.toString()}\n"
       /* return "{\n" +
                "\t\"id\":$id,\n" +
                "\t\"nombre\":\"$nombre\",\n" +
                "\t\"direccion\":\"$direccion\",\n" +
                "\t\"numeroTelefonico\":\"$numeroTelefonico\",\n" +
                "\t\"enOperacion\":$enOperacion,\n" +
                "\t\"meds\":\n" +
                "\t${meds.toString()},\n" +
                "\t},\n"*/
    }
}

data class Mediicamento(
/*
* idFarmacia
* idMedicamento
* nombreGenericoMedicamento
* precioMedicamento
* cantidadMedicamento
* fechaCaducidadMedicamento
* */
var idFarmacia:Int,
var idMedicamento: Int,
var nombre:String,
var precio:Double,
var cantidad:Int,
) {
    override fun toString(): String {
      /* return "\t\t{\n" +
                "\t\t\"idFarmacia\":$idFarmacia,\n" +
                "\t\t\"idMedicamento\":$idMedicamento,\n" +
                "\t\t\"nombre\":\"$nombre\",\n" +
                "\t\t\"precio\":$precio,\n" +
                "\t\t\"cantidad\":$cantidad,\n" +
                "\t\t\"fechaCaducidad\":\"$fechaCaducidad\"\n" +
                "\t\t}\n"*/
        return "$idFarmacia;$idMedicamento;$nombre;$precio;$cantidad"
    }
}


