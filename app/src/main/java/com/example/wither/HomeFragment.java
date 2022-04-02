package com.example.wither;

import android.Manifest;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;

import android.graphics.PointF;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;

import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.UiSettings;

import com.naver.maps.map.overlay.LocationOverlay;
import com.naver.maps.map.overlay.Marker;

import com.naver.maps.map.overlay.OverlayImage;
import com.naver.maps.map.util.FusedLocationSource;
import com.naver.maps.map.widget.ZoomControlView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class HomeFragment extends Fragment implements OnMapReadyCallback {

    FragmentActivity mcontext;


    private LocationManager locationManager;
    private GpsTracker gpsTracker;
    private FusedLocationProviderClient mFusedLocationClient;
    private FusedLocationSource mLocationSource;
    private NaverMap mNaverMap;
    private double latitude, longitude;

//    private FusedLocationProviderClient fusedLocationClient;

    public HomeFragment() {
        // Required empty public constructor

        //onMapReady(mNaverMap);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ImageButton ShowLocationButton = (ImageButton)view.findViewById(R.id.button3);
        ShowLocationButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                gpsTracker = new GpsTracker(getActivity().getApplicationContext());

                latitude = gpsTracker.getLatitude();
                longitude = gpsTracker.getLongitude();

                Marker marker = new Marker();
                marker.setPosition(new LatLng(latitude, longitude));
                marker.setMap(mNaverMap);

                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(latitude, longitude));
                mNaverMap.moveCamera(cameraUpdate);
            }
        });

//        MapFragment mapFragment = (MapFragment) fm.findFragmentById(R.id.map_fragment);
//        if (mapFragment == null) {
//            mapFragment = MapFragment.newInstance();
//            fm.beginTransaction().add(R.id.map_fragment, mapFragment).commit();
//        }
//        mapFragment.onDestroy();

//        mapFragment.getMapAsync(this);
        //locationManager = (LocationManager)getContext().getSystemService(Context.LOCATION_SERVICE);
//사용자의 현재 위치

        //mLocationSource = new FusedLocationSource(this, PERMISSION_REQUEST_CODE);


        // 다른 탭으로 이동 후에 다시 돌아와도 지도 초기화 안되게 하는 코드
        MapView mapView;
        mapView = (MapView) view.findViewById(R.id.map_fragment);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this); // 비동기적 방식으로 구글 맵 실행

        return view;
    }

    // 지도 마커 표시를 위한 메서드
    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {

        // 마커 아이콘 등록

        // NaverMap 객체 받아서 NaverMap 객체에 위치 소스 지정
        mNaverMap = naverMap;
        mNaverMap.setLocationSource(mLocationSource);


//        this.requestPermissions(PERMISSIONS, PERMISSION_REQUEST_CODE);

        // 현위치 버튼 , zoom버튼 생성
        UiSettings uiSettings = mNaverMap.getUiSettings();
        uiSettings.setLocationButtonEnabled(false);
        uiSettings.setZoomControlEnabled(false);



//         네이버 로고 위치 지정
        uiSettings.setLogoGravity(getView().getTop());
        uiSettings.setLogoMargin(940,30,30,0);

        //기울임 틸트 비율 비활성화
        uiSettings.setTiltGesturesEnabled(false);
        uiSettings.setRotateGesturesEnabled(false);
        uiSettings.setScaleBarEnabled(false);
    }
}


