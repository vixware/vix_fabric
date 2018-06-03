package com.vix.front.fabricContract.action;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.VixSecAction;
import com.vix.core.utils.SortSet;
import com.vix.core.utils.StrUtils;
import com.vix.front.fabricContract.entity.FabricContract;
import com.vix.front.fabricContract.service.IFabricContractService;

import net.sf.json.JSONObject;

@Controller
@Scope("request")
public class FabricContractAction extends VixSecAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IFabricContractService fabricContractService;

	private FabricContract fabricContract;

	private String id;

	private List<FabricContract> fabricContractList;

	/*
	 * 创建合约
	 */
	public String goNewContract() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMDDHHmmss");
		String dateStr = sdf.format(new Date());
		fabricContract = new FabricContract();
		fabricContract.setContractCode("CONTRACT_" + dateStr);
		return "goNewContract";
	}

	/*
	 * 合约列表
	 */
	public String goContractList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			// params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			fabricContractList = fabricContractService.findAllByConditions(FabricContract.class, params);
			SortSet ss = new SortSet("createTime");
			Collections.sort(fabricContractList, Collections.reverseOrder(ss));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goContractList";
	}

	/*
	 * 合约详情
	 */
	public String goContractDetail() {
		try {
			if (StrUtils.objectIsNotNull(id)) {
				fabricContract = fabricContractService.findEntityById(FabricContract.class, id);
			} else {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMDDHHmmss");
				String dateStr = sdf.format(new Date());
				fabricContract = new FabricContract();
				fabricContract.setContractCode("CONTRACT_" + dateStr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goContractDetail";
	}

	/*
	 * 保存发布
	 */
	public void saveOrUpdateContract() {
		try {
			if (null != fabricContract && StrUtils.objectIsNotNull(fabricContract.getId())) {
				fabricContract.setUpdateTime(new Date());
			} else {
				fabricContract.setCreateTime(new Date());
			}
			fabricContract = fabricContractService.mergeOriginal(fabricContract);
			String contractJsonStr = null;
			if (fabricContract != null) {
				JSONObject contractJson = new JSONObject();
				contractJson.put("contractCode", fabricContract.getContractCode());

				contractJsonStr = contractJson.toString();
			}

			//fabricBaseSDK.save("张三", "李四", VixUUID.createCode(15));
			renderText("1:发布成功！");
		} catch (Exception e) {
			e.printStackTrace();
			renderText("0:发布失败！");
		}
	}

	public FabricContract getFabricContract() {
		return fabricContract;
	}

	public void setFabricContract(FabricContract fabricContract) {
		this.fabricContract = fabricContract;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<FabricContract> getFabricContractList() {
		return fabricContractList;
	}

	public void setFabricContractList(List<FabricContract> fabricContractList) {
		this.fabricContractList = fabricContractList;
	}
}