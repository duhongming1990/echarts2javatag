<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="id" type="java.lang.String" required="true" description="div的ID"%>
<%@ attribute name="measureRange" type="java.lang.String" required="true" description="量程"%>
<%@ attribute name="unitName" type="java.lang.String" required="true" description="单位"%>
<%@ attribute name="dataType" type="java.lang.String" required="true" description="数据类型"%>
<%@ attribute name="title" type="java.lang.String" required="true" description="标题"%>
<style type="text/css">
    .main${id} {
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
<div id="${id}" class="main${id}" ></div>
<input type="text" id="input${id}" >
<script type="text/javascript">
	var myChart${id};
	var option${id};
    // 使用
    require(
        [
            'echarts',
            'echarts/chart/gauge' // 使用仪表盘就加载gauge模块，按需加载
        ],
        function (ec) {
            // 基于准备好的dom，初始化echarts图表
            myChart${id} = ec.init(document.getElementById('${id}')); 
			option${id} = {
				title : {
			        text: '${title}',
			       
			    },
			    tooltip : {
			        formatter: "{a} <br/>{c} {b}"
			    },
			    toolbox: {
			        show : false,
			        feature : {
			            mark : {show: false},
			            restore : {show: false},
			            saveAsImage : {show: false}
			        }
			    },
			    series : [
			        {
			            name:'${dataType}',
			            type:'gauge',
			            z: 3,
			            min:0,
			            max:'${measureRange}',
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
			             axisLabel: {           // 坐标轴文本标签，详见axis.axisLabel
			                show:true,
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
        }
    );
    $("#input${id}").change(function(){
		option${id}.series[0].data[0].value = $(this).val();
     	myChart${id}.setOption(option${id},true);
	}); 
</script>