<%@ page  language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/hrhx/echarts3.5.4" prefix="echarts"%>
<%@ taglib prefix="ec" tagdir="/WEB-INF/tags/echarts3.5.4" %>
<%
	String basePath = request.getContextPath();
%>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">

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

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>  
  <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>  
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>  
<![endif]-->

<!-- sockjs必须要放在echarts.js上面防止JS冲突 -->
<script src="<%=basePath%>/styles/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath%>/styles/sockjs-client-master/dist/sockjs-1.1.1.min.js"></script>

<!-- ECharts单文件引入 -->
<script src="<%=basePath%>/styles/echarts-3.5.4/echarts.min.js"></script>
<script src="<%=basePath%>/styles/echarts-3.5.4/theme/macarons.js"></script>

