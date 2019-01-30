package ru.bww.app.testtask.presentation.presenter.frags

import android.graphics.BitmapFactory
import android.os.Bundle
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import okhttp3.ResponseBody
import ru.bww.app.testtask.ThisApp
import ru.bww.app.testtask.model.api_controller.AsyncTasksController
import ru.bww.app.testtask.presentation.view.frags.Frag3PerView

@InjectViewState
class Frag3PerPresenter : MvpPresenter<Frag3PerView>(), AsyncTasksController.EmployeePhoto.IResult {

    var hasResponse = false
    lateinit var response: ResponseBody

    fun employeePhoto() {
        if (!hasResponse){
            viewState.showProgressBar()
            AsyncTasksController.EmployeePhoto(this, arguments?.getString("ID"))
                .execute(ThisApp.INSTANCE.getLoginPassword())
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

    var arguments: Bundle? = null

    fun setDataset(arguments: Bundle?) {
        when{
            arguments != null -> {
                this.arguments = arguments
                viewState.setViewsValues(arguments)
            }
            else -> {
                viewState.setViewsValues(this.arguments!!)
            }
        }
    }
}
