package ru.bww.app.testtask.ui.activs

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.activity_main.*
import ru.bww.app.testtask.BuildConfig.*
import ru.bww.app.testtask.R
import ru.bww.app.testtask.ThisApp.Companion.INSTANCE
import ru.bww.app.testtask.presentation.presenter.activs.MainPresenter
import ru.bww.app.testtask.presentation.view.activs.MainView
import ru.bww.app.testtask.ui.frags.Frag1Rev

class MainActivity : MvpAppCompatActivity(), MainView {

    @InjectPresenter
    lateinit var mainPresenter : MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        if(INSTANCE.curFragmentName == "")
            INSTANCE.curFragmentName = this.javaClass.simpleName
        navigation.setOnNavigationItemSelectedListener(
            mainPresenter.onNavigationItemSelectedListener(supportFragmentManager))
        navigation.selectedItemId = R.id.navigation_review
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0){
            if (resultCode === Activity.RESULT_OK) {
                val contents = intent.getStringExtra("SCAN_RESULT")
                toast(contents)
                // Handle successful scan
            } else if (resultCode === Activity.RESULT_CANCELED) {
                // Handle cancel
                toast(getString(R.string.operation_canceled))
            }
        }
    }

    override fun onResume() {
        Log.i("------>", "onResume()")
        super.onResume()
        when(INSTANCE.curFragmentName){
            this.javaClass.simpleName -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.rlContent, Frag1Rev())
                    .addToBackStack(FRAG1_REV)
                    .commit()
            }
            else -> {
                val fragment = Class.forName(
                    "ru.bww.app.testtask.ui.frags.${INSTANCE.curFragmentName}")
                    .newInstance() as Fragment
                supportFragmentManager.beginTransaction()
                    .replace(R.id.rlContent, fragment)
                    .addToBackStack(INSTANCE.curFragmentName)
                    .commit()
            }
        }
    }

    override fun onPause() {
        super.onPause()
    }

    override fun toast(message: String) {
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
    }

    override fun finish() {
        finish()
    }

    override fun onBackPressed() {
        when(INSTANCE.curFragmentName){
            FRAG1_SEL, FRAG1_PAY, FRAG1_RAT -> {
                supportFragmentManager.popBackStack()
            }
            else -> super.onBackPressed()
        }
    }
}
