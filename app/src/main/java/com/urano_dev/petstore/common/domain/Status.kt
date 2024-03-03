package com.urano_dev.petstore.common.domain

enum class Status {
    NOT_STARTED,
    LOADING,
    FINISHED,
    ERROR,
}

fun Status.isLoading():Boolean{
    return this==Status.LOADING
}