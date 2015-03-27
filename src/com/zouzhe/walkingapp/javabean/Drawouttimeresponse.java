package com.zouzhe.walkingapp.javabean;

import java.io.Serializable;
import java.util.LinkedList;



public class Drawouttimeresponse implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4384059722494403963L;
	private String status;
	private Result result;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}
	

	@Override
	public String toString() {
		return "Drawouttimeresponse [status=" + status + ", result=" + result
				+ ", getStatus()=" + getStatus() + ", getResult()="
				+ getResult() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}


	public class Result implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = -2688867834107060432L;
		String total;
		LinkedList<Schedulebean> schedule_list;

		public String getTotal() {
			return total;
		}

		public void setTotal(String total) {
			this.total = total;
		}

		public LinkedList<Schedulebean> getSchedule_list() {
			return schedule_list;
		}

		public void setSchedule_list(LinkedList<Schedulebean> schedule_list) {
			this.schedule_list = schedule_list;
		}

		@Override
		public String toString() {
			return "Result [total=" + total + ", schedule_list="
					+ schedule_list + ", getTotal()=" + getTotal()
					+ ", getSchedule_list()=" + getSchedule_list()
					+ ", getClass()=" + getClass() + ", hashCode()="
					+ hashCode() + ", toString()=" + super.toString() + "]";
		}
		
		

	}
}
