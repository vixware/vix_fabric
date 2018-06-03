package com.vix.oa.personaloffice.action;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
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
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.oa.adminMg.typeSettings.entity.VoteType;
import com.vix.oa.adminMg.typeSettings.entity.VotingType;
import com.vix.oa.adminMg.vote.controller.VotingManagementController;
import com.vix.oa.adminMg.vote.entity.VotingManagement;
import com.vix.oa.task.taskDefinition.controller.TaskDefineController;

/**
 * 
 * @ClassName: VoTeAction
 * @Description: 个人办公——投票管理 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-7-30 下午3:24:43
 */
@Controller
@Scope("prototype")
public class VoTeAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Logger logger = Logger.getLogger(TaskDefineController.class);
	
	@Autowired
	private VotingManagementController votingManagementController;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	
	private VotingManagement votingManagement;
	
	private String id;
	
	private String pageNo;
	
	private Date updateTime;
	
	/** 行政办公-投票管理*/
	private List<VotingManagement> votingManagementList;
	
	/** 投票类型 */
	private List<VotingType> votingTypeList;
	
	/** 投票查看类型 */
	private List<VoteType> voteTypeList;
	
	
	
	/** 跳转到列表页面 */
	@Override
	public String goList() {
		try {
			votingManagementList = votingManagementController.doListSalesOrderIndex();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
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
	
	public String goSearch() {
		return "goSearch";
	}

	/** 获取投票搜索列表数据 */
	public String goSearchList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = null;
			String i = getRequestParameter("i");
			// 主题
			String title = getRequestParameter("title");
			if (null != title && !"".equals(title)) {
				title = URLDecoder.decode(title, "utf-8");
			}
			// 发布人
			String uploadPersonName = getRequestParameter("uploadPersonName");
			if (null != uploadPersonName && !"".equals(uploadPersonName)) {
				uploadPersonName = URLDecoder.decode(uploadPersonName, "utf-8");
			}
			// 描述
			String voteDescribe = getRequestParameter("voteDescribe");
			if (null != voteDescribe && !"".equals(voteDescribe)) {
				voteDescribe = URLDecoder.decode(voteDescribe, "utf-8");
			}
			// 备注
			String remarks = getRequestParameter("remarks");
			if (null != remarks && !"".equals(remarks)) {
				remarks = URLDecoder.decode(remarks, "utf-8");
			}
			// 发布范围
			String pubNames = getRequestParameter("pubNames");
			if (null != pubNames && !"".equals(pubNames)) {
				pubNames = URLDecoder.decode(pubNames, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("title," + SearchCondition.ANYLIKE, title);
				pager = votingManagementController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != uploadPersonName && !"".equals(uploadPersonName)) {
					params.put("uploadPersonName," + SearchCondition.ANYLIKE, uploadPersonName);
				}
				if (null != title && !"".equals(title)) {
					params.put("title," + SearchCondition.ANYLIKE, title);
				}
				if (null != voteDescribe && !"".equals(voteDescribe)) {
					params.put("voteDescribe," + SearchCondition.ANYLIKE, voteDescribe);
				}
				if (null != remarks && !"".equals(remarks)) {
					params.put("remarks," + SearchCondition.ANYLIKE, remarks);
				}
				if (null != pubNames && !"".equals(pubNames)) {
					params.put("pubNames," + SearchCondition.ANYLIKE, pubNames);
				}
				pager = votingManagementController.goSingleList(params, getPager());
			}
			logger.info("获取投票搜索列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate(){
		try {
			votingTypeList = baseHibernateService.findAllByEntityClass(VotingType.class);
			voteTypeList = baseHibernateService.findAllByEntityClass(VoteType.class);
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
			/**索引 */
			String title = votingManagement.getTitle();
			String py = ChnToPinYin.getPYString(title);
			votingManagement.setChineseFirstLetter(py.toUpperCase());
			
			initEntityBaseController.initEntityBaseAttribute(votingManagement);
			votingManagement = votingManagementController.doSaveSalesOrder(votingManagement);
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
	
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate1(){
		try {
			votingTypeList = baseHibernateService.findAllByEntityClass(VotingType.class);
			voteTypeList = baseHibernateService.findAllByEntityClass(VoteType.class);
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
	public String saveOrUpdate1() {
		boolean isSave = true;
		try {
			votingTypeList = baseHibernateService.findAllByEntityClass(VotingType.class);
			voteTypeList = baseHibernateService.findAllByEntityClass(VoteType.class);
			if (StringUtils.isNotEmpty(votingManagement.getId()) && !"".equals(votingManagement.getId())) {
				isSave = false;
			}

			initEntityBaseController.initEntityBaseAttribute(votingManagement);
			votingManagement = votingManagementController.doSaveSalesOrder(votingManagement);
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

	public List<VotingType> getVotingTypeList() {
		return votingTypeList;
	}

	public void setVotingTypeList(List<VotingType> votingTypeList) {
		this.votingTypeList = votingTypeList;
	}

	public List<VoteType> getVoteTypeList() {
		return voteTypeList;
	}

	public void setVoteTypeList(List<VoteType> voteTypeList) {
		this.voteTypeList = voteTypeList;
	}

}
