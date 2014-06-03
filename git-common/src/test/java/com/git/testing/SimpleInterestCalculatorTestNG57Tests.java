package com.git.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * TestNG强大功能之一是对数据驱动驱动测试的内建支持.TestNG清晰地将测试数据和测试逻辑分离,使你可以用不同的数据集多次进行一个测试方法.
 * 在TestNG 中,测试数据集由数据提供者提供,数据提供者是带有@DataProvider注解的方法
 * 
 * @author Pansanday
 * 
 */
public class SimpleInterestCalculatorTestNG57Tests {

	private InterestCalculator interestCalculator;

	@BeforeMethod
	public void init() {
		interestCalculator = new SimpleInterestCalculator();
		interestCalculator.setRate(0.05);
	}

	@DataProvider(name = "legal")
	public Object[][] createLegalInterestParameters() {
		return new Object[][] { new Object[] { 10000, 2, 1000.0 } };
	}

	@DataProvider(name = "illegal")
	public Object[][] createIllegalInterestParameters() {
		return new Object[][] { new Object[] { -10000, 2 },
				new Object[] { 10000, -2 }, new Object[] { -10000, -2 } };
	}

	/**
	 * calculate()方法将执行一次
	 * 
	 * @param amount
	 * @param year
	 * @param result
	 */
	@Test(dataProvider = "legal")
	public void calculate(double amount, double year, double result) {
		double interest = interestCalculator.calculate(amount, year);
		Assert.assertEquals(interest, result);
	}

	/**
	 * illegalCalculate()方法将执行三次,因为illegal数据提供者返回三个数据集
	 * 
	 * @param amount
	 * @param year
	 */
	@Test(dataProvider = "illegal", expectedExceptions = IllegalArgumentException.class)
	public void illegalCalculate(double amount, double year) {
		interestCalculator.calculate(amount, year);
	}

	// @Test
	// public void calculate(double amount, double year, double result) {
	// double interest = interestCalculator.calculate(10000, 2);
	// Assert.assertEquals(interest, 1000.0);
	// }

	// @Test(expectedExceptions = IllegalArgumentException.class)
	// public void illegalCalculate() {
	// interestCalculator.calculate(-10000, 2);
	// }

}
