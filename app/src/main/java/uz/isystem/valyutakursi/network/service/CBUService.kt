package uz.isystem.valyutakursi.network.service


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import uz.isystem.valyutakursi.network.model.CurrencyData

interface CBUService {

    @GET("oz/arkhiv-kursov-valyut/json/")
    fun getLatestData(): Call<List<CurrencyData>>

    @GET("oz/arkhiv-kursov-valyut/json/all/{date}/")
    fun getDateData(@Path("date") date: String): Call<List<CurrencyData>>

    @GET("oz/arkhiv-kursov-valyut/json/{type}/{date}/")
    fun getDateDataByType(
        @Path("date") date: String,
        @Path("type") type: String
    ): Call<List<CurrencyData>>

}