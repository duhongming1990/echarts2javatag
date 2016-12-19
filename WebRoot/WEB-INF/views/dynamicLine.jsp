<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
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
                
                $(document).ready(function(){ 
			        JS.Engine.on({
			             dynamicLine : function(echartsData){//侦听一个channel
			                  myChart.addData([
						        [
						            0,        // 系列索引
						            echartsData.beijing_maxtemp, // 新增数据
						            false,     // 新增数据是否从队列头部插入
						            false,     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
						            echartsData.datestr, // 坐标轴标签
						        ]
						      ]);	
						}
			        });
			        JS.Engine.start('connComet4J');
		       });
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
