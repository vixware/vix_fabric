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
import com.vix.hr.job.controler.RecruitSummaryController;
import com.vix.hr.job.entity.HrRecruitSummary;
import com.vix.hr.job.service.IRecruitSummaryService;

/**
 * @Description: 招聘总结
 * @author 李大鹏
 * @date 2013-08-21
 */
@Controller
@Scope("prototype")
public class ReSumAciton extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(HrRecruitSummary.class);

	/** 注入service */
	@Autowired
	private RecruitSummaryController recruitSummaryController;
	private List<HrRecruitSummary> recruitSummariesList;
	private HrRecruitSummary hrRecruitSummary;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	@Autowired
	private IRecruitSummaryService iRecruitSummaryService;
	private String id;
	private String pageNo;
	private List<HrRecruitSummary> hrRecruitSummaries;

	public List<HrRecruitSummary> getRecruitSummariesList() {
		return recruitSummariesList;
	}

	public void setRecruitSummariesList(List<HrRecruitSummary> recruitSummariesList) {
		this.recruitSummariesList = recruitSummariesList;
	}

	public HrRecruitSummary getHrRecruitSummary() {
		return hrRecruitSummary;
	}

	public void setHrRecruitSummary(HrRecruitSummary hrRecruitSummary) {
		this.hrRecruitSummary = hrRecruitSummary;
	}

	public IRecruitSummaryService getiRecruitSummaryService() {
		return iRecruitSummaryService;
	}

	public void setiRecruitSummaryService(IRecruitSummaryService iRecruitSummaryService) {
		this.iRecruitSummaryService = iRecruitSummaryService;
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

	public List<HrRecruitSummary> getHrRecruitSummaries() {
		return hrRecruitSummaries;
	}

	public void setHrRecruitSummaries(List<HrRecruitSummary> hrRecruitSummaries) {
		this.hrRecruitSummaries = hrRecruitSummaries;
	}

	@Override
	public String goList() {
		try {
			recruitSummariesList = recruitSummaryController.findRecruitSummaryIndex();
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

	/**
	 * 获取招聘总结列表页数据
	 * 
	 * @return
	 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			/* 审批状态 */
			String approvalStatus = getRequestParameter("approvalStatus");
			if (null != approvalStatus && !"".equals(approvalStatus)) {
				params.put("approvalStatus," + SearchCondition.ANYLIKE, approvalStatus);
			}
			/* 按最近使用 */
			String actualStartTime = getRequestParameter("actualStartTime");
			if (actualStartTime != null && !"".equals(actualStartTime)) {
				params.put("actualStartTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(actualStartTime));
			}
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("actualStartTime");
				getPager().setOrderBy("desc");
			}
			/* uploadPersonId包含当前登录人id */
			String employeeId = SecurityUtil.getCurrentUserId();
			params.put("uploadPersonId," + SearchCondition.EQUAL, employeeId);
			Pager pager = recruitSummaryController.goSingleList(params, getPager());
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

	/**
	 * 获取招聘总结列表页搜索数据
	 * 
	 * @return
	 */
	public String goSearchList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = null;
			String i = getRequestParameter("i");
			// 应聘计划名称
			String recruitmentPlanName = getRequestParameter("recruitmentPlanName");
			if (null != recruitmentPlanName && !"".equals(recruitmentPlanName)) {
				recruitmentPlanName = URLDecoder.decode(recruitmentPlanName, "utf-8");
			}
			// 招聘活动名称
			String recruitmentActivityName = getRequestParameter("recruitmentActivityName");
			if (null != recruitmentActivityName && !"".equals(recruitmentActivityName)) {
				recruitmentActivityName = URLDecoder.decode(recruitmentActivityName, "utf-8");
			}
			// 已招聘职位
			String hasTheJob = getRequestParameter("hasTheJob");
			if (null != hasTheJob && !"".equals(hasTheJob)) {
				hasTheJob = URLDecoder.decode(hasTheJob, "utf-8");
			}
			// 招聘负责人
			String recruiter = getRequestParameter("recruiter");
			if (null != recruiter && !"".equals(recruiter)) {
				recruiter = URLDecoder.decode(recruiter, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("recruitmentPlanName," + SearchCondition.ANYLIKE, recruitmentPlanName);
				pager = recruitSummaryController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != recruitmentPlanName && !"".equals(recruitmentPlanName)) {
					params.put("recruitmentPlanName," + SearchCondition.ANYLIKE, recruitmentPlanName);
				}
				if (null != recruitmentActivityName && !"".equals(recruitmentActivityName)) {
					params.put("recruitmentActivityName," + SearchCondition.ANYLIKE, recruitmentActivityName);
				}
				if (null != hasTheJob && !"".equals(hasTheJob)) {
					params.put("hasTheJob," + SearchCondition.ANYLIKE, hasTheJob);
				}
				if (null != recruiter && !"".equals(recruiter)) {
					params.put("recruiter," + SearchCondition.ANYLIKE, recruiter);
				}
				pager = recruitSummaryController.goSingleList(params, getPager());
			}
			logger.info("获取列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/**
	 * 获取招聘总结列表页数据，跳转
	 * 
	 * @return
	 */
	public String goSubSingleList() {
		try {
			Map<String, Object> params = getParams();
			getPager().setPageSize(6);
			Pager pager = recruitSummaryController.doSubSingleList(params, getPager());
			logger.info("");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SUB_SINGLE_LIST;
	}

	/**
	 * 跳转至招聘总结修改页面
	 * 
	 * @return
	 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				hrRecruitSummary = recruitSummaryController.doListEntityById(id);
				logger.info("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/**
	 * 处理招聘总结修改，保存功能
	 * 
	 * @return
	 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(hrRecruitSummary.getId()) && !"0".equals(hrRecruitSummary.getId())) {
				isSave = false;
			}
			/** 索引 */
			String title = hrRecruitSummary.getRecruitmentPlanName();
			String py = ChnToPinYin.getPYString(title);
			hrRecruitSummary.setChineseFirstLetter(py.toUpperCase());

			initEntityBaseController.initEntityBaseAttribute(hrRecruitSummary);
			hrRecruitSummary = recruitSummaryController.doSaveRecruitSummary(hrRecruitSummary);

			this.hrRecruitSummary.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.hrRecruitSummary.setUploadPerson(SecurityUtil.getCurrentUserName());
			/** 拿到当前用户的姓名，保存 */
			hrRecruitSummary.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			this.saveBaseEntity(this.hrRecruitSummary);
			logger.info("对招聘总结进行了修改！");
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
	 * 根据Id删除招聘总结数据
	 * 
	 * @return
	 */
	public String deleteById() {
		try {
			HrRecruitSummary hrRecruitSummary = recruitSummaryController.doListEntityById(id);
			if (null != hrRecruitSummary) {
				recruitSummaryController.doDeleteByEntity(hrRecruitSummary);
				logger.info("");
				setMessage(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
			logger.info("删除招聘总结信息");
		} catch (Exception e) {
			e.printStackTrace();
			setMessage(DELETE_FAIL);
		}
		return UPDATE;
	}

	/**
	 * 根据json跳转选择部门弹出窗口
	 */
	public void findTreeToJson() {
		try {
			List<Organization> listCategory = new ArrayList<Organization>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (null != id && !"".equals(id)) {
				listCategory = iRecruitSummaryService.findAllSubEntity(Organization.class, "parentOrganization.id", id, params);
			} else {
				listCategory = iRecruitSummaryService.findAllSubEntity(Organization.class, "parentOrganization.id", null, params);
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

	/**
	 * 弹出选择部门，职位窗体
	 * 
	 * @return
	 */
	public String goChooseCategory() {
		return "goChooseCategory";
	}
}
