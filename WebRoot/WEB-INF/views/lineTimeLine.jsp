<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
	单轴：
	<div id=line_normal  class="main000"></div>
	<echarts:lineTimeLine
	    id="line_normal"
		title="2000年短期预测数据对比曲线" 
		subtitle="数据来自国家统计局"
		xAxisName="预测时间"
		yAxisName="实际电量(MW)" 
		xAxisData="${xAxisData}" 
		yAxisData="${yAxisData}" 
		timelineData="${timelineData}"
		timelineAxisData="${timelineAxisData}"
		/>
	双轴：
	<div id="line_yAxisIndex"  class="main000"></div>
	<echarts:lineTimeLine
		id="line_yAxisIndex"
		title="2000年短期预测数据对比曲线" 
		subtitle="数据来自国家统计局"
		xAxisName="预测时间"
		yAxisName="实际电量(MW),实际总辐射(w/㎡)" 
		xAxisData="${xAxisData}" 
		yAxisData="${yAxisData}"
		timelineData="${timelineData}"
		timelineAxisData="${timelineAxisData}"
		yAxisIndex="${yAxisIndex}"/>