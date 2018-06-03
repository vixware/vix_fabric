package com.vix.drp.printMediaManagement.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.drp.printMediaManagement.controller.PrintMediaManagementController;
import com.vix.drp.printMediaManagement.entity.PrintMedia;

@Controller
@Scope("prototype")
public class PrintMediaManagementAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private PrintMediaManagementController printMediaManagementController;
	private String id;
	private PrintMedia printMedia;
	private List<PrintMedia> printMediaList;

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			Pager pager = printMediaManagementController.findPagerByHqlConditions(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				printMedia = printMediaManagementController.doListPrintMediaById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/**
	 * 
	 * @return
	 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != printMedia.getId() && !"".equals(printMedia.getId())) {
				isSave = false;
			}
			printMedia.setIsTemp("");
			printMedia = printMediaManagementController.doSavePrintMedia(printMedia);
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
			PrintMedia printMedia = printMediaManagementController.doListPrintMediaById(id);
			if (null != printMedia) {
				printMediaManagementController.doDeleteByEntity(printMedia);
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PrintMedia getPrintMedia() {
		return printMedia;
	}

	public void setPrintMedia(PrintMedia printMedia) {
		this.printMedia = printMedia;
	}

	public List<PrintMedia> getPrintMediaList() {
		return printMediaList;
	}

	public void setPrintMediaList(List<PrintMedia> printMediaList) {
		this.printMediaList = printMediaList;
	}

}
