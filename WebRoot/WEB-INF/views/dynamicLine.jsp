<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
	<head>
		<%@ include file="/WEB-INF/views/include.inc.jsp"%>
		<script type="text/javascript">
			$(document).ready(function(){ 
	            var uri = "/echarts2javatag/webSocket/data";
	            var ws = new SockJS(uri);  
	            ws.onopen = function () {
	                //console.log('Info: connection opened.');  
	            };  
	            ws.onmessage = function (event) { 
	            	var json=eval("("+event.data+")");//将数据转成json格式
	            	console.log('Received: ' + json); 
	            };
	            ws.onclose = function (event) {  
	                console.log('Info: connection closed.');  
	                console.log(event);  
	            };
	         });
		</script>
	</head>
<body>
	单轴：
	<div id="line_normal"  class="main000"></div>
	
	<script type="text/javascript">         
		require(
            [
                'echarts',
                'echarts/chart/line' // 使用柱状图就加载bar模块，按需加载
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('line_normal')); 
                myChart.setTheme('macarons');
                var option =<echarts:dynamicLine 
					        id="line_normal"
							title="本机剩余内存" 
							subtitle="本机剩余内存Lenovo"
							xAxisData="${xAxisData}" 
							yAxisData="${yAxisData}" 
							xAxisName="采样时间"
							yAxisName="内存量(KB)"
							itemStyleShow="true" /> ;
        
                // 为echarts对象加载数据 
                myChart.setOption(option);
			});
		
    </script>

	双轴：
	<div id="line_yAxisIndex"  class="main000"></div>
	<script type="text/javascript">
        // 使用
        require(
            [
                'echarts',
                'echarts/chart/line' // 使用柱状图就加载bar模块，按需加载
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('line_yAxisIndex')); 
                
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
            }
        );
    </script>
</body>	

</html>