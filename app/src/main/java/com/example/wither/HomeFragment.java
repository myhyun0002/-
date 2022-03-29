package com.example.wither;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.NaverMapOptions;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.UiSettings;
import com.naver.maps.map.overlay.LocationOverlay;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.OverlayImage;
import com.naver.maps.map.util.FusedLocationSource;
import com.naver.maps.map.widget.LocationButtonView;
import com.naver.maps.map.widget.ZoomControlView;

import java.security.Permission;
import java.util.Objects;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link HomeFragment#newInstance} factory method to
// * create an instance of this fragment.
// */

public class HomeFragment extends Fragment implements OnMapReadyCallback{

        // 지도에 마커 표시를 위한 코드

    private static final int PERMISSION_REQUEST_CODE  = 100;
    private static final String[] PERMISSIONS = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
    };

    private FusedLocationSource mLocationSource;
    private NaverMap mNaverMap;


    public HomeFragment() {
        // Required empty public constructor
    }



//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//

//    public static HomeFragment newInstance(String param1, String param2) {
//        HomeFragment fragment = new HomeFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

                // 밑에는 지도 마커 표시를 위한 코드다
        FragmentManager fm = getParentFragmentManager();
        MapFragment mapFragment = (MapFragment)fm.findFragmentById(R.id.map_fragment);
        if(mapFragment == null){
            mapFragment = MapFragment.newInstance();
            fm.beginTransaction().add(R.id.map_fragment,mapFragment).commit();
        }

        mapFragment.getMapAsync(this);
        mLocationSource = new FusedLocationSource(this,PERMISSION_REQUEST_CODE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //ZoomControlView zoomControlView = view.findViewById(R.id.zoom);
        //zoomControlView.setMap(mNaverMap);

        return view;

    }

        // 지도 마커 표시를 위한 메서드
    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {

        // 지도 상에 마커 표시
        Marker marker = new Marker();

        marker.setPosition(new LatLng(34,102));

//        LocationOverlay locationOverlay = naverMap.getLocationOverlay();
//        marker.setIcon(OverlayImage.fromResource(R.drawable.ic_chat));
//        locationOverlay.setVisible(true);
//        locationOverlay.setIcon(OverlayImage.fromResource(R.drawable.ic_chat1));
//        locationOverlay.setIconWidth(50);
//        locationOverlay.setIconHeight(50);
//        locationOverlay.setAnchor(new PointF(0.5f, 1));

        // NaverMap 객체 받아서 NaverMap 객체에 위치 소스 지정
        mNaverMap = naverMap;
        mNaverMap.setLocationSource(mLocationSource);

        this.requestPermissions(PERMISSIONS, PERMISSION_REQUEST_CODE);

        // 현위치 버튼 , zoom버튼 생성
        UiSettings uiSettings = mNaverMap.getUiSettings();
        uiSettings.setLocationButtonEnabled(true);
        uiSettings.setZoomControlEnabled(false);



        // 네이버 로고 위치 지정
        //uiSettings.setLogoGravity(getView().getTop());
        //uiSettings.setLogoMargin(940,30,30,0);

        //기울임 틸트 비율 비활성화
        uiSettings.setTiltGesturesEnabled(false);
        uiSettings.setRotateGesturesEnabled(false);
        uiSettings.setScaleBarEnabled(false);


    }

        @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == PERMISSION_REQUEST_CODE){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                mNaverMap.setLocationTrackingMode(LocationTrackingMode.Follow);
            }
        }
    }



}
