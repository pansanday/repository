package com.git.testing;

public class DuplicateAccountException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7536574296983048758L;

	public DuplicateAccountException(){
		System.out.println("Duplicate Account");
	}
}
