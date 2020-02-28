package com.example.weatherassessment.data.network

import com.example.weatherassessment.utils.Constants
import com.example.weatherassessment.model.WeatherResponseModel
import com.example.weatherassessment.utils.Constants.APPID
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    //using Rx
    //weather?zip=110089,IN&APPID=d0a641e8d1accb9bb0ff0ef9dbe7a64a
    @GET("weather")
    fun getCurrentWeatherByZip(@Query("zip") zip: String, @Query("appid") appid: String = APPID): Observable<WeatherResponseModel>

    //forecast?zip=94040&appid=b6907d289e10d714a6e88b30761fae22
    @GET("forecast")
    fun getWeeklyWeatherByZip(@Query("zip") zip: String, @Query("appid") appid: String = APPID): Observable<List<WeatherResponseModel>>


    companion object {
        operator fun invoke(networkConnectionInterceptor: NetworkConnectionInterceptor): ApiService {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(networkConnectionInterceptor)
                .build()

            return Retrofit.Builder()
                .client(client)
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}