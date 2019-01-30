package ru.bww.app.testtask.model.api_controller;

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Query
import ru.bww.app.testtask.model.response_json.ResponseJSONHello
import ru.bww.app.testtask.model.response_json.ResponseJSONStructure

/**
 * Created by bobkov-vv on 23.01.2019.
 */

public interface TaxseeAPIInterface {


    @GET("Hello")
    fun getSuccess(
        @Query("login") login: String
        , @Query("password") password: String
    ): Call<ResponseJSONHello>

    @GET("GetAll")
    fun getStructure(
        @Query("login") login: String
        , @Query("password") password: String
    ): Call<ResponseJSONStructure>

    @GET("GetWPhoto")
    fun getWPhoto(
        @Query("login") login: String
        , @Query("password") password: String
        , @Query("id") id: String
    ): Call<ResponseBody>

}
