package com.jobscolin.wanandroid.uiview.login

import androidx.lifecycle.MutableLiveData
import com.jobscolin.wanandroid.base.model.BaseRepository
import com.jobscolin.wanandroid.bean.BaseResult
import com.jobscolin.wanandroid.bean.LoginInfo
import com.jobscolin.wanandroid.http.BaseObserver
import com.jobscolin.wanandroid.http.BaseRequest
import com.jobscolin.wanandroid.http.api.ResultApi
import com.jobscolin.wanandroid.util.ResultStatusUtil
import io.reactivex.disposables.Disposable

/**
 *     @author : xdl
 *     @time   : 2019/08/07
 *     @describe :
 */
open class LoginRepository(private val clazz: Class<*>) : BaseRepository(clazz) {
    private var resultApi = ResultApi()
    var data =MutableLiveData<LoginInfo>()


    /**
     * 登录
     * @param name String
     * @param pwd String
     * @return LoginInfo?
     */
    fun login(name: String, pwd: String){
        showLoading()
        val observer = BaseRequest.request(resultApi.loginApi(name, pwd), object :
                BaseObserver<BaseResult<LoginInfo>>(clazz) {
            override fun onNext(t: BaseResult<LoginInfo>) {
                super.onNext(t)
                if (ResultStatusUtil.handleResult(t)) {
                    data.value=t.data
                }
            }
        })
        addDisposable(observer as Disposable)
    }

}