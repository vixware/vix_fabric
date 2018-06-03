package com.vix.system.warningSource.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.id.VixUUID;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.system.warningSource.controller.WarningSourceController;
import com.vix.system.warningSource.entity.ModuleCategory;
import com.vix.system.warningSource.entity.WarningType;

import flexjson.JSONDeserializer;

@Controller
@Scope("prototype")
public class WarningSourceAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(WarningSourceAction.class);
	@Autowired
	private WarningSourceController warningSourceController;
	private String id;
	private String ids;
	private String pageNo;
	/**
	 * 模块分类
	 */
	private ModuleCategory moduleCategory;
	private List<ModuleCategory> moduleCategoryList;

	@Override
	public String goList() {
		try {
			moduleCategoryList = warningSourceController.doListModuleCategoryIndex();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/**
	 * 根据条件查询入库单信息
	 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			// code 编码
			String code = getRequestParameter("code");
			if (null != code && !"".equals(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// status 状态
			String status = getRequestParameter("status");
			if (status != null && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			Pager pager = warningSourceController.doListModuleCategory(params, getPager());
			setPager(pager);
			logger.info("获取服务订单列表数据");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				moduleCategory = warningSourceController.doListModuleCategoryById(id);
				logger.info("");
			} else {
				moduleCategory = new ModuleCategory();
				moduleCategory.setCategoryCode(VixUUID.createCode(12));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != moduleCategory.getId()) {
				isSave = false;
			}
			/* 调拨单明细 */
			String dlJson = getRequestParameter("dlJson");
			List<WarningType> dlList = new ArrayList<WarningType>();
			if (dlJson != null && !"".equals(dlJson)) {
				dlList = new JSONDeserializer<List<WarningType>>().use("values", WarningType.class).deserialize(dlJson);
			}
			moduleCategory = warningSourceController.doSaveModuleCategory(moduleCategory, dlList);
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
			ModuleCategory pb = warningSourceController.doListModuleCategoryById("id");
			if (null != pb) {
				warningSourceController.doDeleteByEntity(pb);
				logger.info("");
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
			logger.info("删除订单信息");
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 获取明细的json数据 */

	public void getWarningTypeJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				ModuleCategory moduleCategory = warningSourceController.doListModuleCategoryById(id);
				if (moduleCategory != null) {
					json = convertListToJson(new ArrayList<WarningType>(moduleCategory.getWarningTypes()), moduleCategory.getWarningTypes().size());
				}
			}
			if (!"".equals(json)) {
				renderJson(json);
			} else {
				renderJson("{\"total\":0,\"rows\":[]}");
			}
			logger.info("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 批量处理删除操作 */
	public String deleteByIds() {
		try {
			if (null != ids && !"".equals(ids)) {
				List<String> delIds = new ArrayList<String>();
				for (String idStr : ids.split(",")) {
					if (null != idStr && !"".equals(idStr) && !"undefined".equals(idStr)) {
						delIds.add(idStr);
					}
				}
				warningSourceController.doDeleteModuleCategoryByIds(delIds);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotChoose"));
			}
			logger.info("");
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
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

	public ModuleCategory getModuleCategory() {
		return moduleCategory;
	}

	public void setModuleCategory(ModuleCategory moduleCategory) {
		this.moduleCategory = moduleCategory;
	}

	public List<ModuleCategory> getModuleCategoryList() {
		return moduleCategoryList;
	}

	public void setModuleCategoryList(List<ModuleCategory> moduleCategoryList) {
		this.moduleCategoryList = moduleCategoryList;
	}

}
