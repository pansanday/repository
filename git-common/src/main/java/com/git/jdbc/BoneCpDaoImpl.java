package com.git.jdbc;

import java.util.Map;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * @author pansanday
 * @since 2016年1月14日
 */
public class BoneCpDaoImpl extends JdbcDaoSupport implements BoneCpDao {

    @Override
    public Map<String, Object> fetchData(String id) {
        String sql = "select * from testtable where id=" + id;
        return this.getJdbcTemplate().queryForMap(sql);
    }

}
