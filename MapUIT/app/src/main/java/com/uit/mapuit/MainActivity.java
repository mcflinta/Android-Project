package com.uit.mapuit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.mapbox.maps.CameraBoundsOptions;
import com.mapbox.maps.CameraOptions;
import com.mapbox.maps.MapInitOptions;
import com.mapbox.maps.MapView;
import com.mapbox.maps.MapboxMap;
import com.mapbox.maps.plugin.Plugin;
import com.mapbox.maps.plugin.animation.CameraAnimationsPlugin;
import com.mapbox.maps.plugin.annotation.AnnotationConfig;
import com.mapbox.maps.plugin.annotation.AnnotationPlugin;
import com.mapbox.maps.plugin.annotation.AnnotationPluginImplKt;
import com.mapbox.maps.plugin.annotation.AnnotationType;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions;
import com.mapbox.maps.plugin.attribution.AttributionPlugin;
import com.mapbox.maps.plugin.logo.LogoPlugin;
import com.mapbox.maps.plugin.scalebar.ScaleBarPlugin;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    MapModel mapData;
    MapView mapView;
    static MapboxMap mapboxMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        APIManager.getMap();
        mapView = findViewById(R.id.mapView);
        mapView.setVisibility(View.INVISIBLE);
        new Thread(() -> {
            while (!MapModel.isReady)
            {
                try {
                    Thread.sleep(100);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
            this.runOnUiThread(this::setMapView);
        }).start();
    }
    private void setMapView() {
        mapData = MapModel.getMapObj();
        ScaleBarPlugin scaleBarPlugin = mapView.getPlugin(Plugin.MAPBOX_SCALEBAR_PLUGIN_ID);
        assert scaleBarPlugin != null;
        scaleBarPlugin.setEnabled(false);
//
        LogoPlugin logoPlugin = mapView.getPlugin(Plugin.MAPBOX_LOGO_PLUGIN_ID);
        assert logoPlugin != null;
        logoPlugin.setEnabled(false);
//
        AttributionPlugin attributionPlugin = mapView.getPlugin(Plugin.MAPBOX_ATTRIBUTION_PLUGIN_ID);
        assert attributionPlugin != null;
        attributionPlugin.setEnabled(false);

        mapboxMap = mapView.getMapboxMap();
        mapboxMap.loadStyleJson(Objects.requireNonNull(new Gson().toJson(mapData)), style -> {

            style.removeStyleLayer("poi-level-1");
            style.removeStyleLayer("highway-name-major");

            // Get the annotation plugin instance
            AnnotationPlugin annoPlugin = AnnotationPluginImplKt.getAnnotations(mapView);
            AnnotationConfig annoConfig = new AnnotationConfig("map_annotation");
            PointAnnotationManager pointAnnoManager = (PointAnnotationManager) annoPlugin.createAnnotationManager(AnnotationType.PointAnnotation, annoConfig);

            // Add click listener to the annotation manager
            pointAnnoManager.addClickListener(pointAnnotation -> {
                String id = Objects.requireNonNull(pointAnnotation.getData()).getAsJsonObject().get("id").getAsString();
//                toggleBottomSheet(id);
//                setBottomSheet1(id);

                return true;
            });

            // Add device markers to the map
            ArrayList<PointAnnotationOptions> markerList = new ArrayList<>();



            pointAnnoManager.create(markerList);
        });
        mapboxMap.setCamera(
                new CameraOptions.Builder()
                        .center(mapData.getCenter())
                        .zoom(mapData.getZoom())
                        .build()
        );

        // Set camera bounds
        mapboxMap.setBounds(
                new CameraBoundsOptions.Builder()
                        .minZoom(mapData.getMinZoom())
                        .maxZoom(mapData.getMaxZoom())
                        .bounds(mapData.getBounds())
                        .build()
        );

        // Set camera animation
        CameraAnimationsPlugin cameraAnimationsPlugin = mapView.getPlugin(Plugin.MAPBOX_CAMERA_PLUGIN_ID);
        assert cameraAnimationsPlugin != null;
//        if (!isHidden()) onHiddenChanged(false);
        mapView.setVisibility(View.VISIBLE);
    }

}