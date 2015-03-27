package com.zouzhe.walkingapp.javabean;

import java.io.Serializable;



public class Register1response implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7685685006314544665L;
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
		private static final long serialVersionUID = -8223237048200299527L;
		String code_msg;
		String valid_code;
		public String getcode_msg() {
			return code_msg;
		}
		public void setcode_msg(String code_msg) {
			this.code_msg = code_msg;
		}
		public String getValid_code() {
			return valid_code;
		}
		public void setValid_code(String valid_code) {
			this.valid_code = valid_code;
		}
		
	
		
	}
}
