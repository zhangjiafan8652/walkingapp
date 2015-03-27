package com.zouzhe.walkingapp.javabean;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/3/18.
 */
public class Driver_routebean implements Serializable{


    //司机id
    private String driver_id;
    //路线标题
    private String route_title;
    //路线标题描述
    private String route_titledescrible;
    //路线
    private String route;
    //价钱没人
    private String price;
    //一共的天数
    private String days;

    public String getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
    }

    public String getRoute_title() {
        return route_title;
    }

    public void setRoute_title(String route_title) {
        this.route_title = route_title;
    }

    public String getRoute_titledescrible() {
        return route_titledescrible;
    }

    public void setRoute_titledescrible(String route_titledescrible) {
        this.route_titledescrible = route_titledescrible;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    @Override
    public String toString() {
        return "driver_routebean{" +
                "driver_id='" + driver_id + '\'' +
                ", route_title='" + route_title + '\'' +
                ", route_titledescrible='" + route_titledescrible + '\'' +
                ", route='" + route + '\'' +
                ", price='" + price + '\'' +
                ", days='" + days + '\'' +
                '}';
    }
}
