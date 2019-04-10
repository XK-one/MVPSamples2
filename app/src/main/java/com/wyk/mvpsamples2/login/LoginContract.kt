package com.wyk.mvpsamples2.login

import com.wyk.mvpsamples2.base.app.IBaseView
import com.wyk.mvpsamples2.base.app.IPresenter
import com.wyk.mvpsamples2.base.app.ModelActivity
import com.wyk.mvpsamples2.base.app.impl.BasePresenter
import com.wyk.mvpsamples2.login.view.LoginActivity

interface LoginContract {

    interface View: IBaseView {
        fun login()
        fun dismissLoading()
        fun showLoading()
        fun showToast(msg: String)
    }

    interface Presenter<T: IBaseView>: IPresenter<T>{
        fun toLogin(map: Map<String,String>)
    }
}