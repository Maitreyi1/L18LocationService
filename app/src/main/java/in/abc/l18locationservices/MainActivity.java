package in.abc.l18locationservices;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "location";
    float lat, lon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button)findViewById(R.id.button2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MapsActivity.class);
                i.putExtra("lat",lat);
                i.putExtra("lon", lon);
                startActivity(i);
            }
        });

        LocationManager locMan = (LocationManager) getSystemService(LOCATION_SERVICE);
        LocationListener LocLis = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                lat = (float) location.getLatitude();
                 lon = (float) location.getLongitude();
                Log.d(TAG, "onLocationChanged: " + location.getLongitude());
                Log.d(TAG, "onLocationChanged: "+ location.getLatitude());
                Log.d(TAG, "onLocationChanged: " +location.getAltitude());
                Log.d(TAG, "onLocationChanged: " + location.getBearing());
                Log.d(TAG, "onLocationChanged: " + location.getSpeed());
              //  Log.d(TAG, "onLocationChanged: " + location.getElapsedRealtimeNanos());//when the location was recorded

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 121);

            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

        }
        else {
            locMan.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER, 1000, 10, LocLis
            );
        }// after how long you get an updatein sec and metres
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==121){
            Toast.makeText(this, "RESTART APP TO USE PERMISSIONS" , Toast.LENGTH_SHORT).show();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
