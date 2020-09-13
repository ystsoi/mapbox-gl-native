package com.test.testemulatormarkerissue;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.mapbox.geojson.Feature;
import com.mapbox.geojson.Geometry;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.style.layers.PropertyFactory;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;

public class MainActivity extends AppCompatActivity {

    private MapView mapView;
    private Button showMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Mapbox.getInstance(this, getString(R.string.mapbox_access_token));

        setContentView(R.layout.activity_main);

        mapView = findViewById(R.id.mapView);
        showMarker = findViewById(R.id.showMarker);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull final MapboxMap mapboxMap) {

                mapboxMap.setStyle("asset://style-empty.json", new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull final Style style) {

                        // Map is set up and the style has loaded. Now you can add data or make other map adjustments
                        showMarker.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                GeoJsonSource s = style.getSourceAs("test_source");
                                if (s == null) {
                                    // Show a marker.
                                    style.addImage("test_icon", ContextCompat.getDrawable(MainActivity.this, com.mapbox.mapboxsdk.R.drawable.mapbox_user_icon));
                                    Geometry g = Point.fromLngLat(mapboxMap.getCameraPosition().target.getLongitude(), mapboxMap.getCameraPosition().target.getLatitude());
                                    Feature f = Feature.fromGeometry(g);
                                    s = new GeoJsonSource("test_source", f);
                                    SymbolLayer l = new SymbolLayer("test_layer", "test_source");
                                    l.setProperties(PropertyFactory.iconAllowOverlap(true), PropertyFactory.iconIgnorePlacement(true), PropertyFactory.iconImage("test_icon"));
                                    style.addSource(s);
                                    style.addLayer(l);
                                } else {
                                    // Update the marker which will turn gray in an emulator.
                                    SymbolLayer l = style.getLayerAs("test_layer");
                                    l.setProperties(PropertyFactory.iconAllowOverlap(true), PropertyFactory.iconIgnorePlacement(true), PropertyFactory.iconImage("test_icon"), PropertyFactory.iconSize((float) (Math.random() + 0.5)));
                                }
                            }
                        });

                    }
                });

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
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

}