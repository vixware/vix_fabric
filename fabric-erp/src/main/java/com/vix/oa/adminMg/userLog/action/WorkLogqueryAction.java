package com.vix.oa.adminMg.userLog.action;

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
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.oa.personaloffice.controller.WorkLogController;
import com.vix.oa.personaloffice.entity.LogComment;
import com.vix.oa.personaloffice.entity.WorkLog;

/**
 * 
 * @ClassName: WorkLogqueryAction
 * @Description: 工作日志查询
 * @author chenzhengwen
 * @author w_a_533@163.com
 * @date 2014-4-22 上午10:47:27
 */
@Controller
@Scope("prototype")
public class WorkLogqueryAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Logger logger = Logger.getLogger(WorkLogController.class);
	@Autowired
	private WorkLogController workLogController;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	private WorkLog workLog;
	private LogComment logComment;
	private String id;
	public Integer logType;
	private String pageNo;
	private Date updateTime;
	private String logContent;
	/** 工作日志 */
	private List<WorkLog> workLogList;
	private List<LogComment> logCommentList;
	private WorkLog entity;

	/** 跳转到列表页面 */
	@Override
	public String goList() {
		try {
			workLogList = workLogController.doListSalesOrderIndex();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			// 类型
			String logType = getRequestParameter("logType");
			if (null != logType && !"".equals(logType)) {
				params.put("logType," + SearchCondition.EQUAL, Integer.parseInt(logType));
			}
			// 按最近使用
			String logDate = getRequestParameter("logDate");
			if (logDate != null && !"".equals(logDate)) {
				params.put("logDate," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(logDate));
			}
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("logDate");
				getPager().setOrderBy("desc");
			}
			Pager pager = workLogController.doSubSingleList(params, getPager());

			logger.info("获取日志列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 截取20个字 */
	public String cutLogContent(String log, int len) {
		if (len == 0)
			len = 20;
		if (null != log && log.length() > len) {
			return log.substring(0, len);
		} else {
			return log;
		}
	}

	public String goSearch() {
		return "goSearch";
	}

	/** 获取工作日志搜索列表数据 */
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
			// 发布内容
			String logContent = getRequestParameter("logContent");
			if (null != logContent && !"".equals(logContent)) {
				logContent = URLDecoder.decode(logContent, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("uploadPersonName," + SearchCondition.ANYLIKE, uploadPersonName);
				pager = workLogController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != title && !"".equals(title)) {
					params.put("title," + SearchCondition.ANYLIKE, title);
				}
				if (null != uploadPersonName && !"".equals(uploadPersonName)) {
					params.put("uploadPersonName," + SearchCondition.ANYLIKE, uploadPersonName);
				}
				if (null != logContent && !"".equals(logContent)) {
					params.put("logContent," + SearchCondition.ANYLIKE, logContent);
				}
				pager = workLogController.goSingleList(params, getPager());
			}
			logger.info("获取工作日志搜索列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrWorkLong() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				workLog = workLogController.doListEntityById(id);
				logger.info("");
			}
		} catch (Exception e) {
			setMessage(e.getMessage());
		}
		return "goSaveOrWorkLong";
	}

	/** 处理新增修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(workLog.getId()) && !"".equals(workLog.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(workLog);
			workLog = workLogController.doSaveSalesOrder(workLog);
			/** 拿到当前用户 */
			this.workLog.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.workLog.setUploadPerson(SecurityUtil.getCurrentUserName());
			/** 拿到当前用户的姓名，保存 */
			workLog.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			this.saveBaseEntity(this.workLog);
			logger.info("新增！");
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

	/** 获取工作日志 */
	public String goLookworkLog() {
		try {
			this.workLog = baseHibernateService.findEntityById(WorkLog.class, id);
			logger.info("获取工作日志列表数据");
			// setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goLookworkLog";
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			WorkLog pb = workLogController.findEntityById(id);
			if (null != pb) {
				workLogController.doDeleteByEntity(pb);
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

	public WorkLogController getWorkLogController() {
		return workLogController;
	}

	public void setWorkLogController(WorkLogController workLogController) {
		this.workLogController = workLogController;
	}

	public InitEntityBaseController getInitEntityBaseController() {
		return initEntityBaseController;
	}

	public void setInitEntityBaseController(InitEntityBaseController initEntityBaseController) {
		this.initEntityBaseController = initEntityBaseController;
	}

	public WorkLog getWorkLog() {
		return workLog;
	}

	public void setWorkLog(WorkLog workLog) {
		this.workLog = workLog;
	}

	public LogComment getLogComment() {
		return logComment;
	}

	public void setLogComment(LogComment logComment) {
		this.logComment = logComment;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getLogType() {
		return logType;
	}

	public void setLogType(Integer logType) {
		this.logType = logType;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public List<WorkLog> getWorkLogList() {
		return workLogList;
	}

	public void setWorkLogList(List<WorkLog> workLogList) {
		this.workLogList = workLogList;
	}

	public List<LogComment> getLogCommentList() {
		return logCommentList;
	}

	public void setLogCommentList(List<LogComment> logCommentList) {
		this.logCommentList = logCommentList;
	}

	public WorkLog getEntity() {
		return entity;
	}

	public void setEntity(WorkLog entity) {
		this.entity = entity;
	}

	public String getLogContent() {
		return logContent;
	}

	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}

}
