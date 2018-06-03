package com.vix.system.base.compOperation.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.org.entity.Organization;
import com.vix.common.org.service.IOrganizationService;
import com.vix.common.security.entity.ConfigUrlAdd;
import com.vix.core.constant.BizConstant;
import com.vix.core.constant.BizOcConstant;
import com.vix.core.persistence.hibernate.util.HqlTenantIdUtil;
import com.vix.core.utils.DateUtil;
import com.vix.core.web.Pager;
import com.vix.system.base.compOperation.vo.CompanyOperationVO;

/**
 * @ClassName: CompanyOperationAction
 * @Description: 公司运营管理
 * @author wangmingchen
 * @date 2014-1-3 下午1:50:07
 *
 */
@Controller
@Scope("prototype")
public class CompanyOperationAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IOrganizationService organizationService;

	private String orgName;

	private String id;

	private String parentId;

	/** 组织机构公司 */
	private Organization organization;

	private CompanyOperationVO entityForm;

	private CompanyOperationVO entity;

	private String compId;

	private String bizType;

	private List<ConfigUrlAdd> urlList;

	public void executeDrools() throws Exception {

	}

	public void executeEsper() throws Exception {

	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {

			Map<String, Object> params = new HashMap<String, Object>();
			if (StringUtils.isNotEmpty(orgName)) {
				// System.out.println(orgName);
				orgName = decode(orgName, "UTF-8");
				// System.out.println(orgName);
				params.put("orgName", "%" + orgName + "%");
			} else {
				if (null == id || !id.equals("")) {
					params.put("orgId", null);
				} else {
					params.put("orgId", id);
				}
			}

			HqlTenantIdUtil.handleParamMap4TenantId(params);// 不增加tenantId
			HqlTenantIdUtil.handleParamMap4CompanyInnerCode(params);// 不处理CompanyInnerCode;

			Pager pager = organizationService.findOrganizationPager4Comp(getPager(), params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (null != id && !id.equals("") && !id.equals("0")) {
				// organization =
				// organizationService.findEntityById(Organization.class, id);
				Organization entityTmp = organizationService.findEntityById(Organization.class, id);

				entity = new CompanyOperationVO();
				BeanUtils.copyProperties(entityTmp, entity, new String[] {});
			} else {
				entity = new CompanyOperationVO();// new Organization();
				String parentId = getRequestParameter("parentId");
				if (null != parentId && !"".equals(parentId)) {
					Organization o = organizationService.findEntityById(Organization.class, parentId);
					if (null != o) {
						entity.setParentOrganization(o);
					}
				}
				entity.setStartTime(new Date());
				entity.setEndTime(DateUtil.getEndOfWorld());
			}

			getRequest().setAttribute("langList", BizConstant.COMMON_LANUAGUAGE_TYPE);
			getRequest().setAttribute("serviceTypeList", BizOcConstant.OC_SERVICE_TNT_TYPE);
			getRequest().setAttribute("matPropList", BizOcConstant.OC_TNT_MP);

			urlList = organizationService.findAllConfigUrlList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != entityForm.getId()) {
				isSave = false;
			}
			/*
			 * if(StrUtils.isNotEmpty(entityForm.getPassword())){ Md5Encoder md5
			 * = new Md5EncoderImpl();
			 * //entityForm.setPassword(md5.encodeString(entityForm.getPassword(
			 * )));//默认密码 }
			 */
			organizationService.saveCompanyOperation(entityForm);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				this.setMessage(SAVE_FAIL + " " + e.getMessage());
			} else {
				this.setMessage(UPDATE_FAIL + " " + e.getMessage());
			}
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			Organization org = organizationService.findEntityById(Organization.class, id);
			if (null != org) {
				if (org.getSubOrganizations().size() > 0) {
					setMessage("存在子公司不允许删除!");
				} else {
					// organizationService.deleteByEntity(org);
					organizationService.deleteCompanyOperation(id);
					renderText(DELETE_SUCCESS);
				}
			} else {
				setMessage("公司信息不存在!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 树形结构JSON */
	public void findTreeToJson() {
		try {
			List<Organization> listOrganization = new ArrayList<Organization>();
			/** 获取查询参数 */
			if (null != id && !"".equals(id)) {
				listOrganization = organizationService.findSubOrganizationListNoTenantId(id);
			} else {
				listOrganization = organizationService.findSubOrganizationListNoTenantId(null);
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

	public String goChooseOrganization() {
		return "goChooseOrganization";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public CompanyOperationVO getEntityForm() {
		return entityForm;
	}

	public void setEntityForm(CompanyOperationVO entityForm) {
		this.entityForm = entityForm;
	}

	public CompanyOperationVO getEntity() {
		return entity;
	}

	public void setEntity(CompanyOperationVO entity) {
		this.entity = entity;
	}

	public String getCompId() {
		return compId;
	}

	public void setCompId(String compId) {
		this.compId = compId;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public List<ConfigUrlAdd> getUrlList() {
		return urlList;
	}

	public void setUrlList(List<ConfigUrlAdd> urlList) {
		this.urlList = urlList;
	}

}
