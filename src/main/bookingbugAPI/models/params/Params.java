package bookingbugAPI.models.params;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Params {

	private int page = 1;
    private int per_page = 100;

	public Params() {}

	public Params(int page) {
		this.page = page;
	}

	public Params(Map<String, String> args){
		setNotNullStringMap(args);
	}

	public static Params withPagination(int page, int per_page){
		Params params = new Params();
		params.page = page;
		params.per_page = per_page;
		return params;
	}

	public static Params withPagination(int page){
		Params params = new Params();
		params.page = page;
		return params;
	}

	public Map<String, String> getNotNullStringMap() {
	    Map<String, String> result = new HashMap<String, String>();
	    Field[] declaredFields = this.getClass().getDeclaredFields();
	    for (Field field : declaredFields) {
			try {
				if(field.getType() == String.class && (String)field.get(this) != null)
					result.put(field.getName(), (String)field.get(this));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}

		}
	    return result;
	}

	public void setNotNullStringMap(Map<String, String> map) {

		Field[] declaredFields = this.getClass().getDeclaredFields();
		for (Field field : declaredFields) {
			try {
				if(field.getType() == String.class && map.containsKey(field.getName()) && map.get(field.getName()) != null)
					field.set(this, map.get(field.getName()));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}

		}
	}

	public Map<String, String> getParams(){
		return getNotNullStringMap();
	}

	public Map<String, Object> getParamsMapObj() {
		Map<String, Object> objectMap = new HashMap<>();
		objectMap.putAll(getParams());
		return objectMap;
	}

    public int getPage() {
        return page;
    }

    public int getPer_page() {
        return per_page;
    }

	@Override
	public String toString(){
		String json = "";
		try {
			json = new ObjectMapper().writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}

}