package com.hrhx.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName ="fund_info")
public class FundAnalysisBean {
	
	@DatabaseField(id = true,columnName = "ID")
	private Integer ID;
	
	@DatabaseField(columnName = "FUND_CODE")
	private String FUND_CODE;
	
	@DatabaseField(columnName = "FUND_NAME")
	private String FUND_NAME;

	@DatabaseField(columnName = "EVERY_THAN_ACCRUAL")
	private Double EVERY_THAN_ACCRUAL;
	
	@DatabaseField(columnName = "ANNUALIZED_YIELD7")
	private Double ANNUALIZED_YIELD7;
	
	@DatabaseField(columnName = "NETVALUE_DATE")
	private String NETVALUE_DATE;

	public FundAnalysisBean() {
		super();
	}

	public FundAnalysisBean(String fUND_CODE, String fUND_NAME, Double eVERY_THAN_ACCRUAL,
			Double aNNUALIZED_YIELD7, String nETVALUE_DATE) {
		super();
		FUND_CODE = fUND_CODE;
		FUND_NAME = fUND_NAME;
		EVERY_THAN_ACCRUAL = eVERY_THAN_ACCRUAL;
		ANNUALIZED_YIELD7 = aNNUALIZED_YIELD7;
		NETVALUE_DATE = nETVALUE_DATE;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getFUND_CODE() {
		return FUND_CODE;
	}

	public void setFUND_CODE(String fUND_CODE) {
		FUND_CODE = fUND_CODE;
	}

	public String getFUND_NAME() {
		return FUND_NAME;
	}

	public void setFUND_NAME(String fUND_NAME) {
		FUND_NAME = fUND_NAME;
	}

	public Double getEVERY_THAN_ACCRUAL() {
		return EVERY_THAN_ACCRUAL;
	}

	public void setEVERY_THAN_ACCRUAL(Double eVERY_THAN_ACCRUAL) {
		EVERY_THAN_ACCRUAL = eVERY_THAN_ACCRUAL;
	}

	public Double getANNUALIZED_YIELD7() {
		return ANNUALIZED_YIELD7;
	}

	public void setANNUALIZED_YIELD7(Double aNNUALIZED_YIELD7) {
		ANNUALIZED_YIELD7 = aNNUALIZED_YIELD7;
	}

	public String getNETVALUE_DATE() {
		return NETVALUE_DATE;
	}

	public void setNETVALUE_DATE(String nETVALUE_DATE) {
		NETVALUE_DATE = nETVALUE_DATE;
	}

	
	
	
}
