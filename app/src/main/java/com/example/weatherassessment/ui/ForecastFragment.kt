package com.example.weatherassessment.ui


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weatherassessment.R
import com.example.weatherassessment.data.network.ApiService
import com.example.weatherassessment.data.network.NetworkConnectionInterceptor
import com.example.weatherassessment.data.network.StatusCode
import com.example.weatherassessment.data.repository.ForecastRepository
import com.example.weatherassessment.databinding.FragmentForecastBinding
import com.example.weatherassessment.utils.Utils
import kotlinx.android.synthetic.main.fragment_forecast.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance


/**
 * A simple [Fragment] subclass.
 */
class ForecastFragment : Fragment() {
    /*override val kodein by kodein()
    private val factory: ForecastViewModelFactory by instance()*/

    private val TAG = ForecastFragment::class.java.simpleName

    private val viewModel: ForecastViewModel by lazy {
        ViewModelProvider(
            this,
            ForecastViewModelFactory(
                ForecastRepository(
                    ApiService.invoke(NetworkConnectionInterceptor(context!!))
                )
            )
        ).get(ForecastViewModel::class.java)
    }
    private lateinit var binding: FragmentForecastBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_forecast, container, false
            )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    /**
     * init
     */
    private fun init() {
        viewModel.getCurrentWeather("110030,IN")
        observeCurrentWeatherByZip()
    }

    private fun observeCurrentWeatherByZip() {
        viewModel.currentWeather.observe(viewLifecycleOwner, Observer { success ->
            when (success?.statusCode) {
                StatusCode.START -> {
                    Utils.hideKeyPad(activity!!)
                    progress_bar.visibility = View.VISIBLE
                }
                StatusCode.SUCCESS -> {
                    progress_bar.visibility = View.GONE
                    Log.d("DEBUG", "Base ${success.base.toString()}")

                }
                StatusCode.ERROR -> {
                    progress_bar.visibility = View.GONE
                    Utils.showToast(context!!, success.msg)
                }
            }
        })

    }

}
