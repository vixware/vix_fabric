package com.vix.drp.natureAndScaleBranch.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.natureAndScaleBranch.controller.BranchOrganizationController;
import com.vix.drp.natureAndScaleBranch.entity.OperatingConditions;
import com.vix.drp.natureAndScaleBranch.entity.PropertiesScale;

/**
 * 分支机构管理
 * 
 * @author zhanghaibing
 * 
 * @date 2014-3-1
 */
@Controller
@Scope("prototype")
public class BranchOrganizationAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private BranchOrganizationController branchOrganizationController;
	private String id;
	private ChannelDistributor channelDistributor;
	private List<ChannelDistributor> channelDistributorList;
	/**
	 * 经营状况
	 */
	private OperatingConditions operatingConditions;
	/**
	 * 性质规模
	 */
	private PropertiesScale propertiesScale;

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			params.put("type," + SearchCondition.ANYLIKE, "5");
			Pager pager = branchOrganizationController.findPagerByHqlConditions(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				channelDistributor = branchOrganizationController.doListChannelDistributorById(id);
			} else {
				channelDistributor = new ChannelDistributor();
				channelDistributor.setIsTemp("1");
				channelDistributor = branchOrganizationController.doSaveChannelDistributor(channelDistributor);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/**
	 * 经营状况
	 * 
	 * @return
	 */
	public String goSaveOrUpdateOperatingConditions() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				operatingConditions = branchOrganizationController.doListOperatingConditionsById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateOperatingConditions";
	}

	/**
	 * 性质规模
	 * 
	 * @return
	 */
	public String goSaveOrUpdatePropertiesScale() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				propertiesScale = branchOrganizationController.doListPropertiesScaleById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdatePropertiesScale";
	}

	/**
	 * 保存渠道
	 * 
	 * @return
	 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != channelDistributor.getId() && !"".equals(channelDistributor.getId())) {
				isSave = false;
			}
			ChannelDistributor pchannelDistributor = null;
			if (getRequestParameter("channelDistributorId") != null && !"".equals(getRequestParameter("channelDistributorId"))) {
				String channelDistributorId = getRequestParameter("channelDistributorId");
				pchannelDistributor = branchOrganizationController.doListChannelDistributorById(channelDistributorId);
			}
			if (pchannelDistributor != null) {
				channelDistributor.setParentChannelDistributor(pchannelDistributor);
			}
			channelDistributor.setType("5");
			channelDistributor.setIsTemp("");
			channelDistributor = branchOrganizationController.doSaveChannelDistributor(channelDistributor);
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
	 * 保存经营状况
	 * 
	 * @return
	 */
	public String saveOrUpdateOperatingConditions() {
		boolean isSave = true;
		try {
			if (null != operatingConditions.getId() && !"".equals(operatingConditions.getId())) {
				isSave = false;
			}
			channelDistributor = branchOrganizationController.doListChannelDistributorById(id);
			operatingConditions.setChannelDistributor(channelDistributor);
			operatingConditions = branchOrganizationController.doSaveOperatingConditions(operatingConditions);
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
	 * 保存性质规模
	 * 
	 * @return
	 */
	public String saveOrUpdatePropertiesScale() {
		boolean isSave = true;
		try {
			if (null != propertiesScale.getId() && !"".equals(propertiesScale.getId())) {
				isSave = false;
			}
			channelDistributor = branchOrganizationController.doListChannelDistributorById(id);
			propertiesScale.setChannelDistributor(channelDistributor);
			propertiesScale = branchOrganizationController.doSavePropertiesScale(propertiesScale);
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

	/** 处理删除操作 */
	public String deleteById() {
		try {
			ChannelDistributor channelDistributor = branchOrganizationController.doListChannelDistributorById(id);
			if (null != channelDistributor) {
				branchOrganizationController.doDeleteByEntity(channelDistributor);
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

	/** 处理删除操作 */
	public String deleteOperatingConditionsById() {
		try {
			OperatingConditions operatingConditions = branchOrganizationController.doListOperatingConditionsById(id);
			if (null != operatingConditions) {
				branchOrganizationController.doDeleteOperatingConditionsByEntity(operatingConditions);
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

	/** 处理删除操作 */
	public String deletePropertiesScaleById() {
		try {
			PropertiesScale propertiesScale = branchOrganizationController.doListPropertiesScaleById(id);
			if (null != propertiesScale) {
				branchOrganizationController.doDeletePropertiesScaleByEntity(propertiesScale);
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

	public void getOperatingConditionsJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				ChannelDistributor channelDistributor = branchOrganizationController.doListChannelDistributorById(id);
				if (channelDistributor != null) {
					json = convertListToJson(new ArrayList<OperatingConditions>(channelDistributor.getOperatingConditionss()), channelDistributor.getOperatingConditionss().size());
				}
			}
			if (!"".equals(json)) {
				renderJson(json);
			} else {
				renderJson("{\"total\":0,\"rows\":[]}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getPropertiesScaleJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				ChannelDistributor channelDistributor = branchOrganizationController.doListChannelDistributorById(id);
				if (channelDistributor != null) {
					json = convertListToJson(new ArrayList<PropertiesScale>(channelDistributor.getPropertiesScales()), channelDistributor.getPropertiesScales().size());
				}
			}
			if (!"".equals(json)) {
				renderJson(json);
			} else {
				renderJson("{\"total\":0,\"rows\":[]}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ChannelDistributor getChannelDistributor() {
		return channelDistributor;
	}

	public void setChannelDistributor(ChannelDistributor channelDistributor) {
		this.channelDistributor = channelDistributor;
	}

	public List<ChannelDistributor> getChannelDistributorList() {
		return channelDistributorList;
	}

	public void setChannelDistributorList(List<ChannelDistributor> channelDistributorList) {
		this.channelDistributorList = channelDistributorList;
	}

	public OperatingConditions getOperatingConditions() {
		return operatingConditions;
	}

	public void setOperatingConditions(OperatingConditions operatingConditions) {
		this.operatingConditions = operatingConditions;
	}

	public PropertiesScale getPropertiesScale() {
		return propertiesScale;
	}

	public void setPropertiesScale(PropertiesScale propertiesScale) {
		this.propertiesScale = propertiesScale;
	}

}
