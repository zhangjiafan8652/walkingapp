package com.zouzhe.walkingapp.javabean;

import java.io.Serializable;
import java.util.List;

public class Cartyperesponse implements Serializable {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 6621012335119431477L;

	private String status;
	
	private List<CarType> result;
	
	public class CarType implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 864190360587887966L;
		private String status;
		private String code;
		private String name;
		private String luggage;
		private String passenger_number;
		private String _id;
		
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getLuggage() {
			return luggage;
		}
		public void setLuggage(String luggage) {
			this.luggage = luggage;
		}
		public String getPassenger_number() {
			return passenger_number;
		}
		public void setPassenger_number(String passenger_number) {
			this.passenger_number = passenger_number;
		}
		public String get_id() {
			return _id;
		}
		public void set_id(String _id) {
			this._id = _id;
		}
		@Override
		public String toString() {
			return "carType [status=" + status + ", code=" + code + ", name="
					+ name + ", luggage=" + luggage + ", passenger_number="
					+ passenger_number + ", _id=" + _id + "]";
		}
		
		
		
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<CarType> getResult() {
		return result;
	}

	public void setResult(List<CarType> result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "Cartyperesponse [status=" + status + ", result=" + result + "]";
	}
	
	
	
}
