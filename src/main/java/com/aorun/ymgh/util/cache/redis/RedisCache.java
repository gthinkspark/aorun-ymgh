package com.aorun.ymgh.util.cache.redis;


import com.aorun.ymgh.controller.login.UserDto;
import com.aorun.ymgh.util.cache.memcache.MemachedCache;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Set;

public class RedisCache {


    public static void put(String key, String value) {
        MemachedCache.put(key, value, 30 * 24 * 60);//最长1个月的分钟数
        try {
            new RedisUtil().set(key, value, 30 * 24 * 60 * 60);//最长1个月的秒数
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void put(String key, String value, int minute) {
        MemachedCache.put(key, value, minute);
        try {
            new RedisUtil().set(key, value, minute * 60);//秒数
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //用户版本-获取用户信息
    public static Object get(String key) {
        if (CheckIsEmpty.isEmpty(key)) {
            return null;
        }
        UserDto user = null;
        String string = null;
        try {
            string = new RedisUtil().get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (CheckIsEmpty.isEmpty(string)) {//从redis中获取获取不到数据，再从memcached中获取。
            string = (String) MemachedCache.get(key);
            if (CheckIsEmpty.isEmpty(string)) {
                return null;
            }
            Gson gson = new Gson();
            user = gson.fromJson(string, new TypeToken<UserDto>() {
            }.getType());

            MemachedCache.put(key, string, 30 * 24 * 60);////最长1个月的分钟数
        } else {
            // 解析data
            Gson gson = new Gson();
            user = gson.fromJson(string, new TypeToken<UserDto>() {
            }.getType());
        }

        return user;
    }


    public static <T> T getObj(String key, Class<T> clacc) {
        if (CheckIsEmpty.isEmpty(key)) {
            return null;
        }
        T rt = null;
        String string = null;
        try {
            string = new RedisUtil().get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (CheckIsEmpty.isEmpty(string)) {//从redis中获取获取不到数据，再从memcached中获取。
            string = (String) MemachedCache.get(key);
            if (CheckIsEmpty.isEmpty(string)) {
                return null;
            }
            Gson gson = new Gson();
            rt = gson.fromJson(string, clacc);
//	        rt = gson.fromJson(string, new TypeToken<T>(){}.getType());

            MemachedCache.put(key, string, 30 * 24 * 60);//1个月
        } else {
            // 解析data
            Gson gson = new Gson();
            rt = gson.fromJson(string, clacc);
//			 rt = gson.fromJson(string, new TypeToken<T>(){}.getType());
        }
        return rt;
    }

    public static String getString(String key) {
        if (CheckIsEmpty.isEmpty(key)) {
            return null;
        }
        String string = null;
        try {
            string = new RedisUtil().get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (CheckIsEmpty.isEmpty(string)) {//从redis中获取获取不到数据，再从memcached中获取。
            string = (String) MemachedCache.get(key);
            if (CheckIsEmpty.isEmpty(string)) {
                return null;
            }
            MemachedCache.put(key, string, 30 * 24 * 60);//1个月
        }
        return string;
    }


    public static void remove(String key) {
        new RedisUtil().del(key);
        MemachedCache.remove(key);
    }

    /******* redis hash ********/

    public static void hset(String key, String field, String value) {
        try {
            new RedisUtil().hset(key, field, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String hget(String key, String field) {
        return new RedisUtil().hget(key, field);
    }

    public static Long hdel(String key, String field) {
        return new RedisUtil().hdel(key, field);
    }

    public static boolean hexists(String key, String field) {
        return new RedisUtil().hexists(key, field);
    }

    public static Long hincrby(String key, String field) {
        return new RedisUtil().hincrby(key, field, 1l);
    }

    public static Long hlen(String key) {
        return new RedisUtil().hlen(key);
    }

    public static Set<String> hkeys(String key) {
        return new RedisUtil().hkeys(key);
    }

//	public static Set<String> keys(String key){
//		return new JedisUtil().keys(key);
//	}

    public static void main(String[] args) {

//		System.out.println(new RedisUtil().hdel(RedisCachConstant.NEWS_HASH_CACHE+"2616", RedisCachConstant.NEWS_HASH_KEY_APPHINTS));
        RedisCache.put("18321218140test", "000000000000000000");
        System.out.println(RedisCache.get("18321218140test"));
//		long del = new  RedisUtil().del("18321218140test");
//		System.out.println(del);

    }


}
