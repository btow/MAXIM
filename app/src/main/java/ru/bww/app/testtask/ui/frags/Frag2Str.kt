package ru.bww.app.testtask.ui.frags

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.frag2_str.*
import org.jetbrains.anko.support.v4.toast
import ru.bww.app.testtask.BuildConfig
import ru.bww.app.testtask.R
import ru.bww.app.testtask.ThisApp
import ru.bww.app.testtask.model.json_objects.Employee
import ru.bww.app.testtask.model.response_json.ResponseJSONStructure
import ru.bww.app.testtask.presentation.adapter.RvStructureAdapter
import ru.bww.app.testtask.presentation.presenter.frags.Frag2StrPresenter
import ru.bww.app.testtask.presentation.view.frags.Frag2StrView
import ru.bww.app.testtask.ui.dialogs.PdLoading

class Frag2Str : MvpAppCompatFragment(), Frag2StrView {

    @InjectPresenter
    lateinit var thisPresenter : Frag2StrPresenter
    lateinit var pdLoading : PdLoading

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        ThisApp.INSTANCE.curFragmentName = this.javaClass.simpleName
        return inflater.inflate(R.layout.frag2_str, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        thisPresenter.companyStructure()
    }

    override fun setUpRvStructure(response: ResponseJSONStructure) {
        rvStructure.layoutManager = LinearLayoutManager(this.context)
        rvStructure.adapter = RvStructureAdapter(this, response)
    }

    fun openEmployCard(employee: Employee) {
        val params = Bundle()
        params.putString("ID", employee.ID)
        params.putString("Name", employee.Name)
        params.putString("Title", employee.Title)
        params.putString("Phone", employee.Phone)
        params.putString("Email", employee.Email)
        val frag = Frag3Per()
            frag.arguments = params
        activity!!.supportFragmentManager.beginTransaction()
            .replace(R.id.rlContent, frag, BuildConfig.FRAG3_PER)
            .addToBackStack(BuildConfig.FRAG3_PER)
            .commit()
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
