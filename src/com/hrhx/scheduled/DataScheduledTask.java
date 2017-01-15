package com.hrhx.scheduled;

import java.sql.SQLException;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;

import com.hrhx.socket.PIWebAPIWebSocketBF;

@Component
public class DataScheduledTask {
	
	@Bean  
	public PIWebAPIWebSocketBF infoHandlerBF(){  
	    return new PIWebAPIWebSocketBF();  
	} 
	/**
	 * 将重要的核心实时数据存储到内存数据库Redis中
	 * @throws SQLException 
	 */
	public void piToRedisPisnapshotBF(){
		infoHandlerBF().sendMessageToUsers(new TextMessage("5"));
		System.out.println("我是循环的！！！");
		
	}
	
}
