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
	<img src="Line.png"/>
	<img src="LineTag.png"/><br/>
  </div>	
  <div>
	<a href="RadarDemo" target="_blank">Radar</a><br/>
	<img src="Radar.png"/>
	<img src="RadarTag.png"/><br/>
  </div>	
  </body>
</html>
