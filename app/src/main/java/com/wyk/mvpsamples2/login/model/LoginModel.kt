package com.wyk.mvpsamples2.login.model

import com.wyk.mvpsamples2.base.app.BaseModel
import com.wyk.mvpsamples2.login.api.LoginApi
import io.reactivex.Observable

class LoginModel: BaseModel<LoginApi,Observable<UserEntity>>(){
    override fun getApi() = LoginApi()
    override fun getResult(maps: Map<String, String>) = getApi().login(maps)
}