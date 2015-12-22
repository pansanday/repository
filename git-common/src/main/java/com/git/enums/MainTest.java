package com.git.enums;

public class MainTest {

    public static void main(String[] args) throws UnsupportedValueException {
        System.out.println(AcceptType.valueOf(1));
        System.out.println(AcceptType.nameOf("Makeup"));

        System.out.println(Role.roleAdmin);
        System.out.println(Role.valueOf("roleAdmin"));
        
        System.out.println(Role.getRoleByAlias("正值"));
        System.out.println(Role.getAliasByRole(Role.roleGroupSlave));
    }
}
