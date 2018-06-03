package com.vix.front.fabricAsset.action;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hyperledger.fabric.sdk.ChaincodeID;
import org.hyperledger.fabric.sdk.Channel;
import org.hyperledger.fabric_sdk.fabric.sdk.testutils.TestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.id.VixUUID;
import com.vix.core.utils.SortSet;
import com.vix.core.utils.StrUtils;
import com.vix.front.base.action.FabricBaseAction;
import com.vix.front.fabricAsset.entity.AssetLocation;
import com.vix.front.fabricAsset.entity.FabricAsset;
import com.vix.front.fabricAsset.service.IFabricAssetService;
import com.vix.nvix.fabric.sdk.FabricBaseSDK;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@Scope("request")
public class FabricAssetAction  extends FabricBaseAction  {

	private static final long serialVersionUID = 1L;
	@Autowired
	private IFabricAssetService fabricAssetService;

	private FabricAsset fabricAsset;
	private List<FabricAsset> fabricAssetList;
	private String id;
	private String points;//坐标集
	/*
	 * 创建资产
	 */
	public String goNewAsset() {
		try {
			if(StrUtils.objectIsNotNull(id)) {
					fabricAsset = fabricAssetService.findEntityById(FabricAsset.class, id);
			}else {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMDDHHmmss");
				String dateStr = sdf.format(new Date());
				fabricAsset = new FabricAsset();
				fabricAsset.setAssetCode("ASSET_" + dateStr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goNewAsset";
	}
	
	public String goMapPage() {
		// 初始化微信参数
		try {
			dealWechatTicket();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goMapPage";
	}
	
	public void saveOrUpdateLocation() {
		try {
			if(StrUtils.objectIsNotNull(id) && StrUtils.objectIsNotNull(points)) {
				fabricAsset = fabricAssetService.findEntityById(FabricAsset.class, id);
				if(null != fabricAsset) {
					JSONArray pointsList = JSONArray.fromObject(points);
					for(int i=0; i < pointsList.size();i++){
						AssetLocation alocation = new AssetLocation();
						JSONObject  point = pointsList.getJSONObject(i);
						Double lat  = point.getDouble("lat");
						Double lng  = point.getDouble("lng");
						alocation.setPoints(point.toString());
						alocation.setLat(lat);
						alocation.setLng(lng);
						alocation.setFabricAsset(fabricAsset);
						//fabricAsset.getAssetLocations().add(alocation);
						fabricAssetService.mergeOriginal(alocation);
					}
				}
				renderText("1:发布成功!"+":"+fabricAsset.getId());
			}else {
				renderText("0:发布失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * 资产列表
	 */
	public String goAssetList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			// params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			fabricAssetList = fabricAssetService.findAllByConditions(FabricAsset.class, params);
			SortSet ss = new SortSet("createTime");
			Collections.sort(fabricAssetList, Collections.reverseOrder(ss));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goAssetList";
	}
	/*
	 * 资产详情
	 */
	public String goAssetDetail() {
		try {
			if (StrUtils.objectIsNotNull(id)) {
				fabricAsset = fabricAssetService.findEntityById(FabricAsset.class, id);
				JSONObject jsonObject =new JSONObject();
				JSONArray jsonArray = new JSONArray();
				for(AssetLocation al : fabricAsset.getAssetLocations()) {
					jsonArray.add(al.getPoints());
				};
				jsonObject.put("points", jsonArray);
				getRequest().setAttribute("pointsStr",jsonObject.toString());
			} else {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMDDHHmmss");
				String dateStr = sdf.format(new Date());
				fabricAsset = new FabricAsset();
				fabricAsset.setAssetCode("ASSET_" + dateStr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goAssetDetail";
	}
	public void saveOrUpdateAssetInner() {
		try {
			if (null != fabricAsset && StrUtils.objectIsNotNull(fabricAsset.getId())) {
				fabricAsset.setUpdateTime(new Date());
			} else {
				fabricAsset.setCreateTime(new Date());
			}
			fabricAsset = fabricAssetService.mergeOriginal(fabricAsset);
			renderText("1:发布成功!"+":"+fabricAsset.getId());
			} catch (Exception e) {
				e.printStackTrace();
				renderText("0:发布失败！");
			}
	}

	private static final String CHAIN_CODE_NAME = "example1";
	private static final String CHAIN_CODE_PATH = "github.com/example_test";
	private static final String CHAIN_CODE_VERSION = "1";

	private static final String FOO_CHANNEL_NAME = "foo";
	private static final String BAR_CHANNEL_NAME = "bar";
	
	
	
	/*
	 * 保存发布
	 */
	public void saveOrUpdateAsset() {
		try {
			if (null != fabricAsset && StrUtils.objectIsNotNull(fabricAsset.getId())) {
				fabricAsset.setUpdateTime(new Date());
			} else {
				fabricAsset.setCreateTime(new Date());
			}
			fabricAsset = fabricAssetService.mergeOriginal(fabricAsset);
			String assetJsonStr = null;
			String key = null;
			if (fabricAsset != null) {
				key = fabricAsset.getAssetCode();
				JSONObject assetjson = new JSONObject();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				assetjson.put("assetCode", fabricAsset.getAssetCode());
				assetjson.put("assetName", fabricAsset.getAssetName());
				assetjson.put("site", fabricAsset.getSite());
				assetjson.put("plantingArea", fabricAsset.getPlantingArea());
				if (StrUtils.objectIsNotNull(fabricAsset.getDateOfAccess())) {
					assetjson.put("dateOfAccess", sdf.format(fabricAsset.getDateOfAccess()));
				}
				/*
				 * assetjson.put("originalValue", fabricAsset.getOriginalValue()); assetjson.put("useType", fabricAsset.getUseType()); if(StrUtils.objectIsNotNull(fabricAsset.getInidleTime())) { assetjson.put("inidleTime", sdf.format(fabricAsset.getInidleTime())); } assetjson.put("hsContract",
				 * fabricAsset.getHsContract()); assetjson.put("annualEarnings", fabricAsset.getAnnualEarnings()); assetjson.put("operation", fabricAsset.getOperation());
				 */
				assetJsonStr = assetjson.toString();
//				FabricBaseSDK fabricBaseSDK = new FabricBaseSDK();
//				String adminName = "admin";
//				String userName = VixUUID.createCode(15);
				//String key = "翠峰";
				//String value = "这是我的数据啊";
//				fabricBaseSDK.setup(adminName, userName);
//     			ChaincodeID chaincodeID = ChaincodeID.newBuilder().setName(CHAIN_CODE_NAME).setVersion(CHAIN_CODE_VERSION).setPath(CHAIN_CODE_PATH).build();
				
//				Channel fooChannel = fabricBaseSDK.constructChannel(FOO_CHANNEL_NAME, fabricBaseSDK.client, TestConfig.getConfig().getIntegrationTestsSampleOrg("peerOrg1"));
//				fabricBaseSDK.runChannel(fabricBaseSDK.client, fooChannel, chaincodeID, true, TestConfig.getConfig().getIntegrationTestsSampleOrg("peerOrg1"), adminName, userName, key, assetJsonStr, "save");
				//fabricBaseSDK.runChannel(fabricBaseSDK.client, fooChannel, chaincodeID, false, TestConfig.getConfig().getIntegrationTestsSampleOrg("peerOrg1"), adminName, userName, key, assetJsonStr, "query");
				
				//Channel barChannel = fabricBaseSDK.constructChannel(BAR_CHANNEL_NAME, fabricBaseSDK.client, TestConfig.getConfig().getIntegrationTestsSampleOrg("peerOrg2"));
				//fabricBaseSDK.runChannel(fabricBaseSDK.client, barChannel, chaincodeID, true, TestConfig.getConfig().getIntegrationTestsSampleOrg("peerOrg2"), adminName, userName, key, assetJsonStr, "save");
				//fabricBaseSDK.runChannel(fabricBaseSDK.client, barChannel, chaincodeID, false, TestConfig.getConfig().getIntegrationTestsSampleOrg("peerOrg2"), adminName, userName, key, assetJsonStr, "query");
				renderText("1:发布成功!"+":"+fabricAsset.getId());
			}

		} catch (Exception e) {
			e.printStackTrace();
			renderText("0:发布失败！");
		}
	}
	public FabricAsset getFabricAsset() {
		return fabricAsset;
	}
	public void setFabricAsset(FabricAsset fabricAsset) {
		this.fabricAsset = fabricAsset;
	}
	/**
	 * @return the fabricAssetList
	 */
	public List<FabricAsset> getFabricAssetList() {
		return fabricAssetList;
	}
	/**
	 * @param fabricAssetList
	 *            the fabricAssetList to set
	 */
	public void setFabricAssetList(List<FabricAsset> fabricAssetList) {
		this.fabricAssetList = fabricAssetList;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPoints() {
		return points;
	}
	public void setPoints(String points) {
		this.points = points;
	}
	
}