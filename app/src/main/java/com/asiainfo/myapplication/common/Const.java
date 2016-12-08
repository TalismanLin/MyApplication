/**
 * Package:com.jialin.common
 * Author:Zhu JL
 * Date:2016年5月15日
 */
package com.asiainfo.myapplication.common;

/**
 * @author Zhu JL
 *
 */
public class Const {

	public static final boolean DEBUG_MODE = false;

    public static String SP_FILE_NAME = "com.asiainfo.myapplication.sp_file";

	/* ------------ 通信用通用设定 ------------ */
    /** 通信结果代码 */
    public static final String HTTP_RESPONSE_CODE = "code";
    
    /** 通信结果内容 */
    public final static String HTTP_RESPONSE_CONTENT = "content";
    
    /** 通信结果代码:正常返回01 */
    public final static String HTTP_CODE_NORMAL_01 = "N01";
    
    /** 通信结果代码:正常返回02 */
    public final static String HTTP_CODE_NORMAL_02 = "N02";
    
    /** 通信结果代码:异常返回01 */
    public final static String HTTP_CODE_ERROR_01 = "E01";
    
    /** 通信结果代码:异常返回02 */
    public final static String HTTP_CODE_ERROR_02 = "E02";
    
    /** 通信结果代码:异常返回98(APP侧错误) */
    public final static String HTTP_CODE_PHONE_ERROR = "E98";

    /** 通信结果代码:异常返回99(Web侧错误) */
    public final static String HTTP_CODE_WEB_ERROR = "E99";
    /* ----------------- END ----------------- */
    
    /* ------------ 字符串通用设定 ------------ */
    /** 空字符 */
    public static final String STR_BLANK = "";
    
    /** 中横线 */
    public static final String STR_HYPHEN = "-";
    
    /** 半角空格*/
    public final static String STR_HALF_SPACE = " ";

    /** 半角逗号 */
    public final static String STR_HALF_COMMNA = ",";

    /** 换行符 */
    public final static String STR_LINE_BREAK = "\n";
    /* ----------------- END ----------------- */
    
    /* --------------- 终端代号 --------------- */
    public final static String DEVICE_TYPE_IOS = "0";
    
    public final static String DEVICE_TYPE_ANDROID = "1";
    /* ----------------- END ----------------- */

	/* ----------- Web通信用通用设定 ----------- */
    /** Web服务器地址 */
    public static final String WEB_SERVER_URL = "61.160.128.39:7006";
    
    /** WebContext名 */
    public static final String WEB_SERVER_CONTEXT = "/assemExtWeb";
    
    /** HTTP通信超时时间 */
    public static final int HTTP_TIMEOUT = 7000;

    /** 通信加密Key */
    public static final String MD5_LOGIN_KEY = "wooppdch2d";
    /* ----------------- END ----------------- */

}
