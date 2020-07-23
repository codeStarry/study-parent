package com.jd.study.common.utils;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Map工具类
 * @author lsy
 */
public class MapUtils {

    /**
     * 将map转为实体类
     * @param map
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T convert(Map<String, String[]> map, Class<T> clazz) {
        if (null == map || map.isEmpty()) {
            return null;
        }
        Map<String, String> toMap = new HashMap<>(map.size());
        map.forEach((key, value) -> {
            toMap.put(key, value[0]);
        });
        String json = JSONObject.toJSONString(toMap);
        T entity = JSONObject.parseObject(json, clazz);

        return entity;
    }
}
