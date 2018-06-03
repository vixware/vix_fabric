package com.vix.nvix.wx.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
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

import com.vix.core.utils.JSonUtils;

/**
 * 微信公众号接口工具类
 * 
 * @类全名 com.vix.diandoc.wechat.util.WxQyUtil
 *
 * @author zhanghaibing
 *
 * @date 2017年4月6日
 */
public class WxQyUtil {

	private static boolean debug = true;
	public static String api_weixin_qq_server = "api.weixin.qq.com";
	public static String QR_LIMIT_SCENE = "QR_LIMIT_SCENE";// 永久
	public static String QR_SCENE = "QR_SCENE";// 临时
	public static String mp_weixin_qq_com = "mp.weixin.qq.com";//

	/**
	 * https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&
	 * appid=APPID&secret=APPSECRET 从微信服务器获取新access_token数据
	 */
	public static Map<?, ?> reloadAccessToken(String appId, String secret) {
		if (StringUtils.isEmpty(appId) || StringUtils.isEmpty(secret))
			return null;
		try {
			URI uri = new URIBuilder().setScheme("https").setHost(api_weixin_qq_server).setPath("/cgi-bin/token").setParameter("grant_type", "client_credential").setParameter("appid", appId).setParameter("secret", secret).build();

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
						if (debug)
							System.out.println(accessTokenRequestStr);
						return resetAccessToken(accessTokenRequestStr);
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
	 * 获取微信服务器IP地址
	 * 
	 * @param access_token
	 * @return
	 */
	public static Map<?, ?> getCallbackIp(String access_token) {
		try {
			// https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token=ACCESS_TOKEN
			URI uri = new URIBuilder().setScheme("https").setHost(api_weixin_qq_server).setPath("/cgi-bin/getcallbackip").setParameter("access_token", access_token).build();

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
						if (debug)
							System.out.println(accessTokenRequestStr);
						return resetAccessToken(accessTokenRequestStr);
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
	 * 通过OpenID来获取用户基本信息
	 * 
	 * @param access_token
	 * @param openid
	 * @return
	 */
	public static Map<?, ?> info(String access_token, String openid) {
		try {
			// https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
			URI uri = new URIBuilder().setScheme("https").setHost(api_weixin_qq_server).setPath("/cgi-bin/user/info").setParameter("access_token", access_token).setParameter("openid", openid).setParameter("lang", "zh_CN").build();

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
						if (debug)
							System.out.println(accessTokenRequestStr);
						return resetAccessToken(accessTokenRequestStr);
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
	 * 
	 * @param jsonstr
	 * @return
	 */
	public static Map<?, ?> resetAccessToken(String jsonstr) {
		try {
			Map<?, ?> dataMap = JSonUtils.readValue(jsonstr, Map.class);
			return dataMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String httpPostRequest(URI uri, String postStr) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = null;
		CloseableHttpResponse response = null;
		try {
			httppost = new HttpPost(uri);
			if (StringUtils.isNotEmpty(postStr)) {
				StringEntity entity = new StringEntity(postStr, ContentType.create("plain/text", Consts.UTF_8));
				httppost.setEntity(entity);
			}
			response = httpclient.execute(httppost);

			HttpEntity responseEntity = response.getEntity();
			if (responseEntity != null) {
				try {
					String reponseStr = EntityUtils.toString(responseEntity);
					if (debug)
						System.out.println(reponseStr);
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

	/**
	 * 发送模板消息
	 * 
	 * @param accessToken
	 * @param data
	 * @return
	 */
	public static String sendTemplateMsg(String accessToken, String data) {
		try {
			// https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN
			URI uri = new URIBuilder().setScheme("https").setHost(api_weixin_qq_server).setPath("/cgi-bin/message/template/send").setParameter("access_token", accessToken).build();
			String request = WxQyUtil.httpPostRequest(uri, data);
			System.out.println(request.getBytes());
			return request;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 获取模板列表
	public static String getAllPrivateTemplate(String accessToken) {
		try {
			// https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=ACCESS_TOKEN
			URI uri = new URIBuilder().setScheme("https").setHost(api_weixin_qq_server).setPath("/cgi-bin/template/get_all_private_template").setParameter("access_token", accessToken).build();
			String request = WxQyUtil.httpPostRequest(uri, null);
			System.out.println(request.getBytes());
			return request;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 创建二维码ticket
	 * 
	 * @param accessToken
	 * @param data
	 * @return
	 */
	public static String create(String accessToken, String scene_id) {
		try {
			// https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN
			URI uri = new URIBuilder().setScheme("https").setHost(api_weixin_qq_server).setPath("/cgi-bin/qrcode/create").setParameter("access_token", accessToken).build();
			String data = getCreateJson(scene_id);
			String request = WxQyUtil.httpPostRequest(uri, data);
			if (StringUtils.isNotEmpty(request)) {
				Map<?, ?> dataMap = resetAccessToken(request);
				if (dataMap != null) {
					if (dataMap.containsKey("ticket")) {
						String ticket = (String) dataMap.get("ticket");
						if (StringUtils.isNotEmpty(ticket)) {
							return ticket;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param scene_id
	 * @return
	 */
	private static String getCreateJson(String scene_id) {
		// {"action_name":"QR_LIMIT_SCENE",
		// "action_info":{"scene":{"scene_id":123 }} }
		StringBuilder sb = new StringBuilder("");
		sb.append("{\"action_name\":\"").append(QR_LIMIT_SCENE).append("\",");
		sb.append("\"action_info\":{");
		sb.append("\"").append("scene").append("\":{");
		sb.append("\"scene_id\":\"").append(scene_id).append("\"}}}");
		return sb.toString();
	}

	public static InputStream getInputStream(URL url) {
		InputStream is = null;
		try {
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			http.setRequestMethod("GET"); // 必须是get方式请求
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
			http.connect();
			// 获取文件转化为byte流
			is = http.getInputStream();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return is;
	}

	/**
	 * 保存二维码到本地
	 * 
	 * @param url
	 * @throws Exception
	 */

	public static String saveQrCode(String filePath, String fileName, String accessToken, String scene_id) {
		String ticket = create(accessToken, scene_id);
		String pathAndName = null;
		InputStream inputStream = null;
		FileOutputStream fileOutputStream = null;
		if (StringUtils.isNotEmpty(ticket)) {
			try {
				ticket = URLEncoder.encode(ticket, "ISO8859_1");
				String url = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=" + ticket;
				URL urlGet = new URL(url);

				String separator = System.getProperty("file.separator");
				/** 上传目录 */
				String basePath = "upload" + separator + "mobile" + separator + "image" + separator + "qrcode" + separator;
				String saveFolder = filePath + separator + basePath;
				File dir = new File(saveFolder);
				if (!dir.exists())
					dir.mkdirs();
				inputStream = getInputStream(urlGet);

				String saveFileName = fileName + ".jpg";
				String saveFilePath = saveFolder + separator + saveFileName;

				byte[] data = new byte[1024];
				int len = 0;
				fileOutputStream = new FileOutputStream(saveFilePath);
				while ((len = inputStream.read(data)) != -1) {
					fileOutputStream.write(data, 0, len);
				}
				fileOutputStream.close();

				pathAndName = "/img/diandoc/" + basePath.replace("\\", "/") + saveFileName;
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (inputStream != null) {
					try {
						inputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (fileOutputStream != null) {
					try {
						fileOutputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return pathAndName;
	}

	public static String getMediaByMediaId(String filePath, String fileName, String accessToken, String mediaId) {
		String pathAndName = null;
		InputStream inputStream = null;
		FileOutputStream fileOutputStream = null;
		try {
			String url = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=" + accessToken + "&media_id=" + mediaId;
			URL urlGet = new URL(url);

			String separator = System.getProperty("file.separator");
			/** 上传目录 */
			String basePath = "upload" + separator + "mobile" + separator + "image" + separator + "head" + separator;
			String saveFolder = filePath + separator + basePath;
			File dir = new File(saveFolder);
			if (!dir.exists())
				dir.mkdirs();
			inputStream = getInputStream(urlGet);

			String saveFileName = fileName;
			String saveFilePath = saveFolder + separator + saveFileName;

			byte[] data = new byte[1024];
			int len = 0;
			fileOutputStream = new FileOutputStream(saveFilePath);
			while ((len = inputStream.read(data)) != -1) {
				fileOutputStream.write(data, 0, len);
			}
			fileOutputStream.close();
			
			pathAndName = "/img/diandoc/" + basePath.replace("\\", "/") + saveFileName;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return pathAndName;
	}

	// https://api.weixin.qq.com/cgi-bin/ticket/getticket?type=jsapi&access_token=ACCESS_TOKEN
	public static String getJsApiTicket(String accessToken) {
		try {
			URI uri = new URIBuilder().setScheme("https").setHost(api_weixin_qq_server).setPath("/cgi-bin/ticket/getticket").setParameter("type", "jsapi").setParameter("access_token", accessToken).build();
			String request = WxQyUtil.httpPostRequest(uri, null);
			return request;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
