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
	<div id="pie"  class="main000"></div>
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
		require([ 'echarts', 'echarts/chart/pie' // 使用玫瑰图就加载radar模块，按需加载
		], function(ec) {
		
			// 基于准备好的dom，初始化echarts图表
			var myChart = ec.init(document.getElementById('pie'));
			var option =
			<echarts:pie
				title="某站点用户访问来源" 
				subtitle="纯属虚构"
				legendMap="${legendMap}"
				/>
			// 为echarts对象加载数据 
			myChart.setOption(option);
			
		});
	</script>