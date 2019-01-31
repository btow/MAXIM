package ru.bww.app.testtask.presentation.view.frags

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.*

interface Frag3PerView : MvpView {
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun closeProgressBsr()
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showProgressBar()
    fun showToast(errorMsg: String)
    fun setUpImageBitmap(decodeStream: Bitmap?)
    fun setViewsValues(arguments: Bundle)
    fun startIntent(callIntent: Intent)
}
