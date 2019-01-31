package ru.bww.app.testtask.presentation.presenter.activs

import android.view.MenuItem
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.bww.app.testtask.BuildConfig
import ru.bww.app.testtask.R
import ru.bww.app.testtask.ThisApp
import ru.bww.app.testtask.presentation.view.activs.MainView

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {

    fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menuLogOut) {
            ThisApp.INSTANCE.preferences.edit().remove(BuildConfig.LOGIN).apply()
            ThisApp.INSTANCE.preferences.edit().remove(BuildConfig.PASSW).apply()
            viewState.showFirstFragment()
        }
        return true
    }

}
