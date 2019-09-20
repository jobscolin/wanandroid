package com.jobscolin.wanandroid.http.api

import com.jobscolin.wanandroid.bean.BaseResult
import com.jobscolin.wanandroid.bean.LoginInfo
import com.jobscolin.wanandroid.http.RetrofitFactory
import io.reactivex.Observable
import java.util.*

/**
 * @date: 2018/7/3 13:58
 * @author: Chunjiang Mao
 * @classname: ResultApi
 * @describe: 传参api
 */

class ResultApi {
    /**
     * 登录
     *
     * @param userName
     * @param pwd
     * @return
     */
    fun loginApi(userName: String, pwd: String): Observable<BaseResult<LoginInfo>> {
        val hashMap = HashMap<String, String>()
        hashMap["user_name"] = userName
        hashMap["password"] = pwd
        return RetrofitFactory.get().create(ApiService::class.java).login(hashMap)
    }
}
