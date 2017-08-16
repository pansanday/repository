package com.git.util;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * 使用Apache commons工具类生成随机字符串
 */
public class RandomStringUtilsDemo {

    public static void main(String[] args) {

        // 使用指定的字符生成10位长度的随机字符串
        String randomChar = RandomStringUtils.random(10, 'a', 'b', 'c', 'd', 'e', 'f');
        System.out.println("randomChar is: " + randomChar);

        // 生成随机字符串, 字符将从一组字母字符中选择
        String randomAlphabetic = RandomStringUtils.randomAlphabetic(10);
        System.out.println("randomAlphabetic is: " + randomAlphabetic);

        // 生成指定长度的字母和数字的随机组合字符串
        String randomAlphanumeric = RandomStringUtils.randomAlphanumeric(10);
        System.out.println("randomAlphanumeric is: " + randomAlphanumeric);

        // 生成随机Ascii码字符串
        String randomAscii = RandomStringUtils.randomAscii(10);
        System.out.println("randomAscii is: " + randomAscii);

        // 生成随机数字字符串
        String randomNumeric = RandomStringUtils.randomNumeric(10);
        System.out.println("randomNumeric is: " + randomNumeric);

        /**
         * 结果类似如下:
         * randomChar is: adebacddff
         * randomAlphabetic is: uZrdybukKm
         * randomAlphanumeric is: QE9yUNvN4t
         * randomAscii is: 9!Y7H6M.zg
         * randomNumeric is: 0309640897
         */
    }

}
