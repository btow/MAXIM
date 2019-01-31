package ru.bww.app.testtask.ui.frags

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.frag3_per.*
import org.jetbrains.anko.support.v4.toast
import ru.bww.app.testtask.BuildConfig
import ru.bww.app.testtask.R
import ru.bww.app.testtask.ThisApp
import ru.bww.app.testtask.presentation.presenter.frags.Frag3PerPresenter
import ru.bww.app.testtask.presentation.view.frags.Frag3PerView
import ru.bww.app.testtask.ui.dialogs.PdLoading

class Frag3Per() : MvpAppCompatFragment(), Frag3PerView {
    @InjectPresenter
    lateinit var thisPresenter : Frag3PerPresenter
    lateinit var pdLoading : PdLoading

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        ThisApp.INSTANCE.curFragmentName = BuildConfig.FRAG3_PER
        return inflater.inflate(R.layout.frag3_per, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        thisPresenter.setDataset(arguments)
        thisPresenter.employeePhoto()
    }

    override fun setViewsValues(args: Bundle) {
        tvEmployID.text = args?.getString("ID")
        tvEmployName.text = args?.getString("Name")
        tvEmployTitle.text = args?.getString("Title")
        tvEmployPhone.text = args?.getString("Phone")
        tvEmployEmail.text = args?.getString("Email")
        if(tvEmployID.text == null) tvEmployID.text = ""
        if(tvEmployName.text == null) tvEmployName.text = ""
        if(tvEmployTitle.text == null) tvEmployTitle.text = ""
        if(tvEmployPhone.text == null)
            tvEmployPhone.text = ""
        else {
            tvEmployPhone.isClickable = true
            tvEmployPhone.isFocusable = true
            tvEmployPhone.setOnClickListener(thisPresenter.tvEmployPhoneOnClickListener())
        }
        if(tvEmployEmail.text == null)
            tvEmployEmail.text = ""
        else {
            tvEmployEmail.isClickable = true
            tvEmployEmail.isFocusable = true
            tvEmployEmail.setOnClickListener(thisPresenter.tvEmployEmailOnClickListener())
        }
    }

    override fun startIntent(callIntent: Intent) {
        startActivity(callIntent)
    }

    override fun setUpImageBitmap(decodeStream: Bitmap?) {
        ivPhoto.setImageBitmap(decodeStream)
    }

    override fun showToast(errorMsg: String) {
        toast(errorMsg)
    }

    override fun showProgressBar() {
        pdLoading = PdLoading(context!!)
        pdLoading.openDialog()
    }

    override fun closeProgressBsr() {
        pdLoading.closeDialog()
    }
}
