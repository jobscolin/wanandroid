package com.jobscolin.wanandroid.util

import com.jobscolin.wanandroid.bean.BaseResult


object ResultStatusUtil {
    fun handleResult(baseResult: BaseResult<*>): Boolean =
            when (baseResult.status) {
                -1 -> {
                    ToastUtils.show(baseResult.msg!!)
                    false
                }
                1 -> true
                else -> {
                    ToastUtils.show(baseResult.msg!!)
                    false
                }
            }


}
