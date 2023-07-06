package com.example.rxworkernotification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rxworkernotification.databinding.ActivityMainBinding
import com.example.rxworkernotification.notification.NotificationService

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNotification.setOnClickListener {
            NotificationService.showVerificationNotification(this)
        }
    }
}