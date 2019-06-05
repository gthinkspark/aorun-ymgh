package com.aorun.ymgh.controller;


import com.aorun.ymgh.util.jsonp.Jsonp_data;
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
    //1.联系我们接口
    @RequestMapping(value = "/contactOur", method = RequestMethod.GET)
    public Object contactOur(
            @RequestParam(name = "sid", required = true, defaultValue = "") String sid
    ) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("tel", "0937-3362339");
        resultMap.put("mobile", "15097235830");
        resultMap.put("fax", "0937-3362339");
        resultMap.put("qq", "1791327277");
        resultMap.put("address", "甘肃省玉门市新市区铁人路9号");
        return Jsonp_data.success(resultMap);
    }


}
