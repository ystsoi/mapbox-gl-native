package com.test.testterrainrgbissue2;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private MapView mapView;
    private TextView position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, "YOUR_MAPBOX_ACCESS_TOKEN");
        setContentView(R.layout.activity_main);
        mapView = findViewById(R.id.mapView);
        position = findViewById(R.id.position);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull final MapboxMap mapboxMap) {
                mapboxMap.setStyle("asset://style.json", new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {
                    }
                });
                position.setText(String.format("%f %f,%f", mapboxMap.getCameraPosition().zoom, mapboxMap.getCameraPosition().target.getLatitude(), mapboxMap.getCameraPosition().target.getLongitude()));
                mapboxMap.addOnCameraMoveListener(new MapboxMap.OnCameraMoveListener() {
                    @Override
                    public void onCameraMove() {
                        position.setText(String.format("%f %f,%f", mapboxMap.getCameraPosition().zoom, mapboxMap.getCameraPosition().target.getLatitude(), mapboxMap.getCameraPosition().target.getLongitude()));
                    }
                });
                findViewById(R.id.switchStyle).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mapboxMap.setStyle(mapboxMap.getStyle().getUri().endsWith("style.json") ? "asset://style2.json" : "asset://style.json");
                    }
                });
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
}
