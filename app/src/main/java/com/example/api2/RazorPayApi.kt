package com.example.api2


import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface RazorPayApi {
    @GET("customers")
    fun getCustomer(@HeaderMap header: HashMap<String, String>): Observable<UserResponseListModel>

    @POST("customers")
    fun postCustomer(
        @HeaderMap header: HashMap<String, String>,
        @Body post: PostDataModel
    ): Observable<PostDataModel>

    @PUT("customers/{id}")
    fun putCustomer(@HeaderMap header: HashMap<String, String>, @Path("id") id: String?, @Body userUpdateItem: UpdateModel): Observable<UpdateModel>

    companion object Factory {
        fun creatRetrofit(): RazorPayApi {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.razorpay.com/v1/")
                .build()
                .create(RazorPayApi::class.java)
            return retrofit
        }
    }
}