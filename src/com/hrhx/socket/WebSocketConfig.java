package com.hrhx.socket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration  
@EnableWebMvc  
@EnableWebSocket  
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer{  
	@Override  
	    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {  
		 	registry.addHandler(systemWebSocketHandlerBF(),"/webSocket/data")
	        .setAllowedOrigins("*")//这个异常是跨域引起的
	        .addInterceptors(new SpringWebSocketHandlerInterceptor())
	        .withSockJS();

	        //registry.addHandler(systemWebSocketHandlerZRH(),"/webSocket/pi/zrh").addInterceptors(new WebSocketHandshakeInterceptor());  
	         //registry.addHandler(systemWebSocketHandler(), "/sockjs/webSocketServer").addInterceptors(new WebSocketHandshakeInterceptor()).withSockJS();  
	         //registry.addHandler(systemWebSocketHandler(), "/webSocketServer/sockjs").withSockJS();  
	         /*registry.addHandler(systemWebSocketHandler(),"/ws").addInterceptors(new WebSocketHandshakeInterceptor()); 
	            registry.addHandler(systemWebSocketHandler(), "/ws/sockjs").addInterceptors(new WebSocketHandshakeInterceptor()) 
	                    .withSockJS();*/  
	    } 
	@Bean  
	public WebSocketHandler systemWebSocketHandlerBF(){  
	    return new PIWebAPIWebSocketBF();  
	} 

}
