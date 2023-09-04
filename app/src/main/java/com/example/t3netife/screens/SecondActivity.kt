package com.example.t3netife.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.t3netife.R

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val imageView = findViewById<ImageView>(R.id.imageView)
        val url = intent.getStringExtra("url")
        Glide.with(this).load(url).into(imageView)
    }
}