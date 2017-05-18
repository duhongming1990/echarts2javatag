package com.hrhx.servlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.abel533.echarts.code.Magic;
import com.github.abel533.echarts.code.Orient;
import com.github.abel533.echarts.code.SeriesType;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.code.X;
import com.github.abel533.echarts.code.Y;
import com.github.abel533.echarts.data.Data;
import com.github.abel533.echarts.feature.MagicType;
import com.github.abel533.echarts.feature.MagicType.Option;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Funnel;
import com.github.abel533.echarts.series.Line;
@WebServlet(name = "BarReverseServlet", urlPatterns = { "/ReverseBarDemo" }, loadOnStartup = 1)
public class BarReverseServlet extends HttpServlet {
	private static final long serialVersionUID = -6886697421555222670L;
	
	private List<String> xAxisData;
	private Map<String,List<Double>> yAxisData;
	private Map<String,Integer> yAxisIndex;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		this.doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//x轴数据
		request.setAttribute("xAxisData", getxAxisData());
		//y轴数据
		request.setAttribute("yAxisData", getyAxisData());
		//Y轴双轴情况下的位置定位
		request.setAttribute("yAxisIndex", getyAxisIndex());
		
		request.getRequestDispatcher("WEB-INF/views/bar/barReverse.jsp").forward(request, response);
	}
	
	public List<String> getxAxisData(){
		xAxisData = new ArrayList<String>();
		xAxisData.add("2015-10-10");
		xAxisData.add("2015-10-11");
		xAxisData.add("2015-10-12");
		xAxisData.add("2015-10-13");
		xAxisData.add("2015-10-14");
		return xAxisData;
	}
	
	public Map<String,List<Double>> getyAxisData(){
		Random random = new Random();
		yAxisData = new HashMap<String,List<Double>>();
		
		List<Double> data1 = new ArrayList<Double>();
		data1.add(random.nextDouble());
		data1.add(random.nextDouble());
		data1.add(random.nextDouble());
		data1.add(random.nextDouble());
		data1.add(random.nextDouble());
		yAxisData.put("柱状一", data1);
		
		List<Double> data2 = new ArrayList<Double>();
		data2.add(random.nextDouble());
		data2.add(random.nextDouble());
		data2.add(random.nextDouble());
		data2.add(random.nextDouble());
		data2.add(random.nextDouble());
		yAxisData.put("柱状二", data2);
		
		return yAxisData;
	}
	
	public Map<String,Integer> getyAxisIndex(){
		yAxisIndex = new HashMap<String,Integer>();
		yAxisIndex.put("柱状一", 0);
		yAxisIndex.put("柱状二", 1);
		return yAxisIndex;
	}
	
	public String getStr(String id,String title,String subtitle,Map<String,Object> orientData,Boolean itemStyleShow){
		// 创建GsonOption对象，即为json字符串
		GsonOption option = new GsonOption();
		option.tooltip().trigger(Trigger.item).formatter("{a} <br/>{b} : {c} ({d}%)");
		option.title(title, subtitle);
		// 工具栏
		Option o = new Option();
		Funnel funnel = new Funnel().x("25%").width("50%").funnelAlign(X.left).max(1548);
		o.setFunnel(funnel);
		
		MagicType magicType = new MagicType(Magic.funnel,Magic.pie);
		magicType.setOption(o);
		
		option.toolbox().show(true).feature(
			// Tool.dataView,
			Tool.saveAsImage,
			magicType
				);
		option.calculable(true);
		
		// 数据轴封装并解析
		for(String xdata : orientData.keySet()) {
			//option.legend().orient(Orient.horizontal).x(X.left).y(Y.bottom).data(xdata);
			option.legend().orient(Orient.vertical).x(X.left).y(Y.center).data(xdata);
		}
		
		if (orientData != null) {
			Line line = new Line();
			//饼图数值显示
			if(itemStyleShow){
				line.itemStyle().normal().label()
											.show(itemStyleShow)
											.formatter("{b} : {c}");
			}
			
			line.name(title).type(SeriesType.pie);
			for (String title_ : orientData.keySet()) {
				Object value = orientData.get(title_);		
				Data data = new Data().name(title_);
				data.value(value);
				line.data(data);				
			}
			option.series(line);
		}
		return option.toString();
	}
//	<div id="" class="main"></div>
//	<script type="text/javascript">
//		var myChart;
//	    // 使用
//	    require(
//	        [
//	            'echarts',
//	            'echarts/chart/pie' // 使用仪表盘就加载gauge模块，按需加载
//	        ],
//	        function (ec) {
//	            // 基于准备好的dom，初始化echarts图表
//	            myChart = ec.init(document.getElementById('')); 
//				var option = {};
//				myChart.setOption(option);
//	        }
//	    );
//	</script>
//	myChart.setOption(option,true);

}
