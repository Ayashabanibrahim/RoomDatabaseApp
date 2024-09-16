package com.example.room.fragments.add.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.room.R
import com.example.room.model.User

class MyAdapter():RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    private var userList= emptyList<User>()
    class MyViewHolder(view: View):RecyclerView.ViewHolder(view) {
         val Id=view.findViewById<TextView>(R.id.textView4)
        val fname=view.findViewById<TextView>(R.id.textView5)
        val lname=view.findViewById<TextView>(R.id.textView6)
        val age=view.findViewById<TextView>(R.id.textView7)
       // val sname=view.findViewById<TextView>(R.id.streetname)
       // val snum=view.findViewById<TextView>(R.id.streetnumber)
        val rowLayout=view.findViewById<ConstraintLayout>(R.id.row_layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
         return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false))
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
         val curItem=userList[position]
        holder.Id.text=curItem.id.toString()
        holder.fname.text=curItem.fname.toString()
        holder.lname.text=curItem.lname.toString()
        holder.age.text=curItem.age.toString()
        //holder.sname.text=curItem.address.streetName
        //holder.snum.text=curItem.address.streetNumber.toString()
        holder.rowLayout.setOnClickListener{
            val action=ListFragmentDirections.actionListFragmentToUpdateFragment(curItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
      return userList.size
    }

    fun refresheData(users:List<User>){
        this.userList=users
        notifyDataSetChanged()
    }

}