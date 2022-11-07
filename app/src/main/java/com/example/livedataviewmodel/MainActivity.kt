package com.example.livedataviewmodel

import android.Manifest
import android.R
import android.annotation.SuppressLint
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.livedataviewmodel.adapter.MyAdapter
import com.example.livedataviewmodel.databinding.ActivityMainBinding

import java.util.*


lateinit var binding: ActivityMainBinding




@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    companion object{
        val EXTRA_ADDRESS: String = "Device_address"
        val EXTRA_NAME: String = "Device_name"
    }
    @RequiresApi(Build.VERSION_CODES.S)
    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        //para permisos de bluetooth
        requestBlePermissions(this,12)
        // Ask for location permission if not already allowed
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) ActivityCompat.requestPermissions(
            this, arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION
            ), 1

        )
        val   bluetoothManager=applicationContext.getSystemService(BLUETOOTH_SERVICE)as BluetoothManager
        //lateinit var adapter: MyAdapter
       // lateinit var DevicesArrays:ArrayList<Devices_list>
        val bluetoothAdapter=bluetoothManager.adapter
       // var name:String
       // var adress:String




        binding.btnActivarB.setOnClickListener {
            val intent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(intent,1)
            Toast.makeText(this,"Bluetooh Encendido", Toast.LENGTH_LONG).show()
            binding.textResultado.text="Encendido"
        }

        binding.btnApagarB.setOnClickListener {
            bluetoothAdapter.disable()
            Toast.makeText(this,"Bluetooh Apagado", Toast.LENGTH_LONG).show()
            binding.textResultado.text = "Apagado"
        }



        /*
        binding.btnBuscar.setOnClickListener {


            val pairedDevices = bluetoothAdapter.bondedDevices
            val data =StringBuffer()
            DevicesArrays = arrayListOf()

            pairedDevices.forEach{
                DevicesArrays.add(Devices_list(nombre = it.name, direccion = it.address))
            }

            for (device: BluetoothDevice in pairedDevices){
                data.append("Device Name="+device.name+"Devices Address"+device.address)

            }

            if (data.isEmpty()){
                Toast.makeText(this,"Bluetooh Devices not Paired", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,data, Toast.LENGTH_LONG).show()
                val layoutManager = LinearLayoutManager(this)
                binding.listDevices.layoutManager= layoutManager
                binding.listDevices.setHasFixedSize(true)
                adapter = MyAdapter(DevicesArrays)
                binding.listDevices.adapter = adapter


            }
        }


         */

        binding.btnBuscar.setOnClickListener{
            val pairedDevices = bluetoothAdapter.bondedDevices
            val list:ArrayList<BluetoothDevice> = ArrayList()
            if(!pairedDevices.isEmpty()){
                for(device: BluetoothDevice in pairedDevices){

                    list.add(device)
                    Log.i("device", ""+device.name)
                }
            }else{
                Toast.makeText(this,"no paired bluetooth devices found",Toast.LENGTH_SHORT).show()
            }
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
            binding.listDevices.adapter = adapter
            binding.listDevices.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
                val device: BluetoothDevice = list[position]
                val address: String = device.address
                val name: String =device.name

                val intent = Intent(this,DetalleActivity::class.java)
                intent.putExtra(EXTRA_ADDRESS, address)
                intent.putExtra(EXTRA_NAME,name)
                startActivity(intent)
            }
        }


    }




    @RequiresApi(Build.VERSION_CODES.S)
    fun requestBlePermissions(activity: Activity?, requestCode: Int) {
        //validacion de Bluetooth
        val BLE_PERMISSIONS = arrayOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.BLUETOOTH_CONNECT,
            Manifest.permission.BLUETOOTH_SCAN,
            Manifest.permission.BLUETOOTH_ADMIN,
            Manifest.permission.BLUETOOTH
        )

        val ANDROID_12_BLE_PERMISSIONS = arrayOf(
            Manifest.permission.BLUETOOTH_SCAN,
            Manifest.permission.BLUETOOTH_CONNECT,
            Manifest.permission.BLUETOOTH_ADVERTISE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.BLUETOOTH_ADMIN,
            Manifest.permission.BLUETOOTH
        )


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) ActivityCompat.requestPermissions(
            activity!!,
            ANDROID_12_BLE_PERMISSIONS,
            requestCode
        ) else ActivityCompat.requestPermissions(
            activity!!, BLE_PERMISSIONS, requestCode
        )
        /// fin ///
    }
}









