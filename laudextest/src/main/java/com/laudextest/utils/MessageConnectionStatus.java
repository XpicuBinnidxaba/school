package com.laudextest.utils;

public enum MessageConnectionStatus {
	OK("Ok"),
	SUCCESS("Success"),
	ERROR("Error"),
	WARNING("Warning"),
	TXERROR("TransactionError");
    
    private String state;
     
    private MessageConnectionStatus(final String state){
        this.state = state;
    }
     
    public String getState(){
        return this.state;
    }
 
    @Override
    public String toString(){
        return this.state;
    }
 
    public String getName(){
        return this.name();
    }
}