package com.aorun.ymgh;

import com.aorun.ymgh.controller.login.UserDto;
import com.aorun.ymgh.model.WorkerMember;
import com.aorun.ymgh.util.CheckObjectIsNull;
import com.aorun.ymgh.util.biz.UnionUtil;
import com.aorun.ymgh.util.cache.redis.RedisCache;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 20160216 on 2018/2/8.
 */
public class SessionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        System.out.println("uri=" + request.getRequestURI());
        //登录不做拦截
        if (request.getRequestURI().indexOf("/worker/checkUserLogin") > -1) {
            return true;
        }
        else if (request.getRequestURI().indexOf("/contact/contactOur") > -1) {
            return true;
        }

        else if (request.getRequestURI().indexOf("/statistics/") > -1) {
            return true;
        }
        String sid = request.getParameter("sid");
        System.out.println("sid==" + sid);
        int status = 0;
        UserDto user = null;
        WorkerMember workerMember = null;
        if (!StringUtils.isEmpty(sid)) {
            user = (UserDto) RedisCache.get(sid);
            if (CheckObjectIsNull.isNull(user)) {
                //return Jsonp.noLoginError("请先登录或重新登录");
                status = 1;
            } else {
                workerMember = RedisCache.getObj(UnionUtil.generateUnionSid(user), WorkerMember.class);
                if (CheckObjectIsNull.isNull(workerMember)) {
                    //return Jsonp.noAccreditError("用户未授权工会,请重新授权");
                    status = 2;
                }
            }

        } else {
            //return Jsonp.noLoginError("用户SID不正确,请核对后重试");
            status = 3;
        }

        if (status != 0) {
            System.out.println("request.getContextPath()==" + request.getContextPath());
            response.sendRedirect("https://appymclient.91catv.com:8089/ymgh_service/worker/checkUserLogin?status=" + status);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}