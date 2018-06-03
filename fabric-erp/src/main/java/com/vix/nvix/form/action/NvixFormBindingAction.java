package com.vix.nvix.form.action;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import com.e6soft.form.model.BusinessForm;
import com.vix.common.org.service.IOrganizationUnitService;
import com.vix.common.security.entity.Role;
import com.vix.core.web.Pager;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.oa.administrativeOfficeCenter.hql.AdministrativeHqlProvider;
import com.vix.system.formBinding.controller.FormBindingController;
import com.vix.system.formBinding.entity.TemplateAndOrganizationUnit;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@Scope("prototype")
public class NvixFormBindingAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private FormBindingController formBindingController;
	private String id;
	private String roleId;
	private String empId;
	/**
	 * 表单模板ID
	 */
	private String businessFormId;
	@Resource(name = "organizationUnitService")
	private IOrganizationUnitService organizationUnitService;
	@Resource(name = "administrativeHqlProvider")
	private AdministrativeHqlProvider administrativeHqlProvider;
	private List<BusinessForm> businessFormList;

	public String goEmp() {
		return "goEmp";
	}

	public String goSingleList() {
		return GO_SINGLE_LIST;
	}

	public String goChooseBusinessFormTemplate() {
		return "goChooseBusinessFormTemplate";
	}

	/**
	 * 获取待绑定表单
	 * 
	 * @return
	 */
	public void goBusinessFormTemplateList() {
		try {
			businessFormList = new ArrayList<BusinessForm>();
			RestTemplate restTemplate = new RestTemplate();
			Map<String, Object> urlVariables = new HashMap<String, Object>();

			// String ip = getHostIp();
			String message = restTemplate.postForObject(flow_ip + "/form/businessform/findBusinessFormList", String.class, String.class, urlVariables);
			JSONArray ecOrderItemList = JSONArray.fromObject(message);
			for (int i = 0; i < ecOrderItemList.size(); i++) {
				BusinessForm businessForm = new BusinessForm();
				JSONObject o = ecOrderItemList.getJSONObject(i);
				businessForm.setId(o.getString("id"));
				businessForm.setBusinessFormCode(o.getString("businessFormCode"));
				businessForm.setBusinessFormName(o.getString("businessFormName"));
				businessFormList.add(businessForm);
			}

			Pager pager = getPager();
			if (businessFormList != null && businessFormList.size() > 0) {
				pager.setPageSize(businessFormList.size());
				pager.setTotalCount(businessFormList.size());
				pager.setResultList(businessFormList);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void binding() {
		try {
			if (businessFormId != null && roleId != null) {
				TemplateAndOrganizationUnit templateAndOrganizationUnit = null;
				// 获取角色对应表单
				Map<String, Object> tmap = new HashMap<String, Object>();
				tmap.put("roleId", roleId);
				tmap.put("businessFormId", businessFormId);
				StringBuilder hql = administrativeHqlProvider.findTemplateAndOrganizationUnit(tmap);
				templateAndOrganizationUnit = formBindingController.findAllTemplateAndOrganizationUnit(hql.toString(), tmap);
				if (templateAndOrganizationUnit == null) {
					templateAndOrganizationUnit = new TemplateAndOrganizationUnit();
					templateAndOrganizationUnit.setBusinessFormId(businessFormId);
					templateAndOrganizationUnit.setRoleId(roleId);
					templateAndOrganizationUnit.setType("1");
					initEntityBaseController.initEntityBaseAttribute(templateAndOrganizationUnit);
					templateAndOrganizationUnit = formBindingController.doSaveTemplateAndOrganizationUnit(templateAndOrganizationUnit);
					renderText("绑定成功");
				} else {
					renderText("该角色跟该表单已经绑定");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText("绑定失败");
		}
	}

	public void bindingEmp() {
		try {
			if (businessFormId != null && empId != null) {
				TemplateAndOrganizationUnit templateAndOrganizationUnit = new TemplateAndOrganizationUnit();
				templateAndOrganizationUnit.setBusinessFormId(businessFormId);
				templateAndOrganizationUnit.setEmpId(empId);
				templateAndOrganizationUnit.setType("2");
				this.initEntityBaseController.initEntityBaseAttribute(templateAndOrganizationUnit);
				templateAndOrganizationUnit = formBindingController.doSaveTemplateAndOrganizationUnit(templateAndOrganizationUnit);
				renderText("绑定成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText("绑定失败");
		}
	}

	/**
	 * 
	 * @Title: findOrgAndUnitTreeToJson @Description: 加载公司和部门以及职位的混合树 @param
	 *         设定文件 @return void 返回类型 @throws
	 */
	public void findOrgAndUnitTreeToJson() {
		try {
			loadOrgPosition(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void loadOrgPosition(String nodeId) {
		try {
			List<Role> orgUnitList = organizationUnitService.findAllByEntityClass(Role.class);

			if (orgUnitList == null) {
				orgUnitList = new LinkedList<Role>();
			}

			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			int count = orgUnitList.size();
			for (int i = 0; i < count; i++) {
				Role org = orgUnitList.get(i);
				Set<Role> subList = org.getSubRoles();
				if (subList != null && subList.size() > 0) {
					strSb.append("{id:\"");
					strSb.append(org.getId());
					strSb.append("\",name:\"");
					strSb.append(org.getName());
					strSb.append("\",open:false,isParent:true}");
				} else {
					strSb.append("{id:\"");
					strSb.append(org.getId());
					strSb.append("\",name:\"");
					strSb.append(org.getName());
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
	 * @return the id
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	@Override
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the businessFormId
	 */
	public String getBusinessFormId() {
		return businessFormId;
	}

	/**
	 * @param businessFormId
	 *            the businessFormId to set
	 */
	public void setBusinessFormId(String businessFormId) {
		this.businessFormId = businessFormId;
	}

	/**
	 * @return the roleId
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * @return the empId
	 */
	public String getEmpId() {
		return empId;
	}

	/**
	 * @param empId
	 *            the empId to set
	 */
	public void setEmpId(String empId) {
		this.empId = empId;
	}

	/**
	 * @param roleId
	 *            the roleId to set
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the businessFormList
	 */
	public List<BusinessForm> getBusinessFormList() {
		return businessFormList;
	}

	/**
	 * @param businessFormList
	 *            the businessFormList to set
	 */
	public void setBusinessFormList(List<BusinessForm> businessFormList) {
		this.businessFormList = businessFormList;
	}
	public InetAddress getInetAddress() {
		try {
			return InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			System.out.println("unknown host!");
		}
		return null;
	}

	public String getHostIp() {
		InetAddress netAddress = getInetAddress();
		if (null == netAddress) {
			return null;
		}
		String ip = netAddress.getHostAddress(); // get the ip address
		return ip;
	}
}
