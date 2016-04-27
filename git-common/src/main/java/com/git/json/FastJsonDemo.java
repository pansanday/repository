package com.git.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class FastJsonDemo {

	public static void main(String[] args) {
		String text = "{\"id\" : \"P360\", \"name\":\"老张头\", \"age\":66}";
		
		/** 根据JSON的key获取其相应的值 **/
		/** 注意: 这里需要解析的对象需要一个默认是无参构造函数, 否则报错**/
		JSONObject json = JSON.parseObject(text);
		System.out.println(json.get("name"));
		// 循环遍历json对象,获取其key和value的值
		for (String key : json.keySet()) {
			System.out.println(key + ":" + json.get(key));
		}

		/** 将JSON字符串转换为JavaBean对象 **/
		User user = JSON.parseObject(text, User.class);
		System.out.println(user);

		/** 将JavaBean对象转换为JSON字符串 **/
		String jsonObject = JSON.toJSONString(user);
		System.out.println(jsonObject);

		/** 将JavaBean对象转换为JSON对象 **/
		JSONObject userJson = (JSONObject) JSON.toJSON(user);
		System.out.println(userJson);
		
		/** 将数组转为JSON字符串 **/
		/** 示例一 **/
		String[] arr = { "bill", "green", "maks", "jim" };
		String jsonString = JSON.toJSONString(arr);
		System.out.println(jsonString);
		/** 示例二 **/
		Address address1 = new Address("广东省", "深圳市", "科苑南路", "580053");
		User user1 = new User("P001", "Tom", 25, address1);
		Address address2 = new Address("江西省", "南昌市", "阳明路", "330004");
		User user2 = new User("P002", "Jack", 23, address2);
		Address address3 = new Address("陕西省", "西安市", "长安南路", "710114");
		User user3 = new User("P003", "Suma", 27, address3);
		User[] userArr = { user1, user2, user3 };
		String jsonString2 = JSON.toJSONString(userArr, true);
		System.out.println(jsonString2);
		
		/** 将JSON字符串转为数组 **/
		/** 注意: 这里要保证jsonText是这种数组格式, 否则会报syntax error错误 **/
		/** 示例一 **/
		String jsonText = "[\"bill\",\"green\",\"maks\",\"jim\"]";
		JSONArray parseArray = JSON.parseArray(jsonText);
		System.out.println(parseArray);
		/** 示例二 **/
		String jsonText2 = "[{\"age\":16,\"id\":\"P001\",\"name\":\"Tom\"},{\"age\":21,\"id\":\"P002\",\"name\":\"Jack\"},{\"age\":20,\"id\":\"P003\",\"name\":\"Suma\"}]";  
        JSONArray parseArray2 = JSON.parseArray(jsonText2);
        System.out.println(parseArray2);  
		
        /** 将list转为JSON对象**/
        List<User> list = new ArrayList<User>();
		list.add(user1);
		list.add(user2);
		list.add(user3);
		String jsonString3 = JSON.toJSONString(list, true);
		System.out.println(jsonString3);
		
		/** 将map转为JSON对象**/
	    Map<String, Address> map = new HashMap<String, Address>();  
		map.put("address1", address1);
		map.put("address2", address2);
		map.put("address3", address3);
		String jsonString4 = JSON.toJSONString(map, true);
	    System.out.println(jsonString4);
	    
	    /** 复杂点json示例 **/
	    String str = "{ "
				+ "\"3\": [{ \"name\": \"黄金搭档\", \"index\": 1, \"value\": \"1.4561\", \"allValue\": \"1.4561\", \"date\": \"2016-03-14\", \"updateDate\": \"2016-04-07\", \"updateValue\": \"-0.0003\", \"displayName\": \"黄金搭档\" }, { \"name\": \"红金搭档\", \"index\": 1, \"value\": \"1.4561\", \"allValue\": \"1.4561\", \"date\": \"2016-03-15\", \"updateDate\": \"2016-04-07\", \"updateValue\": \"0.0000\", \"displayName\": \"红金搭档\" }], "
				+ "\"5\": [{ \"name\": \"脑白金\", \"index\": 2, \"value\": \"1.2675\", \"allValue\": \"1.2675\", \"date\": \"2016-03-11\", \"updateDate\": \"2016-04-07\", \"updateValue\": \"-0.0106\", \"displayName\": \"脑白金\" }, { \"name\": \"脑黑金\", \"index\": 2, \"value\": \"1.2768\", \"allValue\": \"1.2768\", \"date\": \"2016-03-21\", \"updateDate\": \"2016-04-07\", \"updateValue\": \"0.0093\", \"displayName\": \"脑黑金\" }] } ";
	    JSONObject parseObject = JSON.parseObject(str); // 将字符串转化为json对象
	    // 遍历json对象, 根据key获取值
		for (String key : parseObject.keySet()) {
			// 将值转为JSONArray数组
			JSONArray jsonArr = JSON.parseArray(parseObject.get(key).toString());
			for (int j = 0; j < jsonArr.size(); j++) {
				JSONObject object = jsonArr.getJSONObject(j); // 每一个小的JSON键值对是个json对象
				System.out.println(object.get("name"));
			}
		}
	}
}

class User {
	private String id;
	private String name;
	private int age;
	private Address address;

	public User() {
		super();
	}

	public User(String id, String name, int age, Address address) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.address = address;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}

class Address {
	private String province;
	private String city;
	private String street;
	private String post;

	public Address() {
		super();
	}

	public Address(String province, String city, String street, String post) {
		super();
		this.province = province;
		this.city = city;
		this.street = street;
		this.post = post;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}
}