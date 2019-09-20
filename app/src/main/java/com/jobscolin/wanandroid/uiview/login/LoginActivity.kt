package com.jobscolin.wanandroid.uiview.login

import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jobscolin.wanandroid.BR
import com.jobscolin.wanandroid.R
import com.jobscolin.wanandroid.base.activity.BaseActivity
import com.jobscolin.wanandroid.base.viewmodel.BaseViewModelFactory
import com.jobscolin.wanandroid.common.Constant
import com.jobscolin.wanandroid.databinding.ActivityLoginBinding
import com.jobscolin.wanandroid.uiview.main.MainActivity
import com.jobscolin.wanandroid.util.SharedPreferencesUtil


class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {
    /**
     * 获取viewmodel的variableId 用于绑定databinding
     * @return Int
     */
    override fun getVariableId(): Int {
        return BR.viewModel
    }


    override fun initViewModel(): LoginViewModel {
        return ViewModelProvider(this, BaseViewModelFactory.getInstance(application, LoginRepository(javaClass)))
                .get(LoginViewModel::class.java)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun initView() {
        viewModel.initTitle()
        if (SharedPreferencesUtil.getInstance().getBoolean(Constant.IS_LOGIN)) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }


    override fun initObserveEvent() {
        super.initObserveEvent()
        viewModel.loginInfo.observe(this, Observer {
            if (it!=null) {
                viewModel.saveLoginInfo()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        })
    }



}
