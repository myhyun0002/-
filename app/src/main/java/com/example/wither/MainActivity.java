package com.example.wither;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.util.FusedLocationSource;

public class MainActivity extends AppCompatActivity  {

    // 여기 코드들은 하단 버튼을 눌렀을 때 그에 맞는 페이지를 보여주기 위한 코드들입니다.

    private BottomNavigationView bottomNavigationView;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private HomeFragment homeFragment;
    private ChattingFragment chattingFragment;
    private UserFragment userFragment;





    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //밑에는 fragment 간 교체를 위한 코드다
        bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuitem) {
                int itemId = menuitem.getItemId();
                if (itemId == R.id.action_home) {
                    setFrag(0);
                } else if (itemId == R.id.action_chat) {
                    setFrag(1);
                } else if (itemId == R.id.action_user) {
                    setFrag(2);
                }
                return true;
            }
        });

        homeFragment = new HomeFragment();
        chattingFragment = new ChattingFragment();
        userFragment = new UserFragment();

        setFrag(0); // 첫 fragment 화면을 homeFragment로 한다.

    }


        // 프래그먼트 교체가 일어나는 실행문이다.
    private void setFrag(int n){
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        switch(n){
            case 0:
                fragmentTransaction.replace(R.id.main_frame,homeFragment);
                fragmentTransaction.commit();
                break;

            case 1:
                fragmentTransaction.replace(R.id.main_frame,chattingFragment);
                fragmentTransaction.commit();
                break;
            case 2:
                fragmentTransaction.replace(R.id.main_frame,userFragment);
                fragmentTransaction.commit();
                break;
        }
    }

//    // 지도 마커 표시를 위한 메서드
//    @Override
//    public void onMapReady(@NonNull NaverMap naverMap) {
//        Log.d(TAG,"onMapReady");
//
//        // 지도 상에 마커 표시
//        Marker marker = new Marker();
//        marker.setPosition(new LatLng(37.5670135,126.9783740));
//
//        // NaverMap 객체 받아서 NaverMap 객체에 위치 소스 지정
//        mNaverMap = naverMap;
//        mNaverMap.setLocationSource(mLocationSource);
//
//        ActivityCompat.requestPermissions(this,PERMISSIONS,PERMISSION_REQUEST_CODE);
//    }
//
//
//    // 지도 마커 표시를 위한 메서드
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//        if(requestCode == PERMISSION_REQUEST_CODE){
//            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                mNaverMap.setLocationTrackingMode(LocationTrackingMode.None);
//            }
//        }
//    }
}