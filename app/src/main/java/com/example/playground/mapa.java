package com.example.playground;


import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;


/**
 * A simple {@link Fragment} subclass.
 */
public class mapa extends Fragment implements OnMapReadyCallback {
    SupportMapFragment mapFragment;
    private GoogleMap mMap;
    private LocationManager lm;


    public mapa() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_mapa, container, false);
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment == null){
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            mapFragment = SupportMapFragment.newInstance();
            ft.replace(R.id.map, mapFragment).commit();
        }
        mapFragment.getMapAsync(this);
        return v;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            /*if  (shouldShowRequestPermissionRationale(Manifest.permission_group.LOCATION)){
                Toast.makeText(this, "Necescitaremos acceder a tu localizacion", Toast.LENGTH_SHORT).show();
            }*/
            //requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION});
            return;
        }
        mMap.setMyLocationEnabled(true);
        lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        Location localizacion = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        //LatLng coordenadas = new LatLng(localizacion.getLatitude(), localizacion.getLongitude());
        LatLng coordenadas = new LatLng(22.38065, -97.905309);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(coordenadas, 15);
        mMap.animateCamera(cameraUpdate);
        casa();
        //La funcion clear de Map quita las polyline existentes
        //mMap.clear();
    }

    private void crearRuta1(){
        LatLng origen = new LatLng(22.37842, -97.903381);
        LatLng destino = new LatLng(22.379333, -97.907581);

        mMap.addPolyline(
                new PolylineOptions()
                        .add(origen)
                        .add(destino)
                        .width(2f)
                        .color(Color.RED)
        );
    }

    private void crearRuta2(){
        LatLng origen = new LatLng(22.380315, -97.906956);
        LatLng destino = new LatLng(22.377855, -97.906481);

        mMap.addPolyline(
                new PolylineOptions()
                        .add(origen)
                        .add(destino)
                        .width(2f)
                        .color(Color.BLUE)
        );
    }

    public void casa(){
        mMap.clear();
        crearRuta1();
        crearRuta2();
    }

    public void ruta1(){
        mMap.clear();
        crearRuta1();
    }
}
