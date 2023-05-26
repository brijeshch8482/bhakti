package com.example.bhakti.Activity

import android.content.Intent
import android.graphics.Color
import android.media.AudioManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.bhakti.R
import com.example.bhakti.databinding.ActivityStartBinding
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : AppCompatActivity() {

    private lateinit var contentBinding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        contentBinding = DataBindingUtil.setContentView(this, R.layout.activity_start)

        window?.decorView?.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        window.statusBarColor = Color.TRANSPARENT

        val mediaPlayer = MediaPlayer.create(this, R.raw.om)
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
        mediaPlayer.start()

        contentBinding.imageGanesh.translationY = -1500f
        contentBinding.imageGirl.translationY = 1500f
        contentBinding.imageDiya.translationX = 1000f
        contentBinding.omGaneshayNamah.translationX = 1000f

        contentBinding.imageGanesh.animate().translationY(0f).duration = 3000
        contentBinding.imageGirl.animate().translationY(0f).duration = 3000
        contentBinding.imageDiya.animate().translationX(0f).duration = 3000
        contentBinding.omGaneshayNamah.animate().translationX(0f).duration = 3000

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            mediaPlayer.stop()
            finish()
        }, 4000)
    }
}