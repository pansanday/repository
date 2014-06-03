package com.git.testing;

import junit.framework.TestCase;

/**
 * JUnit 3.8.1版本测试结果,需要扩展TestCase类
 * @author Pansanday
 *
 */
public class SimpleInterestCalculatorJUnit381Tests extends TestCase {

	private InterestCalculator interestCalculator;

	/**
	 * 覆盖TestCase中定义的setUp()方法以初始化测试夹具
	 */
	@Override
	protected void setUp() throws Exception {
		interestCalculator = new SimpleInterestCalculator();
		interestCalculator.setRate(0.05);
	}

	/**
	 * junit 3中测试类必须以test开头
	 */
	public void testCalculate() {
		double interest = interestCalculator.calculate(10000, 2);
		assertEquals(interest, 1000.0);
	}

	public void testIllegalCalculate() {
		try {
			interestCalculator.calculate(-10000, 2);
			fail("No exception on illegal argument");
		} catch (IllegalArgumentException e) {
		}
	}

}
