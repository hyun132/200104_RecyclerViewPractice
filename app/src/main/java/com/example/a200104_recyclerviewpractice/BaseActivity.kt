package com.example.a200104_recyclerviewpractice

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity:AppCompatActivity() {
    var mContext = this

    abstract fun setValues()
    abstract fun setupEvents()

}