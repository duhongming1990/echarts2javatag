package com.hrhx.tag;

import java.io.IOException;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;

import com.github.abel533.echarts.code.Orient;
import com.github.abel533.echarts.code.SeriesType;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.code.X;
import com.github.abel533.echarts.code.Y;
import com.github.abel533.echarts.data.Data;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Line;

public class EChartsPieTag extends BodyTagSupport {
	private static final long serialVersionUID = 1L;
	private String title;
	private String subtitle;
	private Map<String, Object> legendMap;

	@Override
	public int doStartTag() throws JspException {
		return BodyTag.EVAL_BODY_BUFFERED;
	}

	@Override
	public int doEndTag() throws JspException {
		// 创建GsonOption对象，即为json字符串
		GsonOption option = new GsonOption();
		option.tooltip().trigger(Trigger.item).formatter("{a} <br/>{b} : {c} ({d}%)");
		option.title(title, subtitle);
		// 工具栏
		option.toolbox().show(true).feature(
		// Tool.mark,
		// Tool.dataView,
				Tool.saveAsImage
				// new MagicType(Magic.line, Magic.bar,Magic.stack,Magic.tiled),
		//		Tool.dataZoom, Tool.restore
				);
		option.calculable(true);
		
		// 数据轴封装并解析
		for(String xdata : legendMap.keySet()) {
			option.legend().orient(Orient.horizontal).x(X.left).y(Y.bottom).data(xdata);
		}
		
		if (legendMap != null) {
			Line line = new Line();
			line.name(title).type(SeriesType.pie);
			for (String title : legendMap.keySet()) {
				Object value = legendMap.get(title);		
				Data data = new Data().name(title);
				data.value(value);
				line.data(data);				
			}
			option.series(line);
		}
		try {
			this.pageContext.getOut().write(option.toString());

		} catch (IOException e) {
			System.err.print(e);
		}
		return Tag.EVAL_PAGE;// 继续处理页面
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

	public Map<String, Object> getLegendMap() {
		return legendMap;
	}

	public void setLegendMap(Map<String, Object> legendMap) {
		this.legendMap = legendMap;
	}

	

}
