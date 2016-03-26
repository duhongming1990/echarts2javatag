package com.hrhx.tag;

import java.io.IOException;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;

import org.junit.Test;

import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.AxisType;
import com.github.abel533.echarts.code.LineType;
import com.github.abel533.echarts.code.Magic;
import com.github.abel533.echarts.code.PointerType;
import com.github.abel533.echarts.code.SeriesType;
import com.github.abel533.echarts.code.Symbol;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.feature.MagicType;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Line;
import com.github.abel533.echarts.style.LineStyle;
 
public class EChartsDoubleNumLineTag extends BodyTagSupport{
	private static final long serialVersionUID = 1L;
	private Map<String,Double[][]> ylist;
	private String title;
	private String subtitle;
	private String yunitname;
	private String xunitname;
	private Map<String,Integer> yloction;
	
	@Override
	public int doStartTag() throws JspException {
		return BodyTag.EVAL_BODY_BUFFERED;
	}
	
	 @SuppressWarnings("unchecked")
	@Override
	public int doEndTag() throws JspException {		
		 //创建GsonOption对象，即为json字符串
		 GsonOption option = new GsonOption();
		 
		 /**
		   tooltip : {
		        trigger: 'axis'
		    }
		 */
		 option.tooltip().trigger(Trigger.axis);
		 option.tooltip().axisPointer().show(true)
		                               .type(PointerType.cross)
		                               .lineStyle(new LineStyle().type(LineType.dashed).width(1));
		 
		 /**
		  * title : {
                'text':'2002全国宏观经济关联分析（GDP vs 房地产）',
                'subtext':'数据来自国家统计局'
            }
		  */
		 option.title(title, subtitle);
		 
		 /**
		    toolbox: {
		        show : true,
		        feature : {
		            mark : {show: true},
		            dataZoom : {show: true},
		            dataView : {show: true},
		            magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
		    }
		  */
		 //工具栏
		 option.toolbox().show(true).feature(
			 //Tool.mark,
			 Tool.dataZoom,
			 //Tool.dataView,
			 //new MagicType(Magic.line, Magic.bar,Magic.stack,Magic.tiled),
			 //Tool.restore,
			 Tool.saveAsImage);
		 option.calculable(true);
		 option.dataZoom().show(true).realtime(true).start(0).end(100);
		 
		 /**
		  *     xAxis : [
			        {
			            type: 'value'
			        }
			    ]
		  */
		 //X轴数据设置类型
		 ValueAxis valueAxis = new ValueAxis();
		 valueAxis.type(AxisType.value);
		 option.xAxis(valueAxis);
		 
		//Y轴数据设置类型
		 CategoryAxis categoryAxis = new CategoryAxis();
		 categoryAxis.type(AxisType.value);
		 option.yAxis(categoryAxis);
		 
		 for(String xtitle:ylist.keySet()){
			 option.legend().data(xtitle);
		 }
		 
		 for(String mapkey:ylist.keySet()){
			 Line line = new Line();
			 line.name(mapkey).type(SeriesType.line).symbol(Symbol.none);
			 Object[][] dataArr= (Double[][])ylist.get(mapkey);
			 for(int num=0;num<dataArr.length;num++){
				 line.data().add(dataArr[num]);
			 }
			
//			if (yloction != null && yloction.get(mapkey) != null) {
//				line.yAxisIndex(yloction.get(mapkey));
//			} else {
//				line.yAxisIndex(0);
//			}
			 option.series(line);
		 }
		 try {
			this.pageContext.getOut().write(option.toString());
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Tag.EVAL_PAGE;//继续处理页面
	}

	@Test
	public void test(){
		 GsonOption option = new GsonOption();
		 option.tooltip().trigger(Trigger.axis);
		 option.title("title", "subtitle");
		 option.toolbox().show(true).feature(
				 Tool.mark,
				 Tool.dataZoom,
				 Tool.dataView,
				 new MagicType(Magic.line, Magic.bar,Magic.stack,Magic.tiled),
				 Tool.restore,
				 Tool.saveAsImage);
			 option.calculable(true);
			 option.dataZoom().show(true).realtime(true).start(0).end(100);
		 
		 
	 }

	public Map<String, Double[][]> getYlist() {
		return ylist;
	}

	public void setYlist(Map<String, Double[][]> ylist) {
		this.ylist = ylist;
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

