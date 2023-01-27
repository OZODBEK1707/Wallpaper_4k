package com.example.wallpaper

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.wallpaper.DB.MyImagesData
import com.example.wallpaper.adapters.RvAdapter
import com.example.wallpaper.adapters.ViewPagerAdapter
import com.example.wallpaper.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity(), RvAdapter.RvClick {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val titleList = arrayListOf("home", "popular", "random", "liked")
    var checkedNavItemIndex = 0
    private val imageMap = MyImagesData.loadMapList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        var actionBarTitle = "Home"
        val tabTitles = arrayOf("ALL", "NEW", "ANIMALS", "TECHNOLOGY", "NATURE")

        binding.viewPager.adapter =
            ViewPagerAdapter(this, imageMap[titleList[checkedNavItemIndex]], this)
        TabLayoutMediator(binding.tabBar, binding.viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()

        binding.btnMenu.setOnClickListener {
            binding.navDrawerLayout.open()
        }


        binding.myNavView.setNavigationItemSelectedListener {
            if (it.itemId != R.id.menu_history && it.itemId != R.id.menu_about) {
                when (it.itemId) {
                    R.id.menu_home -> {
                        actionBarTitle = "Home"
                        checkedNavItemIndex = 0
                    }
                    R.id.menu_popular -> {
                        actionBarTitle = "Popular"
                        checkedNavItemIndex = 1
                    }
                    R.id.menu_random -> {
                        actionBarTitle = "Random"
                        checkedNavItemIndex = 2
                    }
                    R.id.menu_liked -> {
                        actionBarTitle = "My Favourites"
                        checkedNavItemIndex = 3
                    }
                }
                changeListImages(checkedNavItemIndex)
                binding.bottomNavView.menu.getItem(checkedNavItemIndex).isChecked = true
                binding.navDrawerLayout.close()
                binding.actionBarTitle.text = actionBarTitle
            }else{
                Toast.makeText(this, "NOT AVAILABLE", Toast.LENGTH_SHORT).show()
            }

            true
        }




        binding.bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_menu_home -> {
                    actionBarTitle = "Home"
                    checkedNavItemIndex = 0
                }
                R.id.bottom_menu_popular -> {
                    actionBarTitle = "Popular"
                    checkedNavItemIndex = 1
                }
                R.id.bottom_menu_random -> {
                    actionBarTitle = "Random"
                    checkedNavItemIndex = 2
                }
                R.id.bottom_menu_like -> {
                    actionBarTitle = "My Favourites"
                    checkedNavItemIndex = 3
                }
            }
            changeListImages(checkedNavItemIndex)
            binding.actionBarTitle.text = actionBarTitle
            true
        }


        binding.btnSearch.setOnClickListener {
            Toast.makeText(this, "This function is not available yet", Toast.LENGTH_SHORT).show()
        }

    }

    private fun changeListImages(position: Int) {
        binding.viewPager.adapter =
            ViewPagerAdapter(this, imageMap[titleList[checkedNavItemIndex]], this)
    }

    override fun rvItemClicked(imageId: Int) {
        val intent = Intent(this, ShowImageActivity::class.java)
        intent.putExtra("id", imageId)
        startActivity(intent)
    }
}