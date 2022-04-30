package uz.isystem.valyutakursi.core.adapter

import android.annotation.SuppressLint
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.isystem.valyutakursi.R
import uz.isystem.valyutakursi.databinding.ItemValyuteBinding
import uz.isystem.valyutakursi.network.model.CurrencyData


class Adapter : RecyclerView.Adapter<Adapter.ViewHolder>() {
    var data = ArrayList<CurrencyData>()
        set(value) {
            field.clear()
            field.addAll(value)
            notifyItemRangeInserted(field.size - value.size, value.size)
        }


    fun clearData() {
        data.clear()
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemValyuteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bindData(d: CurrencyData) {
            val delta1 = d.Diff.toDouble()
            if (delta1 >= 0) {
                binding.imageView.setImageResource(R.drawable.yuqoriga)
            } else if (delta1 < 0) {
                if (delta1 < 0) {
                    binding.imageView.setImageResource(R.drawable.pastga)
                }
            }
            binding.name.ellipsize = TextUtils.TruncateAt.MARQUEE
            binding.name.isSelected = true
            binding.code.text = "Code " + d.Code
            binding.nominal.text = "Nominal " + d.Nominal
            binding.name.text = d.CcyNm_UZ + ", " + d.Ccy
//            val animatsia=AnimationUtils.loadAnimation(binding.name.context,R.anim.name_)
//            binding.name.startAnimation(animatsia)
            binding.kurs.text = d.Rate
            binding.date.text = d.Date
            binding.delta.text = d.Diff


        }

        val searchDta = data.filter {
            it.CcyNm_UZ.startsWith("a")
            data.add(it)
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemValyuteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false

            )
        )
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bindData(data[position])


    override fun getItemCount(): Int = data.size

}

