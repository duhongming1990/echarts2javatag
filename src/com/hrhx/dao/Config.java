package com.hrhx.dao;

import java.io.File;
import java.sql.SQLException;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

public class Config {
	private static final String databaseUrl ="jdbc:sqlite:china_weather.db";
	public static ConnectionSource getConnectionSource(){
		String rootClassPath = PathKit.getRootClassPath()+File.separator;
		String[] databaseUrlArr = databaseUrl.split(":");
		try {
			System.out.println("正在加载数据库配置："+databaseUrlArr[0]+":"+databaseUrlArr[1]+":"+rootClassPath+databaseUrlArr[2]);
			return new JdbcConnectionSource(databaseUrlArr[0]+":"+databaseUrlArr[1]+":"+rootClassPath+databaseUrlArr[2]);
		} catch (SQLException e) {
			return null;
		}		
	}
}
