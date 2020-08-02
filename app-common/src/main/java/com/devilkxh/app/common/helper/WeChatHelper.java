package com.devilkxh.app.common.helper;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.devilkxh.app.model.constant.WechatConstant;
import com.devilkxh.app.model.enums.WeChat;
import com.devilkxh.app.model.wechat.AccessToken;
import com.devilkxh.app.model.wechat.JsApiTicket;
import com.devilkxh.app.model.wechat.UserInfo;
import com.devilkxh.app.model.wechat.WeixinUser;
import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.*;

import static com.devilkxh.app.common.helper.HttpHelper.httpRequest;

public class WeChatHelper {

    /**
     * 获取access_token
     *
     * @param appid
     *            凭证
     * @param appsecret
     *            密钥
     * @return
     */
    public static AccessToken getAccessToken(String appid, String appsecret, String agentid) {

        AccessToken accessToken = null;
        String access_token_url = "";
        String requestUrl = "";
        if (StringUtils.isEmpty(agentid)) {
            access_token_url = WechatConstant.CODE_TO_ACCESS_TOKEN;
            requestUrl = access_token_url.replace(WeChat.APPID.toString(), appid).replace(WeChat.SECRET.toString(), appsecret);
        } else {
            access_token_url = WechatConstant.CODE_TP_CORP_TOKEN;
            requestUrl = access_token_url.replace(WeChat.CORPID.toString(), appid).replace(WeChat.SECRET.toString(), appsecret);
        }

        JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
        // 如果请求成功
        if (null != jsonObject) {
            try {
                accessToken = new AccessToken();
                accessToken.setToken(jsonObject.getString("access_token"));
                accessToken.setExpiresIn(7200);
            } catch (JSONException e) {

                accessToken = null;
                // 获取token失败
//                log.error("获取token失败 errcode:{} errmsg:{}",
//                        jsonObject.getInteger("errcode"),
//                        jsonObject.getString("errmsg"));
                System.out.println("获取token失败 errcode:{} errmsg:{}"
                        + jsonObject.getInteger("errcode")
                        + jsonObject.getString("errmsg"));
            }
        }
        System.out.println("获取token成功:" + accessToken.getToken());
        return accessToken;
    }

    public static JsApiTicket getJsapiTicket(String appId, String appSecret, String agentid) {
        String access_token = TokenHelper.checkTokenGZH(appId, appSecret, agentid);
        String url = WechatConstant.CODE_TO_JSAPI_TICKET.replace("ACCESS_TOKEN", access_token);
        JSONObject object = httpRequest(url, HttpHelper.GET, null);
        return JSONObject.toJavaObject(object, JsApiTicket.class);
    }

    public static String getStringByUrl(String myUrl,String key) throws Exception{
        URL url = new URL(myUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        int code = conn.getResponseCode();
        if(code == 200){
            InputStream is = conn.getInputStream();
            int bufferSize = 1024;
            char[] buffer = new char[bufferSize];
            StringBuilder out = new StringBuilder();
            Reader in = new InputStreamReader(is, "UTF-8");
            for (; ; ) {
                int rsz = in.read(buffer, 0, buffer.length);
                if (rsz < 0)
                    break;
                out.append(buffer, 0, rsz);
            }
            String result = out.toString();
            return (String) JSONObject.parseObject(result).get(key);
        }
        return null;
    }

    /**
     * 随机字符串
     * @return
     */
    public static String CreateNoncestr() {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String res = "";
        for (int i = 0; i < 16; i++) {
            Random rd = new Random();
            res += chars.charAt(rd.nextInt(chars.length() - 1));
        }
        return res;
    }

    /**
     * 获取签名字符串 by wuhengbo@joeone.net 2016-01-27
     * @param bizObj
     * @return
     * @throws Exception
     */
    public static String GetJsAPISign(HashMap<String, String> bizObj) throws Exception {
        HashMap<String, String> bizParameters = new HashMap<String, String>();

        List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(bizObj.entrySet());

        Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return (o1.getKey()).toString().compareTo(o2.getKey());
            }
        });

        for (int i = 0; i < infoIds.size(); i++) {
            Map.Entry<String, String> item = infoIds.get(i);
            if (item.getKey() != "") {
//                bizParameters.put(item.getKey().toLowerCase(), item.getValue());
                bizParameters.put(item.getKey(), item.getValue());
            }
        }

        String bizString = FormatBizQueryParaMap(bizParameters, false);

        return Sha1(bizString);

    }

    public static String FormatBizQueryParaMap(HashMap<String, String> paraMap,
                                               boolean urlencode) throws Exception {

        String buff = "";
        try {
            List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(
                    paraMap.entrySet());

            Collections.sort(infoIds,
                    new Comparator<Map.Entry<String, String>>() {
                        public int compare(Map.Entry<String, String> o1,
                                           Map.Entry<String, String> o2) {
                            return (o1.getKey()).toString().compareTo(
                                    o2.getKey());
                        }
                    });

            for (int i = 0; i < infoIds.size(); i++) {
                Map.Entry<String, String> item = infoIds.get(i);
                //System.out.println(item.getKey());
                if (item.getKey() != "") {

                    String key = item.getKey();
                    String val = item.getValue();
                    if (urlencode) {
                        val = URLEncoder.encode(val, "utf-8");// utf-8

                    }
//					buff += key.toLowerCase() + "=" + val + "&";
                    buff += key + "=" + val + "&";

                }
            }

            if (!buff.equals("")){
                buff = buff.substring(0, buff.length() - 1);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return buff;
    }

    public final static String Sha1(String s) {
        System.out.println("加密前的签名："+s);

        char hexDigits[]={'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            byte[] btInput = s.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("sha-1");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            String rtn = new String(str);
            System.out.println("加密后的签名："+rtn);
            return rtn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static WeixinUser getOpenId(String code) {
        String url = WechatConstant.CODE_TO_SNS_OAUTH.replace(WeChat.APPID.toString(), WechatConstant.APPID).replace(WeChat.SECRET.toString(), WechatConstant.SECRET).replace(WeChat.CODE.toString(), code);
        JSONObject json = HttpHelper.httpRequest(url, HttpHelper.GET, null);
        WeixinUser user = JSONObject.toJavaObject(json, WeixinUser.class);
        return user;
    }

    public static UserInfo getUserInfo(String code) {
        WeixinUser user = getOpenId(code);
        String url = WechatConstant.CODE_TO_SNS_USERINFO.replace(WeChat.ACCESS_TOKEN.toString(), user.getAccess_token()).replace(WeChat.OPENID.toString(), user.getOpenid());
        JSONObject json = HttpHelper.httpRequest(url, HttpHelper.GET, null);
        return JSONObject.toJavaObject(json, UserInfo.class);
    }

}
