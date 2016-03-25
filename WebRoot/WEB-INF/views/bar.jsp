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
	单轴：
	<div id="bar0"  class="main000"></div>
	双轴：
	<div id="bar1"  class="main000"></div>
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
		require([ 'echarts', 'echarts/chart/bar' // 使用折线图就加载bar模块，按需加载
		], function(ec) {
		
			// 基于准备好的dom，初始化echarts图表
			var myChart0= ec.init(document.getElementById('bar0'));
			var option0 =
			<echarts:bar 
				title="短期预测数据对比曲线" 
				subtitle="短期预测数据对比曲线"
				xlist="${xlist}" 
				ylist="${ylist}" 
				xunitname="预测时间"
				yunitname="实际电量(MW)" 
				/>
			// 为echarts对象加载数据 
			myChart0.setOption(option0);
			
			
			// 基于准备好的dom，初始化echarts图表
			var myChart1 = ec.init(document.getElementById('bar1'));
			var option1 =
			<echarts:bar
				title="短期预测数据对比曲线" 
				subtitle="短期预测数据对比曲线"
				xlist="${xlist}" 
				ylist="${ylist}" 
				xunitname="预测时间"
				yunitname="实际电量(MW),实际总辐射(w/㎡)" 
				yloction="${yloction}"/>
			// 为echarts对象加载数据 
			myChart1.setOption(option1);
		});
	</script>