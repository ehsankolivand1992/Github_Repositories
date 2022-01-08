package com.ehsankolivand.githubrepositories.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ehsankolivand.githubrepositories.base.BaseActivity
import com.ehsankolivand.githubrepositories.databinding.ActivityMainBinding
import com.ehsankolivand.githubrepositories.ui.adapter.MainAdapter
import com.ehsankolivand.githubrepositories.utils.Status
import com.ehsankolivand.githubrepositories.utils.hide
import com.ehsankolivand.githubrepositories.utils.show
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainAdapter = MainAdapter(this)





        mainViewModel.fetchRep().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {

                    binding.rvRepoes.show()
                    binding.emptyContainer
                    binding.rvRepoes.layoutManager = LinearLayoutManager(this)
                    it.data?.let { it1 -> mainAdapter.setRepoList(it1.items) }
                    binding.rvRepoes.adapter = mainAdapter

                }
                Status.ERROR -> {
                    binding.rvRepoes.show()

                }
                Status.LOADING -> {
                    binding.rvRepoes.hide()


                }
            }
        })

    }


    override fun setBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
}

