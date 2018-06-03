package com.vix.oa.personaloffice.phoneSms.action;

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
import com.vix.oa.personaloffice.phoneSms.controller.PhoneSmsController;
import com.vix.oa.personaloffice.phoneSms.entity.PhoneSms;

@Controller
@Scope("prototype")
public class PhoneSmsAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(PhoneSmsController.class);

	@Autowired
	private PhoneSmsController phoneSmsController;
	private List<PhoneSms> phoneSmsList;
	private PhoneSms phoneSms;
	private String id;


	public String goSingleList() {
		try {
			phoneSmsList = baseHibernateService.findAllByEntityClass(PhoneSms.class);
			Map<String, Object> params = getParams();
			String code = getRequestParameter("code");
			if (null != code && !"".equals(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
			}
			// pubids包含当前登录人id
			String employeeId = SecurityUtil.getCurrentEmpId();
			params.put("pubIds," + SearchCondition.ANYLIKE, "," + employeeId + ",");
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("createTime");
				getPager().setOrderBy("desc");
			}
			Pager pager = phoneSmsController.doSubSingleList(params, getPager());
			logger.info("获取短信列表数据");
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
			Pager pager = phoneSmsController.doSubSingleList(params, getPager());
			logger.info("获取短信列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSearch() {
		return "goSearch";
	}

	/** 获取短信搜索列表数据 */
	public String goSearchList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = null;
			String i = getRequestParameter("i");
			// 发送人
			String uploadPersonName = getRequestParameter("uploadPersonName");
			if (null != uploadPersonName && !"".equals(uploadPersonName)) {
				uploadPersonName = URLDecoder.decode(uploadPersonName, "utf-8");
			}
			// 收信人
			String recipients = getRequestParameter("recipients");
			if (null != recipients && !"".equals(recipients)) {
				recipients = URLDecoder.decode(recipients, "utf-8");
			}
			// 手机号
			String phoneNumber = getRequestParameter("phoneNumber");
			if (null != phoneNumber && !"".equals(phoneNumber)) {
				phoneNumber = URLDecoder.decode(phoneNumber, "utf-8");
			}
			// 内容
			String phoneSmsContent = getRequestParameter("phoneSmsContent");
			if (null != phoneSmsContent && !"".equals(phoneSmsContent)) {
				phoneSmsContent = URLDecoder.decode(phoneSmsContent, "utf-8");
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("recipients," + SearchCondition.ANYLIKE, recipients);
				pager = phoneSmsController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != uploadPersonName && !"".equals(uploadPersonName)) {
					params.put("uploadPersonName," + SearchCondition.ANYLIKE, uploadPersonName);
				}
				if (null != recipients && !"".equals(recipients)) {
					params.put("recipients," + SearchCondition.ANYLIKE, recipients);
				}
				if (null != phoneNumber && !"".equals(phoneNumber)) {
					params.put("phoneNumber," + SearchCondition.ANYLIKE, phoneNumber);
				}
				if (null != phoneSmsContent && !"".equals(phoneSmsContent)) {
					params.put("phoneSmsContent," + SearchCondition.ANYLIKE, phoneSmsContent);
				}
				pager = phoneSmsController.goList(params, getPager());
			}
			logger.info("获取短信搜索列表数据页");
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
			if (phoneSms != null && null != phoneSms.getId()) {
				isSave = false;
			}
			phoneSms.setCreateTime(new Date());
			this.saveBaseEntity(phoneSms);
			phoneSms = phoneSmsController.doSaveSalesOrder(phoneSms);
			this.phoneSms.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.phoneSms.setUploadPerson(SecurityUtil.getCurrentUserName());
			/** 拿到当前用户的姓名，保存 */
			phoneSms.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			this.phoneSms.setIsTop(0);
			this.saveBaseEntity(this.phoneSms);
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
				getPager().setOrderField("sendTime");
				getPager().setOrderBy("desc");
			}
			Pager pager = phoneSmsController.doSubSingleList(params, getPager());
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
			PhoneSms pb = phoneSmsController.findEntityById(id);
			if (null != pb) {
				phoneSmsController.doDeleteByEntity(pb);
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
			PhoneSms pb = phoneSmsController.findEntityById(id);
			if (null != pb) {
				phoneSmsController.doDeleteByEntity(pb);
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

	/** 获取未发送消息列表页面 */
	public String goUnsentMessages() {
		try {
			Map<String, Object> params = getParams();
			params.put("isTop," + SearchCondition.EQUAL, 1);
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("sendTime");
				getPager().setOrderBy("desc");
			}
			Pager pager = phoneSmsController.doSubSingleList(params, getPager());
			logger.info("获未发送消息列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goUnsentMessages";
	}

	/** 跳转至用草稿箱发送页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				phoneSms = phoneSmsController.doListEntityById(id);
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
			if (phoneSms != null && null != phoneSms.getId()) {
				isSave = false;
			}
			phoneSms.setCreateTime(new Date());
			this.saveBaseEntity(phoneSms);
			phoneSms = phoneSmsController.doSaveSalesOrder(phoneSms);
			this.phoneSms.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.phoneSms.setUploadPerson(SecurityUtil.getCurrentUserName());
			/** 拿到当前用户的姓名，保存 */
			phoneSms.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			this.phoneSms.setIsTop(1);
			this.saveBaseEntity(this.phoneSms);
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

	public PhoneSmsController getPhoneSmsController() {
		return phoneSmsController;
	}

	public void setPhoneSmsController(PhoneSmsController phoneSmsController) {
		this.phoneSmsController = phoneSmsController;
	}

	public List<PhoneSms> getPhoneSmsList() {
		return phoneSmsList;
	}

	public void setPhoneSmsList(List<PhoneSms> phoneSmsList) {
		this.phoneSmsList = phoneSmsList;
	}

	public PhoneSms getPhoneSms() {
		return phoneSms;
	}

	public void setPhoneSms(PhoneSms phoneSms) {
		this.phoneSms = phoneSms;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
