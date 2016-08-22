package com.newversion.util;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.newversion.bean.SNSUserInfo;
import com.newversion.bean.WeixinOauth2Token;
import com.newversion.message.resp.Article;
import com.newversion.message.resp.Music;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class AdvancedUtil {
	private static Logger log = LoggerFactory.getLogger(AdvancedUtil.class);

	/**
	 * 组装文本客服消息
	 * 
	 * @param openId
	 * @param content
	 * @return
	 */
	public static String makeTextCustomMessage(String openId, String content) {
		content = content.replace("\"", "\\\"");
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"text\",\"text\":{\"content\":\"%s\"}}";
		return String.format(jsonMsg, openId, content);
	}

	/**
	 * 组装图片客服消息
	 * 
	 * @param openId
	 * @param mediaId
	 * @return
	 */
	public static String makeImageCustomMessage(String openId, String mediaId) {
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"image\",\"image\":{\"media_id\":\"%s\"}}";
		return String.format(jsonMsg, openId, mediaId);
	}

	/**
	 * 组装语音客服消息
	 * 
	 * @param openId
	 * @param mediaId
	 * @return
	 */
	public static String makeVoiceCustomMessage(String openId, String mediaId) {
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"voice\",\"voice\":{\"media_id\":\"%s\"}}";
		return String.format(jsonMsg, openId, mediaId);
	}

	/**
	 * 组装视频客服消息
	 * @param openId
	 * @param mediaId
	 * @param title
	 * @param description
	 * @return
	 */
	public static String makeVideoCustomMessage(String openId, String mediaId, String title, String description) {
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"video\",\"video\":{\"media_id\":\"%s\",\"thumb_media_id\":\"%s\",\"title\":\"%s\",\"description\":\"%s\"}}";
		return String.format(jsonMsg, openId, mediaId, mediaId, title, description);
	}
	
	/**
	 * 组装音乐客服消息
	 * @param openId
	 * @param music
	 * @return
	 */
	public static String makeMusicCustomMessage(String openId, Music music) {
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"music\",\"music\":%s}";
		jsonMsg = String.format(jsonMsg, openId, JSONObject.fromObject(music).toString());
		// title,description,musicurl这些顺序没有关系吗?
		// 大小写没有关系吗?
		jsonMsg = jsonMsg.replace("HQMusicUrl", "hqmusicurl");
		jsonMsg = jsonMsg.replace("musicUrl", "musicurl");
		jsonMsg = jsonMsg.replace("thumbMediaId", "thumb_media_id"); // 大小写没关系吗?
		
		
		return jsonMsg;
	}
	
	/**
	 * 组装图文客服消息(点击跳转到外链),图文消息条数限制在8条以内
	 * 注意:如果图文数超过8，则将会无响应
	 * @param openId
	 * @param articleList
	 * @return
	 */
	public static String makeNewsCustomMessage(String openId, List<Article> articleList) {
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"news\",\"news\":{\"articles\":%s}}";
		jsonMsg = String.format(jsonMsg, openId, JSONArray.fromObject(articleList).toString().replaceAll("\"", "\\\""));
//		jsonMsg = jsonMsg.replace("picUrl", "picurl");
		return jsonMsg;
	}
	
	/**
	 * 组装图文客服消息(点击跳转到图文消息页面)
	 * 注意:如果图文数超过8，则将会无响应
	 * @param openId
	 * @param mediaId
	 * @return
	 */
	public static String makeMpnewsCustomMessage(String openId, String mediaId) {
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"mpnews\",\"mpnews\":{\"media_id\":\"%s\"}}";
		return String.format(jsonMsg, openId, mediaId);
	}
	
	/**
	 * 发送客服消息
	 * 
	 * @param accessToken 接口访问凭证
	 * @param jsonMsg json格式的客服消息（包括touser、msgtype和消息内容）
	 * @return true | false
	 */
	public static boolean sendCustomMessage(String accessToken, String jsonMsg) {
		log.info("消息内容：{}", jsonMsg);
		boolean result = false;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		// 发送客服消息
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST", jsonMsg);

		if (null != jsonObject) {
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (0 == errorCode) {
				result = true;
				log.info("客服消息发送成功 errcode:{} errmsg:{}", errorCode, errorMsg);
			} else {
				log.error("客服消息发送失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}

		return result;
	}
	
	/**
	 * 获取网页授权凭证(第二步：通过code换取网页授权access_token)
	 * @param appId	公众账号的唯一标识
	 * @param appSecret	公众账号的密钥
	 * @param code
	 * @return
	 */
	public static WeixinOauth2Token getOauth2AccessToken(String appId, String appSecret, String code) {
		WeixinOauth2Token wat = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		requestUrl = requestUrl.replace("APPID", appId);
		requestUrl = requestUrl.replace("SECRET", appSecret);
		requestUrl = requestUrl.replace("CODE", code);
		System.out.println("requestUrl为: " + requestUrl);
		
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
		
		if (null != jsonObject) {
			try {
				wat = new WeixinOauth2Token();
				wat.setAccessToken(jsonObject.getString("access_token"));
				wat.setExpiresIn(jsonObject.getInt("expires_in"));
				wat.setRefreshToken(jsonObject.getString("refresh_token"));
				wat.setOpenId(jsonObject.getString("openid"));
				wat.setScope(jsonObject.getString("scope"));
			} catch (Exception e) {
				wat = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error("获取网页授权凭证失败 errcode:{} errmsg:{}", errorCode,
						errorMsg);
			}
		}
		
		return wat;
	}
	
	/**
	 * 刷新网页授权凭证
	 * @param appId 公众账号的唯一标识
	 * @param refreshToken
	 * @return WeixinAouth2Token
	 */
	public static WeixinOauth2Token refreshOauth2AccessToken(String appId, String refreshToken) {
		WeixinOauth2Token wat = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
		requestUrl = requestUrl.replace("APPID", appId);
		requestUrl = requestUrl.replace("REFRESH_TOKEN", refreshToken);
		// 刷新网页授权凭证
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
		if (null != jsonObject) {
			System.out.println("jsonObject为:" + jsonObject);
			try {
				wat = new WeixinOauth2Token();
				wat.setAccessToken(jsonObject.getString("access_token"));
				wat.setExpiresIn(jsonObject.getInt("expires_in"));
				wat.setRefreshToken(jsonObject.getString("refresh_token"));
				wat.setOpenId(jsonObject.getString("openid"));
				wat.setScope(jsonObject.getString("scope"));
			} catch (Exception e) {
				wat = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error("刷新网页授权凭证失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return wat;
	}
	
	/**
	 * 通过网页授权获取用户信息
	 * @param accessToken
	 * @param openId
	 * @return
	 */
	public static SNSUserInfo getSNSUserInfo(String accessToken, String openId) {
		SNSUserInfo snsUserInfo = null;
		String requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		requestUrl = requestUrl.replace("OPENID", openId);
		
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
		if (null != jsonObject) {
			try {
				snsUserInfo = new SNSUserInfo();
				snsUserInfo.setOpenId(jsonObject.getString("openid"));
				snsUserInfo.setNickname(jsonObject.getString("nickname"));
				snsUserInfo.setSex(jsonObject.getInt("sex"));
				snsUserInfo.setProvince(jsonObject.getString("province"));
				snsUserInfo.setCity(jsonObject.getString("city"));
				snsUserInfo.setCountry(jsonObject.getString("country"));
				snsUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
//				JSONArray.toCollection(jsonObject.getJSONArray("privilege")).toArray();
				snsUserInfo.setPrivilegeList(JSONArray.toList(jsonObject.getJSONArray("privilege"),List.class));
			} catch (Exception e) {
				snsUserInfo = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error("获取用户信息失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		
		return snsUserInfo;
	}
	
	

	public static void main(String[] args) {
		// 第三方用户唯一凭证
		String appId = "";
		// 第三方用户唯一凭证密钥
		String appSecret = "";

		String accessToken = CommonUtil.getToken(appId, appSecret).getAccessToken();
		
		// 测试发文本消息
//		String jsonMsg = makeTextCustomMessage("oOcdJv3NPEr6EHD05-SgdI_GZhNc", "测试发送客服消息!");
//		sendCustomMessage(accessToken, jsonMsg);
		
		// 测试发音乐消息
		/*Music music = new Music();
		music.setTitle("我是title");
		music.setDescription("我是descriptions");
		music.setHQMusicUrl("我是hqmusicurl");
		music.setMusicUrl("http://music.163.com");
		music.setThumbMediaId("我是thumbmediaId"); // TODO,需要一个真正的media_id
//		System.out.println(makeMusicCustomMessage("哈哈", music));
		String jsonMsg = makeMusicCustomMessage("oOcdJv3NPEr6EHD05-SgdI_GZhNc", music);
		sendCustomMessage(accessToken, jsonMsg);*/
		
		List<Article> articleList = new ArrayList<Article>();
		Article article = new Article();
		article.setDescription("我是描述");
		article.setPicUrl("");
		article.setTitle("我是title");
		article.setUrl("http://www.haosou.com");
		articleList.add(article);
//		System.out.println(makeNewsCustomMessage("我是openId",articleList));
		String jsonMsg = makeNewsCustomMessage("oOcdJv3NPEr6EHD05-SgdI_GZhNc", articleList);
		sendCustomMessage(accessToken, jsonMsg);
	}
}
