package com.vix.system.systemLog.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.org.entity.Organization;
import com.vix.core.web.Pager;
import com.vix.system.latestOperate.entity.LatestOperateEntity;
import com.vix.system.systemLog.entity.SysLog;

/**
 * 系统日志
 * 
 * @author zhanghaibing
 * 
 * @date 2014-2-25
 */
@Controller
@Scope("prototype")
public class SystemLogManagementAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String id;
	private String organizationId;
	private SysLog sysLog;

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), LatestOperateEntity.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	//
	public String goLoginList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), SysLog.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goLoginList";
	}

	//
	public String goErrorList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), SysLog.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goErrorList";
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				sysLog = baseHibernateService.findEntityById(SysLog.class, id);
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
			if (null != sysLog.getId()) {
				isSave = false;
			}
			sysLog = baseHibernateService.merge(sysLog);
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
			SysLog et = baseHibernateService.findEntityById(SysLog.class, id);
			if (null != et) {
				baseHibernateService.deleteByEntity(et);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("system_brandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 获取任务日志数据 */
	public void getSysLogJson() {
		try {
			String json = "";
			List<SysLog> sysLogList = new ArrayList<SysLog>();
			if (organizationId != null && !"".equals(organizationId)) {
				sysLogList = baseHibernateService.findAllByEntityClassAndAttribute(SysLog.class, "objectId", organizationId);
			} else {
				sysLogList = baseHibernateService.findAllByEntityClass(SysLog.class);
			}
			json = convertListToJson(new ArrayList<SysLog>(sysLogList), sysLogList.size());
			if (!"".equals(json)) {
				renderJson(json);
			} else {
				renderJson("{\"total\":0,\"rows\":[]}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 树形结构JSON */
	public void findOrganizationJson() {
		try {
			List<Organization> listOrganization = new ArrayList<Organization>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (null != id && !"".equals(id)) {
				listOrganization = baseHibernateService.findAllSubEntity(Organization.class, "parentOrganization.id", id, params);
			} else {
				listOrganization = baseHibernateService.findAllSubEntity(Organization.class, "parentOrganization.id", null, params);
			}
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			int count = listOrganization.size();
			for (int i = 0; i < count; i++) {
				Organization org = listOrganization.get(i);
				if (org.getSubOrganizations().size() > 0) {
					strSb.append("{id:\"");
					strSb.append(org.getId());
					strSb.append("\",name:\"");
					strSb.append(org.getOrgName());
					strSb.append("\",open:false,isParent:true}");
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SysLog getSysLog() {
		return sysLog;
	}

	public void setSysLog(SysLog sysLog) {
		this.sysLog = sysLog;
	}

	/**
	 * @return the organizationId
	 */
	public String getOrganizationId() {
		return organizationId;
	}

	/**
	 * @param organizationId
	 *            the organizationId to set
	 */
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

}