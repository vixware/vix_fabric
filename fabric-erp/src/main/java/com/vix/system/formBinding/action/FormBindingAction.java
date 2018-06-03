package com.vix.system.formBinding.action;

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
import com.vix.common.base.action.BaseAction;
import com.vix.common.org.service.IOrganizationUnitService;
import com.vix.common.security.entity.Role;
import com.vix.system.formBinding.controller.FormBindingController;
import com.vix.system.formBinding.entity.TemplateAndOrganizationUnit;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@Scope("prototype")
public class FormBindingAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private FormBindingController formBindingController;
	private String id;
	/**
	 * 表单模板ID
	 */
	private String businessFormId;
	/**
	 * roleID
	 */
	private String roleId;
	private String empId;
	private String pageNo;
	@Resource(name = "organizationUnitService")
	private IOrganizationUnitService organizationUnitService;

	private List<BusinessForm> businessFormList;

	@Override
	public String goList() {
		return GO_LIST;
	}

	public String goEmp() {
		return "goEmp";
	}

	public String goSingleList() {
		return GO_SINGLE_LIST;
	}

	public String goChooseBusinessFormTemplate() {
		return "goChooseBusinessFormTemplate";
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

	/**
	 * 获取待绑定表单
	 * 
	 * @return
	 */
	public String goBusinessFormTemplateList() {
		try {
			businessFormList = new ArrayList<BusinessForm>();
			RestTemplate restTemplate = new RestTemplate();
			Map<String, Object> urlVariables = new HashMap<String, Object>();

			String ip = getHostIp();
			System.out.println(ip);
			String message = restTemplate.postForObject("http://" + ip + ":8080/vform/form/businessform/findBusinessFormList", String.class, String.class, urlVariables);
			JSONArray ecOrderItemList = JSONArray.fromObject(message);
			for (int i = 0; i < ecOrderItemList.size(); i++) {
				BusinessForm businessForm = new BusinessForm();
				JSONObject o = ecOrderItemList.getJSONObject(i);
				businessForm.setId(o.getString("id"));
				businessForm.setBusinessFormCode(o.getString("businessFormCode"));
				businessForm.setBusinessFormName(o.getString("businessFormName"));
				businessFormList.add(businessForm);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goBusinessFormTemplateList";
	}

	public String binding() {
		try {
			if (businessFormId != null && roleId != null) {
				TemplateAndOrganizationUnit templateAndOrganizationUnit = new TemplateAndOrganizationUnit();
				templateAndOrganizationUnit.setBusinessFormId(businessFormId);
				templateAndOrganizationUnit.setRoleId(roleId);
				templateAndOrganizationUnit.setType("1");
				this.initEntityBaseController.initEntityBaseAttribute(templateAndOrganizationUnit);
				templateAndOrganizationUnit = formBindingController.doSaveTemplateAndOrganizationUnit(templateAndOrganizationUnit);
				this.setMessage("绑定成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.setMessage("绑定失败");
		}
		return UPDATE;
	}

	public String bindingEmp() {
		try {
			if (businessFormId != null && empId != null) {
				TemplateAndOrganizationUnit templateAndOrganizationUnit = new TemplateAndOrganizationUnit();
				templateAndOrganizationUnit.setBusinessFormId(businessFormId);
				templateAndOrganizationUnit.setEmpId(empId);
				templateAndOrganizationUnit.setType("2");
				this.initEntityBaseController.initEntityBaseAttribute(templateAndOrganizationUnit);
				templateAndOrganizationUnit = formBindingController.doSaveTemplateAndOrganizationUnit(templateAndOrganizationUnit);
				this.setMessage("绑定成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.setMessage("绑定失败");
		}
		return UPDATE;
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
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
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

}
