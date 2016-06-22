package com.git.xml;

import java.util.List;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * Dom4j解析XML的示例
 */
public class Dom4jTest {

	public static void main(String[] args) throws Exception {
		// 构造XML字符串
		StringBuffer buffer = new StringBuffer();
		buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		buffer.append("<person>");
		buffer.append("<name>柳峰</name>");
		buffer.append("<sex>男</sex>");
		buffer.append("<address>广东省佛山市禅城区</address>");
		buffer.append("</person>");

		// 通过解析XML字符串创建Document对象
		Document document = DocumentHelper.parseText(buffer.toString());
		// 得到XML的根元素(本例中是person)
		Element root = document.getRootElement();
		// 得到根元素person的所有子节点
		List<Element> elementList = root.elements();
		// 遍历所有子节点
		for (Element e : elementList) {
			// 输出子节点名称和值
			System.out.println(e.getName() + " => " + e.getText());
		}
	}
}
