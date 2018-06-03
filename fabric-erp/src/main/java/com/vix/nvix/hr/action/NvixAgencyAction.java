package com.vix.nvix.hr.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.rest.ebusiness.util.Md5Util;
import com.vix.common.org.entity.Organization;
import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.org.service.IOrganizationService;
import com.vix.common.security.entity.UserAccount;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.wechat.base.entity.WxpQyWeixinSite;
import com.vix.wechat.base.util.WxQyUtil;

import net.sf.json.JSONObject;
/**
 * @组织架构
 * 
 * @author luchuan
 *
 */
@Controller
@Scope("prototype")
public class NvixAgencyAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	private String id;
	private String treeType;
	private OrganizationUnit organizationUnit;
	private String parentId;
	private UserAccount userAccount;
	private Organization organization;
	private Organization parentOrganization;//集团
	
	@Autowired
	private IOrganizationService organizationService;
	
	/** 获取列表数据 */
	@SuppressWarnings("unchecked")
	public void goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			if (StringUtils.isNotEmpty(parentId)) {
				params.put("parentOrganization.id," + SearchCondition.EQUAL, parentId);
			}
			String orgName = getDecodeRequestParameter("orgName");
			if (StringUtils.isNotEmpty(orgName)) {
				params.put("orgName," + SearchCondition.ANYLIKE, orgName);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, Organization.class, params);
			if (pager.getResultList().size() < 10) {
				int listSize = pager.getResultList().size();
				for (int i = 0; i < 10 - listSize; i++) {
					pager.getResultList().add(new Organization());
				}
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				organization = vixntBaseService.findEntityById(Organization.class, id);
			} else {
				organization = new Organization();
				if (StringUtils.isNotEmpty(parentId)) {
					  parentOrganization = vixntBaseService.findEntityById(Organization.class, parentId);
					  if (parentOrganization != null) {
						  organization.setParentOrganization(parentOrganization);
					  }
				}
				Employee employee = getEmployee();
				if (employee != null) {
					organization.setCreator(employee.getName());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			String py = ChnToPinYin.getPinYinHeadChar(organization.getBriefName()).toUpperCase();
			if (organization != null && StringUtils.isNotEmpty(organization.getId()) && !"0".equals(organization.getId())) {
				isSave = false;
			}
			if (organization.getParentOrganization() == null || StringUtils.isEmpty(organization.getParentOrganization().getId())) {
				organization.setParentOrganization(null);
			}
			String account = organization.getAccount();
			String password = organization.getPassword();
			String passwordConfirm = organization.getPasswordConfirm();
			password= Md5Util.MD5(password).toLowerCase();
			passwordConfirm= Md5Util.MD5(passwordConfirm).toLowerCase();
		    userAccount = new UserAccount();
		    userAccount.setAccount(account);
		    userAccount.setPassword(password);
		    userAccount.setPasswordConfirm(passwordConfirm);
		    userAccount = vixntBaseService.merge(userAccount);
		    organization.setCompSuperAdmin(userAccount);
		    
			organization.setChineseCharacter(py);
			initEntityBaseController.initEntityBaseAttribute(organization);
			organization = vixntBaseService.merge(organization);
			//
			createEncodingRulesTableInTheMiddle(py, organization.getTenantId());
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText("更新成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText("更新失败");

			}
		}
		return UPDATE;
	}

	public void findOrgAndUnitTreeToJson() {
		try {
			loadOrg(id, treeType);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void loadOrg(String nodeId, String nodeTreeType) {
		try {
			
			List<Organization> listOrganization = new ArrayList<Organization>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (null != id && !"".equals(id)) {
				listOrganization = organizationService.findAllSubEntity(Organization.class, "parentOrganization.id", id, params);
			} else {
				listOrganization = organizationService.findAllSubEntity(Organization.class, "parentOrganization.id", null, params);
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

	/** 处理删除操作 */
	public String deleteEntityById() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				OrganizationUnit organizationUnit = vixntBaseService.findEntityById(OrganizationUnit.class, id);
				if (organizationUnit != null) {
					vixntBaseService.deleteByEntity(organizationUnit);
					WxpQyWeixinSite w = vixntBaseService.findEntityByAttribute(WxpQyWeixinSite.class, "qiyeCorpId", api_qiye_corpid);
					if (w != null) {
						String request = WxQyUtil.departmentDelete(organizationUnit.getSyncId(), w.getQiyeAccessToken());
						if (StringUtils.isNotEmpty(request)) {
							JSONObject jsonObject = JSONObject.fromObject(request);
							if (jsonObject.has("errcode")) {
								String errcode = jsonObject.getString("errcode");
								if ("0".equals(errcode)) {
									System.out.println("企业号部门删除成功!");
								}
							}
						}
					}
				}
			}
			renderText(DELETE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getTreeType() {
		return treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	public OrganizationUnit getOrganizationUnit() {
		return organizationUnit;
	}

	public void setOrganizationUnit(OrganizationUnit organizationUnit) {
		this.organizationUnit = organizationUnit;
	}

	@Override
	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public Organization getParentOrganization() {
		return parentOrganization;
	}

	public void setParentOrganization(Organization parentOrganization) {
		this.parentOrganization = parentOrganization;
	}
    
}
