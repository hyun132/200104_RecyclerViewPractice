package com.example.a200104_recyclerviewpractice.utils

import android.accessibilityservice.GestureDescription
import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.annotation.UiThread
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.util.*

class ConnetServer{

    interface JsonResponseHandler{
        fun onResponse(json:JSONObject)
    }

    companion object{

        val BASE_URL = "http://openapi.seoul.go.kr:8088/4279637352726c61313035776d4c5264/json/womanSafeAreaInfo"

        fun getRequestSafeArea(context: Context ,handler: JsonResponseHandler){

            var client = OkHttpClient()

            var urlBuilder = HttpUrl.parse("$BASE_URL/1/40")!!.newBuilder()
            var url=urlBuilder.build().toString()

//            요청을 만들고
            var request = Request.Builder().url(url).build()
//            요청을 보낸다.
            client.newCall(request).enqueue(object :Callback{
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }

                override fun onResponse(call: Call, response: Response) {
                    var body = response.body()!!.string()
                    var json = JSONObject(body)
                    handler.onResponse(json)
                }

            })
        }
        }

}