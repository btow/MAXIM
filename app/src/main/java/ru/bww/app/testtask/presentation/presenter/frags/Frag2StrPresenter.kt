package ru.bww.app.testtask.presentation.presenter.frags

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.bww.app.testtask.ThisApp
import ru.bww.app.testtask.model.api_controller.AsyncTasksController
import ru.bww.app.testtask.presentation.view.frags.Frag2StrView
import ru.bww.app.testtask.ui.frags.Frag2Str

@InjectViewState
class Frag2StrPresenter : MvpPresenter<Frag2StrView>() {

    fun companyStructure(frag2Str: Frag2Str) {
        AsyncTasksController.CompanyStructure(frag2Str).execute(ThisApp.INSTANCE.getLoginPassword())
    }

}
