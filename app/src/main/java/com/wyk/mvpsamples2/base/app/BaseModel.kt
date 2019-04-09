package com.wyk.mvpsamples2.base.app

abstract class BaseModel<T,E>{
        abstract fun getApi(): T
        abstract fun getResult(maps: Map<String, String>): E
}