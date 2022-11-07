package com.example.livedataviewmodel.ConnectBluetooth

import android.bluetooth.BluetoothSocket
import android.os.Handler
import android.os.SystemClock
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

class ConnectedThread(val mmSocket: BluetoothSocket, private val mHandler: Handler) :
    Thread() {

    val MESSAGE_READ =
        2 // Se utilize en el controller Bluetooth para identifier la actualization de mensajes
    val mmInStream: InputStream?
    val mmOutStream: OutputStream?
    override fun run() {
        var buffer: ByteArray // almacén de búfer para el flujo
        var bytes: Int // bytes devueltos desde read()
        // Seguir escuchando InputStream hasta que ocurra una excepción
        while (true) {
            try {

                // Leer del InputStream
                bytes = mmInStream!!.available()
                if (bytes != 0) {
                    buffer = ByteArray(1024)
                    SystemClock.sleep(5000) //pausar y esperar el resto de datos. Ajuste esto dependiendo de su velocidad de envío.
                    bytes = mmInStream.available() // ¿Cuántos bytes están listos para ser leídos?
                    bytes = mmInStream.read(
                        buffer,
                        0,
                        bytes
                    ) //  registrar cuántos bytes leemos realmente
                    mHandler.obtainMessage(MESSAGE_READ, bytes, -1, buffer)
                        .sendToTarget() // Envía los bytes obtenidos a la actividad de la interfaz de usuario
                }
            } catch (e: IOException) {
                e.printStackTrace()
                break
            }
        }
    }

    /* Llame a esto desde la actividad principal para enviar datos al dispositivo remoto */
    fun write(input: String) {
        val bytes = input.toByteArray() //convierte la cadena ingresada en bytes
        try {
            mmOutStream!!.write(bytes)
        } catch (e: IOException) {
            println(e)
        }
    }

    /* Llame a esto desde la actividad principal para cerrar la conexión*/
    fun cancel() {
        try {
            mmSocket.close()
        } catch (e: IOException) {
            println(e)
        }
    }

    init {
        var tmpIn: InputStream? = null
        var tmpOut: OutputStream? = null


// Obtenga los flujos de entrada y salida, usando objetos temporales porque
        // los flujos de miembros son definitivos
        try {
            tmpIn = mmSocket.inputStream
            tmpOut = mmSocket.outputStream
        } catch (e: IOException) {
            println(e)
        }
        mmInStream = tmpIn
        mmOutStream = tmpOut
    }
}