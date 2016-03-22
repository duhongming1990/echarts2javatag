# echarts2javatag
通过自定义标签实现echarts图表展示
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
**直线Line的Tag:**
	<echarts:line 
		title="短期预测数据对比曲线" 
		subtitle="短期预测数据对比曲线"
		xlist="${xlist}" 
		ylist="${ylist}" 
		xunitname="预测时间"
		yunitname="实际电量(MW),实际总辐射(w/㎡)" 
		yloction="${yloction}"/>
**直线Line的数据格式:**		
	private List<String> xlist;
	private Map<String,List<Double>> ylist;
	private Map<String,Integer> yloction;
	
**玫瑰图Radar的Tag:**
    <echarts:radar 
		title="气象预测风向玫瑰图16方位" 
		subtitle="预测时间"
		legendList="${legendList}"
		typeNum="16"
		/>
**玫瑰图Radar的数据格式:**		
	private List<Map<String,Object>> legendList;