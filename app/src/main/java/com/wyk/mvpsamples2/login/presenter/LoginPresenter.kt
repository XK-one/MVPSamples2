package com.wyk.mvpsamples2.login.presenter

import com.wyk.mvpsamples2.base.app.impl.BasePresenter
import com.wyk.mvpsamples2.login.LoginContract
import com.wyk.mvpsamples2.login.model.LoginModel
import com.wyk.mvpsamples2.login.view.LoginActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginPresenter: BasePresenter<LoginActivity>(), LoginContract.Presenter<LoginActivity>{

    var mModel: LoginModel
    init {
        mModel = LoginModel()
    }
    override fun toLogin(map: Map<String,String>) {
        if(!isAttachView()){
            return
        }
        getView()?.showLoading()
        //getCurrView().showLoading()
        mDisable = mModel.getResult(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    getView()?.dismissLoading()
                    getView()?.showToast(it.toString())
                },{
                    getView()?.dismissLoading()
                    getView()?.showToast(it.toString())
                })
    }

    override fun start() {}
}