package com.git.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class FastJsonDemo {

	public static void main(String[] args) {
		String text = "{\"name\":\"老张头\", \"age\":66}";
		JSONObject json = JSON.parseObject(text);
		System.out.println(json.get("name"));

		/** 将JSON字符串转换为JavaBean对象 **/
		User user = JSON.parseObject(text, User.class);
		System.out.println(user);

		/** 将JavaBean对象转换为JSON字符串 **/
		String jsonObject = JSON.toJSONString(user);
		System.out.println(jsonObject);

		/** 将JavaBean对象转换为JSON对象 **/
		JSONObject userJson = (JSONObject) JSON.toJSON(user);
		System.out.println(userJson);
	}
}

class User {
	private String name;
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}