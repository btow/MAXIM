package ru.bww.app.testtask.ui.frags

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.frag1_log.*
import ru.bww.app.testtask.R
import ru.bww.app.testtask.ThisApp
import ru.bww.app.testtask.presentation.presenter.frags.Frag1LogPresenter
import ru.bww.app.testtask.presentation.view.frags.Frag1LogView
import ru.bww.app.testtask.ui.activs.MainActivity
import ru.bww.app.testtask.ui.dialogs.PdLoading


class Frag1Log : MvpAppCompatFragment(), Frag1LogView {

    @InjectPresenter
    lateinit var thisPresenter : Frag1LogPresenter
    lateinit var pdLoading : PdLoading

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        ThisApp.INSTANCE.curFragmentName = this.javaClass.simpleName
        return inflater.inflate(R.layout.frag1_log, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mbtnInput.setOnClickListener(thisPresenter.mbtnInputOnClick(etLogin, etPassw))
    }

    override fun showFragment(fragName: String) {
        (activity as MainActivity).showAnotherFragment(fragName, true)
    }

    override fun showProgressBar() {
        pdLoading = PdLoading(context!!)
        pdLoading.openDialog()
    }

    override fun closeProgressBar() {
        pdLoading.closeDialog()
    }

    override fun onError(errorMsg: String) {
        tvMsg.text = "${errorMsg}\n${tvMsg.text}"
    }
}
