package com.vix.chain.tranLog.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.chain.tranLog.controller.TranLogController;
import com.vix.common.base.action.BaseAction;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.drp.rides.entity.TranLog;
import com.vix.hr.organization.entity.Employee;

@Controller
@Scope("prototype")
public class TranLogAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private TranLogController tranLogController;

	private String id;
	private String ids;
	private String pageNo;
	private TranLog tranLog;
	private String treeType;
	private List<TranLog> tranLogList;

	/**
	 * 跳转到数据列表页面
	 */
	@Override
	public String goList() {
		try {
			tranLogList = tranLogController.doListTranLog();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();

			if (SecurityUtil.getCurrentEmpId() != null) {
				// 获取当前员工信息
				Employee employee = tranLogController.doListEmployee(SecurityUtil.getCurrentEmpId());
				if (employee != null) {
					if (employee.getCompanyCode() != null) {
						params.put("companyCode," + SearchCondition.ANYLIKE, employee.getCompanyCode());
					}
					if (employee.getChannelDistributor() != null) {
						// 如果登录的员工属于经销商或门店
						params.put("channelDistributor.code," + SearchCondition.ANYLIKE, employee.getChannelDistributor().getCode());
					}
				}
			}
			if ("C".equals(treeType)) {
			} else if ("CH".equals(treeType)) {
				// 点击的树节点是分销体系结构
				params.put("channelDistributor.id," + SearchCondition.EQUAL, id);
			}
			Pager pager = tranLogController.doListTranLog(params, getPager());
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

	public TranLog getTranLog() {
		return tranLog;
	}

	public void setTranLog(TranLog tranLog) {
		this.tranLog = tranLog;
	}

	public List<TranLog> getTranLogList() {
		return tranLogList;
	}

	public void setTranLogList(List<TranLog> tranLogList) {
		this.tranLogList = tranLogList;
	}

	public String getTreeType() {
		return treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

}
