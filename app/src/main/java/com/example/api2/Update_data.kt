package com.example.api2

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.api2.databinding.ActivityUpdateDataBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class Update_data : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateDataBinding
    private var userId: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userId = intent.getStringExtra("customer_Id")
        binding.updateButton.setOnClickListener {
            updateData()
        }
    }

//    override fun onResume() {
//        super.onResume()
//        updateData()
//    }

    @SuppressLint("CheckResult")
    private fun updateData() {
        val name = binding.nameEditTextUpdate.text.toString()
        val email = binding.emailEditTextUpdate.text.toString()
        val contact = binding.contactEditTextUpdate.text.toString()

        // Create UpdateModel instance

        val apiKey = "rzp_test_RSmJ5XIT4BmmhN"
        val apiSecret = "TXAQdDF6l09go7kbIj7mMZjJ"

        val authenticator = "Basic " + android.util.Base64.encodeToString(
            "$apiKey:$apiSecret".toByteArray(),
            android.util.Base64.NO_WRAP
        )

        val headerMap = HashMap<String, String>()
        headerMap["Authorization"] = authenticator

        val update = UpdateModel(contact, email, name)

        RazorPayApi.creatRetrofit().putCustomer(headerMap,userId,update)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                Toast.makeText(this, "Data added successfully${result.name}", Toast.LENGTH_SHORT)
                    .show()
                finish()
            },
            { error ->
                Toast.makeText(this, "${error.message}", Toast.LENGTH_SHORT).show()
            })
    }
}



