package com.wyk.mvpsamples2.login.model

data class UserEntity(val code: Int, val data: String){
    override fun toString(): String {
        return "code: ${code},data: ${data}"
    }
}