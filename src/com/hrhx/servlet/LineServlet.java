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
@WebServlet(name = "LineServlet", urlPatterns = { "/LineDemo" }, loadOnStartup = 1)
public class LineServlet extends HttpServlet {
	private static final long serialVersionUID = -6886697421555222670L;
	
	private List<String> xlist;
	private Map<String,List<Double>> ylist;
	private Map<String,Integer> yloction;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		this.doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//x轴数据
		request.setAttribute("xlist", getXList());
		//y轴数据
		request.setAttribute("ylist", getYList());
		//Y轴双轴情况下的位置定位
		request.setAttribute("yloction", getYLoction());
		
		request.getRequestDispatcher("WEB-INF/views/line.jsp").forward(request, response);
	}
	
	public List<String> getXList(){
		xlist = new ArrayList<String>();
		xlist.add("2015-10-10");
		xlist.add("2015-10-11");
		xlist.add("2015-10-12");
		xlist.add("2015-10-13");
		xlist.add("2015-10-14");
		return xlist;
	}
	
	public Map<String,List<Double>> getYList(){
		Random random = new Random();
		ylist = new HashMap<String,List<Double>>();
		
		List<Double> data1 = new ArrayList<Double>();
		data1.add(random.nextDouble());
		data1.add(random.nextDouble());
		data1.add(random.nextDouble());
		data1.add(random.nextDouble());
		data1.add(random.nextDouble());
		ylist.put("曲线一", data1);
		
		List<Double> data2 = new ArrayList<Double>();
		data2.add(random.nextDouble());
		data2.add(random.nextDouble());
		data2.add(random.nextDouble());
		data2.add(random.nextDouble());
		data2.add(random.nextDouble());
		ylist.put("曲线二", data2);
		
		return ylist;
	}
	
	public Map<String,Integer> getYLoction(){
		yloction = new HashMap<String,Integer>();
		yloction.put("曲线一", 0);
		yloction.put("曲线二", 1);
		return yloction;
	}
	
	

}
