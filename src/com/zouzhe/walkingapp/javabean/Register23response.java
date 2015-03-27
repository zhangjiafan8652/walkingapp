package com.zouzhe.walkingapp.javabean;

import java.io.Serializable;


public class Register23response implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6524924163788271953L;
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
		private static final long serialVersionUID = -8388942279962102360L;
		String code_msg;
		public String getcode_msg() {
			return code_msg;
		}
		public void setcode_msg(String code_msg) {
			this.code_msg = code_msg;
		}
		
	}
}
