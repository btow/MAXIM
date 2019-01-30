package ru.bww.app.testtask

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import ru.bww.app.testtask.model.api_controller.AsyncTasksController
import ru.bww.app.testtask.model.api_controller.TaxseeAPIController

class ThisApp: Application() {

    companion object {
        lateinit var INSTANCE : ThisApp
        lateinit var taxseeAPIController: TaxseeAPIController
        var primStart = true
    }

    lateinit var preferences : SharedPreferences
    var curFragmentName = ""

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        taxseeAPIController = TaxseeAPIController()
        preferences = getSharedPreferences(
            this::class.java.simpleName, Context.MODE_PRIVATE)
        val login = preferences.getString(BuildConfig.LOGIN, null)
        val password = preferences.getString(BuildConfig.PASSW, null)
        if((login != null) and (password != null)){
            curFragmentName = BuildConfig.FRAG2_STR
        } else {
            curFragmentName = BuildConfig.FRAG1_LOG
        }
    }

    fun saveLoginPassword(login: String, password: String) {
        this.preferences.edit().putString(BuildConfig.LOGIN, login).apply()
        this.preferences.edit().putString(BuildConfig.PASSW, password).apply()
    }

    fun getLoginPassword(): Pair<String, String> {
        return Pair(
            this.preferences.getString(BuildConfig.LOGIN, "test_user")
            , this.preferences.getString(BuildConfig.PASSW, "test_pass"))
    }

}