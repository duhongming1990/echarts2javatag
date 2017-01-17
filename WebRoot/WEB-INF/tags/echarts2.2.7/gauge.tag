<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ attribute name="id" type="java.lang.String" required="true" description="div的ID"%>
<%@ attribute name="title" type="java.lang.String" required="true" description="标题"%>
<%@ attribute name="subtitle" type="java.lang.String" required="false" description="子标题"%>

<%@ attribute name="height" type="java.lang.String" required="true" description="图表高度"%>

<%@ attribute name="toFixed" type="java.lang.Integer" required="true" description="保留小数位数"%>
<%@ attribute name="measureRange" type="java.lang.Integer" required="true" description="量程"%>
<%@ attribute name="splitNumber" type="java.lang.Integer" required="true" description="切分等份"%>
<%@ attribute name="axisLabelShow" type="java.lang.Boolean" required="true" description="是否显示刻度"%>


<%@ attribute name="unitName" type="java.lang.String" required="true" description="单位"%>

<%@ attribute name="uri" type="java.lang.String" required="true" description="Socket URL地址"%>

<style type="text/css">
    .main${id} {
        height: ${height};
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
<div id="${id}" class="main${id}" ></div>
<script type="text/javascript">
    // 使用
    require(
        [
            'echarts',
            'echarts/chart/gauge' // 使用仪表盘就加载gauge模块，按需加载
        ],
        function (ec) {
            // 基于准备好的dom，初始化echarts图表
            var myChart${id} = ec.init(document.getElementById('${id}')); 
			var option${id} = {
				title : {
			        text: '${title}',
			        subtext: '${subtitle}'
			    },
			    tooltip : {
			        formatter: "{a} <br/>{c} {b}"
			    },
			    toolbox: {
			        show : true,
			        feature : {
			            mark : {show: false},
			            restore : {show: false},
			            saveAsImage : {show: true}
			        }
			    },
			    series : [
			        {
			            name:'${id}',
			            type:'gauge',
			            z: 3,
			            min:0,
			            max:${measureRange},
			            splitNumber:${splitNumber},
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
			             axisLabel: {           // 坐标轴文本标签，详见axis.axisLabel
			                show:${axisLabelShow},
			              	textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
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
			            data:[{value: 0, name: '${unitName}'}]
			        }
			    ]
			};
			myChart${id}.setOption(option${id}); 
	            var ws = new SockJS("${uri}");  
	            ws.onopen = function () {
	                console.log('Info: connection opened.');  
	            };  
	            ws.onmessage = function (event) { 
	            	var json=eval("("+event.data+")");//将数据转成json格式
	            	$.each(json,function(i,item){
	            		if(item.tag=='${id}'){
	            			option${id}.series[0].data[0].value = item.value.toFixed(${toFixed});
     						myChart${id}.setOption(option${id},true);
	            		}	
	            		console.log('Received: ' + '#'+item.tag+':'+item.value); 
	            	});
	            	
	            };
	            ws.onclose = function (event) {  
	                console.log('Info: connection closed.');  
	                console.log(event);  
	            };	       
        }
    );//刷新仪表数据，使input 的hidden隐藏表单和echarts仪表的一致，有更好的方法请告诉我！
</script>