package com.devilkxh.app.model.constant;

public class WechatConstant {

    public static final String APPID = "wxd21cf43aa834218f";

    public static final String CORPID = "";

    public static final String SECRET = "";

    public static final String CODE_TO_JSAPI_TICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";

    public static final String CODE_TO_SNS_OAUTH = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

    public static final String CODE_TO_SNS_USERINFO = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

    public static final String CODE_TO_ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=SECRET";

    public static final String CODE_TP_CORP_TOKEN = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=CORPID&corpsecret=SECRET";
}
