<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
	双数值轴折线:
	<div id="line0"  class="main000"></div>
	<script type="text/javascript">
		// 使用
		require([ 'echarts', 'echarts/chart/line' // 使用折线图就加载bar模块，按需加载
		], function(ec) {
		
			// 基于准备好的dom，初始化echarts图表
			var myChart0= ec.init(document.getElementById('line0'));
			var option0 =
			<echarts:numline 
				title="双数值轴折线" 
				subtitle="短期预测数据对比曲线"
				ylist="${ylist}" 
				xunitname="预测时间"
				yunitname="实际电量(MW)" 
				/>
			// 为echarts对象加载数据 
			myChart0.setOption(option0);
			
		});
	</script>