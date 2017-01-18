package com.hrhx.scheduled;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;

import com.google.common.collect.Lists;
import com.hrhx.bean.SocketDataGauge;
import com.hrhx.bean.SocketDataLine;
import com.hrhx.socket.GaugeDataWebSocket;
import com.hrhx.socket.LineDataWebSocket;
import com.hrhx.util.JsonUtil;

@Component
public class DataScheduledTask{
	
	@Autowired
	private JsonUtil jsonUtil;
	
	@Bean  
	public GaugeDataWebSocket getGaugeDataWebSocket(){  
	    return new GaugeDataWebSocket();  
	} 
	/**
	 * 将重要的核心实时数据存储到内存数据库Redis中
	 * @throws SQLException 
	 */
	@Scheduled(cron="0/2 * *  * * ? ")   //每2秒执行一次
	public void getGaugeDataScheduledTask(){
		
		List<SocketDataGauge> list = Lists.newArrayList();
		
		Random random = new Random();
		
		Double i1 = random.nextDouble()*10000;
		SocketDataGauge socketDataGauge1 = new SocketDataGauge("temperature1",i1);
		
		Double i2 = random.nextDouble()*10000;
		SocketDataGauge socketDataGauge2 = new SocketDataGauge("temperature2",i2);
		
		Double i3 = random.nextDouble()*10000;
		SocketDataGauge socketDataGauge3 = new SocketDataGauge("temperature3",i3);
		
		Double i4 = random.nextDouble()*10000;
		SocketDataGauge socketDataGauge4 = new SocketDataGauge("temperature4",i4);
		
		list.add(socketDataGauge1);
		list.add(socketDataGauge2);
		list.add(socketDataGauge3);
		list.add(socketDataGauge4);
		
		String jsonStr = jsonUtil.toStrings(list);
		getGaugeDataWebSocket().sendMessageToUsers(new TextMessage(jsonStr));
		System.out.println("我是DataScheduledTask,getGaugeDataScheduledTask()正在发送后台推送数据："+jsonStr);
		
	}
	
	@Bean  
	public LineDataWebSocket getLineDataWebSocket(){  
	    return new LineDataWebSocket();  
	} 
	/**
	 * 将重要的核心实时数据存储到内存数据库Redis中
	 * @throws SQLException 
	 */
	@Scheduled(cron="0/1 * *  * * ? ")   //每1秒执行一次
	public void getLineDataScheduledTask(){
		Random random = new Random();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SocketDataLine socketDataLine = new SocketDataLine(sdf.format(new Date()),random.nextDouble()*10,random.nextDouble()*10);
		String jsonStr = jsonUtil.toStrings(socketDataLine);
		getLineDataWebSocket().sendMessageToUsers(new TextMessage(jsonStr));
		System.out.println("我是DataScheduledTask,getLineDataScheduledTask()正在发送后台推送数据："+jsonStr);
		
	}

	
}
