package com.hrhx.bean;

public class SocketDataLine {
	
	private String tag;
	private Double value1;
	private Double value2;
	
	
	public SocketDataLine() {
		super();
	}


	public SocketDataLine(String tag, Double value1, Double value2) {
		super();
		this.tag = tag;
		this.value1 = value1;
		this.value2 = value2;
	}


	public String getTag() {
		return tag;
	}


	public void setTag(String tag) {
		this.tag = tag;
	}


	public Double getValue1() {
		return value1;
	}


	public void setValue1(Double value1) {
		this.value1 = value1;
	}


	public Double getValue2() {
		return value2;
	}


	public void setValue2(Double value2) {
		this.value2 = value2;
	}
	
	
}
