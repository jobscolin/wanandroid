package com.jobscolin.wanandroid.uiview.main

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jobscolin.wanandroid.R
import com.jobscolin.wanandroid.base.adapter.BaseAdapter
import com.jobscolin.wanandroid.base.viewmodel.BaseViewModel
import com.jobscolin.wanandroid.bean.AnimalsBean
import com.jobscolin.wanandroid.bean.BaseResult
import com.jobscolin.wanandroid.bean.ListBean
import com.jobscolin.wanandroid.common.Constant
import com.jobscolin.wanandroid.databinding.AnimalsItemBinding
import com.jobscolin.wanandroid.rxbus.RxBus
import com.jobscolin.wanandroid.rxbus.event.DismissLoadingEvent
import com.jobscolin.wanandroid.rxbus.event.ShowLoadingEvent
import com.jobscolin.wanandroid.util.ReadLoadDataUtil
import com.jobscolin.wanandroid.util.ToastUtils

/**
 *     @author : xdl
 *     @time   : 2019/08/08
 *     @describe :
 */

class MainViewModel(application: Application) : BaseViewModel(application) {

    var animals = MutableLiveData<AnimalsBean>()
    private var animalList = ArrayList<ListBean>()


    var baseAdapter = MutableLiveData<BaseAdapter<ListBean>>()



    fun initTitle() {
        baseViewModel?.titleText?.value = "动物列表"
    }

    fun getAnimals() {
        RxBus.getInstance().post(ShowLoadingEvent(MainActivity::class.java))
        val value = ReadLoadDataUtil.readAssetFile("data.json")
        val type = object : TypeToken<BaseResult<AnimalsBean>>() {}.type
        val fromJson: BaseResult<AnimalsBean> = Gson().fromJson(value, type)
        val runnable = Runnable {
            run {
                Thread.sleep(2000)
                RxBus.getInstance().post(DismissLoadingEvent(MainActivity::class.java))
                animals.postValue(fromJson.data)
            }
        }
        Thread(runnable).start()
    }

    fun initRecyclerView() {
        var listener: BaseAdapter.OnItemClickListener = object : BaseAdapter.OnItemClickListener {
            override fun item(binding: ViewDataBinding, position: Int) {
                var bind: AnimalsItemBinding = binding as AnimalsItemBinding

                binding.root.setOnClickListener {
                    Log.e("test",position.toString())
                }
                bind.animalBtn.setOnClickListener {
                    ToastUtils.show(position.toString())
                    val map = HashMap<String, Any>()
                    map[Constant.ACTIVITY_CLAZZ] = MainActivity::class.java
                    map[Constant.ACTIVITY_BUNDLE] = Bundle()

                    baseViewModel?.activityInfo?.value=map
                }
            }
        }
        baseAdapter.value= BaseAdapter(animalList, R.layout.animals_item, listener)
    }


    fun updateRecyclerView(data: List<ListBean>) {
        animalList.addAll(data)
        baseAdapter.value?.notifyDataSetChanged()
    }

    override fun onCleared() {
        super.onCleared()
        Log.e("test","mainViewModel clear")
    }

}