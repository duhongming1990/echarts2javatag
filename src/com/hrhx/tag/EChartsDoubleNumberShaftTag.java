package com.hrhx.tag;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;

import org.junit.Test;

import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.AxisType;
import com.github.abel533.echarts.code.Magic;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.feature.MagicType;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Line;
 

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
		 //����GsonOption���󣬼�Ϊjson�ַ���
		 GsonOption option = new GsonOption();
		 
		 /**
		   tooltip : {
		        trigger: 'axis'
		    }
		 */
		 option.tooltip().trigger(Trigger.axis);
		 
		 /**
		  * title : {
                'text':'2002ȫ����۾��ù���������GDP vs ���ز���',
                'subtext':'�������Թ���ͳ�ƾ�'
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
		 //������
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
		return Tag.EVAL_PAGE;//��������ҳ��
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
		 //X��������������
		 ValueAxis valueAxis = new ValueAxis();
		 valueAxis.type(AxisType.value);
		 
		 option.legend().data("����1");
		 option.legend().data("����2");
		 
		 Line line = new Line();
		 int a[][]=new int [2][2];
		 a[0][0]=1;
		 a[0][1]=2;
		 //line.name("����1").type(SeriesType.line).data(a);
		 option.series(line);
		 System.out.println(option.toPrettyString());
		 
	 }

	public Map<String, List<Double>> getItems() {
		return items;
	}

	public void setItems(Map<String, List<Double>> items) {
		this.items = items;
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

	public Map<String, Integer> getYloction() {
		return yloction;
	}

	public void setYloction(Map<String, Integer> yloction) {
		this.yloction = yloction;
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
}  
