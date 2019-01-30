package ru.bww.app.testtask.presentation.view.frags

import android.graphics.Bitmap
import com.arellomobile.mvp.MvpView

interface Frag3PerView : MvpView {
    fun showProgressBar()
    fun closeProgressBsr()
    fun setUpImageBitmap(decodeStream: Bitmap?)
    fun showToast(errorMsg: String)

}