package ru.bww.app.testtask.presentation.presenter.frags

import android.view.View
import android.widget.EditText
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.bww.app.testtask.BuildConfig
import ru.bww.app.testtask.ThisApp
import ru.bww.app.testtask.model.api_controller.AsyncTasksController
import ru.bww.app.testtask.presentation.view.frags.Frag1LogView

@InjectViewState
class Frag1LogPresenter : MvpPresenter<Frag1LogView>(), AsyncTasksController.LoginToServer.IResult {

    fun mbtnInputOnClick(etLogin: EditText?, etPassw: EditText?): View.OnClickListener? {

        val presenter = this
        return View.OnClickListener {
            viewState.showProgressBar()
            AsyncTasksController.LoginToServer(presenter).execute(
                etLogin?.text.toString(), etPassw?.text.toString())
        }
    }

    override fun onSuccess() {
        viewState.closeProgressBar()
        viewState.showFragment(BuildConfig.FRAG2_STR)
    }

    override fun onError(errorMsg: String) {
        viewState.closeProgressBar()
        viewState.onError(errorMsg)
    }

}
