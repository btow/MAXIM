package ru.bww.app.testtask.ui.activs

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.WindowManager
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import org.jetbrains.anko.toast
import ru.bww.app.testtask.BuildConfig.*
import ru.bww.app.testtask.R
import ru.bww.app.testtask.ThisApp
import ru.bww.app.testtask.presentation.presenter.activs.MainPresenter
import ru.bww.app.testtask.presentation.view.activs.MainView
import ru.bww.app.testtask.ui.frags.Frag1Log

class MainActivity : MvpAppCompatActivity(), MainView {

    private val LOG_TAG = "===>"

    @InjectPresenter
    lateinit var mainPresenter : MainPresenter

    lateinit var INSTANCE: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.INSTANCE = this
        setContentView(R.layout.activity_main)

        if(ThisApp.INSTANCE.curFragmentName == "")
            ThisApp.INSTANCE.curFragmentName = this.javaClass.simpleName

        when(ThisApp.INSTANCE.curFragmentName){
            this.javaClass.simpleName -> {
                window.setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE
                            or WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
                )
                supportFragmentManager.beginTransaction()
                    .replace(R.id.rlContent, Frag1Log())
                    .commit()
            }
            else -> {
                window.setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
                            or WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING
                )
                showFragment(ThisApp.INSTANCE.curFragmentName)
            }
        }
    }

    override fun showToast(message: String) {
        toast(message)
    }

    override fun logOut() {

    }

    override fun showFragment(name: String) {
        val fragment = Class.forName(
            "ru.bww.app.testtask.ui.frags.${name}")
            .newInstance() as Fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.rlContent, fragment)
            .addToBackStack(name)
            .commit()
    }

    override fun onBackPressed() {
        when(ThisApp.INSTANCE.curFragmentName){
            FRAG3_PER -> {
                supportFragmentManager.popBackStack()
            }
            else -> super.onBackPressed()
        }
    }
}
