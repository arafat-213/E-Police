package com.svit.epolice.utilities;

import java.util.ArrayList;
import java.util.Collections;

public class SpinnerData {
    ArrayList<String> stationList;
    ArrayList<String> notificationTypesList;
    ArrayList<String> designationList;

    public SpinnerData() {
        stationList = new ArrayList<>();
        designationList = new ArrayList<>();
    }

    public ArrayList<String> getStationList() {
        stationList.add("Old city");
        stationList.add("Sayajigunj");
        stationList.add("Akota");
        stationList.add("Manjalpur");
        stationList.add("Raopura");
        stationList.add("Waghodia Road");
        stationList.add("Karelibaug");
        stationList.add("Gotri");
        stationList.add("Vaasna");
        stationList.add("Tarsali");
        stationList.add("Ajwa Road");
        stationList.add("Harni");
        stationList.add("Tandalja");
        stationList.add("OP Road");
        Collections.sort(stationList);
        return stationList;
    }

    public void setStationList(ArrayList<String> stationList) {
        this.stationList = stationList;
    }

    public ArrayList<String> getNotificationTypesList() {
        notificationTypesList.add("Emergeny");
        notificationTypesList.add("Informative");
        notificationTypesList.add("Alert");
        notificationTypesList.add("Greetings");
        notificationTypesList.add("Missing person");
        notificationTypesList.add("Wanted person");
        notificationTypesList.add("City news");
        return notificationTypesList;
    }

    public void setNotificationTypesList(ArrayList<String> notificationTypesList) {
        this.notificationTypesList = notificationTypesList;
    }

    public ArrayList<String> getDesignationList() {
        designationList.add("Commissioner of police");
        designationList.add("ACP");
        designationList.add("DCP");
        designationList.add("Police Inspector");
        designationList.add("Police Sub-Inspector");
        designationList.add("Police Constable");
        return designationList;
    }

    public void setDesignationList(ArrayList<String> designationList) {
        this.designationList = designationList;
    }
}
