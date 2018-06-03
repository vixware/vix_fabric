package com.rest.app.oa.notice;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rest.core.base.BaseRestController;
import com.rest.core.exception.RestException;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.oa.bulletin.entity.AccountStatements;
import com.vix.oa.bulletin.entity.AnnouncementNotification;
import com.vix.oa.bulletin.service.IAnnouncementNotificationService;

/**
 * 
* @ClassName: NoticeModuleAppController
* @Description:  App 公告通知
* @author bobchen
* @date 2015年12月14日 下午12:04:26
*
 */

@Controller
@RequestMapping(value = "restService/app/oa/notice/noticeApp")
public class NoticeAppController extends BaseRestController{
	
	@Resource(name="announcementNotificationService")
	private IAnnouncementNotificationService announcementNotificationService;
	
	/**
	 * 获取公告通知列表
	 * @param request
	 * @param response
	 * @param announcementNotification
	 * @throws Exception
	 */
	@RequestMapping(value="findAnnouncementNotificationList.rs",method = RequestMethod.POST)
	//@ResponseBody
	public void findAll(HttpServletRequest request, HttpServletResponse response, AnnouncementNotification announcementNotification) throws Exception {
		List<AnnouncementNotification> tlist = announcementNotificationService.findAllByEntityClass(AnnouncementNotification.class);
		List<AnnouncementNotification> resList = new ArrayList<AnnouncementNotification>();
		if(null != tlist && tlist.size() > 0){
			for(AnnouncementNotification a : tlist){
				AnnouncementNotification amg = new AnnouncementNotification();
				BeanUtils.copyProperties(a, amg,new String[]{"comments","organizationUnit","role","employee","userAccount","accountStatements","noticeUploader"});
				resList.add(amg);
			}
		}
		renderResult(response,resList);
	}
	
	/**
	 * 获取公告通知分页列表
	 * 
	 * @param request
	 * @param response
	 * @param pager
	 * @param announcementNotification
	 * @throws Exception
	 */
	@RequestMapping(value = "findAnnouncementNotificationPager.rs", method = RequestMethod.POST)
	public void findAnnouncementNotificationPager(HttpServletRequest request, HttpServletResponse response, Pager pager, AnnouncementNotification announcementNotification) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		if (StrUtils.isNotEmpty(announcementNotification.getTitle())) {
			params.put("title", announcementNotification.getTitle());
		}
		if (StrUtils.isNotEmpty(announcementNotification.getPubNames())) {
			params.put("pubNames", announcementNotification.getPubNames());
		}
		Pager pagerRes = announcementNotificationService.findPagerByHqlConditions(pager, AnnouncementNotification.class, params);
		renderResultPager(response, pagerRes);
	}
	
	/**
	 * 根据ID获取公告通知
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "findAnnouncementNotification.rs", method = RequestMethod.POST)
	public void querys(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		AnnouncementNotification t = null;
		if (StringUtils.isNotEmpty(id)) {
			AnnouncementNotification announcementNotification = announcementNotificationService.findEntityByAttributeNoTenantId(AnnouncementNotification.class, "id", id);
			String message = "";
			if (announcementNotification == null) {
				message = "对象不存在(id:" + id + ")";
				throw new RestException(HttpStatus.NOT_FOUND, message);
			} else {
				t = new AnnouncementNotification();
				BeanUtils.copyProperties(announcementNotification, t, new String[] {"comments","organizationUnit","role","employee","userAccount","accountStatements","noticeUploader"});
			}
		}
		renderResult(response, t);
	}
	
	/**
	 * 根据ID获取公告通知并记录阅读情况与阅读次数
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "findNoticeReadingLog.rs", method = RequestMethod.POST)
	public void recordQuery(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		AnnouncementNotification t = null;
		if (StringUtils.isNotEmpty(id)) {
			AnnouncementNotification announcementNotification = announcementNotificationService.findEntityByAttributeNoTenantId(AnnouncementNotification.class, "id", id);
			String message = "";
			if (announcementNotification == null) {
				message = "对象不存在(id:" + id + ")";
				throw new RestException(HttpStatus.NOT_FOUND, message);
			} else {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("announcementNotification.id," + SearchCondition.EQUAL,id);
				params.put("employee.id," + SearchCondition.EQUAL,  SecurityUtil.getCurrentEmpId());
				List<AccountStatements> accountStatementsList=announcementNotificationService.findAllByConditions(AccountStatements.class, params);
				for(AccountStatements accountStatements:accountStatementsList){
					accountStatements.setIsPublish(1);
					accountStatements.setUploadPersonId(SecurityUtil.getCurrentUserId());
					accountStatements.setUploadPerson(SecurityUtil.getCurrentUserName());
					accountStatements.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
					accountStatements.setCreateTime(new Date());
					announcementNotificationService.mergeOriginal(accountStatements);
				}
				if(announcementNotification.getReadTimes()==null){
					announcementNotification.setReadTimes(1l);
				}else{
					announcementNotification.setReadTimes(announcementNotification.getReadTimes()+1);
				}
				announcementNotification = announcementNotificationService.mergeOriginal(announcementNotification);
				t = new AnnouncementNotification();
				BeanUtils.copyProperties(announcementNotification, t, new String[] {"comments","organizationUnit","role","employee","userAccount","accountStatements","noticeUploader"});
			}
		}
		renderResult(response, t);
	}
}
