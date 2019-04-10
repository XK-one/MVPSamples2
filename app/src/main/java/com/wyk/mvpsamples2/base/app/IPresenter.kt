package com.wyk.mvpsamples2.base.app


interface IPresenter<T>{
    fun start()
    //fun attachView(view: IBaseView)
    fun attachView(view: T)
    fun detachView(isDetach: Boolean)

}