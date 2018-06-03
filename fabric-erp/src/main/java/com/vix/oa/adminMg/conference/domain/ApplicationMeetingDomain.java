package com.vix.oa.adminMg.conference.domain;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.oa.adminMg.conference.entity.ApplicationMg;
import com.vix.oa.adminMg.conference.entity.EquipmentDetails;
import com.vix.oa.adminMg.conference.entity.MeetingDevice;

/**
 * 
 * @ClassName: MeetingRequestDomain
 * @Description: 申请会议室
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-3-19 上午11:16:49
 */
@Component("applicationMeetingDomain")
@Transactional
public class ApplicationMeetingDomain extends BaseDomain{

	
	/** 获取申请会议室列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params,Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager,ApplicationMg.class, params);
		return p;
	}
	
	/** 获取申请会议室搜索列表数据  */
	public Pager findPagerByOrHqlConditions(Map<String,Object> params,Pager pager) throws Exception{
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, ApplicationMg.class, params);
		return p;
	}
	
	/** 索引对象列表 */
	public List<ApplicationMg> findSalesOrderIndex() throws Exception {
		return baseHibernateService.findAllByConditions(ApplicationMg.class, null);
	}
	
	public ApplicationMg findEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(ApplicationMg.class, id);
	}
	
	public void deleteByEntity(ApplicationMg applicationMg) throws Exception {
		baseHibernateService.deleteByEntity(applicationMg);
	}
	
	public ApplicationMg merge(ApplicationMg applicationMg) throws Exception {
		return baseHibernateService.merge(applicationMg);
	}
	
	/**设备明细*/
	public EquipmentDetails merge1(EquipmentDetails equipmentDetails)throws Exception {
		return baseHibernateService.merge(equipmentDetails);
	}
	
	/**根据id获取设备明细数据*/
	public EquipmentDetails findEquipmentDetailsById(String id) throws Exception{
		return baseHibernateService.findEntityById(EquipmentDetails.class, id);
	}
	/**删除设备明细*/
	public void deleteEquipmentDetailsEntity(EquipmentDetails equipmentDetails) throws Exception {
		baseHibernateService.deleteByEntity(equipmentDetails);
	}
	/** 获取设备列表数据 */
	public Pager findPagerByHqlConditions1(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, MeetingDevice.class, params);
		return p;
	}
	
}
