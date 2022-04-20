package uz.isystem.valyutakursi

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.isystem.valyutakursi.core.adapter.Adapter
import uz.isystem.valyutakursi.databinding.ActivityMainBinding
import uz.isystem.valyutakursi.network.connection.NetworkConnection
import uz.isystem.valyutakursi.network.model.CurrencyData
import uz.isystem.valyutakursi.network.service.CBUService

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivityTAG"
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val adapter = Adapter()


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.listValyute.adapter = adapter
        binding.listValyute.layoutManager = GridLayoutManager(this, 1)
        val connection = NetworkConnection.getRetrofit()

        val service: CBUService = connection.create(CBUService::class.java)

        val result = service.getLatestData()
        
        result.enqueue(object : Callback<List<CurrencyData>> {
            override fun onResponse(
                call: Call<List<CurrencyData>>,
                response: Response<List<CurrencyData>>
            ) {


                if (response.isSuccessful) {
                    adapter.clearData()
                    val data = response.body()
                    adapter.data = data as ArrayList<CurrencyData>
                    Log.d(TAG, "onResponse: $data")

                }

            }

            override fun onFailure(call: Call<List<CurrencyData>>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t}")
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_LONG).show()
            }

        })

    }
}















































































































