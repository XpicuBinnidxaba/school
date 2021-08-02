package com.laudextest.utils;

public class ResponseWrapper {
	private MessageConnectionStatus status;
	private Object data;
	private String messageStatus;
	
	public ResponseWrapper(){
		this.status = MessageConnectionStatus.TXERROR;
		this.data = null;
		this.messageStatus = "Transaction error";
	}

	public MessageConnectionStatus getStatus() {
		return status;
	}

	public void setStatus(MessageConnectionStatus status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessageStatus() {
		return messageStatus;
	}

	public void setMessageStatus(String messageStatus) {
		this.messageStatus = messageStatus;
	}
}
