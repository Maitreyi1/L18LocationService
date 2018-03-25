package in.abc.l18locationservices;

import android.content.Intent;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    float lat, lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Intent got = getIntent();
         lat = got.getFloatExtra("lat", 0);
         lon = got.getFloatExtra("lon", 0);


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
    @Override
    public void onMapReady(GoogleMap googleMap) {
        LocationManager locMan = (LocationManager) getSystemService(LOCATION_SERVICE);

        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng delhi = new LatLng(lat,lon);
        mMap.addMarker(new MarkerOptions().position(delhi).title("Marker in Delhi"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(delhi));//camera si what you see on screen
        mMap.animateCamera(CameraUpdateFactory.zoomTo(12));//15 max zoon, 1 min zoom
        mMap.getUiSettings().setCompassEnabled(true);//chamge the Ui settings of the map
        mMap.getUiSettings().setZoomControlsEnabled(true);
    }
}

//multiDexEnabled in build.gradle helps in the exception of Dexenabled. it makes 2 jar files sp that the memory is not over exploded it helps.
