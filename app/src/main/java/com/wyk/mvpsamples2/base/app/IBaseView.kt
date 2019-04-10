package com.wyk.mvpsamples2.base.app

interface IBaseView {
    fun <T: IPresenter<out IBaseView>> createPresenter(): T

}