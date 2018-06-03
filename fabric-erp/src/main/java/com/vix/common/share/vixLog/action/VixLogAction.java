package com.vix.common.share.vixLog.action;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.share.vixLog.controller.VixLogController;
import com.vix.core.web.Pager;
import com.vix.system.latestOperate.entity.LatestOperateEntity;

@Controller
@Scope("prototype")
public class VixLogAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(VixLogAction.class);
	@Autowired
	private VixLogController vixLogController;
	private Long id;
	private String ids;
	private String pageNo;

	/**
	 * 操作日志
	 */
	private LatestOperateEntity latestOperateEntity;
	private List<LatestOperateEntity> latestOperateEntityList;

	@Override
	public String goList() {
		try {
			latestOperateEntityList = vixLogController.doListLatestOperateEntity();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/**
	 * 根据条件查询入库单信息
	 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			//倒序排序
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("id");
			pager = vixLogController.doListLatestOperateEntityPaper(params, pager);
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
