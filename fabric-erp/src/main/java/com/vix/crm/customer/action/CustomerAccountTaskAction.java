package com.vix.crm.customer.action;

import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.oa.task.taskDefinition.entity.VixTask;

/** 代办任务 */
@Controller
@Scope("prototype")
public class CustomerAccountTaskAction extends BaseAction {
	private static final long serialVersionUID = 1L;


	private String id;
	private VixTask task;

	/** 获取列表数据 */
	public String goListContent() {
		try {
			Pager pager = getPager();
			if (null != id && !"".equals(id)) {
				List<VixTask> pcList = baseHibernateService.findAllByEntityClassAndAttribute(VixTask.class, "customerAccount.id", id);
				pager.setResultList(pcList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (null != id && !"".equals(id)) {
				task = baseHibernateService.findEntityById(VixTask.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != task.getId()) {
				isSave = false;
			} else {
				task.setCreateTime(new Date());
				loadCommonData(task);
			}
			if (null == task.getCustomerAccount().getId() || null == task.getCustomerAccount().getId()) {
				task.setCustomerAccount(null);
			}
			task = baseHibernateService.merge(task);
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
			VixTask pb = baseHibernateService.findEntityById(VixTask.class, id);
			if (null != pb) {
				baseHibernateService.deleteByEntity(pb);
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the task
	 */
	public VixTask getTask() {
		return task;
	}

	/**
	 * @param task
	 *            the task to set
	 */
	public void setTask(VixTask task) {
		this.task = task;
	}

}
