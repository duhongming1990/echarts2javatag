<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
	单轴：
	<div id="line_normal"  class="main000"></div>
	<echarts:barReverse 
	  	id="line_normal"
		title="短期预测数据对比曲线" 
		subtitle="短期预测数据对比曲线"
		xAxisData="${xAxisData}" 
		yAxisData="${yAxisData}" 
		xAxisName="预测时间"
		yAxisName="实际电量(MW)"
		itemStyleShow="true" 
		/>