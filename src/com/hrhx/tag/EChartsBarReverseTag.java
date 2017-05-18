package com.hrhx.tag;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;

import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.AxisType;
import com.github.abel533.echarts.code.Position;
import com.github.abel533.echarts.code.SeriesType;
import com.github.abel533.echarts.code.Symbol;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Line;

public class EChartsBarReverseTag extends BodyTagSupport {
	private static final long serialVersionUID = 1L;
	private String id;
	private String title;
	private String subtitle;
	private String xAxisName;
	private String yAxisName;
	private List<String> xAxisData;
	private Map<String, Integer> yAxisIndex;
	private Map<String, List<Double>> yAxisData;
	private Boolean itemStyleShow;

	@Override
	public int doStartTag() throws JspException {
		return BodyTag.EVAL_BODY_BUFFERED;
	}

	@Override
	public int doEndTag() throws JspException {
		StringBuffer sb = new StringBuffer();
		sb.append("<script type='text/javascript'>");
		sb.append("var myChart= echarts.init(document.getElementById('" + id+ "'),'macarons');");
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
		//option.dataZoom().show(true).realtime(true).start(0).end(100);
		
		for (String key : yAxisData.keySet()) {
			option.legend().data(key);
		}
		// X轴数据封装并解析
		option.xAxis(new ValueAxis().type(AxisType.value));
		
		ValueAxis yvalueAxis = new ValueAxis();
		for (String key : xAxisData) {
			yvalueAxis.type(AxisType.category).data(key);
		}
		option.yAxis(yvalueAxis);

		int i = 0;
		for (String key : yAxisData.keySet()) {
			// 遍历list得到数据
			List<Double> list = yAxisData.get(key);
			Line line = new Line().name(key);
			line.itemStyle().normal().label().show(itemStyleShow).position(Position.right);
			for (Double d : list) {
				// 数据为空的话会报错，为空则为零
				if (d != null) {
					line.type(SeriesType.bar).data(d);
				} else {
					line.type(SeriesType.bar).data(0);
				}
				line.symbol(Symbol.none);
			}
			option.series(line);
			i++;
		}
		sb.append("var option=" + option.toString() + ";");
		sb.append("myChart.setOption(option);");
		sb.append("</script>");
		try {
			this.pageContext.getOut().write(sb.toString());
			//System.out.println(sb.toString());
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

	public String getxAxisName() {
		return xAxisName;
	}

	public void setxAxisName(String xAxisName) {
		this.xAxisName = xAxisName;
	}

	public String getyAxisName() {
		return yAxisName;
	}

	public void setyAxisName(String yAxisName) {
		this.yAxisName = yAxisName;
	}

	public List<String> getxAxisData() {
		return xAxisData;
	}

	public void setxAxisData(List<String> xAxisData) {
		this.xAxisData = xAxisData;
	}

	public Map<String, Integer> getyAxisIndex() {
		return yAxisIndex;
	}

	public void setyAxisIndex(Map<String, Integer> yAxisIndex) {
		this.yAxisIndex = yAxisIndex;
	}

	public Map<String, List<Double>> getyAxisData() {
		return yAxisData;
	}

	public void setyAxisData(Map<String, List<Double>> yAxisData) {
		this.yAxisData = yAxisData;
	}

	public Boolean getItemStyleShow() {
		return itemStyleShow;
	}

	public void setItemStyleShow(Boolean itemStyleShow) {
		this.itemStyleShow = itemStyleShow;
	}

}
