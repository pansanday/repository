package com.git.algorithm;

public class Demo {

    /**
     * 计算一个数各位数之和(使用递归)
     * @param num
     * @return
     */
    public static int sum(int num) {
        if (num / 10 == 0) {
            return num;
        } else {
            return sum(num / 10) + num % 10;
        }
    }
    
    /**
     * 压缩叠数(aaabbbcccdedef,变为3a3b3cdedef)
     * @param str
     * @return
     */
    public static String zipString(String str) {
        int iMaxCount = 9;
        String result = "";
        str += " "; // 防止越界
        int counter = 1;
        char[] cs = str.toCharArray();
        for (int i = 0; i < cs.length - 1; i++) {
            if (cs[i + 1] == cs[i]) {
                counter++;
                if (counter > iMaxCount) {
                    result += (Integer.toString(iMaxCount) + cs[i]);
                    counter = 1;
                }
                continue;
            } else {
                if (counter == 1) {
                    result += cs[i];
                } else {
                    result += (Integer.toString(counter) + cs[i]);
                }
                counter = 1;
            }
        }
        return result;
    } 
    
    // TODO:根据销售情况获得相应的利益

    public static void main(String[] args) {
        String str = "aaabbbcccdedef";
        System.out.println(zipString(str));
        
        System.out.println(sum(23456));
    }
}
