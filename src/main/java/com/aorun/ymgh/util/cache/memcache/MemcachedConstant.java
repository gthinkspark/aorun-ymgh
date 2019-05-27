package com.aorun.ymgh.util.cache.memcache;

import com.aorun.ymgh.util.biz.PropertyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/***
 *
 * @author King 索亮
 *
 */
public class MemcachedConstant {

    protected static Logger log = LoggerFactory.getLogger(MemcachedConstant.class);
    //private static Properties props = null;
    //private static PropertyUtil props = null;

    public static Integer[] MEMCACHEDS_WEIGHTS;
    // 设置初始连接数、最小和最大连接数以及最大处理时间
    public static String MEMCACHEDS_SERVERURL;
    public static Integer MEMCACHEDS_INITCONN_NUMBER;
    public static Integer MEMCACHEDS_MINCONN_NUMBER;
    public static Integer MEMCACHEDS_MAXCONN_NUMBER;
    public static Integer MEMCACHEDS_MAXIDLE;
    // 设置主线程的睡眠时间
    public static Integer MEMCACHEDS_MAINTSLEEP_NUMBER;
    // 设置TCP的参数，连接超时等
    public static Integer MEMCACHEDS_SOCKETTO_PORT;
    public static boolean MEMCACHEDS_NAGLE;
    public static String MEMCACHEDS_DATA_POOL;
    public static String MEMCACHEDS_SESSION_POOL;
    public static Integer MEMCACHEDS_SCONNECTTO_NUMBER;

    static {
        //if (props == null) {
//			InputStream is = PropertiesUitls.class.getClassLoader().getResourceAsStream(
//					"conf/memcached.properties");

        PropertyUtil props = new PropertyUtil(System.getenv("aorun_env_config_path") + "/account/memcached.properties");

//			props = new Properties();
//			try {
        //props.load(is);
        MEMCACHEDS_SERVERURL = props.getCommonConf("memcacheds.server");
        MEMCACHEDS_INITCONN_NUMBER = Integer.valueOf(props
                .getCommonConf("memcacheds.initConn"));
        MEMCACHEDS_MINCONN_NUMBER = Integer
                .valueOf(props.getCommonConf("memcacheds.minConn"));
        MEMCACHEDS_MAXCONN_NUMBER = Integer
                .valueOf(props.getCommonConf("memcacheds.maxConn"));
        MEMCACHEDS_MAXIDLE = Integer.valueOf(props.getCommonConf("memcacheds.maxidle"));

        MEMCACHEDS_MAINTSLEEP_NUMBER = Integer.valueOf(props
                .getCommonConf("memcacheds.maintsleep"));
        MEMCACHEDS_SOCKETTO_PORT = Integer
                .valueOf(props.getCommonConf("memcacheds.socketto"));
        MEMCACHEDS_NAGLE = Boolean.valueOf(props.getCommonConf("memcacheds.nagle"));
        MEMCACHEDS_DATA_POOL = String.valueOf(props.getCommonConf("memcacheds.data"));
        MEMCACHEDS_SESSION_POOL = String.valueOf(props.getCommonConf("memcacheds.session"));

        MEMCACHEDS_SCONNECTTO_NUMBER = Integer.valueOf(props
                .getCommonConf("memcacheds.sconnectto"));
        String temp[] = props.getCommonConf("memcacheds.weights").split(",");
        MEMCACHEDS_WEIGHTS = new Integer[temp.length];
        int index = 0;
        for (String str : temp) {
            MEMCACHEDS_WEIGHTS[index++] = Integer.valueOf(str);
        }
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
    }
    //}

    public static void main(String[] args) {
        System.out.println(MEMCACHEDS_SERVERURL);
        System.out.println(MEMCACHEDS_SERVERURL);
        System.out.println(MEMCACHEDS_SERVERURL);
        System.out.println(MEMCACHEDS_INITCONN_NUMBER);
        System.out.println(MEMCACHEDS_MINCONN_NUMBER);
        System.out.println(MEMCACHEDS_MAXCONN_NUMBER);
        System.out.println(MEMCACHEDS_MAXIDLE);
        System.out.println(MEMCACHEDS_MAINTSLEEP_NUMBER);
        System.out.println(MEMCACHEDS_SOCKETTO_PORT);
        System.out.println(MEMCACHEDS_NAGLE);
        System.out.println(MEMCACHEDS_DATA_POOL);
        System.out.println(MEMCACHEDS_SESSION_POOL);
        System.out.println(MEMCACHEDS_SCONNECTTO_NUMBER);
        for (int i = 0; i < MEMCACHEDS_WEIGHTS.length; i++) {
            System.out.print(MEMCACHEDS_WEIGHTS[i] + ",");
        }

    }

}
