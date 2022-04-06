package com.example.wither;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class HomeFloatingFragment extends Fragment {

    // fragment에서 context를 쓸려면 getActivity()

    TextView dateText;

    DatePickerDialog datePickerDialog;
    Calendar calendar = Calendar.getInstance();
    int pYear = calendar.get(Calendar.YEAR); //년
    int pMonth = calendar.get(Calendar.MONTH);//월
    int pDay = calendar.get(Calendar.DAY_OF_MONTH);//일


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

        // 날짜 선택 버튼
        dateText = view.findViewById(R.id.date_picker_text);
        Button button = view.findViewById(R.id.date);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate();
            }
        });

        return view;

    }
    void showDate() {
        //오늘 날짜(년,월,일) 변수에 담기

        datePickerDialog = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                        //1월은 0부터 시작하기 때문에 +1을 해준다.
                        month = month + 1;
                        String date = year + "/" + month + "/" + day;

                        dateText.setText(date);
                    }
                }, pYear, pMonth, pDay);
        datePickerDialog.show();
    }

    // 시간 설정
//    void showTime() {
//        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
//            @Override
//            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                h = hourOfDay;
//                mi = minute;
//
//            }
//        }, 21, 12, true);
//
//        timePickerDialog.setMessage("메시지");
//        timePickerDialog.show();
//    }
}
