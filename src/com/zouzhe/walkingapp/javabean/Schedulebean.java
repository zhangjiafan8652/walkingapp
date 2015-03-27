package com.zouzhe.walkingapp.javabean;

import java.io.Serializable;


public class Schedulebean extends  Basebean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2742996341130875658L;


	String driver_id;
	String date;
	String schedule_id;
	String schedule_status;
	
	public String getDriver_id() {
		return driver_id;
	}
	public void setDriver_id(String driver_id) {
		this.driver_id = driver_id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSchedule_id() {
		return schedule_id;
	}
	public void setSchedule_id(String schedule_id) {
		this.schedule_id = schedule_id;
	}
	public String getSchedule_status() {
		return schedule_status;
	}
	public void setSchedule_status(String schedule_status) {
		this.schedule_status = schedule_status;
	}
	@Override
	public String toString() {
		return "Schedulebean [driver_id=" + driver_id + ", date=" + date
				+ ", schedule_id=" + schedule_id + ", schedule_status="
				+ schedule_status + "]";
	}

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }


}
