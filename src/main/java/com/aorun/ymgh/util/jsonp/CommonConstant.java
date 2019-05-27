package com.aorun.ymgh.util.jsonp;

public class CommonConstant {

    public static final String YES = "y";
    public static final String NO = "n";
    /**
     * 未登录状态
     */
    public static final int NOT_LOGIN = 0;
    /**
     * 已加入状态
     */
    public static final int HAS_ADD = 1;
    /**
     * 加入成功
     */
    public static final int SUCCESS_ADD = 2;

    /**
     * 未审核
     */
    public static final int CHECK_NO = 0;
    /**
     * 拒绝
     */
    public static final int CHECK_REFUL = 2;
    /**
     * 审核成功
     */
    public static final int CHECK_SUCCESS = 1;
    /**
     * 已确认
     */
    public static final int ISOK = 1;
    /**
     * 未确认
     */
    public static final int ISNO = 0;
    /**
     * 显示
     */
    public static final int SHOW_OK = 1;
    /**
     * 不显示
     */
    public static final int SHOW_NO = 0;

    /**
     * VOTE投票按天计算
     */
    public static final int VOTE_DAY = 1;
    /**
     * VOTE投票按人计算
     */
    public static final int VOTE_MEMBER = 2;

    /**
     * MESSAGE类型  站内信
     */
    public static final int MESSAGE_MAIL = 1;
    /**
     * MESSAGE类型  应急广播
     */
    public static final int MESSAGE_BROAD = 2;

    //CMS后台限制数
    public static final int CMS_LIMIT = 20;


    // 通用的消息
    public abstract class CommonMessage {
        public static final String LOGIN_MESSAGE = "请先登录或重新登录";// 请先登录或重新登录
        public static final String FAILED_MESSAGE = "获取数据失败!";// 获取数据失败
        public static final String SUCCESS_MESSAGE = "请求数据成功!";// 获取数据失败
        public static final String ERROR_MESSAGE = "请求数据出错!!";// 获取数据出错!
        public static final String MD5_ERROR_MESSAGE = "MD5校验失败"; // MD5校验失败
        public static final String PARAM_ERROR_MESSAGE = "请求参数传递错误!!";// 参数传递错误
        public static final String SHOPPING_CART_FULL_MESSAGE = "亲!,您的购物车满了先下单结算吧^_^!";// 购物车满了
        public static final String NO_LOGIN = "用户未登录";
        public static final String PAY_OFF_FAILED_MSG = "订单结算序列失效!";
        public static final String ACOUNT_FAILURE = "支付密码错误!";
        public static final String SEARCH_FAILURE = "查询信息失败!";
        public static final String SMS_LIMIT_MESSAGE = "10分钟内只能获取3条";
        public static final String CHECK_FAIL_MESSAGE = "校验失败";
    }

    // 通用的状态码
    public abstract class CommonCode {
        // 公共编码
        public static final String SUCCESS_CODE = "0";// 获取数据成功状态码
        public static final String SUCCESS_CODE_NEWS = "200";// 玉门新闻同步成功响应值
        public static final String FOREIGN_BIZ_API_SUCCESS_CODE = "200";// 对外业务API接口任务处理成功
        public static final String NO_LOGIN_CODE = "201";// 请先登录
        public static final String NO_ACCREDIT_CODE = "202";// 请先工会授权
        public static final String PARAM_ERROR_CODE = "300";// 参数传递错误状态码
        public static final String BUSINESS_TIPS_CODE = "400"; // 业务提示状态码
        public static final String ERROR_CODE = "500"; // 获取数据出错状态码


        public static final String SHOPPING_CART_FULL_CODE = "888";// 购物车满了
        public static final String PAY_OFF_FAILED = "202";// 202
        // 结算序列失效，请重新结算。跳到购物车结算页
        public static final String MD5_ERROR_CODE = "555"; // MD5校验失败状态码
        public static final String CURRENT_VERSION = "203";// 203表示系统最新的版本
        public static final String ACOUNT_FAILURE_CODE = "601"; // 账号支付密码验证失败码
        public static final String SEARCH_FAILURE_CODE = "602"; // 查询失败码
        public static final String SMS_LIMIT_CODE = "333";//短信发送量超出限制失败码
        public static final String CHECK_FAIL_CODE = "306";//校验失败
        public static final String UPDATE_CODE = "603"; //更新失败状态码
        public static final String ADD_CODE = "602"; //添加状态码
        public static final String REPETITTION_CODE = "604"; //重复状态吗
        public static final String TRYCATCH_CODE = "600"; //异常

    }

}
