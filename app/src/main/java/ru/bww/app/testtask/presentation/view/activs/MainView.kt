package ru.bww.app.testtask.presentation.view.activs

import com.arellomobile.mvp.MvpView

interface MainView : MvpView {
    fun showToast(message: String)
    fun logOut()
    fun showFragment(name : String)
}
