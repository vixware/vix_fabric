package com.vix.system.precisionControl.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.org.entity.Organization;
import com.vix.system.precisionControl.controller.PrecisionControlController;
import com.vix.system.precisionControl.entity.PrecisionControl;

@Controller
@Scope("prototype")
public class PrecisionControlAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private PrecisionControlController precisionControlController;

	private String id;
	/**
	 * 数据精度
	 */
	private PrecisionControl precisionControl;

	private String organizationId;

	/**
	 * 跳转到数据列表页面
	 * 
	 */
	@Override
	public String goList() {
		return GO_LIST;
	}

	/**
	 * 不执行页面跳转及刷新 只将数据显示到输入框中
	 * 
	 * @return
	 */
	public String goSaveOrUpdate() {
		try {
			if (null != organizationId && !"".equals(organizationId)) {
				precisionControl = precisionControlController.doListPrecisionControl(organizationId);
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
			if (null != precisionControl.getId()) {
				isSave = false;
			}
			Organization organization = precisionControlController.doListOrganizationById(organizationId);
			precisionControl.setOrganization(organization);
			precisionControlController.doSavePrecisionControl(precisionControl);
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

	/** 树形结构JSON */
	public void findTreeToJson() {
		try {
			List<Organization> listOrganization = new ArrayList<Organization>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (null != id && !"".equals(id)) {
				listOrganization = precisionControlController.doListAllOrganization("parentOrganization.id", id, params);
			} else {
				listOrganization = precisionControlController.doListAllOrganization("parentOrganization.id", null, params);
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

	public PrecisionControl getPrecisionControl() {
		return precisionControl;
	}

	public void setPrecisionControl(PrecisionControl precisionControl) {
		this.precisionControl = precisionControl;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

}
