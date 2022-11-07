package com.example.livedataviewmodel.FormatoImpresion

import java.io.ByteArrayOutputStream
import java.io.IOException

class imprimir {
    /*TODO: FORMATOS [ARA CREAR FACTURA*/
  private val ENCODING = "ISO-8859-1"
  private val FONT = "MF204"
  private val FONTMED = "MF204,HMULT2,VMULT2"
  private val FONTSMALL = "MF226"
  private val FONTSMALL2 = "PT05T"
  private val FONTLARGE = "MF072"
  private val FONTBOLD = "MF107"
  private val FONTBOLD2 = "MF185"
  private val FONTINVERSE = "MF204,HMULT2,VMULT2,INVERSE"
  private val FONTINVERSE2 = "MF107,INVERSE"
  private val FONTBOLD3 = "MF185"
  private val FONTINVERSE3 = "MF204,INVERSE"
  private val SEPARADOR = "========================================================================="
  private val SEPARADOR2 = "________________________________________________________________________________"
  private val LN = "|"
  private val DP = ":"
    private val ESC = ""
    /*TODO: FORMATOS [ARA CREAR FACTURA FIM*/

    /*TODO: COORDENADAS*/
   private var y:Int = 50
   private var x:Int = 30
    private var space:Int = 15
    var texto:String = "Campana de alumbrado de San Salvador de S.A de C.V"
    var texto2:String = "Edificio Corporativo CAESS col. San Antonio Calle el Bambu Ayuntsmirnto, San Salvador"
    var nameCliente:String = "FLORES ESPINOZA MANUEL ANTONIO"
    var dirsum:String = "FINAL 3ª CALLE PONIENTE PJE GALLARDO No 20 APOPA"
    var dircob:String = "FINAL 3ª CALLE PONIENTE PJE GALLARDO No 20 APOPA"
    var mensaje:String = "Su servicio presenta un mes pendiente en mora por favor pagar su factura antes de la fecha de vencimiento"
    val nombres = arrayOf("Cargo de distribucion     12.60", "Costo por tasa municipal por poste   5.75", "Cargo de comercializacion   75.15","Cargo de energia   24.15", "SUBCIIDIO DE GOBIERNO    5.00")
    val cargosv = arrayOf("Interes por mora     12.60")

    @Throws(IOException::class)
    fun Formato1(): ByteArray? {
        val sbResultado = StringBuilder()
        with(sbResultado) {
            append(ESC + "EZ{PRINT:\n")

            append("@").append(y).append(",").append(x).append(DP).append(FONTBOLD).append(LN).append("IMPRIMIENDO EN HONEYWELL RP4 ").append(LN)
            append("@").append(y+space).append(",").append(x).append(DP).append(FONTBOLD).append(LN).append("USANDO KOTLIN :)").append(LN)
            append("}")
        }
        return stringToByteArray(sbResultado.toString())
    }

