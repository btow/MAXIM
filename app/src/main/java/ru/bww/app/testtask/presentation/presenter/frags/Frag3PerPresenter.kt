package ru.bww.app.testtask.presentation.presenter.frags

import android.graphics.BitmapFactory
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import okhttp3.ResponseBody
import ru.bww.app.testtask.ThisApp
import ru.bww.app.testtask.model.api_controller.AsyncTasksController
import ru.bww.app.testtask.presentation.view.frags.Frag3PerView
import ru.bww.app.testtask.ui.activs.MainActivity

@InjectViewState
class Frag3PerPresenter : MvpPresenter<Frag3PerView>(), AsyncTasksController.EmployeePhoto.IResult {

    var hasResponse = false
    lateinit var response: ResponseBody

    fun employeePhoto(emplId: String?) {
        if (!hasResponse){
            viewState.showProgressBar()
            AsyncTasksController.EmployeePhoto(this, emplId).execute(ThisApp.INSTANCE.getLoginPassword())
        }
    }

    override fun onSuccess(response: ResponseBody) {
        this.response = response
        hasResponse = true
        viewState.closeProgressBsr()
        viewState.setUpImageBitmap(BitmapFactory.decodeStream(response.byteStream()))
    }

    override fun onError(errorMsg: String) {
        viewState.showToast(errorMsg)
    }

    lateinit var emplId: String

    fun setAndGetEmplId(emplId: String?): String {
        when{
            emplId != null -> {
                this.emplId = emplId
                return emplId
            }
            else -> return this.emplId
        }
    }
}
