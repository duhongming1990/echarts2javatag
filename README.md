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

**双数值轴折线图NumLine的Tag:**
```xml
	<echarts:numline 
		title="双数值轴折线" 
		subtitle="短期预测数据对比曲线"
		ylist="${ylist}" 
		xunitname="预测时间"
		yunitname="实际电量(MW)" 
		/>
	// 为echarts对象加载数据 
	myChart0.setOption(option0);
```

**双数值轴折线图NumLine的数据格式:**		

	private Map<String,Double[][]> ylist;
	
**搭配时间轴折线图GroupLine的Tag:**
```xml
	<echarts:groupline 
		title="2000年短期预测数据对比曲线" 
		subtitle="数据来自国家统计局"
		timelinetitle="${timelinetitle}"
		timeline="${timeline}"
		xlist="${xlist}" 
		ylist="${ylist}" 
		xunitname="预测时间"
		yunitname="实际电量(MW)" 
		/>
```

**搭配时间轴折线图GroupLine的数据格式:**		

	private List<String> timelinetitle;
	
	private List<Map<String,List<Double>>> timeline;
	
	private List<String> xlist;
	
	private Map<String,List<Double>> ylist;
	
	private Map<String,Integer> yloction;

-----------------基础版-----------------

**直线Line的Tag:**
```xml
	<echarts:line 
		title="短期预测数据对比曲线" 
		subtitle="短期预测数据对比曲线"
		xlist="${xlist}" 
		ylist="${ylist}" 
		xunitname="预测时间"
		yunitname="实际电量(MW),实际总辐射(w/㎡)" 
		yloction="${yloction}"/>
```

**直线Line的数据格式:**		

	private List<String> xlist;
	
	private Map<String,List<Double>> ylist;
	
	private Map<String,Integer> yloction;


**柱状图Bar的Tag:**
```xml
    <echarts:bar
		title="短期预测数据对比曲线" 
		subtitle="短期预测数据对比曲线"
		xlist="${xlist}" 
		ylist="${ylist}" 
		xunitname="预测时间"
		yunitname="实际电量(MW),实际总辐射(w/㎡)" 
		yloction="${yloction}"/>
```

**柱状图Bar的数据格式:**		

	private List<String> xlist;
	
	private Map<String,List<Double>> ylist;
	
	private Map<String,Integer> yloction;
	
	
**饼图Pie的Tag:**
```xml
    <echarts:pie
		title="某站点用户访问来源" 
		subtitle="纯属虚构"
		legendMap="${legendMap}"
		/>
```

**饼图Pie的数据格式:**		

	private Map<String,Object> legendMap;
	
**玫瑰图Radar的Tag:**
```xml
    <echarts:radar 
		title="气象预测风向玫瑰图16方位" 
		subtitle="预测时间"
		legendList="${legendList}"
		typeNum="16"
		/>
```

**玫瑰图Radar的数据格式:**		

	private List<Map<String,Object>> legendList;
	


	

	