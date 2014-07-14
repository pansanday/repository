package com.git.selenium;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

public class Register {

	private WebDriver driver;
	private String baseUrl;
	private Log logger = LogFactory.getLog(this.getClass());
	private WebElement element;

	public void openBrowser() throws Exception {
		File file = new File("D:\\Program Files\\Mozilla Firefox\\firefox.exe"); // 这里注意对\进行转义
		FirefoxBinary firefoxBin = new FirefoxBinary(file);
		driver = new FirefoxDriver(firefoxBin, null);
		driver.get(baseUrl);
	}

	public void sendMessage() {
		baseUrl = "http://nj.5i5j.com/newLogin/register";
		try {
			this.openBrowser();
			driver.findElement(By.id("tel")).clear();
			driver.findElement(By.id("tel")).sendKeys("15221405830");
			driver.findElement(By.id("msend")).click();
			// ///////////////////////////////////////////////////////
			// baseUrl = "http://nj.edai.com/user/register/";
			// driver.get(baseUrl);
			// driver.findElement(By.id("phone")).click();
			// driver.findElement(By.id("phone")).clear();
			// driver.findElement(By.id("phone")).sendKeys("15221405833");
			// Selenium selenium = new DefaultSelenium("localhost", 4444,
			// "*firefox", baseUrl);
			// selenium.focus("id=phone");
			// selenium.keyPressNative("09");
			// driver.findElement(By.id("password")).click();
			// driver.findElement(By.id("getAuthCode")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Register register = new Register();
		register.sendMessage();
	}

}
