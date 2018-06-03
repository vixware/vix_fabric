package com.vix.chain.customerLog.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.chain.customerLog.controller.CustomerLogController;
import com.vix.common.base.action.BaseAction;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.drp.MembershipCardmanagement.entity.ZoVipCardLog;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.hr.organization.entity.Employee;

@Controller
@Scope("prototype")
public class CustomerLogAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private CustomerLogController customerLogController;

	private String id;
	private String ids;
	private String pageNo;
	private ZoVipCardLog zoVipCardLog;
	private String treeType;
	private List<ZoVipCardLog> zoVipCardLogList;

	/**
	 * 跳转到数据列表页面
	 */
	@Override
	public String goList() {
		try {
			zoVipCardLogList = customerLogController.doListZoVipCardLog();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			// 获取当前员工信息
			if (SecurityUtil.getCurrentEmpId() != null) {
				Employee employee = customerLogController.doListEmployeeById(SecurityUtil.getCurrentEmpId());
				if (employee != null) {
					if (employee.getCompanyCode() != null) {
						params.put("companyCode," + SearchCondition.ANYLIKE, employee.getCompanyCode());
					}
					if (employee.getChannelDistributor() != null) {
						// 如果登录的员工属于经销商或门店
						ChannelDistributor channelDistributor = employee.getChannelDistributor();
						params.put("channelDistributor.code," + SearchCondition.ANYLIKE, channelDistributor.getCode());
					}
				}
			}
			if ("C".equals(treeType)) {
			} else if ("CH".equals(treeType)) {
				// 点击的树节点是分销体系结构
				params.put("channelDistributor.id," + SearchCondition.EQUAL, id);
			}
			Pager pager = customerLogController.doListZoVipCardLog(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
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
