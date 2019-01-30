package ru.bww.app.testtask.model.api_controller

import android.util.Log
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import ru.bww.app.testtask.model.response_json.ResponseJSONHello

class TaxseeAPIControllerTest {

    val LOG_TAG = "TaxseeAPIControllerTest"
    lateinit var apiController: TaxseeAPIController
    lateinit var loginAndPassword: Pair<String, String>

    @Before
    fun setUp(){
        apiController = TaxseeAPIController()
        loginAndPassword  = Pair("test_user", "test_pass")
    }

    @Test
    fun getAPIgetSuccess() {
        Log.i(LOG_TAG, "Run getAPIgetSuccess()")
        val testCases
                = arrayListOf<Pair<Pair<String, String>, ResponseJSONHello>>()
        //Case 0
        testCases.add(
            Pair(loginAndPassword
                , ResponseJSONHello(null, true))
        )
        //Case 1
        testCases.add(
            Pair(Pair("test_user", "test")
                , ResponseJSONHello("Неправильное имя пользователя или пароль.", false))
        )
        var testCaseNumber = 0
        for (testCase in testCases){
            val response = apiController
                .getAPI().getSuccess(testCase.first.first, testCase.first.second).execute()
            Log.i(LOG_TAG, "The recponse is ${response}")
            assertNotNull(response)
            Log.i(LOG_TAG, "The expected response code: 200\n" +
                    "The actual response code:  ${response.code()}")
            assertEquals(200, response.code())
            Log.i(LOG_TAG, "The expected response body: ${testCase.second}\n" +
                    "The actual response code:  ${response.body()!!}")
            assertEquals("The test case ${testCaseNumber++}"
                , testCase.second.toString(), response.body()!!.toString())
        }
    }

    @Test
    fun getIPIgetStructure() {
        Log.i(LOG_TAG, "Run getIPIgetStructure()")
        val testCases
                = arrayListOf<Pair<Pair<String, String>, Int>>()
        //Case 0
        testCases.add(
            Pair(loginAndPassword, 200)
        )
        var testCaseNumber = 0
        for (testCase in testCases){
            val response = apiController
                .getAPI().getStructure(testCase.first.first, testCase.first.second).execute()
            Log.i(LOG_TAG, "The recponse is ${response}")
            assertNotNull(response)
            Log.i(LOG_TAG, "The expected response code: ${testCase.second}\n" +
                    "The actual response code:  ${response.code()}")
            assertEquals("The test case ${testCaseNumber++}"
                , testCase.second, response.code())
        }
    }

    @Test
    fun getIPIgetWPhoto() {
        Log.i(LOG_TAG, "Run getIPIgetWPhoto()")
        val testCases
                = arrayListOf<Pair<Int, Int>>()
        //Case 0
        testCases.add(
            Pair(4647, 200)
        )
        //Case 1
        testCases.add(
            Pair(7935, 200)
        )
        var testCaseNumber = 0
        for (testCase in testCases){
            val response = apiController
                .getAPI().getWPhoto(
                    loginAndPassword.first
                    , loginAndPassword.second
                    , testCase.second.toString()).execute()
            Log.i(LOG_TAG, "The recponse is ${response}")
            assertNotNull(response)
            Log.i(LOG_TAG, "The expected response code: ${testCase.second}\n" +
                    "The actual response code:  ${response.code()}")
            assertEquals("The test case ${testCaseNumber++}"
                , testCase.second, response.code())
        }
    }
}