package com.vix.nvix.wx.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class PadWebUtil {
	/**
	 * 检查map中是否有keysToCheck对应的数据，
	 * 
	 * @param dataMap
	 * @return
	 */
	public static String checkMapDataValid(Map<String, Object> dataMap, String... keysToCheck) {
		return null;
	}

	public static String downloadFile(String savePath, String nameWithoutExt, URI url) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = null;
		CloseableHttpResponse response = null;
		String savedName = null;
		String savePathWithoutExt = savePath + File.separator + nameWithoutExt;
		try {
			// 获得HttpGet对象
			httpget = new HttpGet(url);
			// 发送请求获得返回结果
			response = httpclient.execute(httpget);
			// 如果成功
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				byte[] result = EntityUtils.toByteArray(response.getEntity());
				BufferedOutputStream bw = null;
				try {
					Header[] headers = response.getHeaders("Content-disposition");// response.getAllHeaders();
					String ext = findFileExtNameFromHeaders(headers);
					if (ext == null)
						ext = "";

					String savePathAndName = savePathWithoutExt + "." + ext;
					// 创建文件对象
					File f = new File(savePathAndName);
					// 创建文件路径
					if (!f.getParentFile().exists())
						f.getParentFile().mkdirs();
					// 写入文件
					bw = new BufferedOutputStream(new FileOutputStream(savePathAndName));
					bw.write(result);

					savedName = nameWithoutExt + "." + ext;

				} catch (Exception e) {
					System.out.println("WxpWeixinApiAction.MediaFileLoader:保存文件错误,path=" + savePathWithoutExt + ",url=" + url);
					throw e;
				} finally {
					try {
						if (bw != null)
							bw.close();
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("WxpWeixinApiAction.MediaFileLoader:finally BufferedOutputStream shutdown close");
					}
				}
			}
			// 如果失败
			else {
				StringBuffer errorMsg = new StringBuffer();
				errorMsg.append("httpStatus:");
				errorMsg.append(response.getStatusLine().getStatusCode());
				errorMsg.append(response.getStatusLine().getReasonPhrase());
				errorMsg.append(", Header: ");
				Header[] headers = response.getAllHeaders();
				for (Header header : headers) {
					errorMsg.append(header.getName());
					errorMsg.append(":");
					errorMsg.append(header.getValue());
				}
				System.out.println("WxpWeixinApiAction.MediaFileLoader:HttpResonse Error:" + errorMsg.toString());
			}
		} catch (ClientProtocolException e) {
			System.out.println("WxpWeixinApiAction.MediaFileLoader:下载文件保存到本地,http连接异常,path=" + savePathWithoutExt + ",url=" + url);
			throw e;
		} catch (IOException e) {
			System.out.println("WxpWeixinApiAction.MediaFileLoader:下载文件保存到本地,文件操作异常,path=" + savePathWithoutExt + ",url=" + url);
			throw e;
		} finally {
			try {
				httpclient.close();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("WxpWeixinApiAction.MediaFileLoader:finally HttpClient shutdown error");
			}
		}

		return savedName;
	}

	public static String findFileExtNameFromHeaders(Header[] headers) {

		// Header[] headers =
		// response.getHeaders("Content-disposition");//response.getAllHeaders();
		String ext = null;
		try {
			for (Header header : headers) {
				String value = header.getValue();
				int idx = value.indexOf("filename");
				if (idx != -1) {
					idx = value.indexOf("\"", idx);
					int idx2 = value.indexOf("\"", idx + 1);
					String name = value.substring(idx + 1, idx2);
					idx = name.lastIndexOf(".");
					if (idx != -1) {
						ext = name.substring(idx + 1);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ext;
	}
}
