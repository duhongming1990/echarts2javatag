package com.hrhx.tag;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.junit.Test;

import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.AxisType;
import com.github.abel533.echarts.code.Magic;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.feature.MagicType;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Line;
 
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EChartsDoubleNumberShaftTag extends BodyTagSupport{
	private static final long serialVersionUID = 1L;
	private Map<String,List<Double>> items;
	private List<String> xlist;
	private String title;
	private String subtitle;
	private String yunitname;
	private String xunitname;
	private Map<String,Integer> yloction;
	
	@Override
	public int doStartTag() throws JspException {
		return BodyTag.EVAL_BODY_BUFFERED;
	}
	
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
			 Tool.mark,
			 Tool.dataZoom,
			 Tool.dataView,
			 new MagicType(Magic.line, Magic.bar,Magic.stack,Magic.tiled),
			 Tool.restore,
			 Tool.saveAsImage);
		 option.calculable(true);
		 option.dataZoom().show(true).realtime(true).start(0).end(100);
		 
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
		 
		 option.legend().data("数据1");
		 option.legend().data("数据2");
		 
		 Line line = new Line();
		 int a[][]=new int [2][2];
		 a[0][0]=1;
		 a[0][1]=2;
		 //line.name("数据1").type(SeriesType.line).data(a);
		 option.series(line);
		 System.out.println(option.toPrettyString());
		 
	 }
}  
