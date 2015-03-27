package com.zouzhe.walkingapp.javabean;

import java.io.Serializable;

public class Driverdetialbean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7470463518674924812L;
	//司机姓名
	String name;
	//简介
	String introduction;
	//常在地
	String main_cities;
	//驾龄
	String driver_years;
	
	String nation;
	//头像
	String avatar;
	//车型号
	String vehicle_mode_id;
	//车型
	String vehicle_model;
	//价格
	String price;
	//车龄
	String vehicle_years;
	//载客数
	String passenger_number;
	//建议行李数
	String bag_number;
	//车辆图片
	String vehicle_photo_url;
	//车辆行驶证
	String vehicle_driving;
	//驾驶证
	String driver_license;
	//运营证
	String operating_certificate;
	//车牌号
	String license_plate_number;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getintroduction() {
		return introduction;
	}
	public void setintroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getDriver_years() {
		return driver_years;
	}
	public void setDriver_years(String driver_years) {
		this.driver_years = driver_years;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getVehicle_mode_id() {
		return vehicle_mode_id;
	}
	public void setVehicle_mode_id(String vehicle_mode_id) {
		this.vehicle_mode_id = vehicle_mode_id;
	}
	public String getVehicle_model() {
		return vehicle_model;
	}
	public void setVehicle_model(String vehicle_model) {
		this.vehicle_model = vehicle_model;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getVehicle_years() {
		return vehicle_years;
	}
	public void setVehicle_years(String vehicle_years) {
		this.vehicle_years = vehicle_years;
	}
	public String getPassenger_number() {
		return passenger_number;
	}
	public void setPassenger_number(String passenger_number) {
		this.passenger_number = passenger_number;
	}
	public String getbag_number() {
		return bag_number;
	}
	public void setbag_number(String bag_number) {
		this.bag_number = bag_number;
	}
	public String getvehicle_photo_url() {
		return vehicle_photo_url;
	}
	public void setvehicle_photo_url(String vehicle_photo_url) {
		this.vehicle_photo_url= vehicle_photo_url;
	}
	public String getVehicle_driving() {
		return vehicle_driving;
	}
	public void setVehicle_driving(String vehicle_driving) {
		this.vehicle_driving = vehicle_driving;
	}
	public String getDriver_license() {
		return driver_license;
	}
	public void setDriver_license(String driver_license) {
		this.driver_license = driver_license;
	}
	public String getOperating_certificate() {
		return operating_certificate;
	}
	public void setOperating_certificate(String operating_certificate) {
		this.operating_certificate = operating_certificate;
	}
	public String getLicense_plate_number() {
		return license_plate_number;
	}
	public void setLicense_plate_number(String license_plate_number) {
		this.license_plate_number = license_plate_number;
	}
	
	public String getMain_cities() {
		return main_cities;
	}
	public void setMain_cities(String main_cities) {
		this.main_cities = main_cities;
	}
	@Override
	public String toString() {
		return "Driverdetialbean [name=" + name + ", introduction="
				+ introduction + ", driver_years=" + driver_years + ", nation="
				+ nation + ", avatar=" + avatar + ", vehicle_mode_id="
				+ vehicle_mode_id + ", vehicle_model=" + vehicle_model
				+ ", price=" + price + ", vehicle_years=" + vehicle_years
				+ ", passenger_number=" + passenger_number + ", bag_number="
				+ bag_number + ", vehicle_photo_url=" + vehicle_photo_url
				+ ", vehicle_driving=" + vehicle_driving + ", driver_license="
				+ driver_license + ", operating_certificate="
				+ operating_certificate + ", license_plate_number="
				+ license_plate_number + "]";
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getBag_number() {
		return bag_number;
	}
	public void setBag_number(String bag_number) {
		this.bag_number = bag_number;
	}
	public String getVehicle_photo_url() {
		return vehicle_photo_url;
	}
	public void setVehicle_photo_url(String vehicle_photo_url) {
		this.vehicle_photo_url = vehicle_photo_url;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
