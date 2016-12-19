package com.hrhx.tag;

import java.io.IOException;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;

import com.github.abel533.echarts.code.Magic;
import com.github.abel533.echarts.code.Orient;
import com.github.abel533.echarts.code.SeriesType;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.code.X;
import com.github.abel533.echarts.code.Y;
import com.github.abel533.echarts.data.Data;
import com.github.abel533.echarts.feature.MagicType;
import com.github.abel533.echarts.feature.MagicType.Option;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Funnel;
import com.github.abel533.echarts.series.Line;

public class EChartsPieTag extends BodyTagSupport {
	private static final long serialVersionUID = 1L;
	private String id;
	private String title;
	private String subtitle;
	private Map<String, Object> orientData;
	private Boolean itemStyleShow;
	@Override
	public int doStartTag() throws JspException {
		return BodyTag.EVAL_BODY_BUFFERED;
	}

	@Override
	public int doEndTag() throws JspException {
		StringBuffer sb = new StringBuffer();
		sb.append("<script type='text/javascript'>");
		sb.append("require([ 'echarts', 'echarts/chart/pie','echarts/chart/funnel'], function(ec) {");
		sb.append("var myChart= ec.init(document.getElementById('" + id+ "'));myChart.setTheme('macarons');");
		// 创建GsonOption对象，即为json字符串
		GsonOption option = new GsonOption();
		option.tooltip().trigger(Trigger.item).formatter("{a} <br/>{b} : {c} ({d}%)");
		option.title(title, subtitle);
		// 工具栏
		Option o = new Option();
		Funnel funnel = new Funnel().x("25%").width("50%").funnelAlign(X.left).max(1548);
		o.setFunnel(funnel);
		
		MagicType magicType = new MagicType(Magic.funnel,Magic.pie);
		magicType.setOption(o);
		
		option.toolbox().show(true).feature(
			// Tool.dataView,
			Tool.saveAsImage,
			magicType
				);
		option.calculable(true);
		
		// 数据轴封装并解析
		for(String xdata : orientData.keySet()) {
			//option.legend().orient(Orient.horizontal).x(X.left).y(Y.bottom).data(xdata);
			option.legend().orient(Orient.vertical).x(X.left).y(Y.center).data(xdata);
		}
		
		if (orientData != null) {
			Line line = new Line();
			//饼图数值显示
			if(itemStyleShow){
				line.itemStyle().normal().label()
											.show(itemStyleShow)
											.formatter("{b} : {c}");
			}
			
			line.name(title).type(SeriesType.pie);
			for (String title : orientData.keySet()) {
				Object value = orientData.get(title);		
				Data data = new Data().name(title);
				data.value(value);
				line.data(data);				
			}
			option.series(line);
		}
		sb.append("var option=" + option.toString() + ";");
		sb.append("myChart.setOption(option);");
		sb.append("});");
		sb.append("</script>");
		try {
			this.pageContext.getOut().write(sb.toString());
		} catch (IOException e) {
			System.err.print(e);
		}
		return Tag.EVAL_PAGE;// 继续处理页面
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Map<String, Object> getOrientData() {
		return orientData;
	}

	public void setOrientData(Map<String, Object> orientData) {
		this.orientData = orientData;
	}
	public Boolean getItemStyleShow() {
		return itemStyleShow;
	}

	public void setItemStyleShow(Boolean itemStyleShow) {
		this.itemStyleShow = itemStyleShow;
	}
}
