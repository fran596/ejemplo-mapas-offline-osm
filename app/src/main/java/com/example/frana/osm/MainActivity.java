package com.example.frana.osm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.osmdroid.tileprovider.tilesource.XYTileSource;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;

public class MainActivity extends AppCompatActivity {

    MapView mapView;
    GeoPoint routeCenter = new GeoPoint(9.9307399,-84.0487998);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MapView mapView = (MapView) findViewById(R.id.mapview);

        mapView.setClickable(true);
        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(true);
        mapView.setUseDataConnection(false);
        CopyFolder.copyAssets(this);
        final MapController mapViewController = (MapController) mapView.getController();
        mapViewController.setZoom(18);
        mapViewController.animateTo(routeCenter);
        mapView.setTileSource(new XYTileSource("tiles", 14, 18, 256, ".png", new String[0]));
    }
}