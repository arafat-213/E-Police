package com.svit.epolice.Models;

public class Policeman {
    String image_id;
    String name;
    float rating;
    String mobile_no;
    String email;
    String rank;
    String area;

    public Policeman(String image_id, String name, float rating, String mobile_no, String email, String rank, String area) {
        this.image_id = image_id;
        this.name = name;
        this.rating = rating;
        this.mobile_no = mobile_no;
        this.email = email;
        this.rank = rank;
        this.area = area;
    }

    public Policeman(String image_id, String name, String mobile_no, String email, String rank, String area) {
        this.image_id = image_id;
        this.name = name;
        this.mobile_no = mobile_no;
        this.email = email;
        this.rank = rank;
        this.area = area;
    }

    public Policeman() {
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
