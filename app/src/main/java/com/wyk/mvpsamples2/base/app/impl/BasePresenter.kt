package com.wyk.mvpsamples2.base.app.impl

import android.app.Activity
import android.support.v4.app.ActivityCompat
import com.wyk.mvpsamples2.base.app.IBaseView
import com.wyk.mvpsamples2.base.app.IPresenter
import com.wyk.mvpsamples2.base.app.ModelActivity
import io.reactivex.disposables.Disposable
import java.lang.ref.WeakReference

/**
 * 供Activity等类去继承，在这里封装好释放内存的操作，
 * 防止P层拥有V层的引用，导致V层无法回收
 */
abstract class BasePresenter: IPresenter {

    var mViewRef: WeakReference<IBaseView>? = null
    var mDisable: Disposable? = null

    override fun attachView(view: IBaseView) {
        mViewRef = WeakReference(view)
    }

    override fun detachView(isDetach: Boolean) {
        if(mViewRef != null){
            mViewRef!!.clear()
            mViewRef = null
        }
        /**防止Rx内存泄漏*/
        if(mDisable != null && !mDisable!!.isDisposed){
            mDisable!!.dispose()
        }
    }
    /**判断是否持有页面的引用*/
    fun isAttachView() = mViewRef != null && mViewRef!!.get() != null
    /**获取页面的引用*/
    fun getView(): IBaseView? = if(mViewRef == null) null else mViewRef!!.get()
}