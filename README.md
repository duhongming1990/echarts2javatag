# echarts2javatag

通过自定义标签实现echarts图表展示

**演示地址:**
http://charts.ittun.com/echarts2javatag/

**china_weather.db数据库:**
http://git.oschina.net/duhongming/echarts2javatag/attach_files

china_weather.7z >>>解压>>> 放到项目Src下面就行了！

**Maven:**
```xml
	<!-- Echarts图表依赖包开始 -->
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
	<!-- Echarts图表依赖包结束 -->
	
   <!-- Sqlite数据库API以及ORM框架开始 -->
   <dependency>
	    <groupId>org.xerial</groupId>
	    <artifactId>sqlite-jdbc</artifactId>
	    <version>3.8.11.2</version>
	</dependency>
	<dependency>
	    <groupId>com.j256.ormlite</groupId>
	    <artifactId>ormlite-jdbc</artifactId>
	    <version>4.47</version>
	</dependency>
  	<!-- Sqlite数据库API以及ORM框架结束 -->
```
-----------------高级版-----------------

**1 双数值轴折线图lineDoubleNum的Tag:**
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

**1 双数值轴折线图lineDoubleNum的数据格式:**		

	private Map<String,Double[][]> axisDataArr;
	
**2 搭配时间轴折线图lineTimeLine的Tag:**
```xml
	<div id="line_yAxisIndex"  class="main000"></div>
	<echarts:lineTimeLine
		id="line_yAxisIndex"
		title="2011年温度对比曲线" 
		subtitle="主要城市的温度对比曲线"
		xAxisName="预测时间"
		yAxisName="最高温度(℃),最低温度(℃)" 
		xAxisData="${xAxisData}" 
		yAxisData="${yAxisData}"
		timelineData="${timelineData}"
		timelineAxisData="${timelineAxisData}"
		yAxisIndex="${yAxisIndex}"/>
```

**2 搭配时间轴折线图lineTimeLine的数据格式:**		

	//X轴的数据
	List<String> xAxisData;
	
	//Y轴的数据
	Map<String,List<Double>> yAxisData;
	
	//Y轴双轴情况下的位置定位
	Map<String,Integer> yAxisIndex;
	
	//TimeLine的Y轴数据
	List<Map<String,List<Double>>> timelineAxisData;
	
	//TimeLine的X轴数据
	List<String> timelineData;
	
**3 动态折线图Line的Tag:**
```xml
	<div id="line_normal"  class="main000"></div>
	<echarts:dynamicLine 
    id="line_normal"
	title="本机剩余内存" 
	subtitle="本机剩余内存Lenovo"
	xAxisData="${xAxisData}" 
	yAxisData="${yAxisData}" 
	xAxisName="采样时间"
	yAxisName="内存量(KB)" /> ;
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
```

**3 动态折线图Line的数据格式:**

	//X轴的数据
	List<String> xAxisData= new ArrayList<String>();
	
	//Y轴的数据
	Map<String,List<Double>> yAxisData = new HashMap<String,List<Double>>();
	
	//Y轴双轴情况下的位置定位
	Map<String,Integer> yAxisIndex = new HashMap<String,Integer>();

**4 反转条形图Bar的Tag:**
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

**4 反转条形图Bar的数据格式:**

	private List<String> xAxisData;
	
	private Map<String,List<Double>> yAxisData;
	
	private Map<String,Integer> yAxisIndex;


**图表单位转换问题：待定**

-----------------基础版-----------------

**1 直线Line的Tag:**
```xml
	<div id="line_yAxisIndex"  class="main000"></div>
	<echarts:line 
	    id="line_yAxisIndex"
		title="2011年温度对比曲线" 
		subtitle="主要城市的温度对比曲线"
		xAxisData="${xAxisData}" 
		yAxisData="${yAxisData}" 
		xAxisName="预测时间"
		yAxisName="最高温度(℃),最低温度(℃)" 
		yAxisIndex="${yAxisIndex}"/>
```

**1 直线Line的数据格式:**		

	//X轴的数据
	List<String> xAxisData;
	
	//Y轴的数据
	Map<String,List<Double>> yAxisData;
	
	//Y轴双轴情况下的位置定位
	Map<String,Integer> yAxisIndex;

**2 柱状图Bar的Tag:**
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

**2 柱状图Bar的数据格式:**		

	private List<String> xAxisData;
	
	private Map<String,List<Double>> yAxisData;
	
	private Map<String,Integer> yAxisIndex;
	
**3 饼图Pie的Tag:**
```xml
   	<div id="pie"  class="main000"></div>
	<echarts:pie
	    id="pie"
		title="某站点用户访问来源" 
		subtitle="纯属虚构"
		orientData="${orientData}"/>
```

**3 饼图Pie的数据格式:**		

	private Map<String,Object> orientData;
	
**4 玫瑰图Radar的Tag:**
```xml
   <div id="radar16"  class="main000"></div>
	<echarts:radar 
		id="radar16"
		title="气象预测风向玫瑰图16方位" 
		subtitle="预测时间"
		orientData="${orientData}"
		polarType="16"/>
```

**4 玫瑰图Radar的数据格式:**		

	private List<Map<String,Object>> orientData;
	


	

	