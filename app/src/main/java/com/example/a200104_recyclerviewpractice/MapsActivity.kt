package com.example.a200104_recyclerviewpractice

import android.content.Intent
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.a200104_recyclerviewpractice.datas.SafeArea
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.io.IOException

class MapsActivity : BaseActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    lateinit var stotreLatLng :LatLng
    lateinit var data:SafeArea
    private lateinit var mFusedLocationProviderClient :FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        setValues()
        setupEvents()

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val storeLoc = stotreLatLng

        mMap.addMarker(MarkerOptions().position(storeLoc!!).title("${data.storeName}").snippet("${data.storeAddr}"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(storeLoc))
        mMap.moveCamera(CameraUpdateFactory.zoomTo(20f))
    }

    override fun setValues() {

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        data = intent?.getSerializableExtra("safeArea") as SafeArea
        var storeAddr = data?.storeAddr
        stotreLatLng = getLocationFromAddr(storeAddr!!)

        Log.d("인텐트 주소 전달 : ", "성공")
    }

    override fun setupEvents() {

    }

    fun getLocationFromAddr(storeAddr:String):LatLng{
        var mGeoCoder = Geocoder(mContext)
        var result:LatLng? = null
        try {
            var location = mGeoCoder.getFromLocationName("$storeAddr",1)

            var mLat = location.get(0).latitude
            var mLng = location.get(0).longitude
            Log.d("위 / 경도 : ","$mLat / $mLng")


            result = LatLng(mLat,mLng)

        }catch (e : IOException){
            e.printStackTrace()
        }
        return result!!
    }


    fun getLocationPermission(){

    }

}
