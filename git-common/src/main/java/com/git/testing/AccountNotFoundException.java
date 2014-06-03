package com.git.testing;

public class AccountNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -976187519893330647L;

	public AccountNotFoundException(){
		System.out.println("Account Not Found");
	}
}
