package com.hilbing.news.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.hilbing.news.R
import com.hilbing.news.databinding.ActivityMainBinding
import com.hilbing.news.presentation.adapter.NewsAdapter
import com.hilbing.news.presentation.viewmodel.NewsViewModel
import com.hilbing.news.presentation.viewmodel.NewsViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var factory : NewsViewModelFactory
    @Inject
    lateinit var newsAdapter : NewsAdapter
    lateinit var viewModel : NewsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bnv.setupWithNavController(
           navController
        )
        viewModel = ViewModelProvider(this, factory).get(NewsViewModel::class.java)
    }
}