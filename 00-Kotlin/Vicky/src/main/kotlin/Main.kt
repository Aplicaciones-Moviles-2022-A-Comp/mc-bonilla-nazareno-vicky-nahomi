import java.util.Date

fun main(){
    //println("Hola mundo")
    //TIPOS DE VARIABLES
    //1. INMUTABLES (no se puede reasignar)
    val inmutable:String="Vicky";
    //println("inmutable: "+inmutable)
    //inmutable="u.u"; no se puede reasignar

    //2. MUTABLES (se puede reasignar con "=")
    var mutable: String="Vonilla";
   // println("incicial mutable: "+mutable)
    mutable="Bonilla";
    //println("final mutable: "+mutable)

    // val > var

    //Sintaxis Duck typing

    val ejemploVariable = "e j m";  //se sobreentiende que es un string
    val edadEjemploInt= 12
    ejemploVariable.trim()

    //Variables primitivas
    //string, double, char, boolean, etc las mismas de java
    val ejemploVariable1 = "e j m";  //se sobreentiende que es un string
    val edadEjemploInt1= 12
    val edadEjemploInt2= 12.3
    val ejemplocaracter='M'
    val vivo=false
    //Clases Java
    //ya no se usa "new"  o sea:  Date = new Date()
    val fechaNcimiento: Date = Date()


    //switch ya no existe
   /* val estadoCivilWhen ="S"
    when(estadoCivilWhen){
        ("S") ->{
            println("acercarse")
        }
        "C"->{
            println("alejarse")
        }
        "UN" -> println("hablar")
        else -> println("nuay")
    }*/
    //val coqueteo = if (estadoCivilWhen=="S") "SI" else "NO"
   // println(coqueteo)

    //void imprimirNombre(String nombre){}
    //Unit==void
   // imprimirNombre("Juana")
   // println(calcularSueldo(390.2))
   // println(calcularSueldo(390.2,10.0))
   // println(calcularSueldo(390.2,10.0,5.0))

   // println(Suma.pi)
    //println("elevado al cuadrado desde estatico: ${Suma.elevarAlCuadrado(2)}")

   /* val sumaUno=Suma(1,2)
    val sumaDos=Suma(3,4)
    val sumaTres=Suma(5,6)
    val sumaCuatro=Suma(7,8)
    sumaUno.sumar()
    sumaDos.sumar()
    sumaTres.sumar()
    sumaCuatro.sumar()*/
    //println(Suma.historialSumas)

    // ARREGLOS

    // Tipos de Arreglos

    // Arreglo Estatico
    val arregloEstatico: Array<Int> = arrayOf(1, 2, 3)
    println("arreglo estático "+arregloEstatico)

    // Arreglo Dinámicos
    val arregloDinamico: ArrayList<Int> = arrayListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    println("arreglo dinamico "+arregloDinamico)
    arregloDinamico.add(11)
    arregloDinamico.add(12)
    println("arreglo dinamico "+arregloDinamico)

    // OPERADORES -> Sirven para los arreglos estáticos y dinámicos
    // FOR EACH -> Unit
    // Iterar un arreglo

    val respuestaForEach: Unit = arregloDinamico
        .forEach {
                valorActual: Int ->
            //println("Valor actual: ${valorActual}")
        }
    arregloDinamico
        .forEachIndexed { indice: Int, valorActual: Int ->
           // println("Valor ${valorActual} Indice: ${indice}")
        }
 //   println(respuestaForEach)

    //los operadores pueden ser usados en cualquier lenguaje

    //MAP
    val respuestaMap: List<Double> = arregloDinamico
        .map{valorActual:Int ->
            return@map valorActual.toDouble()+100.00
        }
    println(respuestaMap)
    val respuestaMapDos= arregloDinamico.map {it-10}

    println("luego de map ${respuestaMapDos}")

    //FILTER -Z FILTRAR EL ARREGLO

    val respuestaFilter: List<Int> = arregloDinamico
    .filter{ valorActual: Int->
        val mayoresACinco: Boolean = valorActual >5
        return@filter mayoresACinco
    }

    println("luego de filter1 ${respuestaFilter}")
    val respuestaFilterDos = arregloDinamico.filter { it%3==0 }
    println("luego de filter2 ${respuestaFilterDos}")

    //and or

    val respuestaAny: Boolean = arregloDinamico
        .any { valorActual: Int ->
            return@any (valorActual>5)
        }
    println("hay alguno mayor a 5? ${respuestaAny}")

    val respuestaAll: Boolean = arregloDinamico
        .all {  valorActual: Int ->
            return@all (valorActual<5)
        }
    println("Todos son menores que 5? ${respuestaAll}")

    println("antes ${arregloDinamico}")
    //REDUCE -> Valor acumulado
    // Valor acumulado = 0 (Siempre 0 en lenguaje Kotlin)
    // [1, 2, 3, 4, 5] -> Sumeme todos los valores del arreglo
    // valorIteracion1 = valorEmpieza + 1 = 0 + 1 = 1 -> Iteracion 1
    // valorIteracion2 = valorIteracion1 + 2 = 1 + 2 = 3 -> Iteracion 2
    // valorIteracion3 = valorIteracion2 + 3 = 3 + 3 = 6 -> Iteracion 3
    // valorIteracion4 = valorIteracion3 + 4 = 6 + 4 = 10 -> Iteracion 4
    // valorIteracion5 = valorIteracion4 + 5 = 10 + 5 = 15 -> Iteracion 5
    val respuestaReduce: Int = arregloDinamico
        .reduce { // acumulado = 0 -> SIEMPRE EMPIEZA EN 0
                acumulado: Int, valorActual: Int ->
            println("acumulado: ${acumulado}")
            return@reduce (acumulado + valorActual) // -> Logica negocio
        }
    println("Valor acumulado: ${respuestaReduce}") // 78
}


