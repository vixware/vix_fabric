package com.vix.nvix.wx.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Date;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.utils.JSonUtils;
import com.vix.nvix.wx.entity.WxpWeixin;

public class WxUtil {
	public static String api_weixin_server = "api.weixin.qq.com";
	private static boolean debug = true;

	/**
	 * 获取发送消息使用的access_token
	 * 
	 * @return
	 */
	public static String getAccessToken(WxpWeixin weixin, IBaseHibernateService loadService) {
		if (weixin.needReloadAccessToken()) {
			WxUtil.reloadAccessToken(weixin, loadService);
		}
		return weixin.getAccessToken();
	}

	/**
	 * 从微信服务器获取新access_token数据
	 */
	public static void reloadAccessToken(BaseEntity baseEntity, IBaseHibernateService loadService) {
		if (baseEntity != null) {
			// https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
			String appId = null;
			String secret = null;
			if (baseEntity instanceof WxpWeixin) {
				WxpWeixin weixin = (WxpWeixin) baseEntity;
				appId = weixin.getAppId();
				secret = weixin.getSecret();
			}
			/*
			 * else if(baseEntity instanceof WxpWeixinAccount) {
			 * WxpWeixinAccount weixin = (WxpWeixinAccount)baseEntity; appId =
			 * weixin.getAppid(); secret = weixin.getSecret(); }
			 */

			if (StrUtils.isEmpty(appId) || StrUtils.isEmpty(secret))
				return;

			String accessTokenRequestStr = WxUtil.loadAccessToken(appId, secret);

			resetAccessToken(accessTokenRequestStr, baseEntity, loadService);

		}
	}

