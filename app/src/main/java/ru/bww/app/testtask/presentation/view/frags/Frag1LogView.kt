package ru.bww.app.testtask.presentation.view.frags

import com.arellomobile.mvp.MvpView

interface Frag1LogView : MvpView {
    fun showProgressBar()
    fun showFragment(fragName: String)
    fun onError(errorMsg: String)
    fun closeProgressBar()

}
