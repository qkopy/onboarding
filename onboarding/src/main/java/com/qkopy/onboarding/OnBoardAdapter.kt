package com.qkopy.onboarding

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.onboard_item.view.*
import java.util.*

internal class OnBoardAdapter(private val mContext: Context, items: ArrayList<OnBoardItem>) : PagerAdapter() {
    var onBoardItems = ArrayList<OnBoardItem>()


    init {
        this.onBoardItems = items
    }

    override fun getCount(): Int {
        return onBoardItems.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView = LayoutInflater.from(mContext).inflate(R.layout.onboard_item, container, false)

        val item = onBoardItems[position]

        itemView.imageViewOnBoard.setImageResource(item.image)
        itemView.textViewHeader.text = item.title
        itemView.textViewDescription.text = item.description

        container.addView(itemView)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ConstraintLayout)
    }

}