fun calcularSueldo(
    sueldo: Double, //Requerido
    tasa: Double=100.00,// Opcional (por defecto)
    bonoEspecial: Double?=null //Opcional (Null) -> nullable
): Double{ //devuelve un doble
        if (bonoEspecial==null){
            return sueldo*(100/tasa)
        }else{
            return sueldo*(100/tasa)+bonoEspecial
        }
}


fun imprimirNombre(nombre: String):  Unit {
    println("Nombre: ${nombre}")
}

abstract class NumerosJava{
    protected val numeroUno: Int
    private val numeroDos: Int
    constructor(
        uno: Int, dos:Int
    ){
        this.numeroUno=uno
        this.numeroDos=dos
    }
}

abstract class Numeros(
    //esto ya es un contructor PRIMARIO
    //solo está puesto luego del nombre de la clase
    protected var numerouno: Int,
    protected val numerodos: Int,
   // var tres:Int, //si no se le pone nada es solo un parámetro y PÚBLICO POR DEFECTO
   // public var cuatro:Int
){
    init { //bloqe codigo constructor primario
        numerouno
        numerodos
        println("inicializado")
       /* tres=3
        this.cuatro=tres+1;
        uno=tres-2;
        println("inicializado: ${uno},${dos},${tres},${cuatro}")*/
    }
}

//heredar de una clase a otra
class Suma(//constructor primario suma
    uno: Int,
    dos: Int
): Numeros(// Super constructor numeros
    uno, dos
){
    init {
        this.numerouno
        this.numerodos
    }
    constructor( //Segundo constructor
        uno:Int?,dos: Int
    ):this(
        if (uno==null) 0 else uno, dos
    ){
    }
    constructor( //Tercer constructor
        uno:Int,dos: Int?
    ):this(
        uno,
        if (dos==null) 0 else  dos
    ){
    }
    constructor( //Cuarto constructor
        uno:Int?,dos: Int?
    ):this(
        if (uno==null) 0 else uno,
        if (dos==null) 0 else  dos
    ){
    }
    fun sumar():Int{ //es un método
        val total= numerouno+numerodos
        agregarHistorial(total)
        return total
    }
    /*
    static permite usar métodos o propiedades sin instanciar clases
     */
    companion object{
        val pi=3.14159
        fun elevarAlCuadrado(num:Int):Int{
            return num*num;
        }
        val historialSumas= arrayListOf<Int>()
        fun agregarHistorial(valorNuevaSuma:Int){
            historialSumas.add(valorNuevaSuma)
        }
    }
}

