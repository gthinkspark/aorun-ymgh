package com.aorun.ymgh.controller;


import com.aorun.ymgh.util.cache.redis.RedisCache;
import com.aorun.ymgh.util.jsonp.Jsonp_data;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: ContactController
 * @Description: TODO
 * @author: lg
 * @date: 2019/4/2 10:28
 */
@RequestMapping("/contact")
@RestController
public class ContactController {
    private final String YMGH_CONTACT_CACHE_TEL="YMGH_CONTACT_CACHE_TEL";
    private final String YMGH_CONTACT_CACHE_MOBILE="YMGH_CONTACT_CACHE_MOBILE";
    private final String YMGH_CONTACT_CACHE_FAX="YMGH_CONTACT_CACHE_FAX";
    private final String YMGH_CONTACT_CACHE_QQ="YMGH_CONTACT_CACHE_QQ";
    private final String YMGH_CONTACT_CACHE_ADDRESS="YMGH_CONTACT_CACHE_ADDRESS";

    private static String tel = "0937-3362339";
    private static String mobile = "15097235830";
    private static String fax = "0937-3362339";
    private static String qq = "1791327277";
    private static String address = "甘肃省玉门市新市区铁人路9号";
    private static String e_mail = "ymszgh@126.com";
    private static String customer = "QQ - 390666779";

    //1.联系我们接口
    @RequestMapping(value = "/contactOur", method = RequestMethod.GET)
    public Object contactOur(
            @RequestParam(name = "sid", required = true, defaultValue = "") String sid
    ) {
        if(!StringUtils.isEmpty(RedisCache.getString(YMGH_CONTACT_CACHE_TEL))){
               tel = RedisCache.getString(YMGH_CONTACT_CACHE_TEL);
        }
        if(!StringUtils.isEmpty(RedisCache.getString(YMGH_CONTACT_CACHE_MOBILE))){
            mobile = RedisCache.getString(YMGH_CONTACT_CACHE_MOBILE);
        }
        if(!StringUtils.isEmpty(RedisCache.getString(YMGH_CONTACT_CACHE_FAX))){
            fax = RedisCache.getString(YMGH_CONTACT_CACHE_FAX);
        }
        if(!StringUtils.isEmpty(RedisCache.getString(YMGH_CONTACT_CACHE_QQ))){
            qq = RedisCache.getString(YMGH_CONTACT_CACHE_QQ);
        }
        if(!StringUtils.isEmpty(RedisCache.getString(YMGH_CONTACT_CACHE_ADDRESS))){
            address = RedisCache.getString(YMGH_CONTACT_CACHE_ADDRESS);
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("tel", tel);
        resultMap.put("mobile", mobile);
        resultMap.put("fax", fax);
        resultMap.put("qq", qq);
        resultMap.put("address", address);
        resultMap.put("e_mail", e_mail);
        resultMap.put("customer", customer);
        return Jsonp_data.success(resultMap);
    }


}
