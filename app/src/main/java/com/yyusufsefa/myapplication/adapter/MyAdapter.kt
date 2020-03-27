package com.yyusufsefa.myapplication.adapter

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.yyusufsefa.myapplication.R
import com.yyusufsefa.myapplication.model.Articles
import com.yyusufsefa.myapplication.util.downloadFromUrl
import com.yyusufsefa.myapplication.util.placeholderProgressBar
import com.yyusufsefa.myapplication.view.HomeFragmentDirections
import kotlinx.android.synthetic.main.fragment_detail.view.*
import kotlinx.android.synthetic.main.items.view.*
import kotlinx.android.synthetic.main.items.view.tvSource
import kotlinx.android.synthetic.main.items.view.tvTitle

class MyAdapter(val articles: ArrayList<Articles>):RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    class ViewHolder(var view: View):RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.items,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val a=articles.get(position)
        holder.view.tvTitle.setText(a.title)
        holder.view.tvSource.setText(a.source?.name)

        var imageUrl=a.urlToImage
        var url=a.url

        holder.view.image.downloadFromUrl(articles[position].urlToImage, placeholderProgressBar(holder.view.context))

        holder.view.setOnClickListener {
            val action=HomeFragmentDirections.actionHomeFragmentToDetailFragment()
            Navigation.findNavController(it).navigate(action)
        }



    }

    fun updateList(newArticles:List<Articles>){
        articles.clear()
        articles.addAll(newArticles)
        notifyDataSetChanged()
    }
}