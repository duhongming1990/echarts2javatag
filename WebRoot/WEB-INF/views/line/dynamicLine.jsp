<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
	<head>
		<%@ include file="/WEB-INF/views/include.inc.jsp"%>
		<script type="text/javascript">
			$(document).ready(function(){ 
	            
	         });
		</script>
	</head>
<body>
	单轴：
	<div id="line_normal"  class="main000"></div>
	
	<script type="text/javascript">         
        // 基于准备好的dom，初始化echarts图表
        var myChart = echarts.init(document.getElementById('line_normal'),'macarons'); 
        var option =<echarts:dynamicLine 
	        id="line_normal"
			title="2011年温度对比曲线" 
			subtitle="主要城市的温度对比曲线"
			xAxisData="${xAxisData}" 
			yAxisData="${yAxisData}" 
			xAxisName="预测时间"
			yAxisName="最高温度(℃),最低温度(℃)"
			itemStyleShow="true" /> ;
   
        // 为echarts对象加载数据 
        myChart.setOption(option);
              
	    var uri = "/echarts2javatag/webSocket/line/data";
           var ws = new SockJS(uri);  
           ws.onopen = function () {
               //console.log('Info: connection opened.');  
           };  
           ws.onmessage = function (event) { 
           	var json=eval("("+event.data+")");//将数据转成json格式
           	// 动态数据接口 addData
		    myChart.addData([
		        [
		            0,        // 系列索引
		            json.value1, // 新增数据
		            true,     // 新增数据是否从队列头部插入
		            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
		        ],
		        [
		            1,        // 系列索引
		            json.value2, // 新增数据
		            false,    // 新增数据是否从队列头部插入
		            false,    // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
		            json.tag  // 坐标轴标签
		        ]
		    ]);
           	console.log('Received: ' + json); 
           };
           ws.onclose = function (event) {  
               console.log('Info: connection closed.');  
               console.log(event);  
           };	
    </script>

	双轴：
	<div id="line_yAxisIndex"  class="main000"></div>
	<script type="text/javascript">
           // 基于准备好的dom，初始化echarts图表
           var myChart = echarts.init(document.getElementById('line_yAxisIndex'),'macarons'); 
           var option =<echarts:dynamicLine
			    id="line_yAxisIndex"
				title="2011年温度对比曲线" 
				subtitle="主要城市的温度对比曲线"
				xAxisData="${xAxisData}" 
				yAxisData="${yAxisData}" 
				xAxisName="预测时间"
				yAxisName="最高温度(℃),最低温度(℃)" 
				yAxisIndex="${yAxisIndex}"/>;
		   
           // 为echarts对象加载数据 
           myChart.setOption(option); 
           
           var uri = "/echarts2javatag/webSocket/line/data";
           var ws = new SockJS(uri);  
           ws.onopen = function () {
               //console.log('Info: connection opened.');  
           };  
           ws.onmessage = function (event) { 
           	var json=eval("("+event.data+")");//将数据转成json格式
           	// 动态数据接口 addData
		    myChart.addData([
		        [
		            0,        // 系列索引
		            json.value1, // 新增数据
		            true,     // 新增数据是否从队列头部插入
		            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
		        ],
		        [
		            1,        // 系列索引
		            json.value2, // 新增数据
		            false,    // 新增数据是否从队列头部插入
		            false,    // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
		            json.tag  // 坐标轴标签
		        ]
		    ]);
           	console.log('Received: ' + json); 
           };
           ws.onclose = function (event) {  
               console.log('Info: connection closed.');  
               console.log(event);  
           };
    </script>
</body>	

</html>