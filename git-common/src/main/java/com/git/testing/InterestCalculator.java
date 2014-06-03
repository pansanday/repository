package com.git.testing;

public interface InterestCalculator {

	/**
	 * 设置利率
	 * @param rate
	 */
	public void setRate(double rate);

	/**
	 * 计算利率
	 * @param amount
	 * @param year
	 * @return
	 */
	public double calculate(double amount, double year);
}
