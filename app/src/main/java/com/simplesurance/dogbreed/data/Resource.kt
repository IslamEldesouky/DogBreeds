package com.simplesurance.dogbreed.data

sealed class Resource<out T> {
    object Empty : Resource<Nothing>() //empty state returns nothing

    object Loading : Resource<Nothing>() //loading state returns nothing

    data class Success<out T>(val value: T) : Resource<T>() // success state returns an object/Model

    class Failure(val exception: Throwable) : Resource<Nothing>() // failure state takes exception

    companion object {

        fun <T> empty(): Resource<T> = Empty

        fun <T> success(data: T): Resource<T> = Success(data)
    }
}
