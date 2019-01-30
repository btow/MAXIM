package ru.bww.app.testtask.presentation.presenter.frags

import android.os.AsyncTask
import android.view.View
import android.widget.EditText
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import kotlinx.android.synthetic.main.frag1_log.*
import ru.bww.app.testtask.ThisApp
import ru.bww.app.testtask.model.api_controller.AsyncTasksController
import ru.bww.app.testtask.presentation.view.frags.Frag1LogView
import ru.bww.app.testtask.ui.frags.Frag1Log

@InjectViewState
class Frag1LogPresenter : MvpPresenter<Frag1LogView>() {

    fun mbtnInputOnClick(frag1Log: Frag1Log, etLogin: EditText?, etPassw: EditText?): View.OnClickListener? {
        return View.OnClickListener {
//            if (valid(etLogin, etPassw)){
            AsyncTasksController.LoginToServer(frag1Log).execute(
                etLogin?.text.toString(), etPassw?.text.toString())
//            }
        }
    }

    private fun valid(etLogin: EditText?, etPassw: EditText?): Boolean {
        when{
            etLogin == null || etPassw == null ||
                    etLogin.text.length == 0 || etPassw.text.length == 0 -> return false
            else -> {
                for (ch in etLogin.text) if(!chckChar(ch)) return false
                for (ch in etPassw.text) if(!chckChar(ch)) return false
                return true
            }
        }
    }

    private fun chckChar(ch: Char) = ch in 'A'..'Z' || ch in 'a'..'z'

}
