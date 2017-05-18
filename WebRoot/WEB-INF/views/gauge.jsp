<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
	<head>
		<%@ include file="/WEB-INF/views/include.inc.jsp"%>
	</head>
	<body>

				<ec:gauge   id="temperature1" 
							height="360px" 
							uri="/echarts2javatag/webSocket/gauge/data" 
							unitName="℃" 
							title="全公司仪表温度" 
							subtitle="仪表温度1号表" 
							measureRange="10000" 
							splitNumber="10"
							axisLabelShow="true"
							toFixed="0">
				</ec:gauge>

				<ec:gauge   id="temperature2" 
							height="360px" 
							uri="/echarts2javatag/webSocket/gauge/data"  
							unitName="℃" 
							title="全公司仪表温度" 
							subtitle="仪表温度2号表" 
							measureRange="10000" 
							splitNumber="10"
							axisLabelShow="false"
							toFixed="1">
				</ec:gauge>

				<ec:gauge   id="temperature3" 
							height="360px" 
							uri="/echarts2javatag/webSocket/gauge/data" 
							unitName="℃" 
							title="全公司仪表温度" 
							subtitle="仪表温度3号表" 
							measureRange="10000" 
							splitNumber="10"
							axisLabelShow="true"
							toFixed="2">
				</ec:gauge>

				<ec:gauge   id="temperature4" 
							height="360px" 
							uri="/echarts2javatag/webSocket/gauge/data"  
							unitName="℃" 
							title="全公司仪表温度" 
							subtitle="仪表温度4号表" 
							measureRange="10000" 
							splitNumber="10"
							axisLabelShow="false"
							toFixed="3">
				</ec:gauge>

	</body>
</html>