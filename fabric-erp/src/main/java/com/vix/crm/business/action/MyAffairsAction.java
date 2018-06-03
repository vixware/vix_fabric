package com.vix.crm.business.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.crm.business.controller.MyAffairsController;
import com.vix.crm.business.entity.MyAffairs;

@Controller
@Scope("prototype")
public class MyAffairsAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private String id;
	private String pageNo;
	@Autowired
	private MyAffairsController myAffairsController;
	/**
	 * 我的事务
	 */
	private MyAffairs myAffairs;

	@Override
	public String goList() {
		return GO_LIST;
	}

	public String goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			//排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}

			if (null != pageNo && !"".equals(pageNo) && !"undefined".equals(pageNo)) {
				pager.setPageNo(Integer.parseInt(pageNo));
			}
			// 根据状态查询
			String status = getRequestParameter("status");
			if (status != null && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 最近使用
			String days = getRequestParameter("days");
			if (days != null && !"".equals(days)) {
				params.put("updateTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(days));
			}
			pager = myAffairsController.doListMyAffairs(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				myAffairs = myAffairsController.doListMyAffairsById(id);
			} else {
				myAffairs = new MyAffairs();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != myAffairs.getId() && !"".equals(myAffairs.getId())) {
				isSave = false;
			}
			myAffairs.setIsTemp("");
			initEntityBaseController.initEntityBaseAttribute(myAffairs);
			//处理修改留痕
			billMarkProcessController.processMark(myAffairs, updateField);
			myAffairs = myAffairsController.doSaveOrUpdateMyAffairs(myAffairs);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
			e.printStackTrace();
		}
		return UPDATE;
	}

	public MyAffairs getMyAffairs() {
		return myAffairs;
	}

	public void setMyAffairs(MyAffairs myAffairs) {
		this.myAffairs = myAffairs;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

}
