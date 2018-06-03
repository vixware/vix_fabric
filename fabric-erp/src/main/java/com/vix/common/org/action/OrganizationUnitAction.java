package com.vix.common.org.action;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.org.entity.OrgView;
import com.vix.common.org.entity.Organization;
import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.org.service.IOrganizationService;
import com.vix.common.org.service.IOrganizationUnitService;
import com.vix.core.constant.BizConstant;
import com.vix.core.utils.DateUtil;
import com.vix.core.web.Pager;

/**
 * @ClassName: OrganizationUnitAction
 * @Description: 组织单元/运营单位
 * @author wangmingchen
 * @date 2013-5-14 下午8:42:48
 * 
 */
@Controller
@Scope("request")
public class OrganizationUnitAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IOrganizationService organizationService;

	@Autowired
	private IOrganizationUnitService organizationUnitService;

	
	//@Autowired
	//private InitEntityBaseController initEntityBaseController;

	private String fullName;

	private String id;

	private String parentId;

	/** C O */
	private String parentTreeType;

	private String parentTreeName;

	/** 组织机构公司 */
	private Organization organization;

	/** 部门 */
	private OrganizationUnit organizationUnit;

	private OrganizationUnit entity;

	private OrganizationUnit entityForm;
	
	private String treeId;

	private String treeType;

	/**
	 * 新建修改时 业务组织树的的json（包括check）
	 */
	private String entityBusinessOrgJsonStr;
	/**
	 * 新建修改时 业务组织的名称显示
	 */
	private String entityBusinessOrgName;
	/**
	 * 新建修改时 业务组织的id记录
	 */
	private String entityBusinessOrgId;

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

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();

			Pager pager = getPager();
			if (StringUtils.isNotEmpty(fullName)) {
				fullName = decode(fullName, "UTF-8");
				params.put("fullName",  "%"+fullName+"%");
				pager = organizationUnitService.findOrganizationUnitListByOrgId(getPager(), params);
			} else if (StringUtils.isNotEmpty(treeType) && 
					(StringUtils.isNotEmpty(id) && !id.equals("0") )) {//(null != id && id != 0l)) {
				if (treeType.equals("C")) {
					// params.put("organization.id"+","+SearchCondition.EQUAL,
					// id);
					params.put("orgId", id);
				} else {
					// treeType.equals("O");
					// params.put("parentOrganizationUnit.id"+","+SearchCondition.EQUAL,
					// id);
					params.put("orgUnitId", id);
				}
				pager = organizationUnitService.findOrganizationUnitListByOrgId(getPager(), params);
			}

			setPager(pager);
			/*
			 * if(StringUtils.isNotEmpty(treeType) && treeType.equals("O")){
			 * if(null == id || id == 0){
			 * params.put("parentOrganization.id"+","+SearchCondition.IS,
			 * "NULL"); }else{
			 * params.put("parentOrganization.id"+","+SearchCondition.EQUAL,
			 * id); } Pager pager =
			 * organizationService.findPagerByHqlConditions(getPager(),
			 * Organization.class, params); setPager(pager); }
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){//if (null != id && id.longValue() > 0) {
				entity = organizationUnitService.findOrganizationUnitAll(id);
				parentTreeType = entity.getParentType();
				parentId = entity.getParentTypeId();
				parentTreeName = entity.getParentTypeName();
			} else {
				entity = new OrganizationUnit();
				
				if (null != parentId && !"".equals(parentId)) {

					if (StringUtils.isNotEmpty(treeType)) {
						if (treeType.equals("C")) {
							Organization org = organizationUnitService.findEntityById(Organization.class, parentId);

							if (null != org) {
								parentTreeType = BizConstant.COMMON_ORG_C;
								parentId = org.getId();
								parentTreeName = org.getOrgName();
							}
						} else {
							OrganizationUnit orgUnit = organizationUnitService.findEntityById(OrganizationUnit.class, parentId);

							if (null != orgUnit) {
								parentTreeType = BizConstant.COMMON_ORG_O;
								parentId = orgUnit.getId();
								parentTreeName = orgUnit.getFullName();
							}
						}
					}
					
				}
				
				entity.setStartUsingDate(new Date());
                entity.setStopUsingDate(DateUtil.getEndOfWorld());
			}

			//String[] resArray = businessOrgService.findBusinessOrgAllForOrgUnit(id);
			//// entityBusinessOrgJsonStr = resArray[0];
			//entityBusinessOrgId = resArray[1];
			//entityBusinessOrgName = resArray[2];
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public String goChooseBusinessOrg() {
		return "goChooseBusinessOrg";
	}

	public void goSaveOrUpdateJson() {
		try {
			//String[] resArray = businessOrgService.findBusinessOrgAllForOrgUnit(id);
			//entityBusinessOrgJsonStr = resArray[0];
			
			// entityBusinessOrgId = resArray[1];
			// entityBusinessOrgName = resArray[2];

			renderHtml(entityBusinessOrgJsonStr);
			// renderHtml("[{id:1,pId:0,name:\"测试根节点1\",isParent:true,children:[{id:2,pId:1,name:\"BO1\",isParent:true,children:[{id:4,pId:2,name:\"bo11\",isParent:false},{id:5,pId:2,name:\"bo12\",isParent:false}]}]}]");
			// renderHtml("[{\"id\":1,\"pId\":0,\"name\":\"aaaaa\",\"isParent\":false}]");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(entityForm.getId())) {
				isSave = false;
			}

			if (StringUtils.isNotEmpty(parentTreeType)) {
				if (parentTreeType.equals("C")) {
					Organization parentOrg = organizationUnitService.findEntityById(Organization.class, parentId);
					entityForm.setOrganization(parentOrg);
					
					entityForm.setTenantId(parentOrg.getTenantId());
					entityForm.setCompanyInnerCode(parentOrg.getCompanyInnerCode());
				} else if (parentTreeType.equals("O")) {
					OrganizationUnit parentUnit = organizationUnitService.findEntityById(OrganizationUnit.class, parentId);
					entityForm.setParentOrganizationUnit(parentUnit);
					
					entityForm.setTenantId(parentUnit.getTenantId());
					entityForm.setCompanyInnerCode(parentUnit.getCompanyInnerCode());
				}

				// organizationUnitService.merge(entityForm);
				initEntityBaseController.initEntityBaseAttribute(entityForm);
				
				
				organizationUnitService.saveOrUpdateOrgUnit(entityForm, entityBusinessOrgId);
				/*
				 * parentTreeType = organizationUnit.getParentType(); parentId =
				 * organizationUnit.getParentTypeId(); parentTreeName =
				 * organizationUnit.getParentTypeName();
				 */
			}

			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
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

	/** 处理删除操作 */
	public String deleteById() {
		try {
			OrganizationUnit org = organizationUnitService.findOrganizationUnitAll(id);
			if (null != org) {
				if (org.getSubOrganizationUnits().size() > 0) {
					setMessage("存在子部门不允许删除!");
				} else {
					organizationService.deleteByEntity(org);
					renderText(DELETE_SUCCESS);
				}
			} else {
				setMessage("部门信息不存在!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 树形结构JSON 
	public void findTreeToJson() {
		try {
			List<Organization> listOrganization = new ArrayList<Organization>();
			Map<String, Object> params = getParams();
			if (null != id && !"".equals(id)) {
				listOrganization = organizationService.findAllSubEntity(Organization.class, "parentOrganization.id", id, params);
			} else {
				listOrganization = organizationService.findAllSubEntity(Organization.class, "parentOrganization.id", 0l, params);
			}
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			int count = listOrganization.size();
			for (int i = 0; i < count; i++) {
				Organization org = listOrganization.get(i);
				if (org.getSubOrganizations().size() > 0) {
					strSb.append("{id:");
					strSb.append(org.getId());
					strSb.append(",name:\"");
					strSb.append(org.getOrgName());
					strSb.append("\",open:true,isParent:true}");
				} else {
					strSb.append("{id:");
					strSb.append(org.getId());
					strSb.append(",name:\"");
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
	*/
	/**
	 * 
	 * @Title: findOrgAndUnitTreeToJson
	 * @Description: 加载公司和部门的混合树
	 * @param 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public void findOrgAndUnitTreeToJson() {
		try {
			// String treeType = getRequestParameter("treeType");
			//String treeId = getRequestParameter("treeId");
			loadOrg(treeId, treeType);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	 private void loadOrg(String nodeId,String nodeTreeType){
	        try{
	        	//oneStr.append(",\"checked\":true");
	        	
	        	//List<OrgView> orgUnitList = organizationUnitService.findOrgViewList(nodeId);
	        	List<OrgView> orgUnitList = organizationUnitService.findOrgViewList(nodeId);
	        	
	            if(orgUnitList==null){
	                orgUnitList = new LinkedList<OrgView>();
	            }
	            
	            StringBuilder strSb = new StringBuilder();
	            strSb.append("[");
	            /** 递归方式 **/
	            int count = orgUnitList.size();
	            for(int i =0; i<count; i++){
	            	OrgView org = orgUnitList.get(i);
	            	
	            	List<OrgView> subList = organizationUnitService.findOrgViewList(org.getId());
	            	String treeId = org.getId();
	                if( subList!=null && subList.size() > 0){
	                    strSb.append("{treeId:\"");
	                    strSb.append(treeId);
	                    strSb.append("\",realId:\"");
	                    strSb.append(org.getRealId());
	                    strSb.append("\",treeType:\"");
	                    strSb.append(org.getOrgType());
	                    strSb.append("\",name:\"");
	                    strSb.append(org.getOrgName());
	                    strSb.append("\",open:false,isParent:true");
	                    
	                }else{
	                    strSb.append("{treeId:\"");
	                    strSb.append(treeId);
	                    strSb.append("\",realId:\"");
	                    strSb.append(org.getRealId());
	                    strSb.append("\",treeType:\"");
	                    strSb.append(org.getOrgType());
	                    strSb.append("\",name:\"");
	                    strSb.append(org.getOrgName());
	                    strSb.append("\",open:false,isParent:false");
	                    
	                }
	                
	                strSb.append("}");//strSb.append("}");
	                
	                if(i < count -1){
	                    strSb.append(",");
	                }
	            }
	            strSb.append("]");
	            renderHtml(strSb.toString());
	        }catch(Exception e){
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
	public String goShowOrganizationUnit() {

		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){//if (null != id && id.longValue() > 0) {
				entity = organizationService.findEntityById(OrganizationUnit.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowOrganizationUnit";
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

	public OrganizationUnit getOrganizationUnit() {
		return organizationUnit;
	}

	public void setOrganizationUnit(OrganizationUnit organizationUnit) {
		this.organizationUnit = organizationUnit;
	}

	public OrganizationUnit getEntity() {
		return entity;
	}

	public void setEntity(OrganizationUnit entity) {
		this.entity = entity;
	}

	public OrganizationUnit getEntityForm() {
		return entityForm;
	}

	public void setEntityForm(OrganizationUnit entityForm) {
		this.entityForm = entityForm;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentTreeType() {
		return parentTreeType;
	}

	public void setParentTreeType(String parentTreeType) {
		this.parentTreeType = parentTreeType;
	}

	public String getParentTreeName() {
		return parentTreeName;
	}

	public void setParentTreeName(String parentTreeName) {
		this.parentTreeName = parentTreeName;
	}

	public String getTreeType() {
		return treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEntityBusinessOrgJsonStr() {
		return entityBusinessOrgJsonStr;
	}

	public void setEntityBusinessOrgJsonStr(String entityBusinessOrgJsonStr) {
		this.entityBusinessOrgJsonStr = entityBusinessOrgJsonStr;
	}

	public String getEntityBusinessOrgName() {
		return entityBusinessOrgName;
	}

	public void setEntityBusinessOrgName(String entityBusinessOrgName) {
		this.entityBusinessOrgName = entityBusinessOrgName;
	}

	public String getEntityBusinessOrgId() {
		return entityBusinessOrgId;
	}

	public void setEntityBusinessOrgId(String entityBusinessOrgId) {
		this.entityBusinessOrgId = entityBusinessOrgId;
	}

	public String getTreeId() {
		return treeId;
	}

	public void setTreeId(String treeId) {
		this.treeId = treeId;
	}

}
