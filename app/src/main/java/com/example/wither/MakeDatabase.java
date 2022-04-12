package com.example.wither;

import android.os.Parcel;
import android.os.Parcelable;

import com.naver.maps.map.overlay.InfoWindow;
import com.naver.maps.map.overlay.Marker;

import java.io.Serializable;
import java.util.ArrayList;

public class MakeDatabase implements Parcelable {
    // 9개 정보
    private double latitude;
    private double longitude;
    private String meeting_name;
    private String meeting_category;
    private int meeting_person;
    private int year;
    private int month;
    private int day;
    private String text_for_meeting_frient;
    private int resourceID;

    // marker 객체 저장
    Marker marker;
    InfoWindow infoWindow;

    MakeDatabase(){

    }

    protected MakeDatabase(Parcel in) {
        latitude = in.readDouble();
        longitude = in.readDouble();
        meeting_name = in.readString();
        meeting_category = in.readString();
        meeting_person = in.readInt();
        year = in.readInt();
        month = in.readInt();
        day = in.readInt();
        text_for_meeting_frient = in.readString();
        resourceID = in.readInt();
    }

    public static final Creator<MakeDatabase> CREATOR = new Creator<MakeDatabase>() {
        @Override
        public MakeDatabase createFromParcel(Parcel in) {
            return new MakeDatabase(in);
        }

        @Override
        public MakeDatabase[] newArray(int size) {
            return new MakeDatabase[size];
        }
    };

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getMeeting_name() {
        return meeting_name;
    }

    public void setMeeting_name(String meeting_name) {
        this.meeting_name = meeting_name;
    }

    public String getMeeting_category() {
        return meeting_category;
    }

    public void setMeeting_category(String meeting_category) {
        this.meeting_category = meeting_category;
    }

    public int getMeeting_person() {
        return meeting_person;
    }

    public void setMeeting_person(int meeting_person) {
        this.meeting_person = meeting_person;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getText_for_meeting_frient() {
        return text_for_meeting_frient;
    }

    public void setText_for_meeting_frient(String text_for_meeting_frient) {
        this.text_for_meeting_frient = text_for_meeting_frient;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public int getResourceID() {
        return resourceID;
    }

    public void setResourceID(int resourceID) {
        this.resourceID = resourceID;
    }

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    public InfoWindow getInfoWindow() {
        return infoWindow;
    }

    public void setInfoWindow(InfoWindow infoWindow) {
        this.infoWindow = infoWindow;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(latitude);
        parcel.writeDouble(longitude);
        parcel.writeString(meeting_name);
        parcel.writeString(meeting_category);
        parcel.writeInt(meeting_person);
        parcel.writeInt(year);
        parcel.writeInt(month);
        parcel.writeInt(day);
        parcel.writeString(text_for_meeting_frient);
        parcel.writeInt(resourceID);
    }

    public int setMarkerIcon(String meeting_category){
        if(meeting_category == "축구"){
            return(R.drawable.category_ic_football);
        }else if(meeting_category == "넷플릭스"){
            return(R.drawable.category_ic_netflix);
        }else if(meeting_category == "담배"){
            return(R.drawable.category_ic_cigarette);
        }else if(meeting_category == "공부"){
            return(R.drawable.category_ic_study);
        }else if(meeting_category == "야구"){
            return(R.drawable.category_ic_baseball);
        }else if(meeting_category == "카페"){
            return(R.drawable.category_ic_cafe);
        }else if(meeting_category == "영화"){
            return(R.drawable.category_ic_movie);
        }else if(meeting_category == "여행"){
            return(R.drawable.category_ic_travel);
        }

        return 0;
    }
}