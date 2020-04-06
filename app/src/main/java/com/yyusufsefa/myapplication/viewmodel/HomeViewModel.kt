package com.yyusufsefa.myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.yyusufsefa.myapplication.db.dao.ProjectDao
import com.yyusufsefa.myapplication.model.HeadLines
import com.yyusufsefa.myapplication.service.ApiInterface
import com.yyusufsefa.myapplication.service.ApiService
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class HomeViewModel : BaseViewModel() {

    lateinit var projectDao: ProjectDao

    init {
        refreshData()
    }

    fun refreshData() {

        getDataFromAPI()

    }

    private fun getDataFromAPI() {
        // You may create repository layer and send request to there
        ApiService.getClient()
            .create(ApiInterface::class.java)
            .getHead()
            .enqueue(object : retrofit2.Callback<HeadLines> {
                override fun onFailure(call: Call<HeadLines>, t: Throwable) {
                    Log.e("onFailure", t.localizedMessage ?: "Empty")
                }

                override fun onResponse(call: Call<HeadLines>, response: Response<HeadLines>) {
                    /**
                     * I used viewModelScope because [insertArticles] is suspend function.
                     * I strongly recommend look at coroutines
                     * @link https://medium.com/@celiktemha/coroutineler-coroutineler-neeeyymis-bu-corotineler-kotlin-51abe1021533
                     *
                     */
                    viewModelScope.launch {
                        projectDao.insertArticles(response.body()?.articles)
                    }
                }
            })
    }
}