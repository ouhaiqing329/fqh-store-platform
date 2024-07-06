//package com.fqh.utils;
//
//import com.fqh.utils.handle.ServiceException;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.util.StringUtils;
//
//import java.util.Objects;
//import java.util.concurrent.TimeUnit;
//
//public class RedisUtil {
//
//    private RedisUtil() {
//    }
//
//    private static RedisTemplate<String, Object> redisTemplate;
//
//    //将Spring容器中的值复制到工具类中的redistemplate静态成员对象
//    public static void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
//        RedisUtil.redisTemplate = redisTemplate;
//    }
//
//    /**
//     * 设置缓存
//     *
//     * @param key   键
//     * @param value 值
//     */
//    public static void set(String key, String value) {
//        if (!StringUtils.hasText(key) || !StringUtils.hasText(value)) {
//            throw new ServiceException("Redis error,key or value is null!");
//        }
//        redisTemplate.opsForValue().set(key, value);
//    }
//
//    /**
//     * 设置过期缓存
//     *
//     * @param key     键
//     * @param value   值
//     * @param timeout 超时时间（redis默认单位：s）
//     */
//    public static void set(String key, String value, Long timeout) {
//        if (!StringUtils.hasText(key) || !StringUtils.hasText(value)) {
//            throw new ServiceException("Redis error,key or value is null!");
//        }
//        redisTemplate.opsForValue().set(key, value, timeout);
//    }
//
//
//    /**
//     * 分布式锁
//     *
//     * @param key      键
//     * @param value    值
//     * @param timeout  超时
//     * @param timeUnit 时间单位
//     */
//    public static boolean lock(String key, String value, Long timeout, TimeUnit timeUnit) {
//        if (!StringUtils.hasText(key) || !StringUtils.hasText(value)) {
//            throw new ServiceException("Redis error,key or value is null!");
//        }
//        //setnx 原子操作，成功返回1，失败返回0
//        Boolean absent = redisTemplate.opsForValue().setIfAbsent(key, value, timeout, timeUnit);
//        if (Objects.isNull(absent)) {
//            throw new ServiceException("get lock error,unknown server!");
//        }
//        return Boolean.TRUE.equals(absent);
//    }
//
//    /**
//     * 分布式锁，不设置默认过期时间，使用redis内存淘汰机制来释放锁（不建议使用）
//     *
//     * @param key   键值
//     * @param value 价值
//     * @return boolean
//     */
//    public static boolean lock(String key, String value) {
//        if (!StringUtils.hasText(key) || !StringUtils.hasText(value)) {
//            throw new ServiceException("Redis error,key or value is null!");
//        }
//        //setnx 原子操作，成功返回1，失败返回0
//        Boolean absent = redisTemplate.opsForValue().setIfAbsent(key, value);
//        if (Objects.isNull(absent)) {
//            throw new ServiceException("get lock error,unknown server!");
//        }
//        if (Boolean.TRUE.equals(absent)) {
//            return true;
//        }
//        return false;
//    }
//
//    /**
//     * 解锁
//     *
//     * @param key 关键
//     */
//    public static void unlock(String key) {
//        if (!StringUtils.hasText(key)){
//            throw new ServiceException("Redis error,key is null!");
//        }
//        String value = (String) redisTemplate.opsForValue().get(key);
//        if (!StringUtils.hasText(value)){
//            throw new ServiceException("Redis error,key is not exist");
//        }
//        Boolean delete = redisTemplate.delete(value);
//        if (Boolean.FALSE.equals(delete)){
//            throw new ServiceException("Redis error,key delete failure");
//        }
//    }
//
//    /**
//     * 获取对象
//     *
//     * @param key 关键
//     * @return {@link Object}
//     */
//    public static Object get(String key){
//        return redisTemplate.opsForValue().get(key);
//    }
//
//    /**
//     * 获取字符串
//     *
//     * @param key 关键
//     * @return {@link String}
//     */
//    public static String getStr(String key){
//        Object value = redisTemplate.opsForValue().get(key);
//        if (Objects.isNull(value)){
//            return "";
//        }
//        if (value instanceof String){
//            return String.valueOf(value);
//        }
//        return value.toString();
//    }
//}
