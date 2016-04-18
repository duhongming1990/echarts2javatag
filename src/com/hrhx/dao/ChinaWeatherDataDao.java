package com.hrhx.dao;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.List;

import com.hrhx.bean.ChinaWeatherDataBean;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.dao.RawRowMapper;
import com.j256.ormlite.support.ConnectionSource;

public class ChinaWeatherDataDao {
	
	private ConnectionSource  connectionSource = Config.getConnectionSource();
	
	public List<ChinaWeatherDataBean> getAll(String query){
		Dao<ChinaWeatherDataBean, String> dao;
		try {
			dao = DaoManager.createDao(connectionSource,ChinaWeatherDataBean.class);
			GenericRawResults<ChinaWeatherDataBean> rawResults = dao.queryRaw(query,new RawRowMapper<ChinaWeatherDataBean>(){
				@Override
				public ChinaWeatherDataBean mapRow(String[] columnNames,
						String[] resultColumns) throws SQLException {
					ChinaWeatherDataBean chinaWeatherDataBean = new ChinaWeatherDataBean();
					//得到类对象  
				    @SuppressWarnings("rawtypes")
					Class chinaAreaDataClass = (Class) chinaWeatherDataBean.getClass();
				    for(int i=0;i<columnNames.length;i++){
				    	Field f;
						try {
							f = chinaAreaDataClass.getDeclaredField(columnNames[i]);
							f.setAccessible(true);
				    		if(columnNames[i].equals(f.getName())&&resultColumns[i]!=null){
				    			String type = f.getType().toString();// 得到此属性的类型
				    			if (type.endsWith("String")) {  
				    				f.set(chinaWeatherDataBean, resultColumns[i]);
				    			}else if(type.endsWith("int")|| type.endsWith("Integer")){
				    				f.set(chinaWeatherDataBean, Integer.parseInt(resultColumns[i]));
				    			}else if(type.endsWith("double")|| type.endsWith("Double")){
				    				f.set(chinaWeatherDataBean, Double.parseDouble(resultColumns[i]));
				    			}			
				    		}
						} catch (SecurityException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (NoSuchFieldException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				    }
					return chinaWeatherDataBean;
				}
				
			});
			List<ChinaWeatherDataBean> results = rawResults.getResults();
			return results;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			try {
				connectionSource.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}
}
