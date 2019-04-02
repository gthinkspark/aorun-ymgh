package com.aorun.ymgh.controller;

import com.aorun.ymgh.util.PageConstant;
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
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("tel","0937-3365111");
        resultMap.put("mobile","16633441344");
        resultMap.put("fax","0937-3365222");
        resultMap.put("qq","XXXXXXXXX");
        resultMap.put("address","甘肃省玉门市XXXXXXXXXXXXXX");
        return Jsonp_data.success(resultMap);
    }


}
