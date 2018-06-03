package com.vix.hr.job.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.org.entity.OrgView;
import com.vix.common.org.entity.Organization;
import com.vix.hr.job.controler.OrgChartController;
import com.vix.hr.job.service.IOrgChartService;

/**
 * @Description: 组织架构图
 * @author 李大鹏
 */
@Controller
@Scope("prototype")
public class OrgChartAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	Logger logger = Logger.getLogger(OrgView.class);
	/** 注入Service */
	@Autowired
	private IOrgChartService iOrgChartService;

	@Autowired
	private OrgChartController orgChartController;

	private String id;
	private String orgId;

	private String pageNo;
	private String parentId;

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	private OrgView org;

	public OrgView getOrg() {
		return org;
	}

	public void setOrg(OrgView org) {
		this.org = org;
	}

	public IOrgChartService getiOrgChartService() {
		return iOrgChartService;
	}

	public void setiOrgChartService(IOrgChartService iOrgChartService) {
		this.iOrgChartService = iOrgChartService;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Override
	public String goList() {
		try {
			if (this.orgId != null) {
				this.org = iOrgChartService.findEntityByAttribute(OrgView.class, "id", orgId);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return GO_LIST;
	}

	public String goSingleList() {
		try {
			if (this.orgId != null) {
				this.org = iOrgChartService.findEntityByAttribute(OrgView.class, "id", orgId);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return GO_SINGLE_LIST;
	}

	/** 树形结构JSON */
	public void findTreeToJson2() {
		try {
			List<Organization> listCategory = new ArrayList<Organization>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (null != id && !"".equals(id)) {
				listCategory = iOrgChartService.findAllSubEntity(Organization.class, "parentOrganization.id", id, params);
			} else {
				listCategory = iOrgChartService.findAllSubEntity(Organization.class, "parentOrganization.id", null, params);
			}
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			for (int i = 0; i < listCategory.size(); i++) {
				Organization sc = listCategory.get(i);
				if (sc.getSubOrganizations().size() > 0) {
					strSb.append("{id:");
					strSb.append(sc.getId());
					strSb.append(",name:\"");
					strSb.append(sc.getOrgName());
					strSb.append("\",open:false,isParent:true}");
				} else {
					strSb.append("{id:");
					strSb.append(sc.getId());
					strSb.append(",name:\"");
					strSb.append(sc.getOrgName());
					strSb.append("\",open:false,isParent:false}");
				}
				if (i < listCategory.size() - 1) {
					strSb.append(",");
				}
			}
			strSb.append("]");
			renderHtml(strSb.toString());
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

}
