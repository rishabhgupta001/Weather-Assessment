package com.example.weatherassessment.data.repository

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.weatherassessment.data.network.ApiService
import com.example.weatherassessment.data.network.StatusCode
import com.example.weatherassessment.model.WeatherResponseModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ForecastRepository(private val api: ApiService) {
    val TAG = ForecastRepository::class.java.simpleName
    val weatherZip = MutableLiveData<WeatherResponseModel>()

    @SuppressLint("CheckResult")
    fun getCurrentWeather(zipCode: String): MutableLiveData<WeatherResponseModel> {
        val responseModel = WeatherResponseModel()

        api.getCurrentWeatherByZip(zipCode)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe {
                responseModel.statusCode = StatusCode.START
                weatherZip.value = responseModel
            }
            .observeOn(AndroidSchedulers.mainThread())
            //below code is working on Main thread
            .subscribe(
                { success ->
                    responseModel.statusCode = StatusCode.SUCCESS
                    weatherZip.value = success
                }, { error ->
                    Log.d(TAG, "api error ${error.message}")
                    responseModel.msg = error.localizedMessage!!
                    responseModel.statusCode = StatusCode.ERROR
                })

        return weatherZip
    }

/*    @SuppressLint("CheckResult")
    private fun getCurrentWeatherByZip(zipCode: String): Pair<MutableLiveData<WeatherResponseModel>, MutableLiveData<NetworkState>> {
        api.getCurrentWeatherByZip(zipCode)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe {
                networkState.postValue(NetworkState.START)
            }
            .observeOn(AndroidSchedulers.mainThread())
            //below code is working on Main thread
            .subscribe(
                { success ->
                    networkState.value = NetworkState.SUCCESS
                    weatherZip.value = success
                }, { error ->
                    Log.d(TAG, "api error ${error.message}")
                    networkState.value = NetworkState.error(error.message!!)
                })

        return Pair(weatherZip, networkState)
    }*/
}