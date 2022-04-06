package com.example.wither;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
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
    private int today_Year = calendar.get(Calendar.YEAR); //년
    private int today_Month = calendar.get(Calendar.MONTH);//월
    private int today_Day = calendar.get(Calendar.DAY_OF_MONTH);//일

    private int date_year = 0;
    private int date_month = 0;
    private int date_day = 0;

    private String create_name_variable;
    private int create_person_num_variable;
    private String create_for_friend_variable;

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
        Button date_button = view.findViewById(R.id.date);
        date_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate();
            }
        });

        // 생성 버튼 리스너
        EditText create_name_edit_text = (EditText)view.findViewById(R.id.creat_name);
        EditText create_person_num_edit_text = (EditText)view.findViewById(R.id.create_person_num);
        EditText create_for_friend_edit_text = (EditText)view.findViewById(R.id.create_for_friend);

        Button make_button = view.findViewById(R.id.create_make_btn);
        make_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if ( create_name_edit_text.getText().toString().length() == 0 ) {
                    //모임 이름이 공백일 때 처리할 내용
                    Toast.makeText(getActivity(),"모임 이름을 설정해주세요",Toast.LENGTH_SHORT).show();
                } else {
                    //모임 이름이 공백이 아닐 때 처리할 내용
                    create_name_variable = create_name_edit_text.getText().toString();
                    if ( create_person_num_edit_text.getText().toString().length() == 0 ) {
                        //인원이 공백일 때 처리할 내용
                        Toast.makeText(getActivity(),"인원을 입력해주세요",Toast.LENGTH_SHORT).show();
                    } else {
                        //인원이 공백이 아닐 때 처리할 내용
                        create_person_num_variable = Integer.parseInt(create_person_num_edit_text.getText().toString());
                        if ( create_for_friend_edit_text.getText().toString().length() == 0 ) {
                            //친구 메세지가 공백일 때 처리할 내용
                            Toast.makeText(getActivity(),"친구들에게 메세지를 전하세요",Toast.LENGTH_SHORT).show();
                        } else {
                            create_for_friend_variable = create_for_friend_edit_text.getText().toString();
                            //공백이 없을 때 처리할 내용
                            if(getDate_day() == 0){
                                Toast.makeText(getActivity(),"날짜를 선택해주세요",Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(getActivity(),create_name_variable + "," + create_person_num_variable + ","+
                                        create_for_friend_variable+"\n"+getDate_year()+ ","+ getDate_month()+","+getDate_day(),Toast.LENGTH_LONG).show();

                                FragmentManager fm = getParentFragmentManager();
                                fm.beginTransaction().remove(fm.findFragmentById(R.id.homeFragmentFrame)).commit();

                                setDate_day(0);
                                setDate_month(0);
                                setDate_year(0);

                                create_name_edit_text.setText(null);
                                create_person_num_edit_text.setText(null);
                                create_for_friend_edit_text.setText(null);

                            }
                        }
                    }
                }
            }
        });

        Button cancel_button = view.findViewById(R.id.create_cancel_btn);
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getParentFragmentManager();
                fm.beginTransaction().remove(fm.findFragmentById(R.id.homeFragmentFrame)).commit();
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

                        setDate_year(year);
                        setDate_month(month);
                        setDate_day(day);

                        date_day = day;

                        dateText.setText(date);
                    }
                }, today_Year, today_Month,today_Day);
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


    public int getDate_day() {
        return date_day;
    }

    public void setDate_day(int date_day) {
        this.date_day = date_day;
    }

    public int getDate_month() {
        return date_month;
    }

    public void setDate_month(int date_month) {
        this.date_month = date_month;
    }

    public int getDate_year() {
        return date_year;
    }

    public void setDate_year(int date_year) {
        this.date_year = date_year;
    }
}
