package com.vix.nvix.hr.action;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.org.entity.Organization;
import com.vix.common.org.entity.OrganizationUnit;
import com.vix.nvix.common.base.action.VixntBaseAction;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * @组织架构图
 * 
 * @author luchuan
 *
 */
@Controller
@Scope("prototype")
public class NvixOrganizeAction extends VixntBaseAction {
	private static final long serialVersionUID = 1L;

	private String id;
	private String name;

	public void findOrgJson() {
		try {
			List<Organization> orgList = null;
			// 加载公司信息
			Map<String, Object> params = getParams();
			orgList = vixntBaseService.findAllSubEntity(Organization.class, "parentOrganization.id", null, params);
			if (orgList != null) {
				for (Organization orgTmp : orgList) {
					JSONObject json = new JSONObject();
					json.put("name", orgTmp.getOrgName());
					if (orgTmp.getOrganizationUnits().size() > 0) {
						json.put("children", dealJson(orgTmp.getOrganizationUnits()));
					}
					renderJson(json.toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JSONArray dealJson(Set<OrganizationUnit> orgUnitList) {
		JSONArray ja = new JSONArray();
		for (OrganizationUnit orgUnit : orgUnitList) {
			if (orgUnit != null) {
				JSONObject json = new JSONObject();
				json.put("name", orgUnit.getFs());
				if (orgUnit.getSubOrganizationUnits() != null && orgUnit.getSubOrganizationUnits().size() > 0) {
					json.put("children", dealJson(orgUnit.getSubOrganizationUnits()));
				}
				ja.add(json);
			}
		}
		return ja;
	}
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
