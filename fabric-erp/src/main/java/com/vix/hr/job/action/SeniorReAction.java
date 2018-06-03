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
import com.vix.hr.job.controler.SeniorController;
import com.vix.hr.job.entity.HrSenior;
import com.vix.hr.job.service.ISeniorService;

/**
 * @Description: 高级人才招聘
 * @author 李大鹏
 * @date 2013-08-19
 */
@Controller
@Scope("prototype")
public class SeniorReAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	Logger logger = Logger.getLogger(HrSenior.class);

	/** 注入service */
	@Autowired
	private SeniorController seniorController;
	private List<HrSenior> hrSeniorsList;
	private HrSenior hrSenior;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	@Autowired
	private ISeniorService iSeniorService;
	private String id;
	private String pageNo;
	private List<HrSenior> hrSeniorss;

	public List<HrSenior> getHrSeniorsList() {
		return hrSeniorsList;
	}

	public void setHrSeniorsList(List<HrSenior> hrSeniorsList) {
		this.hrSeniorsList = hrSeniorsList;
	}

	public HrSenior getHrSenior() {
		return hrSenior;
	}

	public void setHrSenior(HrSenior hrSenior) {
		this.hrSenior = hrSenior;
	}

	public ISeniorService getiSeniorService() {
		return iSeniorService;
	}

	public void setiSeniorService(ISeniorService iSeniorService) {
		this.iSeniorService = iSeniorService;
	}

	public List<HrSenior> getHrSeniorss() {
		return hrSeniorss;
	}

	public void setHrSeniorss(List<HrSenior> hrSeniorss) {
		this.hrSeniorss = hrSeniorss;
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
			hrSeniorsList = seniorController.findSeniorIndex();
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
			/* 结论 */
			String conclusion = getRequestParameter("conclusion");
			if (null != conclusion && !"".equals(conclusion)) {
				params.put("conclusion," + SearchCondition.ANYLIKE, conclusion);
			}
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
			/* uploadPersonId包含当前登录人id */
			String employeeId = SecurityUtil.getCurrentUserId();
			params.put("uploadPersonId," + SearchCondition.EQUAL, employeeId);
			Pager pager = seniorController.goSingleList(params, getPager());
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
			// 姓名
			String applicantsName = getRequestParameter("applicantsName");
			if (null != applicantsName && !"".equals(applicantsName)) {
				applicantsName = URLDecoder.decode(applicantsName, "utf-8");
			}
			// 应聘职位
			String employmentObjective = getRequestParameter("employmentObjective");
			if (null != employmentObjective && !"".equals(employmentObjective)) {
				employmentObjective = URLDecoder.decode(employmentObjective, "utf-8");
			}
			// 所在公司部门
			String applicantsDepartment = getRequestParameter("applicantsDepartment");
			if (null != applicantsDepartment && !"".equals(applicantsDepartment)) {
				applicantsDepartment = URLDecoder.decode(applicantsDepartment, "utf-8");
			}
			// 推荐部门
			String recommendedDepartment = getRequestParameter("recommendedDepartment");
			if (null != recommendedDepartment && !"".equals(recommendedDepartment)) {
				recommendedDepartment = URLDecoder.decode(recommendedDepartment, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("applicantsName," + SearchCondition.ANYLIKE, applicantsName);
				pager = seniorController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != applicantsName && !"".equals(applicantsName)) {
					params.put("applicantsName," + SearchCondition.ANYLIKE, applicantsName);
				}
				if (null != employmentObjective && !"".equals(employmentObjective)) {
					params.put("employmentObjective," + SearchCondition.ANYLIKE, employmentObjective);
				}
				if (null != applicantsDepartment && !"".equals(applicantsDepartment)) {
					params.put("applicantsDepartment," + SearchCondition.ANYLIKE, applicantsDepartment);
				}
				if (null != recommendedDepartment && !"".equals(recommendedDepartment)) {
					params.put("recommendedDepartment," + SearchCondition.ANYLIKE, recommendedDepartment);
				}
				pager = seniorController.goSingleList(params, getPager());
			}
			logger.info("获取高级人才招聘搜索列表数据页");
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
			Pager pager = seniorController.doSubSingleList(params, getPager());
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
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {// if (null !=
																// id &&
																// id.longValue()
																// > 0) {
				hrSenior = seniorController.doListEntityById(id);
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
			if (StringUtils.isNotEmpty(hrSenior.getId()) && !"0".equals(hrSenior.getId())) {
				isSave = false;
			}
			/** 索引 */
			String title = hrSenior.getApplicantsName();
			String py = ChnToPinYin.getPYString(title);
			hrSenior.setChineseFirstLetter(py.toUpperCase());

			initEntityBaseController.initEntityBaseAttribute(hrSenior);
			hrSenior = seniorController.doSaveSenior(hrSenior);

			this.hrSenior.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.hrSenior.setUploadPerson(SecurityUtil.getCurrentUserName());
			/** 拿到当前用户的姓名，保存 */
			hrSenior.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			this.saveBaseEntity(this.hrSenior);
			logger.info("对高级人才招聘进行了修改！");
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
			HrSenior hrSenior = seniorController.doListEntityById(id);
			if (null != hrSenior) {
				seniorController.doDeleteByEntity(hrSenior);
				logger.info("");
				setMessage(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
			logger.info("删除高级人才招聘信息");
		} catch (Exception e) {
			e.printStackTrace();
			setMessage(DELETE_FAIL);
		}
		return UPDATE;
	}

	// 弹框选择组织
	public void findTreeToJson() {
		try {
			List<Organization> listCategory = new ArrayList<Organization>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (null != id && !"".equals(id)) {
				listCategory = iSeniorService.findAllSubEntity(Organization.class, "parentOrganization.id", id, params);
			} else {
				listCategory = iSeniorService.findAllSubEntity(Organization.class, "parentOrganization.id", null, params);
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

	/** 弹出选择部门，职位窗体 */
	public String goChooseCategory() {
		return "goChooseCategory";
	}
}
