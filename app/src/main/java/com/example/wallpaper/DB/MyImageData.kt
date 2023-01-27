package com.example.wallpaper.DB

import com.example.wallpaper.R

object MyImagesData {

    fun loadMapList(): HashMap<String, Array<List<Int>>> {
        val imagesList = arrayListOf(
            R.drawable.image_1,
            R.drawable.image_2,
            R.drawable.image_3,
            R.drawable.image_4,
            R.drawable.image_5,
            R.drawable.image_6,
            R.drawable.image_7,
            R.drawable.image_8,
            R.drawable.image_9,
            R.drawable.image_10,
            R.drawable.image_11,
            R.drawable.image_12,
            R.drawable.image_13,
            R.drawable.image_14,
            R.drawable.image_15,
            R.drawable.image_16,
            R.drawable.image_17,
            R.drawable.image_18,
            R.drawable.image_19,
            R.drawable.image_20,
            R.drawable.image_21,
            R.drawable.image_22,
            R.drawable.image_23,
            R.drawable.image_24,
            R.drawable.image_25,
            R.drawable.image_26,
            R.drawable.image_27,
            R.drawable.image_28,
            R.drawable.image_29,
            R.drawable.image_30,
            R.drawable.image_31,
            R.drawable.image_32,
            R.drawable.image_33,
            R.drawable.image_34,
            R.drawable.image_35,
            R.drawable.image_36,
            R.drawable.image_37,
            R.drawable.image_38,
            R.drawable.image_39,
            R.drawable.image_40,

            )

        val imageMap = HashMap<String, Array<List<Int>>>()
        val titleList = arrayListOf("home", "popular", "random", "liked")
        for (i in titleList) {
            val mapList = arrayOf(
                imagesList.shuffled(),
                imagesList.shuffled(),
                imagesList.shuffled(),
                imagesList.shuffled(),
                imagesList.shuffled()
            )
            imageMap[i] = mapList

        }

        return imageMap
    }


}