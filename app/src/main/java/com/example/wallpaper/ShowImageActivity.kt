package com.example.wallpaper

import android.annotation.SuppressLint
import android.app.WallpaperManager
import android.content.Intent
import android.graphics.Bitmap.CompressFormat
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.graphics.drawable.toBitmap
import com.example.wallpaper.databinding.ActivityShowImageBinding
import com.example.wallpaper.databinding.SheetBottomBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class ShowImageActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy { ActivityShowImageBinding.inflate(layoutInflater) }
    private var imageId = R.drawable.image_3
    private var btnLikeStateChecked = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)
        imageId = intent.getIntExtra("id", R.drawable.image_3)
        binding.image.setImageResource(imageId)



        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )


        binding.btnBack.setOnClickListener(this)
        binding.btnDownload.setOnClickListener(this)
        binding.btnShare.setOnClickListener(this)
        binding.btnInfo.setOnClickListener(this)
        binding.btnSetImg.setOnClickListener(this)
        binding.btnEdit.setOnClickListener(this)
        binding.btnLike.setOnClickListener(this)
    }

    @SuppressLint("ResourceType")
    override fun onClick(p0: View?) {
        when (p0?.id) {
            binding.btnBack.id -> {
                finish()
            }
            binding.btnShare.id -> {
                shareImage(binding.image)
            }
            binding.btnInfo.id -> {
                val bottomSheetDialog = BottomSheetDialog(this, R.style.AppBottomSheetDialogTheme)
                val bottomSheetBinding = SheetBottomBinding.inflate(layoutInflater)
                bottomSheetBinding.tvWebLink.underline()
                bottomSheetDialog.setContentView(bottomSheetBinding.root)
                bottomSheetDialog.show()
            }
            binding.btnDownload.id -> {
                saveToGallery(binding.image)
            }
            binding.btnSetImg.id -> {
                try {
                    WallpaperManager.getInstance(applicationContext).setResource(imageId)
                    Toast.makeText(this, "Wallpaper has been set", Toast.LENGTH_SHORT).show()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            binding.btnEdit.id -> {
                Toast.makeText(this, "This function is unavailable", Toast.LENGTH_SHORT).show()
            }
            binding.btnLike.id -> {
                btnLikeStateChecked = if (!btnLikeStateChecked) {
                    binding.btnLike.setImageResource(R.drawable.menu_liked)
                    true
                } else {
                    binding.btnLike.setImageResource(R.drawable.bottom_icon_like)
                    false
                }
            }

        }
    }

    private fun saveToGallery(imageView: ImageView) {

        MediaStore.Images.Media.insertImage(
            contentResolver,
            imageView.drawable.toBitmap(),
            "yourTitle",
            "yourDescription"
        )
        Toast.makeText(this, "Image saved to the gallery", Toast.LENGTH_SHORT).show()
    }


    private fun shareImage(content: ImageView) {
        val drawable: Drawable = content.drawable
        val bitmap = (drawable as BitmapDrawable).bitmap

        try {
            val file = File(
                applicationContext.externalCacheDir,
                File.separator + "image that you wants to share"
            )
            val fOut = FileOutputStream(file)
            bitmap.compress(CompressFormat.JPEG, 100, fOut)
            fOut.flush()
            fOut.close()
            file.setReadable(false, false)
            val intent = Intent(Intent.ACTION_SEND)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            val photoURI = FileProvider.getUriForFile(
                applicationContext,
                BuildConfig.APPLICATION_ID + ".provider",
                file
            )
            intent.putExtra(Intent.EXTRA_STREAM, photoURI)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            intent.type = "image/png"
            startActivity(Intent.createChooser(intent, "Share image via"))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun TextView.underline() {
        paintFlags = paintFlags or Paint.UNDERLINE_TEXT_FLAG
    }


}