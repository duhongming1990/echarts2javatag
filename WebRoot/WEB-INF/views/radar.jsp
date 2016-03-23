<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>

	<style type="text/css">
	    .main000 {
	        height: 360px;
	        /*width: 778px !important;*/
	        overflow: hidden;
	        padding : 10px;
	        margin-bottom: 10px;
	        border: 1px solid #e3e3e3;
	        -webkit-border-radius: 4px;
	           -moz-border-radius: 4px;
	                border-radius: 4px;
	        -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.05);
	           -moz-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.05);
	                box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.05);
	    }
	</style>
	8方位：
	<div id="radar8"  class="main000"></div>
	16方位：
	<div id="radar16"  class="main000"></div>
	<!-- ECharts单文件引入 -->
	<script src="<%=basePath%>/styles/echarts-2.2.7/build/dist/echarts.js"></script>
	<script type="text/javascript">
		// 路径配置
		require.config({
			paths : {
				echarts : '<%=basePath%>/styles/echarts-2.2.7/build/dist'
			} 
		});
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