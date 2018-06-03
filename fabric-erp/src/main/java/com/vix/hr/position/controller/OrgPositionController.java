package com.vix.hr.position.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.org.entity.OrgPosition;
import com.vix.hr.position.domain.OrgPositionDomain;

@Scope("prototype")
@Controller("orgPositionController")
public class OrgPositionController extends BaseAction {

	private static final long serialVersionUID = 1L;
	/** 键值从配置文件获取 */
	public static String industryIdentify = "GeneralIndustry";
	Logger logger = Logger.getLogger("OrgPositionController");
	@Autowired
	private OrgPositionDomain orgPositionDomain;

	public OrgPositionDomain getOrgPositionDomain() {
		return orgPositionDomain;
	}

	public void setOrgPositionDomain(OrgPositionDomain orgPositionDomain) {
		this.orgPositionDomain = orgPositionDomain;
	}

	public OrgPosition findEntityById(String id) throws Exception {
		return orgPositionDomain.findEntityById(id);
	}

}
