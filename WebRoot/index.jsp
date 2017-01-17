<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
  </head>
  
  <body>
  ////////////////////////////////////////////////////////DEMO造就想法/////////////////////////////////////////////////
  <div>
  	<a href="styles/echarts-2.2.7/doc/example.html" target="_blank">Echarts2.2.7</a>
  </div>
  <div>
  	<a href="styles/echarts-3.4.0/test/-primary-cases.html" target="_blank">Echarts3.4.0</a>
  </div>
  <div>
	<a href="/echarts2javatag/styles/ueditor1_4_3_3-utf8-jsp/charts.html" target="_blank">UEditor编辑图表</a><br/>
  </div>
  /////////////////////////////////////////////////////////静态图表///////////////////////////////////////////////////
  <div>
	<a href="LineDemo" target="_blank">折线图Line</a><br/>
	<img src="images/Line.png" width="600px" height="200px"/>
	<img src="images/LineTag.png" width="300px" height="200px"/><br/>
  </div>	
  <div>
	<a href="LineDoubleNumDemo" target="_blank"><b style="color:red">高级折线：</b>双数值轴折线图NumLine</a><br/>
	<img src="images/LineDoubleNum.png" width="600px" height="200px"/>
	<img src="images/LineDoubleNumTag.png" width="300px" height="200px"/><br/>
  </div>
  <div>
	<a href="LineTimeLineDemo" target="_blank"><b style="color:red">高级折线：</b>搭配时间轴折线图Line</a><br/>
	<img src="images/LineTimeLine.png" width="600px" height="200px"/>
	<img src="images/LineTimeLineTag.png" width="300px" height="200px"/><br/>
  </div>
  <div>
	<a href="BarDemo" target="_blank">条形图Bar</a><br/>
	<img src="images/Bar.png" width="600px" height="200px"/>
	<img src="images/BarTag.png" width="300px" height="200px"/><br/>
  </div>
  <div>
	<a href="ReverseBarDemo" target="_blank">反转条形图Bar</a><br/>
	<img src="images/ReverseBar.png" width="600px" height="200px"/>
	<img src="images/ReverseBarTag.png" width="300px" height="200px"/><br/>
  </div>
   <div>
	<a href="PieDemo" target="_blank">饼状图Pie</a><br/>
	<img src="images/Pie.png" width="600px" height="300px"/>
	<img src="images/PieTag.png" width="300px" height="200px"/><br/>
  </div>
  <div>
	<a href="RadarDemo" target="_blank">玫瑰图Radar</a><br/>
	<img src="images/Radar.png" width="600px" height="300px"/>
	<img src="images/RadarTag.png" width="300px" height="200px"/><br/>
  </div>	
  /////////////////////////////////////////////////////////动态图表///////////////////////////////////////////////////
  <div>
	<a href="DynamicLineDemo" target="_blank"><b style="color:red">高级折线：</b>动态折线图Line</a><br/>
	<img src="images/DynamicLine.png" width="600px" height="300px"/>
	<img src="images/DynamicLineTag.png" width="600px" height="300px"/><br/>
  </div>
  
  <div>
	<a href="GaugeDemo" target="_blank"><b style="color:red">高级仪表盘：</b>仪表盘Gauge</a><br/>
	<img src="images/DynamicGauge.png" width="600px" height="300px"/>
	<img src="images/DynamicGaugeTag.png" width="600px" height="300px"/><br/>
  </div>
  /////////////////////////////////////////////////////////地图图表///////////////////////////////////////////////////
  <div>
	<a href="map/china" target="_blank"><b style="color:red">中国地图</b></a><br/>
	<img src="images/ChinaMap.png" width="600px" height="300px"/>
	<img src="images/ChinaMapTag.png" width="600px" height="300px"/><br/>
  </div>
  </body>
</html>
