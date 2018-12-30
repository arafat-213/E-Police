package com.svit.epolice.Models;

public class User {

    private String name;

    private String mobileNumber;

    private String email;

    private String profile_pic_url;

    private String aadhar_no;

    private String dob;

    public User() {
    }

    public User(String name, String mobileNumber, String email, String profile_pic_url, String aadhar_no, String dob) {
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.profile_pic_url = profile_pic_url;
        this.aadhar_no = aadhar_no;
        this.dob = dob;
    }

    public User(String name, String mobileNumber, String email) {
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfile_pic_url() {
        return profile_pic_url;
    }

    public void setProfile_pic_url(String profile_pic_url) {
        this.profile_pic_url = profile_pic_url;
    }

    public String getAadhar_no() {
        return aadhar_no;
    }

    public void setAadhar_no(String aadhar_no) {
        this.aadhar_no = aadhar_no;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", email='" + email + '\'' +
                ", profile_pic_url='" + profile_pic_url + '\'' +
                ", aadhar_no='" + aadhar_no + '\'' +
                ", dob='" + dob + '\'' +
                '}';
    }
}
