package ru.bww.app.testtask.model.api_controller

import android.os.AsyncTask
import android.util.Log
import okhttp3.ResponseBody
import retrofit2.Response
import ru.bww.app.testtask.R
import ru.bww.app.testtask.ThisApp
import ru.bww.app.testtask.model.response_json.ResponseJSONHello
import ru.bww.app.testtask.model.response_json.ResponseJSONStructure
import ru.bww.app.testtask.presentation.presenter.frags.Frag3PerPresenter
import ru.bww.app.testtask.ui.frags.Frag1Log
import ru.bww.app.testtask.ui.frags.Frag2Str

class AsyncTasksController {

    class LoginToServer(val frag1Log: Frag1Log) : AsyncTask<String, Void, Response<ResponseJSONHello>>(){

        val LOG_TAG = "LoginToServer ->"

        lateinit var login : String
        lateinit var password : String

        override fun doInBackground(vararg params: String?): Response<ResponseJSONHello> {
            login = params[0].toString()
            password = params[1].toString()
            return ThisApp.taxseeAPIController.getAPI()
                .getSuccess(login, password).execute()
        }

        override fun onPostExecute(result: Response<ResponseJSONHello>?) {
            super.onPostExecute(result)
            when{
                result?.code() == 200 -> {
                    when{
                        result.body()?.Success!! -> {
                            ThisApp.INSTANCE.saveLoginPassword(login, password)
                            Log.i(LOG_TAG, frag1Log.getString(R.string.frag1_atc_login_is_success))
                            frag1Log.onSuccess()
                        }
                        else -> {
                            Log.e(LOG_TAG, result.body()?.Message!!)
                            (frag1Log as Frag1Log).onError(result.body()?.Message!!)
                        }
                    }
                }
                else -> {
                    Log.e(LOG_TAG, result?.code().toString())
                    (frag1Log as Frag1Log).onError(result?.code().toString())
                }
            }
        }

        interface IResult {
            fun onSuccess()
            fun onError(errorMsg: String)
        }
    }

    class CompanyStructure(val frag2Str: Frag2Str) : AsyncTask<Pair<String, String>, Void, Response<ResponseJSONStructure>>() {

        val LOG_TAG = "CompanyStructure ->"

        override fun doInBackground(vararg params: Pair<String, String>?): Response<ResponseJSONStructure> {
            return ThisApp.taxseeAPIController.getAPI()
                .getStructure(params[0]!!.first, params[0]!!.second).execute()
        }

        override fun onPostExecute(result: Response<ResponseJSONStructure>?) {
            super.onPostExecute(result)
            when{
                result?.code() == 200 -> {
                    frag2Str.onSuccess(result.body()!!)
                }
                else -> {
                    Log.e(LOG_TAG, result?.code().toString())
                    frag2Str.onError(result?.code().toString())
                }
            }
        }

        interface IResult {
            fun onSuccess(response: ResponseJSONStructure)
            fun onError(errorMsg: String)
        }
    }

    class EmployeePhoto(val presenter: Frag3PerPresenter, val emplId: String?) : AsyncTask<Pair<String, String>, Void, Response<ResponseBody>>()  {

        val LOG_TAG = "EmployeePhoto ->"

        override fun doInBackground(vararg params: Pair<String, String>?): Response<ResponseBody> {
            return ThisApp.taxseeAPIController.getAPI()
                .getWPhoto(params[0]!!.first, params[0]!!.second, emplId!!).execute()
        }

        override fun onPostExecute(result: Response<ResponseBody>?) {
            super.onPostExecute(result)
            when{
                result?.code() == 200 -> {
                    if (result.body() != null)
                        presenter.onSuccess(result.body()!!)
                    else
                        presenter.onError("The response body is null.")
                }
                else -> {
                    if (result?.message() != null)
                        presenter.onError(result?.message()!!)
                    else
                        presenter.onError("The response status code is ${result?.code()}.")
                }
            }
        }

        interface IResult{
            fun onSuccess(response: ResponseBody)
            fun onError(errorMsg: String)
        }
    }
}
