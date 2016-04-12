# echarts2javatag

通过自定义标签实现echarts图表展示

**演示地址:**
http://dtmonitor.tunnel.qydev.com/echarts2javatag/

**Maven:**
```xml
<dependency>
    <groupId>com.github.abel533</groupId>
    <artifactId>ECharts</artifactId>
    <version>2.2.7</version>
</dependency>
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>[2.6.2,)</version>
</dependency>
```
-----------------高级版-----------------

**双数值轴折线图lineDoubleNum的Tag:**
```xml
	<div id="line_doubleNum"  class="main000"></div>
	<echarts:lineDoubleNum 
	    id="line_doubleNum"
		title="双数值轴折线" 
		subtitle="短期预测数据对比曲线"
		xAxisName="预测时间"
		yAxisName="实际电量(MW)" 
		axisDataArr="${axisDataArr}"/>
```

**双数值轴折线图lineDoubleNum的数据格式:**		

	private Map<String,Double[][]> axisDataArr;
	
**搭配时间轴折线图lineTimeLine的Tag:**
```xml
	<div id="line_yAxisIndex"  class="main000"></div>
	<echarts:lineTimeLine
		id="line_yAxisIndex"
		title="2000年短期预测数据对比曲线" 
		subtitle="数据来自国家统计局"
		xAxisName="预测时间"
		yAxisName="实际电量(MW),实际总辐射(w/㎡)" 
		xAxisData="${xAxisData}" 
		yAxisData="${yAxisData}"
		timelineData="${timelineData}"
		timelineAxisData="${timelineAxisData}"
		yAxisIndex="${yAxisIndex}"/>
```

**搭配时间轴折线图lineTimeLine的数据格式:**		

	private List<String> xAxisData;
	
	private Map<String,List<Double>> yAxisData;
	
	private Map<String,Integer> yAxisIndex;
	
	private List<String> timelineData;
	
	private List<Map<String,List<Double>>> timelineAxisData;
	

**图表单位转换问题：待定**

-----------------基础版-----------------

**直线Line的Tag:**
```xml
	<div id="line_yAxisIndex"  class="main000"></div>
	<echarts:line 
	    id="line_yAxisIndex"
		title="短期预测数据对比曲线" 
		subtitle="短期预测数据对比曲线"
		xAxisData="${xAxisData}" 
		yAxisData="${yAxisData}" 
		xAxisName="预测时间"
		yAxisName="实际电量(MW),实际总辐射(w/㎡)" 
		yAxisIndex="${yAxisIndex}"/>
```

**直线Line的数据格式:**		

	private List<String> xAxisData;
	
	private Map<String,List<Double>> yAxisData;
	
	private Map<String,Integer> yAxisIndex;


**柱状图Bar的Tag:**
```xml
   <div id="line_yAxisIndex"  class="main000"></div>
   <echarts:bar
		id="line_yAxisIndex"
		title="短期预测数据对比曲线" 
		subtitle="短期预测数据对比曲线"
		xAxisData="${xAxisData}" 
		yAxisData="${yAxisData}" 
		xAxisName="预测时间"
		yAxisName="实际电量(MW),实际总辐射(w/㎡)" 
		yAxisIndex="${yAxisIndex}"/>
```

**柱状图Bar的数据格式:**		

	private List<String> xAxisData;
	
	private Map<String,List<Double>> yAxisData;
	
	private Map<String,Integer> yAxisIndex;
	
**饼图Pie的Tag:**
```xml
   	<div id="pie"  class="main000"></div>
	<echarts:pie
	    id="pie"
		title="某站点用户访问来源" 
		subtitle="纯属虚构"
		orientData="${orientData}"/>
```

**饼图Pie的数据格式:**		

	private Map<String,Object> orientData;
	
**玫瑰图Radar的Tag:**
```xml
   <div id="radar16"  class="main000"></div>
	<echarts:radar 
		id="radar16"
		title="气象预测风向玫瑰图16方位" 
		subtitle="预测时间"
		orientData="${orientData}"
		polarType="16"/>
```

**玫瑰图Radar的数据格式:**		

	private List<Map<String,Object>> orientData;
	


	

	