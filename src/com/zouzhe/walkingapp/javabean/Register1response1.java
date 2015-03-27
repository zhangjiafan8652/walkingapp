package com.zouzhe.walkingapp.javabean;

import java.io.Serializable;


public class Register1response1 implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6719879951600463810L;
	private String status;
	private Result result;

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

	public class Result implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = -7608421986506100638L;
		String code_msg;
		String driver_id;
		public String getcode_msg() {
			return code_msg;
		}
		public void setcode_msg(String code_msg) {
			this.code_msg = code_msg;
		}
		public String getDriver_id() {
			return driver_id;
		}
		public void setDriver_id(String driver_id) {
			this.driver_id = driver_id;
		}
		
	}
}
