package com.svit.epolice.Models;

public class Feedback {
    private String policeStation;
    private String policeOfficer;
    private String rating;
    private String description;
    private String userName;
    private  String policemanID;



    public Feedback(String policeStation, String policeOfficer, String rating, String description, String userName, String policemanID) {
        this.policeStation = policeStation;
        this.policeOfficer = policeOfficer;
        this.rating = rating;
        this.description = description;
        this.userName = userName;
        this.policemanID = policemanID;
    }

    public Feedback() { }


    public String getPoliceStation() {
        return policeStation;
    }

    public void setPoliceStation(String policeStation) {
        this.policeStation = policeStation;
    }

    public String getPoliceOfficer() {
        return policeOfficer;
    }

    public void setPoliceOfficer(String policeOfficer) {
        this.policeOfficer = policeOfficer;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPolicemanID() { return policemanID; }

    public void setPolicemanID(String policemanID) { this.policemanID = policemanID; }

    @Override
    public String toString() {
        return "Feedback{" +
                "policeStation='" + policeStation + '\'' +
                ", policeOfficer='" + policeOfficer + '\'' +
                ", rating='" + rating + '\'' +
                ", description='" + description + '\'' +
                ", userName='" + userName + '\'' +
                ", policemanID='" + policemanID + '\'' +
                '}';
    }

}
