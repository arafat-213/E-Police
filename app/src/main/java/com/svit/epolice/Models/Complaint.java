package com.svit.epolice.Models;

import java.util.Arrays;

public class Complaint {
    private String[] media;
    private String area, address, description;
    private boolean isAnonymous;

    public Complaint() {
    }

    public Complaint(String[] media, String area, String address, String description, boolean isAnonymous) {
        this.media = media;
        this.area = area;
        this.address = address;
        this.description = description;
        this.isAnonymous = isAnonymous;
    }

    public String[] getMedia() {
        return media;
    }

    public void setMedia(String[] media) {
        this.media = media;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(boolean anonymous) {
        isAnonymous = anonymous;
    }

    @Override
    public String toString() {
        return "Complaint{" +
                "media=" + Arrays.toString(media) +
                ", area='" + area + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", isAnonymous=" + isAnonymous +
                '}';
    }
}
