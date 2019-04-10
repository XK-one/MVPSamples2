package com.wyk.mvpsamples2.base.app

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.wyk.mvpsamples2.ProductFlavorsTest

abstract class ModelActivity: IBaseView, Activity(){

    protected lateinit var mPresenter: IPresenter<ModelActivity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = createPresenter()
        mPresenter.attachView(this)
        Log.i("productFlavorsTest", "ModelActivity input: ${ProductFlavorsTest.input()}")
    }

    override fun onDestroy() {
        super.onDestroy()
        if(this::mPresenter.isInitialized) mPresenter.detachView(true)
    }

}