package com.foxxx.vkcliencompose.presentation.main

sealed class AuthState {

    object Authorized : AuthState()

    object NotAuthorized : AuthState()

    object Initial : AuthState()
}