package com.vix.nvix.task.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.id.VixUUID;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.oa.task.typeSettings.entity.TaskType;

/**
 * 任务类型设置
 * 
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class WxpTaskTypeAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	private String id;
	private TaskType taskType;


	/** 获取列表数据 */
	public void goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			String searchName = getDecodeRequestParameter("searchName");
			if (StringUtils.isNotEmpty(searchName)) {
				params.put("name," + SearchCondition.ANYLIKE, searchName);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, TaskType.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				taskType = vixntBaseService.findEntityById(TaskType.class, id);
			}else {
				taskType=new TaskType();
				taskType.setCode(VixUUID.getUUID());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if(StrUtils.isNotEmpty(taskType.getId())){
				isSave = false;
				taskType.setUpdateTime(new Date());
			}else{
				taskType.setCreateTime(new Date());
				taskType.setUpdateTime(new Date());
			}
			taskType = vixntBaseService.merge(taskType);
			if("1".equals(taskType.getIsDefault())){
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("isDefault," + SearchCondition.EQUAL, "1");
				params.put("id," + SearchCondition.NOEQUAL, taskType.getId());
				List<TaskType> taskTypes = vixntBaseService.findAllDataByConditions(TaskType.class, params);
				if(taskTypes != null && taskTypes.size() > 0){
					for (TaskType ht : taskTypes) {
						ht.setIsDefault("0");
						ht = vixntBaseService.merge(ht);
					}
				}
			}
			if(isSave){
				renderText(SAVE_SUCCESS);
			}else{
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(isSave){
				renderText(SAVE_FAIL);
			}else{
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}
	public void deleteTaskTypeById() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				taskType = vixntBaseService.findEntityById(TaskType.class, id);
				if (null != taskType) {
					vixntBaseService.deleteByEntity(taskType);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			//e.printStackTrace();
			renderText("热度等级已使用,不可删除");
		}
	}
	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public TaskType getTaskType() {
		return taskType;
	}

	public void setTaskType(TaskType taskType) {
		this.taskType = taskType;
	}

}
