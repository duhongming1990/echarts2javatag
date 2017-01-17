package com.hrhx.util;


import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.stream.JsonReader;
/**
 *   
 *Google浏览器插件-yformater
  
    直接在浏览器中格式化web server接口返回的json数据，彻底抛弃“在线”工具！

  //如果中文返回出现？？字符
  response.setCharacterEncoding("UTF-8");
  
  //如果返回的中文是“烇湫”这种乱码，说明浏览器的解析问题
  response.setHeader("Content-type", "text/json;charset=UTF-8");
 * @author duhongming
 *
 */
@Component
public class JsonUtil {
	
	/**
	 * 将JavaBean转化为jsonStr
	 * @param obj
	 * @return
	 */
	public String toStrings(Object obj) {
		Gson gson = new GsonBuilder()
				.disableHtmlEscaping()
				.registerTypeAdapter(java.util.Date.class,new UtilDateSerializer())
				.setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		return gson.toJson(obj);
	}

	/**
	 * 将jsonStr转化为JavaBean
	 * @param <T>
	 * @param json
	 * @param type
	 * @return
	 */
	public <T> T toObj(String json, Type type) {
		//T t = new com.google.gson.reflect.TypeToken<T>() {}.getType();
		Gson gson = new GsonBuilder()
				.disableHtmlEscaping()
				.registerTypeAdapter(java.util.Date.class,new UtilDateDeserializer())
				.setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		return gson.fromJson(json, type);
	}

	/**
	 * 将jsonStr转化成Map<String, String>
	 * @param json
	 * @return
	 */
	public Map<String, String> toMap(String json) {
		JsonReader reader = new JsonReader(new StringReader(json));
		Map<String, String> map = new HashMap<String, String>();
		try {
			reader.beginObject();
			while (reader.hasNext()) {
				map.put(reader.nextName(), reader.nextString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 将jsonStr转化成Map<String, String>
	 * @param result
	 * @return
	 */
	public Map<String, String> getMapData(String result) {

		Map<String, String> map = new HashMap<String, String>();
		Type type = new com.google.gson.reflect.TypeToken<Map<String, String>>() {}.getType();
		map = toObj(result, type);
		return map;
	}

	/**
	 * 将jsonStr转化成List<Map<String, String>>
	 * @param result
	 * @return
	 */
	public List<Map<String, String>> getMapListData(String result) {
		List<Map<String, String>> ml = new ArrayList<Map<String, String>>();
		Type type = new com.google.gson.reflect.TypeToken<List<Map<String, String>>>() {}.getType();
		ml = toObj(result, type);
		return ml;
	}
	
	/**
	 * 序列化日期类型的实现类
	 * @author Administrator
	 *
	 */
	class UtilDateSerializer implements JsonSerializer<java.util.Date> {

		public JsonElement serialize(java.util.Date src, Type typeOfSrc,
				JsonSerializationContext context) {
			return new JsonPrimitive(src.getTime());
		}

	}
	
	/**
	 * 反序列化日期类型的实现类
	 * @author Administrator
	 */
	class UtilDateDeserializer implements JsonDeserializer<java.util.Date> {
		public java.util.Date deserialize(JsonElement json, Type typeOfT,
				JsonDeserializationContext context) throws JsonParseException {
			return new java.util.Date(json.getAsJsonPrimitive().getAsLong());
		}
	}
}
