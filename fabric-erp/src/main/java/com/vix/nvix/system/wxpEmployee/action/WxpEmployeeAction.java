package com.vix.nvix.system.wxpEmployee.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.org.entity.OrgPosition;
import com.vix.common.org.entity.Organization;
import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.org.service.IOrganizationUnitService;
import com.vix.common.org.vo.OrgUnit;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.hr.position.entity.OrgPositionView;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.wechat.base.entity.WxpQyPicture;
import com.vix.wechat.base.entity.WxpQyWeixinSite;
import com.vix.wechat.base.util.WxQyUtil;

import net.sf.json.JSONObject;

@Controller
@Scope("prototype")
public class WxpEmployeeAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	private String id;
	private String treeType;
	private Employee emp;
	private String fileId;
	private String parentId;
	private String organizationUnitId;
	private String employeeId;
	private String orgPositionId;
	private Long deptId;
	@Resource(name = "organizationUnitService")
	private IOrganizationUnitService organizationUnitService;

	/** 获取列表数据 */
	public void goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			if (StringUtils.isNotEmpty(treeType) && StringUtils.isNotEmpty(parentId)) {
				if ("O".equals(treeType)) {
					params.put("organizationUnit.id," + SearchCondition.ANYLIKE, parentId);
				}
			}
			String searchCode = getDecodeRequestParameter("searchCode");
			if (StringUtils.isNotEmpty(searchCode)) {
				params.put("name," + SearchCondition.ANYLIKE, searchCode);
			}
			params.put("empType," + SearchCondition.EQUAL, "S");
			pager = vixntBaseService.findPagerByHqlConditions(pager, Employee.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				emp = vixntBaseService.findEntityById(Employee.class, id);
				deptId = emp.getOrganizationUnit().getSyncId();
			} else {
				emp = new Employee();
				emp.setGender(1);
				if (StringUtils.isNotEmpty(treeType) && StringUtils.isNotEmpty(parentId)) {
					if ("O".equals(treeType)) {
						OrganizationUnit organizationUnit = vixntBaseService.findEntityById(OrganizationUnit.class, parentId);
						if (organizationUnit != null) {
							emp.setStaffJobNumber(autoCreateCode.getBillNO(organizationUnit.getChineseCharacter()));
							deptId = organizationUnit.getSyncId();
							emp.setOrganizationUnit(organizationUnit);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if (emp != null && StringUtils.isNotEmpty(emp.getId())) {
				isSave = false;
			}

			emp.setEmpType("S");
			initEntityBaseController.initEntityBaseAttribute(emp);
			emp = vixntBaseService.merge(emp);

			if (emp.getOrganizationUnit() != null) {
				deptId = emp.getOrganizationUnit().getSyncId();
			}
			contatCreate(emp, deptId);
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
	}

	/**
	 * 处理微信企业号成员同步
	 * 
	 * @throws Exception
	 */
	private void contatCreate(Employee employee, Long deptId) throws Exception {
		WxpQyWeixinSite w = vixntBaseService.findEntityByAttribute(WxpQyWeixinSite.class, "qiyeCorpId", api_qiye_corpid);
		if (w != null && employee != null && deptId != null) {
			List<Long> deptIdList = new ArrayList<Long>();
			deptIdList.add(deptId);
			saveOrUpdateWxpWeixinSite(w);
			// 调用微信企业号接口
			String request = WxQyUtil.contatCreate(employee, deptIdList, w.getQiyeAccessToken());
			if (StringUtils.isNotEmpty(request)) {
				JSONObject jsonObject = JSONObject.fromObject(request);
				if (jsonObject.has("errcode")) {
					String errcode = jsonObject.getString("errcode");
					if ("0".equals(errcode)) {
						System.out.println("保存成功!");
					}
				}
			}
		}
	}

	private void contactUpdate(Employee employee, Long deptId) throws Exception {
		WxpQyWeixinSite w = vixntBaseService.findEntityByAttribute(WxpQyWeixinSite.class, "qiyeCorpId", api_qiye_corpid);
		if (w != null && employee != null && deptId != null) {
			List<Long> deptIdList = new ArrayList<Long>();
			deptIdList.add(deptId);
			saveOrUpdateWxpWeixinSite(w);
			// 调用微信企业号接口
			String request = WxQyUtil.contactUpdate(employee, deptIdList, w.getQiyeAccessToken());
			if (StringUtils.isNotEmpty(request)) {
				JSONObject jsonObject = JSONObject.fromObject(request);
				if (jsonObject.has("errcode")) {
					String errcode = jsonObject.getString("errcode");
					if ("0".equals(errcode)) {
						System.out.println("保存成功!");
					}
				}
			}
		}
	}

	// 保存岗位
	public void saveOrUpdateOrgPosition() {
		try {
			if (StringUtils.isNotEmpty(employeeId)) {
				emp = vixntBaseService.findEntityById(Employee.class, employeeId);
				if (emp != null) {
					Set<OrgPosition> orgPositions = new HashSet<OrgPosition>();
					if (StringUtils.isNotEmpty(orgPositionId)) {
						orgPositionId = orgPositionId.substring(0, orgPositionId.length() - 1);
						OrgPosition orgPosition = vixntBaseService.findEntityById(OrgPosition.class, orgPositionId);
						if (orgPosition != null) {
							orgPositions.add(orgPosition);
						}
					}
					emp.setOrgPositions(orgPositions);
					emp = vixntBaseService.merge(emp);
				}
				renderText("分配成功!");
			} else {
				renderText("分配失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText("分配失败!");
		}
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
			List<OrgUnit> orgUnitList = null;
			List<Organization> orgList = null;
			if (null != nodeId && !"".equals(nodeId) && !"undefined".equals(nodeId)) {
				if (StringUtils.isNotEmpty(nodeTreeType)) {
					// 没有根结点 需要加载公司信息 其实id不为空 则treetype肯定也不为空
					if (nodeTreeType.equals("C") || nodeTreeType.equals("O") || nodeTreeType.equals("P")) {
						// 加载公司信息和部门信息
						orgUnitList = vixntBaseService.findOrgAndUnitTreeList(nodeTreeType, nodeId, "O");
					}
				}
			} else {
				// id为空 则类型也为空,加载公司信息
				Map<String, Object> params = getParams();
				orgList = vixntBaseService.findAllSubEntity(Organization.class, "parentOrganization.id", null, params);
			}

			if (orgUnitList == null) {
				orgUnitList = new LinkedList<OrgUnit>();
			}

			if (orgList != null) {
				for (Organization orgTmp : orgList) {
					OrgUnit ou1 = new OrgUnit(orgTmp.getId(), "C", orgTmp.getOrgName());
					if (orgTmp.getSubOrganizations().size() > 0) {
						List<OrgUnit> ou2List = new LinkedList<OrgUnit>();
						for (Organization childOrg : orgTmp.getSubOrganizations()) {
							ou2List.add(new OrgUnit(childOrg.getId(), "C", childOrg.getOrgName()));
						}
						ou1.setSubOrgUnits(ou2List);
					}
					if (orgTmp.getOrganizationUnits().size() > 0) {
						List<OrgUnit> ou2List = new LinkedList<OrgUnit>();
						for (OrganizationUnit organizationUnit : orgTmp.getOrganizationUnits()) {
							ou2List.add(new OrgUnit(organizationUnit.getId(), "O", organizationUnit.getFs()));
						}
						ou1.setSubOrgUnits(ou2List);
					}
					orgUnitList.add(ou1);
				}
			}

			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			int count = orgUnitList.size();
			for (int i = 0; i < count; i++) {
				OrgUnit org = orgUnitList.get(i);
				if (org.getSubOrgUnits() != null && org.getSubOrgUnits().size() > 0) {
					strSb.append("{id:\"");
					strSb.append(org.getId());
					strSb.append("\",treeType:\"");
					strSb.append(org.getTreeType());
					strSb.append("\",name:\"");
					strSb.append(org.getOrgName());
					strSb.append("\",open:false,isParent:true}");
				} else {
					strSb.append("{id:\"");
					strSb.append(org.getId());
					strSb.append("\",treeType:\"");
					strSb.append(org.getTreeType());
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
	public void deleteEntityById() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				Employee employee = vixntBaseService.findEntityById(Employee.class, id);
				if (employee != null) {
					WxpQyWeixinSite w = vixntBaseService.findEntityByAttribute(WxpQyWeixinSite.class, "qiyeCorpId", "wx1a67071803f6077f");
					if (w != null) {
						saveOrUpdateWxpWeixinSite(w);
						String request = WxQyUtil.contactDelete(employee.getUserId(), w.getQiyeAccessToken());
						if (StringUtils.isNotEmpty(request)) {
							JSONObject jsonObject = JSONObject.fromObject(request);
							if (jsonObject.has("errcode")) {
								String errcode = jsonObject.getString("errcode");
								if ("0".equals(errcode)) {
									vixntBaseService.deleteByEntity(employee);
									renderText(DELETE_SUCCESS);
								} else {
									vixntBaseService.deleteByEntity(employee);
									setMessage(jsonObject.getString("errmsg"));
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	/**
	 * 保存附件
	 */
	public void saveOrUpdateWxpQyPicture() {
		try {
			// 保存图片
			String[] savePathAndName = saveUploadPic();
			if (savePathAndName != null && savePathAndName.length == 2) {
				WxpQyPicture wxpQyPicture = new WxpQyPicture();
				wxpQyPicture.setPictureUrl("/img/wechat/" + savePathAndName[1].toString());
				wxpQyPicture.setCreateTime(new Date());
				wxpQyPicture = vixntBaseService.mergeOriginal(wxpQyPicture);
				if (wxpQyPicture != null) {
					renderText(wxpQyPicture.getId() + "," + "/img/wechat/" + savePathAndName[1].toString());
				} else {
					renderText("0,保存失败!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String[] saveUploadPic() {
		String[] pathAndName = null;
		try {
			if (null != fileToUpload) {
				/** 上传目录 */
				String saveFolder = this.getUploadFileSavePic();
				@SuppressWarnings("resource")
				BufferedInputStream bufIn = new BufferedInputStream(new FileInputStream(fileToUpload));
				String[] fileNames = fileToUploadFileName.split("\\.");
				String extFileName = fileNames[fileNames.length - 1];
				String fileName = fileToUploadFileName.substring(0, fileToUploadFileName.length() - extFileName.length() - 1);

				long newFileMark = System.currentTimeMillis() / 1000;
				String saveFileName = fileName + "_" + newFileMark + "." + extFileName;

				String saveFilePath = saveFolder + "/" + saveFileName;

				BufferedOutputStream bufOut = new BufferedOutputStream(new FileOutputStream(saveFilePath));
				byte[] buf = new byte[1024 * 100];
				int len = -1;
				while ((len = bufIn.read(buf)) != -1) {
					bufOut.write(buf, 0, len);
				}
				bufOut.close();

				pathAndName = new String[2];
				pathAndName[0] = saveFolder;
				pathAndName[1] = saveFileName;
			} else {
				return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return pathAndName;
	}

	/**
	 * 选择岗位
	 * 
	 * @return
	 */
	public String goChooseOrgPosition() {
		return "goChooseOrgPosition";
	}

	/**
	 * 
	 * @Title: findOrgAndUnitTreeToJson @Description: 加载公司和部门以及职位的混合树 @param
	 *         设定文件 @return void 返回类型 @throws
	 */
	public void findOrgPositionViewTreeToJson() {
		try {
			String treeType = getRequestParameter("treeType");
			loadOrgPositionView(id, treeType);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadOrgPositionView(String nodeId, String nodeTreeType) {
		try {
			List<OrgPositionView> orgUnitList = organizationUnitService.findOrgPositionViewList(nodeId);

			if (orgUnitList == null) {
				orgUnitList = new LinkedList<OrgPositionView>();
			}

			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			int count = orgUnitList.size();
			for (int i = 0; i < count; i++) {
				OrgPositionView org = orgUnitList.get(i);

				List<OrgPositionView> subList = organizationUnitService.findOrgPositionViewList(org.getId());
				if (subList != null && subList.size() > 0) {
					strSb.append("{id:\"");
					strSb.append(org.getId());
					strSb.append("\",treeType:\"");
					strSb.append(org.getOrgType());
					strSb.append("\",name:\"");
					strSb.append(org.getOrgName());
					strSb.append("\",open:false,isParent:true}");
				} else {
					strSb.append("{id:\"");
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

	/**
	 * 选择部门
	 * 
	 * @return
	 */
	public String goChooseOrganizationUnit() {
		return "goChooseOrganizationUnit";
	}

	/**
	 * 部门列表
	 */
	public void goOrganizationUnitList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String employeeName = getDecodeRequestParameter("employeeName");
			if (StringUtils.isNotEmpty(employeeName)) {
				params.put("name," + SearchCondition.ANYLIKE, employeeName);
			}
			Pager pager = vixntBaseService.findPagerByHqlConditions(getPager(), OrganizationUnit.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 调换部门
	public void changeOrganizationUnit() {
		try {
			if (StringUtils.isNotEmpty(id) && StringUtils.isNotEmpty(organizationUnitId)) {
				Employee employee = vixntBaseService.findEntityById(Employee.class, id);
				OrganizationUnit organizationUnit = vixntBaseService.findEntityById(OrganizationUnit.class, organizationUnitId);
				if (employee != null && organizationUnit != null) {
					employee.setOrganizationUnit(organizationUnit);
					employee = vixntBaseService.merge(employee);
					if (employee.getOrganizationUnit() != null) {
						deptId = employee.getOrganizationUnit().getSyncId();
					}
					contactUpdate(employee, deptId);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the parentId
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * @param parentId
	 *            the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the treeType
	 */
	public String getTreeType() {
		return treeType;
	}

	/**
	 * @param treeType
	 *            the treeType to set
	 */
	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	/**
	 * @return the deptId
	 */
	public Long getDeptId() {
		return deptId;
	}

	/**
	 * @param deptId
	 *            the deptId to set
	 */
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getOrganizationUnitId() {
		return organizationUnitId;
	}

	public void setOrganizationUnitId(String organizationUnitId) {
		this.organizationUnitId = organizationUnitId;
	}

	/**
	 * @return the fileId
	 */
	public String getFileId() {
		return fileId;
	}

	/**
	 * @param fileId
	 *            the fileId to set
	 */
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getOrgPositionId() {
		return orgPositionId;
	}

	public void setOrgPositionId(String orgPositionId) {
		this.orgPositionId = orgPositionId;
	}

	/**
	 * @return the emp
	 */
	public Employee getEmp() {
		return emp;
	}

	/**
	 * @param emp
	 *            the emp to set
	 */
	public void setEmp(Employee emp) {
		this.emp = emp;
	}

}
