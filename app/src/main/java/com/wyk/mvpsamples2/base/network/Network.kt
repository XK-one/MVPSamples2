package com.wyk.mvpsamples2.base.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.RuntimeException
import java.util.concurrent.TimeUnit

class Network {

    companion object {

        val BASE_URL = "http://ip.taobao.com/"
        val mGsonConverter =  GsonConverterFactory.create()
        val mRxRetrofit = RxJava2CallAdapterFactory.create()
        val mOkHttpClient = OkHttpClient().newBuilder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(2 * 10, TimeUnit.SECONDS)
                .readTimeout(2 * 10, TimeUnit.SECONDS)
                .build()
        lateinit var mBaseApi: BaseApiService

        fun buildRetrofit() = Retrofit.Builder()
                                        .baseUrl(BASE_URL)
                                        .addConverterFactory(mGsonConverter)
                                        .addCallAdapterFactory(mRxRetrofit)
                                        .client(mOkHttpClient)
                                        .build()
        fun <T> create(service: Class<T>): T{
            if(service == null) throw RuntimeException("Api service is null!")
            return buildRetrofit().create(service)
        }
        fun createBaseApi() = create(BaseApiService::class.java)
        fun getBaseApi() = mBaseApi?: createBaseApi().also { mBaseApi = it }

    }
}