package com.example.a200104_recyclerviewpractice.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.a200104_recyclerviewpractice.MapsActivity
import com.example.a200104_recyclerviewpractice.R
import com.example.a200104_recyclerviewpractice.datas.SafeArea

class SafeAreaAdapter<T>(context: Context, list: ArrayList<SafeArea>) :
    RecyclerView.Adapter<SafeAreaAdapter.myViewHolder>() {

    val mList = list
    val mContext = context
    val inf = LayoutInflater.from(mContext)

    //    item 의 개수를 리턴하는 함수 => 보통은 데이터 리스트의 길이로 사용
    override fun getItemCount(): Int {
        return mList.size
    }

    //    view를 inflate 해주고 Viewholder에 넣어주기?
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
//        how do we even create view
        var tempRow = inf.inflate(R.layout.safe_area_item, parent, false)

//    ViewHolder을 리턴해 주어야 하므로 하단에 따로 작성한 myViewHolder의
        return myViewHolder(tempRow)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        holder.bindItem(mList.get(position), mContext)
        Log.d("data : ", "$mList.get(position)")
    }

    class myViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindItem(data: SafeArea, context: Context) {

            itemView.findViewById<TextView>(R.id.storeAddrTxt).text = data.storeName
            itemView.findViewById<TextView>(R.id.stroeNameTxt).text = data.storeAddr
            itemView.setOnClickListener {
                if (data != null) {

                    var intent = Intent(context, MapsActivity::class.java)
                    intent.putExtra("safeArea", data)
                    context.startActivity(intent)
                    Log.d("startActivity ; ", "$data")
                }else{
                }
            }
        }

    }
}

