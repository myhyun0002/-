package com.example.wither;

import android.graphics.Color;
import android.graphics.PointF;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraUpdate;

import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;

import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.UiSettings;

import com.naver.maps.map.overlay.LocationOverlay;
import com.naver.maps.map.overlay.Marker;

import com.naver.maps.map.overlay.OverlayImage;
import com.naver.maps.map.util.FusedLocationSource;

import java.util.Objects;


public class HomeFragment extends Fragment implements OnMapReadyCallback {

    FragmentActivity mcontext;

    // 실시간 데이터 주고 받기
    private SharedViewModel sharedViewModel;

    private LocationManager locationManager;
    private GpsTracker gpsTracker;
    private FusedLocationProviderClient mFusedLocationClient;
    private FusedLocationSource mLocationSource;
    private NaverMap mNaverMap;
    private double latitude, longitude;
    Location location;
    private Marker marker = new Marker();

    private HomeFloatingFragment homeFlaotingActionFragment;
    private FragmentActivity myContext;

    // 데이터베이스 값 저장
    private MakeDatabase database;

    // 사용자 위치
    //

    public HomeFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // 현위치 버튼 클릭시 현재 위치로 이동과 함께 마커 표시
        ImageButton ShowLocationButton = (ImageButton)view.findViewById(R.id.gpsbtn);
        ShowLocationButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                gpsTracker = new GpsTracker(getActivity().getApplicationContext());

                setLatitude(gpsTracker.getLatitude());
                setLongitude(gpsTracker.getLongitude());

                // 지도에 마커 표시
                setUserMarker(getLatitude(),getLongitude());

                // 현위치로 지도 이동
                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(getLatitude(), getLongitude()));
                mNaverMap.moveCamera(cameraUpdate);
            }
        });

        //activity에서 실시간 위치 정보 가져옴


//        TextView textView = (TextView)view.findViewById(R.id.textView);
//        textView.setText("위도는 " + getLatitude() + "\n 경도는 " + getLongitude()+"\n" + count);


        // 다른 탭으로 이동 후에 다시 돌아와도 지도 초기화 안되게 하는 코드
        MapView mapView;
        mapView = (MapView) view.findViewById(R.id.map_fragment);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this); // 비동기적 방식으로 구글 맵 실행

        // 홈화면 floatingbutton 누르면 HomeFloatingFragment 띄우기
        FragmentManager fm = getChildFragmentManager();
        FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.floatingActionButton);
        homeFlaotingActionFragment = new HomeFloatingFragment();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fm.findFragmentByTag("homefloatingbtn") != null) {
                    //if the fragment exists, show it.
                    fm.beginTransaction().remove(fm.findFragmentById(R.id.homeFragmentFrame)).commit();
                } else {
                    //if the fragment does not exist, add it to fragment manager.
                    //fm.beginTransaction().add(R.id.homeFragmentFrame, homeFlaotingActionFragment, "homefloatingbtn").commit();

                    Bundle bundle = new Bundle(3); // 번들을 통해 값 전달
                    bundle.putDouble("latitude",getLatitude());//번들에 넘길 값 저장
                    bundle.putDouble("longitude",getLongitude());//번들에 넘길 값 저장
                    bundle.putBundle("long_latitudeBundle",bundle);
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                    HomeFloatingFragment homeFloatingFragment = new HomeFloatingFragment();//프래그먼트2 선언
                    homeFloatingFragment.setArguments(bundle);//번들을 프래그먼트2로 보낼 준비
                    transaction.add(R.id.homeFragmentFrame, homeFloatingFragment);
                    transaction.commit();
                }
            }
        });

        return view;
    }

    // homeFloatingFragment에서 마커 생성 버튼을 누르면 정보를 받아와 지도에 마커 생성
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        sharedViewModel.getLiveData().observe(getViewLifecycleOwner(), new Observer<MakeDatabase>() {
            @Override
            public void onChanged(MakeDatabase database) {
                setDatabase(database);
                setCategoryMarker(database);

                Toast.makeText(getActivity(),database.getMeeting_name()+"",Toast.LENGTH_SHORT).show();
            }
        });
    }

    // 지도 마커 표시를 위한 메서드
    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {

        // NaverMap 객체 받아서 NaverMap 객체에 위치 소스 지정
        mNaverMap = naverMap;
        mNaverMap.setLocationSource(mLocationSource);

//        this.requestPermissions(PERMISSIONS, PERMISSION_REQUEST_CODE);

        Bundle bundle = getArguments();

        // 로딩 때 현재 위치 가져와서 화면에 보여주기
        if(bundle != null){
            setLatitude(bundle.getDouble("latitude"));
            setLongitude(bundle.getDouble("longitude"));

            setUserMarker(getLatitude(),getLongitude());

            CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(getLatitude(), getLongitude()));
            mNaverMap.moveCamera(cameraUpdate);

        }

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

    private void setUserMarker(double lat, double lng){
        LocationOverlay locationOverlay = mNaverMap.getLocationOverlay();
        locationOverlay.setIconHeight(70);
        locationOverlay.setIconWidth(70);
        locationOverlay.setCircleRadius(50);
//        locationOverlay.setCircleOutlineWidth(1);
        locationOverlay.setCircleOutlineColor(Color.rgb(12,144,255));
        locationOverlay.setPosition(new LatLng(lat, lng));
        locationOverlay.setVisible(true);
    }


    private void setCategoryMarker(MakeDatabase makeDatabase)
    {
        Marker marker = new Marker();
        //아이콘 지정
        marker.setIcon(OverlayImage.fromResource(makeDatabase.getResourceID()));
        //마커 위치
        marker.setPosition(new LatLng(makeDatabase.getLatitude(), makeDatabase.getLongitude()));
        //마커 표시
        marker.setWidth(70);
        marker.setHeight(70);
        marker.setMap(mNaverMap);
    }

//    @Override
//    public void setArguments(@NonNull Bundle args) {
//        assert args != null;
//        MakeDatabase database = (MakeDatabase) args.getSerializable("database");
//    }


    public double getLatitude(){
        return latitude;
    }

    public void setLatitude(double latitude){
        this.latitude = latitude;
    }

    public double getLongitude(){
        return longitude;
    }

    public void setLongitude(double longitude){
        this.longitude = longitude;
    }

    public void setDatabase(MakeDatabase database) {
        this.database = database;
    }
}

//    MapFragment mapFragment = (MapFragment) fm.findFragmentById(R.id.map_fragment);
//        if (mapFragment == null) {
//            mapFragment = MapFragment.newInstance();
//            fm.beginTransaction().add(R.id.map_fragment, mapFragment).commit();
//        }
//        mapFragment.onDestroy();
//
//        mapFragment.getMapAsync(this);
//locationManager = (LocationManager)getContext().getSystemService(Context.LOCATION_SERVICE);
//사용자의 현재 위치

//mLocationSource = new FusedLocationSource(this, PERMISSION_REQUEST_CODE);



//TextView textView = (TextView)view.findViewById(R.id.textView);
//textView.setText("위도는 " + latitude + "\n 경도는 " + longitude);


