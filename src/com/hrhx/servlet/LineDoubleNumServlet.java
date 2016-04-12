package com.hrhx.servlet;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name = "LineDoubleNumServlet", urlPatterns = { "/LineDoubleNumDemo" }, loadOnStartup = 1)
public class LineDoubleNumServlet extends HttpServlet {
	private static final long serialVersionUID = -6886697421555222670L;
	
	private Map<String,Double[][]> axisDataArr;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		this.doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//x+y轴数据Double[x轴数据][y轴数据]
		request.setAttribute("axisDataArr", getaxisDataArr());
		request.getRequestDispatcher("WEB-INF/views/lineDoubleNum.jsp").forward(request, response);
	}
	
	public Map<String,Double[][]> getaxisDataArr(){
		
		Random random = new Random();
		axisDataArr = new HashMap<String,Double[][]>();
		
		Double[][] data1 = new Double[10][2];
		for(int i=0;i<10;i++){
			data1[i][0]=i+0.0;
			data1[i][1]=random.nextInt(10)+0.0;
		}
		axisDataArr.put("曲线一", data1);
		
		Double[][] data2 = new Double[10][2];
		for(int i=0;i<10;i++){
			data2[i][0]=i+1.0;
			data2[i][1]=random.nextInt(10)+0.0;
		}
		axisDataArr.put("曲线二", data2);
		
		return axisDataArr;
	}	

}
