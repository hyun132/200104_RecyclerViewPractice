package com.example.a200104_recyclerviewpractice.datas

import org.json.JSONObject
import java.io.Serializable

class SafeArea() : Serializable {
    var id = -1
    var brandName = ""
    var storeName = ""
    var storeAddr = ""
    var storeGuName = ""

    companion object {
        fun getSafeAreaFromJson(json: JSONObject): SafeArea {
            val parsedSafeArea = SafeArea()

            parsedSafeArea.id = json.getInt("ID")
            parsedSafeArea.brandName = json.getString("BR_NM")
            parsedSafeArea.storeName = json.getString("NM")
            parsedSafeArea.storeAddr = json.getString("ADDR")
            parsedSafeArea.storeGuName = json.getString("GU_NM")

            return parsedSafeArea
        }
    }
}