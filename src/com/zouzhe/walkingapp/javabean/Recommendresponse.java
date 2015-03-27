package com.zouzhe.walkingapp.javabean;

import java.io.Serializable;


public class Recommendresponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6367324527459137392L;
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
	
	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public static class Result implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 7208603609678074405L;
		String code_msg;
		public String getcode_msg() {
			return code_msg;
		}
		public void setcode_msg(String code_msg) {
			this.code_msg = code_msg;
		}
		public String getCode_msg() {
			return code_msg;
		}
		public void setCode_msg(String code_msg) {
			this.code_msg = code_msg;
		}
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		
		
		
		
	}
	
	
}
