package com.vix.system.processMonitoring.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import com.e6soft.form.model.BusinessFormTemplate;
import com.vix.common.base.action.BaseAction;
import com.vix.common.org.entity.OrgView;
import com.vix.common.org.service.IOrganizationUnitService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@Scope("prototype")
public class ProcessMonitoringAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String id;
	private String pageNo;
	@Resource(name = "organizationUnitService")
	private IOrganizationUnitService organizationUnitService;
	private BusinessFormTemplate businessFormTemplate;

	private List<BusinessFormTemplate> businessFormTemplateList;

	public String goSingleList() {
		try {
			businessFormTemplateList = new ArrayList<BusinessFormTemplate>();
			RestTemplate restTemplate = new RestTemplate();
			Map<String, Object> urlVariables = new HashMap<String, Object>();
			String message = restTemplate.postForObject("http://localhost:8080/vform/form/businessbasetemplate/viewtemplateanddata?id=31d838fef82445fba7da126d860f2e06&formData=1111", String.class, String.class, urlVariables);
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
				businessFormTemplate.setHtmlCode(o.getString("htmlCode"));
				businessFormTemplateList.add(businessFormTemplate);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/**
	 * 
	 * @Title: findOrgAndUnitTreeToJson @Description: 加载公司和部门以及职位的混合树 @param
	 *         设定文件 @return void 返回类型 @throws
	 */
	public void findOrgAndUnitTreeToJson() {
		try {
			String treeType = getRequestParameter("treeType");
			loadOrgPosition(id, treeType);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void loadOrgPosition(String nodeId, String nodeTreeType) {
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

	public BusinessFormTemplate getBusinessFormTemplate() {
		return businessFormTemplate;
	}

	public void setBusinessFormTemplate(BusinessFormTemplate businessFormTemplate) {
		this.businessFormTemplate = businessFormTemplate;
	}

	public List<BusinessFormTemplate> getBusinessFormTemplateList() {
		return businessFormTemplateList;
	}

	public void setBusinessFormTemplateList(List<BusinessFormTemplate> businessFormTemplateList) {
		this.businessFormTemplateList = businessFormTemplateList;
	}

}
