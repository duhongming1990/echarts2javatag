package com.hrhx.scheduled;

import java.sql.SQLException;
import java.util.Random;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;

import com.hrhx.socket.PIWebAPIWebSocketBF;

@Component
public class DataScheduledTask{
	
	@Bean  
	public PIWebAPIWebSocketBF infoHandlerBF(){  
	    return new PIWebAPIWebSocketBF();  
	} 
	/**
	 * 将重要的核心实时数据存储到内存数据库Redis中
	 * @throws SQLException 
	 */
	@Scheduled(cron="0/2 * *  * * ? ")   //每10秒执行一次
	public void piToRedisPisnapshotBF(){
		Random random = new Random();
		Integer i= random.nextInt(10000);
		infoHandlerBF().sendMessageToUsers(new TextMessage(String.valueOf(i)));
		System.out.println("我是DataScheduledTask,正在发送后台推送数据："+i);
		
	}
	
}
