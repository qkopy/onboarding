package com.qkopy.onboarding

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_onboard.*
import java.util.*

class OnBoardActivity : AppCompatActivity() {


    private var previousPosition = 0
    private var dotsCount = 0
    private var dots = arrayOfNulls<ImageView>(3)
    private var onBoardItems = ArrayList<OnBoardItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboard)

        loadBoardings()

        viewPager.adapter = OnBoardAdapter(this, onBoardItems)
        viewPager.currentItem = 0
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {

                // Change the current position intimation
                for (i in 0 until dotsCount) {
                    dots[i]?.setImageDrawable(
                        ContextCompat.getDrawable(
                            this@OnBoardActivity,
                            R.drawable.non_selected_item_dot
                        )
                    )
                }

                dots[position]?.setImageDrawable(
                    ContextCompat.getDrawable(
                        this@OnBoardActivity,
                        R.drawable.selected_item_dot
                    )
                )

                val pos = position + 1
                if (pos == dotsCount && previousPosition == dotsCount - 1)
                    showAnimation()
                else if (pos == dotsCount - 1 && previousPosition == dotsCount)
                    hideAnimation()

                previousPosition = pos
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

        buttonGettingStarted.setOnClickListener {
            Toast.makeText(this@OnBoardActivity, "Redirect to wherever you want", Toast.LENGTH_LONG).show()
        }

        setUiPageViewController()

    }


    private fun loadBoardings() {
        var item = OnBoardItem(R.drawable.ic_what, "", "Enables you to experience the excitement of spreading your status updates to the world around")
        onBoardItems.add(item)
        item = OnBoardItem(R.drawable.ic_what, "", "Enables you to experience the excitement of spreading your status updates to the world around")
        onBoardItems.add(item)
        item = OnBoardItem(R.drawable.ic_what, "", "Enables you to experience the excitement of spreading your status updates to the world around")
        onBoardItems.add(item)
    }

    private fun setUiPageViewController() {

        dotsCount = onBoardItems.size

        for (i in 0 until dotsCount) {
            dots[i] = ImageView(this)
            dots[i]?.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.non_selected_item_dot))

            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            params.setMargins(6, 0, 6, 0)

            viewPagerCountDots.addView(dots[i], params)
        }

        dots[0]?.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.selected_item_dot))
    }


    // Button bottomUp animation
    fun showAnimation() {
        val show = AnimationUtils.loadAnimation(this, R.anim.slide_up_anim)

        buttonGettingStarted.startAnimation(show)

        show.setAnimationListener(object : Animation.AnimationListener {

            override fun onAnimationStart(animation: Animation) {
                buttonGettingStarted.setVisibility(View.VISIBLE)
            }

            override fun onAnimationRepeat(animation: Animation) {}

            override fun onAnimationEnd(animation: Animation) {

                buttonGettingStarted.clearAnimation()

            }

        })


    }

    // Button TopDown animation
    fun hideAnimation() {
        val hide = AnimationUtils.loadAnimation(this, R.anim.slide_down_anim)

        buttonGettingStarted.startAnimation(hide)

        hide.setAnimationListener(object : Animation.AnimationListener {

            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationRepeat(animation: Animation) {}

            override fun onAnimationEnd(animation: Animation) {

                buttonGettingStarted.clearAnimation()
                buttonGettingStarted.setVisibility(View.GONE)

            }

        })


    }
}
