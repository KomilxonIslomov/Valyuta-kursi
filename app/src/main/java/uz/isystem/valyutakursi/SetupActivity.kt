package uz.isystem.valyutakursi

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import uz.isystem.valyutakursi.core.adapter.OnBoardAdapter
import uz.isystem.valyutakursi.core.model.PageData
import uz.isystem.valyutakursi.databinding.ActivitySetupBinding

class SetupActivity : AppCompatActivity() {
    private lateinit var data: ArrayList<PageData>
    private val adapter = OnBoardAdapter()

    private var _binding: ActivitySetupBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySetupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.onBoardView.adapter = adapter

        loadBoardData()
        binding.skipButton.setOnClickListener {
            binding.onBoardView.setCurrentItem(2, true)

        }

        binding.nextButton.setOnClickListener {
            if (binding.onBoardView.currentItem == 2) {
                intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {

                binding.onBoardView.setCurrentItem(binding.onBoardView.currentItem + 1, true)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.onBoardView.registerOnPageChangeCallback(viewCallback)
    }

    private val viewCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)

            if (position == 2) {
                binding.skipButton.visibility = View.GONE
                binding.nextButton.setText(R.string.start)
            } else {
                binding.nextButton.setText(R.string.next)
                binding.skipButton.visibility = View.VISIBLE
            }
        }
    }

    override fun onStop() {
        super.onStop()

        binding.onBoardView.registerOnPageChangeCallback(viewCallback)
    }

    private fun loadBoardData() {

        this.data = ArrayList<PageData>()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            data.addAll(
                listOf(
                    PageData(
                        title = "Eng ishonli",
                        description = "Markaziy Bank ma'lumotlari orqali valyuta kurslarimiz ko'rsatiladi!",
                        image = R.drawable.ic__1,
                        backgroundColor = getColor(R.color.purple_700)
                    )
                )
            )
            data.addAll(
                listOf(
                    PageData(
                        title = "Eng so'ngi",
                        description = "So'ngi ma'lumotlar Markaziy Bank ma'lumotlari orqali valyutalar!",
                        image = R.drawable.ic__2,
                        backgroundColor = getColor(R.color.purple_700)
                    )
                )
            )
            data.addAll(
                listOf(
                    PageData(
                        title = "Deyarli barcha",
                        description = "CBU.uz valyutalar kursi so'mdagi qiymati!",
                        image = R.drawable.ic__3,
                        backgroundColor = getColor(R.color.teal_200)
                    )
                )
            )
            adapter.data = data

        }

    }

    fun onClick(view: View) {}
}