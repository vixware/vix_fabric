
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
import com.vix.common.security.util.DaysUtils;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.oa.personaloffice.controller.WorkLogController;
import com.vix.oa.personaloffice.entity.LogComment;
import com.vix.oa.personaloffice.entity.WorkLog;

/**
 * 
 * @ClassName: WorkLogAction
 * @Description: 工作日志
 * @author chenzhengwen
 * @author w_a_533@163.com
 * @date 2014-4-17 下午3:08:16
 */
@Controller
@Scope("prototype")
public class WorkLogAction extends BaseAction {

	private static final long serialVersionUID = 1L;
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
	private String onchangedate;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private List<WorkLog> workLogList;
	private List<LogComment> logCommentList;
	private WorkLog entity;

	/** 跳转到列表页面 */
	@Override
	public String goList() {

		return GO_LIST;
	}

	/** 日志列表 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			workLogList = workLogController.doListSalesOrderIndex();
			logCommentList = workLogController.doListSalesOrderIndex1();
			// 根据时间过滤工作日志
			if (onchangedate != null && !"".equals(onchangedate)) {
				params.put("logDate," + SearchCondition.BETWEENAND, DaysUtils.getBeginDay(dateFormat.parse(onchangedate)) + "!" + DaysUtils.getEndDay(dateFormat.parse(onchangedate)));
			} else {
				params.put("logDate," + SearchCondition.BETWEENAND, DaysUtils.getBeginDay(new Date()) + "!" + DaysUtils.getEndDay(new Date()));
			}
			// uploadPersonId包含当前登录人id
			String employeeId = SecurityUtil.getCurrentUserId();
			params.put("uploadPersonId," + SearchCondition.EQUAL, employeeId);

			Pager pager = workLogController.doSubSingleList(params, getPager());
			logger.info("获取工作日志列表数据");
			setPager(pager);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 新建日志 */
	public String goSingleList1() {
		try {
			Map<String, Object> params = getParams();
			workLogList = workLogController.doListSalesOrderIndex();
			logCommentList = workLogController.doListSalesOrderIndex1();
			// 根据时间过滤工作日志
			if (onchangedate != null && !"".equals(onchangedate)) {
				params.put("logDate," + SearchCondition.BETWEENAND, DaysUtils.getBeginDay(dateFormat.parse(onchangedate)) + "!" + DaysUtils.getEndDay(dateFormat.parse(onchangedate)));
			} else {
				params.put("logDate," + SearchCondition.BETWEENAND, DaysUtils.getBeginDay(new Date()) + "!" + DaysUtils.getEndDay(new Date()));
			}
			Pager pager = workLogController.doSubSingleList(params, getPager());
			logger.info("获取工作日志列表数据");
			setPager(pager);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return GO_SINGLE_LIST;
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
			// 内容
			String logContent = getRequestParameter("logContent");
			if (null != logContent && !"".equals(logContent)) {
				logContent = URLDecoder.decode(logContent, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("title," + SearchCondition.ANYLIKE, title);
				pager = workLogController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != logContent && !"".equals(logContent)) {
					params.put("logContent," + SearchCondition.ANYLIKE, logContent);
				}
				if (null != title && !"".equals(title)) {
					params.put("title," + SearchCondition.ANYLIKE, title);
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
			/** 索引 */
			String title = workLog.getTitle();
			String py = ChnToPinYin.getPYString(title);
			workLog.setChineseFirstLetter(py.toUpperCase());
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

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				logComment = workLogController.doListEntityById1(id);
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
			if (StringUtils.isNotEmpty(logComment.getId()) && !"".equals(logComment.getId())) {
				isSave = false;
			}

			initEntityBaseController.initEntityBaseAttribute(logComment);
			logComment = workLogController.doSaveSalesOrder1(logComment);
			/** 拿到当前用户 */
			this.logComment.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.logComment.setUploadPerson(SecurityUtil.getCurrentUserName());
			/** 拿到当前用户的姓名，保存 */
			logComment.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			this.saveBaseEntity(this.logComment);
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

	/**
	 * 获取到工作日志内容，获取内容时将阅读次数加一，保存阅读次数
	 * 
	 * @author chenzhengwen
	 * @date 2014-7-18上午10:38:12
	 * @return
	 */
	public String goSeenoticenotice() {
		try {
			this.workLog = baseHibernateService.findEntityById(WorkLog.class, id);
			System.out.println(workLog.getCommentsnumber() + "=====");
			if (workLog.getCommentsnumber() == null) {
				workLog.setCommentsnumber(1l);
			} else {
				workLog.setCommentsnumber(workLog.getCommentsnumber() + 1);
			}
			this.workLog = baseHibernateService.save(workLog);
			logger.info("获取评论人员列表数据");
			// setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSeenoticenotice";
	}

	/** 处理新增修改操作 */
	public String popNews() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(workLog.getId()) && !"".equals(workLog.getId())) {
				isSave = false;
			}
			workLog = workLogController.doSaveSalesOrder(workLog);
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
		return "popNews";
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

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public WorkLog getWorkLog() {
		return workLog;
	}

	public void setWorkLog(WorkLog workLog) {
		this.workLog = workLog;
	}

	public Integer getLogType() {
		return logType;
	}

	public void setLogType(Integer logType) {
		this.logType = logType;
	}

	public List<WorkLog> getWorkLogList() {
		return workLogList;
	}

	public void setWorkLogList(List<WorkLog> workLogList) {
		this.workLogList = workLogList;
	}

	public WorkLog getEntity() {
		return entity;
	}

	public void setEntity(WorkLog entity) {
		this.entity = entity;
	}

	public LogComment getLogComment() {
		return logComment;
	}

	public void setLogComment(LogComment logComment) {
		this.logComment = logComment;
	}

	public List<LogComment> getLogCommentList() {
		return logCommentList;
	}

	public void setLogCommentList(List<LogComment> logCommentList) {
		this.logCommentList = logCommentList;
	}

	public String getOnchangedate() {
		return onchangedate;
	}

	public void setOnchangedate(String onchangedate) {
		this.onchangedate = onchangedate;
	}

	public SimpleDateFormat getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(SimpleDateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}

}
