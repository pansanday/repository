package com.git.enums;

public enum AcceptType {

    /**
     * GPS正常受理
     */
    Normal(1),

    /**
     * GPS异常受理
     */
    Abnormal(2),

    /**
     * 后台补录
     */
    Makeup(3);

    private int value;

    private AcceptType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static AcceptType valueOf(int value) throws UnsupportedValueException {
        for (AcceptType item : AcceptType.values()) {
            if (item.value == value) {
                return item;
            }
        }
        throw new UnsupportedValueException("枚举类型AcceptType不支持整型值 " + value);
    }

    public static AcceptType nameOf(String name) throws UnsupportedValueException {
        for (AcceptType item : AcceptType.values()) {
            if (item.toString().equals(name)) {
                return item;
            }
        }
        throw new UnsupportedValueException("枚举类型AcceptType不支持字面值 " + name);
    }
}
