package cn.vi1zen.kotlinproject.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

import java.util.ArrayList

import cn.vi1zen.kotlinproject.R
import cn.vi1zen.kotlinproject.bean.DailyBean

class Adapter(private val context: Context, private val dailyBeans: ArrayList<DailyBean>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == ITEM_TYPE_NONE) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_gank_daily_info_no_image, parent, false)
            return NoImageViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_gank_daily_info, parent, false)
            return ViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == ITEM_TYPE_NONE) {
            (holder as NoImageViewHolder).tvTitle.text = dailyBeans[position].desc
            holder.tvAuthor.text = "@" + (if(dailyBeans[position].who == null) "null" else dailyBeans[position].who)
        } else {
            val viewHolder = holder as ViewHolder
            viewHolder.tvTitle.text = dailyBeans[position].desc
            viewHolder.tvAuthor.text = "@" + (if(dailyBeans[position].who == null) "null" else dailyBeans[position].who)
            Glide.with(context)
                    .load(dailyBeans[position].images!![0])
                    .placeholder(R.drawable.empty_img)
                    .error(R.drawable.icon_no_net)
                    .skipMemoryCache(false)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(viewHolder.ivDaily)
        }

    }

    override fun getItemViewType(position: Int): Int {
        return if (dailyBeans[position].images == null) ITEM_TYPE_NONE else ITEM_TYPE_HAVA
    }

    override fun getItemCount(): Int {
        return dailyBeans.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var ivDaily: ImageView
        var tvTitle: TextView
        var tvAuthor: TextView

        init {
            ivDaily = itemView.findViewById(R.id.iv_daily)
            tvTitle = itemView.findViewById(R.id.tv_title)
            tvAuthor = itemView.findViewById(R.id.tv_author)
        }
    }

    inner class NoImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvTitle: TextView
        var tvAuthor: TextView

        init {
            tvTitle = itemView.findViewById(R.id.tv_title)
            tvAuthor = itemView.findViewById(R.id.tv_author)
        }
    }

    companion object {

        private val ITEM_TYPE_NONE = 0
        private val ITEM_TYPE_HAVA = 1
    }
}