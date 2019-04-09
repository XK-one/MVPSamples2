package com.wyk.mvpsamples2.base.app


interface IPresenter{
    fun start()
    fun attachView(view: IBaseView)
    fun detachView(isDetach: Boolean)

}