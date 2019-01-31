package ru.bww.app.testtask.presentation.presenter.frags

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import okhttp3.ResponseBody
import ru.bww.app.testtask.R
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
        viewState.closeProgressBsr()
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

    fun tvEmployPhoneOnClickListener(): View.OnClickListener? {
        return View.OnClickListener {
            val phone = Uri.parse("tel:${(it as TextView).text}")
            val callIntent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:${(it as TextView).text}")
            }
            val activs
                    = ThisApp.INSTANCE.packageManager.queryIntentActivities(callIntent, 0)
            when{
                activs.size == 1 -> viewState.startIntent(callIntent)
                activs.size > 1 -> {
                    val title = ThisApp.INSTANCE.getString(R.string.abc_activity_chooser_view_see_all)
                    val chooser = Intent.createChooser(callIntent, title)
                    viewState.startIntent(chooser)
                }
                else -> viewState.showToast(ThisApp.INSTANCE.getString(R.string.frag3_dialer_not_installed))
            }
        }
    }

    fun tvEmployEmailOnClickListener(): View.OnClickListener? {
        return View.OnClickListener {
            val callIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:") // only email apps should handle this
                putExtra(Intent.EXTRA_EMAIL, arrayOf<String>((it as TextView).text.toString()))
            }
            val activs
                    = ThisApp.INSTANCE.packageManager.queryIntentActivities(callIntent, 0)
            when {
                activs.size == 1 -> viewState.startIntent(callIntent)
                activs.size > 1 -> {
                    val title = ThisApp.INSTANCE.getString(R.string.abc_activity_chooser_view_see_all)
                    val chooser = Intent.createChooser(callIntent, title)
                    viewState.startIntent(chooser)
                }
                else -> viewState.showToast(ThisApp.INSTANCE.getString(R.string.frag3_emailer_not_installed))
            }
        }
    }
}
