/**
 * Copyright 2014-2015 the original author or authors.
 */
package com.git.jdbc;

import java.util.Map;

/**
 * @author pansanday
 * @since 2016年1月14日
 */
public class BoneCpServiceImpl implements BoneCpService {

    private BoneCpDao bonecpDao;
    public void setBonecpDao(BoneCpDao bonecpDao) {
        this.bonecpDao = bonecpDao;
    }


    /**
     * 根据主键获取值
     * @param id
     */
    public Map<String, Object> fetchData(String id) {
        return bonecpDao.fetchData(id);
    }
    
}
