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
import com.vix.hr.job.controler.RecruitactivitityController;
import com.vix.hr.job.entity.HrRecruitactivitity;
import com.vix.hr.job.entity.HrRecruitactivitityDetails;
import com.vix.hr.job.service.IRecruitactivitityService;

/**
 * @Description: 招聘活动
 * @author 李大鹏
 * @date 2013-07-31
 */
@Controller
@Scope("prototype")
public class ReActAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(HrRecruitactivitity.class);
	/** 注入Service */
	@Autowired
	private RecruitactivitityController recruitactivitityController;
	private List<HrRecruitactivitity> hrrecruitactivititiesList;
	private HrRecruitactivitity hrRecruitactivitity;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	@Autowired
	private IRecruitactivitityService iRecruitactivitityService;
	private HrRecruitactivitityDetails details;

	private String id;
	private String pageNo;
	private List<HrRecruitactivitity> hrRecruitactivitities;

	public IRecruitactivitityService getiRecruitactivitityService() {
		return iRecruitactivitityService;
	}

	public void setiRecruitactivitityService(IRecruitactivitityService iRecruitactivitityService) {
		this.iRecruitactivitityService = iRecruitactivitityService;
	}

	public List<HrRecruitactivitity> getHrrecruitactivititiesList() {
		return hrrecruitactivititiesList;
	}

	public void setHrrecruitactivititiesList(List<HrRecruitactivitity> hrrecruitactivititiesList) {
		this.hrrecruitactivititiesList = hrrecruitactivititiesList;
	}

	public HrRecruitactivitity getHrRecruitactivitity() {
		return hrRecruitactivitity;
	}

	public void setHrRecruitactivitity(HrRecruitactivitity hrRecruitactivitity) {
		this.hrRecruitactivitity = hrRecruitactivitity;
	}

	@Override
	public String goList() {
		try {
			hrrecruitactivititiesList = recruitactivitityController.findRecruitactivitityIndex();
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
	 * 获取招聘活动列表数据
	 * 
	 * @return
	 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			/* 按最近使用 */
			String activitystartDate = getRequestParameter("activitystartDate");
			if (activitystartDate != null && !"".equals(activitystartDate)) {
				params.put("activitystartDate," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(activitystartDate));
			}
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("activitystartDate");
				getPager().setOrderBy("desc");
			}
			/* uploadPersonId包含当前登录人id */
			String employeeId = SecurityUtil.getCurrentUserId();
			params.put("uploadPersonId," + SearchCondition.EQUAL, employeeId);
			Pager pager = recruitactivitityController.goSingleList(params, getPager());
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

	/** 获取招聘活动搜索列表数据 */
	public String goSearchList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = null;
			String i = getRequestParameter("i");
			// 活动名称
			String activityName = getRequestParameter("activityName");
			if (null != activityName && !"".equals(activityName)) {
				activityName = URLDecoder.decode(activityName, "utf-8");
			}
			// 活动地点
			String activityAddress = getRequestParameter("activityAddress");
			if (null != activityAddress && !"".equals(activityAddress)) {
				activityAddress = URLDecoder.decode(activityAddress, "utf-8");
			}
			// 招聘对象
			String recruitingObject = getRequestParameter("recruitingObject");
			if (null != recruitingObject && !"".equals(recruitingObject)) {
				recruitingObject = URLDecoder.decode(recruitingObject, "utf-8");
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
				params.put("activityName," + SearchCondition.ANYLIKE, activityName);
				pager = recruitactivitityController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != activityName && !"".equals(activityName)) {
					params.put("activityName," + SearchCondition.ANYLIKE, activityName);
				}
				if (null != activityAddress && !"".equals(activityAddress)) {
					params.put("activityAddress," + SearchCondition.ANYLIKE, activityAddress);
				}
				if (null != recruitingObject && !"".equals(recruitingObject)) {
					params.put("recruitingObject," + SearchCondition.ANYLIKE, recruitingObject);
				}
				if (null != uploadPersonName && !"".equals(uploadPersonName)) {
					params.put("uploadPersonName," + SearchCondition.ANYLIKE, uploadPersonName);
				}
				pager = recruitactivitityController.goSingleList(params, getPager());
			}
			logger.info("获取列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/**
	 * 获取招聘活动列表数据，跳转
	 * 
	 * @return
	 */
	public String goSubSingleList() {
		try {
			Map<String, Object> params = getParams();
			getPager().setPageSize(6);
			Pager pager = recruitactivitityController.doSubSingleList(params, getPager());
			logger.info("");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SUB_SINGLE_LIST;
	}

	/**
	 * 跳转至招聘活动修改页面
	 * 
	 * @return
	 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				hrRecruitactivitity = recruitactivitityController.doListEntityById(id);
			} else {
				hrRecruitactivitity = new HrRecruitactivitity();
				hrRecruitactivitity.setIsTemp("1");
				hrRecruitactivitity = recruitactivitityController.doSaveRecruitactivitity(hrRecruitactivitity);
				logger.info("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/**
	 * 获取招聘活动明细json数据
	 */
	public void getHrRecruitactivitityDetailsJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				HrRecruitactivitity hr = recruitactivitityController.findEntityById(id);
				List<HrRecruitactivitityDetails> hrTemp = new ArrayList<HrRecruitactivitityDetails>(hr.getHrRecruitactivitityDetails());
				for (HrRecruitactivitityDetails var : hrTemp) {
					if ("1".equals(var.getPublicationType())) {
						var.setPublicationType("内部");
					}
					if ("2".equals(var.getPublicationType())) {
						var.setPublicationType("外部");
					}
					if ("3".equals(var.getPublicationType())) {
						var.setPublicationType("内部和外部");
					}
					if ("1".equals(var.getPublicationStruts())) {
						var.setPublicationStruts("已发布");
					}
					if ("2".equals(var.getPublicationStruts())) {
						var.setPublicationStruts("未发布");
					}
				}
				json = convertListToJson(hrTemp, hrTemp.size(), "hrRecruitactivitity");
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
	 * 根据Id保存招聘活动明细表数据
	 * 
	 * @return
	 */
	public String saveOrUpdateHrRecruitactivitityDetails() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				hrRecruitactivitity = recruitactivitityController.doListEntityById(id);
				if (hrRecruitactivitity != null) {
					details.setHrRecruitactivitity(hrRecruitactivitity);
				}
				recruitactivitityController.doSaveHrRecruitactivitityDetails(details);
				setMessage("保存成功！");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			setMessage("保存失败！");
		}
		return UPDATE;
	}

	/**
	 * 跳转到招聘计划明细页面
	 * 
	 * @return
	 */
	public String goAddHrRecruitactivitityDetails() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				details = recruitactivitityController.doListHrRecruitactivitityDetailsById(id);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goAddHrRecruitactivitityDetails";
	}

	/**
	 * 处理招聘活动修改，保存功能
	 * 
	 * @return
	 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(hrRecruitactivitity.getId()) && !"0".equals(hrRecruitactivitity.getId())) {
				isSave = false;
			}
			/** 索引 */
			String title = hrRecruitactivitity.getActivityName();
			String py = ChnToPinYin.getPYString(title);
			hrRecruitactivitity.setChineseFirstLetter(py.toUpperCase());

			initEntityBaseController.initEntityBaseAttribute(hrRecruitactivitity);
			hrRecruitactivitity = recruitactivitityController.doSaveRecruitactivitity(hrRecruitactivitity);
			hrRecruitactivitity.setIsTemp("");

			this.hrRecruitactivitity.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.hrRecruitactivitity.setUploadPerson(SecurityUtil.getCurrentUserName());
			/** 拿到当前用户的姓名，保存 */
			hrRecruitactivitity.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			this.saveBaseEntity(this.hrRecruitactivitity);
			logger.info("对招聘活动进行了修改！");
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
	 * 根据Id删除招聘活动主表数据
	 * 
	 * @return
	 */
	public String deleteById() {
		try {
			HrRecruitactivitity hrRecruitactivitity = recruitactivitityController.doListEntityById(id);
			if (null != hrRecruitactivitity) {
				recruitactivitityController.doDeleteByEntity(hrRecruitactivitity);
				logger.info("");
				setMessage(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
			logger.info("删除招聘活动信息");
		} catch (Exception e) {
			e.printStackTrace();
			setMessage(DELETE_FAIL);
		}
		return UPDATE;
	}

	/**
	 * 根据Id删除招聘活动明细数据
	 * 
	 * @return
	 */
	public String deleteHrRecruitactivitityDetailsById() {
		try {
			HrRecruitactivitityDetails hrRecruitactivitityDetails = recruitactivitityController.doListHrRecruitactivitityDetailsById(id);
			if (null != hrRecruitactivitityDetails) {
				recruitactivitityController.deleteHrRecruitactivitityDetailsDetailsEntity(hrRecruitactivitityDetails);
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

	/**
	 * 根据json跳转选择部门弹出窗口
	 */
	public void findTreeToJson() {
		try {
			List<Organization> listCategory = new ArrayList<Organization>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (null != id && !"".equals(id)) {
				listCategory = iRecruitactivitityService.findAllSubEntity(Organization.class, "parentOrganization.id", id, params);
			} else {
				listCategory = iRecruitactivitityService.findAllSubEntity(Organization.class, "parentOrganization.id", null, params);
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<HrRecruitactivitity> getHrRecruitactivitities() {
		return hrRecruitactivitities;
	}

	public void setHrRecruitactivitities(List<HrRecruitactivitity> hrRecruitactivitities) {
		this.hrRecruitactivitities = hrRecruitactivitities;
	}

	public HrRecruitactivitityDetails getDetails() {
		return details;
	}

	public void setDetails(HrRecruitactivitityDetails details) {
		this.details = details;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

}
