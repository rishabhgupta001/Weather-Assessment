package com.example.weatherassessment.data.repository

import com.example.weatherassessment.data.network.ApiService
import com.example.weatherassessment.model.WeatherResponseModel
import io.reactivex.Observable

class ForecastRepository(private val api: ApiService) {
    val TAG = ForecastRepository::class.java.simpleName

    fun getCurrentWeather(zip: String): Observable<WeatherResponseModel> {
        return api.getCurrentWeatherByZip(zip)
    }

    fun getWeeklyWeather(zip: String): Observable<List<WeatherResponseModel>> {
        return api.getWeeklyWeatherByZip(zip)
    }

}

/* @SuppressLint("CheckResult")
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
    }*/