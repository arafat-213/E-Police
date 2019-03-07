package com.svit.epolice.Models;

import java.io.Serializable;

public class Notification implements Serializable {
    private String notificationType;
    private String notificationContent;
    private String notificationImage;

    public Notification() {
    }

    public Notification(String notificationType, String notificationContent) {
        this.notificationType = notificationType;
        this.notificationContent = notificationContent;
    }

    public Notification(String notificationType, String notificationContent, String notificationImage) {
        this.notificationType = notificationType;
        this.notificationContent = notificationContent;
        this.notificationImage = notificationImage;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public String getNotificationContent() {
        return notificationContent;
    }

    public void setNotificationContent(String notificationContent) {
        this.notificationContent = notificationContent;
    }

    public String getNotificationImage() {
        return notificationImage;
    }

    public void setNotificationImage(String notificationImage) {
        this.notificationImage = notificationImage;
    }

    @Override
    public String toString() {
        return "com.example.arafat_213.e_policephase2.Models.Notification{" +
                "notificationType='" + notificationType + '\'' +
                ", notificationContent='" + notificationContent + '\'' +
                ", notificationImage='" + notificationImage + '\'' +
                '}';
    }
}
