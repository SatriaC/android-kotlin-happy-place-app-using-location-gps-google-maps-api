package com.fromzerotohero.happyplaces.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fromzerotohero.happyplaces.R
import com.fromzerotohero.happyplaces.models.HappyPlaceModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_map.*

/**
 * https://www.raywenderlich.com/230-introduction-to-google-maps-api-for-android-with-kotlin
 */
// TODO (Step 3: Extend a OnMapReadyCallback interface.)
class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    // TODO (Step 1: Create a variable for data model class.)
    // START
    private var mHappyPlaceDetails: HappyPlaceModel? = null
    // END

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        // TODO (Step 2: Receives the details through intent and used further.)
        // START
        if (intent.hasExtra(MainActivity.EXTRA_PLACE_DETAILS)) {
            mHappyPlaceDetails =
                intent.getSerializableExtra(MainActivity.EXTRA_PLACE_DETAILS) as HappyPlaceModel
        }

        if (mHappyPlaceDetails != null) {

            setSupportActionBar(toolbar_map)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.title = mHappyPlaceDetails!!.title

            toolbar_map.setNavigationOnClickListener {
                onBackPressed()
            }

            val supportMapFragment: SupportMapFragment =
                supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
            supportMapFragment.getMapAsync(this)
        }
        // END
    }

    // TODO (Step 4: After extending an interface adding the location pin to the map when the map is ready using the latitude and longitude.)
    // START
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

        /**
         * Add a marker on the location using the latitude and longitude and move the camera to it.
         */
        val position = LatLng(
            mHappyPlaceDetails!!.latitude,
            mHappyPlaceDetails!!.longitude
        )
        googleMap.addMarker(MarkerOptions().position(position).title(mHappyPlaceDetails!!.location))
        val newLatLngZoom = CameraUpdateFactory.newLatLngZoom(position, 15f)
        googleMap.animateCamera(newLatLngZoom)
    }
// END
}