package com.pandaria.learn.core;

import org.apache.commons.lang3.RandomUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * 生成建议的mysql端口号和密码
 */
public class GenerateMysqlPortAndPwd {

    // TODO: 怎么把这两个正则合并成一个啊!!
    private static final String wordNumberPattern = "^.*[a-zA-Z][0-9]+.*$";
    private static final String specialCharacter = "^.*[/^/$/.//,;/-:'!_@<=>~#%&/*/|/?/+/(/)/[/]/{/}]+.*$";

    public static void main(String[] args) {
        int mysqlPasswordLength = 15;

        // exclude these characters
        Set<Integer> excludeSet = new HashSet<>();
        // "
        excludeSet.add(34);
        // '
        excludeSet.add(39);
        // )
        excludeSet.add(41);
        // /
        excludeSet.add(47);
        // \
        excludeSet.add(92);
        // `
        excludeSet.add(96);

        Set<Integer> resultSet = new HashSet<>();
        StringBuilder sb;

        do {
            resultSet.clear();
            sb = new StringBuilder();
            while(resultSet.size() != mysqlPasswordLength) {
                resultSet.add(RandomUtils.nextInt(33, 126));
                resultSet.removeAll(excludeSet);
            }

            for (int item : resultSet) {
                sb.append((char)item);
            }
        } while (!(sb.toString().matches(wordNumberPattern) && sb.toString().matches(specialCharacter)));

        int randomPort = RandomUtils.nextInt(1024, 65535);
        System.out.println("Suggested mysql port is: " + randomPort);
        System.out.println("Please use below command to make sure the port is not been occupied: " +
                "(1) lsof -i:" + randomPort + " " +
                "(2) cat /etc/services | grep " + randomPort + " " +
                "(3) netstat -apn | grep " + randomPort);
        System.out.println("Suggested mysql password is: " + sb.toString());
    }
}