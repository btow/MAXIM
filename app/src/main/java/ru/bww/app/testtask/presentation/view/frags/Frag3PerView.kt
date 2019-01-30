package ru.bww.app.testtask.presentation.view.frags

import android.graphics.Bitmap
import android.os.Bundle
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.*

@StateStrategyType(OneExecutionStateStrategy::class)
interface Frag3PerView : MvpView {
    fun showProgressBar()
    fun closeProgressBsr()
    fun setUpImageBitmap(decodeStream: Bitmap?)
    fun showToast(errorMsg: String)
    fun setViewsValues(arguments: Bundle)

}
