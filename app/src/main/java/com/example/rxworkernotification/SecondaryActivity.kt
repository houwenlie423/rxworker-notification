package com.example.rxworkernotification

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.rxworkernotification.databinding.ActivitySecondaryBinding


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version SecondaryActivity, v 0.1 Thu 7/6/2023 3:52 PM by Houwen Lie
 */
class SecondaryActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondaryBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}