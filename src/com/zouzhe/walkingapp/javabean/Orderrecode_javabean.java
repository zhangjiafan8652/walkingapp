package com.zouzhe.walkingapp.javabean;



import java.io.Serializable;

/**
 * Created by Administrator on 2015/3/3.
 */
public class Orderrecode_javabean  implements Serializable {


    private String id;
    //标题
    private String title;
    //标题描述
    private String titledescrible;
    //线路
    private String route;
    //开始时间
    private String start_date;
    //结束时间
    private String end_date;
    //司机id
    private String driver_id;
    //订单id
    private String order_id;
    //总价
    private String total_price;
    //联系人
    private String customer;
    //联系人电话号码
    private String customer_phone;
    //乘客数
    private String passengers;

    //共多少天
    private String days;

    //车类型
    private String car_type;

    public String getCar_type() {
        return car_type;
    }

    public void setCar_type(String car_type) {
        this.car_type = car_type;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getPassengers() {
        return passengers;
    }

    public void setPassengers(String passengers) {
        this.passengers = passengers;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitledescrible() {
        return titledescrible;
    }
    public void setTitledescrible(String titledescrible) {
        this.titledescrible = titledescrible;
    }
    public String getRoute() {
        return route;
    }
    public void setRoute(String route) {
        this.route = route;
    }
    public String getStart_date() {
        return start_date;
    }
    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }
    public String getEnd_date() {
        return end_date;
    }
    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }
    public String getDriver_id() {
        return driver_id;
    }
    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
    }
    public String getOrder_id() {
        return order_id;
    }
    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }
    public String getTotal_price() {
        return total_price;
    }
    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }
    public String getCustomer() {
        return customer;
    }
    public void setCustomer(String customer) {
        this.customer = customer;
    }
    public String getCustomer_phone() {
        return customer_phone;
    }
    public void setCustomer_phone(String customer_phone) {
        this.customer_phone = customer_phone;
    }

    @Override
    public String toString() {
        return "Orderrecode_javabean{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", titledescrible='" + titledescrible + '\'' +
                ", route='" + route + '\'' +
                ", start_date='" + start_date + '\'' +
                ", end_date='" + end_date + '\'' +
                ", driver_id='" + driver_id + '\'' +
                ", order_id='" + order_id + '\'' +
                ", total_price='" + total_price + '\'' +
                ", customer='" + customer + '\'' +
                ", customer_phone='" + customer_phone + '\'' +
                ", passengers='" + passengers + '\'' +
                ", days='" + days + '\'' +
                ", car_type='" + car_type + '\'' +
                '}';
    }
}
