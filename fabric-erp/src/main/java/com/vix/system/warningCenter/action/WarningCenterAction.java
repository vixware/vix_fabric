package com.vix.system.warningCenter.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.id.VixUUID;
import com.vix.common.org.vo.OrgUnit;
import com.vix.common.security.util.SecurityUtil;
import com.vix.common.share.entity.AlertTask;
import com.vix.common.share.entity.Notice;
import com.vix.common.share.entity.TaskPlan;
import com.vix.core.web.Pager;
import com.vix.system.warningCenter.controller.WarningCenterController;
import com.vix.system.warningCenter.service.IWarningCenterService;
import com.vix.system.warningSource.entity.ModuleCategory;
import com.vix.system.warningSource.entity.WarningType;

@Controller
@Scope("prototype")
public class WarningCenterAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private WarningCenterController warningCenterController;
	@Autowired
	private IWarningCenterService warningCenterService;
	private String id;
	private String ids;
	private String pageNo;
	private String treeType;

	private Long parentId;
	/**
	 * 任务
	 */
	private AlertTask timedTask;
	/**
	 * 计划
	 */
	private TaskPlan taskPlan;
	/**
	 * 通知
	 */
	private Notice notice;

	private List<AlertTask> timedTaskList;

	/**
	 * 跳转到数据列表页面
	 */
	@Override
	public String goList() {
		try {
			timedTaskList = warningCenterController.doListTimedTaskIndex();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			// 过滤临时数据
			Pager pager = warningCenterController.doListTimedTask(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				timedTask = warningCenterController.doListEntityById(id);
			} else {
				timedTask = new AlertTask();
				if (parentId != null) {
					WarningType warningType = warningCenterController.doListWarningTypeById(parentId);
					if (warningType != null) {
						timedTask.setWarningType(warningType);
					}
				}
				timedTask.setCreator(SecurityUtil.getCurrentUserName());
				timedTask.setCode(VixUUID.createCode(10));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public String goSaveOrUpdateTaskPlan() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				taskPlan = warningCenterController.doListTaskPlanById(id);
			} else {
				taskPlan = new TaskPlan();
				taskPlan.setCode(VixUUID.createCode(10));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateTaskPlan";
	}

	public String goSaveOrUpdateNotice() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				notice = warningCenterController.doListNoticeById(id);
			} else {
				notice = new Notice();
				if (parentId != null) {
					WarningType warningType = warningCenterController.doListWarningTypeById(parentId);
					if (warningType != null) {
						notice.setWarningType(warningType);
					}
				}
				notice.setCode(VixUUID.createCode(10));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateNotice";
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != timedTask.getId()) {
				isSave = false;
			}
			timedTask = warningCenterController.doSaveOrUpdateTask(timedTask);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	public String saveOrUpdateTaskPlan() {
		boolean isSave = true;
		try {
			if (null != taskPlan.getId()) {
				isSave = false;
			}
			taskPlan = warningCenterController.doSaveOrUpdateTaskPlan(taskPlan);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	public String saveOrUpdateNotice() {
		boolean isSave = true;
		try {
			if (null != notice.getId()) {
				isSave = false;
			}
			notice = warningCenterController.doSaveOrUpdateNotice(notice);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			AlertTask timedTask = warningCenterController.doListEntityById(id);
			if (null != timedTask) {
				warningCenterController.doDeleteByEntity(timedTask);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteByIds() {
		try {
			if (null != ids && !"".equals(ids)) {
				List<String> delIds = new ArrayList<String>();
				for (String idStr : ids.split(",")) {
					if (null != idStr && !"".equals(idStr) && !"undefined".equals(idStr)) {
						delIds.add(idStr);
					}
				}
				warningCenterController.doDeleteByIds(delIds);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotChoose"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public void findModuleCategoryTreeToJson() {
		try {
			loadModuleCategory(id, treeType);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadModuleCategory(String nodeId, String nodeTreeType) {
		try {
			List<OrgUnit> orgUnitList = null;
			List<ModuleCategory> moduleCategoryList = null;
			if (null != nodeId && !"".equals(nodeId)) {
				if (StringUtils.isNotEmpty(nodeTreeType)) {
					if (nodeTreeType.equals("M") || nodeTreeType.equals("W")) {
						orgUnitList = warningCenterService.findOrgAndUnitTreeList(nodeTreeType, nodeId);
					}
				}
			} else {
				moduleCategoryList = warningCenterService.findAllByEntityClass(ModuleCategory.class);
			}

			if (orgUnitList == null) {
				orgUnitList = new LinkedList<OrgUnit>();
			}

			if (moduleCategoryList != null) {
				for (ModuleCategory moduleCategory : moduleCategoryList) {
					OrgUnit ou1 = new OrgUnit(moduleCategory.getId(), "M", moduleCategory.getCategoryName());
					if (moduleCategory.getWarningTypes().size() > 0) {
						List<OrgUnit> ou2List = new LinkedList<OrgUnit>();
						for (WarningType warningType : moduleCategory.getWarningTypes()) {
							ou2List.add(new OrgUnit(warningType.getId(), "W", warningType.getTypeName()));
						}
						ou1.setSubOrgUnits(ou2List);
					}
					orgUnitList.add(ou1);
				}
			}

			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			int count = orgUnitList.size();
			for (int i = 0; i < count; i++) {
				OrgUnit org = orgUnitList.get(i);
				if (org.getSubOrgUnits() != null && org.getSubOrgUnits().size() > 0) {
					strSb.append("{id:\"");
					strSb.append(org.getId());
					strSb.append("\",treeType:\"");
					strSb.append(org.getTreeType());
					strSb.append("\",name:\"");
					strSb.append(org.getOrgName());
					strSb.append("\",open:false,isParent:true}");
				} else {
					strSb.append("{id:\"");
					strSb.append(org.getId());
					strSb.append("\",treeType:\"");
					strSb.append(org.getTreeType());
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


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getTreeType() {
		return treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	public AlertTask getTimedTask() {
		return timedTask;
	}

	public void setTimedTask(AlertTask timedTask) {
		this.timedTask = timedTask;
	}

	public List<AlertTask> getTimedTaskList() {
		return timedTaskList;
	}

	public void setTimedTaskList(List<AlertTask> timedTaskList) {
		this.timedTaskList = timedTaskList;
	}

	public TaskPlan getTaskPlan() {
		return taskPlan;
	}

	public void setTaskPlan(TaskPlan taskPlan) {
		this.taskPlan = taskPlan;
	}

	public Notice getNotice() {
		return notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

}
