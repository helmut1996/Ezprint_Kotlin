package com.example.livedataviewmodel.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.livedataviewmodel.DetalleActivity
import com.example.livedataviewmodel.R
import com.example.livedataviewmodel.models.Devices_list

class MyAdapter(private val Deviceslist:ArrayList<Devices_list>):RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_devices, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = Deviceslist [position]
        holder.nombre.text =currentItem.nombre
        holder.direccion.text = currentItem.direccion


    }

    override fun getItemCount(): Int {
        return Deviceslist.size
    }



    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val nombre:TextView
        val direccion:TextView
        init {
             nombre = itemView.findViewById(R.id.list_name)
             direccion = itemView.findViewById(R.id.list_address)
            itemView.setOnClickListener {
                Toast.makeText(itemView.context,"Precionando ${nombre.text.toString()}",Toast.LENGTH_LONG).show()


            }
        }


    }
}