	public static String loadAccessToken(String appId, String secret) {

		try {
			URI uri = new URIBuilder().setScheme("https").setHost(api_weixin_server).setPath("/cgi-bin/token").setParameter("grant_type", "client_credential").setParameter("appid", appId).setParameter("secret", secret).build();

			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet httpget = null;
			CloseableHttpResponse response = null;
			try {
				httpget = new HttpGet(uri);
				response = httpclient.execute(httpget);

				HttpEntity entity = response.getEntity();
				if (entity != null) {
					try {
						String accessTokenRequestStr = EntityUtils.toString(entity);

						return accessTokenRequestStr;
						// Map dataMap =
						// JSonUtils.readValue(accessTokenRequestStr,
						// Map.class);
						// return (String)dataMap.get("access_token");

					} catch (Exception rwe) {
						rwe.printStackTrace();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (response != null) {
					try {
						response.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 更新系统全局access_token数据
	 * 
	 * @param atJsonStr
	 *            微信服务器返回的access_token json
	 */
	public static void resetAccessToken(String atJsonStr, BaseEntity baseEntity, IBaseHibernateService service) {
		try {
			// {"access_token":"ACCESS_TOKEN","expires_in":7200}

			Map dataMap = JSonUtils.readValue(atJsonStr, Map.class);

			String newAccessToken = (String) dataMap.get("access_token");
			Integer expireSec = (Integer) dataMap.get("expires_in");

			long expireTime = System.currentTimeMillis() + 1000L * expireSec;

			if (baseEntity instanceof WxpWeixin) {
				WxpWeixin weixin = (WxpWeixin) baseEntity;
				weixin.setAccessToken(newAccessToken);
				weixin.setAccessTokenExpireTime(new Date(expireTime));
			}
			/*
			 * else if(baseEntity instanceof WxpWeixinAccount) {
			 * WxpWeixinAccount weixin = (WxpWeixinAccount)baseEntity;
			 * weixin.setAccessToken(newAccessToken);
			 * weixin.setTokenExpireTime(new Date(expireTime)); }
			 */

			service.merge(baseEntity);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("  --- 获取access_token失败" + e.toString());
			System.out.println("  --- 获取返回信息：" + atJsonStr);
		}
	}

	/**
	 * 模拟form表单的形式 ，上传文件 以输出流的形式把文件写入到url中，然后用输入流来获取url的响应
	 * 
	 * @param url
	 *            请求地址 form表单url地址
	 * @param filePath
	 *            文件在服务器保存路径
	 * @return String url的响应信息返回值
	 * @throws IOException
	 */
	public static String uploadMedia(String url, String filePath, String type) throws IOException {
		// http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=
		// https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN
		String result = null;
		String mediaId = null;
		File file = new File(filePath);
		if (!file.exists() || !file.isFile()) {
			throw new IOException("文件不存在");
		}

		/**
		 * 第一部分
		 */
		URL urlObj = new URL(url);
		// 连接
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

		/**
		 * 设置关键值
		 */
		con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setUseCaches(false); // post方式不能使用缓存

		// 设置请求头信息
		con.setRequestProperty("Connection", "Keep-Alive");
		con.setRequestProperty("Charset", "UTF-8");

		// 设置边界
		String BOUNDARY = "----------" + System.currentTimeMillis();
		con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

		// 请求正文信息

		// 第一部分：
		StringBuilder sb = new StringBuilder();
		sb.append("--"); // 必须多两道线
		sb.append(BOUNDARY);
		sb.append("\r\n");
		// file
		sb.append("Content-Disposition: form-data;name=\"" + type + "\";filename=\"" + file.getName() + "\"\r\n");
		sb.append("Content-Type:application/octet-stream\r\n\r\n");

		byte[] head = sb.toString().getBytes("utf-8");

		// 获得输出流
		OutputStream out = new DataOutputStream(con.getOutputStream());
		// 输出表头
		out.write(head);

		// 文件正文部分
		// 把文件已流文件的方式 推入到url中
		DataInputStream in = new DataInputStream(new FileInputStream(file));
		int bytes = 0;
		byte[] bufferOut = new byte[1024];
		while ((bytes = in.read(bufferOut)) != -1) {
			out.write(bufferOut, 0, bytes);
		}
		in.close();

		// 结尾部分
		byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线

		out.write(foot);

		out.flush();
		out.close();

		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = null;
		try {
			// 定义BufferedReader输入流来读取URL的响应
			reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				// System.out.println(line);
				buffer.append(line);
			}
			if (result == null) {
				result = buffer.toString();
			}
		} catch (IOException e) {
			System.out.println("发送POST请求出现异常！" + e);
			e.printStackTrace();
			throw new IOException("数据读取异常");
		} finally {
			if (reader != null) {
				reader.close();
			}

		}
		System.out.println("result:" + result);
		return result;
	}

	/**
	 * 下载媒体文件
	 *
	 * @param accessToken
	 *            接口访问凭证
	 * @param mediaId
	 *            媒体文件标识
	 * @param savePath
	 *            文件在服务器上的存储路径
	 * @param saveName
	 *            存储的文件名,不包含后缀
	 * @return 返回保存路径、文件名
	 */
	public static String[] downloadMedia(String requestUrl, String savePath, String saveName) {
		String filePath = null;
		String[] filePathAndName = null;
		// "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";

		try {
			URL url = new URL(requestUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setRequestMethod("GET");

			if (!savePath.endsWith("/")) {
				savePath += "/";
			}

			File folder = new File(savePath);
			if (!folder.exists())
				folder.mkdirs();

			// 根据内容类型获取扩展名
			String fileExt = getFileExtByHeader(conn);
			// 将mediaId作为文件名
			String fileName = saveName + fileExt;
			filePath = savePath + fileName;

			filePathAndName = new String[2];
			filePathAndName[0] = savePath;
			filePathAndName[1] = fileName;

			BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
			FileOutputStream fos = new FileOutputStream(new File(filePath));
			byte[] buf = new byte[8096];
			int size = 0;
			while ((size = bis.read(buf)) != -1)
				fos.write(buf, 0, size);
			fos.close();
			bis.close();

			conn.disconnect();
			// log.info("下载媒体文件成功，filePath="+ filePath);
		} catch (Exception e) {
			filePath = null;
			// log.error("下载媒体文件失败：{}", e);
		}
		return filePathAndName;
	}

	private static String getFileExtByHeader(HttpURLConnection conn) {
		/*
		 * HTTP/1.1 200 OK Connection: close Content-Type: image/jpeg
		 * Content-disposition: attachment; filename="MEDIA_ID.jpg" Date: Sun,
		 * 06 Jan 2013 10:20:18 GMT Cache-Control: no-cache, must-revalidate
		 * Content-Length: 339721
		 */
		String field = conn.getHeaderField("Content-disposition");
		System.out.println("header field : " + field);
		try {
			int idx = field.indexOf(".");
			if (idx != -1) {
				return field.substring(idx, idx + 4);
			} else {
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 生成微信临时素材上传链接
	 * 
	 * @param accessToken
	 * @param type
	 *            分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
	 * @return
	 */
	public static StringBuilder genWxMediaUploadUrl(String accessToken, String type) {
		// api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE
		StringBuilder url = new StringBuilder("http://api.weixin.qq.com/cgi-bin/media/upload");
		url.append("?access_token=").append(accessToken);
		url.append("&type=").append(type);

		return url;
	}

	/**
	 * 生成微信临时素材下载链接
	 * 
	 * @param accessToken
	 * @param mediaId
	 * @return
	 */
	public static StringBuilder genWxMediaDownloadUrl(String accessToken, String mediaId) {
		// http://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID;
		StringBuilder url = new StringBuilder("http://api.weixin.qq.com/cgi-bin/media/get");
		url.append("?access_token=").append(accessToken);
		url.append("&media_id=").append(mediaId);

		return url;
	}

	public static String sendMessage(String openId, String text, String accessToken) {

		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"touser\":\"").append(openId).append("\"");
		sb.append(",\"msgtype\":\"text\"");
		sb.append(",\"text\":{");
		sb.append("\"content\":\"").append(text).append("\"");
		sb.append("}");
		sb.append("}");

		String sendJson = sb.toString();
		try {
			URI uri = new URIBuilder().setScheme("https").setHost(api_weixin_server).setPath("/cgi-bin/message/custom/send").setParameter("access_token", accessToken).build();
			System.out.println(sendJson);
			String response = httpPostRequest(uri, sendJson);

			return response;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String loadQRCodeTicketLimit(String accessToken, String sceneStr) {
		// https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = null;
		CloseableHttpResponse response = null;
		try {
			URI uri = new URIBuilder().setScheme("https").setHost(api_weixin_server).setPath("/cgi-bin/qrcode/create").setParameter("access_token", accessToken).build();
			httppost = new HttpPost(uri);
			String postJson = "{\"action_name\":\"QR_LIMIT_STR_SCENE\",\"action_info\":{\"scene\":{\"scene_str\":\"" + sceneStr + "\"}}}";
			StringEntity entity = new StringEntity(postJson, ContentType.create("plain/text", Consts.UTF_8));
			entity.setChunked(true);
			httppost.setEntity(entity);
			response = httpclient.execute(httppost);

			HttpEntity responseEntity = response.getEntity();
			if (responseEntity != null) {
				// {"ticket":"xxxx","expire_seconds":60,"url":"http:\/\/weixin.qq.com\/q\/kZgfwMTm72WWPkovabbI"}
				try {
					String reponseStr = EntityUtils.toString(responseEntity);

					return reponseStr;

				} catch (Exception rwe) {
					rwe.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public static void main(String[] args) throws IOException {

		// String filePath = "D:/test/lufei.jpg";
		// String sendUrl =
		// "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=R3TtRwYW0wtsTNxFJod7C1eItwJcNPeEd8vmZAUw8QIsguwQYJZz0tzhAA9xPZ8qG8OT0FsqhrhR7sRV-IpDKmvHSH3g4kSOKIfwQjk4cZQ&type=image";
		// String result = null;
		// result = WxUtil.uploadMedia(sendUrl, filePath);
		// System.out.println(result);

		// WxUtil.loadMsgStatistic(WxUtil.stats_news_read_day_uri
		// ,"xI3X01-xPZ4MxtIisWFazGhSXieRb8EC_a_OM6dLifm_QrmzNy_AGFj3ruKAHMipFb0A6dGqn_JP2R78joHAR3TNgGd84hj-p3hSWXu7xSU"
		// ,"{\"begin_date\": \"2015-02-01\", \"end_date\": \"2015-02-03\"}")

		// testMemberApi();

		// System.out.println(WxUtil.loadAccessToken("wxd993f229a56aec55",
		// "342534337c640805c381422df21c132f"));
		String accessToken = "aW3cwMAIOqE_66DbDTfkLKnY2O78czYrlpbpmkGRURmwPpjIQfZXBo4X1hwwnWw6RVh5mlW-HjxxWNaC2K6r4XMd9orZYof4LZue32rm3HA";
		System.out.println(WxUtil.loadQRCodeTicketLimit(accessToken, "smk0002"));
	}

	public static void testMemberApi() {
		System.out.println("begin");
		try {
			// /http://dev.sapchinese.com:8000/test/fmcall/ZF_CRM_VIP_BIND_WEIXIN?
			// sap-client=300&format=json&I_VIPID=0000000001&I_TELEPHONE=13888888888&I_WEIXIN=test001

			// http://dev.sapchinese.com:8000/test/fmcall/ZF_CRM_VIP_GET?
			// sap-client=300&format=json&I_VIPID=0000000001

			// http://dev.sapchinese.com:8000/test/fmcall/ZF_CRM_VIPCARD_DEPOSIT?
			// sap-client=300&format=json&I_VIPCARDID=0000000001&I_AMOUNT=1.00&I_REMARK=备注说明

			// http://dev.sapchinese.com:8000/test/fmcall/ZF_CRM_VIPCARD_UNDEPOSIT?
			// sap-client=300&format=json&I_VIPCARDID=0000000001&I_TRANID=0000000001&I_REMARK=备注说明

			// http://dev.sapchinese.com:8000/test/fmcall/ZF_CRM_INTEGRAL_CONSUME?
			// sap-client=300&format=json&I_VIPID=0000000001&I_AMOUNT=1.00&I_REMARK=备注说明

			// http://dev.sapchinese.com:8000/test/fmcall/ZF_CRM_INTEGRAL_UNCONSUME?
			// sap-client=300&format=json&I_VIPID=0000000001&I_TRANID=0000000001&I_REMARK=备注说明

			// 以上已测试

			URI uri = new URIBuilder().setScheme("http").setHost("192.168.2.103").setPort(8000).setPath("/test/fmcall/ZF_CRM_VIP_BIND_WEIXIN").setParameter("sap-client", "300").setParameter("format", "json").setParameter("I_VIPID", "0000000001").setParameter("I_TELEPHONE", "13888888888").setParameter("I_WEIXIN", "wx00001").build();

			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet httpget = null;
			CloseableHttpResponse response = null;
			try {
				httpget = new HttpGet(uri);

				String username = "RFCUSER";
				String password = "12345678";
				String basic_auth = new String(Base64.encodeBase64((username + ":" + password).getBytes()));
				httpget.addHeader("Authorization", "Basic " + basic_auth);

				response = httpclient.execute(httpget);

				HttpEntity entity = response.getEntity();
				if (entity != null) {
					try {
						String accessTokenRequestStr = EntityUtils.toString(entity);

						System.out.println(accessTokenRequestStr);

					} catch (Exception rwe) {
						rwe.printStackTrace();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (response != null) {
					try {
						response.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("end");
	}

	static void sczgExportFileCode() {
		// 央视选手报名导出文档乱码处理
		try {
			File srcDir = new File("C:/Documents and Settings/ibm/桌面/央视选手报名");
			String tarDir = "C:/Documents and Settings/ibm/桌面/选手报名_20150313/";

			File[] srcFiles = srcDir.listFiles();
			for (File srcFile : srcFiles) {
				System.out.println("copy " + srcFile.getName());
				File tarFile = new File(tarDir + srcFile.getName());

				BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(srcFile), "GB2312"));
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tarFile), "UTF-8"));

				String temp = null;
				while ((temp = reader.readLine()) != null) {
					writer.write(temp);
					writer.flush();
				}

				writer.close();
				reader.close();

				// BufferedInputStream bufIn = new BufferedInputStream(new
				// FileInputStream(srcFile));
				// BufferedOutputStream bufOut = new BufferedOutputStream(new
				// FileOutputStream(tarFile));
				// byte[] buf = new byte[1024 * 100];
				// int len = -1;
				// while ((len = bufIn.read(buf)) != -1) {
				// bufOut.write(buf, 0, len);
				// }
				//
				// try{
				// bufIn.close();
				// bufOut.close();
				// }catch(Exception e){
				// e.printStackTrace();
				// }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String httpPostRequest(URI uri, String postStr) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = null;
		CloseableHttpResponse response = null;
		try {
			httppost = new HttpPost(uri);
			if (StrUtils.isNotEmpty(postStr)) {
				StringEntity entity = new StringEntity(postStr, ContentType.create("plain/text", Consts.UTF_8));
				// entity.setChunked(true);
				httppost.setEntity(entity);
			}
			response = httpclient.execute(httppost);

			HttpEntity responseEntity = response.getEntity();
			if (responseEntity != null) {
				// success return: {"errcode": 0,"errmsg": "created","id": 2}
				try {
					String reponseStr = EntityUtils.toString(responseEntity);
					if (debug)
						System.out.println(reponseStr);
					// Map retMap = JSonUtils.readValue(reponseStr, Map.class);
					// String errcode = String.valueOf(retMap.get("errcode"));

					return reponseStr;

				} catch (Exception rwe) {
					rwe.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	static String httpGetRequest(URI uri) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = null;
		CloseableHttpResponse response = null;
		try {
			httpget = new HttpGet(uri);
			response = httpclient.execute(httpget);

			HttpEntity entity = response.getEntity();
			if (entity != null) {
				try {
					String responseStr = EntityUtils.toString(entity);

					if (debug)
						System.out.println(responseStr);

					return responseStr;

				} catch (Exception rwe) {
					rwe.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

}
