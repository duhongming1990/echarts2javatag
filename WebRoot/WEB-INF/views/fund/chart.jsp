<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>

<a href="<%=basePath %>/fund/updateData">更新数据</a>

<div id="T"  class="main000"></div>
<echarts:line 
    id="T"
	title="基金决策-天弘余额宝货币" 
	subtitle=""
	xAxisData="${xAxisDataT}" 
	yAxisData="${yAxisDataT}" 
	xAxisName="净值日期"
	yAxisName="每万份收益,7日年化收益率（%）" 
	yAxisIndex="${yAxisIndexT}"
	itemStyleShow="true"/>
	
<div id="Y"  class="main000"></div>
<echarts:line 
    id="Y"
	title="基金决策-易方达易货币" 
	subtitle=""
	xAxisData="${xAxisDataY}" 
	yAxisData="${yAxisDataY}" 
	xAxisName="净值日期"
	yAxisName="每万份收益,7日年化收益率（%）" 
	yAxisIndex="${yAxisIndexY}"
	itemStyleShow="true"/>