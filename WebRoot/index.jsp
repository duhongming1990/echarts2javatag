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
  <div>
	<a href="LineDemo" target="_blank">折线图Line</a><br/>
	<img src="images/Line.png" width="300px" height="200px"/>
	<img src="images/LineTag.png" width="300px" height="200px"/><br/>
  </div>	
  <div>
	<a href="LineDoubleNumDemo" target="_blank"><b style="color:red">高级折线：</b>双数值轴折线图NumLine</a><br/>
	<img src="images/NumLine.png" width="300px" height="200px"/>
	<img src="images/LineDoubleNumTag.png" width="300px" height="200px"/><br/>
  </div>
  <div>
	<a href="LineTimeLineDemo" target="_blank"><b style="color:red">高级折线：</b>搭配时间轴折线图Line</a><br/>
	<img src="images/GroupLine.png" width="300px" height="200px"/>
	<img src="images/LineTimeLineTag.png" width="300px" height="200px"/><br/>
  </div>
  <div>
	<a href="BarDemo" target="_blank">条形图Bar</a><br/>
	<img src="images/Bar.png" width="300px" height="200px"/>
	<img src="images/BarTag.png" width="300px" height="200px"/><br/>
  </div>
   <div>
	<a href="PieDemo" target="_blank">饼状图Pie</a><br/>
	<img src="images/Pie.png" width="300px" height="200px"/>
	<img src="images/PieTag.png" width="300px" height="200px"/><br/>
  </div>
  <div>
	<a href="RadarDemo" target="_blank">玫瑰图Radar</a><br/>
	<img src="images/Radar.png" width="300px" height="200px"/>
	<img src="images/RadarTag.png" width="300px" height="200px"/><br/>
  </div>	
  
  </body>
</html>
