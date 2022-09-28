package pe.joshluq.birrapp.feature.findbeer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pe.joshluq.birrapp.databinding.ItemBeerBinding
import pe.joshluq.birrapp.domain.model.Beer

class BeerListAdapter(private val listener: OnItemClickListener) :
    ListAdapter<Beer, BeerListAdapter.BeerViewHolder>(BeerDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemBeerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            .let(::BeerViewHolder)

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class BeerViewHolder(private val binding: ItemBeerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Beer) = with(binding) {
            tvTitle.text = item.name
            root.setOnClickListener {
                listener.onItemClick(item)
            }
        }
    }

    object BeerDiffCallback : DiffUtil.ItemCallback<Beer>() {
        override fun areItemsTheSame(oldItem: Beer, newItem: Beer): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Beer, newItem: Beer): Boolean {
            return oldItem == newItem
        }
    }

    fun interface OnItemClickListener {
        fun onItemClick(beer: Beer)
    }

}