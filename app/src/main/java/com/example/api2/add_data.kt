package com.example.api2

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.api2.databinding.ActivityAddDataBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class add_data : AppCompatActivity() {
    private lateinit var binding:ActivityAddDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

         binding.submitButton.setOnClickListener {
             addUser()
         }
    }

    override fun onResume() {
        super.onResume()
        addUser()
    }

    @SuppressLint("CheckResult")
    fun addUser() {
        val name = binding.nameEditText.text.toString()
        val email = binding.emailEditText.text.toString()
        val contact = binding.contactEditText.text.toString()
        val postData = PostDataModel(
            contact,
            name,
            email,
            "1",
           "12ABCDE2356F7GH",
            Notes("Tea, Earl Grey, Hot", "Tea, Earl Grey… decaf")
        )

        val apiKey = "rzp_test_RSmJ5XIT4BmmhN"
        val apiSecret = "TXAQdDF6l09go7kbIj7mMZjJ"

        val authenticator = "Basic" + android.util.Base64.encodeToString(
            "$apiKey:$apiSecret".toByteArray(),
            android.util.Base64.NO_WRAP
        )

        val headerMap = HashMap<String, String>()
        headerMap["Authorization"] = authenticator

        RazorPayApi.creatRetrofit().postCustomer(headerMap, postData)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                Toast.makeText(this, "Data added successfully${result.name}", Toast.LENGTH_SHORT).show()
                finish()
            })
            { error ->
                Toast.makeText(this, "${error.message}", Toast.LENGTH_SHORT).show()
            }
    }
}