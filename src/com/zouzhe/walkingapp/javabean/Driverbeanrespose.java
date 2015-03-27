package com.zouzhe.walkingapp.javabean;

import java.io.Serializable;
import java.util.List;


public class Driverbeanrespose implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9108485475076271264L;
	private String status;
	private Driverdetialbean result;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Driverdetialbean getResult() {
		return result;
	}

	public void setResult(Driverdetialbean result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "Driverbeanrespose [status=" + status + ", result=" + result
				+ ", getStatus()=" + getStatus() + ", getResult()="
				+ getResult() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

	
}
