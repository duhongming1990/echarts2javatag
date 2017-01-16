<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String basePath = request.getContextPath();
%>
<script src="<%=basePath%>/styles/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="<%=basePath%>/styles/echarts-3.4.0/dist/echarts.js"></script>
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
<div id="main"  class="main000"></div>
<script type="text/javascript">
	$.get('<%=basePath%>/styles/echarts-3.4.0/map/json/china.json', function (chinaJson) {
	    echarts.registerMap('china', chinaJson);
	    var chart = echarts.init(document.getElementById('main'));
	    chart.setOption({
	        series: [{
	            type: 'map',
	            map: 'china'
	        }]
	    });
	});
</script>