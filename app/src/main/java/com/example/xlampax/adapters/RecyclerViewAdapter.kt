package com.example.xlampax.adapters

import com.example.xlampax.models.News
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.xlampax.R
import kotlinx.android.synthetic.main.item_news.view.imageView
import kotlinx.android.synthetic.main.item_news.view.tvSource
import kotlinx.android.synthetic.main.item_news.view.tvTime
import kotlinx.android.synthetic.main.item_news.view.tvTitle
import kotlinx.android.synthetic.main.item_news_top.view.*


class RecyclerViewAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerViewAdapter.PagerVH>() {
    private var newsList:List<News> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH {
        return when(viewType){
            0->PagerVH(LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false))
            else ->PagerTopVH(LayoutInflater.from(parent.context).inflate(R.layout.item_news_top, parent, false))
        }


    }

    override fun getItemViewType(position: Int): Int {
        if(newsList.get(position).top!=null){
            var isTop = newsList[position].top!!.contentEquals("1")
            if(isTop){
                return 1
            }else{
                return 0
            }
        }

        return 0
    }


    override fun getItemCount(): Int = newsList.size

    override fun onBindViewHolder(holder: PagerVH, position: Int) {
        val news = newsList[position]
        if(getItemViewType(position) == 1){
            var view = holder as PagerTopVH
            view.tvTopIdent.text = context.getString(R.string.top)
        }

        var url: String? = news.img
        if(url!=null) {
            url?.let {
                holder.imgView.visibility = View.VISIBLE
                Glide.with(context).load(url).into(holder.imgView)
            }
        }else{
            holder.imgView.visibility = View.GONE
        }
        holder.titleTextView.text = news.title
        holder.sourceTextView.text = news.click_url
        holder.timeTextView.text = news.time


    }

    fun setNewsListItems(newsList: List<News>?){
        if (newsList != null) {
            this.newsList = newsList
        }
        notifyDataSetChanged()
    }

    open class PagerVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.tvTitle
        val sourceTextView: TextView = itemView.tvSource
        val timeTextView: TextView = itemView.tvTime
        val imgView = itemView.imageView

    }

    class PagerTopVH(itemView: View) : PagerVH(itemView) {
        val tvTopIdent: TextView = itemView.tv_top_ident
    }
}