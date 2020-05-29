package com.example.ss

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_bikelist.view.*

class Adapter(val list: List<String>): RecyclerView.Adapter<ViewHolder>() {
    inner class ViewHolder {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): com.example.ss.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row_bikelist,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount()=list.size



    override fun onBindViewHolder(holder: com.example.ss.ViewHolder, position: Int) {

    }
}
class ViewHolder(view: View):RecyclerView.ViewHolder(view){
    val position = view.position
    val availableCNT = view.availableCNT
    val empCNT = view.empCNT
    val cAddress = view.cAddress

}