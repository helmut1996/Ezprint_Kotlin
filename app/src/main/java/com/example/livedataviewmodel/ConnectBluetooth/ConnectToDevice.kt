@file:Suppress("DEPRECATION")

package com.example.livedataviewmodel.ConnectBluetooth

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Context
import android.os.AsyncTask
import android.util.Log
import com.example.livedataviewmodel.DetalleActivity
import java.io.IOException

@Suppress("DEPRECATION", "DEPRECATION")
class ConnectToDevice(c: Context) : AsyncTask<Void, Void, String>(){
    private var connectSucess: Boolean = true
    @SuppressLint("StaticFieldLeak")
    private val context: Context

    init{
        this.context = c
    }

    @Deprecated("Deprecated in Java")
    override fun onPreExecute() {
        super.onPreExecute()
        DetalleActivity.m_progress = ProgressDialog.show(context, "Connecting...", "please wait")
    }

    @Deprecated("Deprecated in Java")
    @SuppressLint("MissingPermission")
    override fun doInBackground(vararg p0: Void?): String {
        try{
            if(DetalleActivity.m_bluetoothSocket == null || !DetalleActivity.m_isConnected){
                DetalleActivity.m_bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
                val device: BluetoothDevice = DetalleActivity.m_bluetoothAdapter.getRemoteDevice(
                    DetalleActivity.m_address
                )
                DetalleActivity.m_bluetoothSocket = device.createInsecureRfcommSocketToServiceRecord(
                    DetalleActivity.m_myUUID
                )
                BluetoothAdapter.getDefaultAdapter().cancelDiscovery()
                DetalleActivity.m_bluetoothSocket!!.connect()

            }
        }catch (e: IOException){
            connectSucess = false
            e.printStackTrace()
        }
        return null.toString()
    }

    @Deprecated("Deprecated in Java")
    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        if(!connectSucess){
            Log.i("data", "couldn't connect")
        }else{
            DetalleActivity.m_isConnected = true
        }
        DetalleActivity.m_progress.dismiss()
    }
}

