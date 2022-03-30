package com.example.wither;

import android.Manifest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;

import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.UiSettings;

import com.naver.maps.map.overlay.Marker;

import com.naver.maps.map.util.FusedLocationSource;
import com.naver.maps.map.widget.ZoomControlView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class HomeFragment extends Fragment implements OnMapReadyCallback {

    // 지도에 마커 표시를 위한 코드

    private static final int PERMISSION_REQUEST_CODE = 100;
    private static final String[] PERMISSIONS = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
    };

    private FusedLocationSource mLocationSource;
    private NaverMap mNaverMap;

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
        // Inflate the layout for this fragment

        FragmentManager fm = getParentFragmentManager();

        MapFragment mapFragment = (MapFragment) fm.findFragmentById(R.id.map_fragment);
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            fm.beginTransaction().add(R.id.map_fragment, mapFragment).commit();
        }
        mapFragment.onDestroy();
        mapFragment.getMapAsync(this);

        mLocationSource = new FusedLocationSource(this, PERMISSION_REQUEST_CODE);


        View view = inflater.inflate(R.layout.fragment_home, container, false);



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

        // 지도 상에 마커 표시


        // 마커 아이콘 등록
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

        Marker marker = new Marker();
//        GpsTracker gpsTracker = new GpsTracker(getActivity());
//        double latitude = gpsTracker.getLatitude(); // 위도
//        double longitude = gpsTracker.getLongitude(); //경도

        marker.setPosition(new LatLng(32, 102));
        marker.setVisible(false);


        this.requestPermissions(PERMISSIONS, PERMISSION_REQUEST_CODE);

        // 현위치 버튼 , zoom버튼 생성

        UiSettings uiSettings = mNaverMap.getUiSettings();
        uiSettings.setLocationButtonEnabled(true);
        uiSettings.setZoomControlEnabled(false);



//         네이버 로고 위치 지정
//        uiSettings.setLogoGravity(getView().getTop());
//        uiSettings.setLogoMargin(940,30,30,0);

        //기울임 틸트 비율 비활성화
        uiSettings.setTiltGesturesEnabled(false);
        uiSettings.setRotateGesturesEnabled(false);
        uiSettings.setScaleBarEnabled(false);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mNaverMap.setLocationTrackingMode(LocationTrackingMode.NoFollow);
            }
        }
    }

//    public String getCurrentAddress(double latitude, double longitude) {
//
//        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
//        List<Address> addresses;
//
//        try {
//            addresses = geocoder.getFromLocation(
//                    latitude, longitude, 100);
//        } catch (IOException ioException) {
//            //네트워크 문제
//            showDialogForLocationServiceSetting();
//            return "지오코더 서비스 사용불가";
//        }
//
//
//    }
//
//    private void showDialogForLocationServiceSetting() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setTitle("위치 서비스 비활성화");
//        builder.setMessage("앱을 사용하기 위해서는 위치 서비스가 필요합니다.\n" + "위치 설정을 수정하실래요?");
//        builder.setCancelable(true);
//        builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int id) {
//                Intent callGPSSettingIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE);
//            }
//        });
//        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int id) {
//                dialog.cancel();
//            }
//        });
//        builder.create().show();
//    }

}
