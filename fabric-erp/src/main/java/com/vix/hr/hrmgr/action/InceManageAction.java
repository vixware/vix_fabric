package com.vix.hr.hrmgr.action;

import java.net.URLDecoder;
import java.util.ArrayList;
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
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.hr.hrmgr.controller.InceManageController;
import com.vix.hr.hrmgr.entity.InceManage;
import com.vix.hr.hrmgr.service.IInceManageService;

@Controller
@Scope("prototype")
public class InceManageAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Logger logger = Logger.getLogger(InceManage.class);

	/** 注入service */
	@Autowired
	private InceManageController inceManageController;
	private List<InceManage> inceManagesList;
	private InceManage inceManage;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	@Autowired
	private IInceManageService iInceManageService;
	private String id;
	private String pageNo;

	public List<InceManage> getInceManagesList() {
		return inceManagesList;
	}

	public void setInceManagesList(List<InceManage> inceManagesList) {
		this.inceManagesList = inceManagesList;
	}

	public InceManage getInceManage() {
		return inceManage;
	}

	public void setInceManage(InceManage inceManage) {
		this.inceManage = inceManage;
	}

	public IInceManageService getiInceManageService() {
		return iInceManageService;
	}

	public void setiInceManageService(IInceManageService iInceManageService) {
		this.iInceManageService = iInceManageService;
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

	@Override
	public String goList() {
		try {
			inceManagesList = inceManageController.findInceManageIndex();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/**
	 * 加载顶端工具栏
	 * 
	 * @return
	 */

	public String goTopDynamicMenuContent() {
		return "goTopDynamicMenuContent";
	}

	public String goSearch() {
		return "goSearch";
	}

	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			/* 状态 */
			String states = getRequestParameter("states");
			if (null != states && !"".equals(states)) {
				params.put("states," + SearchCondition.ANYLIKE, states);
			}
			/* 按最近使用 */
			String inceTime = getRequestParameter("inceTime");
			if (inceTime != null && !"".equals(inceTime)) {
				params.put("inceTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(inceTime));
			}
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("inceTime");
				getPager().setOrderBy("desc");
			}
			Pager pager = inceManageController.goSingleList(params, getPager());
			logger.info("获取列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 获取搜索列表数据 */
	public String goSearchList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = null;
			String i = getRequestParameter("i");
			/* 员工编码 */
			String inceEmployeeCode = getRequestParameter("inceEmployeeCode");
			if (null != inceEmployeeCode && !"".equals(inceEmployeeCode)) {
				inceEmployeeCode = URLDecoder.decode(inceEmployeeCode, "utf-8");
			}
			/* 员工名称 */
			String inceEmployeeName = getRequestParameter("inceEmployeeName");
			if (null != inceEmployeeName && !"".equals(inceEmployeeName)) {
				inceEmployeeName = URLDecoder.decode(inceEmployeeName, "utf-8");
			}
			/* 主题 */
			String inceTheme = getRequestParameter("inceTheme");
			if (null != inceTheme && !"".equals(inceTheme)) {
				inceTheme = URLDecoder.decode(inceTheme, "utf-8");
			}
			/* 执行人 */
			String uploadPersonName = getRequestParameter("uploadPersonName");
			if (null != uploadPersonName && !"".equals(uploadPersonName)) {
				uploadPersonName = URLDecoder.decode(uploadPersonName, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("inceEmployeeCode," + SearchCondition.ANYLIKE, inceEmployeeCode);
				pager = inceManageController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != inceEmployeeCode && !"".equals(inceEmployeeCode)) {
					params.put("inceEmployeeCode," + SearchCondition.ANYLIKE, inceEmployeeCode);
				}
				if (null != inceEmployeeName && !"".equals(inceEmployeeName)) {
					params.put("inceEmployeeName," + SearchCondition.ANYLIKE, inceEmployeeName);
				}
				if (null != inceTheme && !"".equals(inceTheme)) {
					params.put("inceTheme," + SearchCondition.ANYLIKE, inceTheme);
				}
				if (null != uploadPersonName && !"".equals(uploadPersonName)) {
					params.put("approval," + SearchCondition.ANYLIKE, uploadPersonName);
				}
				pager = inceManageController.goSingleList(params, getPager());
			}
			logger.info("获取奖惩管理搜索列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 获取列表数据，跳转 */
	public String goSubSingleList() {
		try {
			Map<String, Object> params = getParams();
			getPager().setPageSize(6);
			Pager pager = inceManageController.doSubSingleList(params, getPager());
			logger.info("");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SUB_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				inceManage = inceManageController.doListEntityById(id);
				logger.info("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改/保存操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(inceManage.getId()) && !"".equals(inceManage.getId())) {
				isSave = false;
			}
			/** 索引 */
			String title = inceManage.getInceEmployeeName();
			String py = ChnToPinYin.getPYString(title);
			inceManage.setChineseFirstLetter(py.toUpperCase());

			initEntityBaseController.initEntityBaseAttribute(inceManage);
			inceManage = inceManageController.doSaveInceManage(inceManage);

			this.inceManage.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.inceManage.setUploadPerson(SecurityUtil.getCurrentUserName());
			/** 拿到当前用户的姓名，保存 */
			inceManage.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			this.saveBaseEntity(this.inceManage);
			logger.info("对奖惩管理进行了修改！");
			if (isSave) {
				setMessage(SAVE_SUCCESS);
			} else {
				setMessage(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				this.setMessage(SAVE_FAIL);
			} else {
				this.setMessage(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			InceManage inceManage = inceManageController.doListEntityById(id);
			if (null != inceManage) {
				inceManageController.deleteByEntity(inceManage);
				logger.info("");
				setMessage(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
			logger.info("删除奖惩管理信息");
		} catch (Exception e) {
			e.printStackTrace();
			setMessage(DELETE_FAIL);
		}
		return UPDATE;
	}

	// 弹框选择组织
	public void findTreeToJson() {
		try {
			List<Organization> listOrganization = new ArrayList<Organization>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				listOrganization = iInceManageService.findAllSubEntity(Organization.class, "parentOrganization.id", id, params);
			} else {
				listOrganization = iInceManageService.findAllSubEntity(Organization.class, "parentOrganization.id", null, params);
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

	/** 弹出选择部门，职位窗体 */
	public String goChooseCategory() {
		return "goChooseCategory";
	}
}