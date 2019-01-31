package ru.bww.app.testtask.presentation.presenter.frags

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.bww.app.testtask.ThisApp
import ru.bww.app.testtask.model.api_controller.AsyncTasksController
import ru.bww.app.testtask.model.response_json.ResponseJSONStructure
import ru.bww.app.testtask.presentation.view.frags.Frag2StrView

@InjectViewState
class Frag2StrPresenter : MvpPresenter<Frag2StrView>(), AsyncTasksController.CompanyStructure.IResult {

    private var hasResponse = false

    private lateinit var response: ResponseJSONStructure

    fun companyStructure() {
        if (!hasResponse) {
            viewState.showProgressBar()
            AsyncTasksController.CompanyStructure(this).execute(ThisApp.INSTANCE.getLoginPassword())
        }
    }

    override fun onSuccess(response: ResponseJSONStructure) {
        this.response = response
        this.hasResponse = true
        viewState.closeProgressBsr()
        viewState.setUpRvStructure(this.response)
    }

    override fun onError(errorMsg: String) {
        viewState.closeProgressBsr()
        viewState.showToast(errorMsg)
    }

}
