package com.vix.drp.MembershipPointsregistration.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.id.VixUUID;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.drp.MembershipCardmanagement.entity.ZoVipCardLog;
import com.vix.drp.MembershipPointsregistration.controller.MemberShipPointsRegistrationController;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.hr.organization.entity.Employee;

@Controller
@Scope("prototype")
public class MembershipPointsregistrationAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private MemberShipPointsRegistrationController memberShipPointsRegistrationController;

	private String id;
	private String ids;
	private String pageNo;
	private String treeType;
	private ZoVipCardLog zoVipCardLog;

	private List<ZoVipCardLog> zoVipCardLogList;

	/**
	 * 跳转到数据列表页面
	 */
	@Override
	public String goList() {
		try {
			Map<String, Object> params = getParams();
			// 获取当前登录用户所在的公司或供应商
			// 获取当前员工信息
			if (SecurityUtil.getCurrentEmpId() != null) {
				Employee employee = memberShipPointsRegistrationController.doListEmployeeById(SecurityUtil.getCurrentEmpId());
				if (employee != null && employee.getChannelDistributor() != null) {
					// 如果登录的员工属于经销商或门店
					ChannelDistributor channelDistributor = employee.getChannelDistributor();
					params.put("channelDistributor.id," + SearchCondition.EQUAL, channelDistributor.getId());
				}
			}
			if (SecurityUtil.getCurrentUserAccountCompanyInnerCode() != null) {
				params.put("companyInnerCode," + SearchCondition.ANYLIKE, SecurityUtil.getCurrentUserAccountCompanyInnerCode());
			}
			if (treeType != null && "CH".equals(treeType)) {
				params.put("channelDistributor.id," + SearchCondition.EQUAL, id);
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
			zoVipCardLogList = memberShipPointsRegistrationController.doListZoVipCardLogList(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/**
	 * 公司 C 部门 O 门店经销商 CH
	 * 
	 * @return
	 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			//排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			// 获取当前登录用户所在的公司或供应商
			// 获取当前员工信息
			if (SecurityUtil.getCurrentEmpId() != null) {
				Employee employee = memberShipPointsRegistrationController.doListEmployeeById(SecurityUtil.getCurrentEmpId());
				if (employee != null && employee.getChannelDistributor() != null) {
					// 如果登录的员工属于经销商或门店
					ChannelDistributor channelDistributor = employee.getChannelDistributor();
					params.put("channelDistributor.id," + SearchCondition.EQUAL, channelDistributor.getId());
				}
			}
			if (SecurityUtil.getCurrentUserAccountCompanyInnerCode() != null) {
				params.put("companyInnerCode," + SearchCondition.ANYLIKE, SecurityUtil.getCurrentUserAccountCompanyInnerCode());
			}
			if (treeType != null && "CH".equals(treeType)) {
				params.put("channelDistributor.id," + SearchCondition.EQUAL, id);
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
			this.addAdvFilterAndSort(params, pager);
			pager = memberShipPointsRegistrationController.doListZoVipCardLog(params, pager);
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
				zoVipCardLog = memberShipPointsRegistrationController.doListZoVipCardLogById(id);
			} else {
				zoVipCardLog = new ZoVipCardLog();
				zoVipCardLog.setCode(VixUUID.getUUID());
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
			if (null != zoVipCardLog.getId() && !"".equals(zoVipCardLog.getId())) {
				isSave = false;
			}
			// 获取当前登录用户所在的公司或供应商
			String employeeId = SecurityUtil.getCurrentEmpId();
			// 获取当前员工信息
			Employee employee = memberShipPointsRegistrationController.doListEmployeeById(employeeId);
			if (employee.getChannelDistributor() != null) {
				// 如果登录的员工属于经销商或门店
				ChannelDistributor channelDistributor = employee.getChannelDistributor();
				zoVipCardLog.setChannelDistributor(channelDistributor);
			}
			initEntityBaseController.initEntityBaseAttribute(zoVipCardLog);
			//处理修改留痕
			billMarkProcessController.processMark(zoVipCardLog, updateField);
			memberShipPointsRegistrationController.doSaveZoVipCardLog(zoVipCardLog);
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
			ZoVipCardLog zoVipCardLog = memberShipPointsRegistrationController.doListZoVipCardLogById(id);
			if (null != zoVipCardLog) {
				memberShipPointsRegistrationController.doDeleteZoVipCardLog(zoVipCardLog);
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
				memberShipPointsRegistrationController.doDeleteByIds(delIds);
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
			Pager pager = memberShipPointsRegistrationController.doListMemberShipCard(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goMemberShipCardList";
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

	public ZoVipCardLog getZoVipCardLog() {
		return zoVipCardLog;
	}

	public void setZoVipCardLog(ZoVipCardLog zoVipCardLog) {
		this.zoVipCardLog = zoVipCardLog;
	}

	public List<ZoVipCardLog> getZoVipCardLogList() {
		return zoVipCardLogList;
	}

	public void setZoVipCardLogList(List<ZoVipCardLog> zoVipCardLogList) {
		this.zoVipCardLogList = zoVipCardLogList;
	}

	public String getTreeType() {
		return treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

}
