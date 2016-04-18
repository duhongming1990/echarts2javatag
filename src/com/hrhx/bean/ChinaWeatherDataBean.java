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
	
	public ChinaWeatherDataBean() {
		super();
	}

	public ChinaWeatherDataBean(String datestr, Double beijing_maxtemp,
			Double beijing_mintemp, Double changchun_maxtemp,
			Double changchun_mintemp) {
		super();
		this.datestr = datestr;
		this.beijing_maxtemp = beijing_maxtemp;
		this.beijing_mintemp = beijing_mintemp;
		this.changchun_maxtemp = changchun_maxtemp;
		this.changchun_mintemp = changchun_mintemp;
	}

	public String getDatestr() {
		return datestr;
	}

	public void setDatestr(String datestr) {
		this.datestr = datestr;
	}

	public Double getBeijing_maxtemp() {
		return beijing_maxtemp;
	}

	public void setBeijing_maxtemp(Double beijing_maxtemp) {
		this.beijing_maxtemp = beijing_maxtemp;
	}

	public Double getBeijing_mintemp() {
		return beijing_mintemp;
	}

	public void setBeijing_mintemp(Double beijing_mintemp) {
		this.beijing_mintemp = beijing_mintemp;
	}

	public Double getChangchun_maxtemp() {
		return changchun_maxtemp;
	}

	public void setChangchun_maxtemp(Double changchun_maxtemp) {
		this.changchun_maxtemp = changchun_maxtemp;
	}

	public Double getChangchun_mintemp() {
		return changchun_mintemp;
	}

	public void setChangchun_mintemp(Double changchun_mintemp) {
		this.changchun_mintemp = changchun_mintemp;
	}

	

}
