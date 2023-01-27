package com.example.wallpaper.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.wallpaper.R
import com.example.wallpaper.databinding.ItemRvBinding
import java.util.ArrayList

class RvAdapter(val context: Context,private val list: ArrayList<Int>, val rvClick: RvClick) :
    RecyclerView.Adapter<RvAdapter.Vh>() {

    private var lastPosition =  -1
    inner class Vh(private val itemRvBinding: ItemRvBinding) :
        RecyclerView.ViewHolder(itemRvBinding.root) {

        fun onBind(imageId: Int,position: Int) {
            itemRvBinding.imageContainer.setImageResource(imageId)

            val rvAnim = if (position>lastPosition){
                AnimationUtils.loadAnimation(context, R.anim.item_layout_animation)
            }else{
                AnimationUtils.loadAnimation(context,R.anim.item_scroll_animation)
            }
            lastPosition = position

            itemRvBinding.root.startAnimation(rvAnim)

            itemRvBinding.root.setOnClickListener {
                rvClick.rvItemClicked(imageId)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) = holder.onBind(list[position],position)


    override fun getItemCount(): Int = list.size


    interface RvClick{
        fun rvItemClicked(imageId: Int)
    }

}