package com.example.room.fragments.add.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.room.R
import com.example.room.model.User
import com.example.room.viewmodel.UserViewModel


class AddFragment : Fragment() {
    private lateinit var mUserViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_add, container, false)
        mUserViewModel=ViewModelProvider(this).get(UserViewModel::class.java)
        view.findViewById<Button>(R.id.add).setOnClickListener(){
            insertDataToDatabase(view)

        }
        return view
    }

    private fun  insertDataToDatabase(view:View)
    {
        val editTextFirstName = view.findViewById<EditText>(R.id.textView)
        val editTextLastName = view.findViewById<EditText>(R.id.textView2)
        val editTextAge = view.findViewById<EditText>(R.id.textView3)
        val firstName = editTextFirstName.text.toString()
        val lastName = editTextLastName.text.toString()
        val age = editTextAge.text
        if(CheckInput(firstName,lastName,age))
        {
            //Creat User Object
            val user= User(0,firstName,lastName,Integer.parseInt(age.toString()))
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(),"successfully adding",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }
        else
        {
            Toast.makeText(requireContext(),"please fill out all fields",Toast.LENGTH_LONG).show()

        }

    }
    private fun CheckInput(firstName:String,lastName:String,age:Editable):Boolean
    {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

}