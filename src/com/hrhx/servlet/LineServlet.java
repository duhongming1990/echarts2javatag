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
@WebServlet(name = "LineServlet", urlPatterns = { "/LineDemo" }, loadOnStartup = 1)
public class LineServlet extends HttpServlet {
	
	private static final long serialVersionUID = -6886697421555222670L;
	
	private ChinaWeatherDataDao chinaAreaDataDao = new ChinaWeatherDataDao();
	
	private List<String> xAxisData;
	private Map<String,List<Double>> yAxisData;
	private Map<String,Integer> yAxisIndex;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		this.doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		xAxisData= new ArrayList<String>();
		yAxisData = new HashMap<String,List<Double>>();
		
		List<ChinaWeatherDataBean> weatherDataList= chinaAreaDataDao.getAll("SELECT * FROM line_weather_main_city");
		
		List<Double> beijingMaxTemp = new ArrayList<Double>();
		List<Double> beijingMinTemp = new ArrayList<Double>();
		List<Double> changchunMaxTemp = new ArrayList<Double>();
		List<Double> changchunMinTemp = new ArrayList<Double>();
		for(ChinaWeatherDataBean chinaWeatherDataBean:weatherDataList){
			//x轴数据
			xAxisData.add(chinaWeatherDataBean.getDatestr());
			//北京最高温度
			beijingMaxTemp.add(chinaWeatherDataBean.getBeijing_maxtemp());
			//北京最低温度
			beijingMinTemp.add(chinaWeatherDataBean.getBeijing_mintemp());
			//沈阳最高温度
			changchunMaxTemp.add(chinaWeatherDataBean.getChangchun_maxtemp());
			//沈阳最高温度
			changchunMinTemp.add(chinaWeatherDataBean.getChangchun_mintemp());
		}
		//y轴数据
		yAxisData.put("北京 最高温度", beijingMaxTemp);
		yAxisData.put("北京 最低温度", beijingMinTemp);
		yAxisData.put("沈阳 最高温度", changchunMaxTemp);
		yAxisData.put("沈阳 最低温度", changchunMinTemp);
		request.setAttribute("xAxisData", xAxisData);
		request.setAttribute("yAxisData", yAxisData);
		//Y轴双轴情况下的位置定位
		request.setAttribute("yAxisIndex", getyAxisIndex());
		request.getRequestDispatcher("WEB-INF/views/line.jsp").forward(request, response);
	}
	
	public Map<String,Integer> getyAxisIndex(){
		yAxisIndex = new HashMap<String,Integer>();
		yAxisIndex.put("北京 最高温度", 0);
		yAxisIndex.put("沈阳 最高温度", 0);
		yAxisIndex.put("北京 最低温度", 1);
		yAxisIndex.put("沈阳 最低温度", 1);
		return yAxisIndex;
	}
	
	

}
