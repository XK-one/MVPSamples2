package com.wyk.mvpsamples2.login.api

import com.wyk.mvpsamples2.base.network.BaseApi
import com.wyk.mvpsamples2.base.network.Network
import com.wyk.mvpsamples2.login.model.UserEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.QueryMap

/**
 * 项目中一般接口返回的实体都是统一格式的，
 * 例如返回:{ status: "success", code: 999,msg: "成功",data: { }}
 * 可以将实体封装成BaseResponse类，这样就可以使得该LoginApi类返回的实体不会被限制为UserEntity类
 */
class LoginApi: BaseApi<Observable<UserEntity>> {

    override fun getData(maps: Map<String,String>) = null

    /**Login模块独有的逻辑*/
    fun login(maps: Map<String,String>) =
            Network.create(LoginService::class.java).login(maps)


    interface LoginService{
        /**
         * http://ip.taobao.com/service/getIpInfo.php?ip=21.22.11.33
         */
        @GET("service/getIpInfo.php")
        @Headers("Accept: application/json")
        fun login(@QueryMap maps: Map<String, String>): Observable<UserEntity>
    }


}