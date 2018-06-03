package com.vix.oa.infoCenter.action;
import java.io.FileInputStream;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.oa.infoCenter.controller.MessageManagementController;
import com.vix.oa.infoCenter.entity.ManagementUploader;
import com.vix.oa.infoCenter.entity.MessageManagement;
import com.vix.oa.infoCenter.entity.NewsComment;

@Controller
@Scope("prototype")
public class MessageManagementAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(MessageManagementController.class);

	@Autowired
	private MessageManagementController messageManagementController;
	private List<MessageManagement> messageManagementList;
	private MessageManagement messageManagement;
	private NewsComment newsComment;
	private String id;
	private String pageNo;
	private String eqId;
	private ManagementUploader managementUploader;
	
	/** 获取消息管理列表数据 */
	@Override
	public String goList() {
			
		return GO_LIST;
	}
	
	
	public String goSingleList() {
		try {
			messageManagementList = baseHibernateService.findAllByEntityClass(MessageManagement.class);
			Map<String, Object> params = getParams();
			String code = getRequestParameter("code");
			if (null != code && !"".equals(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
			}	
			//pubids包含当前登录人id
			String employeeId = SecurityUtil.getCurrentEmpId();
			params.put("pubIds," + SearchCondition.ANYLIKE, ","+employeeId+",");
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("createTime");
				getPager().setOrderBy("desc");
			}
			Pager pager = messageManagementController.doSubSingleList(params,getPager());
			logger.info("获取消息列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	public String goSingleList1() {
		try {
			Map<String, Object> params = getParams();
			String code = getRequestParameter("code");
			if (null != code && !"".equals(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
			}			
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("createTime");
				getPager().setOrderBy("desc");
			}
			Pager pager = messageManagementController.doSubSingleList(params,getPager());
			logger.info("获取消息列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	public String goSingleList2() {
		try {
			Map<String, Object> params = getParams();
			String code = getRequestParameter("code");
			if (null != code && !"".equals(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
			}			
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("createTime");
				getPager().setOrderBy("desc");
			}
			Pager pager = messageManagementController.doSubSingleList(params,getPager());
			logger.info("获取任消息列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	public String goSearch() {
		return "goSearch";
	}
	
	/** 获取消息管理搜索列表数据 */
	public String goSearchList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = null;
			String i = getRequestParameter("i");
			//收信人
			String senderPeople = getRequestParameter("senderPeople");
			if (null != senderPeople && !"".equals(senderPeople)) {
				senderPeople = URLDecoder.decode(senderPeople, "utf-8");
			}
			//抄送人
			String ccPeople = getRequestParameter("ccPeople");
			if (null != ccPeople && !"".equals(ccPeople)) {
				ccPeople = URLDecoder.decode(ccPeople, "utf-8");
			}
			//抄送人1
			String ccPeople1 = getRequestParameter("ccPeople1");
			if (null != ccPeople1 && !"".equals(ccPeople1)) {
				ccPeople1 = URLDecoder.decode(ccPeople1, "utf-8");
			}
			// 内容
			String newscontent = getRequestParameter("newscontent");
			if (null != newscontent && !"".equals(newscontent)) {
				newscontent = URLDecoder.decode(newscontent, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("senderPeople," + SearchCondition.ANYLIKE, senderPeople);
				pager = messageManagementController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != senderPeople && !"".equals(senderPeople)) {
					params.put("senderPeople," + SearchCondition.ANYLIKE, senderPeople);
				}
				if (null != ccPeople && !"".equals(ccPeople)) {
					params.put("ccPeople," + SearchCondition.ANYLIKE, ccPeople);
				}
				if (null != ccPeople1 && !"".equals(ccPeople1)) {
					params.put("ccPeople1," + SearchCondition.ANYLIKE, ccPeople1);
				}
				if (null != newscontent && !"".equals(newscontent)) {
					params.put("newscontent," + SearchCondition.ANYLIKE, newscontent);
				}
				pager = messageManagementController.goList(params, getPager());
			}
			logger.info("获取消息管理搜索列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	/** 获取发送消息页面 */
	public String goMessagingList() {
		return "goMessagingList";
	}
	
	/** 处理发送操作并同时保存当前发送人 */
	public String saveMessaging() {
		boolean isSave = true;
		try {
			if (messageManagement != null && null != messageManagement.getId()) {
				isSave = false;
			}
			messageManagement.setCreateTime(new Date());
			this.saveBaseEntity(messageManagement);
			messageManagement = messageManagementController.doSaveSalesOrder(messageManagement);
			this.messageManagement.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.messageManagement.setUploadPerson(SecurityUtil.getCurrentUserName());
			/**拿到当前用户的姓名，保存*/
			messageManagement.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			this.messageManagement.setIsTop(0);
			this.saveBaseEntity(this.messageManagement);
			logger.info("对消息进行保存！");
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
	
	
	/** 获取未发送消息列表页面 */
	public String goSendMessages() {
		try {
			Map<String, Object> params = getParams();
			params.put("isTop," + SearchCondition.EQUAL, 0);
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("sendDate");
				getPager().setOrderBy("desc");
			}
			Pager pager = messageManagementController.doSubSingleList(params,getPager());
			logger.info("获未发送消息列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSendMessages";
	}
	
	/** 处理删除发送未发送消息操作 */
	public String deleteById() {
		try {
			MessageManagement pb = messageManagementController.findEntityById(id);
			if (null != pb) {
				messageManagementController.doDeleteByEntity(pb);
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
	
	/** 处理删除发送未发送消息操作因为页面的问题需要两种不同的处理方式 */
	public String deleteById1() {
		try {
			MessageManagement pb = messageManagementController.findEntityById(id);
			if (null != pb) {
				messageManagementController.doDeleteByEntity(pb);
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
	
	
	/** 处理删除附件操作 */
	public String deleteUploader() {
		try {
			ManagementUploader pb = messageManagementController.findUploaderById(id);
			if (null != pb) {
				messageManagementController.doDeleteByUploader(pb);
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
	
	/** 获取附件列表页面 */
	public String goUploader() {
		try {
			Map<String, Object> params = getParams();
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("uploadTime");
				getPager().setOrderBy("desc");
			}
			Pager pager = messageManagementController.doManagementUploader(params,getPager());
			logger.info("获附件列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goUploader";
	}
	
	
	/** 获取未发送消息列表页面 */
	public String goUnsentMessages() {
		try {
			Map<String, Object> params = getParams();
			params.put("isTop," + SearchCondition.EQUAL, 1);
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("sendDate");
				getPager().setOrderBy("desc");
			}
			Pager pager = messageManagementController.doSubSingleList(params,getPager());
			logger.info("获未发送消息列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goUnsentMessages";
	}
	
	/** 跳转至用草稿箱发送页面 */
	public String goSaveOrUpdate(){
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				messageManagement = messageManagementController.doListEntityById(id);
				logger.info("");
			}
		} catch (Exception e) {
			setMessage(e.getMessage());
		}
		return GO_SAVE_OR_UPDATE;
	}
	
	/** 处理未发送消息操作 */
	public String saveUnsentMessages() {
		boolean isSave = true;
		try {
			if (messageManagement != null && null != messageManagement.getId()) {
				isSave = false;
			}
			messageManagement.setCreateTime(new Date());
			this.saveBaseEntity(messageManagement);
			messageManagement = messageManagementController.doSaveSalesOrder(messageManagement);
			this.messageManagement.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.messageManagement.setUploadPerson(SecurityUtil.getCurrentUserName());
			/**拿到当前用户的姓名，保存*/
			messageManagement.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			this.messageManagement.setIsTop(1);
			this.saveBaseEntity(this.messageManagement);
			logger.info("保存草稿箱！");
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
	
	/** 跳转至消息评论页面 */
	public String goCommentMessage(){
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				newsComment = messageManagementController.doNewsCommentById(id);
				logger.info("");
			}
		} catch (Exception e) {
			setMessage(e.getMessage());
		}
		return "goCommentMessage";
	}
	
	/** 处理消息评论操作 */
	public String saveCommentMessage() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(newsComment.getId()) && !"".equals(newsComment.getId())) {
				isSave = false;
			}
			
			initEntityBaseController.initEntityBaseAttribute(newsComment);
			newsComment = messageManagementController.doSaveNewsComment(newsComment);
			/**拿到当前用户*/
			newsComment.setUploadPersonId(SecurityUtil.getCurrentUserId());
			newsComment.setUploadPerson(SecurityUtil.getCurrentUserName());
			/**拿到当前用户的姓名，保存*/
			newsComment.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			saveBaseEntity(newsComment);
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
	
	/**获取消息与评论*/
	public String goLooknewsComment() {
		try {
			messageManagement = baseHibernateService.findEntityById(MessageManagement.class, id);
			logger.info("获取消息与评论数据");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goLooknewsComment";
	}	
	
	/** 消息与评论操作 */
	public String looknewsComment() {
		return "looknewsComment";
	}
	
	public String eqSbwdPager()
	{
		//设备文档
		if(StringUtils.isNotEmpty(eqId))
		{
			Map<String,Object> params = getParams();
			this.addTimeLimitToParam(params);
			params.put("uploader.id,"+SearchCondition.EQUAL, this.eqId);
			
			Pager pager = this.getPager();
			pager.setOrderField("id");
			pager.setOrderBy("desc");

			//在parms之后，覆盖相同项目
			this.addAdvFilterAndSort(params, pager);

			try{
				this.baseHibernateService.findPagerByHqlConditions(pager, ManagementUploader.class, params);
			}catch(Exception e){
				e.printStackTrace();
			}	
		}
		return "eqSbwdPager";
	}
	
	
	public String eqSbwdEdit(){
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				managementUploader = messageManagementController.doNoticeUploader(id);
				logger.info("");
			}else {
				managementUploader = new ManagementUploader();
				//将任务set到Uploader里边
				String messageManagementId = getRequestParameter("messageManagementId");
				messageManagement = baseHibernateService.findEntityById(MessageManagement.class, messageManagementId);
				managementUploader.setMessageManagement(messageManagement);
			}
		} catch (Exception e) {
			setMessage(e.getMessage());
		}
		return "eqSbwdEdit";
	}
	
	public void saveEqSbwd()
	{
		String[] savePathAndName = this.saveUploadFile();
		if(savePathAndName!=null && savePathAndName.length==2)
		{
			this.managementUploader.setFileName(savePathAndName[1]);
			this.managementUploader.setFilePath(savePathAndName[0]);
		}

		this.managementUploader.setUploadPersonId(SecurityUtil.getCurrentUserId());
		this.managementUploader.setUploadPerson(SecurityUtil.getCurrentUserName());
		/**拿到当前用户的姓名，保存*/
		managementUploader.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
		this.managementUploader.setUploadTime(new Date());
		this.saveBaseEntity(this.managementUploader);
		try {
			managementUploader=this.baseHibernateService.merge(managementUploader);
			/*this.renderText(String.valueOf(managementUploader.getMessageManagement().getId()));*/
			renderText(String.valueOf(managementUploader.getMessageManagement().getId()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public String downloadEqDocument()
	{
		if(StringUtils.isNotEmpty(id)){
			try{
				ManagementUploader doc = baseHibernateService.findEntityById(ManagementUploader.class, this.id);
				String fileName = doc.getFileName();
				String filePath = doc.getFilePath();
				String title = doc.getTitle();
				int idx = fileName.lastIndexOf(".");
				if(idx!=-1)
				{
					title = title + fileName.substring(idx);
				}
				
				this.setOriFileName(title);

				String downloadFile = filePath + fileName;
		        this.setInputStream(new FileInputStream(downloadFile));
			}catch(Exception e){
				e.printStackTrace();
			}
		}else{
			return NONE;
		}
		return "downloadEqDocument";
	}	
	
	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public MessageManagementController getMessageManagementController() {
		return messageManagementController;
	}

	public void setMessageManagementController(
			MessageManagementController messageManagementController) {
		this.messageManagementController = messageManagementController;
	}

	public List<MessageManagement> getMessageManagementList() {
		return messageManagementList;
	}

	public void setMessageManagementList(
			List<MessageManagement> messageManagementList) {
		this.messageManagementList = messageManagementList;
	}

	public MessageManagement getMessageManagement() {
		return messageManagement;
	}

	public void setMessageManagement(MessageManagement messageManagement) {
		this.messageManagement = messageManagement;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}



	public String getEqId() {
		return eqId;
	}


	public void setEqId(String eqId) {
		this.eqId = eqId;
	}


	public ManagementUploader getManagementUploader() {
		return managementUploader;
	}


	public void setManagementUploader(ManagementUploader managementUploader) {
		this.managementUploader = managementUploader;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}


	public NewsComment getNewsComment() {
		return newsComment;
	}


	public void setNewsComment(NewsComment newsComment) {
		this.newsComment = newsComment;
	}

}
