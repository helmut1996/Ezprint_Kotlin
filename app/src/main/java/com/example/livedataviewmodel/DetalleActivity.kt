@file:Suppress("DEPRECATION")

package com.example.livedataviewmodel


import android.app.ProgressDialog
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothSocket
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.livedataviewmodel.ConnectBluetooth.ConnectToDevice
import com.example.livedataviewmodel.FormatoImpresion.imprimir
import com.example.livedataviewmodel.databinding.ActivityDetalleBinding
import java.io.IOException
import java.util.*


@Suppress("DEPRECATION")
class DetalleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetalleBinding

        private  lateinit var imp:imprimir
    companion object{
        var m_myUUID: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
        var m_bluetoothSocket: BluetoothSocket? = null
        lateinit var m_progress: ProgressDialog
        lateinit var m_bluetoothAdapter: BluetoothAdapter
        var m_isConnected: Boolean = false
        lateinit var m_address: String
        lateinit var m_name:String
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalleBinding.inflate(layoutInflater)
        setContentView(binding.root)

         imp = imprimir()

        ConnectToDevice(this).execute()



        m_address = intent.getStringExtra(MainActivity.EXTRA_ADDRESS).toString()
        m_name = intent.getStringExtra(MainActivity.EXTRA_NAME).toString()


        binding.textName.text = m_name
        binding.textAdress.text = m_address

        binding.btnImpriir.setOnClickListener {
            sendCommand()
        }
        binding.btnPrint2.setOnClickListener {
            PrinterFormat2()
        }

        binding.btnDenectar.setOnClickListener {
            disconnect()
        }

    }
    private fun sendCommand(){
        val ss = imp.Formato2()
        if(m_bluetoothSocket != null){
            try{
                m_bluetoothSocket!!.outputStream.write(ss, 0, ss!!.size)
            }catch(e: IOException){
                e.printStackTrace()
            }
        }
    }

    private fun PrinterFormat2(){
        val ss = imp.Formato1()
        if(m_bluetoothSocket != null){
            try{
                m_bluetoothSocket!!.outputStream.write(ss, 0, ss!!.size)
            }catch(e: IOException){
                e.printStackTrace()
            }
        }
    }

    private fun disconnect(){
        if(m_bluetoothSocket != null){
            try {
                m_bluetoothSocket!!.close()
                m_bluetoothSocket = null
                m_isConnected = false
            }catch(e: IOException){
                e.printStackTrace()
            }
        }
        finish()

}

}
