package uz.isystem.valyutakursi

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.isystem.valyutakursi.core.adapter.Adapter
import uz.isystem.valyutakursi.databinding.ActivityMainBinding
import uz.isystem.valyutakursi.fragment.FavoriteFragment
import uz.isystem.valyutakursi.fragment.HomeFragment
import uz.isystem.valyutakursi.fragment.InfoFragment
import uz.isystem.valyutakursi.network.connection.NetworkConnection
import uz.isystem.valyutakursi.network.model.CurrencyData
import uz.isystem.valyutakursi.network.service.CBUService

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivityTAG"
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val adapter = Adapter()

    lateinit var toggle: ActionBarDrawerToggle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.menu.setOnClickListener {
//
//        }
        binding.listValyute.adapter = adapter
        binding.listValyute.layoutManager = LinearLayoutManager(this)
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


        toggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open, R.string.close)

        binding.drawerLayout.addDrawerListener(toggle)

        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.navView.setNavigationItemSelectedListener {
            it.isChecked = true
            when (it.itemId) {
                R.id.nav_home -> replaceFragment(HomeFragment(), it.title.toString())
                R.id.nav_fav -> replaceFragment(FavoriteFragment(), it.title.toString())
                R.id.nav_info -> replaceFragment(InfoFragment(), it.title.toString())
                R.id.nav_exit -> finish()

            }
            true

        }
    }

    private fun replaceFragment(fragment: Fragment, title: String) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
        binding.drawerLayout.closeDrawers()
        setTitle(title)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}