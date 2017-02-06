package com.hrhx.dao;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.hrhx.bean.ChinaWeatherDataBean;
import com.hrhx.bean.FundAnalysisBean;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.dao.RawRowMapper;
import com.j256.ormlite.support.ConnectionSource;

public class FundAnalysisDao {
	
	public void insert(FundAnalysisBean fundAnalysisBean) throws SQLException{
		ConnectionSource  connectionSource = Config.getConnectionSource();
		Dao<FundAnalysisBean,String> fundAnalysisDao =
			     DaoManager.createDao(connectionSource, FundAnalysisBean.class);
		fundAnalysisDao.create(fundAnalysisBean);
	}
	
	public List<FundAnalysisBean> getAll(String query){
		ConnectionSource  connectionSource = Config.getConnectionSource();
		Dao<FundAnalysisBean, String> dao;
		try {
			dao = DaoManager.createDao(connectionSource,FundAnalysisBean.class);
			GenericRawResults<FundAnalysisBean> rawResults = dao.queryRaw(query,new RawRowMapper<FundAnalysisBean>(){
				@Override
				public FundAnalysisBean mapRow(String[] columnNames,
						String[] resultColumns) throws SQLException {
					FundAnalysisBean fundAnalysisBean = new FundAnalysisBean();
					//得到类对象  
				    @SuppressWarnings("rawtypes")
					Class fundAnalysisClass = (Class) fundAnalysisBean.getClass();
				    for(int i=0;i<columnNames.length;i++){
				    	Field f;
						try {
							f = fundAnalysisClass.getDeclaredField(columnNames[i]);
							f.setAccessible(true);
				    		if(columnNames[i].equals(f.getName())&&resultColumns[i]!=null){
				    			String type = f.getType().toString();// 得到此属性的类型
				    			if (type.endsWith("String")) {  
				    				f.set(fundAnalysisBean, resultColumns[i]);
				    			}else if(type.endsWith("int")|| type.endsWith("Integer")){
				    				f.set(fundAnalysisBean, Integer.parseInt(resultColumns[i]));
				    			}else if(type.endsWith("double")|| type.endsWith("Double")){
				    				f.set(fundAnalysisBean, Double.parseDouble(resultColumns[i]));
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
					return fundAnalysisBean;
				}
				
			});
			List<FundAnalysisBean> results = rawResults.getResults();
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
