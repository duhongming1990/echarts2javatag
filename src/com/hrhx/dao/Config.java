package com.hrhx.dao;

import java.sql.SQLException;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

public class Config {
	
	private static final String databaseUrl ="jdbc:sqlite:china_weather.db";
	
	public static ConnectionSource getConnectionSource(){
		
		try {
			return new JdbcConnectionSource(databaseUrl);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
}
