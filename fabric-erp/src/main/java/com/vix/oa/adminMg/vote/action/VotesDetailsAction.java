package com.vix.oa.adminMg.vote.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.oa.adminMg.vote.controller.VotingManagementController;
import com.vix.oa.adminMg.vote.entity.VotingManagement;

/**
 * 
 * @ClassName: VotesDetailsAction
 * @Description: 行政办公——投票管理——子投票  
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-7-30 下午5:00:13
 */
@Controller
@Scope("prototype")
public class VotesDetailsAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(VotingManagementController.class);
	@Autowired
	private VotingManagementController votingManagementController;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	private VotingManagement votingManagement;
	private VotingManagement entity;
	private String id;
	public Integer publishType;
	private String pageNo;
	private Date updateTime;
	/** 行政办公-投票管理*/
	private List<VotingManagement> votingManagementList;	
	
	/** 跳转到列表页面 */
	@Override
	public String goList() {
		return GO_LIST;
	}
	
	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			params.put("publishType," + SearchCondition.EQUAL, 1);
			//状态
			String publishType = getRequestParameter("publishType");
			if (null != publishType && !"".equals(publishType)) {
				params.put("publishType," + SearchCondition.EQUAL, Integer.parseInt(publishType));
			}			
			// 按最近使用
			String startTime = getRequestParameter("startTime");
			if (startTime != null && !"".equals(startTime)) {
				params.put("startTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(startTime));
			}			
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("startTime");
				getPager().setOrderBy("desc");
			}
			Pager pager = votingManagementController.doSubSingleList(params,getPager());
			logger.info("获取投票列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	/** 跳转至项目投票页面 */
	public String projectVote() {
		return "projectVote";
	}
	
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate(){
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				votingManagement = votingManagementController.doListEntityById(id);
				logger.info("");
			}
		} catch (Exception e) {
			setMessage(e.getMessage());
		}
		return GO_SAVE_OR_UPDATE;
	}
	
	/** 处理新增修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(votingManagement.getId()) && !"".equals(votingManagement.getId())) {
				isSave = false;
			}

			initEntityBaseController.initEntityBaseAttribute(votingManagement);
			votingManagement = votingManagementController.doSaveSalesOrder(votingManagement);
			this.votingManagement.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.votingManagement.setUploadPerson(SecurityUtil.getCurrentUserName());
			votingManagement.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			this.saveBaseEntity(this.votingManagement);
			logger.info("新增！");
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}
	
	public String goChooseOrganization(){
		return "goChooseOrganization";
	}
	
	public String goProjectVote(){
		return "goProjectVote";
	}
	
	/** 处理删除操作 */
	public String deleteById() {
		try {
			VotingManagement pb = votingManagementController.findEntityById(id);
			if (null != pb) {
				votingManagementController.doDeleteByEntity(pb);
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

	public VotingManagement getVotingManagement() {
		return votingManagement;
	}

	public void setVotingManagement(VotingManagement votingManagement) {
		this.votingManagement = votingManagement;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public List<VotingManagement> getVotingManagementList() {
		return votingManagementList;
	}

	public void setVotingManagementList(List<VotingManagement> votingManagementList) {
		this.votingManagementList = votingManagementList;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public VotingManagement getEntity() {
		return entity;
	}

	public void setEntity(VotingManagement entity) {
		this.entity = entity;
	}


	public Integer getPublishType() {
		return publishType;
	}


	public void setPublishType(Integer publishType) {
		this.publishType = publishType;
	}

}
