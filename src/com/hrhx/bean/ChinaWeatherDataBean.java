package com.hrhx.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName ="line_weather_main_city")
public class ChinaWeatherDataBean {
	@DatabaseField(columnName = "datestr")
	private String datestr;
	
	@DatabaseField(columnName = "beijing_maxtemp")
	private double beijing_maxtemp;
	@DatabaseField(columnName = "beijing_mintemp")
	private double beijing_mintemp;
	
	@DatabaseField(columnName = "changchun_maxtemp")
	private double changchun_maxtemp;
	@DatabaseField(columnName = "changchun_mintemp")
	private double changchun_mintemp;
	
	@DatabaseField(columnName = "shenyang_maxtemp")
	private double shenyang_maxtemp;
	@DatabaseField(columnName = "shenyang_mintemp")
	private double shenyang_mintemp;
	
	@DatabaseField(columnName = "haerbin_maxtemp")
	private double haerbin_maxtemp;
	@DatabaseField(columnName = "haerbin_mintemp")
	private double haerbin_mintemp;
	
	public ChinaWeatherDataBean() {
		super();
	}
	
	public ChinaWeatherDataBean(String datestr, double beijing_maxtemp,
			double beijing_mintemp, double changchun_maxtemp,
			double changchun_mintemp, double shenyang_maxtemp,
			double shenyang_mintemp, double haerbin_maxtemp,
			double haerbin_mintemp) {
		super();
		this.datestr = datestr;
		this.beijing_maxtemp = beijing_maxtemp;
		this.beijing_mintemp = beijing_mintemp;
		this.changchun_maxtemp = changchun_maxtemp;
		this.changchun_mintemp = changchun_mintemp;
		this.shenyang_maxtemp = shenyang_maxtemp;
		this.shenyang_mintemp = shenyang_mintemp;
		this.haerbin_maxtemp = haerbin_maxtemp;
		this.haerbin_mintemp = haerbin_mintemp;
	}


	public String getDatestr() {
		return datestr;
	}

	public void setDatestr(String datestr) {
		this.datestr = datestr;
	}

	public double getBeijing_maxtemp() {
		return beijing_maxtemp;
	}

	public void setBeijing_maxtemp(double beijing_maxtemp) {
		this.beijing_maxtemp = beijing_maxtemp;
	}

	public double getBeijing_mintemp() {
		return beijing_mintemp;
	}

	public void setBeijing_mintemp(double beijing_mintemp) {
		this.beijing_mintemp = beijing_mintemp;
	}

	public double getChangchun_maxtemp() {
		return changchun_maxtemp;
	}

	public void setChangchun_maxtemp(double changchun_maxtemp) {
		this.changchun_maxtemp = changchun_maxtemp;
	}

	public double getChangchun_mintemp() {
		return changchun_mintemp;
	}

	public void setChangchun_mintemp(double changchun_mintemp) {
		this.changchun_mintemp = changchun_mintemp;
	}

	public double getShenyang_maxtemp() {
		return shenyang_maxtemp;
	}

	public void setShenyang_maxtemp(double shenyang_maxtemp) {
		this.shenyang_maxtemp = shenyang_maxtemp;
	}

	public double getShenyang_mintemp() {
		return shenyang_mintemp;
	}

	public void setShenyang_mintemp(double shenyang_mintemp) {
		this.shenyang_mintemp = shenyang_mintemp;
	}

	public double getHaerbin_maxtemp() {
		return haerbin_maxtemp;
	}

	public void setHaerbin_maxtemp(double haerbin_maxtemp) {
		this.haerbin_maxtemp = haerbin_maxtemp;
	}

	public double getHaerbin_mintemp() {
		return haerbin_mintemp;
	}

	public void setHaerbin_mintemp(double haerbin_mintemp) {
		this.haerbin_mintemp = haerbin_mintemp;
	}

}
