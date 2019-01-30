package ru.bww.app.testtask.model.api_controller;

import android.util.Log;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.bww.app.testtask.BuildConfig

/**
 * Created by bobkov-vv on 23.01.2019.
 */

public class TaxseeAPIController {

    companion object {
        val LOG_TAG = "TaxseeAPIController"
    }

    fun getAPI() : TaxseeAPIInterface{

        val gson = GsonBuilder()
                .setLenient()
                .create()
        val builder = OkHttpClient.Builder()
            val interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger() {
                fun log(message: String) {
                    Log.d(TaxseeAPIController.LOG_TAG, message);
                }
            });
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);

        val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(builder.build())
                .build();

        return retrofit.create(TaxseeAPIInterface::class.java)
    }
}
