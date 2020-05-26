package com.example.ss

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
//import sun.jvm.hotspot.utilities.IntArray
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.concurrent.ExecutorService
import com.squareup.okhttp.*
import org.json.JSONArray
import org.w3c.dom.DOMStringList
import java.io.IOException
import java.net.URL
import java.util.concurrent.Executors


class MapsActivity : AppCompatActivity(), OnMapReadyCallback,
    GoogleMap.OnMyLocationButtonClickListener, LocationListener {
    private lateinit var service: ExecutorService
    private lateinit var client: OkHttpClient
    private lateinit var mMap: GoogleMap
    var list1 = ArrayList<Double>()
    var list2 = ArrayList<Double>()
    var AvailableCNT = ArrayList<String>()
    var EmpCNT = ArrayList<String>()
    var Position = ArrayList<String>()
    var fromEdit: EditText? = null
    var toEdit: EditText? = null
    var navigateButton: Button? = null
    var addplace = LaLn()
    var myself_gps = LatLng(0.000, 0.000)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        if (checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 0)
        }
        val manager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER) || manager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
            )
        ) {
            var self_gps: Location? = manager.getLastKnownLocation("gps")
            if (self_gps != null) {
                myself_gps = LatLng(self_gps.latitude, self_gps.longitude)

            } else {
                self_gps = manager.getLastKnownLocation("network")
                myself_gps = LatLng(self_gps.latitude, self_gps.longitude)
            }
        }

        initData()
        handleJson()

    }

    private fun initData() {
        client = OkHttpClient()
        service = Executors.newSingleThreadExecutor()
    }

    private fun initView() {
        //setContentView(R.layout.activity_maps)
        //send_request.setOnClickListener { handleRequest() }
        //handleJson()
    }

    private fun handleJson() {
        val request = Request.Builder()
            .url("http://e-traffic.taichung.gov.tw/DataAPI/api/YoubikeAllAPI")
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(request: Request, e: IOException) {
                runOnUiThread { /*text.text = e.message*/ }
            }

            @Throws(IOException::class)
            override fun onResponse(response: Response?) {
                val resStr = response?.body()?.string()
                val jsonData =
                    Gson().fromJson<List<JsonData>>(resStr, object : TypeToken<List<JsonData>>() {
                    }.type)
                runOnUiThread {
                    for (json in jsonData) {
                        Position.add(json.Position.toString())
                        list1.add(json.X.toString().toDouble())
                        list2.add(json.Y.toString().toDouble())
                        AvailableCNT.add(json.AvailableCNT.toString())
                        EmpCNT.add(json.EmpCNT.toString())
//aaas
                        //aaaas

                    }
                    for (i in 0..list1.size - 2) {
                        mMap.addMarker(
                            MarkerOptions().position(
                                LatLng(
                                    list2.get(i),
                                    list1.get(i)
                                )
                            ).title(Position.get(i)).snippet("可借：${AvailableCNT.get(i)} 空位：${EmpCNT.get(i)}").icon(
                                BitmapDescriptorFactory.fromResource(
                                    R.drawable.googlemap_iconset
                                )
                            )
                        )
                    }

                }
            }
        })
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
        mMap.addMarker(MarkerOptions().position(myself_gps).title("我的位置"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(myself_gps))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myself_gps, 15.0f))

        handleJson()

        mMap.setOnCircleClickListener {
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onMyLocationButtonClick(): Boolean {
        return true
    }

    override fun onLocationChanged(location: Location?) {

    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

    }

    override fun onProviderEnabled(provider: String?) {

    }

    override fun onProviderDisabled(provider: String?) {

    }
}


private fun GoogleMap.setInfoWindowAdapter() {

}
