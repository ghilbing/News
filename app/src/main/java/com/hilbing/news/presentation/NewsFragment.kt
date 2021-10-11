package com.hilbing.news.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hilbing.news.R
import com.hilbing.news.data.util.Resource
import com.hilbing.news.databinding.FragmentNewsBinding
import com.hilbing.news.presentation.adapter.NewsAdapter
import com.hilbing.news.presentation.viewmodel.NewsViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NewsFragment : Fragment() {

    private lateinit var viewModel: NewsViewModel
    private lateinit var binding: FragmentNewsBinding
    private lateinit var newsAdapter : NewsAdapter
    private var country = "us"
    private var page = 1
    private var isScrolling = false
    private var isLoading = false
    private var isLastPage = false
    private var pages = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel
        newsAdapter = (activity as MainActivity).newsAdapter
        newsAdapter.setOnItemClickListener{
            val bundle = Bundle().apply {
                putSerializable("selected_article", it)
            }
            findNavController().navigate(R.id.action_newsFragment_to_infoFragment,bundle)
        }
        initRecyclerView()
        viewNewsList()
        setSearchView()
    }

    private fun viewNewsList() {
        viewModel.getNewsHeadlines(country,page)
        viewModel.newsHeadlines.observe(viewLifecycleOwner, {
            response->
            when(response){
                is Resource.Success->{
                    hideProgressBar()
                    response.data?.let{
                        newsAdapter.differ.submitList(it.articles.toList())
                        if(it.totalResults%20 == 0){
                            pages = it.totalResults/20
                        } else{
                           pages = it.totalResults/20+1
                        }
                        isLastPage = page == pages

                    }
                }
                is Resource.Error->{
                    hideProgressBar()
                    response.message?.let{
                        Toast.makeText(activity, "An error ocurred: $it", Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Loading->{
                    showProgressBar()
                }

            }
        })
    }

    private fun initRecyclerView() {
      //  newsAdapter = NewsAdapter()
        binding.rvNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(this@NewsFragment.onScrollListener)
        }
    }

    private fun showProgressBar(){
        isLoading = true
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar(){
        isLoading = false
        binding.progressBar.visibility = View.GONE
    }

    private val onScrollListener = object : RecyclerView.OnScrollListener(){
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = binding.rvNews.layoutManager as LinearLayoutManager
            val sizeOfTheCurrentList = layoutManager.itemCount
            val visibleItems = layoutManager.childCount
            val topPosition = layoutManager.findFirstVisibleItemPosition()
            val hasReachedToEnd = topPosition+visibleItems >= sizeOfTheCurrentList
            val shouldPaginate = !isLoading && !isLastPage && hasReachedToEnd && isScrolling
            if(shouldPaginate){
                page++
                viewModel.getNewsHeadlines(country,page)
                isScrolling = false
            }
        }
    }

    // search
    private fun setSearchView(){
        binding.svNews.setOnQueryTextListener(
            object: SearchView.OnQueryTextListener{
                override fun onQueryTextChange(newText: String?): Boolean {
                    viewModel.searchedNews(country, newText.toString(), page)
                    viewSearchedNews()
                    return false
                }

                override fun onQueryTextSubmit(query: String?): Boolean {
                    MainScope().launch {
                        delay(2000)
                        viewModel.searchedNews(country, query.toString(), page)
                        viewSearchedNews()
                    }

                    return false
                }
        })
        binding.svNews.setOnCloseListener (object : SearchView.OnCloseListener{
            override fun onClose(): Boolean {
                initRecyclerView()
                viewNewsList()
                return false
            }
        })
    }
    fun viewSearchedNews(){

        viewModel.searchedNews.observe(viewLifecycleOwner, {
                response->
            when(response){
                is Resource.Success->{
                    hideProgressBar()
                    response.data?.let{
                        newsAdapter.differ.submitList(it.articles.toList())
                        if(it.totalResults%20 == 0){
                            pages = it.totalResults/20
                        } else{
                            pages = it.totalResults/20+1
                        }
                        isLastPage = page == pages

                    }
                }
                is Resource.Error->{
                    hideProgressBar()
                    response.message?.let{
                        Toast.makeText(activity, "An error ocurred: $it", Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Loading->{
                    showProgressBar()
                }

            }
        })
    }



}