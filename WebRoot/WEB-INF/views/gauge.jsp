<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
	<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="main" style="height:400px"></div>
	<script type="text/javascript">
        // 使用
        require(
            [
                'echarts',
                'echarts/chart/gauge' // 使用柱状图就加载bar模块，按需加载
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('main')); 
                
   				option = {
					    tooltip : {
					        formatter: "{a} <br/>{c} {b}"
					    },
					    toolbox: {
					        show : true,
					        feature : {
					            mark : {show: true},
					            restore : {show: true},
					            saveAsImage : {show: true}
					        }
					    },
					    series : [
					        {
					            name:'速度',
					            type:'gauge',
					            z: 3,
					            min:0,
					            max:220,
					            splitNumber:11,
					            axisLine: {            // 坐标轴线
					                lineStyle: {       // 属性lineStyle控制线条样式
					                    width: 10
					                }
					            },
					            axisTick: {            // 坐标轴小标记
					                length :15,        // 属性length控制线长
					                lineStyle: {       // 属性lineStyle控制线条样式
					                    color: 'auto'
					                }
					            },
					            splitLine: {           // 分隔线
					                length :20,         // 属性length控制线长
					                lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
					                    color: 'auto'
					                }
					            },
					            title : {
					                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
					                    fontWeight: 'bolder',
					                    fontSize: 20,
					                    fontStyle: 'italic'
					                }
					            },
					            detail : {
					                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
					                    fontWeight: 'bolder'
					                }
					            },
					            data:[{value: 40, name: 'km/h'}]
					        }
					    ]
					};
					myChart.setOption(option); 
					var timeTicket;
					clearInterval(timeTicket);
					timeTicket = setInterval(function (){
					    option.series[0].data[0].value = (Math.random()*7).toFixed(2) - 0;
					    // 为echarts对象加载数据 
					    myChart.setOption(option,true);
					},2000);       
            }
        );
    </script>