    @Throws(IOException::class)
    fun Formato2(): ByteArray? {

        var y:Int = 100
        var x:Int = 0
        var x2:Int = 100
        var x3:Int = 450
        var space:Int = 20
        var sdata1:String = ""
        var sdata2:String= ""
        var sdata5:String= ""
        var sbResultado = StringBuilder()
        with(sbResultado) {
            /*TODO: PARTE 1*/
            append(ESC + "EZ{PRINT:\n")

            val size: Int = texto.length
            if (size > 45) {
                sdata1 = texto.substring(0, 20)
                sdata2 = texto.substring(21, size )
            }
            if (size in 24..45) {
                sdata1 = texto.substring(0, 20)
                sdata2 = texto.substring(21, size)
            } else if (size < 23) {
                sdata1 = texto
                sdata2 = " "
            }
            append("@").append(y).append(",").append(x).append(DP).append(FONT).append(LN).append(sdata1).append(LN)
            append("@").append(y+20).append(",").append(x).append(DP).append(FONT).append(LN).append(sdata2).append(LN)

            sdata1 = ""
            sdata2 =""

            val size2: Int = texto2.length
            if (size2 > 45) {
                sdata1 = texto2.substring(0, 35)
                sdata2 = texto2.substring(36, size )
            }
            if (size2 in 24..45) {
                sdata1 = texto2.substring(0, 35)
                sdata2 = texto2.substring(36, size)
            } else if (size2 < 23) {
                sdata1 = texto2
                sdata2 = " "
            }

            //INICIO
            append("@").append(y+40).append(",").append(x).append(DP).append(FONT).append(LN).append(sdata1).append(LN)
            append("@").append(y+60).append(",").append(x).append(DP).append(FONT).append(LN).append(sdata2).append(LN)

            y = 180
            //FACTURA
            append("@").append(y+20).append(",").append(x).append(DP).append(FONT).append(LN).append("FACTURA:").append(LN)
            append("@").append(y+20).append(",").append(x2).append(DP).append(FONT).append(LN).append("55854872").append(LN)

            //REGISTRO
            append("@").append(y+40).append(",").append(x).append(DP).append(FONT).append(LN).append("Registro:").append(LN)
            append("@").append(y+40).append(",").append(x2).append(DP).append(FONT).append(LN).append("558548-5").append(LN)

           //NIT
            append("@").append(y+60).append(",").append(x).append(DP).append(FONT).append(LN).append("NIT:").append(LN)
            append("@").append(y+60).append(",").append(x2).append(DP).append(FONT).append(LN).append("0614-16195-1030").append(LN)
           //GIRO
            append("@").append(y+80).append(",").append(x).append(DP).append(FONT).append(LN).append("Giro:").append(LN)
            append("@").append(y+80).append(",").append(x2).append(DP).append(FONT).append(LN).append("Distribucion de Energia Electrica").append(LN)

                y = 100
            //Numero de Contrato
            append("@").append(y+20).append(",").append(x3+50).append(DP).append(FONT).append(LN).append("Numero de Contrato").append(LN)
            append("@").append(y+40).append(",").append(x3+60).append(DP).append(FONTLARGE).append(LN).append("NIC").append(LN)
            append("@").append(y+40).append(",").append(x3+160).append(DP).append(FONTLARGE).append(LN).append("512802").append(LN)
           //Fecha Vencimiento
            append("@").append(y+100).append(",").append(x3+30).append(DP).append(FONT).append(LN).append("Fecha de Vencimiento").append(LN)
            append("@").append(y+120).append(",").append(x3+110).append(DP).append(FONTLARGE).append(LN).append("22/02/2021").append(LN)
            //TOTAL A PAGAR
            append("@").append(y+160).append(",").append(x3+30).append(DP).append(FONT).append(LN).append("Total a Pagar").append(LN)
            append("@").append(y+180).append(",").append(x3+110).append(DP).append(FONTLARGE).append(LN).append("$ 58.88").append(LN)

            /*TODO: PARTE 1 FIN*/
            append("}")

            /*TODO: PARTE 2 */
            y =85
            x = 20
            x2=110
            x3 = 450
            space = 20
            var sdata3 = ""
            var sdata4 = ""

            append(ESC + "EZ{PRINT:\n")
            //CLIENTE
            append("@").append(y).append(",").append(x).append(DP).append(FONT).append(LN).append("Cliente:").append(LN)
            val size3: Int = nameCliente.length
            if (size3 > 10) {
                sdata3 = nameCliente.substring(0, 15)
                sdata4 = nameCliente.substring(16,size3 )
            }
            if (size3 in 14..20) {
                sdata3 = nameCliente.substring(0, 15)
                sdata4 = nameCliente.substring(16, size3)
            } else if (size2 < 13) {
                sdata3 = nameCliente
                sdata4 = " "
            }

            append("@").append(y+20).append(",").append(x).append(DP).append(FONTBOLD).append(LN).append(sdata3).append(LN)
            append("@").append(y+40).append(",").append(x).append(DP).append(FONTBOLD).append(LN).append(sdata4).append(LN)

            //DIR SUM
            sdata3 = ""
            sdata4 = ""
            append("@").append(y+60).append(",").append(x).append(DP).append(FONT).append(LN).append("DIR SUM:").append(LN)

            val size4: Int = dirsum.length
            if (size4 > 45) {
                sdata3 = dirsum.substring(0, 25)
                sdata4 = dirsum.substring(26, size4 )
            }
            if (size4 in 24..45) {
                sdata3 = dirsum.substring(0, 25)
                sdata4 = dirsum.substring(26, size4)
            } else if (size2 < 23) {
                sdata3 = dirsum
                sdata4 = " "
            }

            append("@").append(y+60).append(",").append(x2).append(DP).append(FONT).append(LN).append(sdata3).append(LN)
            append("@").append(y+80).append(",").append(x2).append(DP).append(FONT).append(LN).append(sdata4).append(LN)

            sdata3 = ""
            sdata4 = ""
            // DIR COB
            append("@").append(y+100).append(",").append(x).append(DP).append(FONT).append(LN).append("DIR COB:").append(LN)
            val size5: Int = dircob.length
            if (size5 > 45) {
                sdata3 = dircob.substring(0, 25)
                sdata4 = dircob.substring(26, size5 )
            }
            if (size5 in 24..45) {
                sdata3 = dircob.substring(0, 35)
                sdata4 = dircob.substring(26, size5)
            } else if (size5 < 23) {
                sdata3 = dircob
                sdata4 = " "
            }
            append("@").append(y+100).append(",").append(x2).append(DP).append(FONT).append(LN).append(sdata3).append(LN)
            append("@").append(y+120).append(",").append(x2).append(DP).append(FONT).append(LN).append(sdata4).append(LN)

            //MENSAJE
            y = y+180
            sdata3 = ""
            sdata4 = ""
            val size6: Int = mensaje.length
            if (size6 > 45) {
                sdata3 = mensaje.substring(0, 35)
                sdata4 = mensaje.substring(36, 65 )
                sdata5 = mensaje.substring(66,size6)
            }
            if (size6 in 24..45) {
                sdata3 = mensaje.substring(0, 35)
                sdata4 = mensaje.substring(36, 65)
                sdata5 = mensaje.substring(66,size6)
            } else if (size6 < 23) {
                sdata3 = mensaje
                sdata4 = " "
                sdata5 = " "
            }
            append("@").append(y+20).append(",").append(x).append(DP).append(FONTBOLD2).append(LN).append(sdata3).append(LN)
            append("@").append(y+40).append(",").append(x).append(DP).append(FONTBOLD2).append(LN).append(sdata4).append(LN)
            append("@").append(y+60).append(",").append(x).append(DP).append(FONTBOLD2).append(LN).append(sdata5).append(LN)

            // TIPOS DE CARGOS
            y = 80
            append("@").append(y).append(",").append(x3+50).append(DP).append(FONT).append(LN).append("ventas afectadas").append(LN)

            for (indice in nombres.indices){
                y += 20
                append("@").append(y).append(",").append(x3).append(DP).append(FONT).append(LN).append(nombres[indice]).append(LN)

            }
            append("@").append(y+40).append(",").append(x3).append(DP).append(FONT).append(LN).append("SUB TOTAL      44.63").append(LN)

            append("@").append(y+80).append(",").append(x3+100).append(DP).append(FONT).append(LN).append("*Cargos varios").append(LN)

            y +=80
            for (indice in cargosv.indices){
                y += 20
                append("@").append(y).append(",").append(x3).append(DP).append(FONT).append(LN).append(cargosv[indice]).append(LN)

            }
            append("@").append(y+40).append(",").append(x3+30).append(DP).append(FONT).append(LN).append("SUB TOTAL      45.78").append(LN)

            append("@").append(y+60).append(",").append(x3+100).append(DP).append(FONT).append(LN).append("*Ventas exentas").append(LN)
            append("@").append(y+80).append(",").append(x3+30).append(DP).append(FONT).append(LN).append("*Facturas pendientes").append(LN)
            append("@").append(y+120).append(",").append(x3+30).append(DP).append(FONT).append(LN).append("TOTAL CAESS     45.78").append(LN)
            append("@").append(y+160).append(",").append(x3+100).append(DP).append(FONT).append(LN).append("* Otro servicios").append(LN)


            append("}")

            /*TODO: PARTE 2 FIN*/

            /*TODO: PARTE 3 GRAFICA*/
            y =130
            x = 20
            x2=110
            x3 = 450
            space = 20
            append(ESC + "EZ{PRINT:\n")
            append("@").append(y).append(",").append(x).append(DP).append(FONT).append(LN).append("Promedio ultimos 6 meses 130.5 Kwh").append(LN)
            append("}")
        }



        return stringToByteArray(sbResultado.toString())
    }



    @Throws(IOException::class)
    fun stringToByteArray(s: String): ByteArray? {
        val b: ByteArray
        val ByteArray = ByteArrayOutputStream()
        val buf = s.toByteArray(charset(ENCODING))
        ByteArray.write(buf)
        b = ByteArray.toByteArray()
        return b
    }
}