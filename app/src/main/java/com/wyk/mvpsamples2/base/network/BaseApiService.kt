package com.wyk.mvpsamples2.base.network

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface BaseApiService {

    @GET
    fun <T> getData(@QueryMap map: Map<String, Object>): Observable<T>
}