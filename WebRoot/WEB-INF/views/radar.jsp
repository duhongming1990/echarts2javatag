<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
	8方位：
	<div id="radar8"  class="main000"></div>
	<echarts:radar
	    id="radar8"
		title="气象预测风向玫瑰图8方位" 
		subtitle="预测时间"
		orientData="${orientData}"
		polarType="8"/>
	16方位：
	<div id="radar16"  class="main000"></div>
	<echarts:radar 
		id="radar16"
		title="气象预测风向玫瑰图16方位" 
		subtitle="预测时间"
		orientData="${orientData}"
		polarType="16"/>
