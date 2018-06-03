package com.vix.hr.job.action;
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
import com.vix.hr.job.controler.RecruitmentPlanSummaryController;
import com.vix.hr.job.entity.HrRecruitmentPlansummary;
import com.vix.hr.job.entity.HrRecruitplan;
import com.vix.hr.job.service.RecruitmentPlanSummaryService;
/**
 * @Description: 招聘计划汇总
 * @author 陈正文
 */
@Controller
@Scope("prototype")
public class RecruitmentPlanSummaryAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	Logger logger = Logger.getLogger(HrRecruitmentPlansummary.class);

	/** 注入service */
	@Autowired
	private RecruitmentPlanSummaryController recruitmentPlanSummaryController;
	private List<HrRecruitmentPlansummary> hrRecruitmentPlansummaryList;
	private HrRecruitmentPlansummary hrRecruitmentPlansummary;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	@Autowired
	private RecruitmentPlanSummaryService recruitmentPlanSummaryService;
	/** 培训课程明细 */
	private HrRecruitplan hrRecruitplan;

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

	public List<HrRecruitmentPlansummary> getHrRecruitmentPlansummaryList() {
		return hrRecruitmentPlansummaryList;
	}

	public void setHrRecruitmentPlansummaryList(List<HrRecruitmentPlansummary> hrRecruitmentPlansummaryList) {
		this.hrRecruitmentPlansummaryList = hrRecruitmentPlansummaryList;
	}

	public HrRecruitmentPlansummary getHrRecruitmentPlansummary() {
		return hrRecruitmentPlansummary;
	}

	public void setHrRecruitmentPlansummary(HrRecruitmentPlansummary hrRecruitmentPlansummary) {
		this.hrRecruitmentPlansummary = hrRecruitmentPlansummary;
	}

	public RecruitmentPlanSummaryService getRecruitmentPlanSummaryService() {
		return recruitmentPlanSummaryService;
	}

	public void setRecruitmentPlanSummaryService(RecruitmentPlanSummaryService recruitmentPlanSummaryService) {
		this.recruitmentPlanSummaryService = recruitmentPlanSummaryService;
	}

	public HrRecruitplan getHrRecruitplan() {
		return hrRecruitplan;
	}

	public void setHrRecruitplan(HrRecruitplan hrRecruitplan) {
		this.hrRecruitplan = hrRecruitplan;
	}

	@Override
	public String goList() {
		try {
			hrRecruitmentPlansummaryList = recruitmentPlanSummaryController.findRecruitmentPlansummaryIndex();
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
			/* 状态 */
			String releaseState = getRequestParameter("releaseState");
			if (null != releaseState && !"".equals(releaseState)) {
				params.put("releaseState," + SearchCondition.ANYLIKE, releaseState);
			}
			/* 按最近使用 */
			String releaseTime = getRequestParameter("releaseTime");
			if (releaseTime != null && !"".equals(releaseTime)) {
				params.put("releaseTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(releaseTime));
			}
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("releaseTime");
				getPager().setOrderBy("desc");
			}
			/* uploadPersonId包含当前登录人id */
			String employeeId = SecurityUtil.getCurrentUserId();
			params.put("uploadPersonId," + SearchCondition.EQUAL, employeeId);
			Pager pager = recruitmentPlanSummaryController.goSingleList(params, getPager());
			logger.info("获取招聘计划汇总列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	/**
	 * 获取搜索列表数据
	 * 
	 * @return
	 */
	public String goSearchList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = null;
			String i = getRequestParameter("i");
			// 招聘职务
			String recruitment = getRequestParameter("recruitment");
			if (null != recruitment && !"".equals(recruitment)) {
				recruitment = URLDecoder.decode(recruitment, "utf-8");
			}
			// 招聘部门
			String recruitmentDepartment = getRequestParameter("recruitmentDepartment");
			if (null != recruitmentDepartment && !"".equals(recruitmentDepartment)) {
				recruitmentDepartment = URLDecoder.decode(recruitmentDepartment, "utf-8");
			}
			// 审核人
			String auditPerson = getRequestParameter("auditPerson");
			if (null != auditPerson && !"".equals(auditPerson)) {
				auditPerson = URLDecoder.decode(auditPerson, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("recruitment," + SearchCondition.ANYLIKE, recruitment);
				pager = recruitmentPlanSummaryController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != recruitment && !"".equals(recruitment)) {
					params.put("recruitment," + SearchCondition.ANYLIKE, recruitment);
				}
				if (null != recruitmentDepartment && !"".equals(recruitmentDepartment)) {
					params.put("recruitmentDepartment," + SearchCondition.ANYLIKE, recruitmentDepartment);
				}
				if (null != auditPerson && !"".equals(auditPerson)) {
					params.put("auditPerson," + SearchCondition.ANYLIKE, auditPerson);
				}
				pager = recruitmentPlanSummaryController.goSingleList(params, getPager());
			}
			logger.info("获取招聘计划汇总搜索列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSearch() {
		return "goSearch";
	}

	/**
	 * 获取列表数据，跳转
	 * 
	 * @return
	 */
	public String goSubSingleList() {
		try {
			Map<String, Object> params = getParams();
			getPager().setPageSize(6);
			Pager pager = recruitmentPlanSummaryController.doSubSingleList(params, getPager());
			logger.info("");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SUB_SINGLE_LIST;
	}
	/**
	 * 跳转至用户修改页面
	 * 
	 * @return
	 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {// if (null !=
																// id &&
																// id.longValue()
																// > 0) {
				hrRecruitmentPlansummary = recruitmentPlanSummaryController.doListEntityById(id);
				logger.info("");
			} else {
				hrRecruitmentPlansummary = new HrRecruitmentPlansummary();
				hrRecruitmentPlansummary = recruitmentPlanSummaryController.doSaveHrRecruitmentPlansummary(hrRecruitmentPlansummary);
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
			if (StringUtils.isNotEmpty(hrRecruitmentPlansummary.getId()) && !"0".equals(hrRecruitmentPlansummary.getId())) {
				isSave = false;
			}
			/** 索引 */
			String title = hrRecruitmentPlansummary.getRecruitment();
			String py = ChnToPinYin.getPYString(title);
			hrRecruitmentPlansummary.setChineseFirstLetter(py.toUpperCase());

			initEntityBaseController.initEntityBaseAttribute(hrRecruitmentPlansummary);
			hrRecruitmentPlansummary = recruitmentPlanSummaryController.doSaveHrRecruitmentPlansummary(hrRecruitmentPlansummary);

			this.hrRecruitmentPlansummary.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.hrRecruitmentPlansummary.setUploadPerson(SecurityUtil.getCurrentUserName());
			/** 拿到当前用户的姓名，保存 */
			hrRecruitmentPlansummary.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			this.saveBaseEntity(this.hrRecruitmentPlansummary);
			logger.info("对招聘计划汇总进行了修改！");
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
			HrRecruitmentPlansummary hrRecruitmentPlansummary = recruitmentPlanSummaryController.doListEntityById(id);
			if (null != hrRecruitmentPlansummary) {
				recruitmentPlanSummaryController.doDeleteByEntity(hrRecruitmentPlansummary);
				logger.info("");
				setMessage(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
			logger.info("删除招聘计划汇总");
		} catch (Exception e) {
			e.printStackTrace();
			setMessage(DELETE_FAIL);
		}
		return UPDATE;
	}

	/**
	 * 
	 * 弹出选择部门，职位窗体
	 * 
	 * @return
	 */
	public String goChooseCategory() {
		return "goChooseCategory";
	}

	/**
	 * 
	 * 获取招聘计划明细json数据
	 */
	public void getHrRecruitplanJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				HrRecruitmentPlansummary hrRecruitmentPlansummary = recruitmentPlanSummaryController.findEntityById(id);
				json = convertListToJson(new ArrayList<HrRecruitplan>(hrRecruitmentPlansummary.getHrRecruitplan()), hrRecruitmentPlansummary.getHrRecruitplan().size(), "hrRecruitmentPlansummary");
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
	 * 招聘计划明细
	 * 
	 * @return
	 */
	public String saveOrUpdateHrRecruitplan() {
		try {
			String tcIdStr = getRequestParameter("tcId");
			if (null != tcIdStr) {
				hrRecruitplan = recruitmentPlanSummaryController.findHrRecruitplanById(tcIdStr);
				hrRecruitmentPlansummary = recruitmentPlanSummaryController.doListEntityById(id);
				hrRecruitplan.setHrRecruitmentPlansummary(hrRecruitmentPlansummary);
				recruitmentPlanSummaryController.doSaveHrRecruitplan(hrRecruitplan);
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
	public String deleteHrRecruitplanById() {
		try {
			HrRecruitplan hrRecruitplan = recruitmentPlanSummaryController.doListHrRecruitplanById(id);
			if (null != hrRecruitplan) {
				recruitmentPlanSummaryController.deleteHrRecruitplanEntity(hrRecruitplan);
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

	/** 跳转到招聘计划添加明细页面 */
	public String goAddHrRecruitplan() {
		try {
			String lineItemIdStr = getRequestParameter("lineItemId");
			if (null != lineItemIdStr) {
				hrRecruitplan = recruitmentPlanSummaryController.findHrRecruitplanById(lineItemIdStr);
			}
			logger.info("添加招聘计划明细");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goAddHrRecruitplan";
	}

	/** 跳转到招聘计划添加明细列表页面 */
	public String goSubRadioSingleLists() {
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
			Pager pager = recruitmentPlanSummaryController.goHrRecruitplanList(params, getPager());
			logger.info("获取选择招聘计划的数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSubRadioSingleLists";
	}

	public void findTreeToJson() {
		try {
			List<Organization> listCategory = new ArrayList<Organization>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (null != id && !"".equals(id)) {
				listCategory = recruitmentPlanSummaryService.findAllSubEntity(Organization.class, "parentOrganization.id", id, params);
			} else {
				listCategory = recruitmentPlanSummaryService.findAllSubEntity(Organization.class, "parentOrganization.id", null, params);
			}
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			for (int i = 0; i < listCategory.size(); i++) {
				Organization sc = listCategory.get(i);
				if (sc.getSubOrganizations().size() > 0) {
					strSb.append("{id:\"");
					strSb.append(sc.getId());
					strSb.append("\",name:\"");
					strSb.append(sc.getOrgName());
					strSb.append("\",open:false,isParent:true}");
				} else {
					strSb.append("{id:\"");
					strSb.append(sc.getId());
					strSb.append("\",name:\"");
					strSb.append(sc.getOrgName());
					strSb.append("\",open:false,isParent:false}");
				}
				if (i < listCategory.size() - 1) {
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
