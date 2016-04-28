package bookingbugAPI.models.params;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Params {

	private int page = 1;
    private int per_page = 100;

	public boolean hasJson = false;
	private String jsonContent;

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

	public void setJson(String json) {
		hasJson = true;
		jsonContent = json;
	}

	public Map<String, String> getNotNullStringMap() {
	    Map<String, String> result = new HashMap<>();
	    Field[] declaredFields = this.getClass().getDeclaredFields();
	    for (Field field : declaredFields) {
			try {
				if(field.get(this) != null)
					result.put(field.getName(), field.get(this).toString());
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}

		}

		//Add pagination params
		result.put("page", String.valueOf(getPage()));
		result.put("per_page", String.valueOf(getPer_page()));

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

	/**
	 * Get parameters. If hasJson is true (setJson method has been called) then the parameters will be extracted from
	 * json string. Otherwise all non null declared fields of this object will be included
	 *
	 * @return Generic Map with parameters
     */
	public Map getParams(){
		if(!hasJson)
			// Return default map
			return getNotNullStringMap();

		// Return map from jsonContent
		ObjectMapper mapper = new ObjectMapper();
		HashMap map = new HashMap<>();
		try {
			map = mapper.readValue(jsonContent, HashMap.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}

    /**
     * @see Params#getParams()
     * @return
     */
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

	public Params setPerPage(int per_page) {
		this.per_page = per_page;
		return this;
	}

	public Params setPage(int page) {
		this.page = page;
		return this;
	}

	@Override
	public String toString(){
		String jsonThis = "";
		try {
            jsonThis = new ObjectMapper().writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return hasJson ? jsonContent : jsonThis;
	}
}