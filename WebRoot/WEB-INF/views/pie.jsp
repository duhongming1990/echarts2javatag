<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
	<div id="pie"  class="main000"></div>
	<echarts:pie
	    id="pie"
		title="某站点用户访问来源" 
		subtitle="纯属虚构"
		orientData="${orientData}"
		itemStyleShow="true"/>