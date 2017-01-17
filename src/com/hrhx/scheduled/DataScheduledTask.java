package com.hrhx.scheduled;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;

import com.google.common.collect.Lists;
import com.hrhx.bean.SocketDataGauge;
import com.hrhx.socket.PIWebAPIWebSocketBF;
import com.hrhx.util.JsonUtil;

@Component
public class DataScheduledTask{
	
	@Autowired
	private JsonUtil jsonUtil;
	
	@Bean  
	public PIWebAPIWebSocketBF infoHandlerBF(){  
	    return new PIWebAPIWebSocketBF();  
	} 
	/**
	 * 将重要的核心实时数据存储到内存数据库Redis中
	 * @throws SQLException 
	 */
	@Scheduled(cron="0/2 * *  * * ? ")   //每2秒执行一次
	public void piToRedisPisnapshotBF(){
		
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
		infoHandlerBF().sendMessageToUsers(new TextMessage(jsonStr));
		System.out.println("我是DataScheduledTask,正在发送后台推送数据："+jsonStr);
		
	}
	
}
