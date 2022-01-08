package com.ehsankolivand.githubrepositories.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.ehsankolivand.githubrepositories.base.BaseActivity
import com.ehsankolivand.githubrepositories.data_source.GithubRepository
import com.ehsankolivand.githubrepositories.data_source.models.RepoSearchResponse
import com.ehsankolivand.githubrepositories.databinding.ActivityMainBinding
import com.ehsankolivand.githubrepositories.utils.Resource
import com.ehsankolivand.githubrepositories.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val mainViewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        mainViewModel.fetchRep().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {

                }
                Status.ERROR -> {

                }
                Status.LOADING -> {

                }
            }
        })
    }

    override fun setBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
}

