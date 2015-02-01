/**
 * Copyright 2014-2015 the original author or authors.
 */
package com.git.testing;

import java.io.File;
import java.io.FileOutputStream;
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
    FileReader _empty = null;
    
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
            _empty = newEmptyFile();
        } catch (IOException e) {
            throw new RuntimeException(e.toString());
        }
    }
    
    private FileReader newEmptyFile() throws IOException {
        File emptyFile = new File("empty.txt");
        FileOutputStream out = new FileOutputStream(emptyFile);
        out.close();
        return new FileReader(emptyFile);
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
     * 测试读到文件末尾时的返回值
     */
    public void testReadAtEnd() throws IOException {
        int ch = -1234;
        for (int i = 0; i < 5; i++) {
            ch = _input.read();
        }
        // 当输入流到达文件尾端,read()应该返回-1
        assertEquals(-1, _input.read());
    }
    
    /**
     * 测试边界条件
     */
    public void testReadBoundaries() throws IOException {
        assertEquals("read first char", '1', _input.read());
        int ch;
        for (int i = 1; i < 4; i++) {
            ch = _input.read();
        }
        // 测试读取最后一个字符
        assertEquals("read last char", '5', _input.read());
        assertEquals("read at end", -1, _input.read());
        // 测试读取文件末尾之后的位置,同样也应该返回-1
        assertEquals("readpast end", -1, _input.read());
    }
    
    /**
     * 测试读取空文件
     */
    public void testEmptyRead() throws IOException {
        assertEquals(-1, _empty.read());
    }
    
    /**
     * 检查预期的错误是否如期出现
     * 如果尝试在关闭流后再读取它,应该会得到一个IOException异常
     */
    public void testReadAfterClose() throws IOException {
        _input.close();
        try {
            _input.read();
            fail("no exception for read past end");
        } catch (IOException io) {}
    }

    /**
     * Run As -> JUnit Test
     * Method used to: 将测试代码集中到测试套件中
     * @return
     */
    public static Test suite() {
        TestSuite suite = new TestSuite();
        // 方法一: 将待测函数的名称以字符串形式传递给构造函数
        // suite.addTest(new TestSuiteDemo("testRead"));
        // suite.addTest(new TestSuiteDemo("testReadAtEnd"));

        // 方法二: 可以向下面这样,在测试套件中包含其他测试套件
        suite.addTest(new TestSuite(TestSuiteDemo.class));
        return suite;
    }

    /**
     * Run As -> Java Application
     * 使用main方法,以字符的方式运行测试用例
     */
    public static void main(String[] args) {
        // 运行测试套件
        // junit.textui.TestRunner.run(suite());
        
        // 传入一个类,这样测试框架会自动寻找那些以"test"开头的测试用例,并将其添加到TestSuite中
        junit.textui.TestRunner.run(new TestSuite(TestSuiteDemo.class));
    }

}
