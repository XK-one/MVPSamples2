package com.wyk.mvpsamples2.login.presenter

import com.wyk.mvpsamples2.base.app.impl.BasePresenter
import com.wyk.mvpsamples2.login.LoginContract
import com.wyk.mvpsamples2.login.model.LoginModel
import com.wyk.mvpsamples2.login.view.LoginActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginPresenter: BasePresenter(), LoginContract.Presenter{

    var mModel: LoginModel
    init {
        mModel = LoginModel()
    }
    override fun toLogin(map: Map<String,String>) {
        if(!isAttachView()){
            return
        }
        //getCurrView().showLoading()
        getCurrView().showLoading()
        mDisable = mModel.getResult(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    getCurrView().dismissLoading()
                    getCurrView().showToast(it.toString())
                },{
                    getCurrView().dismissLoading()
                    getCurrView().showToast(it.toString())
                })
    }

    fun getCurrView() = super.getView() as LoginActivity

    override fun start() {}
}