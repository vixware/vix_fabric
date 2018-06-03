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
import com.vix.hr.job.controler.HirManageController;
import com.vix.hr.job.entity.HrHirManage;
import com.vix.hr.job.service.IHirManageService;

/**
 * @Description: 录用管理
 * @author 李大鹏
 * @date 2013-09-16
 */
@Controller
@Scope("prototype")
public class HirManageAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(HrHirManage.class);
	/** 注入service */
	@Autowired
	private HirManageController hirManageController;
	private List<HrHirManage> hrHirManageList;
	private HrHirManage hrHirManage;
	private String pageNo;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	@Autowired
	private IHirManageService iHirManageService;

	public List<HrHirManage> getHrHirManageList() {
		return hrHirManageList;
	}

	public void setHrHirManageList(List<HrHirManage> hrHirManageList) {
		this.hrHirManageList = hrHirManageList;
	}

	public HrHirManage getHrHirManage() {
		return hrHirManage;
	}

	public void setHrHirManage(HrHirManage hrHirManage) {
		this.hrHirManage = hrHirManage;
	}

	public IHirManageService getiHirManageService() {
		return iHirManageService;
	}

	public void setiHirManageService(IHirManageService iHirManageService) {
		this.iHirManageService = iHirManageService;
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

	public List<HrHirManage> getHrHirManages() {
		return hrHirManages;
	}

	public void setHrHirManages(List<HrHirManage> hrHirManages) {
		this.hrHirManages = hrHirManages;
	}

	private String id;
	private List<HrHirManage> hrHirManages;

	/**
	 * 加载顶端工具栏
	 * 
	 * @return
	 */

	public String goTopDynamicMenuContent() {
		return "goTopDynamicMenuContent";
	}

	@Override
	public String goList() {
		try {
			hrHirManageList = hirManageController.findHrHirManageIndex();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			/* 性别 */
			String sex = getRequestParameter("sex");
			if (null != sex && !"".equals(sex)) {
				params.put("sex," + SearchCondition.ANYLIKE, sex);
			}
			/* 按最近使用 */
			String expectedArrivalDate = getRequestParameter("expectedArrivalDate");
			if (expectedArrivalDate != null && !"".equals(expectedArrivalDate)) {
				params.put("expectedArrivalDate," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(expectedArrivalDate));
			}
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("expectedArrivalDate");
				getPager().setOrderBy("desc");
			}
			/* uploadPersonId包含当前登录人id */
			String employeeId = SecurityUtil.getCurrentUserId();
			params.put("uploadPersonId," + SearchCondition.EQUAL, employeeId);
			Pager pager = hirManageController.goSingleList(params, getPager());
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
			// 应聘人姓名
			String candidateName = getRequestParameter("candidateName");
			if (null != candidateName && !"".equals(candidateName)) {
				candidateName = URLDecoder.decode(candidateName, "utf-8");
			}
			// 笔试结果
			String writtenTestResults = getRequestParameter("writtenTestResults");
			if (null != writtenTestResults && !"".equals(writtenTestResults)) {
				writtenTestResults = URLDecoder.decode(writtenTestResults, "utf-8");
			}
			// 应聘职位
			String employmentObjective = getRequestParameter("employmentObjective");
			if (null != employmentObjective && !"".equals(employmentObjective)) {
				employmentObjective = URLDecoder.decode(employmentObjective, "utf-8");
			}
			// 应聘部门
			String applicantsDepartment = getRequestParameter("applicantsDepartment");
			if (null != applicantsDepartment && !"".equals(applicantsDepartment)) {
				applicantsDepartment = URLDecoder.decode(applicantsDepartment, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("candidateName," + SearchCondition.ANYLIKE, candidateName);
				pager = hirManageController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != candidateName && !"".equals(candidateName)) {
					params.put("candidateName," + SearchCondition.ANYLIKE, candidateName);
				}
				if (null != writtenTestResults && !"".equals(writtenTestResults)) {
					params.put("writtenTestResults," + SearchCondition.ANYLIKE, writtenTestResults);
				}
				if (null != employmentObjective && !"".equals(employmentObjective)) {
					params.put("employmentObjective," + SearchCondition.ANYLIKE, employmentObjective);
				}
				if (null != applicantsDepartment && !"".equals(applicantsDepartment)) {
					params.put("applicantsDepartment," + SearchCondition.ANYLIKE, applicantsDepartment);
				}
				pager = hirManageController.goSingleList(params, getPager());
			}
			logger.info("获取列表数据页");
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
			Pager pager = hirManageController.doSubSingleList(params, getPager());
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
				hrHirManage = hirManageController.doListEntityById(id);
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
			if (StringUtils.isNotEmpty(hrHirManage.getId()) && !"0".equals(hrHirManage.getId())) {
				isSave = false;
			}
			/** 索引 */
			String title = hrHirManage.getCandidateName();
			String py = ChnToPinYin.getPYString(title);
			hrHirManage.setChineseFirstLetter(py.toUpperCase());

			initEntityBaseController.initEntityBaseAttribute(hrHirManage);
			hrHirManage = hirManageController.doSaveHrHirManage(hrHirManage);

			this.hrHirManage.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.hrHirManage.setUploadPerson(SecurityUtil.getCurrentUserName());
			/** 拿到当前用户的姓名，保存 */
			hrHirManage.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			this.saveBaseEntity(this.hrHirManage);
			logger.info("对录用管理进行了修改！");
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
			HrHirManage hrHirManage = hirManageController.doListEntityById(id);
			if (null != hrHirManage) {
				hirManageController.doDeleteByEntity(hrHirManage);
				logger.info("");
				setMessage(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
			logger.info("删除录用管理信息");
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
				listCategory = iHirManageService.findAllSubEntity(Organization.class, "parentOrganization.id", id, params);
			} else {
				listCategory = iHirManageService.findAllSubEntity(Organization.class, "parentOrganization.id", null, params);
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
