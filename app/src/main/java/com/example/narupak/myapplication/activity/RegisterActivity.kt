package com.example.narupak.myapplication.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import com.example.narupak.myapplication.GenericRequest
import com.example.narupak.myapplication.R
import com.example.narupak.myapplication.R.id.user
import com.example.narupak.myapplication.model.User
import com.example.narupak.myapplication.service.ApiInterface

import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        btn_register.setOnClickListener(View.OnClickListener {
            val username = rg_username.text.toString()
            val password = rg_password.text.toString()
            val firstname = rg_firsrname.text.toString()
            val lastname = rg_lastname.text.toString()
            val address = rg_address.text.toString()
            val phone = rg_phone.text.toString()
            val mail = rg_email.text.toString()
            val user = User(username,password,firstname,lastname,address,phone,mail)
            callWebservice(user)
            var intent = Intent(baseContext,LoginActivity::class.java)
            startActivity(intent)
            finish()
        })
    }
    fun callWebservice(user : User){
        val apiService = ApiInterface.create()
        val users = GenericRequest<User>()
        users.request = user
        val call = apiService.registerUser(users)
        Log.d("REQUEST", call.toString() + "")
        call.enqueue(object : retrofit2.Callback<User>{
            override fun onResponse(call: Call<User>?, response: Response<User>?) {
                if(response!!.isSuccessful){
                    Log.d("success",response.body().toString())
                }
            }

            override fun onFailure(call: Call<User>?, t: Throwable?) {
                Log.d("failed",t.toString())
            }
        })
    }
//        call.enqueue(object : retrofit2.Callback<User>{
//            override fun onResponse(call: Call<User>?, response: Response<User>?) {
//                if(response != null){
//                    //Log.d("response",response.body().kind!!.toString())
//                    Log.d("response",response.body().data!!.toString())
//                    val list : data = response.body().data!!
//                    var dist : String = list.dist.toString()
//                    //animal_list.add(dist)
//                    val children : List<children> = list.children!!
//                    Log.d("response",children.toString())
//                    //var childrens : List<Childrean> = children.childrean!!
//                    children.forEach { it: children ->
//                        val data: dataChidren = it.data!!
//                        animal_list.add(data.title.toString())
//                        adapterview(this@RecyclerActivity)
//                    }
//                }
//            }
//            override fun onFailure(call: Call<CategoryResponse>?, t: Throwable?) {
//                Log.d("failed",t.toString())
//            }
//        })
    //}
}
