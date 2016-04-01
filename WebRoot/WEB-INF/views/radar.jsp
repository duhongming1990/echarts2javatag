<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
	8方位：
	<div id="radar8"  class="main000"></div>
	16方位：
	<div id="radar16"  class="main000"></div>
	<script type="text/javascript">
		// 使用
		require([ 'echarts', 'echarts/chart/radar' // 使用玫瑰图就加载radar模块，按需加载
		], function(ec) {
			// 基于准备好的dom，初始化echarts图表
			var myChart = ec.init(document.getElementById('radar8'));
			var option8 =
			<echarts:radar 
				title="气象预测风向玫瑰图8方位" 
				subtitle="预测时间"
				legendList="${legendList}"
				typeNum="8"
				/>
			// 为echarts对象加载数据 
			myChart.setOption(option8);
			
			// 基于准备好的dom，初始化echarts图表
			var myChart = ec.init(document.getElementById('radar16'));
			var option16 =
			<echarts:radar 
				title="气象预测风向玫瑰图16方位" 
				subtitle="预测时间"
				legendList="${legendList}"
				typeNum="16"
				/>
			// 为echarts对象加载数据 
			myChart.setOption(option16);
		});
	</script>