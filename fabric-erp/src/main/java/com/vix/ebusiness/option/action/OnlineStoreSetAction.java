package com.vix.ebusiness.option.action;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.channel.entity.StoreType;
import com.vix.ebusiness.option.entity.Logistics;
import com.vix.ebusiness.util.channel.ChannelClassStatus;

import net.sf.json.JSONObject;

@Controller
@Scope("prototype")
public class OnlineStoreSetAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IBaseHibernateService baseHibernateService;
	private String id;
	private String ids;
	private String pageNo;
	/**
	 * 店铺类型
	 */
	private StoreType storeType;
	/**
	 * 物流公司
	 */
	private Logistics logistics;
	/**
	 * 网店
	 */
	private ChannelDistributor channelDistributor;
	/**
	 * 网店类型
	 */
	private List<StoreType> storeTypeList;

	private String storeurl;
	private Long channelTypeId = 0l;
	// 京东授权
	private String oAuthURL = "http://auth.sandbox.360buy.com/oauth/authorize";
	private String callBackURL = "http://www.vixware.com.cn:8080/vix/";
	private String code;

	@Override
	public String goList() {
		try {
			storeTypeList = baseHibernateService.findAllByEntityClass(StoreType.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	public String goLogisticsPage() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), Logistics.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goLogistics";
	}

	public String goStoreTypePage() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), StoreType.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goStoreType";
	}

	public String goStorePage() {
		try {
			storeTypeList = baseHibernateService.findAllByEntityClass(StoreType.class);
			Map<String, Object> params = getParams();
			params.put("type," + SearchCondition.ANYLIKE, "5");
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), ChannelDistributor.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goStore";
	}

	/**
	 * 保存网店信息
	 * 
	 * @return
	 */
	public String saveOrUpdateChannelDistributor() {
		boolean isSave = true;
		try {
			channelDistributor.setType("5");
			initEntityBaseController.initEntityBaseAttribute(channelDistributor);
			if (channelDistributor.getStoreType() != null) {
				channelDistributor.setChannelTypeId(channelDistributor.getStoreType().getType());
			}
			channelDistributor.setChannelName(channelDistributor.getName() != null ? channelDistributor.getName().trim() : "");
			channelDistributor.setState(0);
			channelDistributor.setChannelClass(ChannelClassStatus.MALL);
			// 组合JSON数据
			JSONObject prop = new JSONObject();
			prop.put("url", storeurl != null ? storeurl.trim() : "");
			prop.put("nick", channelDistributor.getNick() != null ? channelDistributor.getNick().trim() : "");
			prop.put("session_key", channelDistributor.getSession() != null ? channelDistributor.getSession().trim() : "");
			prop.put("mallType", channelDistributor.getMallType() != null ? channelDistributor.getMallType().trim() : "0");
			prop.put("app_id", channelDistributor.getAppKey() != null ? channelDistributor.getAppKey().trim() : "");
			prop.put("app_secret", channelDistributor.getAppSecret() != null ? channelDistributor.getAppSecret().trim() : "");
			prop.put("apiVersion", 2);
			channelDistributor.setChannelProps(prop.toString());
			// 组合JSON数据
			channelDistributor = baseHibernateService.merge(channelDistributor);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	/**
	 * 保存电商平台类型
	 * 
	 * @return
	 */

	public String saveOrUpdateStoreType() {
		try {
			storeType = baseHibernateService.merge(storeType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return UPDATE;
	}

	/**
	 * 保存物流公司信息
	 * 
	 * @return
	 */
	public String saveOrUpdateLogistics() {
		try {
			initEntityBaseController.initEntityBaseAttribute(logistics);
			logistics = baseHibernateService.mergeOriginal(logistics);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return UPDATE;
	}

	public String goChooseStoreType() {
		return "goChooseStoreType";
	}

	public void goJDAuthorize() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				ChannelDistributor channelDistributor = baseHibernateService.findEntityById(ChannelDistributor.class, id);
				if (channelDistributor != null) {
					storeurl = getJingDongURL(channelDistributor);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 树形结构JSON */
	public void findStoreTypeTree() {
		try {
			List<StoreType> storeTypeList = baseHibernateService.findAllByEntityClass(StoreType.class);
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			int count = storeTypeList.size();
			for (int i = 0; i < count; i++) {
				StoreType org = storeTypeList.get(i);
				strSb.append("{id:\"");
				strSb.append(org.getId());
				strSb.append("\",name:\"");
				strSb.append(org.getName());
				strSb.append("\",open:false,isParent:false}");
				if (i < count - 1) {
					strSb.append(",");
				}
			}
			strSb.append("]");
			renderHtml(strSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 删除网店
	public String deleteStoreById() {
		try {
			ChannelDistributor channelDistributor = baseHibernateService.findEntityById(ChannelDistributor.class, id);
			if (null != channelDistributor) {
				baseHibernateService.deleteByEntity(channelDistributor);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	// 删除物流公司
	public String deleteLogisticsById() {
		try {
			Logistics logistics = baseHibernateService.findEntityById(Logistics.class, id);
			if (null != logistics) {
				baseHibernateService.deleteByEntity(logistics);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	// 删除电商平台类型
	public String deleteStoreTypeById() {
		try {
			StoreType storeType = baseHibernateService.findEntityById(StoreType.class, id);
			if (null != storeType) {
				baseHibernateService.deleteByEntity(storeType);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	// 授权
	public void goAuthorize() {

		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				ChannelDistributor channelDistributor = baseHibernateService.findEntityById(ChannelDistributor.class, id);

				storeurl = getJingDongURL(channelDistributor);

				String u = "https://auth.360buy.com/oauth/token?grant_type=authorization_code&client_id=" + channelDistributor.getAppKey() + "&client_secret=" + channelDistributor.getAppSecret() + "&scope=read&redirect_uri=http://" + "www.vixware.com.cn:8080/vix/" + "&code=" + code + "&state=1234";
				URL uri = new URL(u);
				HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
				conn.setRequestProperty("Accept-Charset", "utf-8");
				conn.setRequestMethod("POST");
				InputStream is = conn.getInputStream();
				String jsonStr = inputStream2String(is);
				System.out.println(jsonStr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String inputStream2String(InputStream is) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int i = -1;
		while ((i = is.read()) != -1) {
			baos.write(i);
		}
		return baos.toString();
	}

	// 京东授权
	public String getJingDongURL(ChannelDistributor channelDistributor) throws Exception {
		JSONObject jsonObject = null;
		if (channelDistributor != null) {
			jsonObject = JSONObject.fromObject(channelDistributor.getChannelProps());
		}
		return oAuthURL + "?response_type=code&client_id=" + jsonObject.getString("app_id") + "&redirect_uri=http://218.24.94.213&scope=write,read&state=" + channelDistributor.getId();
	}

	// 淘宝授权
	private String taobaoCurrentURL;
	private String taobaOAuthorizeURL;
	private String taobaCallback;
	private String taobaoContainerURL;

	public String getTaobaoURL(JSONObject json) throws Exception {
		if (json != null) {
			String key = json.getString("app_id") != null ? json.getString("app_id").trim() : "";
			int accredit = 0;
			if (json.has("accredit"))
				accredit = json.getInt("accredit");
			if (accredit == 2) { // oauth授权

				int mallType = json.getInt("mallType");
				String view = "tmall";
				if (mallType == 0) {
					view = "web";
				}
				taobaoCurrentURL = taobaOAuthorizeURL + "?response_type=code&client_id=" + key + "&redirect_uri=http://" + getRequest().getHeader("host") + taobaCallback + "&view=" + view;

			} else { // top授权
				boolean mode = json.getBoolean("mode");
				if (mode) {
					taobaoCurrentURL = taobaoContainerURL + "?appkey=" + key + "&encode=utf-8";
				} else {
					taobaoCurrentURL = taobaoContainerURL + "?appkey=" + key + "&encode=utf-8";
				}
			}
		}
		return taobaoCurrentURL;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public StoreType getStoreType() {
		return storeType;
	}

	public void setStoreType(StoreType storeType) {
		this.storeType = storeType;
	}

	public Logistics getLogistics() {
		return logistics;
	}

	public void setLogistics(Logistics logistics) {
		this.logistics = logistics;
	}

	public ChannelDistributor getChannelDistributor() {
		return channelDistributor;
	}

	public void setChannelDistributor(ChannelDistributor channelDistributor) {
		this.channelDistributor = channelDistributor;
	}

	public List<StoreType> getStoreTypeList() {
		return storeTypeList;
	}

	public void setStoreTypeList(List<StoreType> storeTypeList) {
		this.storeTypeList = storeTypeList;
	}

	public String getStoreurl() {
		return storeurl;
	}

	public void setStoreurl(String storeurl) {
		this.storeurl = storeurl;
	}

	public Long getChannelTypeId() {
		return channelTypeId;
	}

	public void setChannelTypeId(Long channelTypeId) {
		this.channelTypeId = channelTypeId;
	}

	public String getoAuthURL() {
		return oAuthURL;
	}

	public void setoAuthURL(String oAuthURL) {
		this.oAuthURL = oAuthURL;
	}

	public String getCallBackURL() {
		return callBackURL;
	}

	public void setCallBackURL(String callBackURL) {
		this.callBackURL = callBackURL;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTaobaoCurrentURL() {
		return taobaoCurrentURL;
	}

	public void setTaobaoCurrentURL(String taobaoCurrentURL) {
		this.taobaoCurrentURL = taobaoCurrentURL;
	}

	public String getTaobaOAuthorizeURL() {
		return taobaOAuthorizeURL;
	}

	public void setTaobaOAuthorizeURL(String taobaOAuthorizeURL) {
		this.taobaOAuthorizeURL = taobaOAuthorizeURL;
	}

	public String getTaobaCallback() {
		return taobaCallback;
	}

	public void setTaobaCallback(String taobaCallback) {
		this.taobaCallback = taobaCallback;
	}

	public String getTaobaoContainerURL() {
		return taobaoContainerURL;
	}

	public void setTaobaoContainerURL(String taobaoContainerURL) {
		this.taobaoContainerURL = taobaoContainerURL;
	}

}
