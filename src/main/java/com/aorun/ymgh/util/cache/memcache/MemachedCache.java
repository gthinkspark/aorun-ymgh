package com.aorun.ymgh.util.cache.memcache;

import com.whalin.MemCached.MemCachedClient;

import java.util.*;

public class MemachedCache{
	
	public static  int DEFAULT_EXPIRATE_MINUTES = 30;

	public static void put(String key, Object value) {
		if(key==null || value==null){
            return ;
        }
        put(key, value,MemachedCache.DEFAULT_EXPIRATE_MINUTES);
	}

	

	public static void put(String key, Object value, int expiraMinutes) {
		if(key==null || value==null){
            return ;
        }
        MemCachedClient proxy = MemcachedUtil.getInstance();
        Calendar c=Calendar.getInstance();
        c.add(Calendar.MINUTE, expiraMinutes);
        proxy.set(key, value,c.getTime());
	}

	public static Object get(String key) {
		MemCachedClient proxy = MemcachedUtil.getInstance();
        Object o = proxy.get(key);
        return o;
	}
	
	
	public static void remove(String key) {
		MemCachedClient proxy = MemcachedUtil.getInstance();
        proxy.delete(key);
	}

	
	
	 /**
* 获取服务器上面所有的key
* @return
* @author liu787427876@126.com
     * @date 2013-12-4
*/
public static List<String> getAllKeys(MemCachedClient memCachedClient) {
	//logger.info("开始获取没有挂掉服务器中所有的key.......");
	List<String> list = new ArrayList<String>();
	Map<String, Map<String, String>> items = memCachedClient.statsItems();
	for (Iterator<String> itemIt = items.keySet().iterator(); itemIt.hasNext();) {
		String itemKey = itemIt.next();
		Map<String, String> maps = items.get(itemKey);
	    for (Iterator<String> mapsIt = maps.keySet().iterator(); mapsIt.hasNext();) {
	    	String mapsKey = mapsIt.next();
           String mapsValue = maps.get(mapsKey);
           if (mapsKey.endsWith("number")) {  //memcached key 类型  item_str:integer:number_str
           	String[] arr = mapsKey.split(":");
               int slabNumber = Integer.valueOf(arr[1].trim());
               int limit = Integer.valueOf(mapsValue.trim());
               Map<String, Map<String, String>> dumpMaps = memCachedClient.statsCacheDump(slabNumber, limit);
               for (Iterator<String> dumpIt = dumpMaps.keySet().iterator(); dumpIt.hasNext();) {
                   String dumpKey = dumpIt.next();
                   Map<String, String> allMap = dumpMaps.get(dumpKey);
                   for (Iterator<String> allIt = allMap.keySet().iterator(); allIt.hasNext();) {
                       String allKey = allIt.next();
                       list.add(allKey.trim());

                   }
               }
           }
		}
	}
	//logger.info("获取没有挂掉服务器中所有的key完成.......");
	return list;
}


	public static void main(String[] args) {
	
		MemachedCache.put("18321218140", "000000000000000000");
		
		System.out.println(MemachedCache.get("18321218140"));
//		MemCachedClient proxy = MemcachedUtil.getInstance();
//		List<String> list  = MemachedCache.getAllKeys(proxy);
	
//		System.out.println(list.size());
	
}

}
