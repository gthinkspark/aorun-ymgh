package com.aorun.ymgh.controller;

import com.aorun.ymgh.util.jsonp.Jsonp_data;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: MyBookController
 * @Description: TODO
 * @author: lg
 * @date: 2019/3/28 16:57
 */
@RequestMapping("/book")
@RestController
public class MyBookController {
    private final String BOOK_URL = "http://www.dzzgsw.com/";
    private final String LEARN_URL = "http://www.dzzgsw.com/activity3/index.html";

    //1.列表接口----分页查询
    @RequestMapping(value = "/bookDetail", method = RequestMethod.GET)
    public Object bookDetai(
            @RequestParam(name = "sid", required = true, defaultValue = "") String sid
    ) {
//        UserDto user = null;
//        WorkerMember workerMember = null;
//        if (!StringUtils.isEmpty(sid)) {
//            user = (UserDto) RedisCache.get(sid);
//            if (CheckObjectIsNull.isNull(user)) {
//                return Jsonp.noLoginError("请先登录或重新登录");
//            }
//            workerMember = RedisCache.getObj(UnionUtil.generateUnionSid(user), WorkerMember.class);
//            if (CheckObjectIsNull.isNull(workerMember)) {
//                return Jsonp.noAccreditError("用户未授权工会,请重新授权");
//            }
//        } else {
//            return Jsonp.noLoginError("用户SID不正确,请核对后重试");
//        }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("bookUrl", BOOK_URL);
        resultMap.put("learnUrl", LEARN_URL);
        return Jsonp_data.success(resultMap);
    }


}
