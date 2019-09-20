package com.jobscolin.wanandroid.binding.viewadapter.imageview

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.jobscolin.wanandroid.uiview.App

/**
 *     @author : xdl
 *     @time   : 2019/08/12
 *     @describe :
 */
object ViewAdapter {
    @JvmStatic
    @BindingAdapter("android:urlSrc")
    fun setImageUrl(view: ImageView, url: String) {
        Glide.with(App.getInstance()).load(url).into(view)
    }
}