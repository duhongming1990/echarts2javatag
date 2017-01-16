package com.hrhx.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "GaugeServlet", urlPatterns = { "/GaugeDemo" }, loadOnStartup = 1)
public class GaugeServlet extends HttpServlet{
	
	private static final long serialVersionUID = 4088316554844715395L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		this.doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/views/gauge.jsp").forward(request, response);
	}
}
