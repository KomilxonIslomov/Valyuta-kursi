package uz.isystem.valyutakursi.network.connection

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkConnection {
    companion object {
        private var retrofit: Retrofit? = null
        fun getRetrofit(): Retrofit {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl("https://cbu.uz/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            }
            return retrofit!!
        }
    }
}