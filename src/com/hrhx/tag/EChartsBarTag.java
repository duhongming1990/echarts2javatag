package com.hrhx.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;

import org.junit.Test;

import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.AxisType;
import com.github.abel533.echarts.code.Magic;
import com.github.abel533.echarts.code.SeriesType;
import com.github.abel533.echarts.code.Symbol;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.feature.MagicType;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Line;

public class EChartsBarTag extends BodyTagSupport {
	private static final long serialVersionUID = 1L;
	private Map<String, List<Double>> ylist;
	private List<String> xlist;
	private String title;
	private String subtitle;
	private String yunitname;
	private String xunitname;
	private Map<String, Integer> yloction;

	@Override
	public int doStartTag() throws JspException {
		return BodyTag.EVAL_BODY_BUFFERED;
	}

	@Override
	public int doEndTag() throws JspException {
		// 创建GsonOption对象，即为json字符串
		GsonOption option = new GsonOption();
		option.tooltip().trigger(Trigger.axis);
		option.title(title, subtitle);
		// 工具栏
		option.toolbox().show(true).feature(
		// Tool.mark,
		// Tool.dataView,
				Tool.saveAsImage,
				// new MagicType(Magic.line, Magic.bar,Magic.stack,Magic.tiled),
				Tool.dataZoom, Tool.restore);
		option.calculable(true);
		option.dataZoom().show(true).realtime(true).start(0).end(100);

		// X轴数据封装并解析
		ValueAxis valueAxis = new ValueAxis();
		for (String s : xlist) {
			valueAxis.type(AxisType.category).data(s);
		}
		// X轴单位
		valueAxis.name(xunitname);
		option.xAxis(valueAxis);
		for (String key : ylist.keySet()) {
			option.legend().data(key);
		}
		// Y轴数据封装并解析
		String[] unitNameArray = yunitname.split(",");
		for (String s : unitNameArray) {
			CategoryAxis categoryAxis = new CategoryAxis();
			categoryAxis.type(AxisType.value);
			option.yAxis(categoryAxis.name(s));
		}
		int i = 0;
		for (String key : ylist.keySet()) {
			// 遍历list得到数据
			List<Double> list = ylist.get(key);
			Line line = new Line().name(key);
			for (Double d : list) {
				// KW与MW单位的转换
				// if(settingGlobal!=null&&settingGlobal.getIskw()==0){
				// d = d/1000;
				// }
				// 数据为空的话会报错，为空则为零
				if (d != null) {
					line.type(SeriesType.bar).data(d);
				} else {
					line.type(SeriesType.bar).data(0);
				}

				if (yloction != null && yloction.get(key) != null) {
					line.type(SeriesType.bar).yAxisIndex(yloction.get(key));
					line.symbol(Symbol.none);
				} else {
					line.type(SeriesType.bar).yAxisIndex(0);
					line.symbol(Symbol.none);
				}

			}
			option.series(line);
			i++;
		}
		try {
			this.pageContext.getOut().write(option.toString());

		} catch (IOException e) {
			System.err.print(e);
		}
		return Tag.EVAL_PAGE;// 继续处理页面
	}

	@Test
	public void test() {

		GsonOption option = new GsonOption();
		/**
		 * tooltip : { trigger: 'axis' }
		 */
		option.tooltip().trigger(Trigger.axis);
		/**
		 * legend: { data:['最高','最低'] }
		 */
		List<Object> legendDataList = new ArrayList<Object>();
		legendDataList.add("最高");
		legendDataList.add("最低");
		option.legend().data(legendDataList);
		/**
		 * toolbox: { show : true, feature : { mark : {show: true}, dataZoom :
		 * {show: true}, dataView : {show: true}, magicType : {show: true, type:
		 * ['line', 'bar', 'stack', 'tiled']}, restore : {show: true},
		 * saveAsImage : {show: true} } }
		 */
		option.toolbox()
				.show(true)
				.feature(
						Tool.mark,
						Tool.dataZoom,
						Tool.dataView,
						new MagicType(Magic.line, Magic.bar, Magic.stack,
								Magic.tiled), Tool.restore, Tool.saveAsImage);
		// calculable : true
		option.calculable(true);
		/**
		 * dataZoom : { show : true, realtime : true, start : 20, end : 80 }
		 */
		option.dataZoom().show(true).realtime(true).start(20).end(80);
		/**
		 * xAxis : [ { type : 'category', boundaryGap : false, data : function
		 * (){ var list = []; for (var i = 1; i <= 30; i++) {
		 * list.push('2013-03-' + i); } return list; }() } ]
		 */
		ValueAxis valueAxis = new ValueAxis();
		valueAxis.type(AxisType.category).data("2015-01-01", "2015-02-02");
		option.xAxis(valueAxis);
		/**
		 * yAxis : [ { type : 'value' } ]
		 */
		CategoryAxis categoryAxis = new CategoryAxis();
		categoryAxis.type(AxisType.value);
		option.yAxis(categoryAxis);
		/**
		 * series : [ { name:'最高', type:'line', data:function (){ var list = [];
		 * for (var i = 1; i <= 30; i++) { list.push(Math.round(Math.random()*
		 * 30)); } return list; }() }, { name:'最低', type:'line', data:function
		 * (){ var list = []; for (var i = 1; i <= 30; i++) {
		 * list.push(Math.round(Math.random()* 10)); } return list; }() } ]
		 */
		Line line1 = new Line();
		line1.name("最高").type(SeriesType.line).data(15, -50);
		Line line2 = new Line();
		line2.name("最低").type(SeriesType.line).data(150, -500);
		option.series(line1);
		option.series(line2);
	}

	public Map<String, List<Double>> getYlist() {
		return ylist;
	}

	public void setYlist(Map<String, List<Double>> ylist) {
		this.ylist = ylist;
	}

	public List<String> getXlist() {
		return xlist;
	}

	public void setXlist(List<String> xlist) {
		this.xlist = xlist;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getYunitname() {
		return yunitname;
	}

	public void setYunitname(String yunitname) {
		this.yunitname = yunitname;
	}

	public String getXunitname() {
		return xunitname;
	}

	public void setXunitname(String xunitname) {
		this.xunitname = xunitname;
	}

	public Map<String, Integer> getYloction() {
		return yloction;
	}

	public void setYloction(Map<String, Integer> yloction) {
		this.yloction = yloction;
	}

}
