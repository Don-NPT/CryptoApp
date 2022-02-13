package com.example.android.cryptoinfo.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

private const val BASE_URL = "https://api.coinranking.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
//    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()


interface CryptoApiServices {
    @Headers("x-access-token: " + "coinrankingc042b072e5845b8519c8a98cd7f49b49f26ece1c9cce0fa9")
    @GET("v2/coins")
    suspend fun getCryptos(): CryptoInfo
//    suspend fun getPhotos(): String
}

object CryptoApi {
    val RETROFIT_SERVICES : CryptoApiServices by lazy {
        retrofit.create(CryptoApiServices::class.java) }
}