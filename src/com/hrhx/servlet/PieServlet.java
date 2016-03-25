package com.hrhx.servlet;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name = "PieServlet", urlPatterns = { "/PieDemo" }, loadOnStartup = 1)
public class PieServlet extends HttpServlet {
	
	private static final long serialVersionUID = 7375363226310112119L;
	
	private Map<String,Object> legendMap;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setAttribute("legendMap", getLegendMap());
		request.getRequestDispatcher("WEB-INF/views/pie.jsp").forward(request, response);
		
	}
	public Map<String,Object> getLegendMap(){
		legendMap = new HashMap<String,Object>();
		legendMap.put("直接访问", 335);
		legendMap.put("邮件营销", 310);
		legendMap.put("联盟广告", 234);
		legendMap.put("视频广告", 135);
		legendMap.put("搜索引擎", 1548);
		return legendMap;
	}
}
