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
import com.vix.hr.trainning.controller.DemandSummaryController;
import com.vix.hr.trainning.entity.DemandApply;
import com.vix.hr.trainning.entity.DemandSummary;
import com.vix.hr.trainning.service.IDemandApplyService;

/**
 * 
 * @Description:培训需求申请
 * @author bobchen
 * @date 2015-8-28 下午2:13:20
 */
@Controller
@Scope("prototype")
public class DemandSummaryAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	Logger logger = Logger.getLogger(DemandSummary.class);

	/** 注入service */
	@Autowired
	private DemandSummaryController demandSummaryController;
	private List<DemandSummary> demandSummaryList;
	private DemandSummary demandSummary;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	@Autowired
	private IDemandApplyService iDemandApplyService;
	/** 培训申请 */
	private DemandApply demandApply;
	private String id;
	private String pageNo;
	private String parentId;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public DemandSummaryController getDemandSummaryController() {
		return demandSummaryController;
	}

	public void setDemandSummaryController(DemandSummaryController demandSummaryController) {
		this.demandSummaryController = demandSummaryController;
	}

	public List<DemandSummary> getDemandSummaryList() {
		return demandSummaryList;
	}

	public void setDemandSummaryList(List<DemandSummary> demandSummaryList) {
		this.demandSummaryList = demandSummaryList;
	}

	public DemandSummary getDemandSummary() {
		return demandSummary;
	}

	public void setDemandSummary(DemandSummary demandSummary) {
		this.demandSummary = demandSummary;
	}

	public DemandApply getDemandApply() {
		return demandApply;
	}

	public void setDemandApply(DemandApply demandApply) {
		this.demandApply = demandApply;
	}

	public InitEntityBaseController getInitEntityBaseController() {
		return initEntityBaseController;
	}

	public void setInitEntityBaseController(InitEntityBaseController initEntityBaseController) {
		this.initEntityBaseController = initEntityBaseController;
	}

	public IDemandApplyService getiDemandApplyService() {
		return iDemandApplyService;
	}

	public void setiDemandApplyService(IDemandApplyService iDemandApplyService) {
		this.iDemandApplyService = iDemandApplyService;
	}

	@Override
	public String goList() {
		try {
			demandSummaryList = demandSummaryController.findDemandSummaryIndex();
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

	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			/* 按最近汇总 */
			String summaryDate = getRequestParameter("summaryDate");
			if (summaryDate != null && !"".equals(summaryDate)) {
				params.put("summaryDate," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(summaryDate));
			}
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("summaryDate");
				getPager().setOrderBy("desc");
			}
			/* uploadPersonId包含当前登录人id */
			String employeeId = SecurityUtil.getCurrentUserId();
			params.put("uploadPersonId," + SearchCondition.EQUAL, employeeId);
			Pager pager = demandSummaryController.goSingleList(params, getPager());
			logger.info("获取列表数据");
			setPager(pager);
		} catch (Exception e) {
			// TODO: handle exception
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
			/* 汇总名称 */
			String summaryName = getRequestParameter("summaryName");
			if (null != summaryName && !"".equals(summaryName)) {
				summaryName = URLDecoder.decode(summaryName, "utf-8");
			}
			/* 培训讲师 */
			String trainingInstructor = getRequestParameter("trainingInstructor");
			if (null != trainingInstructor && !"".equals(trainingInstructor)) {
				trainingInstructor = URLDecoder.decode(trainingInstructor, "utf-8");
			}
			/* 汇总部门或人员 */
			String summaryDepartmentORPeople = getRequestParameter("summaryDepartmentORPeople");
			if (null != summaryDepartmentORPeople && !"".equals(summaryDepartmentORPeople)) {
				summaryDepartmentORPeople = URLDecoder.decode(summaryDepartmentORPeople, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("summaryName," + SearchCondition.ANYLIKE, summaryName);
				pager = demandSummaryController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != summaryName && !"".equals(summaryName)) {
					params.put("summaryName," + SearchCondition.ANYLIKE, summaryName);
				}
				if (null != trainingInstructor && !"".equals(trainingInstructor)) {
					params.put("trainingInstructor," + SearchCondition.ANYLIKE, trainingInstructor);
				}
				if (null != summaryDepartmentORPeople && !"".equals(summaryDepartmentORPeople)) {
					params.put("summaryDepartmentORPeople," + SearchCondition.ANYLIKE, summaryDepartmentORPeople);
				}
				pager = demandSummaryController.goSingleList(params, getPager());
			}
			logger.info("获取需求汇总搜索列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/**
	 * 跳转至用户修改页面
	 * 
	 * @return
	 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				demandSummary = demandSummaryController.doListEntityById(id);
				logger.info("");
			} else {
				demandSummary = new DemandSummary();
				demandSummary = demandSummaryController.doSaveDemandSummary(demandSummary);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/**
	 * 
	 * 处理修改/保存操作
	 * 
	 * @return
	 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(demandSummary.getId()) && !"0".equals(demandSummary.getId())) {
				isSave = false;
			}

			/** 索引 */
			String title = demandSummary.getSummaryName();
			String py = ChnToPinYin.getPYString(title);
			demandSummary.setChineseFirstLetter(py.toUpperCase());

			initEntityBaseController.initEntityBaseAttribute(demandSummary);
			demandSummary = demandSummaryController.doSaveDemandSummary(demandSummary);

			this.demandSummary.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.demandSummary.setUploadPerson(SecurityUtil.getCurrentUserName());
			/** 拿到当前用户的姓名，保存 */
			demandSummary.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			this.saveBaseEntity(this.demandSummary);
			logger.info("对培训需求汇总进行了修改！");
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
	 * 
	 * 处理删除操作
	 * 
	 * @return
	 */
	public String deleteById() {
		try {
			DemandSummary demandSummary = demandSummaryController.doListEntityById(id);
			if (null != demandSummary) {
				demandSummaryController.doDeleteByEntity(demandSummary);
				logger.info("");
				setMessage(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
			logger.info("删除培训汇总信息");
		} catch (Exception e) {
			e.printStackTrace();
			setMessage(DELETE_FAIL);
		}
		return UPDATE;
	}

	/*******************************************
	 * 培训需求申请
	 ********************************************************************************/
	/**
	 * 
	 * 获取培训课程json数据
	 */
	public void getDemandApplyJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				DemandSummary demandSummary = demandSummaryController.findEntityById(id);
				json = convertListToJson(new ArrayList<DemandApply>(demandSummary.getDemandApply()), demandSummary.getDemandApply().size(), "demandSummary");
			}
			if (!"".equals(json)) {
				renderJson(json);
			} else {
				renderJson("{\"total\":0,\"rows\":[]}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 培训申请明细
	 * 
	 * @return
	 */
	public String saveOrUpdateDemandApply() {
		try {
			String tcIdStr = getRequestParameter("tcId");
			if (null != tcIdStr) {
				demandApply = demandSummaryController.findDemandApplyById(tcIdStr);
				demandSummary = demandSummaryController.doListEntityById(id);
				demandApply.setDemandSummary(demandSummary);
				demandSummaryController.doSaveDemandApply(demandApply);
				setMessage("保存成功！");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			setMessage("保存失败！");
		}
		return UPDATE;
	}

	/**
	 * 处理删除tab明细操作
	 * 
	 * @return
	 */
	public String deleteDemandApplyById() {
		try {
			DemandApply demandApply = demandSummaryController.doListDemandApplyById(id);
			if (null != demandApply) {
				demandSummaryController.deleteDemandApplyEntity(demandApply);
				setMessage(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			setMessage(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 跳转到培训申请添加明细页面 */
	public String goAddDemandApply() {
		try {
			String lineItemIdStr = getRequestParameter("lineItemId");
			if (null != lineItemIdStr) {
				demandApply = demandSummaryController.findDemandApplyById(lineItemIdStr);
			}
			logger.info("添加培训申请");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goAddDemandApply";
	}

	/** 跳转到培训申请添加申请明细列表页面 */
	public String goDemandApplyLists() {
		try {
			Map<String, Object> params = getParams();
			String name = getRequestParameter("searchContent");
			String status = getRequestParameter("status");
			// 按状态
			if (null != name && !"".equals(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			// 按状态
			if (null != status && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			Pager pager = demandSummaryController.goDemandApplyList(params, getPager());
			logger.info("获取选择申请的数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goDemandApplyLists";
	}

	// 弹框选择组织
	public void findTreeToJson() {
		try {
			List<Organization> listOrganization = new ArrayList<Organization>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				listOrganization = iDemandApplyService.findAllSubEntity(Organization.class, "parentOrganization.id", id, params);
			} else {
				listOrganization = iDemandApplyService.findAllSubEntity(Organization.class, "parentOrganization.id", null, params);
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
}
