package com.hrhx.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hrhx.bean.ChinaWeatherDataBean;
import com.hrhx.bean.FundAnalysisBean;
import com.hrhx.dao.ChinaWeatherDataDao;
import com.hrhx.dao.FundAnalysisDao;

@Controller
@RequestMapping(value="/fund")
public class FundInfoController {
	//数据Dao层
	private FundAnalysisDao fundAnalysisDao = new FundAnalysisDao();
	@RequestMapping(value="chart")
	public String chart(Model model){
		
		//X轴的数据
		List<String> xAxisDataT= new ArrayList<String>();
		//Y轴的数据
		Map<String,List<Double>> yAxisDataT = new HashMap<String,List<Double>>();
		//Y轴双轴情况下的位置定位
		Map<String,Integer> yAxisIndexT = new HashMap<String,Integer>();
		
		List<FundAnalysisBean> fundAnalysisListT= fundAnalysisDao.getAll("SELECT * FROM fund_info where FUND_CODE='000198'");//天弘余额宝货币
		
		List<Double> everyThanAccrualT = new ArrayList<Double>();
		List<Double> annualizedYield7T = new ArrayList<Double>();
		
		for(FundAnalysisBean fundAnalysisBean:fundAnalysisListT){
			//x轴数据
			xAxisDataT.add(fundAnalysisBean.getNETVALUE_DATE());
			//每万份收益
			everyThanAccrualT.add(fundAnalysisBean.getEVERY_THAN_ACCRUAL());
			//7日年化收益率（%）
			annualizedYield7T.add(fundAnalysisBean.getANNUALIZED_YIELD7());
		}
		
		//y轴数据
		yAxisDataT.put("每万份收益", everyThanAccrualT);
		yAxisDataT.put("7日年化收益率（%）", annualizedYield7T);
		
		//Y轴双轴情况下的位置定位
		yAxisIndexT.put("每万份收益", 0);//0表示Y轴左轴
		yAxisIndexT.put("7日年化收益率（%）", 1);//1表示Y轴右轴
		
		model.addAttribute("yAxisIndexT",yAxisIndexT);
		model.addAttribute("xAxisDataT",xAxisDataT);
		model.addAttribute("yAxisDataT",yAxisDataT);
		
		
		//X轴的数据
		List<String> xAxisDataY= new ArrayList<String>();
		//Y轴的数据
		Map<String,List<Double>> yAxisDataY = new HashMap<String,List<Double>>();
		//Y轴双轴情况下的位置定位
		Map<String,Integer> yAxisIndexY = new HashMap<String,Integer>();
		
		List<FundAnalysisBean> fundAnalysisListY= fundAnalysisDao.getAll("SELECT * FROM fund_info where FUND_CODE='000359'");//易方达易货币
		
		List<Double> everyThanAccrualY = new ArrayList<Double>();
		List<Double> annualizedYield7Y = new ArrayList<Double>();
		
		for(FundAnalysisBean fundAnalysisBean:fundAnalysisListY){
			//x轴数据
			xAxisDataY.add(fundAnalysisBean.getNETVALUE_DATE());
			//每万份收益
			everyThanAccrualY.add(fundAnalysisBean.getEVERY_THAN_ACCRUAL());
			//7日年化收益率（%）
			annualizedYield7Y.add(fundAnalysisBean.getANNUALIZED_YIELD7());
		}
		
		//y轴数据
		yAxisDataY.put("每万份收益", everyThanAccrualY);
		yAxisDataY.put("7日年化收益率（%）", annualizedYield7Y);
		
		//Y轴双轴情况下的位置定位
		yAxisIndexY.put("每万份收益", 0);//0表示Y轴左轴
		yAxisIndexY.put("7日年化收益率（%）", 1);//1表示Y轴右轴
		
		model.addAttribute("yAxisIndexY",yAxisIndexY);
		model.addAttribute("xAxisDataY",xAxisDataY);
		model.addAttribute("yAxisDataY",yAxisDataY);
		
		return "/fund/chart";
	}
	
	@RequestMapping(value="updateData")
	public String updateData() throws IOException{
		update1();
		update2();
		
		return "redirect:/fund/chart";
	}
	
	private void update1(){
		//天弘余额宝货币(000198)
		Document doc = null;
		try {
			doc = Jsoup.connect("http://fund.eastmoney.com/f10/F10DataApi.aspx?type=lsjz&code=000198&page=1&per=20000&sdate=&edate=&rt=0.07694074122676131").get();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Elements trs = doc.select("table").select("tbody").select("tr");
		for(int i=0;i<trs.size();i++){
			
			Element element = trs.get(i);
			Elements tds = element.select("td");
			
			String netValueDate = tds.get(0).html().substring(0, 10);
			Double everyThanAccrual = Double.parseDouble(tds.get(1).html());
			Double annualizedYield7 = Double.parseDouble(tds.get(2).html().replace("%", ""));
			
			FundAnalysisDao fundAnalysisDao= new FundAnalysisDao();
			FundAnalysisBean fundAnalysisBean = new FundAnalysisBean("000198","天弘余额宝货币",everyThanAccrual,annualizedYield7,netValueDate);
			try {
				fundAnalysisDao.insert(fundAnalysisBean);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void update2(){
		//易方达易货币(000359)
		Document doc = null;
		try {
			doc = Jsoup.connect("http://fund.eastmoney.com/f10/F10DataApi.aspx?type=lsjz&code=000359&page=1&per=20000&sdate=&edate=&rt=0.07694074122676131").get();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Elements trs = doc.select("table").select("tbody").select("tr");
		for(int i=0;i<trs.size();i++){
			
			Element element = trs.get(i);
			Elements tds = element.select("td");
			
			String netValueDate = tds.get(0).html().substring(0, 10);
			Double everyThanAccrual = Double.parseDouble(tds.get(1).html());
			Double annualizedYield7 = Double.parseDouble(tds.get(2).html().replace("%", ""));
			
			FundAnalysisDao fundAnalysisDao= new FundAnalysisDao();
			FundAnalysisBean fundAnalysisBean = new FundAnalysisBean("000359","易方达易货币",everyThanAccrual,annualizedYield7,netValueDate);
			try {
				fundAnalysisDao.insert(fundAnalysisBean);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
