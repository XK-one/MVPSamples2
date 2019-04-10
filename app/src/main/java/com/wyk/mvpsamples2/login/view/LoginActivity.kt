package com.wyk.mvpsamples2.login.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import com.wyk.mvpsamples2.R
import com.wyk.mvpsamples2.base.app.IBaseView
import com.wyk.mvpsamples2.base.app.IPresenter
import com.wyk.mvpsamples2.base.app.ModelActivity
import com.wyk.mvpsamples2.login.LoginContract
import com.wyk.mvpsamples2.login.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity: ModelActivity(),LoginContract.View{

    override fun <T : IPresenter<out IBaseView>> createPresenter(): T {
        return LoginPresenter() as T
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        password.setOnEditorActionListener { v, actionId, event ->
            if(actionId == R.id.login && actionId == EditorInfo.IME_NULL){
                login()
                true
            }
            false
        }
        email_sign_in_button.setOnClickListener {
            login()
        }
    }

    override fun login() {
        password.error = null
        email.error = null
        val emailText = email.text.toString()
        val psw = password.text.toString()

        var cancel = false
        var focusView: View? = null

        if(!TextUtils.isEmpty(psw) && !(psw.length > 4)){
            password.error = getString(R.string.error_invalid_password)
            cancel = true
            focusView = password
        }
        if(TextUtils.isEmpty(emailText)){
            email.error = getString(R.string.error_field_required)
            cancel = true
            focusView = email
        }else if(!emailText.contains("@")){
            email.error = getString(R.string.error_invalid_email)
            cancel = true
            focusView = email
        }

        if(cancel){
            focusView!!.requestFocus()
            return
        }

        val map = mapOf(Pair("ip", "21.22.11.33"))
        (mPresenter as LoginPresenter).toLogin(map)
    }

    override fun showToast(data: String) {
        runOnUiThread { Toast.makeText(this@LoginActivity, data, Toast.LENGTH_SHORT).show() }
    }
    override fun dismissLoading() {
        showUIProgress(false)
    }
    override fun showLoading() {
        showUIProgress(true)
    }
    fun showUIProgress(isShow: Boolean){
        runOnUiThread {
            showProgress(isShow)
        }
    }
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private fun showProgress(show: Boolean) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            val shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime)

            login_form.setVisibility(if (show) View.GONE else View.VISIBLE)
            login_form.animate().setDuration(shortAnimTime.toLong()).alpha(
                    (if (show) 0 else 1).toFloat()).setListener(object : AnimatorListenerAdapter() {

                override fun onAnimationEnd(animation: Animator) {
                    login_form.setVisibility(if (show) View.GONE else View.VISIBLE)
                }
            })

            login_progress.setVisibility(if (show) View.VISIBLE else View.GONE)
            login_progress.animate().setDuration(shortAnimTime.toLong()).alpha(
                    (if (show) 1 else 0).toFloat()).setListener(object : AnimatorListenerAdapter() {

                override fun onAnimationEnd(animation: Animator) {
                    login_progress.setVisibility(if (show) View.VISIBLE else View.GONE)
                }
            })
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            login_progress.setVisibility(if (show) View.VISIBLE else View.GONE)
            login_form.setVisibility(if (show) View.GONE else View.VISIBLE)
        }
    }



}
