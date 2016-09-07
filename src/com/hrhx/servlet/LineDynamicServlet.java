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

import com.hrhx.bean.ChinaWeatherDataBean;
import com.hrhx.dao.ChinaWeatherDataDao;
@WebServlet(name = "LineDynamicServlet", urlPatterns = { "/DynamicLineDemo" }, loadOnStartup = 1)
public class LineDynamicServlet extends HttpServlet {
	
	private static final long serialVersionUID = -6886697421555222670L;
	
	//数据Dao层
	private ChinaWeatherDataDao chinaAreaDataDao = new ChinaWeatherDataDao();
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//X轴的数据
		List<String> xAxisData= new ArrayList<String>();
		//Y轴的数据
		Map<String,List<Double>> yAxisData = new HashMap<String,List<Double>>();
		//Y轴双轴情况下的位置定位
		Map<String,Integer> yAxisIndex = new HashMap<String,Integer>();
		
		List<ChinaWeatherDataBean> weatherDataList= chinaAreaDataDao.getAll("SELECT * FROM line_weather_main_city limit 0,10");
		
		List<Double> beijingMaxTemp = new ArrayList<Double>();

		
		for(ChinaWeatherDataBean chinaWeatherDataBean:weatherDataList){
			//x轴数据
			xAxisData.add(chinaWeatherDataBean.getDatestr());
			//北京最高温度
			beijingMaxTemp.add(chinaWeatherDataBean.getBeijing_maxtemp());
		}
		
		//y轴数据
		yAxisData.put("本机剩余内存", beijingMaxTemp);
		
		//Y轴双轴情况下的位置定位
		yAxisIndex.put("本机剩余内存", 0);//0表示Y轴左轴

		request.setAttribute("yAxisIndex", yAxisIndex);
		request.setAttribute("xAxisData", xAxisData);
		request.setAttribute("yAxisData", yAxisData);
		request.getRequestDispatcher("WEB-INF/views/dynamicLine.jsp").forward(request, response);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
			this.doPost(request, response);
	}
}
