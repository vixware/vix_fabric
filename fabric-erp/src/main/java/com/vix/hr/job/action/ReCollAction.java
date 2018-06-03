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
import com.vix.hr.job.controler.RecruitnoficaitionController;
import com.vix.hr.job.entity.HrRecruitnoficaition;
import com.vix.hr.job.service.IRecruitnoficaitionService;

/**
 * @Description: 招聘征集
 * @author 李大鹏
 * @date 2013-08-07
 */
@Controller
@Scope("prototype")
public class ReCollAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(HrRecruitnoficaition.class);
	/** 注入service */
	@Autowired
	private RecruitnoficaitionController recruitnoficaitionController;
	private List<HrRecruitnoficaition> hrRecruitnoficaitionList;
	private HrRecruitnoficaition hrRecruitnoficaition;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	@Autowired
	private IRecruitnoficaitionService iRecruitnoficaitionService;
	private String id;
	private String pageNo;
	private List<HrRecruitnoficaition> hrRecruitnoficaitions;

	public void executeDrools() throws Exception {

	}

	public void executeEsper() throws Exception {

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

	public IRecruitnoficaitionService getiRecruitnoficaitionService() {
		return iRecruitnoficaitionService;
	}

	public void setiRecruitnoficaitionService(IRecruitnoficaitionService iRecruitnoficaitionService) {
		this.iRecruitnoficaitionService = iRecruitnoficaitionService;
	}

	public HrRecruitnoficaition getHrRecruitnoficaition() {
		return hrRecruitnoficaition;
	}

	public void setHrRecruitnoficaition(HrRecruitnoficaition hrRecruitnoficaition) {
		this.hrRecruitnoficaition = hrRecruitnoficaition;
	}

	public List<HrRecruitnoficaition> getHrRecruitnoficaitionList() {
		return hrRecruitnoficaitionList;
	}

	public void setHrRecruitnoficaitionList(List<HrRecruitnoficaition> hrRecruitnoficaitionList) {
		this.hrRecruitnoficaitionList = hrRecruitnoficaitionList;
	}

	public List<HrRecruitnoficaition> getHrRecruitnoficaitions() {
		return hrRecruitnoficaitions;
	}

	public void setHrRecruitnoficaitions(List<HrRecruitnoficaition> hrRecruitnoficaitions) {
		this.hrRecruitnoficaitions = hrRecruitnoficaitions;
	}

	@Override
	public String goList() {
		try {
			hrRecruitnoficaitionList = recruitnoficaitionController.findRecruitnoficaitionIndex();
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
	 * 获取招聘征集列表
	 * 
	 * @return
	 */
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
			Pager pager = recruitnoficaitionController.goSingleList(params, getPager());
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

	/** 获取招聘征集列表页搜索数据 */
	public String goSearchList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = null;
			String i = getRequestParameter("i");
			// 主题
			String noticeTheName = getRequestParameter("noticeTheName");
			if (null != noticeTheName && !"".equals(noticeTheName)) {
				noticeTheName = URLDecoder.decode(noticeTheName, "utf-8");
			}
			// 通知部门
			String companyOrDepartment = getRequestParameter("companyOrDepartment");
			if (null != companyOrDepartment && !"".equals(companyOrDepartment)) {
				companyOrDepartment = URLDecoder.decode(companyOrDepartment, "utf-8");
			}
			// 发送部门
			String sendObj = getRequestParameter("sendObj");
			if (null != sendObj && !"".equals(sendObj)) {
				sendObj = URLDecoder.decode(sendObj, "utf-8");
			}
			// 发布人
			String uploadPersonName = getRequestParameter("uploadPersonName");
			if (null != uploadPersonName && !"".equals(uploadPersonName)) {
				uploadPersonName = URLDecoder.decode(uploadPersonName, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("noticeTheName," + SearchCondition.ANYLIKE, noticeTheName);
				pager = recruitnoficaitionController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != noticeTheName && !"".equals(noticeTheName)) {
					params.put("noticeTheName," + SearchCondition.ANYLIKE, noticeTheName);
				}
				if (null != companyOrDepartment && !"".equals(companyOrDepartment)) {
					params.put("companyOrDepartment," + SearchCondition.ANYLIKE, companyOrDepartment);
				}
				if (null != sendObj && !"".equals(sendObj)) {
					params.put("sendObj," + SearchCondition.ANYLIKE, sendObj);
				}
				if (null != uploadPersonName && !"".equals(uploadPersonName)) {
					params.put("uploadPersonName," + SearchCondition.ANYLIKE, uploadPersonName);
				}
				pager = recruitnoficaitionController.goSingleList(params, getPager());
			}
			logger.info("获取列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/**
	 * 获取招聘征集列表数据，跳转
	 * 
	 * @return
	 */
	public String goSubSingleList() {
		try {
			Map<String, Object> params = getParams();
			getPager().setPageSize(6);
			Pager pager = recruitnoficaitionController.doSubSingleList(params, getPager());
			logger.info("");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SUB_SINGLE_LIST;
	}

	/**
	 * 跳转至招聘征集修改页面
	 * 
	 * @return
	 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				hrRecruitnoficaition = recruitnoficaitionController.doListEntityById(id);
				logger.info("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/**
	 * 处理招聘征集修改，保存功能
	 * 
	 * @return
	 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(hrRecruitnoficaition.getId()) && !"0".equals(hrRecruitnoficaition.getId())) {
				isSave = false;
			}
			/** 索引 */
			String title = hrRecruitnoficaition.getNoticeTheName();
			String py = ChnToPinYin.getPYString(title);
			hrRecruitnoficaition.setChineseFirstLetter(py.toUpperCase());

			initEntityBaseController.initEntityBaseAttribute(hrRecruitnoficaition);
			hrRecruitnoficaition = recruitnoficaitionController.doSaveRecruitnoficaition(hrRecruitnoficaition);

			this.hrRecruitnoficaition.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.hrRecruitnoficaition.setUploadPerson(SecurityUtil.getCurrentUserName());
			/** 拿到当前用户的姓名，保存 */
			hrRecruitnoficaition.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			this.saveBaseEntity(this.hrRecruitnoficaition);
			logger.info("对招聘征集进行了修改！");
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
	 * 根据Id删除招聘征集信息
	 * 
	 * @return
	 */
	public String deleteById() {
		try {
			HrRecruitnoficaition hrRecruitnoficaition = recruitnoficaitionController.doListEntityById(id);
			if (null != hrRecruitnoficaition) {
				recruitnoficaitionController.doDeleteByEntity(hrRecruitnoficaition);
				logger.info("");
				setMessage(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
			logger.info("删除招聘征集信息");
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
				listCategory = iRecruitnoficaitionService.findAllSubEntity(Organization.class, "parentOrganization.id", id, params);
			} else {
				listCategory = iRecruitnoficaitionService.findAllSubEntity(Organization.class, "parentOrganization.id", null, params);
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
