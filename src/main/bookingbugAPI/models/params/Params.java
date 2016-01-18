package bookingbugAPI.models.params;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public abstract class Params {

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
}