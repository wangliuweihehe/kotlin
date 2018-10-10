package com.hjm.admin.mykotlin.views.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.hjm.admin.mykotlin.R
import com.hjm.admin.mykotlin.views.chAllDisplayImage
import com.hjm.admin.mykotlin.views.networkbusiness.ReviewItem
import com.zhy.autolayout.utils.AutoUtils
import kotlinx.android.synthetic.main.item_findfunny_review.view.*
import org.w3c.dom.Text

/**
 * Created by admin on 2017/11/5.
 */
class FindFunnyReviewAdapter(val ctx: Context) : RecyclerView.Adapter<FindFunnyReviewAdapter.ViewHolder>() {
    var m_listReviewData: MutableList<ReviewItem>? = null

    fun insertAllReviews(data: MutableList<ReviewItem>) {
        m_listReviewData = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = m_listReviewData?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        m_listReviewData?.let {
            holder?.apply {
                val objResult = m_listReviewData?.get(position)
                funny_review_title?.text = "${objResult?.title}"
                funny_review_summary?.text = objResult?.summary?.trim()
                funny_review_movie_cover?.chAllDisplayImage(ctx, objResult?.relatedObj?.image)
                funny_review_moview_nameyear?.text = "${objResult?.relatedObj?.title} (${objResult?.relatedObj?.year})"
                funny_review_person_head?.chAllDisplayImage(ctx, objResult?.userImage)
                funny_review_person_name?.text = objResult?.nickname
                funny_review_person_rating?.text = "评分: ${objResult?.rating}分"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.item_findfunny_review, parent, false)
        return ViewHolder(view)
    }


    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        init {
            AutoUtils.autoSize(itemView)
        }

        val funny_review_title = itemView?.findViewById<TextView>(R.id.funny_review_title)
        val funny_review_summary = itemView?.findViewById<TextView>(R.id.funny_review_summary)
        val funny_review_movie_cover = itemView?.findViewById<ImageView>(R.id.funny_review_movie_cover)
        val funny_review_moview_nameyear = itemView?.findViewById<TextView>(R.id.funny_review_movie_nameyear)
        val funny_review_person_head = itemView?.findViewById<ImageView>(R.id.funny_review_person_head)
        val funny_review_person_name = itemView?.findViewById<TextView>(R.id.funny__review_person_name)
        val funny_review_person_rating = itemView?.findViewById<TextView>(R.id.funny_review_person_rating)
    }
}