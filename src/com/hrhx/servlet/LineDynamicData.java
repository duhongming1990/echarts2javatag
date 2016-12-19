package com.hrhx.servlet;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.comet4j.core.CometContext;
import org.comet4j.core.CometEngine;

import com.hrhx.bean.ChinaWeatherDataBean;
  
public class LineDynamicData implements ServletContextListener {  
        private static final String CHANNEL = "dynamicLine";  
        public void contextInitialized(ServletContextEvent arg0) {  
                CometContext cc = CometContext.getInstance();  
                cc.registChannel(CHANNEL);//注册应用的channel  
                Thread helloAppModule = new Thread(new Comet4jThread(), "Sender App Module");  
                helloAppModule.setDaemon(true);  
                helloAppModule.start();  
        }  
  
        class Comet4jThread implements Runnable {  
                public void run() {  
                        while (true) {  
                                try {  
                                        Thread.sleep(1000);  
                                } catch (Exception ex) {  
                                        ex.printStackTrace();  
                                }  
                                CometEngine engine = CometContext.getInstance().getEngine();  
                                //engine.sendToAll(CHANNEL, Runtime.getRuntime().freeMemory()/1024);
                                ChinaWeatherDataBean chinaWeatherDataBean = new ChinaWeatherDataBean();
                                chinaWeatherDataBean.setBeijing_maxtemp(Runtime.getRuntime().freeMemory()/1024/1024/1024);
                                Date date = new Date();
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                chinaWeatherDataBean.setDatestr(sdf.format(date));
                                engine.sendToAll(CHANNEL, chinaWeatherDataBean);  
                        }  
                }  
        }  
  
        public void contextDestroyed(ServletContextEvent arg0) {  
  
        }  
}  