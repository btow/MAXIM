package ru.bww.app.testtask.presentation.view.frags

import android.graphics.Bitmap
import android.os.Bundle
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.*

interface Frag3PerView : MvpView {
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun closeProgressBsr()
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showProgressBar()
    fun setUpImageBitmap(decodeStream: Bitmap?)
    fun showToast(errorMsg: String)
    fun setViewsValues(arguments: Bundle)

}
