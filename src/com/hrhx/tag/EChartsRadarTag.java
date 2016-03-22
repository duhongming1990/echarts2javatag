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
		 //创建GsonOption对象，即为json字符串
		 GsonOption option = new GsonOption();
		 /**
		  *  title: {
                text: '实时风向玫瑰图',
                subtext: '预测时间:'
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
                       { text: '正北（N）', max: 100 },
                       { text: '西北（NW）', max: 100 },
                       { text: '正西（W）', max: 100 },
                       { text: '西南（SW）', max: 100 },
                       { text: '正南（S）', max: 100 },
                       { text: '东南（SE）', max: 100 },
                       { text: '正东（E）', max: 100 },
                       { text: '东北（NE）', max: 100 }
                   ]
               }
            ]
		  */
		//工具栏
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
				 polar.indicator(new Data().text("正北（N）").max(100))
				 	  .indicator(new Data().text("西北（NW）").max(100))
				 	  .indicator(new Data().text("正西（W）").max(100))
				 	  .indicator(new Data().text("西南（SW）").max(100))
				 	  .indicator(new Data().text("正南（S）").max(100))
				 	  .indicator(new Data().text("东南（SE）").max(100))
				 	  .indicator(new Data().text("正东（E）").max(100))
				 	  .indicator(new Data().text("东北（NE)").max(100));
				 
		 }else if(typeNum==16){
			 polar.indicator(new Data().text("正北（N）").max(100))
			  	  .indicator(new Data().text("北西北（NNW）").max(100))
			 	  .indicator(new Data().text("西北（NW）").max(100))
			 	  .indicator(new Data().text("西北西（WNW）").max(100))
			 	  .indicator(new Data().text("正西（W）").max(100))
			 	  .indicator(new Data().text("西南西（WSW）").max(100))
			 	  .indicator(new Data().text("西南（SW）").max(100))
			 	  .indicator(new Data().text("南西南（SSW）").max(100))
			 	  .indicator(new Data().text("正南（S）").max(100))
			 	  .indicator(new Data().text("南东南（SSE）").max(100))
			 	  .indicator(new Data().text("东南（SE）").max(100))
			 	  .indicator(new Data().text("东南东（ESE）").max(100))
			 	  .indicator(new Data().text("正东（E）").max(100))
			 	  .indicator(new Data().text("东北东（ENE）").max(100))
			 	  .indicator(new Data().text("东北（NE)").max(100))
			 	  .indicator(new Data().text("北东北（NNE）").max(100));
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
					'${item.tower_mater}米风向',
				</c:forEach>
				]
            },
		  */
		 if(legendList!=null){
			 for(Map<String,Object> legendMap:legendList){
				 option.legend().orient(Orient.horizontal).x(X.left).y(Y.bottom).data(legendMap.get("title").toString());
				 Line line = new Line();
				 Data data=new Data().name("预测风向频率（%）");
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
		return Tag.EVAL_PAGE;//继续处理页面
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
