package uz.isystem.valyutakursi.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.isystem.valyutakursi.core.model.PageData
import uz.isystem.valyutakursi.databinding.ItemBoardBinding

class OnBoardAdapter : RecyclerView.Adapter<OnBoardAdapter.ViewHolder>() {
    var data = ArrayList<PageData>()
        set(value) {
            field.clear()
            field.addAll(value)
            notifyItemRangeInserted(field.size - value.size, value.size)
        }

    fun clearData() {
        data.clear()
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemBoardBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(d: PageData) {

            binding.imageBoard.setImageResource(d.image)
            binding.titleBoard.text = d.title
            binding.descriptionBoard.text = d.description
//            val icon = BitmapFactory.decodeResource(
//                binding.rootView.context.resources,
//                d.image
//            )
//            Palette.Builder(icon).generate {
//                it?.let {
//                    binding.rootView.setBackgroundColor(it.getDominantColor(d.backgroundColor))
//                }
//            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemBoardBinding.inflate(
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