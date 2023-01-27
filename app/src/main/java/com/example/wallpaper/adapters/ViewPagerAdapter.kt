package com.example.wallpaper.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.wallpaper.Utils.RecyclerItemDecoration
import com.example.wallpaper.databinding.RvLayoutBinding
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import java.util.ArrayList

class ViewPagerAdapter(val context: Context, val list: Array<List<Int>>?, val rvClick: RvAdapter.RvClick) :
    RecyclerView.Adapter<ViewPagerAdapter.Vh>() {

    inner class Vh(private val rvLayoutBinding: RvLayoutBinding) : ViewHolder(rvLayoutBinding.root) {
        fun onBind(position: Int) {
            rvLayoutBinding.myRv.layoutManager = GridLayoutManager(context, 3)

            rvLayoutBinding.myRv.adapter = ScaleInAnimationAdapter(RvAdapter(context,list?.get(position) as ArrayList<Int>, rvClick))
            rvLayoutBinding.myRv.addItemDecoration(RecyclerItemDecoration(3, 1))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(RvLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) = holder.onBind(position)


    override fun getItemCount(): Int = 5


}

