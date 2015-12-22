package com.git.enums;

public class UnsupportedValueException extends Exception {

    /** serialVersionUID */
    private static final long serialVersionUID = 392913292412044140L;
    
    /**
     * Constructor 
     */
    public UnsupportedValueException(String string) {
        super(string);
        System.out.println("==>" + string);
    }
}
