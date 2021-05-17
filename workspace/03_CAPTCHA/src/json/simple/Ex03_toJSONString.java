package json.simple;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Ex03_toJSONString {	// JSONObject를 -> String으로 바꿔준다.

	public static void main(String[] args) {
		
		/*
		JSONObject obj = new JSONObject();
		
		obj.put("name", "브레드");
		obj.put("age", 50);		// -> hashMap과 사용법이 같다.
		
		String requestData = obj.toJSONString();
		System.out.println(requestData);
		*/
		
		JSONObject obj1 = new JSONObject();		// JSONObject<String, Object> 원래 이렇게 해야되는데, 오류나서 생략. 그래서 아래 노란줄을 대신 얻음
		obj1.put("name", "브레드");
		obj1.put("age", 50);	
		
		JSONObject obj2 = new JSONObject();
		obj2.put("name", "초코");
		obj2.put("age", 30);	
	
		JSONArray list = new JSONArray();
		list.add(obj1);
		list.add(obj2);
		
		String requestData = list.toJSONString();
		System.out.println(requestData);
		
	}

}
