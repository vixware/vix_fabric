package com.vix.drp.RegistrationExpiredpoints.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.id.VixUUID;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.drp.RegistrationExpiredpoints.controller.ExpiredIntegralController;
import com.vix.drp.RegistrationExpiredpoints.entity.ExpiredIntegral;

@Controller
@Scope("prototype")
public class ExpiredIntegralAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private ExpiredIntegralController expiredIntegralController;

	private String id;
	private String ids;
	private String pageNo;
	private ExpiredIntegral expiredIntegral;

	private List<ExpiredIntegral> expiredIntegralList;

	/**
	 * 跳转到数据列表页面
	 */
	@Override
	public String goList() {
		try {
			Map<String, Object> params = getParams();
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
			expiredIntegralList = expiredIntegralController.doListExpiredIntegralList(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
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
			this.addAdvFilterAndSort(params, pager);
			pager = expiredIntegralController.doListExpiredIntegral(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				expiredIntegral = expiredIntegralController.doListExpiredIntegralById(id);
			} else {
				expiredIntegral = new ExpiredIntegral();
				expiredIntegral.setCode(VixUUID.getUUID());
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
			if (StringUtils.isNotEmpty(expiredIntegral.getId()) && !"0".equals(expiredIntegral.getId())) {
				isSave = false;
			}
			//处理修改留痕
			billMarkProcessController.processMark(expiredIntegral, updateField);
			expiredIntegralController.doSaveExpiredIntegral(expiredIntegral);
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
			ExpiredIntegral expiredIntegral = expiredIntegralController.doListExpiredIntegralById(id);
			if (null != expiredIntegral) {
				expiredIntegralController.doDeleteExpiredIntegral(expiredIntegral);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 批量处理删除操作 */
	public String deleteByIds() {
		try {
			if (StringUtils.isNotEmpty(ids)) {
				List<String> delIds = new ArrayList<String>();
				for (String idStr : ids.split(",")) {
					if (null != idStr && !"".equals(idStr) && !"undefined".equals(idStr)) {
						delIds.add(idStr);
					}
				}
				expiredIntegralController.doDeleteByIds(delIds);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	// 跳转到选择会员卡页面
	public String goChooseMemberShipCard() {
		return "goChooseMemberShipCard";
	}

	public String goMemberShipCardList() {
		try {
			Map<String, Object> params = getParams();
			String name = getRequestParameter("searchContent");
			String status = getRequestParameter("status");
			// 按状态
			if (null != name && !"".equals(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			// 按状态
			if (null != status && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 按最近使用
			Pager pager = expiredIntegralController.doListMemberShipCard(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goMemberShipCardList";
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

	public ExpiredIntegral getExpiredIntegral() {
		return expiredIntegral;
	}

	public void setExpiredIntegral(ExpiredIntegral expiredIntegral) {
		this.expiredIntegral = expiredIntegral;
	}

	public List<ExpiredIntegral> getExpiredIntegralList() {
		return expiredIntegralList;
	}

	public void setExpiredIntegralList(List<ExpiredIntegral> expiredIntegralList) {
		this.expiredIntegralList = expiredIntegralList;
	}

}
