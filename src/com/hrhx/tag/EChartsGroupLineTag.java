package com.hrhx.tag;

import java.io.IOException;
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
import com.github.abel533.echarts.code.Orient;
import com.github.abel533.echarts.code.SeriesType;
import com.github.abel533.echarts.code.Symbol;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.code.X;
import com.github.abel533.echarts.code.Y;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Line;

public class EChartsGroupLineTag extends BodyTagSupport {
	private static final long serialVersionUID = 1L;
	private List<String> timelinetitle;
	private List<Map<String,List<Double>>> timeline;
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
		GsonOption option = new GsonOption();
		
		GsonOption options = new GsonOption();
		/**
		 *  timeline:{
		        data:[
		            '2002-01-01','2003-01-01','2004-01-01','2005-01-01','2006-01-01',
		            '2007-01-01','2008-01-01','2009-01-01','2010-01-01','2011-01-01'
		        ],
		        label : {
		            formatter : function(s) {
		                return s.slice(0, 4);
		            }
		        },
		        autoPlay : true,
		        playInterval : 1000
		    },
		 */
		option.timeline().autoPlay(true).playInterval(1000)
						 .label().formatter("function(s){return s.slice(0, 4);}");
		for(String key:timelinetitle){
			option.timeline().data(key);
		}
		/**
		 *  title : {
                'text':'2002全国宏观经济指标',
                'subtext':'数据来自国家统计局'
            },
		 */
		options.title(title, subtitle);
		/**
		 * tooltip : {'trigger':'axis'},
		 */
		options.tooltip().trigger(Trigger.axis);
		/**
		 * legend : {
                x:'right',
                'data':['GDP','金融','房地产','第一产业','第二产业','第三产业'],
                'selected':{
                    'GDP':true,
                    '金融':false,
                    '房地产':true,
                    '第一产业':false,
                    '第二产业':false,
                    '第三产业':false
                }
            },
		 */
		options.legend().x(X.right);
		for (String key : ylist.keySet()) {
			options.legend().data(key);
		}
		/**
		 * toolbox : {
                'show':true, 
                orient : 'vertical',
                x: 'right', 
                y: 'center',
                'feature':{
                    'mark':{'show':true},
                    'dataView':{'show':true,'readOnly':false},
                    'magicType':{'show':true,'type':['line','bar','stack','tiled']},
                    'restore':{'show':true},
                    'saveAsImage':{'show':true}
                }
            },
            calculable : true,
		 */
		// 工具栏
		options.toolbox().orient(Orient.vertical)
						 .x(X.right)
						 .y(Y.center)
						 .show(true).feature(
						// Tool.mark,
//						Tool.dataView,
						Tool.saveAsImage,
						// new MagicType(Magic.line, Magic.bar,Magic.stack,Magic.tiled),
						Tool.dataZoom, Tool.restore);
//		options.calculable(true);
//		options.dataZoom().show(true).realtime(true).start(0).end(100);
		/**
		 *  xAxis : [{
                'type':'category',
                'axisLabel':{'interval':0},
                'data':[
                    '北京','\n天津','河北','\n山西','内蒙古','\n辽宁','吉林','\n黑龙江',
                    '上海','\n江苏','浙江','\n安徽','福建','\n江西','山东','\n河南',
                    '湖北','\n湖南','广东','\n广西','海南','\n重庆','四川','\n贵州',
                    '云南','\n西藏','陕西','\n甘肃','青海','\n宁夏','新疆'
                ]
            }],
		 */
		// X轴数据封装并解析
		ValueAxis valueAxis = new ValueAxis();
		for (String s : xlist) {
			valueAxis.type(AxisType.category).data(s);
		}
		// X轴单位
		valueAxis.name(xunitname);
		options.xAxis(valueAxis);
		/**
		 * yAxis : [
                {
                    'type':'value',
                    'name':'GDP（亿元）',
                    'max':53500
                },
                {
                    'type':'value',
                    'name':'其他（亿元）'
                }
            ],
		 */
		// Y轴数据封装并解析
		String[] unitNameArray = yunitname.split(",");
		for (String s : unitNameArray) {
			CategoryAxis categoryAxis = new CategoryAxis();
			categoryAxis.type(AxisType.value);
			options.yAxis(categoryAxis.name(s));
		}
		
	
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
					line.type(SeriesType.line).data(d);
				} else {
					line.type(SeriesType.line).data(0);
				}

				if (yloction != null && yloction.get(key) != null) {
					line.type(SeriesType.line).yAxisIndex(yloction.get(key));
					line.symbol(Symbol.none);
				} else {
					line.type(SeriesType.line).yAxisIndex(0);
					line.symbol(Symbol.none);
				}

			}
			options.series(line);
		}
		option.options(options);
		for(int ii=1;ii<timelinetitle.size();ii++){
			Map<String,List<Double>> timelineData= timeline.get(ii-1);
			GsonOption timeLineOption = new GsonOption();
			
			timeLineOption.title(timelinetitle.get(ii)+title.substring(4, title.length()),subtitle);
			for (String key : timelineData.keySet()) {
				// 遍历list得到数据
				List<Double> list = timelineData.get(key);
				Line line = new Line().name(key);
				for (Double d : list) {
					// KW与MW单位的转换
					// if(settingGlobal!=null&&settingGlobal.getIskw()==0){
					// d = d/1000;
					// }
					// 数据为空的话会报错，为空则为零
					if (d != null) {
						line.type(SeriesType.line).data(d);
					} else {
						line.type(SeriesType.line).data(0);
					}

					if (yloction != null && yloction.get(key) != null) {
						line.type(SeriesType.line).yAxisIndex(yloction.get(key));
						line.symbol(Symbol.none);
					} else {
						line.type(SeriesType.line).yAxisIndex(0);
						line.symbol(Symbol.none);
					}

				}
				timeLineOption.series(line);
			}
			option.options(timeLineOption);
		}
		try {
			this.pageContext.getOut().write(option.toPrettyString());

		} catch (IOException e) {
			System.err.print(e);
		}
		return Tag.EVAL_PAGE;// 继续处理页面
	}

	@Test
	public void test() {
		
	}
	

	public List<String> getTimelinetitle() {
		return timelinetitle;
	}

	public void setTimelinetitle(List<String> timelinetitle) {
		this.timelinetitle = timelinetitle;
	}

	public List<Map<String, List<Double>>> getTimeline() {
		return timeline;
	}

	public void setTimeline(List<Map<String, List<Double>>> timeline) {
		this.timeline = timeline;
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
