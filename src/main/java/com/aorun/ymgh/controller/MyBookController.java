package com.aorun.ymgh.controller;

import com.aorun.ymgh.controller.login.UserDto;
import com.aorun.ymgh.model.Message;
import com.aorun.ymgh.util.CheckObjectIsNull;
import com.aorun.ymgh.util.PageConstant;
import com.aorun.ymgh.util.biz.UnionUtil;
import com.aorun.ymgh.util.cache.redis.RedisCache;
import com.aorun.ymgh.util.jsonp.Jsonp;
import com.aorun.ymgh.util.jsonp.Jsonp_data;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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
    private final String BOOK_URL="http://www.ucdrs.net/area/gslib";

    //1.列表接口----分页查询
    @RequestMapping(value = "/bookDetail", method = RequestMethod.GET)
    public Object bookDetail(
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
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("bookUrl",BOOK_URL);
        return Jsonp_data.success(resultMap);
    }


}
