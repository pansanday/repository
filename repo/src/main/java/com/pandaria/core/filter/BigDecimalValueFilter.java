package com.pandaria.core.filter;

import com.alibaba.fastjson.serializer.ValueFilter;

import java.math.BigDecimal;

public class BigDecimalValueFilter implements ValueFilter {
    /**
     * @param object 对象
     * @param name   对象的字段的名称
     * @param value  对象的字段的值
     */
    public Object process(Object object, String name, Object value) {
        if (null != value && value instanceof BigDecimal) {
            return value.toString();
        }
        return value;
    }
}