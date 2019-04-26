package com.aorun.ymgh.controller.global;


import com.aorun.ymgh.util.jsonp.Jsonp;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by bysocket on 07/02/2017.
 */
@RequestMapping("/worker")
@RestController
public class CheckLoginUserController {

    @RequestMapping("/checkUserLogin")
    public Object checkUserLogin(@RequestParam(name = "status", required = true, defaultValue = "") String status) {

        if (status.equals("1")) {
            return Jsonp.noLoginError("请先登录或重新登录");
        }
        if (status.equals("2")) {
            return Jsonp.noAccreditError("用户未授权工会,请重新授权");
        }
        if (status.equals("3")) {
            return Jsonp.noLoginError("用户SID不正确,请核对后重试");
        }
        return Jsonp.noLoginError("请先登录或重新登录");
    }


}
