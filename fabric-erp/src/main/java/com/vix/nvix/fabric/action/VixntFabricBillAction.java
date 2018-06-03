package com.vix.nvix.fabric.action;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.web.Pager;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.fabric.entity.FabricBill;
import com.vix.nvix.fabric.entity.FabricHistoryItem;

import net.sf.json.JSONObject;

@Controller
@Scope("prototype")
public class VixntFabricBillAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	private final static String url = "http://192.168.0.156:4000/channels/mychannel/chaincodes/mycc/invoke";

	private FabricBill fabricBill;

	private String hodrCmId;

	private String waitEndorserCmId;

	private String billInfoId;

	public void goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			// 倒序排序
			Pager pager = getPager();
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, FabricBill.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void goFabricHistoryItemList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			// 倒序排序
			Pager pager = getPager();
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, FabricHistoryItem.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 待背书票据列表
	 */
	public void goWaitEndSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			// 倒序排序
			Pager pager = getPager();
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, FabricBill.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				fabricBill = vixntBaseService.findEntityById(FabricBill.class, id);
			} else {
				fabricBill = new FabricBill();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/**
	 * 跳转到用户查看页面
	 * 
	 * @return
	 */
	public String goShowFabricBill() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				fabricBill = vixntBaseService.findEntityById(FabricBill.class, id);
				queryMyBill(String.valueOf(getSession().getAttribute("cmId")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowFabricBill";
	}
	public String goEndorseFabricBill() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				fabricBill = vixntBaseService.findEntityById(FabricBill.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goEndorseFabricBill";
	}

	public void saveOrUpdate() {
		try {
			fabricBill = vixntBaseService.merge(fabricBill);
			issue(fabricBill, String.valueOf(getSession().getAttribute("token")));
			renderText(SAVE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 发布票据
	public void issue(FabricBill fabricBill, String token) {
		JSONObject json = new JSONObject();
		json.put("token", token);
		json.put("peers", "[\"peer1\"]");
		json.put("fcn", "issue");
		JSONObject fabricBillJson = new JSONObject();
		fabricBillJson.put("BillInfoID", fabricBill.getBillInfoId());
		fabricBillJson.put("BillInfoAmt", fabricBill.getBillInfoAmt());
		fabricBillJson.put("BillInfoType", fabricBill.getBillInfoType());
		fabricBillJson.put("BillInfoIsseDate", fabricBill.getBillInfoIsseDate());
		fabricBillJson.put("BillInfoDueDate", fabricBill.getBillInfoDueDate());
		fabricBillJson.put("DrwrCmID", fabricBill.getDrwrCmId());
		fabricBillJson.put("DrwrAcct", fabricBill.getDrwrAcct());
		fabricBillJson.put("AccptrCmID", fabricBill.getAccptrCmId());
		fabricBillJson.put("AccptrAcct", fabricBill.getAccptrAcct());
		fabricBillJson.put("PyeeCmID", fabricBill.getPyeeCmId());
		fabricBillJson.put("PyeeAcct", fabricBill.getPyeeAcct());
		fabricBillJson.put("HodrCmID", fabricBill.getHodrCmId());
		fabricBillJson.put("HodrAcct", fabricBill.getHodrAcct());

		List<String> modlist = new LinkedList<>();
		modlist.add("\"" + fabricBillJson.toString() + "\"");
		json.put("args", modlist);
		System.out.println(json.toString());
		String resp = postToPos(url, json.toString());
		if (StringUtils.isNotEmpty(resp)) {
			System.out.println(resp);
			JSONObject returnjson = JSONObject.fromObject(resp);
			String returnValue = returnjson.getString("success");
			if ("true".equals(returnValue)) {
				queryByBillNo(fabricBill.getBillInfoId());
				renderText("票据发布成功!");
			}
		}
	}
	// 票据背书
	public void endorse() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				FabricBill fb = vixntBaseService.findEntityById(FabricBill.class, id);
				fb.setWaitEndorserAcct(fabricBill.getWaitEndorserAcct());
				fb.setWaitEndorserCmId(fabricBill.getWaitEndorserCmId());
				fb = vixntBaseService.merge(fb);
				JSONObject json = new JSONObject();
				json.put("token", String.valueOf(getSession().getAttribute("token")));
				json.put("peers", "[\"peer1\"]");
				json.put("fcn", "endorse");
				List<String> list = Arrays.asList(new String[]{fb.getBillInfoId(), fb.getWaitEndorserCmId(), fb.getWaitEndorserAcct()});
				json.put("args", list);
				System.out.println(json);
				String resp = postToPos(url, json.toString());
				if (StringUtils.isNotEmpty(resp)) {
					System.out.println(resp);
					JSONObject returnjson = JSONObject.fromObject(resp);
					String returnValue = returnjson.getString("success");
					if ("true".equals(returnValue)) {
						queryByBillNo(fabricBill.getBillInfoId());
						renderText("票据背书成功!");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText("票据背书失败!");
		}
	}
	// 背书签收
	public void accept() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				fabricBill = vixntBaseService.findEntityById(FabricBill.class, id);
				JSONObject json = new JSONObject();
				json.put("token", String.valueOf(getSession().getAttribute("token")));
				json.put("peers", "[\"peer1\"]");
				json.put("fcn", "accept");
				List<String> list = Arrays.asList(new String[]{fabricBill.getBillInfoId(), fabricBill.getWaitEndorserCmId(), fabricBill.getWaitEndorserAcct()});
				json.put("args", list);
				System.out.println(json);
				String resp = postToPos(url, json.toString());
				if (StringUtils.isNotEmpty(resp)) {
					System.out.println(resp);
					JSONObject returnjson = JSONObject.fromObject(resp);
					String returnValue = returnjson.getString("success");
					if ("true".equals(returnValue)) {
						queryByBillNo(fabricBill.getBillInfoId());
						renderText("背书签收成功!");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 背书拒收
	public void reject() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				fabricBill = vixntBaseService.findEntityById(FabricBill.class, id);
				JSONObject json = new JSONObject();
				json.put("token", String.valueOf(getSession().getAttribute("token")));
				json.put("peers", "[\"peer1\"]");
				json.put("fcn", "reject");
				List<String> list = Arrays.asList(new String[]{fabricBill.getBillInfoId(), fabricBill.getWaitEndorserCmId(), fabricBill.getWaitEndorserAcct()});
				json.put("args", list);
				System.out.println(json);
				String resp = postToPos(url, json.toString());
				if (StringUtils.isNotEmpty(resp)) {
					System.out.println(resp);
					JSONObject returnjson = JSONObject.fromObject(resp);
					String returnValue = returnjson.getString("success");
					if ("true".equals(returnValue)) {
						queryByBillNo(fabricBill.getBillInfoId());
						renderText("背书拒收成功!");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 查询持票人的票据列表
	public void queryMyBill(String hodrCmId) {
		JSONObject json = new JSONObject();
		json.put("token", String.valueOf(getSession().getAttribute("token")));
		json.put("peers", "[\"peer1\"]");
		json.put("fcn", "queryMyBill");
		List<String> list = Arrays.asList(new String[]{hodrCmId});
		json.put("args", list);
		System.out.println(json);
		String resp = postToPos(url, json.toString());
		if (StringUtils.isNotEmpty(resp)) {
			System.out.println(resp);
			JSONObject returnjson = JSONObject.fromObject(resp);
			String returnValue = returnjson.getString("success");
			String message = returnjson.getString("message");
			if ("true".equals(returnValue)) {
				System.out.println(message);
			}
		}
	}
	// 查询待签收票据列表
	public void queryMyWaitBill() {
		JSONObject json = new JSONObject();
		json.put("token", String.valueOf(getSession().getAttribute("token")));
		json.put("peers", "[\"peer1\"]");
		json.put("fcn", "queryMyWaitBill");
		List<String> list = Arrays.asList(new String[]{waitEndorserCmId});
		json.put("args", list);
		System.out.println(json);
		String resp = postToPos(url, json.toString());
		if (StringUtils.isNotEmpty(resp)) {
			System.out.println(resp);
			JSONObject returnjson = JSONObject.fromObject(resp);
			String returnValue = returnjson.getString("success");
			if ("true".equals(returnValue)) {
			}
		}
	}
	// 根据票据号码查询票据信息
	public void queryByBillNo(String billInfoId) {
		JSONObject json = new JSONObject();
		json.put("token", String.valueOf(getSession().getAttribute("token")));
		json.put("peers", "[\"peer1\"]");
		json.put("fcn", "queryByBillNo");
		List<String> list = Arrays.asList(new String[]{billInfoId});
		json.put("args", list);
		System.out.println(json);
		String resp = postToPos(url, json.toString());
		if (StringUtils.isNotEmpty(resp)) {
			System.out.println(resp);
			JSONObject returnjson = JSONObject.fromObject(resp);
			String returnValue = returnjson.getString("success");
			String message = returnjson.getString("message");
			if ("true".equals(returnValue)) {
				System.out.println(message);
			}
		}
	}

	/**
	 * @return the fabricBill
	 */
	public FabricBill getFabricBill() {
		return fabricBill;
	}

	/**
	 * @param fabricBill
	 *            the fabricBill to set
	 */
	public void setFabricBill(FabricBill fabricBill) {
		this.fabricBill = fabricBill;
	}
	/**
	 * @return the hodrCmId
	 */
	public String getHodrCmId() {
		return hodrCmId;
	}
	/**
	 * @param hodrCmId
	 *            the hodrCmId to set
	 */
	public void setHodrCmId(String hodrCmId) {
		this.hodrCmId = hodrCmId;
	}
	/**
	 * @return the waitEndorserCmId
	 */
	public String getWaitEndorserCmId() {
		return waitEndorserCmId;
	}
	/**
	 * @param waitEndorserCmId
	 *            the waitEndorserCmId to set
	 */
	public void setWaitEndorserCmId(String waitEndorserCmId) {
		this.waitEndorserCmId = waitEndorserCmId;
	}
	/**
	 * @return the billInfoId
	 */
	public String getBillInfoId() {
		return billInfoId;
	}
	/**
	 * @param billInfoId
	 *            the billInfoId to set
	 */
	public void setBillInfoId(String billInfoId) {
		this.billInfoId = billInfoId;
	}

}