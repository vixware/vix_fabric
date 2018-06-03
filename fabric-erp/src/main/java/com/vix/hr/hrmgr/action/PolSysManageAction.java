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
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.hr.hrmgr.controller.PolSysManageController;
import com.vix.hr.hrmgr.entity.PolSysManage;
import com.vix.hr.hrmgr.service.IPolSysManageService;

@Controller
@Scope("prototype")
public class PolSysManageAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(PolSysManage.class);
	/** 注入service */
	@Autowired
	private PolSysManageController polSysManageController;
	@Autowired
	private InitEntityBaseController initEntityBaseController;

	@Autowired
	private IPolSysManageService iPolSysManageService;

	private String id;
	private String parentId;
	private String pageNo;
	/** 政策制度管理 */
	private PolSysManage polSysManage;
	/** 政策制度管理集合 */
	private List<PolSysManage> polSysManagesList;

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PolSysManage getPolSysManage() {
		return polSysManage;
	}

	public void setPolSysManage(PolSysManage polSysManage) {
		this.polSysManage = polSysManage;
	}

	public List<PolSysManage> getPolSysManagesList() {
		return polSysManagesList;
	}

	public void setPolSysManagesList(List<PolSysManage> polSysManagesList) {
		this.polSysManagesList = polSysManagesList;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public IPolSysManageService getiPolSysManageService() {
		return iPolSysManageService;
	}

	public void setiPolSysManageService(IPolSysManageService iPolSysManageService) {
		this.iPolSysManageService = iPolSysManageService;
	}

	/** 跳转到列表页面 */
	@Override
	public String goList() {
		try {
			polSysManagesList = polSysManageController.findPolSysManageIndex();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return GO_LIST;
	}

	/** 树形结构JSON */
	public void findTreeToJson() {
		try {
			List<Organization> listOrganization = new ArrayList<Organization>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				listOrganization = iPolSysManageService.findAllSubEntity(Organization.class, "parentOrganization.id", id, params);
			} else {
				listOrganization = iPolSysManageService.findAllSubEntity(Organization.class, "parentOrganization.id", null, params);
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

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			/* 按最近使用 */
			String createTime = getRequestParameter("createTime");
			if (createTime != null && !"".equals(createTime)) {
				params.put("createTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(createTime));
			}
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("createTime");
				getPager().setOrderBy("desc");
			}
			Pager pager = polSysManageController.goSingleList(params, getPager());
			logger.info("获取列表上半部数据页");
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
			// 内容
			String searchContent = getRequestParameter("searchContent");
			// 名称
			String name = getRequestParameter("name");
			// 联系人
			String contacts = getRequestParameter("contacts");
			if (null != contacts && !"".equals(contacts)) {
				contacts = URLDecoder.decode(contacts, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("contacts," + SearchCondition.ANYLIKE, searchContent);
				params.put("name," + SearchCondition.ANYLIKE, searchContent);
				pager = polSysManageController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != contacts && !"".equals(contacts)) {
					params.put("contacts," + SearchCondition.ANYLIKE, contacts);
				}
				// 如果名称为空，则需要将封装好的名称条件移除
				if (null == name || "".equals(name)) {
					params.remove(name);
				}
				pager = polSysManageController.goSingleList(params, getPager());
			}
			logger.info("获取列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSubSingleList() {
		try {
			Map<String, Object> params = getParams();
			getPager().setPageSize(6);
			Pager pager = polSysManageController.goSubSingleList(params, getPager());
			logger.info("获取列表数据页");
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
				polSysManage = polSysManageController.doListPolSysManageById(id);
				logger.info("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	/**
	 * 跳转至用户修改页面
	 * 
	 * @return pol_update.jsp
	 */
	public String goSaveOrUpdatePol() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				polSysManage = polSysManageController.doListPolSysManageById(id);
				logger.info("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdatePol";
	}

	/** 弹出选择所属分类 */
	public String goChooseSupplierCategory() {
		return "goChooseSupplierCategory";
	}

	/**
	 * 处理修改操作
	 * 
	 * @return
	 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(polSysManage.getId()) && !"".equals(polSysManage.getId())) {
				isSave = false;
			}
			/** 索引 */
			String title = polSysManage.getName();
			String py = ChnToPinYin.getPYString(title);
			polSysManage.setChineseFirstLetter(py.toUpperCase());

			if (null != parentId && !"".equals(parentId)) {
				PolSysManage sCategory = polSysManageController.findPolSysManageById(parentId);
				// polSysManage.setPolSysManage(sCategory);
			}
			initEntityBaseController.initEntityBaseAttribute(polSysManage);
			polSysManage = polSysManageController.doSavePolSysManage(polSysManage);
			logger.info("对政策制度进行了修改！");
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

	/**
	 * 处理保存，修改政策制度内容表内容
	 * 
	 * @return
	 */
	public String saveOrUpdatePol() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(polSysManage.getId()) && !"".equals(polSysManage.getId())) {
				isSave = false;
			}

			if (null != parentId && !"".equals(parentId)) {
				PolSysManage sCategory = polSysManageController.findPolSysManageById(parentId);
				// polSysManage.setPolSysManage(sCategory);
			}
			initEntityBaseController.initEntityBaseAttribute(polSysManage);
			polSysManage = polSysManageController.doSavePolSysManage(polSysManage);
			logger.info("对政策制度进行了修改！");
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
			PolSysManage polSysManageTemp = polSysManageController.doListPolSysManageById(id);
			if (null != polSysManageTemp) {
				polSysManageController.doDeleteByPolSysManage(polSysManageTemp);
				logger.info("");
				setMessage(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
			logger.info("删除政策制度信息");
		} catch (Exception e) {
			e.printStackTrace();
			setMessage(DELETE_FAIL);
		}
		return UPDATE;
	}

	/**
	 * 弹出选择部门，职位窗体
	 * 
	 * @return
	 */
	public String goChooseCategory() {
		return "goChooseCategory";
	}
}
