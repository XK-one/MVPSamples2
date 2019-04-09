package com.wyk.mvpsamples2.base.network

interface BaseApi<T> {
    fun getData(maps: Map<String,String>): T?
}