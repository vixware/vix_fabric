package com.vix.oa.administrativeOfficeCenter.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import com.e6soft.form.model.BusinessForm;
import com.e6soft.form.model.BusinessFormTemplate;
import com.e6soft.form.model.BusinessFormType;
import com.vix.common.base.action.BaseAction;
import com.vix.common.security.entity.UserAccount;
import com.vix.common.security.util.SecurityUtil;
import com.vix.oa.administrativeOfficeCenter.hql.AdministrativeHqlProvider;
import com.vix.system.formBinding.controller.FormBindingController;
import com.vix.system.formBinding.entity.TemplateAndOrganizationUnit;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * @ClassName: AdministrativeAction
 * @Description: 电脑手机采购申请表单
 * @author chenzhengwen
 * @author w_a_533@163.com
 * @date 2014-5-16 下午4:15:24
 */
@Controller
@Scope("prototype")
public class AdministrativeAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String id;

	private String pageNo;
	private String businessFormTemplateId;
	private List<BusinessFormTemplate> businessFormTemplateList;
	private Map<String, List<BusinessFormTemplate>> businessFormTemplateMap;
	private List<BusinessFormType> businessFormTypeList;
	private List<BusinessForm> businessFormList;
	@Autowired
	private FormBindingController formBindingController;
	@Resource(name = "administrativeHqlProvider")
	private AdministrativeHqlProvider administrativeHqlProvider;

	/** 跳转到列表页面 */
	@Override
	public String goList() {
		businessFormTemplateMap = new HashMap<String, List<BusinessFormTemplate>>();
		// 获取表单模板
		businessFormTemplateList = new ArrayList<BusinessFormTemplate>();
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> urlVariables = new HashMap<String, Object>();
		String message = restTemplate.postForObject("http://192.168.0.101:8080/vform/form/businessbasetemplate/findAllBusinessFormTemplate", String.class, String.class, urlVariables);
		if (message != null && !"".equals(message)) {
			JSONArray ecOrderItemList = JSONArray.fromObject(message);

			for (int i = 0; i < ecOrderItemList.size(); i++) {
				BusinessFormTemplate businessFormTemplate = new BusinessFormTemplate();
				JSONObject o = ecOrderItemList.getJSONObject(i);
				businessFormTemplate.setId(o.getString("id"));
				businessFormTemplate.setBusinessFormId(o.getString("businessFormId"));
				businessFormTemplate.setTemplateName(o.getString("templateName"));
				businessFormTemplate.setTemplateCode(o.getString("templateCode"));
				businessFormTemplate.setTempatePath(o.getString("tempatePath"));
				businessFormTemplate.setHtmlElementPath(o.getString("htmlElementPath"));
				if (o.has("htmlCode")) {
					businessFormTemplate.setHtmlCode(o.getString("htmlCode"));
				}
				businessFormTemplateList.add(businessFormTemplate);
			}
		}

		// 获取表单类型
		businessFormTypeList = new ArrayList<BusinessFormType>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String businessFormTypeListString = restTemplate.postForObject("http://192.168.0.101:8080/vform/form/businessformtype/businessFormTypeList", String.class, String.class, paramMap);
		JSONArray businessFormTypeListArray = JSONArray.fromObject(businessFormTypeListString);
		for (int i = 0; i < businessFormTypeListArray.size(); i++) {
			BusinessFormType businessFormType = new BusinessFormType();
			JSONObject o = businessFormTypeListArray.getJSONObject(i);
			businessFormType.setId(o.getString("id"));
			businessFormType.setTypeName(o.getString("typeName"));
			businessFormTypeList.add(businessFormType);
		}
		// 获取表单
		businessFormList = new ArrayList<BusinessForm>();
		Map<String, Object> businessFormMap = new HashMap<String, Object>();
		String businessFormListString = restTemplate.postForObject("http://192.168.0.101:8080/vform/form/businessform/findBusinessFormList", String.class, String.class, businessFormMap);
		JSONArray businessFormListArray =JSONArray.fromObject(businessFormListString);
		for (int i = 0; i < businessFormListArray.size(); i++) {
			BusinessForm businessForm = new BusinessForm();
			JSONObject o = businessFormListArray.getJSONObject(i);
			businessForm.setId(o.getString("id"));
			businessForm.setTypeId(o.getString("typeId"));
			try {
				//
				Map<String, Object> tmap = new HashMap<String, Object>();
				if (SecurityUtil.getCurrentEmpId() != null) {
					UserAccount userAccount = SecurityUtil.getCurrentUserAccount();
					if (userAccount != null && userAccount.getRoles() != null) {
						tmap.put("roleId", userAccount.getId());
					}
				}
				tmap.put("businessFormId", o.getString("id"));
				StringBuilder hql = administrativeHqlProvider.findTemplateAndOrganizationUnit(tmap);
				TemplateAndOrganizationUnit templateAndOrganizationUnit = formBindingController.findAllTemplateAndOrganizationUnit(hql.toString(), tmap);
				if (templateAndOrganizationUnit != null) {
					businessFormList.add(businessForm);
				}
				//
				Map<String, Object> empmap = new HashMap<String, Object>();
				if (SecurityUtil.getCurrentEmpId() != null) {
					empmap.put("empId", SecurityUtil.getCurrentEmpId());
				}
				empmap.put("businessFormId", o.getString("id"));
				StringBuilder emphql = administrativeHqlProvider.findEmpTemplateAndOrganizationUnit(empmap);
				TemplateAndOrganizationUnit empTemplateAndOrganizationUnit = formBindingController.findAllTemplateAndOrganizationUnit(emphql.toString(), empmap);
				if (empTemplateAndOrganizationUnit != null) {
					businessFormList.add(businessForm);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		for (BusinessForm businessForm : businessFormList) {
			List<BusinessFormTemplate> bList = new ArrayList<BusinessFormTemplate>();
			for (BusinessFormTemplate businessFormTemplate : businessFormTemplateList) {
				if (businessForm.getId().equals(businessFormTemplate.getBusinessFormId())) {
					bList.add(businessFormTemplate);
				}
			}
			if (businessFormTemplateMap.containsKey(businessForm.getTypeId())) {
				bList.addAll(businessFormTemplateMap.get(businessForm.getTypeId()));
				businessFormTemplateMap.put(businessForm.getTypeId(), bList);
			} else {
				businessFormTemplateMap.put(businessForm.getTypeId(), bList);
			}
		}
		return GO_LIST;
	}

	/**
	 * 跳转到表单填写
	 * 
	 * @return
	 */
	public String goBusinessFormTemplate() {
		return "goBusinessFormTemplate";
	}

	/**
	 * 表单历史数据查看
	 * 
	 * @return
	 */
	public String goListBusinessFormData() {
		return "goListBusinessFormData";
	}

	public String getId() {
		return id;
	}

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
	 * @return the businessFormTemplateId
	 */
	public String getBusinessFormTemplateId() {
		return businessFormTemplateId;
	}

	/**
	 * @param businessFormTemplateId
	 *            the businessFormTemplateId to set
	 */
	public void setBusinessFormTemplateId(String businessFormTemplateId) {
		this.businessFormTemplateId = businessFormTemplateId;
	}

	/**
	 * @return the businessFormTemplateList
	 */
	public List<BusinessFormTemplate> getBusinessFormTemplateList() {
		return businessFormTemplateList;
	}

	/**
	 * @param businessFormTemplateList
	 *            the businessFormTemplateList to set
	 */
	public void setBusinessFormTemplateList(List<BusinessFormTemplate> businessFormTemplateList) {
		this.businessFormTemplateList = businessFormTemplateList;
	}

	/**
	 * @return the businessFormTypeList
	 */
	public List<BusinessFormType> getBusinessFormTypeList() {
		return businessFormTypeList;
	}

	/**
	 * @param businessFormTypeList
	 *            the businessFormTypeList to set
	 */
	public void setBusinessFormTypeList(List<BusinessFormType> businessFormTypeList) {
		this.businessFormTypeList = businessFormTypeList;
	}

	/**
	 * @return the businessFormTemplateMap
	 */
	public Map<String, List<BusinessFormTemplate>> getBusinessFormTemplateMap() {
		return businessFormTemplateMap;
	}

	/**
	 * @param businessFormTemplateMap
	 *            the businessFormTemplateMap to set
	 */
	public void setBusinessFormTemplateMap(Map<String, List<BusinessFormTemplate>> businessFormTemplateMap) {
		this.businessFormTemplateMap = businessFormTemplateMap;
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
