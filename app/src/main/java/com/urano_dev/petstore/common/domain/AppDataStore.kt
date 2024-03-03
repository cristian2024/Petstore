package com.urano_dev.petstore.common.domain

import kotlinx.coroutines.flow.Flow

interface AppDataStore<TYPE, OUT> {
    suspend fun setValue(value: TYPE)

    suspend fun readValue(): OUT?

     suspend fun readSingleValue(): OUT?

    fun transformValue(value: TYPE?): OUT?
}