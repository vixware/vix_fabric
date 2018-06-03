package com.vix.system.latestOperate.action;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.system.latestOperate.controller.OperateLog;
import com.vix.system.latestOperate.entity.LatestOperateEntity;

@Controller
@Scope("prototype")
public class LatestOperateAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private OperateLog latestOperateController;
	private Long id;
	private LatestOperateEntity latestOperateEntity;
	private List<LatestOperateEntity> latestOperateEntityList;

	@Override
	public String goList() {
		try {
			latestOperateEntityList = latestOperateController.doListLatestOperateEntity();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = latestOperateController.findPagerByHqlConditions(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LatestOperateEntity getLatestOperateEntity() {
		return latestOperateEntity;
	}

	public void setLatestOperateEntity(LatestOperateEntity latestOperateEntity) {
		this.latestOperateEntity = latestOperateEntity;
	}

	public List<LatestOperateEntity> getLatestOperateEntityList() {
		return latestOperateEntityList;
	}

	public void setLatestOperateEntityList(List<LatestOperateEntity> latestOperateEntityList) {
		this.latestOperateEntityList = latestOperateEntityList;
	}

}
