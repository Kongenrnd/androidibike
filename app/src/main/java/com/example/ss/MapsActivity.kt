package com.example.ss

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
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
        val myPlace = LatLng(24.1626492,120.6381141)  // this is New York
        mMap.addMarker(MarkerOptions().position(myPlace).title("台中歌劇院"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(myPlace))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myPlace, 16.0f))
        val taipie101 = LatLng(25.033611, 121.565000)
        val markOpt = MarkerOptions()
        markOpt.position(taipie101)
        markOpt.title("台北101")
        markOpt.draggable(true)
        markOpt.infoWindowAnchorU
        mMap.addMarker(markOpt)
        mMap.setOnCircleClickListener {
            mMap.setInfoWindowAdapter()
        }


    }
}

private fun GoogleMap.setInfoWindowAdapter() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}
