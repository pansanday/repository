package com.git.testing;

/**
 * 简单的利率计算器
 * @author Pansanday
 *
 */
public class SimpleInterestCalculator implements InterestCalculator {

	private double rate;

	/**
	 * 设置固定利率
	 */
	public void setRate(double rate) {
		this.rate = rate;
	}

	public double calculate(double amount, double year) {
		if (amount < 0 || year < 0) {
			throw new IllegalArgumentException(
					"Amount or year must be positive");
		}
		return amount * year * rate;
	}

}
