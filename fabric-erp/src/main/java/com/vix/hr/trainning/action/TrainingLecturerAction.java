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
import com.vix.hr.trainning.controller.TrainingLecturerController;
import com.vix.hr.trainning.entity.TrainingLecturer;
import com.vix.hr.trainning.service.ITrainingLecturerService;

/**
 * 
 * @Description:培训讲师
 * @author bobchen
 * @date 2015-8-25 下午2:21:37
 */
@Controller
@Scope("prototype")
public class TrainingLecturerAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	Logger logger = Logger.getLogger(TrainingLecturer.class);

	/** 注入service */
	@Autowired
	private TrainingLecturerController trainingLecturerController;
	private List<TrainingLecturer> trainingLecturerList;
	private TrainingLecturer trainingLecturer;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	@Autowired
	private ITrainingLecturerService iTrainingLecturerService;
	private String id;
	private String pageNo;
	private String parentId;

	@Override
	public String goList() {
		try {
			trainingLecturerList = trainingLecturerController.findTrainingLecturerIndex();
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
			/* 状态 */
			String lecturerNature = getRequestParameter("lecturerNature");
			if (null != lecturerNature && !"".equals(lecturerNature)) {
				params.put("lecturerNature," + SearchCondition.ANYLIKE, lecturerNature);
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
			Pager pager = trainingLecturerController.goSingleList(params, getPager());
			logger.info("获取培训教师列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSearch() {
		return "goSearch";
	}

	/** 获取培训教师搜索列表数据 */
	public String goSearchList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = null;
			String i = getRequestParameter("i");
			// 编码
			String lecturerCode = getRequestParameter("lecturerCode");
			if (null != lecturerCode && !"".equals(lecturerCode)) {
				lecturerCode = URLDecoder.decode(lecturerCode, "utf-8");
			}
			// 姓名
			String lecturerName = getRequestParameter("lecturerName");
			if (null != lecturerName && !"".equals(lecturerName)) {
				lecturerName = URLDecoder.decode(lecturerName, "utf-8");
			}
			// 职位
			String lecturerPosition = getRequestParameter("lecturerPosition");
			if (null != lecturerPosition && !"".equals(lecturerPosition)) {
				lecturerPosition = URLDecoder.decode(lecturerPosition, "utf-8");
			}
			// 部门
			String branches = getRequestParameter("branches");
			if (null != branches && !"".equals(branches)) {
				branches = URLDecoder.decode(branches, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("lecturerCode," + SearchCondition.ANYLIKE, lecturerCode);
				pager = trainingLecturerController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != lecturerCode && !"".equals(lecturerCode)) {
					params.put("lecturerCode," + SearchCondition.ANYLIKE, lecturerCode);
				}
				if (null != lecturerName && !"".equals(lecturerName)) {
					params.put("lecturerName," + SearchCondition.ANYLIKE, lecturerName);
				}
				if (null != lecturerPosition && !"".equals(lecturerPosition)) {
					params.put("lecturerPosition," + SearchCondition.ANYLIKE, lecturerPosition);
				}
				if (null != branches && !"".equals(branches)) {
					params.put("branches," + SearchCondition.ANYLIKE, branches);
				}
				pager = trainingLecturerController.goSingleList(params, getPager());
			}
			logger.info("获取培训教师搜索列表数据页");
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
				trainingLecturer = trainingLecturerController.doListEntityById(id);
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
			if (StringUtils.isNotEmpty(trainingLecturer.getId()) && !"0".equals(trainingLecturer.getId())) {
				isSave = false;
			}
			/** 索引 */
			String title = trainingLecturer.getLecturerName();
			String py = ChnToPinYin.getPYString(title);
			trainingLecturer.setChineseFirstLetter(py.toUpperCase());

			initEntityBaseController.initEntityBaseAttribute(trainingLecturer);
			trainingLecturer = trainingLecturerController.doSaveTrainingLecturer(trainingLecturer);

			this.trainingLecturer.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.trainingLecturer.setUploadPerson(SecurityUtil.getCurrentUserName());
			/** 拿到当前用户的姓名，保存 */
			trainingLecturer.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			this.saveBaseEntity(this.trainingLecturer);
			logger.info("对培训讲师进行了修改！");
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
			TrainingLecturer trainingLecturer = trainingLecturerController.doListEntityById(id);
			if (null != trainingLecturer) {
				trainingLecturerController.doDeleteByEntity(trainingLecturer);
				logger.info("");
				setMessage(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
			logger.info("删除培训教师");
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
				listCategory = iTrainingLecturerService.findAllSubEntity(Organization.class, "parentOrganization.id", id, params);
			} else {
				listCategory = iTrainingLecturerService.findAllSubEntity(Organization.class, "parentOrganization.id", null, params);
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

	public List<TrainingLecturer> getTrainingLecturerList() {
		return trainingLecturerList;
	}

	public void setTrainingLecturerList(List<TrainingLecturer> trainingLecturerList) {
		this.trainingLecturerList = trainingLecturerList;
	}

	public TrainingLecturer getTrainingLecturer() {
		return trainingLecturer;
	}

	public void setTrainingLecturer(TrainingLecturer trainingLecturer) {
		this.trainingLecturer = trainingLecturer;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
}
