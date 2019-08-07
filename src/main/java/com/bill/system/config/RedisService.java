package com.bill.system.config;

import com.bill.system.utils.SerializeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    /**
     * 默认过期时长，单位：秒
     */
    public static final long DEFAULT_EXPIRE = 60 * 60 * 24;

    /**
     * 不设置过期时长
     */
    public static final long NOT_EXPIRE = -1;




    public boolean existsKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 重名名key，如果newKey已经存在，则newKey的原值被覆盖
     *
     * @param oldKey
     * @param newKey
     */
    public void renameKey(String oldKey, String newKey) {
        redisTemplate.rename(oldKey, newKey);
    }

    /**
     * newKey不存在时才重命名
     *
     * @param oldKey
     * @param newKey
     * @return 修改成功返回true
     */
    public boolean renameKeyNotExist(String oldKey, String newKey) {
        return redisTemplate.renameIfAbsent(oldKey, newKey);
    }

    /**
     * 删除key
     *
     * @param key
     */
    public void deleteKey(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 删除多个key
     *
     * @param keys
     */
    public void deleteKey(String... keys) {
        Set<String> kSet = Stream.of(keys).map(k -> k).collect(Collectors.toSet());
        redisTemplate.delete(kSet);
    }

    /**
     * 删除Key的集合
     *
     * @param keys
     */
    public void deleteKey(Collection<String> keys) {
        Set<String> kSet = keys.stream().map(k -> k).collect(Collectors.toSet());
        redisTemplate.delete(kSet);
    }

    /**
     * 设置key的生命周期
     *
     * @param key
     * @param time
     * @param timeUnit
     */
    public void expireKey(String key, long time, TimeUnit timeUnit) {
        redisTemplate.expire(key, time, timeUnit);
    }

    /**
     * 指定key在指定的日期过期
     *
     * @param key
     * @param date
     */
    public void expireKeyAt(String key, Date date) {
        redisTemplate.expireAt(key, date);
    }

    /**
     * 查询key的生命周期
     *
     * @param key
     * @param timeUnit
     * @return
     */
    public long getKeyExpire(String key, TimeUnit timeUnit) {
        return redisTemplate.getExpire(key, timeUnit);
    }

    /**
     * 将key设置为永久有效
     *
     * @param key
     */
    public void persistKey(String key) {
        redisTemplate.persist(key);
    }


    /**
     * get
     * @param key
     * @return
     */
    public Object get(String key) {

        Object value = redisTemplate.opsForValue().get(key);
        if(value!=null) {
            byte[] val=(byte[]) value;
            value= SerializeUtil.unserialize(val);
        }
        return value;
    }




    /**
     *
     * @param key
     * @param obj
     */
    public void set(String key, Object obj) {
        if (obj == null) {
            return;
        }
        redisTemplate.opsForValue().set(key, SerializeUtil.serialize(obj));
    }


    /**
     * getString
     * @param key
     * @return
     */
    public String getString(String key){
        Object value = redisTemplate.opsForValue().get( key);
        String valueRetun = "";

        if(value!=null) {
            byte[] val=(byte[]) value;
            valueRetun=SerializeUtil.unserialize(val).toString();
        }
        return valueRetun;
    }

    /**
     * getString
     * @param key
     * @return
     */
    public String getStringNoPrefix(String key){
        Object value = redisTemplate.opsForValue().get(key);
        String valueRetun = "";

        if(value!=null) {
            byte[] val=(byte[]) value;
            valueRetun=SerializeUtil.unserialize(val).toString();
        }
        return valueRetun;
    }

    /**
     * set key time
     * @param key 键
     * @param obj 值
     * @param timeout 超时时间
     * @param unit  超时时间单位 参照TimeUnit
     */
    public void setKeyByTime(String key, Object obj, Long timeout, TimeUnit unit) {
        if (obj == null) {
            return;
        }

        if (timeout != null) {
            redisTemplate.opsForValue().set( key, SerializeUtil.serialize(obj), timeout, unit);
        } else {
            redisTemplate.opsForValue().set(key, SerializeUtil.serialize(obj));
        }
    }
}
