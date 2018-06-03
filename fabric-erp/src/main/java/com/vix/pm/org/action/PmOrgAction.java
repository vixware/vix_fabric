package com.vix.pm.org.action;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.security.entity.Role;
import com.vix.core.constant.BizConstant;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.pm.org.entity.PmOrg;
import com.vix.pm.org.entity.PmOrgView;
import com.vix.pm.org.entity.PmView;
import com.vix.pm.org.service.IPmOrgService;
import com.vix.pm.org.service.IPmViewService;

/**
 * 业务组织的action
 * 
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class PmOrgAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IPmOrgService pmOrgService;

	@Autowired
	private IPmViewService pmViewService;

	//@Autowired
	//private IOrganizationUnitService organizationUnitService;

	/** 业务组织名称 */
	private String businessName;

	private String id;

	private String parentIdStr;

	private String parentId;

	private PmOrg entity;

	private PmOrg entityForm;

	private String treeId;

	private String treeType;

	//V O
	private String parentTreeType;

	private String parentTreeName;

	private String bizOrgType;
	private String bizOrgIds;
	private String bizOrgNames;

	public void executeDrools() throws Exception {

	}

	public void executeEsper() throws Exception {

	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			/*Map<String, Object> params = new HashMap<String, Object>();
			if (StringUtils.isNotEmpty(businessName)) {
				params.put("businessOrgName", "%"+businessName+"%");
			} else {
				 if(null == id || id == 0){
			         params.put("businessId", null);
			     }else{
			         params.put("businessId", id);
			     }
			}
			
			Pager pager = pmOrgService.findBusinessOrgPager(getPager(), params);
			setPager(pager);*/
			Map<String, Object> params = getParams();

			Pager pager = getPager();
			if (StringUtils.isNotEmpty(businessName)) {
				params.put("businessName", businessName);
				pager = pmOrgService.findBusinessOrgPager(getPager(), params);
			} else if (StringUtils.isNotEmpty(treeId) && StringUtils.isNotEmpty(treeType)) {
				String viewIdStr = treeId.substring(0, treeId.length() - 1);

				if (treeType.equals("V")) {
					//params.put("organization.id"+","+SearchCondition.EQUAL, id);
					params.put("bizOrgViewId", viewIdStr);
				} else {
					//treeType.equals("O");
					//params.put("parentOrganizationUnit.id"+","+SearchCondition.EQUAL, id);
					params.put("bizOrgId", viewIdStr);
				}
				pager = pmOrgService.findBusinessOrgPager(getPager(), params);
			}

			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (null != id) {
				entity = pmOrgService.findEntityById(PmOrg.class, id);

				PmOrg parentOrg = entity.getParentBusinessOrg();
				PmView parentView = entity.getPmView();
				if (parentOrg != null) {
					parentIdStr = parentOrg.getId() + "O";
					parentTreeName = parentOrg.getBusinessOrgName();
				} else if (parentView != null) {
					parentIdStr = parentView.getId() + "V";
					parentTreeName = parentView.getName();
				}

				String bizType = entity.getBizOrgType();
				if (bizType.equalsIgnoreCase(BizConstant.COMMON_ORG_O)) {
					OrganizationUnit unit = entity.getOrganizationUnit();
					if (unit != null) {
						bizOrgIds = unit.getId() + "O";
						bizOrgNames = unit.getFullName();
					}

				} else if (bizType.equalsIgnoreCase(BizConstant.COMMON_ORG_R)) {
					Role role = entity.getRole();
					if (role != null) {
						bizOrgIds = role.getId().toString();
						bizOrgNames = role.getName();
					}
				} else if (bizType.equalsIgnoreCase(BizConstant.COMMON_ORG_E)) {
					Employee emp = entity.getEmployee();
					if (emp != null) {
						bizOrgIds = emp.getId().toString();
						bizOrgNames = emp.getName();
					}
				}

			} else {
				if (StringUtils.isNotEmpty(parentIdStr)) {
					entity = new PmOrg();

					String parentIdStrTmp = parentIdStr.substring(0, parentIdStr.length() - 1);
					String parentIdLong = parentIdStrTmp;

					char treeTypeChar = parentIdStr.charAt(parentIdStr.length() - 1);
					if (treeTypeChar == 'V') {
						PmView orgView = pmOrgService.findEntityById(PmView.class, parentIdLong);
						entity.setPmView(orgView);

						parentTreeType = BizConstant.COMMON_BIZORG_V;
						//parentId = orgView.getId();
						parentTreeName = orgView.getName();
					} else if (treeTypeChar == 'O') {
						PmOrg o = pmOrgService.findEntityById(PmOrg.class, parentIdLong);
						entity.setParentBusinessOrg(o);

						parentTreeType = BizConstant.COMMON_BIZORG_O;
						//parentId = o.getId();
						parentTreeName = o.getBusinessOrgName();
					}
				}
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
			if (null != entityForm.getId()) {
				isSave = false;
			}

			if (StringUtils.isNotEmpty(parentIdStr)) {
				//PmOrg obj = pmOrgService.saveUpdateBusinessOrg(entityForm);
				String parentIdStrTmp = parentIdStr.substring(0, parentIdStr.length() - 1);

				char treeTypeChar = parentIdStr.charAt(parentIdStr.length() - 1);

				pmOrgService.saveUpdateBusinessOrg(bizOrgIds, bizOrgType, entityForm, treeTypeChar, parentIdStrTmp);
			}

			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
			/* PmOrg parentObj = obj.getParentBusinessOrg();
			 
			 StringBuilder objString = new StringBuilder();
			 objString.append("{");
			 
			 objString.append("\"obj\":");
			 obj.setParentBusinessOrg(null);
			 obj.setSubBusinessOrgs(null);
			 objString.append(JSonUtils.makeBeanToJson(obj));
			 
			 objString.append(",");
			 
			 objString.append("\"parentObj\":");
			 if(parentObj==null){
			 	objString.append("null");
			 }else{
			 	parentObj.setParentBusinessOrg(parentBusinessOrg);
			 	parentObj.setSubBusinessOrgs(null);
			 	objString.append(JSonUtils.makeBeanToJson(parentObj));
			 }
			 
			 objString.append("}");
			 
			 //renderHtml(objString.toString());
			 makeMsgObjHtml(true, message, null, objString.toString());
			 */
		} catch (Exception e) {
			e.printStackTrace();
			//makeMsgObjHtml(false, UPDATE_FAIL, null, null);
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

			PmOrg busOrg = pmOrgService.findEntityById(PmOrg.class, id);
			if (null != busOrg) {
				if (busOrg.getSubBusinessOrgs().size() > 0) {
					setMessage("存在子业务组织不允许删除!");
				} else {
					pmOrgService.deleteByEntity(busOrg);
					renderText(DELETE_SUCCESS);
				}
			} else {
				setMessage("业务组织信息不存在!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 树形结构JSON */
	/* public void findTreeToJson(){
	     try{
	         String str = findAllBoStr();
	         //renderJson(strSb.toString());
	         renderHtml(str);
	         //renderHtml("[{id:1,pId:0,name:\"测试根节点1\",isParent:true,children:[{id:2,pId:1,name:\"BO1\",isParent:true,children:[{id:4,pId:2,name:\"bo11\",isParent:false},{id:5,pId:2,name:\"bo12\",isParent:false}]}]}]");
	         //renderHtml("[{\"id\":1,\"pId\":0,\"name\":\"aaaaa\",\"isParent\":false}]");
	     }catch(Exception e){
	         e.printStackTrace();
	     }
	 }
	 
	 private String findAllBoStr(){
	 	StringBuilder strSb = new StringBuilder("");
	      *//** 获取查询参数 */
	/*
	//Map<String,Object> params = getParams();
	try {
	Set<PmOrg> listBusinessOrg = new HashSet<PmOrg>();
	listBusinessOrg = pmOrgService.findBusinessOrgAll();
	
	strSb.append("[");

	List<String> objStr = new LinkedList<String>();
	*//** 递归方式 **/
	/*
	for (PmOrg bo : listBusinessOrg) {
	StringBuilder oneStr = new StringBuilder();
	oneStr.append(makeBusinessOne(bo));
	objStr.add(oneStr.toString());
	}
	if (!objStr.isEmpty()) {
	strSb.append(StringUtils.join(objStr.iterator(), ","));
	}

	strSb.append("]");
	
	} catch (Exception e) {
	e.printStackTrace();
	}
	return strSb.toString();
	}
	
	
	private String makeBusinessOne(PmOrg bo){
	StringBuilder oneStr = new StringBuilder("");
	
	PmOrg parent = bo.getParentBusinessOrg();
	Set<PmOrg> subs =  bo.getSubBusinessOrgs();
	String pid = (parent==null?0L:parent.getId());
	
	oneStr.append("{\"id\":");
	oneStr.append(bo.getId());
	oneStr.append(",\"pId\":");
	oneStr.append(pid);
	oneStr.append(",\"name\":\"");
	oneStr.append(bo.getBusinessOrgName());
	if(subs!=null && !subs.isEmpty()){
	oneStr.append("\",\"isParent\":true");//open:true,
	}else{
	oneStr.append("\",\"isParent\":false");//,open:false
	}
	
	
	
	
	if(subs!=null && !subs.isEmpty()){
	oneStr.append(",\"children\":[");
	
	List<String> subString = new LinkedList<String>();
	for(PmOrg subBo:subs){
	String str = makeBusinessOne(subBo);
	subString.add(str);
	}
	oneStr.append(StringUtils.join(subString.iterator(), ","));
	oneStr.append("]");
	}
	
	oneStr.append("}");
	return oneStr.toString();
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
			//String str = findAllBoStr();
			// renderJson(strSb.toString());
			//renderHtml(str);
			//String treeType = getRequestParameter("treeType");
			loadBizOrg(treeId, treeType);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void loadBizOrg(String nodeId, String nodeTreeType) {
		try {
			List<PmOrgView> bizOrgViewList = pmViewService.findOrgViewList(nodeId);

			if (bizOrgViewList == null) {
				bizOrgViewList = new LinkedList<PmOrgView>();
			}

			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			int count = bizOrgViewList.size();
			for (int i = 0; i < count; i++) {
				PmOrgView orgView = bizOrgViewList.get(i);

				List<PmOrgView> subList = pmViewService.findOrgViewList(orgView.getId());
				if (subList != null && subList.size() > 0) {
					strSb.append("{treeId:\"");
					strSb.append(orgView.getId());
					strSb.append("\",treeType:\"");
					strSb.append(orgView.getViewType());
					strSb.append("\",name:\"");
					strSb.append(orgView.getName());
					strSb.append("\",open:false,isParent:true}");
				} else {
					strSb.append("{treeId:\"");
					strSb.append(orgView.getId());
					strSb.append("\",treeType:\"");
					strSb.append(orgView.getViewType());
					strSb.append("\",name:\"");
					strSb.append(orgView.getName());
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

	public String goChooseBusinessOrg() {
		return "goChooseBusinessOrg";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentIdStr() {
		return parentIdStr;
	}

	public void setParentIdStr(String parentIdStr) {
		this.parentIdStr = parentIdStr;
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

	public PmOrg getEntityForm() {
		return entityForm;
	}

	public void setEntityForm(PmOrg entityForm) {
		this.entityForm = entityForm;
	}

	public void setEntity(PmOrg entity) {
		this.entity = entity;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getTreeId() {
		return treeId;
	}

	public void setTreeId(String treeId) {
		this.treeId = treeId;
	}

	public IPmOrgService getPmOrgService() {
		return pmOrgService;
	}

	public void setPmOrgService(IPmOrgService pmOrgService) {
		this.pmOrgService = pmOrgService;
	}

	public IPmViewService getPmViewService() {
		return pmViewService;
	}

	public void setPmViewService(IPmViewService pmViewService) {
		this.pmViewService = pmViewService;
	}

	public String getTreeType() {
		return treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	public String getBizOrgIds() {
		return bizOrgIds;
	}

	public void setBizOrgIds(String bizOrgIds) {
		this.bizOrgIds = bizOrgIds;
	}

	public String getBizOrgType() {
		return bizOrgType;
	}

	public void setBizOrgType(String bizOrgType) {
		this.bizOrgType = bizOrgType;
	}

	public String getBizOrgNames() {
		return bizOrgNames;
	}

	public void setBizOrgNames(String bizOrgNames) {
		this.bizOrgNames = bizOrgNames;
	}

	public PmOrg getEntity() {
		return entity;
	}

}
