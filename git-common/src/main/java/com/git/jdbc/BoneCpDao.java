package com.git.jdbc;

import java.util.Map;

/**
 * @author pansanday
 * @since 2016年1月14日
 */
public interface BoneCpDao {

    /**
     * 根据主键获取该条记录
     * @param id
     */
    public Map<String, Object> fetchData(String id);
}
