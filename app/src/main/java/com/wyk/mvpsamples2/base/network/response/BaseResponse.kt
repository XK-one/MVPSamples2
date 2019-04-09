package com.wyk.mvpsamples2.base.network.response

data class BaseResponse<T>(val status: String, val code: String, val msg: String, val data: T)