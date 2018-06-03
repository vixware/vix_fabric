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
import com.vix.hr.trainning.controller.TrainingCourseController;
import com.vix.hr.trainning.entity.TrainingCourse;
import com.vix.hr.trainning.service.ITrainingCourseService;

/**
 * @Description: 培训课程
 * @author 李大鹏
 */
@Controller
@Scope("prototype")
public class TrainingCourseAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	Logger logger = Logger.getLogger(TrainingCourse.class);

	/** 注入service */
	@Autowired
	private TrainingCourseController trainingCourseController;
	private List<TrainingCourse> trainingCourseList;
	private TrainingCourse trainingCourse;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	@Autowired
	private ITrainingCourseService iTrainingCourseService;
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

	public List<TrainingCourse> getTrainingCourseList() {
		return trainingCourseList;
	}

	public void setTrainingCourseList(List<TrainingCourse> trainingCourseList) {
		this.trainingCourseList = trainingCourseList;
	}

	public TrainingCourse getTrainingCourse() {
		return trainingCourse;
	}

	public void setTrainingCourse(TrainingCourse trainingCourse) {
		this.trainingCourse = trainingCourse;
	}

	public ITrainingCourseService getiTrainingCourseService() {
		return iTrainingCourseService;
	}

	public void setiTrainingCourseService(ITrainingCourseService iTrainingCourseService) {
		this.iTrainingCourseService = iTrainingCourseService;
	}

	@Override
	public String goList() {
		try {
			trainingCourseList = trainingCourseController.findTrainingCourseIndex();
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
			String courseType = getRequestParameter("courseType");
			if (null != courseType && !"".equals(courseType)) {
				params.put("courseType," + SearchCondition.ANYLIKE, courseType);
			}
			/* 按最近使用 */
			String effectiveStartDate = getRequestParameter("effectiveStartDate");
			if (effectiveStartDate != null && !"".equals(effectiveStartDate)) {
				params.put("effectiveStartDate," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(effectiveStartDate));
			}
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("effectiveStartDate");
				getPager().setOrderBy("desc");
			}
			/* uploadPersonId包含当前登录人id */
			String employeeId = SecurityUtil.getCurrentUserId();
			params.put("uploadPersonId," + SearchCondition.EQUAL, employeeId);
			Pager pager = trainingCourseController.goSingleList(params, getPager());
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

	/** 获取培训课程搜索列表数据 */
	public String goSearchList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = null;
			String i = getRequestParameter("i");
			// 课程名称
			String courseName = getRequestParameter("courseName");
			if (null != courseName && !"".equals(courseName)) {
				courseName = URLDecoder.decode(courseName, "utf-8");
			}
			// 课程类别
			String courseType = getRequestParameter("courseType");
			if (null != courseType && !"".equals(courseType)) {
				courseType = URLDecoder.decode(courseType, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("courseName," + SearchCondition.ANYLIKE, courseName);
				pager = trainingCourseController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != courseName && !"".equals(courseName)) {
					params.put("courseName," + SearchCondition.ANYLIKE, courseName);
				}
				if (null != courseType && !"".equals(courseType)) {
					params.put("courseType," + SearchCondition.ANYLIKE, courseType);
				}
				pager = trainingCourseController.goSingleList(params, getPager());
			}
			logger.info("获取培训课程搜索列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				trainingCourse = trainingCourseController.doListEntityById(id);
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
			if (StringUtils.isNotEmpty(trainingCourse.getId()) && !"0".equals(trainingCourse.getId())) {
				isSave = false;
			}
			/** 索引 */
			String title = trainingCourse.getCourseName();
			String py = ChnToPinYin.getPYString(title);
			trainingCourse.setChineseFirstLetter(py.toUpperCase());

			initEntityBaseController.initEntityBaseAttribute(trainingCourse);
			trainingCourse = trainingCourseController.doSaveTrainingCourse(trainingCourse);

			this.trainingCourse.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.trainingCourse.setUploadPerson(SecurityUtil.getCurrentUserName());
			/** 拿到当前用户的姓名，保存 */
			trainingCourse.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			this.saveBaseEntity(this.trainingCourse);
			logger.info("对培训课程进行了修改！");
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
			TrainingCourse trainingCourse = trainingCourseController.doListEntityById(id);
			if (null != trainingCourse) {
				trainingCourseController.doDeleteByEntity(trainingCourse);
				logger.info("");
				setMessage(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
			logger.info("删除培训课程");
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
				listOrganization = iTrainingCourseService.findAllSubEntity(Organization.class, "parentOrganization.id", id, params);
			} else {
				listOrganization = iTrainingCourseService.findAllSubEntity(Organization.class, "parentOrganization.id", null, params);
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
