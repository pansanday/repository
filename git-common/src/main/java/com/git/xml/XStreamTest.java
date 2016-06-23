package com.git.xml;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * XStream的使用示例
 */
public class XStreamTest {

	/**
	 * Java对象转化为XML
	 * 
	 * @param person
	 * @return
	 */
	public static String javaObject2Xml(Person person) {
		XStream xs = new XStream(new DomDriver());
		// 给Person类定义别名
		xs.alias("person", person.getClass());
		return xs.toXML(person);
	}

	/**
	 * XML转换为Java对象
	 * 
	 * @param xml
	 * @return
	 */
	public static Object xml2JavaObject(String xml) {
		XStream xs = new XStream(new DomDriver());
		xs.alias("person", Person.class);
		Person person = (Person) xs.fromXML(xml);
		return person;
	}

	public static void main(String[] args) {
		// 构造Person对象
		Person p1 = new Person();
		p1.setName("张三");
		p1.setSex("男");
		p1.setAddress("新疆福海县");
		System.out.println(javaObject2Xml(p1));

		// 构造xml字符串
		String xml = "<person><name>爱丽丝</name><sex>女</sex><address>广州市</address></person>";
		Person p2 = (Person) xml2JavaObject(xml);
		System.out.println(p2.getName() + " " + p2.getSex() + " " + p2.getAddress());
	}

}

class Person {
	private String name;
	private String sex;
	private String address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}