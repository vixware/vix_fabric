package com.vix.ebusiness.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.ec.api.item.domain.Sku;
import com.vix.ebusiness.util.ftp.ContinueFTP;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * @author zhanghaibing
 * 
 * @date 2014-3-14
 */
public class VixShopUtils {

	/**
	 * 沙箱测试环境
	 */
	public static final String JINGDONG_SANDBOX_URL = "http://gw.api.sandbox.360buy.com/routerjson";

	/**
	 * 新Api: http://gw.api.360buy.com/routerjson
	 */
	public static final String JINGDONG_NEW_URL = "http://gw.api.360buy.com/routerjson";

	// 云商店属性转换京东SKU属性
	public static String getJdAttributes(String props) {
		String properties = null;
		if (props != null && !"".equals(props)) {
			JSONObject obj = JSONObject.fromObject(props);
			if (obj != null && obj.has("props")) {
				String getProps = obj.getString("props");
				if (getProps != null && !"".equals(getProps)) {
					properties = getProps.replace(";", "|");
				}
			}
		}
		return properties;
	}

	public static String getGoodsAttributes(String props) {
		String properties = null;
		if (props != null && !"".equals(props)) {
			properties = props.replace(";", "|");
		}
		return properties;
	}

	public static String replaceString(String pricer) {
		String skupricer = null;
		if (pricer != null && !pricer.isEmpty()) {
			skupricer = pricer.replace(",", "|");
		}
		return skupricer;
	}

	public static String replaceString1(String properties) {
		String skuProperties = null;
		if (properties != null && !properties.isEmpty()) {
			skuProperties = properties.replace(";", "^").replace(",", "|");
		}
		return skuProperties;
	}

	public static String[] toSkuPrises(List<Sku> skus) {
		String[] values = new String[2];
		if (skus != null && !skus.isEmpty()) {
			String price = "";
			String store = "";
			for (int i = 0; i < skus.size(); i++) {
				Sku sku = skus.get(i);
				price += sku.getPrice();
				store += sku.getStore();
				if (skus.size() != i + 1) {
					price += "|";
					store += "|";
				}
			}
			values[0] = price;
			values[1] = store;
		}
		return values;
	}

	public static String toSkuProperties(List<Sku> skus) {
		String str = "";
		if (skus != null && !skus.isEmpty()) {
			for (int i = 0; i < skus.size(); i++) {
				Sku sku = skus.get(i);
				String spec = sku.getSpec(); // sku
				if (spec != null && !"".equals(spec)) {
					JSONArray array = JSONArray.fromObject(spec);
					for (int j = 0; j < array.size(); j++) { // sku.spec
						JSONObject obj = array.getJSONObject(j);
						String specVId = "", specId = "";
						if (obj.has("id"))
							specVId = obj.getString("id");
						if (obj.has("specId"))
							specId = obj.getString("specId");
						str += specId + ":" + specVId;

						if (array.size() != j + 1)
							str += "^";
					}
					if (skus.size() != i + 1)
						str += "|";
				}
			}
		}
		return str;
	}

	public static String getAttributes(String spec) {
		String str = "";
		if (spec != null && !"".equals(spec)) {
			JSONArray array = JSONArray.fromObject(spec);
			for (int j = 0; j < array.size(); j++) { // sku.spec
				JSONObject obj = array.getJSONObject(j);
				String specVId = "", specId = "";
				if (obj.has("id"))
					specVId = obj.getString("id");
				if (obj.has("specId"))
					specId = obj.getString("specId");
				str += specId + ":" + specVId;

				if (array.size() != j + 1)
					str += "^";
			}
		}
		return str;
	}

	public static byte[] getPic(String tenantId, String url, String serverIp, int serverPort, String userName, String password) throws IOException {

		ContinueFTP ftp = new ContinueFTP();
		try {
			ftp.connect(serverIp, serverPort, userName, password);
			return ftp.getContent(tenantId + "/" + url);
		} catch (IOException e) {
			throw new IOException(e);
		} finally {
			try {
				ftp.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static byte[] inputStreamToByte(InputStream is) throws IOException {
		ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
		int ch;
		while ((ch = is.read()) != -1) {
			bytestream.write(ch);
		}
		byte imgdata[] = bytestream.toByteArray();
		bytestream.close();
		return imgdata;
	}
}
