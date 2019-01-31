package ru.bww.app.testtask.ui.activs

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import org.jetbrains.anko.toast
import ru.bww.app.testtask.BuildConfig
import ru.bww.app.testtask.BuildConfig.FRAG3_PER
import ru.bww.app.testtask.R
import ru.bww.app.testtask.ThisApp
import ru.bww.app.testtask.presentation.presenter.activs.MainPresenter
import ru.bww.app.testtask.presentation.view.activs.MainView
import ru.bww.app.testtask.ui.frags.Frag1Log


class MainActivity : MvpAppCompatActivity(), MainView {

    private val LOG_TAG = "===>"

    @InjectPresenter
    lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ThisApp.INSTANCE.curFragmentName == "")
            ThisApp.INSTANCE.curFragmentName = this.javaClass.simpleName

        when (ThisApp.INSTANCE.curFragmentName) {
            this.javaClass.simpleName -> {
                showFirstFragment()
            }
            else -> {
                showAnotherFragment(ThisApp.INSTANCE.curFragmentName, false)
            }
        }
    }

    override fun showFirstFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.rlContent, Frag1Log(), BuildConfig.FRAG1_LOG)
            .commit()
    }

    override fun showAnotherFragment(name: String, newInput: Boolean) {
        window.setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
                    or WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING
        )
        if (fragmentNotOpen(name, newInput)) {
            val fragment = Class.forName(
                "ru.bww.app.testtask.ui.frags.${name}"
            )
                .newInstance() as Fragment
            supportFragmentManager.beginTransaction()
                .replace(R.id.rlContent, fragment, name)
                .addToBackStack(name)
                .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return mainPresenter.onOptionsItemSelected(item)
    }

    override fun showToast(message: String) {
        toast(message)
    }

    private fun fragmentNotOpen(name: String, newInput: Boolean): Boolean {
        return when{
            newInput -> newInput
            else -> supportFragmentManager.findFragmentByTag(name) == null
        }
    }

    override fun onBackPressed() {
        when (ThisApp.INSTANCE.curFragmentName) {
            FRAG3_PER -> {
                supportFragmentManager.popBackStack()
            }
            else -> super.onBackPressed()
        }
    }
}
