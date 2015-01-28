/**
 * Copyright 2014-2015 the original author or authors.
 */
package com.git.testing;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author Pansanday
 * @since 2015年1月28日
 */
public class TestSuiteDemo extends TestCase {

    FileReader _input = null;
    
    /**
     * 构造函数
     */
    public TestSuiteDemo(String name) {
        super(name);
    }

    /**
     * setUp用来产生相关对象
     */
    protected void setUp() {
        try {
            _input = new FileReader("./src/main/resources/data.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException("unable to open test file");
        }
    }

    /**
     * tearDown负责删除它们
     */
    protected void tearDown() {
        try {
            _input.close();
        } catch (IOException e) {
            throw new RuntimeException("error on cloase test file");
        }
    }

    /**
     * 测试代码
     */
    public void testRead() throws IOException {
        char ch = '&';
        // _input.close();
        for (int i = 0; i < 4; i++) {
            ch = (char) _input.read();
            assert ('w' == ch);
            // assertEquals('w', ch);
        }
    }

    /**
     * Method used to: 将测试代码集中到测试套件中
     * @return
     */
    public static Test suite() {
        TestSuite suite = new TestSuite();
        // 将待测函数的名称以字符串形式传递给构造函数
        suite.addTest(new TestSuiteDemo("testRead"));
        return suite;
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

}
