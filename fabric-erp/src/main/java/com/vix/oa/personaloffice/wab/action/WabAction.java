
package com.vix.oa.personaloffice.wab.action;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.common.org.entity.Organization;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.oa.personaloffice.wab.controller.WabController;
import com.vix.oa.personaloffice.wab.entity.Wab;

/**
 * 
 * @ClassName: WabAction
 * @Description: 通讯簿
 * @author chenzhengwen
 * @author w_a_533@163.com
 * @date 2014-5-22 下午5:11:13
 */
@Controller
@Scope("prototype")
public class WabAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(WabController.class);
	@Autowired
	private WabController wabController;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	private Wab wab;
	private String id;
	private String parentId;
	private String pageNo;
	private Date updateTime;

	/** 跳转到列表页面 */
	@Override
	public String goList() {

		return GO_LIST;
	}

	/** 获取个人通讯簿列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			String code = getRequestParameter("code");
			if (null != code && !"".equals(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
			}
			if (StringUtils.isNotEmpty(parentId) && !parentId.equals("0")) {
				params.put("parentCategory.id," + SearchCondition.EQUAL, parentId);
			}
			// uploadPersonId包含当前登录人id
			String currentUserId = SecurityUtil.getCurrentUserId();
			params.put("uploadPersonId," + SearchCondition.EQUAL, currentUserId);
			Pager pager = wabController.doSubSingleList(params, getPager());
			logger.info("获取电话薄列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSearch() {
		return "goSearch";
	}

	/** 获取电话薄搜索列表数据 */
	public String goSearchList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = null;
			/* params.put("wabtype," + SearchCondition.EQUAL,"1"); */
			String i = getRequestParameter("i");
			// 姓名
			String name = getRequestParameter("name");
			if (null != name && !"".equals(name)) {
				name = URLDecoder.decode(name, "utf-8");
			}
			// 公司
			String company = getRequestParameter("company");
			if (null != company && !"".equals(company)) {
				company = URLDecoder.decode(company, "utf-8");
			}
			// 地址
			String langCode = getRequestParameter("langCode");
			if (null != langCode && !"".equals(langCode)) {
				langCode = URLDecoder.decode(langCode, "utf-8");
			}
			// 职位
			String position = getRequestParameter("position");
			if (null != position && !"".equals(position)) {
				position = URLDecoder.decode(position, "utf-8");
			}
			// 公司号
			String calls = getRequestParameter("calls");
			if (null != calls && !"".equals(calls)) {
				calls = URLDecoder.decode(calls, "utf-8");
			}
			// 邮箱
			String email = getRequestParameter("email");
			if (null != email && !"".equals(email)) {
				email = URLDecoder.decode(email, "utf-8");
			}
			// 手机号码
			String mobileno = getRequestParameter("mobileno");
			if (null != mobileno && !"".equals(mobileno)) {
				mobileno = URLDecoder.decode(mobileno, "utf-8");
			}
			// QQ
			String qq = getRequestParameter("qq");
			if (null != qq && !"".equals(qq)) {
				qq = URLDecoder.decode(qq, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("mobileno," + SearchCondition.ANYLIKE, mobileno);
				pager = wabController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != name && !"".equals(name)) {
					params.put("name," + SearchCondition.ANYLIKE, name);
				}
				if (null != company && !"".equals(company)) {
					params.put("company," + SearchCondition.ANYLIKE, company);
				}
				if (null != langCode && !"".equals(langCode)) {
					params.put("langCode," + SearchCondition.ANYLIKE, langCode);
				}
				if (null != position && !"".equals(position)) {
					params.put("position," + SearchCondition.ANYLIKE, position);
				}
				if (null != calls && !"".equals(calls)) {
					params.put("calls," + SearchCondition.ANYLIKE, calls);
				}
				if (null != email && !"".equals(email)) {
					params.put("email," + SearchCondition.ANYLIKE, email);
				}
				if (null != mobileno && !"".equals(mobileno)) {
					params.put("mobileno," + SearchCondition.ANYLIKE, mobileno);
				}
				if (null != qq && !"".equals(qq)) {
					params.put("qq," + SearchCondition.ANYLIKE, qq);
				}
				pager = wabController.goSingleList(params, getPager());
			}
			logger.info("获取电话薄搜索列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至个人通讯簿用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				wab = wabController.doListEntityById(id);
				logger.info("");
			} else {
				if (StringUtils.isNotEmpty(parentId) && !parentId.equals("0")) {
					Wab c = baseHibernateService.findEntityById(Wab.class, parentId);
					if (null != c) {
						wab = new Wab();
						wab.setParentCategory(c);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理个人通讯簿新增修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (wab.getParentCategory() == null || StringUtils.isNotEmpty(wab.getParentCategory().getId())) {
				wab.setParentCategory(null);
			}
			if (StringUtils.isNotEmpty(wab.getId()) && !"".equals(wab.getId())) {
				isSave = false;
			}

			initEntityBaseController.initEntityBaseAttribute(wab);
			wab = wabController.doSaveSalesOrder(wab);
			/** 拿到当前用户 */
			this.wab.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.wab.setUploadPerson(SecurityUtil.getCurrentUserName());
			/** 拿到当前用户的姓名，保存 */
			wab.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			this.saveBaseEntity(this.wab);
			logger.info("新增！");
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

	/** 处理删除个人通讯簿操作 */
	public String deleteById() {
		try {
			Wab pb = wabController.findEntityById(id);
			if (null != pb) {
				wabController.doDeleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 树形结构JSON */
	public void findTreeToJson() {
		try {
			List<Organization> listOrganization = new ArrayList<Organization>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
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

	public String goChooseOrganization() {
		return "goChooseOrganization";
	}

	public String goChooseCategory() {
		return "goChooseCategory";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Wab getWab() {
		return wab;
	}

	public void setWab(Wab wab) {
		this.wab = wab;
	}

}
