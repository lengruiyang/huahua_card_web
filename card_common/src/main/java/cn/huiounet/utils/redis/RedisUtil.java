package cn.huiounet.utils.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

public class RedisUtil {

    /**
     *
     * @param key
     * @param value
     * @param isExpire 是否需要设置过期时间
     * @param time 时间（毫秒）
     * @return
     */
    public static String redisSetString(String key, String value,boolean isExpire,int time) {
        JedisShardInfo shardInfo = new JedisShardInfo("localhost");//这里是连接的本地地址和端口

        shardInfo.setPassword("lry123456");//这里是密码

        Jedis jedis = new Jedis(shardInfo);

        String set = jedis.set(key, value);

        if(isExpire){
            jedis.expire(key,time);
        }

        return set;
    }


    public static String redisGetString(String key) {
        JedisShardInfo shardInfo = new JedisShardInfo("localhost");//这里是连接的本地地址和端口

        shardInfo.setPassword("lry123456");//这里是密码

        Jedis jedis = new Jedis(shardInfo);

        String s = jedis.get(key);

        return s;
    }
}
