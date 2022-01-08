package com.ehsankolivand.githubrepositories.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ehsankolivand.githubrepositories.base.BaseActivity
import com.ehsankolivand.githubrepositories.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun setBinding(): ActivityMainBinding
    = ActivityMainBinding.inflate(layoutInflater)
}