package com.hrhx.bean;

public class SocketDataGauge {
	
	private String tag;
	private Double value;
	
	
	public SocketDataGauge() {
		super();
	}
	
	public SocketDataGauge(String tag, Double value) {
		super();
		this.tag = tag;
		this.value = value;
	}
	
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	
	
	
}
