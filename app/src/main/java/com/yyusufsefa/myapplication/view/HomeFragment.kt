package com.yyusufsefa.myapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.yyusufsefa.myapplication.adapter.MyAdapter
import com.yyusufsefa.myapplication.databinding.FragmentHomeBinding
import com.yyusufsefa.myapplication.db.ProjectDatabase
import com.yyusufsefa.myapplication.util.hide
import com.yyusufsefa.myapplication.util.show
import com.yyusufsefa.myapplication.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var adapter: MyAdapter

    private lateinit var binding: FragmentHomeBinding

    private val projectDao by lazy {
        ProjectDatabase.getInstance(requireContext()).projectDao()
    }

    private val viewModel by lazy {
        ViewModelProviders.of(this)
            .get(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel.projectDao = projectDao

        //Try to use databinding everyscreen
        binding = FragmentHomeBinding.inflate(layoutInflater, null, false)

        // Click event with higher-order fun. looking more pretty :),
        adapter = MyAdapter(arrayListOf()) { articles ->
            val action =
                HomeFragmentDirections.actionHomeFragmentToDetailFragment(articles)
            findNavController().navigate(action)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter

        binding.swipeRefresh.setOnRefreshListener {
            // i think , hiding recycler view is not good practice ? :(
//            binding.recyclerView.hide()
            viewModel.refreshData()
        }
        observeLiveData()
    }

    private fun observeLiveData() {
        projectDao.getAllArticles().observe(viewLifecycleOwner, Observer { articleList ->
            //if you set false to below it means when data is load than refreshing state will stopped
            binding.swipeRefresh.isRefreshing = false
            articleList?.let { articles ->
//                binding.recyclerView.show()
                adapter.updateList(articles)
            }
        })
    }
}
