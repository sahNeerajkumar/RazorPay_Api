package com.example.api2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import android.util.Base64
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private val userList = ArrayList<Item>()
    private lateinit var adapter: UserAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var post_button: FloatingActionButton

    @SuppressLint("MissingInflatedId", "CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recycle_view)
        post_button = findViewById(R.id.floating_button)

        post_button.setOnClickListener {
            startActivity(Intent(this,add_data::class.java))
        }

        // Your API key and secret

    }

    @SuppressLint("CheckResult")
    fun getItemes(){
        val apiKey = "rzp_test_RSmJ5XIT4BmmhN"
        val apiSecret = "TXAQdDF6l09go7kbIj7mMZjJ"

        // Concatenate apiKey and apiSecret separated by a colon and encode to Base64
        val credentials = "$apiKey:$apiSecret"
        val authHeader = "Basic " + Base64.encodeToString(credentials.toByteArray(), Base64.NO_WRAP)

        val headerMap = HashMap<String, String>()
        headerMap["Authorization"] = authHeader

        RazorPayApi.creatRetrofit().getCustomer(headerMap)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                val allData = result.items

                if (!allData.isNullOrEmpty()) {
                    userList.addAll(allData)
                    adapter = UserAdapter(this, userList)
                    recyclerView.adapter = adapter
                    recyclerView.layoutManager = LinearLayoutManager(this)
                } else {
                    Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show()
                }
            }, { error ->
                Toast.makeText(this, "Error:${error.message}", Toast.LENGTH_SHORT).show()
            })
    }

    override fun onResume() {
        super.onResume()
        getItemes()
    }
}
