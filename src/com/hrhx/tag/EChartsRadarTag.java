package com.hrhx.tag;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;

import com.github.abel533.echarts.Polar;
import com.github.abel533.echarts.code.Orient;
import com.github.abel533.echarts.code.SeriesType;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.code.X;
import com.github.abel533.echarts.code.Y;
import com.github.abel533.echarts.data.Data;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Line;

public class EChartsRadarTag extends BodyTagSupport{
	private static final long serialVersionUID = 1L;
	private String title;
	private String subtitle;
	private Integer typeNum;
	private List<Map<String,Object>> legendList;
	@Override
	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub
		return BodyTag.EVAL_BODY_BUFFERED;
	}
	
	@Override
	public int doEndTag() throws JspException {		
		 // TODO Auto-generated method stub
		 //����GsonOption���󣬼�Ϊjson�ַ���
		 GsonOption option = new GsonOption();
		 /**
		  *  title: {
                text: 'ʵʱ����õ��ͼ',
                subtext: 'Ԥ��ʱ��:'
             },
		  */
		 option.title(title, subtitle);
		 /**
		  *  tooltip: {
                trigger: 'axis'
             },
		  */
		 option.tooltip().trigger(Trigger.axis);
		 /**
		  * polar: [
               {
                   indicator: [
                       { text: '������N��', max: 100 },
                       { text: '������NW��', max: 100 },
                       { text: '������W��', max: 100 },
                       { text: '���ϣ�SW��', max: 100 },
                       { text: '���ϣ�S��', max: 100 },
                       { text: '���ϣ�SE��', max: 100 },
                       { text: '������E��', max: 100 },
                       { text: '������NE��', max: 100 }
                   ]
               }
            ]
		  */
		//������
		 option.toolbox().show(true).feature(
			 //Tool.mark,
			 //Tool.dataView,
			 Tool.saveAsImage
			 //new MagicType(Magic.line, Magic.bar,Magic.stack,Magic.tiled),
			 //Tool.dataZoom,
			 //Tool.restore
		 );
		 Polar polar= new Polar();
		 if(typeNum==8){ 
				 polar.indicator(new Data().text("������N��").max(100))
				 	  .indicator(new Data().text("������NW��").max(100))
				 	  .indicator(new Data().text("������W��").max(100))
				 	  .indicator(new Data().text("���ϣ�SW��").max(100))
				 	  .indicator(new Data().text("���ϣ�S��").max(100))
				 	  .indicator(new Data().text("���ϣ�SE��").max(100))
				 	  .indicator(new Data().text("������E��").max(100))
				 	  .indicator(new Data().text("������NE)").max(100));
				 
		 }else if(typeNum==16){
			 polar.indicator(new Data().text("������N��").max(100))
			  	  .indicator(new Data().text("��������NNW��").max(100))
			 	  .indicator(new Data().text("������NW��").max(100))
			 	  .indicator(new Data().text("��������WNW��").max(100))
			 	  .indicator(new Data().text("������W��").max(100))
			 	  .indicator(new Data().text("��������WSW��").max(100))
			 	  .indicator(new Data().text("���ϣ�SW��").max(100))
			 	  .indicator(new Data().text("�����ϣ�SSW��").max(100))
			 	  .indicator(new Data().text("���ϣ�S��").max(100))
			 	  .indicator(new Data().text("�϶��ϣ�SSE��").max(100))
			 	  .indicator(new Data().text("���ϣ�SE��").max(100))
			 	  .indicator(new Data().text("���϶���ESE��").max(100))
			 	  .indicator(new Data().text("������E��").max(100))
			 	  .indicator(new Data().text("��������ENE��").max(100))
			 	  .indicator(new Data().text("������NE)").max(100))
			 	  .indicator(new Data().text("��������NNE��").max(100));
		 }
		 option.polar(polar);
		 option.calculable(true);
		 
		 /**
		  *  legend: {
                orient: 'horizontal',
                x: 'left',
                y: 'bottom',
                data: [
                <c:forEach var="item" items="${towerList}" varStatus="status">
					'${item.tower_mater}�׷���',
				</c:forEach>
				]
            },
		  */
		 if(legendList!=null){
			 for(Map<String,Object> legendMap:legendList){
				 option.legend().orient(Orient.horizontal).x(X.left).y(Y.bottom).data(legendMap.get("title").toString());
				 Line line = new Line();
				 Data data=new Data().name("Ԥ�����Ƶ�ʣ�%��");
				 Object[] dataArr = (Double[])legendMap.get("dataArr");
				 data.value(dataArr);
				 line.name(legendMap.get("title").toString()).type(SeriesType.radar).data(data);
				 option.series(line);
			 }
		 }
		 try {
			this.pageContext.getOut().write(option.toString());
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Tag.EVAL_PAGE;//��������ҳ��
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

	public Integer getTypeNum() {
		return typeNum;
	}

	public void setTypeNum(Integer typeNum) {
		this.typeNum = typeNum;
	}

	public List<Map<String, Object>> getLegendList() {
		return legendList;
	}

	public void setLegendList(List<Map<String, Object>> legendList) {
		this.legendList = legendList;
	}

}  
