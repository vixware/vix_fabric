package com.vix.hr.attendance.action;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.hr.attendance.controller.AttendanceCategoryController;
import com.vix.hr.attendance.entity.AttendanceCategory;

/**
 * @Description 考勤类别
 * @author 李大鹏
 */
@Controller
@Scope("prototype")
public class AttendanceTeamAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(AttendanceCategory.class);
	/** 注入service */
	@Autowired
	private AttendanceCategoryController attendanceCategoryController;
	@Autowired
	private InitEntityBaseController initEntityBaseController;

	private String id;
	private String parentId;
	private String pageNo;
	/** 考勤类别 */
	private AttendanceCategory attendanceCategory;
	/** 考勤类别集合 */
	private List<AttendanceCategory> attendanceCategoriesList;

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public AttendanceCategory getAttendanceCategory() {
		return attendanceCategory;
	}

	public void setAttendanceCategory(AttendanceCategory attendanceCategory) {
		this.attendanceCategory = attendanceCategory;
	}

	public List<AttendanceCategory> getAttendanceCategoriesList() {
		return attendanceCategoriesList;
	}

	public void setAttendanceCategoriesList(List<AttendanceCategory> attendanceCategoriesList) {
		this.attendanceCategoriesList = attendanceCategoriesList;
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

	/** 跳转到列表页面 */
	@Override
	public String goList() {
		try {
			attendanceCategoriesList = attendanceCategoryController.findAttendanceCategoryIndex();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return GO_LIST;
	}

	/** 树形结构JSON */
	public void findTreeToJson() {
		try {
			List<AttendanceCategory> listCategory = new ArrayList<AttendanceCategory>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (null != id && !"".equals(id)) {
				listCategory = attendanceCategoryController.findAllSubEntity("attendanceCategory.id", id, params);
			} else {
				listCategory = attendanceCategoryController.findAllSubEntity("attendanceCategory.id", null, params);
			}
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			for (int i = 0; i < listCategory.size(); i++) {
				AttendanceCategory sc = listCategory.get(i);
				if (sc.getAttendanceCategories().size() > 0) {
					strSb.append("{id:");
					strSb.append(sc.getId());
					strSb.append(",name:\"");
					strSb.append(sc.getCategoryName());
					strSb.append("\",open:false,isParent:true}");
				} else {
					strSb.append("{id:");
					strSb.append(sc.getId());
					strSb.append(",name:\"");
					strSb.append(sc.getCategoryName());
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

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			if (StringUtils.isNotEmpty(parentId) && !parentId.equals("0")) {
				attendanceCategory = attendanceCategoryController.doListAttendanceCategoryById(id);
				if (null != id) {
					attendanceCategory = attendanceCategoryController.doListAttendanceCategoryById(id);
					logger.info("");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 获取搜索列表数据 */
	public String goSearchList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = null;
			String i = getRequestParameter("i");
			// 内容
			String searchContent = getRequestParameter("searchContent");
			// 名称
			String name = getRequestParameter("name");
			// 联系人
			String contacts = getRequestParameter("contacts");
			if (null != contacts && !"".equals(contacts)) {
				contacts = URLDecoder.decode(contacts, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("contacts," + SearchCondition.ANYLIKE, searchContent);
				params.put("name," + SearchCondition.ANYLIKE, searchContent);
				pager = attendanceCategoryController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != contacts && !"".equals(contacts)) {
					params.put("contacts," + SearchCondition.ANYLIKE, contacts);
				}
				// 如果名称为空，则需要将封装好的名称条件移除
				if (null == name || "".equals(name)) {
					params.remove(name);
				}
				pager = attendanceCategoryController.goSingleList(params, getPager());
			}
			logger.info("获取列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSubSingleList() {
		try {
			Map<String, Object> params = getParams();
			getPager().setPageSize(6);
			Pager pager = attendanceCategoryController.goSubSingleList(params, getPager());
			logger.info("获取列表数据页");
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
				attendanceCategory = attendanceCategoryController.doListAttendanceCategoryById(id);
				if (StringUtils.isNotEmpty(parentId) && !parentId.equals("0")) {
					attendanceCategory = attendanceCategoryController.doListAttendanceCategoryById(id);
					logger.info("");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 弹出选择所属分类 */
	public String goChooseSupplierCategory() {
		return "goChooseSupplierCategory";
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != attendanceCategory.getId()) {
				isSave = false;
			}
			// 设置供应商状态为为审批
			attendanceCategory.setStatus("S1");
			initEntityBaseController.initEntityBaseAttribute(attendanceCategory);
			if (null != parentId && !"".equals(parentId)) {
				AttendanceCategory sCategory = attendanceCategoryController.findAttendanceCategoryById(parentId);
				attendanceCategory.setAttendanceCategory(sCategory);
			}
			attendanceCategory = attendanceCategoryController.doSaveAttendanceCategory(attendanceCategory);
			logger.info("对订单进行了修改！");
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
			AttendanceCategory attendanceCategoryTemp = attendanceCategoryController.doListAttendanceCategoryById(id);
			if (null != attendanceCategoryTemp) {
				attendanceCategoryController.doDeleteByAttendanceCategory(attendanceCategoryTemp);
				logger.info("");
				setMessage(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
			logger.info("删除订单信息");
		} catch (Exception e) {
			e.printStackTrace();
			setMessage(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 计算最近使用日期 */
	public String getUpdateTime(String up) {
		String updateTime = null;
		Calendar calendar = Calendar.getInstance();
		if ("T1".equals(up)) {
			calendar.add(Calendar.DATE, -2);
		} else if ("T2".equals(up)) {
			calendar.add(Calendar.DATE, -6);
		} else if ("T3".equals(up)) {
			calendar.add(Calendar.DATE, -29);
		} else {
			calendar.add(Calendar.DATE, -89);
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		updateTime = dateFormat.format(calendar.getTime());
		return updateTime;
	}
}
