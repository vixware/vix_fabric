package com.vix.hr.trainning.action;

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
import com.vix.hr.trainning.controller.DemandNoticeController;
import com.vix.hr.trainning.entity.DemandNotice;
import com.vix.hr.trainning.service.IDemandNoticeService;

/**
 * 
 * @Description:需求通知
 * @author bobchen
 * @date 2015-9-1 下午5:47:21
 */
@Controller
@Scope("prototype")
public class DemandNoticeAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(DemandNotice.class);
	/** 注入service */
	@Autowired
	private DemandNoticeController demandNoticeController;
	private List<DemandNotice> demandNoticeList;
	private DemandNotice demandNotice;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	@Autowired
	private IDemandNoticeService iDemandNoticeService;
	private String id;
	private String pageNo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String goList() {
		try {
			demandNoticeList = demandNoticeController.findDemandNoticeIndex();
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

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			/* 按最近使用 */
			String noticeStartTime = getRequestParameter("noticeStartTime");
			if (noticeStartTime != null && !"".equals(noticeStartTime)) {
				params.put("noticeStartTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(noticeStartTime));
			}
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("noticeStartTime");
				getPager().setOrderBy("desc");
			}
			/* uploadPersonId包含当前登录人id */
			String employeeId = SecurityUtil.getCurrentUserId();
			params.put("uploadPersonId," + SearchCondition.EQUAL, employeeId);
			Pager pager = demandNoticeController.goSingleList(params, getPager());
			logger.info("获取列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSearch() {
		return "goSearch";
	}

	/** 获取搜索列表数据 */
	public String goSearchList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = null;
			String i = getRequestParameter("i");
			/* 通知主题 */
			String noticeTheName = getRequestParameter("noticeTheName");
			if (null != noticeTheName && !"".equals(noticeTheName)) {
				noticeTheName = URLDecoder.decode(noticeTheName, "utf-8");
			}
			/* 通知部门或人员 */
			String companyOrDepartment = getRequestParameter("companyOrDepartment");
			if (null != companyOrDepartment && !"".equals(companyOrDepartment)) {
				companyOrDepartment = URLDecoder.decode(companyOrDepartment, "utf-8");
			}
			/* 发送部门或人员 */
			String departmentOrPerson = getRequestParameter("departmentOrPerson");
			if (null != departmentOrPerson && !"".equals(departmentOrPerson)) {
				departmentOrPerson = URLDecoder.decode(departmentOrPerson, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("noticeTheName," + SearchCondition.ANYLIKE, noticeTheName);
				pager = demandNoticeController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != noticeTheName && !"".equals(noticeTheName)) {
					params.put("noticeTheName," + SearchCondition.ANYLIKE, noticeTheName);
				}
				if (null != companyOrDepartment && !"".equals(companyOrDepartment)) {
					params.put("companyOrDepartment," + SearchCondition.ANYLIKE, companyOrDepartment);
				}
				if (null != departmentOrPerson && !"".equals(departmentOrPerson)) {
					params.put("departmentOrPerson," + SearchCondition.ANYLIKE, departmentOrPerson);
				}
				pager = demandNoticeController.goSingleList(params, getPager());
			}
			logger.info("获取需求通知搜索列表数据页");
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
			Pager pager = demandNoticeController.doSubSingleList(params, getPager());
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
				demandNotice = demandNoticeController.doListEntityById(id);
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
			if (StringUtils.isNotEmpty(demandNotice.getId()) && !"0".equals(demandNotice.getId())) {
				isSave = false;
			}
			/** 索引 */
			String title = demandNotice.getNoticeTheName();
			String py = ChnToPinYin.getPYString(title);
			demandNotice.setChineseFirstLetter(py.toUpperCase());

			initEntityBaseController.initEntityBaseAttribute(demandNotice);
			demandNotice = demandNoticeController.doSaveDemandNotice(demandNotice);

			this.demandNotice.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.demandNotice.setUploadPerson(SecurityUtil.getCurrentUserName());
			/** 拿到当前用户的姓名，保存 */
			demandNotice.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			this.saveBaseEntity(this.demandNotice);
			logger.info("对需求通知进行了修改！");
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
			DemandNotice demandNotice = demandNoticeController.doListEntityById(id);
			if (null != demandNotice) {
				demandNoticeController.doDeleteByEntity(demandNotice);
				logger.info("");
				setMessage(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
			logger.info("删除需求通知信息");
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
				listOrganization = iDemandNoticeService.findAllSubEntity(Organization.class, "parentOrganization.id", id, params);
			} else {
				listOrganization = iDemandNoticeService.findAllSubEntity(Organization.class, "parentOrganization.id", null, params);
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

	public IDemandNoticeService getiDemandNoticeService() {
		return iDemandNoticeService;
	}

	public void setiDemandNoticeService(IDemandNoticeService iDemandNoticeService) {
		this.iDemandNoticeService = iDemandNoticeService;
	}

	public List<DemandNotice> getDemandNoticeList() {
		return demandNoticeList;
	}

	public void setDemandNoticeList(List<DemandNotice> demandNoticeList) {
		this.demandNoticeList = demandNoticeList;
	}

	public DemandNotice getDemandNotice() {
		return demandNotice;
	}

	public void setDemandNotice(DemandNotice demandNotice) {
		this.demandNotice = demandNotice;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

}
