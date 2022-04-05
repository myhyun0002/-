package com.example.wither;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeFloatingFragment extends Fragment {

    public HomeFloatingFragment() {

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        View view = inflater.inflate(R.layout.fragment_home_floatingbtn, container, false);

        return view;

    }
//    public void showDatePicker(View view) {
//        DialogFragment newFragment = new DatePickerFragment();
//        newFragment.show(view.getSupportFragmentManager(),"datePicker");
//    }
//
//    public void processDatePickerResult(int year, int month, int day){
//        String month_string = Integer.toString(month+1);
//        String day_string = Integer.toString(day);
//        String year_string = Integer.toString(year);
//        String dateMessage = (month_string + "/" + day_string + "/" + year_string);
//
//        Toast.makeText(this,"Date: "+dateMessage,Toast.LENGTH_SHORT).show();
//    }
}
