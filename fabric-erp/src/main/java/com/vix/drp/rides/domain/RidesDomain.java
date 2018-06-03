package com.vix.drp.rides.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.rides.entity.Rides;
import com.vix.hr.organization.entity.Employee;

@Component("ridesDomain")
@Transactional
public class RidesDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findRidesPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, Rides.class, params);
		return p;
	}

	public Rides findEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(Rides.class, id);
	}

	public Employee findEmployeeById(String id) throws Exception {
		return baseHibernateService.findEntityById(Employee.class, id);
	}

	public ChannelDistributor findChannelDistributorById(String id) throws Exception {
		return baseHibernateService.findEntityById(ChannelDistributor.class, id);
	}

	//Domain
	public void deleteByEntity(Rides rides) throws Exception {
		baseHibernateService.deleteByEntity(rides);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(Rides.class, ids);
	}

	public void saveOrUpdate(Rides rides) throws Exception {
		baseHibernateService.saveOrUpdate(rides);
	}

	/** 索引对象列表 */
	public List<Rides> findRidesIndex() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		return baseHibernateService.findAllByConditions(Rides.class, params);
	}
}
