package com.git.probability;

import java.util.Date;

public class AwardBatch {
	private long id;
	/**
	 * 奖品名称
	 */
	private String name;
	/**
	 * 奖品总量
	 */
	private int totalAmount;
	/**
	 * 奖品余量
	 */
	private int balance;
	/**
	 * 更新时间
	 */
	private Date updateTime;

	public AwardBatch() {
		super();
	}

	public AwardBatch(long id, String name, int totalAmount, int balance, Date updateTime) {
		this.id = id;
		this.name = name;
		this.totalAmount = totalAmount;
		this.balance = balance;
		this.updateTime = updateTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "中奖奖品信息 [id=" + id + ", name=" + name + ", totalAmount=" + totalAmount + ", balance=" + balance
				+ ", updateTime=" + updateTime.toLocaleString() + "]";
	}
}
