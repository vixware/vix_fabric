package com.vix.hr.position.action;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.common.org.entity.OrgJob;
import com.vix.common.org.entity.OrgPosition;
import com.vix.common.org.entity.OrgView;
import com.vix.common.org.entity.Organization;
import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.org.service.IOrgJobService;
import com.vix.common.org.service.IOrganizationUnitService;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.hr.position.controller.OrgPositionController;
import com.vix.hr.position.service.IOrgPositionService;

/**
 * 岗位管理
 * 
 * @author Administrator
 *
 */
@Controller
@Scope("request")
public class OrgPositionAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private String orgpositionId;

	private OrgPosition entity;

	private OrgPosition entityForm;

	private String treeId;

	private String treeName;

	private List<OrgJob> orgJobList;

	@Resource(name = "orgPositionController")
	private OrgPositionController orgPositionController;

	@Resource(name = "orgPositionService")
	private IOrgPositionService orgPositionService;

	@Resource(name = "orgJobService")
	private IOrgJobService orgJobService;

	@Resource(name = "initEntityBaseController")
	private InitEntityBaseController initEntityBaseController;

	@Resource(name = "organizationUnitService")
	private IOrganizationUnitService organizationUnitService;

	/** 附件 */
	private File fileToUpload;
	/** 附件的名称 */
	private String fileToUploadFileName;

	/** 部门id */
	private String orgUnitId;
	/** 部门名称 */
	private String orgUnitName;
	/** 公司id */
	private String orgId;

	private String posName;

	public String goSingleList() {
		try {
			Pager pager = getPager();
			if (StringUtils.isNotEmpty(treeId)) {
				String orgViewIdStr = treeId.substring(0, treeId.length() - 1);
				String orgViewId = orgViewIdStr;

				if (StringUtils.isNotEmpty(posName)) {
					posName = decode(posName, "UTF-8");
				} else {
					posName = null;
				}

				char treeTypeChar = treeId.charAt(treeId.length() - 1);
				if (treeTypeChar == 'O') {
					pager = orgPositionService.findOrgPositionPager(pager, orgViewId, posName);// (pager,
																								// orgViewId);
				}
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
			if (StrUtils.isNotEmpty(id)) {
				entity = orgPositionService.findOrgPositionById(id);
				Organization org = entity.getOrganization();
				orgId = org.getId();

				orgJobList = orgJobService.findOrgJobByOrgId(org.getId());

			} else {
				// entity = new OrgPosition();
				if (StringUtils.isNotEmpty(treeId)) {
					entity = new OrgPosition();

					String orgViewIdStr = treeId.substring(0, treeId.length() - 1);
					String orgViewId = orgViewIdStr;

					char treeTypeChar = treeId.charAt(treeId.length() - 1);
					if (treeTypeChar == 'O') {
						Organization org = organizationUnitService.getOrganizationByUnitId(orgViewId);
						orgId = org.getId();
						entity.setOrganization(org);

						orgJobList = orgJobService.findOrgJobByOrgId(org.getId());

						OrganizationUnit ou = orgPositionService.findEntityById(OrganizationUnit.class, orgViewId);
						entity.setOrganizationUnit(ou);

						entity.setCode(ou.getOrgCode());
					}
					// List<OrgJob> orgJobList =
					// orgJobService.findOrgJobByOrgId(orgId);
					// entity = orgPositionService.merge(entity);
				}
			}
			if (orgJobList == null) {
				orgJobList = new ArrayList<OrgJob>();
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
			// initEntityBaseController.initEntityBaseAttribute(entityForm);
			orgPositionService.saveOrUpdateOrgPosition(entityForm);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
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
			if (StrUtils.isNotEmpty(id)) {
				orgPositionService.deleteOrgPosition(id);
			}
			renderText(DELETE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public String goChoosePosition() {
		return "goChoosePosition";
	}

	public String goChoosePositionOrDep() {
		return "goChoosePositionOrDep";
	}

	/** 上级岗位的选择 */
	public void findOrgPositionByOrg() {
		try {
			List<OrgPosition> opList = new ArrayList<OrgPosition>();
			/** 获取查询参数 */
			// Map<String,Object> params = getParams();
			opList = orgPositionService.findOrgPositionListByOrgId(orgId, id);

			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			int count = opList.size();
			for (int i = 0; i < count; i++) {
				OrgPosition op = opList.get(i);

				List<OrgPosition> subList = orgPositionService.findOrgPositionListByOrgId(orgId, op.getId());
				if (subList != null && subList.size() > 0) {
					strSb.append("{id:\"");
					strSb.append(op.getId());
					strSb.append("\",name:\"");
					strSb.append(op.getPosName());
					strSb.append("\",open:true,isParent:true}");
				} else {
					strSb.append("{id:\"");
					strSb.append(op.getId());
					strSb.append("\",name:\"");
					strSb.append(op.getPosName());
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
	 * 
	 * @Title: findOrgAndUnitTreeToJson @Description: 加载公司和部门的混合树 @param
	 *         设定文件 @return void 返回类型 @throws
	 */
	public void findOrgAndUnitTreeToJson() {
		try {
			String treeType = getRequestParameter("treeType");
			loadOrg(treeId, treeType);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void loadOrg(String nodeId, String nodeTreeType) {
		try {
			List<OrgView> orgUnitList = organizationUnitService.findOrgViewList(nodeId);

			if (orgUnitList == null) {
				orgUnitList = new LinkedList<OrgView>();
			}

			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			int count = orgUnitList.size();
			for (int i = 0; i < count; i++) {
				OrgView org = orgUnitList.get(i);

				List<OrgView> subList = organizationUnitService.findOrgViewList(org.getId());
				if (subList != null && subList.size() > 0) {
					strSb.append("{treeId:\"");
					strSb.append(org.getId());
					strSb.append("\",treeType:\"");
					strSb.append(org.getOrgType());
					strSb.append("\",name:\"");
					strSb.append(org.getOrgName());
					strSb.append("\",open:false,isParent:true}");
				} else {
					strSb.append("{treeId:\"");
					strSb.append(org.getId());
					strSb.append("\",treeType:\"");
					strSb.append(org.getOrgType());
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

	public String addHrAttachments() {
		return "addHrAttachments";
	}

	/**
	 * 跳转到查看页面
	 * 
	 * @return
	 */
	public String goShowPosition() {

		try {
			if (StrUtils.isNotEmpty(id)) {
				entity = orgPositionService.findOrgPositionById(id);
				Organization org = entity.getOrganization();
				orgId = org.getId();

				orgJobList = orgJobService.findOrgJobByOrgId(org.getId());

			} else {
				// entity = new OrgPosition();
				if (StringUtils.isNotEmpty(treeId)) {
					entity = new OrgPosition();

					String orgViewIdStr = treeId.substring(0, treeId.length() - 1);
					String orgViewId = orgViewIdStr;

					char treeTypeChar = treeId.charAt(treeId.length() - 1);
					if (treeTypeChar == 'O') {
						Organization org = organizationUnitService.getOrganizationByUnitId(orgViewId);
						orgId = org.getId();
						entity.setOrganization(org);

						orgJobList = orgJobService.findOrgJobByOrgId(org.getId());

						OrganizationUnit ou = orgPositionService.findEntityById(OrganizationUnit.class, orgViewId);
						entity.setOrganizationUnit(ou);
					}
					// List<OrgJob> orgJobList =
					// orgJobService.findOrgJobByOrgId(orgId);
					entity = orgPositionService.merge(entity);
				}
			}
			if (orgJobList == null) {
				orgJobList = new ArrayList<OrgJob>();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowPosition";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public OrgPosition getEntity() {
		return entity;
	}

	public void setEntity(OrgPosition entity) {
		this.entity = entity;
	}

	public OrgPosition getEntityForm() {
		return entityForm;
	}

	public void setEntityForm(OrgPosition entityForm) {
		this.entityForm = entityForm;
	}

	public IOrgPositionService getOrgPositionService() {
		return orgPositionService;
	}

	public void setOrgPositionService(IOrgPositionService orgPositionService) {
		this.orgPositionService = orgPositionService;
	}

	public String getTreeId() {
		return treeId;
	}

	public void setTreeId(String treeId) {
		this.treeId = treeId;
	}

	public String getTreeName() {
		return treeName;
	}

	public void setTreeName(String treeName) {
		this.treeName = treeName;
	}

	public List<OrgJob> getOrgJobList() {
		return orgJobList;
	}

	public void setOrgJobList(List<OrgJob> orgJobList) {
		this.orgJobList = orgJobList;
	}

	public String getOrgUnitId() {
		return orgUnitId;
	}

	public void setOrgUnitId(String orgUnitId) {
		this.orgUnitId = orgUnitId;
	}

	public String getOrgUnitName() {
		return orgUnitName;
	}

	public void setOrgUnitName(String orgUnitName) {
		this.orgUnitName = orgUnitName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Override
	public File getFileToUpload() {
		return fileToUpload;
	}

	@Override
	public void setFileToUpload(File fileToUpload) {
		this.fileToUpload = fileToUpload;
	}

	@Override
	public String getFileToUploadFileName() {
		return fileToUploadFileName;
	}

	@Override
	public void setFileToUploadFileName(String fileToUploadFileName) {
		this.fileToUploadFileName = fileToUploadFileName;
	}

	public String getOrgpositionId() {
		return orgpositionId;
	}

	public void setOrgpositionId(String orgpositionId) {
		this.orgpositionId = orgpositionId;
	}

	public String getPosName() {
		return posName;
	}

	public void setPosName(String posName) {
		this.posName = posName;
	}

}