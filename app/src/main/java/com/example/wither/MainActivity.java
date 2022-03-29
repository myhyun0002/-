package com.example.wither;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class MainActivity extends AppCompatActivity  {

    // 여기 코드들은 하단 버튼을 눌렀을 때 그에 맞는 페이지를 보여주기 위한 코드들입니다.

    private HomeFragment homeFragment;
    private ChattingFragment chattingFragment;
    private UserFragment userFragment;





    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //김동찬 연동 확인용
        //밑에는 fragment 간 교체를 위한 코드다
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavi);
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
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
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
}