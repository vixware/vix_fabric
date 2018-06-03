package com.vix.drp.basicInformation.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.drp.basicInformation.controller.BasicInformationController;
import com.vix.drp.basicInformation.entity.BasicInformation;

/**
 * 基础信息
 * 
 * @author zhanghaibing
 * 
 * @date 2013-10-16
 */
@Controller
@Scope("prototype")
public class BasicInformationAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private BasicInformationController basicInformationController;
	private String id;
	private String pageNo;
	/**
	 * 基础信息
	 */
	private BasicInformation basicInformation;
	private List<BasicInformation> basicInformationList;

	@Override
	public String goList() {
		try {
			Map<String, Object> params = getParams();
			basicInformationList = basicInformationController.doListBasicInformationIndex(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/**
	 * 
	 * @return
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
			Pager pager = basicInformationController.doListBasicInformation(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	// 搜索
	public String goSearchBasicInformationList() {
		try {
			Map<String, Object> params = getParams();
			String tag = getRequestParameter("tag");
			Pager pager = null;
			if ("0".equals(tag)) {
				// 简单搜索
				pager = basicInformationController.doListBasicInformationByCon(params, getPager());
			} else {
				// 高级搜索
				pager = basicInformationController.doListBasicInformation(params, getPager());
			}
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				basicInformation = basicInformationController.doListBasicInformationById(id);
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
			if (null != basicInformation.getId() && !"".equals(basicInformation.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(basicInformation);
			basicInformation = basicInformationController.doSaveOrUpdateBasicInformation(basicInformation);
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
			BasicInformation basicInformation = basicInformationController.doListBasicInformationById(id);
			if (null != basicInformation) {
				basicInformationController.doDeleteByEntity(basicInformation);
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

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public BasicInformation getBasicInformation() {
		return basicInformation;
	}

	public void setBasicInformation(BasicInformation basicInformation) {
		this.basicInformation = basicInformation;
	}

	public List<BasicInformation> getBasicInformationList() {
		return basicInformationList;
	}

	public void setBasicInformationList(List<BasicInformation> basicInformationList) {
		this.basicInformationList = basicInformationList;
	}

}
