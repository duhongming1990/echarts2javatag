<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
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
	<a href="LineDemo" target="_blank">Line</a><br/>
	<img src="Line.png" width="600px" height="400px"/>
	<img src="LineTag.png" width="600px" height="400px"/><br/>
  </div>	
  <div>
	<a href="RadarDemo" target="_blank">Radar</a><br/>
	<img src="Radar.png" width="600px" height="400px"/>
	<img src="RadarTag.png" width="600px" height="400px"/><br/>
  </div>	
  <div>
	<a href="BarDemo" target="_blank">Bar</a><br/>
	<img src="Bar.png" width="600px" height="400px"/>
	<img src="BarTag.png" width="600px" height="400px"/><br/>
  </div>
   <div>
	<a href="PieDemo" target="_blank">Pie</a><br/>
	<img src="Pie.png" width="600px" height="400px"/>
	<img src="PieTag.png" width="600px" height="400px"/><br/>
  </div>
  </body>
</html>
