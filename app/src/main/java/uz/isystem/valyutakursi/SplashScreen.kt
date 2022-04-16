package uz.isystem.valyutakursi

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import uz.isystem.valyutakursi.databinding.ActivitySplashScreenBinding
import uz.isystem.valyutakursi.extentions.TimeExtentions

class SplashScreen : AppCompatActivity() {
    private var _binding: ActivitySplashScreenBinding? = null
    private val binding get() = _binding!!
    private var timer: CountDownTimer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val animation = AnimationUtils.loadAnimation(this, R.anim.name_)
        binding.loading.startAnimation(animation)
        timer = TimeExtentions(3000, 1000,
            doOnFinish = {
                val intent = Intent(this, SetupActivity::class.java)
                startActivity(intent)
                finish()
            }
        ).start()

    }

    override fun onStop() {
        super.onStop()
        timer?.cancel()
    }
}