package com.vix.common.org.action;

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
import com.vix.core.constant.BizConstant;
import com.vix.core.utils.DateUtil;
import com.vix.core.web.Pager;
import com.vix.system.base.compOperation.vo.CompanyOperationVO;

/**
 * @ClassName: OrganizationAction
 * @Description: 组织机构公司的Action
 * @author wangmingchen
 * @date 2013-5-11 下午1:50:07
 * 
 */
@Controller
@Scope("request")
public class OrganizationAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IOrganizationService organizationService;
	
	//@Autowired
	//private InitEntityBaseController initEntityBaseController;

	private String orgName;

	private String id;

	private String parentId;
	

	/** 组织机构公司 */
	private Organization organization;

	private Organization entityForm;

	private Organization entity;

	public void executeDrools() throws Exception {

	}

	public void executeEsper() throws Exception {

	}

	/**
	 * 加载顶端工具栏
	 * 
	 * @return
	 */

	public String goTopDynamicMenuContent() {
		return "goTopDynamicMenuContent";
	}

	/**
	 * 获取组织管理列表页
	 * @return 
	 */
	public String goSingleList() {
		try {
			
			Map<String, Object> params = new HashMap<String, Object>();
			if (StringUtils.isNotEmpty(orgName)) {
				orgName = decode(orgName, "UTF-8");
				params.put("orgName", "%"+orgName+"%");
			} else {
				if(StringUtils.isEmpty(id) || id.equals("0")){
					params.put("orgId", null);
				} else {
					params.put("orgId", id);
				}
			}
			/*
			 * if( StringUtils.isNotEmpty(orgName) ){
			 * params.put("orgName"+","+SearchCondition.ANYLIKE, orgName);
			 * }else{ if(null == id || id == 0){
			 * params.put("parentOrganization.id"+","+SearchCondition.IS,
			 * "NULL"); }else{
			 * params.put("parentOrganization.id"+","+SearchCondition.EQUAL,
			 * id); } }
			 */

			// Pager pager =
			// organizationService.findPagerByHqlConditions(getPager(),
			// Organization.class, params);
			Pager pager = organizationService.findOrganizationPager4NoComp(getPager(), params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){//if(null != id && id.longValue() > 0){
	                //organization = organizationService.findEntityById(Organization.class, id);
	            	Organization entityTmp = organizationService.findEntityById(Organization.class, id);
	    			
	            	entity = new CompanyOperationVO();
	            	BeanUtils.copyProperties(entityTmp, entity, new String[]{});
	            }else{
	                String parentId = getRequestParameter("parentId");
	                if(null != parentId && !"".equals(parentId)){
	                    Organization o = organizationService.findEntityById(Organization.class, parentId);
	                    if(null != o){
	                        entity = new CompanyOperationVO();//new Organization();
	                        entity.setParentOrganization(o);
	                    }
	                }
	                
	                entity.setStartTime(new Date());
	                entity.setEndTime(DateUtil.getEndOfWorld());
	            }
	            
	            getRequest().setAttribute("kqmk", BizConstant.COMMON_SECURITY_MOD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作 
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if(null != entityForm.getId()){
                isSave = false;
            }
            organizationService.saveCompanyOperation(entityForm);
            if(isSave){
                renderText(SAVE_SUCCESS);
            }else{
                renderText(SAVE_SUCCESS);
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
	 */
	/** 处理删除操作 */
	public String deleteById() {
		try {
			Organization org = organizationService.findEntityById(Organization.class, id);
			if (null != org) {
				if (org.getSubOrganizations().size() > 0) {
					setMessage("存在子公司不允许删除!");
				} else {
					organizationService.deleteByEntity(org);
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

	public String goChooseOrganization() {
		return "goChooseOrganization";
	}
	
	/**
	 * 跳转到查看页面
	 * 
	 * @return
	 */
	public String goShowOrganization() {

		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){//if (null != id && id.longValue() > 0) {
				entity = organizationService.findEntityById(Organization.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowOrganization";
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

	public Organization getEntityForm() {
		return entityForm;
	}

	public void setEntityForm(Organization entityForm) {
		this.entityForm = entityForm;
	}

	public Organization getEntity() {
		return entity;
	}

	public void setEntity(Organization entity) {
		this.entity = entity;
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

}
