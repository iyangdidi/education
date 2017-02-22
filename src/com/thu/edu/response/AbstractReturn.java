package com.thu.edu.response;

/** 请求返回的基类 */
public abstract class AbstractReturn {

	protected int status;
	protected String reason;
	
	

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}
