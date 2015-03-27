package com.zouzhe.walkingapp.javabean;

import java.io.Serializable;

public class Driverbean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 637624024775572809L;
	// 司机姓名
	private String name;
	// 司机id
	private String driver_id;
	// 民族
	private String nation;

	public String getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(String driver_id) {
		this.driver_id = driver_id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getdriver_id() {
		return driver_id;
	}

	public void setdriver_id(String drierid) {
		this.driver_id = drierid;
	}

	// 地理位置
	private String position;
	// 驾龄
	private String vehicle_age;
	// 车型
	private String vehicle;
	// 车型号
	private String vehicle_model;
	// 可坐人数
	private String passenger_number;
	// 价格
	private String price;
	// 说明
	private String description;
	// 车牌号
	private String license_plate_number;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getVehicle_age() {
		return vehicle_age;
	}

	public void setVehicle_age(String vehicle_age) {
		this.vehicle_age = vehicle_age;
	}

	public String getVehicle() {
		return vehicle;
	}

	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}

	public String getVehicle_model() {
		return vehicle_model;
	}

	public void setVehicle_model(String vehicle_model) {
		this.vehicle_model = vehicle_model;
	}

	public String getPassenger_number() {
		return passenger_number;
	}

	public void setPassenger_number(String passenger_number) {
		this.passenger_number = passenger_number;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLicense_plate_number() {
		return license_plate_number;
	}

	public void setLicense_plate_number(String license_plate_number) {
		this.license_plate_number = license_plate_number;
	}

	@Override
	public String toString() {
		return "Driverbean [name=" + name + ", driver_id=" + driver_id
				+ ", nation=" + nation + ", position=" + position
				+ ", vehicle_age=" + vehicle_age + ", vehicle=" + vehicle
				+ ", vehicle_model=" + vehicle_model + ", passenger_number="
				+ passenger_number + ", price=" + price + ", description="
				+ description + ", license_plate_number="
				+ license_plate_number + "]";
	}

}
