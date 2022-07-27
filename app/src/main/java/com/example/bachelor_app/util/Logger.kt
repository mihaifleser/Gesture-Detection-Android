package com.example.bachelor_app.util

object Logger {

    @JvmStatic
    fun info(tag: String, msg: String) {
        println("$tag $msg")
    }
}