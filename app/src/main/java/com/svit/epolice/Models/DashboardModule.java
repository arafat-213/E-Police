package com.svit.epolice.Models;

public class DashboardModule {
    private String name;
    private int icon;

    public DashboardModule(String name, int icon) {
        this.name = name;
        this.icon = icon;
    }

    public DashboardModule() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
