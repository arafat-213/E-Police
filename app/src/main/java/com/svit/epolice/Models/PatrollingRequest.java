package com.svit.epolice.Models;

public class PatrollingRequest {
    private String fromDate, toDate, fullName, address, phoneNo, area;

    public PatrollingRequest(String fromDate, String toDate, String fullName, String address, String phoneNo, String area) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.fullName = fullName;
        this.address = address;
        this.phoneNo = phoneNo;
        this.area = area;
    }

    public PatrollingRequest() {

    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
