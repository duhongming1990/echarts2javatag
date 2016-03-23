package com.hrhx.servlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name = "RadarServlet", urlPatterns = { "/RadarDemo" }, loadOnStartup = 1)
public class RadarServlet extends HttpServlet {
	
	private static final long serialVersionUID = 7375363226310112119L;
	
	private List<Map<String,Object>> legendList;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setAttribute("legendList", getLegendList8());
		request.getRequestDispatcher("WEB-INF/views/radar.jsp").forward(request, response);
		
	}
	public List<Map<String,Object>> getLegendList8(){
		legendList = new ArrayList<Map<String,Object>>();
		
		Double[] dataArr1 = new Double[]{0.1*100,0.2*100,0.3*100,0.1*100,0.05*100,0.05*100,0.1*100,0.1*100};
		Map<String,Object> mapData1 = new HashMap<String,Object>();
		mapData1.put("dataArr", dataArr1);
		mapData1.put("title", "玫瑰图1");
		legendList.add(mapData1);
		
		Double[] dataArr2 = new Double[]{0.05*100,0.05*100,0.1*100,0.1*100,0.1*100,0.2*100,0.3*100,0.1*100};
		Map<String,Object> mapData2 = new HashMap<String,Object>();
		mapData2.put("dataArr", dataArr2);
		mapData2.put("title", "玫瑰图2");
		legendList.add(mapData2);
		
		return legendList;
		
	}
}
