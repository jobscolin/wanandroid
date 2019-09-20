package com.jobscolin.wanandroid.uiview.login

import android.app.Application
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.jobscolin.wanandroid.base.model.BaseRepository
import com.jobscolin.wanandroid.base.viewmodel.BaseViewModel
import com.jobscolin.wanandroid.common.Constant
import com.jobscolin.wanandroid.util.SharedPreferencesUtil
import com.jobscolin.wanandroid.util.ToastUtils

/**
 *     @author : xdl
 *     @time   : 2019/08/03
 *     @describe :
 */
class LoginViewModel(application: Application, baseRepository: BaseRepository) : BaseViewModel(application) {
    val adminInput = MutableLiveData("")
    val adminPwd = MutableLiveData("")
    private val loginRepository = baseRepository as LoginRepository
    var loginInfo = loginRepository.data




    fun onClickLogin(view: View) {
        if (TextUtils.isEmpty(adminInput.value)) {
            ToastUtils.show("请输入用户名")
            return
        }
        if (TextUtils.isEmpty(adminPwd.value)) {
            ToastUtils.show("请输入密码")
            return
        }
        loginRepository.login(adminInput.value!!, adminPwd.value!!)
    }



    fun initTitle() {
        baseViewModel!!.setTitle("登录")
    }

    fun saveLoginInfo(){
        SharedPreferencesUtil.getInstance().save(Constant.IS_LOGIN,true)
    }

    override fun onCleared() {
        super.onCleared()
        Log.e("test", "loginViewModel clear")
        loginRepository.clear()
    }

}
