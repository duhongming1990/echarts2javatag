<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
	<div id="pie"  class="main000"></div>
	<script type="text/javascript">
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