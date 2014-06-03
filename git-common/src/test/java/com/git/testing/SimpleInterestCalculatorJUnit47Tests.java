package com.git.testing;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * 使用JUnit 4.7进行测试
 * @author Pansanday
 *
 */
public class SimpleInterestCalculatorJUnit47Tests {

	private InterestCalculator interestCalculator;

	
	/**
	 * 在Junit 4中使用@Before注解进行初始化
	 */
	@Before
	public void init() {
		interestCalculator = new SimpleInterestCalculator();
		interestCalculator.setRate(0.05);
	}

	@Test
	public void calculate() {
		double interest = interestCalculator.calculate(10000, 2);
		assertEquals(interest, 1000.0, 0);
	}

	/**
	 * 使用expect可以预期在测试案例中抛出一个异常
	 */
	@Test(expected = IllegalArgumentException.class)
	public void illegalCalculate() {
		interestCalculator.calculate(-10000, 2);
	}
}
