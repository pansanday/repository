package com.git.pinyin;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinyinUtil {

    /**
     * 将字符串中的中文转化为拼音,并包含声调(1-4声),其他字符不变
     * @param inputString
     * @return
     */
    public static String getPinYin(String inputString) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 小写
        // format.setToneType(HanyuPinyinToneType.WITH_TONE_MARK);//不知道这个是什么意思
        // 写完查api看看
        // format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);//不带拼音
        format.setToneType(HanyuPinyinToneType.WITH_TONE_NUMBER);// 不知道这个是什么意思
        // format.setVCharType(HanyuPinyinVCharType.WITH_V);//

        char[] input = inputString.trim().toCharArray();
        String output = "";

        try {
            for (int i = 0; i < input.length; i++) {
                if (java.lang.Character.toString(input[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(input[i], format);
                    output += temp[0];
                } else {
                    output += input[i];
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

    /**
     * 获取汉字串拼音首字母,英文字符不变
     * @param chineseString 汉字串
     * @return 汉语拼音首字母
     */
    public static String getFirstSpell(String chineseString) {
        StringBuffer pybf = new StringBuffer();
        char[] arr = chineseString.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V);

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 128) {
                try {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat);
                    if (temp != null) {
                        pybf.append(temp[0].charAt(0));
                    } else {
                        pybf.append(arr[i]);
                    }
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            }
        }
        return pybf.toString().replaceAll("\\W", "").trim();
    }

    /**
     * 获取汉字串全拼拼音,英文字符不变
     * @param chinese   汉字串
     * @return 汉语拼音
     */
    public static String getFullSpell(String chinese) {
        StringBuffer pybf = new StringBuffer();
        char[] arr = chinese.toCharArray();

        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        // defaultFormat.setToneType(HanyuPinyinToneType.WITH_TONE_MARK);//不知道这个是什么意思
        // 写完查api看看
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不带拼音
        // defaultFormat.setToneType(HanyuPinyinToneType.WITH_TONE_NUMBER);
        defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V);

        try {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] > 128) {
                    pybf.append(PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat)[0]);
                } else {
                    pybf.append(arr[i]);
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        return pybf.toString();
    }

    public static void main(String[] args) {
        System.out.println(PinyinUtil.getPinYin("司令"));
        System.out.println(PinyinUtil.getFullSpell("军长"));
        System.out.println(PinyinUtil.getFirstSpell("师长"));
    }
}
