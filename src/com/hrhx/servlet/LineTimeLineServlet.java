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
@WebServlet(name = "LineTimeLineServlet", urlPatterns = { "/LineTimeLineDemo" }, loadOnStartup = 1)
public class LineTimeLineServlet extends HttpServlet {
	private static final long serialVersionUID = -6886697421555222670L;
	private List<String> xAxisData;
	private Map<String,List<Double>> yAxisData;
	private Map<String,Integer> yAxisIndex;
	private List<String> timelineData;
	private List<Map<String,List<Double>>> timelineAxisData;
	
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//时间轴数据
		request.setAttribute("timelineData", gettimelineData());
		request.setAttribute("timelineAxisData", gettimelineAxisData());
		
		//x轴数据
		request.setAttribute("xAxisData", getxAxisData());
		//y轴数据
		request.setAttribute("yAxisData", getyAxisData());
		//Y轴双轴情况下的位置定位
		request.setAttribute("yAxisIndex", getyAxisIndex());
		
		request.getRequestDispatcher("WEB-INF/views/lineTimeLine.jsp").forward(request, response);
	}
	
	public List<String> gettimelineData(){
		
		timelineData = new ArrayList<String>();
		
		timelineData.add("2000");
		
		timelineData.add("2002");
		
		timelineData.add("2004");
		
		timelineData.add("2006");
		
		timelineData.add("2008");
		
		timelineData.add("2010");
		
		return timelineData;
	}
	
	public List<Map<String,List<Double>>> gettimelineAxisData(){
		timelineAxisData = new ArrayList<Map<String,List<Double>>>();
		
		timelineAxisData.add(getyAxisData());
		
		timelineAxisData.add(getyAxisData());
		
		timelineAxisData.add(getyAxisData());
		
		timelineAxisData.add(getyAxisData());
		
		timelineAxisData.add(getyAxisData());
		
		return timelineAxisData;
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
		yAxisData.put("曲线一", data1);
		
		List<Double> data2 = new ArrayList<Double>();
		data2.add(random.nextDouble());
		data2.add(random.nextDouble());
		data2.add(random.nextDouble());
		data2.add(random.nextDouble());
		data2.add(random.nextDouble());
		yAxisData.put("曲线二", data2);
		
		return yAxisData;
	}
	
	public Map<String,Integer> getyAxisIndex(){
		yAxisIndex = new HashMap<String,Integer>();
		yAxisIndex.put("曲线一", 0);
		yAxisIndex.put("曲线二", 1);
		return yAxisIndex;
	}
	
	

}
