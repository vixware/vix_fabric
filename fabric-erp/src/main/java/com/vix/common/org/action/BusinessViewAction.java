package com.vix.common.org.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.org.entity.BusinessView;
import com.vix.common.org.entity.Organization;
import com.vix.common.org.service.IBusinessViewService;
import com.vix.common.security.service.IUserAccountService;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.BizConstant;
import com.vix.core.web.Pager;

@Controller
@Scope("request")
public class BusinessViewAction extends BaseAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private IBusinessViewService businessViewService;
	
	@Resource(name="userAccountService")
	private IUserAccountService userAccountService;
	
	private String id;
	
	private String businessViewName;
	
	private BusinessView entity;
	
	private BusinessView entityForm;
	
	/** 公司id */
	private String companyId;
	
	//是否承租户超级管理员
	private Boolean isTenantAdmin = false;
	
	@Override
	public String goList() {
		Boolean isSuperAdmin = SecurityUtil.isSuperAdmin();
		Boolean isAdmin = false;
		try {
			String userAccountId = SecurityUtil.getCurrentUserId();
			isAdmin = userAccountService.findIsSuperAdmin(userAccountId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(isAdmin && !isSuperAdmin){
			isTenantAdmin = true;
		}
		return super.goList();
	}
	
	/** 获取列表数据  */
	public String goSingleList(){
		try {
			Map<String,Object> params = new HashMap<String, Object>();
			//params.put("roleCode,"+SearchCondition.NOEQUAL, BizConstant.ROLE_SUPERADMIN);
			//roleName
			if(StringUtils.isNotEmpty(businessViewName)){
				params.put("name", "%"+businessViewName+"%");
			}
			if(StringUtils.isNotEmpty(companyId) && !companyId.equals("0")){//if(companyId!=null && companyId>0){
				params.put("companyId", companyId);
			}
			
			Pager pager = businessViewService.findBusinessViewPager(getPager(), params);
			setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
 
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){//if(null != id && id.longValue() > 0){
				entity = businessViewService.findEntityById(BusinessView.class, id);
			}else{
				
                if(null != companyId && !"".equals(companyId)){
                    Organization o = businessViewService.findEntityById(Organization.class, companyId);
                    if(null != o){
                        entity = new BusinessView();//new Organization();
                        entity.setOrganization(o);
                    }
                }
			}
			getRequest().setAttribute("bvType", BizConstant.COMMON_BIZORG_V_TYPE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作  */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if(null != entityForm.getId()){
				isSave = false;
			}
			
			businessViewService.saveOrUpdateBusinessView(entityForm);
			
			if(isSave){
				renderText(SAVE_SUCCESS);
			}else{
				renderText(SAVE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(isSave){
				renderText(SAVE_FAIL);
			}else{
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			businessViewService.deleteById(BusinessView.class, id);
			renderText(DELETE_SUCCESS);
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
				listOrganization = businessViewService.findAllSubEntity(Organization.class, "parentOrganization.id", id, params);
			} else {
				listOrganization = businessViewService.findAllSubEntity(Organization.class, "parentOrganization.id", null, params);
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
					strSb.append("\",open:false,isParent:false}");
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
	
	/**
	 * @Title: checkBizViewHasDefaultRelation
	 * @Description: 检查是否生成默认的上下级回报关系视图  只能是承租户超级管理员使用
	 * @param     设定文件
	 * @return void    返回类型
	 * @throws
	 */
	public void checkBizViewHasDefaultRelation(){
    	Boolean isSuccess = true;
    	String message = "没有默认上下级回报关系，请联系承租户管理员！";
    	try {
    		String tenantId = SecurityUtil.getCurrentUserTenantId();
    		if(StringUtils.isEmpty(tenantId)){
    			isSuccess = false;
    		}
    		BusinessView bv = businessViewService.findDefaultBizViewByTenantId(tenantId);
    		if(bv == null){
    			isSuccess = false;
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
        }
    	handleBaseMessageRenderText(isSuccess, message, null);
    }
	//创建承租户上下级关系
	public String createBizViewHasDefaultRelation() {
		try {
			businessViewService.saveOrUpdateBizViewHasDefaultRelation();
			setMessage(OPER_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			this.setMessage(OPER_FAIL);
		}
		return UPDATE;
	}
	
	public String getId() {
		return id;
	}

	public String getBusinessViewName() {
		return businessViewName;
	}

	public void setBusinessViewName(String businessViewName) {
		this.businessViewName = businessViewName;
	}

	public BusinessView getEntity() {
		return entity;
	}

	public void setEntity(BusinessView entity) {
		this.entity = entity;
	}

	public BusinessView getEntityForm() {
		return entityForm;
	}

	public void setEntityForm(BusinessView entityForm) {
		this.entityForm = entityForm;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public Boolean getIsTenantAdmin() {
		return isTenantAdmin;
	}

	public void setIsTenantAdmin(Boolean isTenantAdmin) {
		this.isTenantAdmin = isTenantAdmin;
	}
	
}
