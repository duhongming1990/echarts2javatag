<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
	双数值轴折线:
	<div id="line_doubleNum"  class="main000"></div>
	<echarts:lineDoubleNum 
	    id="line_doubleNum"
		title="双数值轴折线" 
		subtitle="短期预测数据对比曲线"
		xAxisName="预测时间"
		yAxisName="实际电量(MW)" 
		axisDataArr="${axisDataArr}"/>