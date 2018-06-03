package com.vix.common.org.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.org.controller.EmployeePostAndTitleController;
import com.vix.common.org.entity.OrgPosition;
import com.vix.common.org.entity.OrgView;
import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.org.service.IEmployeeHrService;
import com.vix.common.org.service.IOrganizationUnitService;
import com.vix.common.security.entity.UserAccount;
import com.vix.core.utils.JSonUtils;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.hr.position.entity.OrgPositionView;
import com.vix.hr.position.service.IOrgPositionService;

/**
 * @ClassName: EmployeeAction
 * @Description: 员工管理Action 注意员工的对象是HR中的
 * @author wangmingchen
 * @date 2013-5-11 下午1:55:09
 * 
 */
@Controller("employeeOrgAction")
@Scope("request")
public class EmployeeAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Logger logger = Logger.getLogger(Employee.class);
	/** 职称、职务管理 */
	@Resource(name = "employeepostandtitlecontroller")
	private EmployeePostAndTitleController employeePostAndTitleController;

	@Resource(name = "employeeHrService")
	private IEmployeeHrService employeeHrService;

	@Resource(name = "organizationUnitService")
	private IOrganizationUnitService organizationUnitService;

	@Resource(name = "orgPositionService")
	private IOrgPositionService orgPositionService;

	/** 职员姓名 */
	private String empName;

	private String id;

	private String parentId;

	private String pageNo;

	private Employee entity;

	private Employee entityForm;

	private String employeeId;

	private String boId;

	private String orgPositionId;

	private List<Employee> employeesList;

	private HttpSession request = getSession();

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
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			if (StringUtils.isNotEmpty(empName)) {
				empName = decode(empName, "UTF-8");
				params.put("epmName", "%" + empName + "%");
				pager = employeeHrService.findEmpAccountPager(pager, null, params);
			} else if (StringUtils.isNotEmpty(id)) {
				String orgViewIdStr = id.substring(0, id.length() - 1);
				String orgViewId = orgViewIdStr;

				char treeTypeChar = id.charAt(id.length() - 1);
				if (treeTypeChar == 'O') {
					pager = employeeHrService.findEmpAccountPager(pager, orgViewId, null);
				}
			}
			setPager(pager);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSearch() {
		return "goSearch";
	}

	/** 获取搜索列表数据 */

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		return GO_SAVE_OR_UPDATE;
	}

	/** 跳转基本信息 */
	public String goSaveOrUpdateEss() {
		if (id != null && !"".equals(id)) {
			request.setAttribute("employeeId", id);
		} else {
			id = String.valueOf(request.getAttribute("employeeId"));
		}
		try {
			// List<BusinessOrg> empBusinessOrgList =
			// employeeHrService.findEssOfBusinessOrgList(8L);

			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				entity = employeeHrService.findEntityById(Employee.class, id);
				entity.getOrganizationUnit().getFs();
			} else {
				entity = new Employee();
				if (StringUtils.isNotEmpty(parentId)) {
					String orgViewIdStr = parentId.substring(0, parentId.length() - 1);
					String parentOrgViewId = orgViewIdStr;
					// char orgtype = parentId.charAt( parentId.length()-1);
					OrganizationUnit unit = employeeHrService.findEntityById(OrganizationUnit.class, parentOrgViewId);
					entity.setOrganizationUnit(unit);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateEss";
	}

	public String goSaveOrUpdatePer() {
		if (id != null && !"".equals(id) && !"0".equals(id)) {
			request.setAttribute("employeeId", id);
		} else {
			id = String.valueOf(request.getAttribute("employeeId"));
		}
		try {
			String employeeId = String.valueOf(request.getAttribute("employeeId"));
			entity = employeeHrService.findEntityById(Employee.class, employeeId);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdatePer";
	}

	public String goSaveOrUpdateRel() {
		if (id != null && !"".equals(id) && !"0".equals(id)) {
			request.setAttribute("employeeId", id);
		} else {
			id = String.valueOf(request.getAttribute("employeeId"));
		}
		try {
			String employeeId = String.valueOf(request.getAttribute("employeeId"));
			entity = employeeHrService.findEntityById(Employee.class, employeeId);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateRel";
	}

	public String goSaveOrUpdateWork() {
		if (id != null && !"".equals(id) && !"0".equals(id)) {
			request.setAttribute("employeeId", id);
		} else {
			id = String.valueOf(request.getAttribute("employeeId"));
		}
		try {
			String employeeId = String.valueOf(request.getAttribute("employeeId"));
			entity = employeeHrService.findEntityById(Employee.class, employeeId);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateWork";
	}

	/** 处理修改操作 */
	public void saveOrUpdate() {
		boolean isSave = true;
		Map<String, Object> msgObj = new HashMap<String, Object>();
		try {
			// Long st = System.currentTimeMillis();
			if (StringUtils.isNotEmpty(entityForm.getId()) && !"0".equals(entityForm.getId())) {
				isSave = false;
			}

			String oId = getRequestParameter("oId");
			if (null != oId) {
				OrganizationUnit oTemp = employeeHrService.findEntityById(OrganizationUnit.class, oId);
				entityForm.setOrganizationUnit(oTemp);
			}
			initEntityBaseController.initEntityBaseAttribute(entityForm);
			entity = employeeHrService.saveOrUpdateEmp(entityForm);
			request.setAttribute("employeeId", entity.getId());

			// Long ed = System.currentTimeMillis();
			// System.out.println(ed-st);
			if (isSave) {
				msgObj.put("msg", SAVE_SUCCESS);
			} else {
				msgObj.put("msg", UPDATE_SUCCESS);
			}
			msgObj.put("objId", entity.getId());

			String resMsg = JSonUtils.toJSon(msgObj);
			renderText(resMsg);

		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				msgObj.put("msg", SAVE_FAIL);
			} else {
				msgObj.put("msg", SAVE_FAIL);
			}
		}
	}

	/** 快速新增 */
	public String goSaveOrUpdateFast() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				entity = employeeHrService.findEntityById(Employee.class, id);
				entity.getOrganizationUnit().getFs();
			} else {
				entity = new Employee();
				if (StringUtils.isNotEmpty(parentId)) {
					String orgViewIdStr = parentId.substring(0, parentId.length() - 1);
					String parentOrgViewId = orgViewIdStr;
					// char orgtype = parentId.charAt( parentId.length()-1);
					OrganizationUnit unit = employeeHrService.findEntityById(OrganizationUnit.class, parentOrgViewId);
					entity.setOrganizationUnit(unit);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateFast";
	}

	public String saveOrUpdateFast() {
		boolean isSave = true;
		try {
			if (null != entityForm.getId()) {
				isSave = false;
			}

			initEntityBaseController.initEntityBaseAttribute(entityForm);
			/**
			 * @TODO
			 */
			entity = employeeHrService.saveOrUpdateEmp(entityForm);
			request.setAttribute("employeeId", entity.getId());
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
			if (StringUtils.isNotEmpty(id)) {
				employeeHrService.deleteById(Employee.class, id);
			}

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
			List<OrgView> listOrganization = new ArrayList<OrgView>();
			listOrganization = organizationUnitService.findOrgViewList(id);

			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			int count = listOrganization.size();
			for (int i = 0; i < count; i++) {
				OrgView org = listOrganization.get(i);

				List<OrgView> subList = organizationUnitService.findOrgViewList(org.getId());
				if (subList != null && subList.size() > 0) {
					strSb.append("{id:\"");
					strSb.append(org.getId());
					strSb.append("\",name:\"");
					strSb.append(org.getOrgName());
					strSb.append("\",open:true,isParent:true}");
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
	 * 
	 * @Title: findOrgAndUnitTreeToJson @Description: 加载公司和部门以及职位的混合树 @param
	 *         设定文件 @return void 返回类型 @throws
	 */
	public void findOrgAndUnitTreeToJson() {
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

	public String goChooseOrganization() {
		return "goChooseOrganization";
	}

	/**
	 * 基本信息的 岗位信息
	 * 
	 * @return
	 */
	public String findEssOfPos() {
		try {
			List<OrgPosition> empOrgPositionList = null;
			if (StringUtils.isNotEmpty(employeeId) && !employeeId.equals("0")) {
				empOrgPositionList = orgPositionService.findOrgPositionListByEmpId(employeeId);
			} else {
				empOrgPositionList = new ArrayList<OrgPosition>();
			}
			renderHtml(convertListToJsonNoPage(empOrgPositionList, empOrgPositionList.size(), "employees", "organization", "organizationUnit", "parentOrgPosition", "subOrgPositions"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 添加业务组织的界面
	 * 
	 * @return
	 */
	public String toAddEssOfBusinessOrg() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {

			} else {

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "toAddEssOfContactType";
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
	 * 基本信息的 用户帐号
	 * 
	 * @return
	 */
	public String findEssOfUserAccount() {
		try {
			List<UserAccount> empUserAccountList = null;
			if (StringUtils.isNotEmpty(employeeId) && !employeeId.equals("0")) {
				empUserAccountList = employeeHrService.findEssOfUserAccountList(employeeId);
			} else {
				empUserAccountList = new ArrayList<UserAccount>();
			}
			renderHtml(convertListToJsonNoPage(empUserAccountList, empUserAccountList.size()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String goChoosehrPost() {
		logger.info("选择职务");
		return "goChoosehrPost";
	}

	public String goChoosehrTitle() {
		logger.info("选择职称");
		return "goChoosehrTitle";
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

	public Employee getEntity() {
		return entity;
	}

	public void setEntity(Employee entity) {
		this.entity = entity;
	}

	public Employee getEntityForm() {
		return entityForm;
	}

	public void setEntityForm(Employee entityForm) {
		this.entityForm = entityForm;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getBoId() {
		return boId;
	}

	public void setBoId(String boId) {
		this.boId = boId;
	}

	public String getOrgPositionId() {
		return orgPositionId;
	}

	public void setOrgPositionId(String orgPositionId) {
		this.orgPositionId = orgPositionId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public List<Employee> getEmployeesList() {
		return employeesList;
	}

	public void setEmployeesList(List<Employee> employeesList) {
		this.employeesList = employeesList;
	}
}
