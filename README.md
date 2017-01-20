# echarts2javatag

通过自定义标签实现echarts图表展示

**演示地址:**
http://charts.ittun.com/echarts2javatag/

**china_weather.db数据库:**
http://git.oschina.net/duhongming/echarts2javatag/attach_files

china_weather.7z >>>解压>>> 放到项目Src下面就行了！

**Maven:**
```xml
	<!-- 必须包：Echarts图表依赖包开始 -->
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
	<!-- 必须包：Echarts图表依赖包开始结束 -->
	
    <!-- 演示包：Sqlite数据库API以及ORM框架开始 -->
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
	 <!-- 演示包：Sqlite数据库API以及ORM框架结束 -->
	 
	 <!-- spring websocket--> 
		<dependency>  
           <groupId>org.springframework</groupId>  
           <artifactId>spring-websocket</artifactId>  
           <version>${spring.version}</version>  
        </dependency>  
        <dependency>  
           <groupId>org.springframework</groupId>  
           <artifactId>spring-messaging</artifactId>  
           <version>${spring.version}</version>  
        </dependency>
        
    <!-- 由于Spring是采用对JSON进行了封装的jackson来生成JSON和返回给客户端 开始-->
	  	<dependency>
		    <groupId>com.fasterxml.jackson.core</groupId>
		    <artifactId>jackson-core</artifactId>
		    <version>2.4.4</version>
		</dependency>
		<dependency>
		    <groupId>com.fasterxml.jackson.core</groupId>
		    <artifactId>jackson-databind</artifactId>
		    <version>2.4.4</version>
		</dependency>
		<dependency>
		    <groupId>com.fasterxml.jackson.core</groupId>
		    <artifactId>jackson-annotations</artifactId>
		    <version>2.4.4</version>
		</dependency>
	<!-- 由于Spring是采用对JSON进行了封装的jackson来生成JSON和返回给客户端  结束-->
```

-----------------动态版-----------------
**1 动态仪表盘Gauge的Tag:**
![alt text](https://git.oschina.net/duhongming/echarts2javatag/raw/master/WebRoot/images/DynamicGauge.png?dir=0&filepath=WebRoot%2Fimages%2FDynamicGauge.png&oid=382337fe1bec52f31072f68fe628bae62b9bc6ac&sha=eee1be544518a345fd23337560186de083bfe985 "动态仪表盘Gauge")
```xml
	<ec:gauge   id="temperature1" 
				height="360px" 
				uri="/echarts2javatag/webSocket/data" 
				unitName="℃" 
				title="全公司仪表温度" 
				subtitle="仪表温度1号表" 
				measureRange="10000" 
				splitNumber="10"
				axisLabelShow="true"
				toFixed="0">
	</ec:gauge>
```

**1 动态仪表盘Gauge的数据格式:**

	public class SocketDataGauge {
		private String tag;
		private Double value;
	}

	private List<SocketDataGauge>
	
	Json数据格式：
	[{"tag":"temperature1","value":178.03634028465075},{"tag":"temperature2","value":6482.322904395684},{"tag":"temperature3","value":2495.388315562964},{"tag":"temperature4","value":1358.7228569841902}]
	

**2 动态折线图Line的Tag:**
```xml
	静态部分和以前一样：
	var option =<echarts:dynamicLine
	id="line_yAxisIndex"
	title="2011年温度对比曲线" 
	subtitle="主要城市的温度对比曲线"
	xAxisData="${xAxisData}" 
	yAxisData="${yAxisData}" 
	xAxisName="预测时间"
	yAxisName="最高温度(℃),最低温度(℃)" 
	yAxisIndex="${yAxisIndex}"/>;


	动态部分：
	var uri = "/echarts2javatag/webSocket/line/data";
	var ws = new SockJS(uri);  
	ws.onopen = function () {
		//console.log('Info: connection opened.');  
	};  
	ws.onmessage = function (event) { 
		var json=eval("("+event.data+")");//将数据转成json格式
		// 动态数据接口 addData
		myChart.addData([
			[
				0,        // 系列索引
				json.value1, // 新增数据
				true,     // 新增数据是否从队列头部插入
				false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
			],
			[
				1,        // 系列索引
				json.value2, // 新增数据
				false,    // 新增数据是否从队列头部插入
				false,    // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
				json.tag  // 坐标轴标签
			]
		]);
		console.log('Received: ' + json); 
	};
```

**2 动态折线图Line的数据格式:**

	静态部分：
	//X轴的数据
	List<String> xAxisData;
	
	//Y轴的数据
	Map<String,List<Double>> yAxisData;
	
	//Y轴双轴情况下的位置定位
	Map<String,Integer> yAxisIndex;



	动态部分：
	//X轴数据
	private String tag;
	//Y轴的1数据
	private Double value1;
	//Y轴的2数据
	private Double value2;

	
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
	
	//每种类型数据是Double数组
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
	

**3 反转条形图Bar的Tag:**
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

**3 反转条形图Bar的数据格式:**

	//X轴的数据
	private List<String> xAxisData;
	
	//Y轴的数据
	private Map<String,List<Double>> yAxisData;
	
	//Y轴双轴情况下的位置定位
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
		
	//X轴的数据
	private List<String> xAxisData;
	
	//Y轴的数据
	private Map<String,List<Double>> yAxisData;
	
	//Y轴双轴情况下的位置定位
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

	//key-value数据
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

	//二维表结构数据
	private List<Map<String,Object>> orientData;
	


	

	