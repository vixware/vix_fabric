package com.vix.oa.bulletin.action;

import java.io.FileInputStream;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.org.service.IEmployeeHrService;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.oa.bulletin.controller.AnnouncementNotificationController;
import com.vix.oa.bulletin.entity.AccountStatements;
import com.vix.oa.bulletin.entity.AnnouncementNotification;
import com.vix.oa.bulletin.entity.NoticeUploader;
import com.vix.oa.bulletin.service.IAnnouncementNotificationService;

/**
 * 
 * @ClassName: AnnouncementNotificationAction
 * @Description: 行政办公——公告通知
 * @author chenzhengwen
 * @author w_a_533@163.com
 * @date 2014-4-3 下午1:25:45
 */
@Controller
@Scope("prototype")
public class AnnouncementNotificationAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Logger logger = Logger.getLogger(AnnouncementNotificationController.class);
	@Autowired
	private IAnnouncementNotificationService announcementNotificationService;
	@Autowired
	private AnnouncementNotificationController announcementNotificationController;
	@Autowired
	private IEmployeeHrService employeeHrService;
	private List<AccountStatements> accountStatementsList;
	private AnnouncementNotification announcementNotification;
	private NoticeUploader noticeUploader;
	private AccountStatements accountStatements;
	private String id;
	public Integer isPublish;
	private String pageNo;
	private Date updateTime;
	private String eqId;
	/** 公告通知管理 */
	private List<AnnouncementNotification> announcementNotificationList;

	/** 跳转到列表页面 */
	@Override
	public String goList() {
		try {
			announcementNotificationList = announcementNotificationController.doListSalesOrderIndex();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			/* params.put("votingType.code," + SearchCondition.ANYLIKE, "1"); */
			String bulletinType = getRequestParameter("bulletinType");
			if (null != bulletinType && !"".equals(bulletinType)) {
				params.put("bulletinType," + SearchCondition.EQUAL, Integer.parseInt(bulletinType));
			}
			// 按最近使用
			String activeStartDate = getRequestParameter("activeStartDate");
			if (activeStartDate != null && !"".equals(activeStartDate)) {
				params.put("activeStartDate," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(activeStartDate));
			}
			// uploadPersonId包含当前登录人id
			String employeeId = SecurityUtil.getCurrentUserId();
			params.put("uploadPersonId," + SearchCondition.EQUAL, employeeId);
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("updateTime");
				getPager().setOrderBy("desc");
			}
			Pager pager = announcementNotificationController.doSubSingleList(params, getPager());
			logger.info("获取公告通知列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSearch() {
		return "goSearch";
	}

	/** 获取公告通知搜索列表数据 */
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
			// 简介
			String plotSummary = getRequestParameter("plotSummary");
			if (null != plotSummary && !"".equals(plotSummary)) {
				plotSummary = URLDecoder.decode(plotSummary, "utf-8");
			}
			// 发布内容
			String content = getRequestParameter("content");
			if (null != content && !"".equals(content)) {
				content = URLDecoder.decode(content, "utf-8");
			}
			// 关键字
			String keywords = getRequestParameter("keywords");
			if (null != keywords && !"".equals(keywords)) {
				keywords = URLDecoder.decode(keywords, "utf-8");
			}
			// 发布人
			String uploadPersonName = getRequestParameter("uploadPersonName");
			if (null != uploadPersonName && !"".equals(uploadPersonName)) {
				uploadPersonName = URLDecoder.decode(uploadPersonName, "utf-8");
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
				pager = announcementNotificationController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != uploadPersonName && !"".equals(uploadPersonName)) {
					params.put("uploadPersonName," + SearchCondition.ANYLIKE, uploadPersonName);
				}
				if (null != title && !"".equals(title)) {
					params.put("title," + SearchCondition.ANYLIKE, title);
				}
				if (null != keywords && !"".equals(keywords)) {
					params.put("keywords," + SearchCondition.ANYLIKE, keywords);
				}
				if (null != content && !"".equals(content)) {
					params.put("content," + SearchCondition.ANYLIKE, content);
				}
				if (null != plotSummary && !"".equals(plotSummary)) {
					params.put("plotSummary," + SearchCondition.ANYLIKE, plotSummary);
				}
				if (null != pubNames && !"".equals(pubNames)) {
					params.put("pubNames," + SearchCondition.ANYLIKE, pubNames);
				}
				pager = announcementNotificationController.goSingleList(params, getPager());
			}
			logger.info("获取公告通知搜索列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至用户公告通知修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				announcementNotification = announcementNotificationController.doListEntityById(id);
				logger.info("");
			} else {
				announcementNotification = new AnnouncementNotification();
				announcementNotification.setIsTemp("1");
				announcementNotification = baseHibernateService.merge(announcementNotification);
			}
			Map<String, Object> params = getParams();
			params.put("announcementNotification.id," + SearchCondition.EQUAL, id);
			Pager pager = announcementNotificationController.doNoticeUploader(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			setMessage(e.getMessage());
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理新增公告通知修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != announcementNotification.getId()) {
				isSave = false;
			}
			/** 索引 */
			String title = announcementNotification.getTitle();
			String py = ChnToPinYin.getPYString(title);
			announcementNotification.setChineseFirstLetter(py.toUpperCase());
			initEntityBaseController.initEntityBaseAttribute(announcementNotification);
			announcementNotification = announcementNotificationController.doSaveSalesOrder(announcementNotification);
			this.announcementNotification.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.announcementNotification.setUploadPerson(SecurityUtil.getCurrentUserName());
			/* BaseEmployee edd = SecurityUtil.getCurrentRealUser(); */
			announcementNotification.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			announcementNotification = announcementNotificationService.merge(announcementNotification);

			List<Employee> employeeList = null;
			String pubType = announcementNotification.getPubType();
			String pubIdsString = announcementNotification.getPubIds();
			/**
			 * 根据职员id 得到职员信息
			 */
			if ("E".equalsIgnoreCase(pubType)) {
				String[] ids = pubIdsString.split(",");
				employeeList = new ArrayList<Employee>();
				for (String idstr : ids) {
					if (!"".equals(idstr)) {
						idstr = idstr.replace(",", "");
						employeeList.add(employeeHrService.findEntityById(Employee.class, idstr));
					}
				}
			}

			if ("D".equalsIgnoreCase(pubType)) {
				if (StrUtils.isEmpty(pubIdsString))
					return null;
				pubIdsString = pubIdsString.trim();
				if (pubIdsString.startsWith(","))
					pubIdsString = pubIdsString.substring(1);
				if (pubIdsString.endsWith(","))
					pubIdsString = pubIdsString.substring(0, pubIdsString.length() - 1);

				String[] ids = pubIdsString.split(",");
				employeeList = new ArrayList<Employee>();
				for (String idstr : ids) {
					if (StrUtils.isEmpty(idstr))
						continue;
					Map<String, Object> params = new HashMap<String, Object>();
					employeeList.add(employeeHrService.findEntityById(Employee.class, idstr));
				}
			}

			/**
			 * 根据部门id 得到职员信息
			 */
			else if ("O".equalsIgnoreCase(pubType)) {
				String[] ids = pubIdsString.split(",");
				employeeList = new ArrayList<Employee>();
				for (String idstr : ids) {
					if (StrUtils.isEmpty(idstr))
						continue;
					Map<String, Object> params = new HashMap<String, Object>();
					Pager pager = new Pager();
					idstr = idstr.substring(0, idstr.length() - 1);
					params.put("orgUnitId", idstr);
					pager.setPageSize(10000);
					pager = employeeHrService.findEmpByOrgUnitIdPager(pager, params);
					employeeList.addAll(pager.getResultList());
				}

			}

			if (employeeList != null && employeeList.size() > 0) {
				if (employeeList != null && employeeList.size() > 0) {
					for (Employee employee : employeeList) {
						if (employee != null) {
							AccountStatements accountStatements = new AccountStatements();
							accountStatements.setEmployee(employee);
							accountStatements.setUploadPersonName(employee.getName());
							accountStatements.setIsPublish(0);
							accountStatements.setAnnouncementNotification(announcementNotification);
							announcementNotificationService.merge(accountStatements);
						}
					}
				}
			}
			announcementNotification.setIsTemp("");
			this.saveBaseEntity(this.announcementNotification);
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
	 * 获取到查看公告通知的人员以及查看时间与查看标题
	 * 
	 * @return
	 */
	public String goViewers() {
		try {
			Map<String, Object> params = getParams();
			params.put("announcementNotification.id," + SearchCondition.EQUAL, id);
			Pager pager = announcementNotificationController.doaccountStatementsList(params, getPager());
			logger.info("获取查看人员列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goViewers";
	}

	public String viewers() {
		return "viewers";
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			AnnouncementNotification pb = announcementNotificationController.findEntityById(id);
			if (null != pb) {
				announcementNotificationController.doDeleteByEntity(pb);
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

	/**
	 * 对公告通知状态进行操作，根据业务需求选择是生效或终止
	 * 
	 * @return
	 */
	public void updateIsPublish() {
		try {
			AnnouncementNotification pb = announcementNotificationController.findEntityById(id);
			if (pb.getIsPublish() == 1) {
				isPublish = 0;
			} else if (pb.getIsPublish() == 0) {
				isPublish = 1;
			}
			pb.setIsPublish(isPublish);
			announcementNotification = announcementNotificationController.doSaveSalesOrder(pb);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String eqSbwdPager() {
		// 设备文档
		if (StringUtils.isNotEmpty(this.eqId)) {
			Map<String, Object> params = getParams();
			this.addTimeLimitToParam(params);
			params.put("uploader.id," + SearchCondition.EQUAL, this.eqId);

			Pager pager = this.getPager();
			pager.setOrderField("id");
			pager.setOrderBy("desc");

			// 在parms之后，覆盖相同项目
			this.addAdvFilterAndSort(params, pager);

			try {
				this.baseHibernateService.findPagerByHqlConditions(pager, NoticeUploader.class, params);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "eqSbwdPager";
	}

	public String eqSbwdEdit() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				noticeUploader = announcementNotificationController.doNoticeUploader(id);
				logger.info("");
			} else {
				noticeUploader = new NoticeUploader();
				// 将任务set到Uploader里边
				String announcementNotificationId = getRequestParameter("announcementNotificationId");
				announcementNotification = baseHibernateService.findEntityById(AnnouncementNotification.class, announcementNotificationId);
				noticeUploader.setAnnouncementNotification(announcementNotification);
			}
		} catch (Exception e) {
			setMessage(e.getMessage());
		}
		return "eqSbwdEdit";
	}

	public void saveEqSbwd() {
		String[] savePathAndName = this.saveUploadFile();
		if (savePathAndName != null && savePathAndName.length == 2) {
			this.noticeUploader.setFileName(savePathAndName[1]);
			this.noticeUploader.setFilePath(savePathAndName[0]);
		}

		this.noticeUploader.setUploadPersonId(SecurityUtil.getCurrentUserId());
		this.noticeUploader.setUploadPerson(SecurityUtil.getCurrentUserName());
		/** 拿到当前用户的姓名，保存 */
		noticeUploader.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
		this.noticeUploader.setUploadTime(new Date());
		this.saveBaseEntity(this.noticeUploader);
		try {
			noticeUploader = this.baseHibernateService.merge(noticeUploader);
			renderText(String.valueOf(noticeUploader.getAnnouncementNotification().getId()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String downloadEqDocument() {
		if (StringUtils.isNotEmpty(this.id)) {
			try {
				NoticeUploader doc = baseHibernateService.findEntityById(NoticeUploader.class, this.id);
				String fileName = doc.getFileName();
				String filePath = doc.getFilePath();
				String title = doc.getTitle();
				int idx = fileName.lastIndexOf(".");
				if (idx != -1) {
					title = title + fileName.substring(idx);
				}

				this.setOriFileName(title);

				String downloadFile = filePath + fileName;
				this.setInputStream(new FileInputStream(downloadFile));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			return NONE;
		}
		return "downloadEqDocument";
	}

	public String goChooseOrganization() {
		return "goChooseOrganization";
	}

	public String getId() {
		return id;
	}

	public Integer getIsPublish() {
		return isPublish;
	}

	public void setIsPublish(Integer isPublish) {
		this.isPublish = isPublish;
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

	public IAnnouncementNotificationService getAnnouncementNotificationService() {
		return announcementNotificationService;
	}

	public void setAnnouncementNotificationService(IAnnouncementNotificationService announcementNotificationService) {
		this.announcementNotificationService = announcementNotificationService;
	}

	public AnnouncementNotificationController getAnnouncementNotificationController() {
		return announcementNotificationController;
	}

	public void setAnnouncementNotificationController(AnnouncementNotificationController announcementNotificationController) {
		this.announcementNotificationController = announcementNotificationController;
	}

	public AnnouncementNotification getAnnouncementNotification() {
		return announcementNotification;
	}

	public void setAnnouncementNotification(AnnouncementNotification announcementNotification) {
		this.announcementNotification = announcementNotification;
	}

	public List<AnnouncementNotification> getAnnouncementNotificationList() {
		return announcementNotificationList;
	}

	public void setAnnouncementNotificationList(List<AnnouncementNotification> announcementNotificationList) {
		this.announcementNotificationList = announcementNotificationList;
	}

	public AccountStatements getAccountStatements() {
		return accountStatements;
	}

	public void setAccountStatements(AccountStatements accountStatements) {
		this.accountStatements = accountStatements;
	}

	public List<AccountStatements> getAccountStatementsList() {
		return accountStatementsList;
	}

	public void setAccountStatementsList(List<AccountStatements> accountStatementsList) {
		this.accountStatementsList = accountStatementsList;
	}

	public NoticeUploader getNoticeUploader() {
		return noticeUploader;
	}

	public void setNoticeUploader(NoticeUploader noticeUploader) {
		this.noticeUploader = noticeUploader;
	}

	public String getEqId() {
		return eqId;
	}

	public void setEqId(String eqId) {
		this.eqId = eqId;
	}

}
