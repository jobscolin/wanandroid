package com.jobscolin.wanandroid.uiview.main

import androidx.lifecycle.ViewModelProvider
import com.jobscolin.wanandroid.BR
import com.jobscolin.wanandroid.R
import com.jobscolin.wanandroid.base.activity.BaseActivity
import com.jobscolin.wanandroid.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    /**
     * 获取当前ViewModel对象
     *
     * @return VM
     */
    override fun initViewModel(): MainViewModel {
        return ViewModelProvider(this).get(MainViewModel::class.java)
    }

    /**
     * 布局id
     *
     * @return Int
     */
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    /**
     * 初始化布局
     */
    override fun initView() {
        viewModel.initTitle()
        viewModel.initRecyclerView()
        viewModel.getAnimals()

    }

    /**
     * 获取ViewModel的variableId 用于绑定DataBinding
     *
     * @return Int
     */
    override fun getVariableId(): Int {
        return BR.viewModel
    }

    override fun initObserveEvent() {
        super.initObserveEvent()
        viewModel.animals.observe(this,androidx.lifecycle.Observer {
            viewModel.updateRecyclerView(it.list)
        })
    }

}
