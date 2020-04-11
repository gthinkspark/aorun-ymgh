package com.aorun.ymgh.util;

/**
 * @author 作者 LG
 * @version
 * @data 创建时间：2019年3月15日 下午7:55:25 类说明
 */
public class UnionConstant {
	public static final String IS_DEL_N = "n";
	public static final String IS_DEL_Y = "y";
	
	public static final int SEX_MAN = 1;
	public static final int SEX_WOMAN = 2;
	
	public static final int CHECK_NEGATIVE = -1;// 默认常量
	public static final int CHECK_DEF = 0;
	public static final int CHECK_SUC = 1;
	public static final int CHECK_FAIL = 2;
	
	//禁用
	public static final int QUESTION_STATUS_DISABLE = 0;
	//启用
	public static final int QUESTION_STATUS_USE = 1;
	
    //每周一答
    public final static Integer QUESTION_BANKTYPE_WEEK=1;
    //专题考试
    public final static Integer QUESTION_BANKTYPE_EXAM=2;
	
    public final static Integer QUESTION_LIMITNUMBER_WEEK=5;
    public final static Integer QUESTION_LIMITNUMBER_EXAM=10;
    
    public final static Integer QUESTION_LIMITEPOINT_WEEK=5;
    public final static Integer QUESTION_LIMITEPOINT_EXAM=10;

    public final static Integer QUESTION_TOTALTIME_EXAM=3600;
	// 短信验证码30分钟内有效
	public static final int SMS_CSCH_MINUTE = 30;
	
	public static final String CMS_UNION_CODE = "20000";
	
	public static final int RESOURCE_RESCODE_LEN=8;
	public static final int RESOURCE_UPLOAD=1;
	public static final int RESOURCE_DOWLOAD=2;
	//资源-新闻
	public static final String RESOURCE_ARTICLE_CODE_NEWS="article_news";
	//资源-生活救助
	public static final String RESOURCE_ARTICLE_CODE_LIVE_CLAIM="article_live_claim";
	//资源-医疗救助
	public static final String RESOURCE_ARTICLE_CODE_MEDICAL_CLAIM="article_medical_claim";
	//资源-临时救助
	public static final String RESOURCE_ARTICLE_CODE_TEMP_CLAIM="article_temp_claim";
	//资源-助学救助
	public static final String RESOURCE_ARTICLE_CODE_SCHOOL_CLAIM="article_school_claim";
	
	//注册表
	public static final String RESOURCE_TAG_REG="reg_tab";
	//身份证
	public static final String RESOURCE_TAG_IDCARD="id_card_img";
	//户口本
	public static final String RESOURCE_TAG_ACCOUNT="account_img";
	//劳动合同
	public static final String RESOURCE_TAG_CONTRACT="contract_img";
	//工资证明
	public static final String RESOURCE_TAG_SALARY="salary_img";
	//医院诊断证明
	public static final String RESOURCE_TAG_DIAGNOSIS="diagnosis_img";
	//医疗发票
	public static final String RESOURCE_TAG_INVOICE="invoice_img";
	//大学录取通知书
	public static final String RESOURCE_TAG_UNIVERSITY_OFFER="university_offer_img";
}
