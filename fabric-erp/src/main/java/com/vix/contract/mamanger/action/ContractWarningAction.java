package com.vix.contract.mamanger.action;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.org.entity.Organization;
import com.vix.common.security.util.SecurityUtil;
import com.vix.contract.mamanger.controller.ContractWarningController;
import com.vix.contract.mamanger.entity.ContractWarning;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;

@Controller
@Scope("prototype")
public class ContractWarningAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(ContractWarningController.class);
	/** 注入service */
	@Autowired
	private ContractWarningController contractWarningController;
	private List<ContractWarning> contractWarningList;
	private String id;
	private String pageNo;
	private ContractWarning contractWarning;
	private Date updateTime;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	/** 跳转到列表页面 */
	@Override
	public String goList() {
		try {
			contractWarningList = contractWarningController.doListContractWarningIndex();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			/*按最近使用*/
			String warnningTime = getRequestParameter("warnningTime");
			if (warnningTime != null && !"".equals(warnningTime)) {
				params.put("warnningTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(warnningTime));
			}
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("warnningTime");
				getPager().setOrderBy("desc");
			}
			/*uploadPersonId包含当前登录人id*/
			String employeeId = SecurityUtil.getCurrentUserId();
			params.put("uploadPersonId," + SearchCondition.EQUAL,employeeId);
			Pager pager = contractWarningController.doSubSingleList(params,getPager());
			logger.info("获取合同预警列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	
	/** 树形结构JSON */
	public void findTreeToJson() {
		try {
			List<Organization> listOrganization = new ArrayList<Organization>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				listOrganization = baseHibernateService.findAllSubEntity(Organization.class, "parentOrganization.id", id, params);
			} else {
				listOrganization = baseHibernateService.findAllSubEntity(Organization.class, "parentOrganization.id", null, params);
			}
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			int count = listOrganization.size();
			for (int i = 0; i < count; i++) {
				Organization org = listOrganization.get(i);
				if (org.getSubOrganizations().size() > 0) {
					strSb.append("{id:\"");
					strSb.append(org.getId());
					strSb.append("\",name:\"");
					strSb.append(org.getOrgName());
					strSb.append("\",open:false,isParent:true}");
				} else {
					strSb.append("{id:\"");
					strSb.append(org.getId());
					strSb.append("\",name:\"");
					strSb.append(org.getOrgName());
					strSb.append("\",open:false,isParent:false}");
				}
				if (i < count - 1) {
					strSb.append(",");
				}
			}
			strSb.append("]");
			renderHtml(strSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<ContractWarning> getContractWarningList() {
		return contractWarningList;
	}

	public void setContractWarningList(List<ContractWarning> contractWarningList) {
		this.contractWarningList = contractWarningList;
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

	public ContractWarning getContractWarning() {
		return contractWarning;
	}

	public void setContractWarning(ContractWarning contractWarning) {
		this.contractWarning = contractWarning;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public SimpleDateFormat getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(SimpleDateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}

	
}
