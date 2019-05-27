package com.aorun.ymgh.util.cache.redis;

import com.aorun.ymgh.util.biz.PropertyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/***
 *
 * @author King 索亮
 *
 */
public class RedisConstant {
    /**
     * 读取配置文件 redis.properties
     */
    protected final static Logger log = LoggerFactory.getLogger(RedisConstant.class);

    //private static Properties props = null;
    public static String REDIS_SERVER;
    public static Integer REDIS_PORT;
    public static String REDIS_MASTER;
    public static String REDIS_DELKEY;
    public static Integer REDIS_MAXACTIVE;
    public static Integer REDIS_MAXIDLE;
    public static Long REDIS_MAXWAIT;
    public static Boolean REDIS_TESTONBORROW;

    static {
        //if(props ==null) {
        //InputStream is = PropertiesUitls.class.getClassLoader().getResourceAsStream("conf/redis.properties");
        //props = new Properties();
        PropertyUtil props = new PropertyUtil(System.getenv("aorun_env_config_path") + "/account/redis.properties");
        //try {
        //props.load(is);
        REDIS_SERVER = props.getCommonConf("redis.server");
        REDIS_PORT = Integer.valueOf(props.getCommonConf("redis.port"));
        REDIS_MASTER = props.getCommonConf("redis.master");
        REDIS_DELKEY = props.getCommonConf("redis.delKey");
        REDIS_MAXACTIVE = Integer.valueOf(props.getCommonConf("redis.MaxActive"));
        REDIS_MAXIDLE = Integer.valueOf(props.getCommonConf("redis.MaxIdle"));
        REDIS_MAXWAIT = Long.valueOf(props.getCommonConf("redis.MaxWait"));
        REDIS_TESTONBORROW = Boolean.valueOf(props.getCommonConf("redis.TestOnBorrow"));
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
        //}
    }

    public static void main(String[] args) {

        System.out.println(REDIS_SERVER);
        System.out.println(REDIS_PORT);
        System.out.println(REDIS_MASTER);
        System.out.println(REDIS_DELKEY);
        System.out.println(REDIS_MAXACTIVE);
        System.out.println(REDIS_MAXIDLE);
        System.out.println(REDIS_MAXWAIT);
        System.out.println(REDIS_TESTONBORROW);
    }

}
