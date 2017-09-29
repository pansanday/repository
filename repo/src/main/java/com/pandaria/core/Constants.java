package com.pandaria.core;

import java.util.HashMap;
import java.util.Map;

public interface Constants {

    /**
     * 异常信息统一头信息<br>
     * 非常遗憾的通知您,程序发生了异常
     */
    String Exception_Head = "Oops! Some errors occurs! Detail as follows: ";
    /**
     * 缓存键值
     */
    Map<Class<?>, String> cacheKeyMap = new HashMap<Class<?>, String>();
    /**
     * 客户端语言
     */
    String USERLANGUAGE = "userLanguage";
    /**
     * 客户端主题
     */
    String WEBTHEME = "webTheme";
    /**
     * 当前用户
     */
    String CURRENT_USER = "CURRENT_USER";
    /**
     * 在线用户数量
     */
    String ALLUSER_NUMBER = "ALLUSER_NUMBER";
    /**
     * 登录用户数量
     */
    String USER_NUMBER = "USER_NUMBER";
    /**
     * 上次请求地址
     */
    String PREREQUEST = "PREREQUEST";
    /**
     * 上次请求时间
     */
    String PREREQUEST_TIME = "PREREQUEST_TIME";
    /**
     * 非法请求次数
     */
    String MALICIOUS_REQUEST_TIMES = "MALICIOUS_REQUEST_TIMES";
    /**
     * 缓存命名空间
     */
    String CACHE_NAMESPACE = "pandaria:";
}
