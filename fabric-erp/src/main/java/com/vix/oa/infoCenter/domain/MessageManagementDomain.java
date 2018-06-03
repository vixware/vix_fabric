
package com.vix.oa.infoCenter.domain;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.oa.infoCenter.entity.ManagementUploader;
import com.vix.oa.infoCenter.entity.MessageManagement;
import com.vix.oa.infoCenter.entity.NewsComment;
@Component("messageManagementDomain")
@Transactional
public class MessageManagementDomain extends BaseDomain{

	/** 获取消息列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params,Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager,MessageManagement.class, params);
		return p;
	}
	
	/** 获取搜索列表数据  */
	public Pager findPagerByOrHqlConditions(Map<String,Object> params,Pager pager) throws Exception{
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, MessageManagement.class, params);
		return p;
	}
	
	/** 获取附件列表数据 */
	public Pager findManagementUploader(Map<String, Object> params,Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager,ManagementUploader.class, params);
		return p;
	}
	
	/**草稿信息*/
	public MessageManagement findEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(MessageManagement.class, id);
	}
	
	/**消息评论*/
	public NewsComment findNewsCommentById(String id) throws Exception {
		return baseHibernateService.findEntityById(NewsComment.class, id);
	}
	
	/**保存评论*/
	public NewsComment merge(NewsComment newsComment) throws Exception {
		return baseHibernateService.merge(newsComment);
	}
	
	public void deleteByEntity(MessageManagement messageManagement) throws Exception {
		baseHibernateService.deleteByEntity(messageManagement);
	}
	
	public void deleteManagementUploader(ManagementUploader managementUploader) throws Exception {
		baseHibernateService.deleteByEntity(managementUploader);
	}
	
	public MessageManagement merge(MessageManagement messageManagement) throws Exception {
		return baseHibernateService.merge(messageManagement);
	}
	
	public ManagementUploader findNoticeUploader(String id) throws Exception {
		return baseHibernateService.findEntityById(ManagementUploader.class, id);
	}
}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	