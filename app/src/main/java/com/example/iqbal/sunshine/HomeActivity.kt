package com.example.iqbal.sunshine

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.util.Log.*
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {

    private val tag = this::class.java.simpleName

    private lateinit var adapter: ForcastAdapter
    private val forcastList = mutableListOf<Forcast>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setupView()
        getData()
    }

    private fun setupView() {
        adapter = ForcastAdapter(forcastList)
        recForcast.layoutManager = LinearLayoutManager(this)
        recForcast.adapter = adapter
    }

    private fun getData() {
        App.api.getForcast().enqueue(object : Callback<ForcastResponse> {
            override fun onFailure(call: Call<ForcastResponse>?, t: Throwable?) {
                e(tag, t?.message)
            }

            override fun onResponse(call: Call<ForcastResponse>?, response: Response<ForcastResponse>?) {
                i(tag, "data : ${Gson().toJsonTree(response?.body())}")
                val forcastResponse: ForcastResponse? = response?.body()
                val kota = forcastResponse?.city?.name
                val kodeNegara = forcastResponse?.city?.country?.toUpperCase()
                tvKota.text = "$kota, $kodeNegara"

                val nForcastList = forcastResponse?.forcastList
                nForcastList?.map {
                    it.dtTxt = convertDataToWeekDay(it.dtTxt)
                }
                val newForcastList = nForcastList?.distinctBy { it.dtTxt }
                //menampilkan current forcast ke home
                displayCurForcast(newForcastList?.get(0))
                nForcastList?.let {
                    forcastList.addAll(it)
                    forcastList.removeAt(0)
                    adapter.notifyDataSetChanged()
                }
            }
        })
    }

    private fun displayCurForcast(forcast: Forcast?){
        tvHariIni.text = "Hari ini"
        tvDerajatSuhu.text = forcast?.main?.temp?.toDouble()?.toInt()?.minus(273)?.toString()
        val icon = getIcon(forcast?.weather?.get(0)?.id?.toInt()?:0)
        imgStatus.setImageDrawable(
                ContextCompat.getDrawable(this, icon)
        )
        tvStatus.text = forcast?.weather?.get(0)?.description
    }
}