package com.example.climate1;

public class Arraydata {
   String time;

    String wind;
    String icon;

    public Arraydata(String time, String wind, String icon) {
        this.time = time;
        this.wind = wind;
        this.icon = icon;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
