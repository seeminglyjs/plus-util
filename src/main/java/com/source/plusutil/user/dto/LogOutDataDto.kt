package com.source.plusutil.user.dto

class LogOutDataDto(val userName : String, val check : Boolean, val message : String) {
    override fun toString(): String {
        return "LogOutDataDto(userName='$userName', check=$check, message='$message')"
    }
}