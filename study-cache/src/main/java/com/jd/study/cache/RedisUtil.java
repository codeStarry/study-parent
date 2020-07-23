package com.jd.study.cache;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

/**
 * 缓存工具类
 * @author lsy
 * @date 2020-7-16
 */
@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class RedisUtil {

    private final StringRedisTemplate template;

    private static StringRedisTemplate redisTemplate;

    private static Gson gson = new Gson();

    @PostConstruct
    private void init() {
        RedisUtil.redisTemplate = this.template;
    }

    /**
     * 缓存String类型数据
     *
     * @param key     键
     * @param value   值
     */
    public static void setValue(String key, String value) {
        RedisUtil.setValue(key, value, -1);
    }

    /**
     * 缓存String类型数据
     *
     * @param key       键
     * @param value     值
     * @param second    过期秒数
     */
    public static void setValue(String key, String value, long second) {
        Type type = new TypeToken<String>() {}.getType();
        RedisUtil.saveResult(key, value, type, second);
    }

    /**
     * 存放进位图
     * @param key       键
     * @param offset    偏移量
     * @param value     值  true or false
     */
    public static void setBit(String key, Long offset, Boolean value) {
        redisTemplate.opsForValue().setBit(key, offset, value);
    }

    /**
     * 根据key和偏移量获取bit位
     * @param key
     * @param offset
     * @return
     */
    public static Boolean getBit(String key, Long offset) {
        return redisTemplate.opsForValue().getBit(key, offset);
    }

    /**
     * 统计该位图值为 1 的数量
     * @param key
     * @return
     */
    public static Long bitCount(String key) {
        return redisTemplate.execute((RedisCallback<Long>) r -> r.bitCount(key.getBytes()));
    }

    /**
     * 统计指定范围内该位图值为 1 的数量
     * @param key
     * @param start
     * @param end
     * @return
     */
    public static Long bitCount(String key, Long start, Long end) {
        return redisTemplate.execute((RedisCallback<Long>) r -> r.bitCount(key.getBytes(), start, end));
    }

    /**
     * 获取String类型数据
     *
     * @param key   键
     * @return      String
     */
    public static String getValue(String key) {
        Type type = new TypeToken<String>() {}.getType();
        String result = RedisUtil.getValue(key, type);
        return result;
    }

    /**
     * 取值模板
     * @param key   键
     * @param type  类型，值数据类型
     * @param <T>
     * @return
     */
    public static <T> T getValue(String key, Type type) {
        T data = null;
        String result = redisTemplate.opsForValue().get(key);
        if (null != result) {
            data = gson.fromJson(result, type);
        }
        return data;
    }

    /**
     * 缓存模板
     * @param key      键
     * @param value    值，泛型
     * @param type     值，数据类型
     * @param second   过期秒数
     * @param <T>
     */
    public static <T> void saveResult(String key, T value, Type type, long second) {
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        String result = gson.toJson(value, type);
        if (second > 0) {
            opsForValue.set(key, result, second, TimeUnit.SECONDS);
        }else {
            opsForValue.set(key, result);
        }
    }
}
