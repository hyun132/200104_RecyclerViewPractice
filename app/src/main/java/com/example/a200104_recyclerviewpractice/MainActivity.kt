package com.example.a200104_recyclerviewpractice

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a200104_recyclerviewpractice.adapters.SafeAreaAdapter
import com.example.a200104_recyclerviewpractice.datas.SafeArea
import com.example.a200104_recyclerviewpractice.utils.ConnetServer
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : BaseActivity() {

    var mList = ArrayList<SafeArea>()
    var mAdapter:SafeAreaAdapter<SafeArea>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setValues()
        setupEvents()
    }

    override fun setValues() {
        addData()
        safeAreaRV.setLayoutManager(LinearLayoutManager(mContext))
        safeAreaRV.adapter = SafeAreaAdapter<SafeArea>(mContext,mList)

    }

    override fun setupEvents() {

    }

    fun addData() {

//        for (i in 0..20){
//            val add = mList.add(SafeArea("가게이름 $i", "가게 주소 $i"))
//        }
        ConnetServer.getRequestSafeArea(mContext,object :ConnetServer.JsonResponseHandler{
            override fun onResponse(json: JSONObject) {
                val womanSafeAreaInfo = json.getJSONObject("womanSafeAreaInfo")
                val RESULT = womanSafeAreaInfo.getJSONObject("RESULT")
                val code = RESULT.getString("CODE")
                Log.d("code : ", code.toString())
                val row = womanSafeAreaInfo.getJSONArray("row")

                if (code == "INFO-000"){
                    if (code == "INFO-000") {
                        runOnUiThread {
                            for (i in 0..row.length()-1) {
                                val safeAreaJson = row.getJSONObject(i)

                                val safeAreaObject = SafeArea.getSafeAreaFromJson(safeAreaJson)
                                mList.add(safeAreaObject)
                            }
                            mAdapter?.notifyDataSetChanged()
                        }
                    }
                }
            }

        })
    }

}
