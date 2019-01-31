package ru.bww.app.testtask.presentation.view.frags

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.bww.app.testtask.model.response_json.ResponseJSONStructure

interface Frag2StrView : MvpView {
    fun setUpRvStructure(response: ResponseJSONStructure)
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun closeProgressBsr()
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showProgressBar()
    fun showToast(errorMsg: String)
}
