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
@WebServlet(name = "LineTimeLineServlet", urlPatterns = { "/LineTimeLineDemo" }, loadOnStartup = 1)
public class LineTimeLineServlet extends HttpServlet {
	
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
		//TimeLine的Y轴数据
		List<Map<String,List<Double>>> timelineAxisData = new ArrayList<Map<String,List<Double>>>();
		//TimeLine的X轴数据
		List<String> timelineData = chinaAreaDataDao.getYearList();
	
		List<ChinaWeatherDataBean> weatherDataList = chinaAreaDataDao.getAll("SELECT * FROM line_weather_main_city WHERE substr(datestr,0,5) = '2011'");
		
		List<Double> beijingMaxTemp = new ArrayList<Double>();
		List<Double> beijingMinTemp = new ArrayList<Double>();
		List<Double> changchunMaxTemp = new ArrayList<Double>();
		List<Double> changchunMinTemp = new ArrayList<Double>();
		List<Double> shenyangMaxTemp = new ArrayList<Double>();
		List<Double> shenyangMinTemp = new ArrayList<Double>();
		List<Double> haerbinMaxTemp = new ArrayList<Double>();
		List<Double> haerbinMinTemp = new ArrayList<Double>();
		
		for(ChinaWeatherDataBean chinaWeatherDataBean:weatherDataList){
			//x轴数据
			xAxisData.add(chinaWeatherDataBean.getDatestr());
			//北京最高温度
			beijingMaxTemp.add(chinaWeatherDataBean.getBeijing_maxtemp());
			//北京最低温度
			beijingMinTemp.add(chinaWeatherDataBean.getBeijing_mintemp());
			//长春最高温度
			changchunMaxTemp.add(chinaWeatherDataBean.getChangchun_maxtemp());
			//长春最高温度
			changchunMinTemp.add(chinaWeatherDataBean.getChangchun_mintemp());
			//沈阳最高温度
			shenyangMaxTemp.add(chinaWeatherDataBean.getShenyang_maxtemp());
			//沈阳最高温度
			shenyangMinTemp.add(chinaWeatherDataBean.getShenyang_mintemp());
			//哈尔滨最高温度
			haerbinMaxTemp.add(chinaWeatherDataBean.getHaerbin_maxtemp());
			//哈尔滨最高温度
			haerbinMinTemp.add(chinaWeatherDataBean.getHaerbin_mintemp());
		}
		
		//y轴数据
		yAxisData.put("北京 最高温度", beijingMaxTemp);
		yAxisData.put("北京 最低温度", beijingMinTemp);
		yAxisData.put("长春 最高温度", changchunMaxTemp);
		yAxisData.put("长春 最低温度", changchunMinTemp);
		yAxisData.put("沈阳 最高温度", shenyangMaxTemp);
		yAxisData.put("沈阳 最低温度", shenyangMinTemp);
		yAxisData.put("哈尔滨 最高温度", haerbinMinTemp);
		yAxisData.put("哈尔滨 最低温度", haerbinMinTemp);
		
		//Y轴双轴情况下的位置定位
		yAxisIndex.put("北京 最高温度", 0);//0表示Y轴左轴
		yAxisIndex.put("长春 最高温度", 0);//0表示Y轴左轴
		yAxisIndex.put("沈阳 最高温度", 0);//0表示Y轴左轴
		yAxisIndex.put("哈尔滨 最高温度", 0);//0表示Y轴左轴
		yAxisIndex.put("北京 最低温度", 1);//1表示Y轴右轴
		yAxisIndex.put("长春 最低温度", 1);//1表示Y轴右轴
		yAxisIndex.put("沈阳 最低温度", 1);//1表示Y轴右轴
		yAxisIndex.put("哈尔滨 最低温度", 1);//1表示Y轴右轴
		
		for(int i=1;i<timelineData.size();i++){
			Map<String,List<Double>> yAxisData1 = new HashMap<String,List<Double>>();
			List<ChinaWeatherDataBean> weatherDataList1 = chinaAreaDataDao.getAll("SELECT * FROM line_weather_main_city WHERE substr(datestr,0,5) = '"+timelineData.get(i)+"'");
			List<Double> beijingMaxTemp1 = new ArrayList<Double>();
			List<Double> beijingMinTemp1 = new ArrayList<Double>();
			List<Double> changchunMaxTemp1 = new ArrayList<Double>();
			List<Double> changchunMinTemp1 = new ArrayList<Double>();
			List<Double> shenyangMaxTemp1 = new ArrayList<Double>();
			List<Double> shenyangMinTemp1 = new ArrayList<Double>();
			List<Double> haerbinMaxTemp1 = new ArrayList<Double>();
			List<Double> haerbinMinTemp1 = new ArrayList<Double>();
			for(ChinaWeatherDataBean chinaWeatherDataBean1:weatherDataList1){
				//北京最高温度
				beijingMaxTemp1.add(chinaWeatherDataBean1.getBeijing_maxtemp());
				//北京最低温度
				beijingMinTemp1.add(chinaWeatherDataBean1.getBeijing_mintemp());
				//长春最高温度
				changchunMaxTemp1.add(chinaWeatherDataBean1.getChangchun_maxtemp());
				//长春最高温度
				changchunMinTemp1.add(chinaWeatherDataBean1.getChangchun_mintemp());
				//沈阳最高温度
				shenyangMaxTemp1.add(chinaWeatherDataBean1.getShenyang_maxtemp());
				//沈阳最高温度
				shenyangMinTemp1.add(chinaWeatherDataBean1.getShenyang_mintemp());
				//哈尔滨最高温度
				haerbinMaxTemp1.add(chinaWeatherDataBean1.getHaerbin_maxtemp());
				//哈尔滨最高温度
				haerbinMinTemp1.add(chinaWeatherDataBean1.getHaerbin_mintemp());
			}
			//y轴数据
			yAxisData1.put("北京 最高温度", beijingMaxTemp1);
			yAxisData1.put("北京 最低温度", beijingMinTemp1);
			yAxisData1.put("长春 最高温度", changchunMaxTemp1);
			yAxisData1.put("长春 最低温度", changchunMinTemp1);
			yAxisData1.put("沈阳 最高温度", shenyangMaxTemp1);
			yAxisData1.put("沈阳 最低温度", shenyangMinTemp1);
			yAxisData1.put("哈尔滨 最高温度", haerbinMinTemp1);
			yAxisData1.put("哈尔滨 最低温度", haerbinMinTemp1);
			timelineAxisData.add(yAxisData1);
		}
		
		//时间轴数据
		request.setAttribute("timelineData", timelineData);
		request.setAttribute("timelineAxisData",timelineAxisData);
		
		request.setAttribute("yAxisIndex", yAxisIndex);
		request.setAttribute("xAxisData", xAxisData);
		request.setAttribute("yAxisData", yAxisData);
		request.getRequestDispatcher("WEB-INF/views/line/lineTimeLine.jsp").forward(request, response);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
			this.doPost(request, response);
	}

}
