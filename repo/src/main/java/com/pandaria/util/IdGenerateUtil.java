package com.pandaria.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @author pansanday
 * @Date 2017/12/26 14:45
 * 生成id主键工具类
 */
public class IdGenerateUtil {

    public static void main(String[] args) {
        System.out.println(generateSixDigitsId("abcd9z"));
    }

    /**
     * 生成6位数主键,主键由数字0-9和小写字母a-z组成;9+1=a,a+1=b,z+1=0进一
     *
     * @param preValue 之前的字母
     * @return 加一后的字母
     */
    public static String generateSixDigitsId(String preValue) {
        boolean breakFlag = false;
        StringBuilder sBuilder = new StringBuilder();

        char[] cArr = preValue.toCharArray();
        for (int i = 5; i < cArr.length; i--) {
            if (i < 0) {
                break;
            }

            if (!breakFlag) {
                char tail = preValue.charAt(i);
                if (tail != 'z') {
                    // 尾数不为z,直接加一
                    char c = add((int) tail);
                    sBuilder.append(c);
                    breakFlag = true;
                    continue;
                } else {
                    // 尾数为z,进一
                    char c = add((int) tail);
                    sBuilder.append(c);
                    continue;
                }
            }

            sBuilder.append(cArr[i]);
        }

        return StringUtils.reverse(sBuilder.toString());
    }

    private static char add(int i) {
        if (i == 122) {
            return '0';
        }
        if (i == 57) {
            return 'a';
        }

        if (i > 9) {
            return (char) (i + 1);
        } else {
            return Character.forDigit(i + 1, 10);
        }
    }

}
