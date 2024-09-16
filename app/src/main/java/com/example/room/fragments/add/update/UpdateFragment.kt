package com.example.room.fragments.add.update

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.room.R
import com.example.room.model.User
import com.example.room.viewmodel.UserViewModel


class UpdateFragment : Fragment() {
   private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mUserViewModel:UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val view= inflater.inflate(R.layout.fragment_update, container, false)

        mUserViewModel=ViewModelProvider(this).get(UserViewModel::class.java)
        view.findViewById<EditText>(R.id.uptextView).setText(args.currentUser.fname)
        view.findViewById<EditText>(R.id.uptextView2).setText(args.currentUser.lname)
        view.findViewById<EditText>(R.id.uptextView3).setText(args.currentUser.age.toString())

        view.findViewById<Button>(R.id.update).setOnClickListener {
            updateItem(view)
        }

        view.findViewById<Button>(R.id.delete).setOnClickListener {
            deleteUser()
        }
        view.findViewById<Button>(R.id.delete_all).setOnClickListener()
        {
            deleteAllUsers()
        }
        //add menu
        setHasOptionsMenu(true)
        return  view
    }

    private fun updateItem(view:View) {

        val editTextFirstName = view.findViewById<EditText>(R.id.uptextView)
        val editTextLastName = view.findViewById<EditText>(R.id.uptextView2)
        val editTextAge = view.findViewById<EditText>(R.id.uptextView3)
        val firstName = editTextFirstName.text.toString()
        val lastName = editTextLastName.text.toString()
        val age = editTextAge.text
        if(CheckInput(firstName,lastName,age))
        {
            //Creat User Object
            val user= User(args.currentUser.id,firstName,lastName,Integer.parseInt(age.toString()))
            mUserViewModel.updateUser(user)
            Toast.makeText(requireContext(),"successfully updated", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        else
        {
            Toast.makeText(requireContext(),"please fill out all fields", Toast.LENGTH_LONG).show()

        }

    }
    private fun CheckInput(firstName:String,lastName:String,age: Editable):Boolean
    {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
         inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.delete_menu){
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
         val builder=AlertDialog.Builder(requireContext())

        builder.setPositiveButton("Yes"){_,_->
            mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(requireContext(),"Successfully removed: ${args.currentUser.fname}",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No"){_,_-> }
        builder.setTitle("Delete ${args.currentUser.fname}?")
        builder.setMessage("Are you sure you want to delete ${args.currentUser.fname}?")
        builder.create().show()
    }

    private fun deleteAllUsers() {
        val builder= AlertDialog.Builder(requireContext())

        builder.setPositiveButton("Yes"){_,_->
            mUserViewModel.deleteAllUsers()
            Toast.makeText(requireContext(),"Successfully removed everything}",
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No"){_,_-> }
        builder.setTitle("Delete everything?")
        builder.setMessage("Are you sure you want to delete everything?")
        builder.create().show()
    }

}


