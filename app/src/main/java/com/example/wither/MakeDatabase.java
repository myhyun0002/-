package com.example.wither;

public class MakeDatabase {
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

//    MakeDatabase(double latitude,
//            double longitude,
//            String meeting_name,
//            String meeting_category,
//            int meeting_person,
//            int year,
//            int month,
//            int day,
//            String text_for_meeting_frient){
//        setMeeting_name(meeting_name);
//        setLongitude(longitude);
//        setLatitude(latitude);
//        setText_for_meeting_frient(text_for_meeting_frient);
//        setYear(year);
//        setMonth(month);
//        setDay(day);
//        setMeeting_person(meeting_person);
//        setMeeting_category(meeting_category);
//    }

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
}
