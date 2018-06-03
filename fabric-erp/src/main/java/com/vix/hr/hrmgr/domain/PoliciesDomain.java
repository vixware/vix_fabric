package com.vix.hr.hrmgr.domain;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.core.web.Pager;
import com.vix.hr.hrmgr.entity.PolSysManage;
import com.vix.hr.hrmgr.service.IPolSysManageService;

/**
 * 
 * @ClassName: PrivateDomain
 * @Description: 政策制度管理
 * @author bobchen
 * @date 2015年12月4日 下午5:25:31
 *
 */
@Component("policiesDomain")
@Transactional
public class PoliciesDomain {

	@Autowired
	private IPolSysManageService iPolSysManageService;

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iPolSysManageService.findPagerByOrHqlConditions(pager, PolSysManage.class, params);
		return p;
	}

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iPolSysManageService.findPagerByHqlConditions(pager, PolSysManage.class, params);
		return p;
	}

	/** 索引对象列表 */
	public List<PolSysManage> findSalesOrderIndex() throws Exception {
		return iPolSysManageService.findAllByConditions(PolSysManage.class, null);
	}

	public PolSysManage merge(PolSysManage polSysManage) throws Exception {
		return iPolSysManageService.merge(polSysManage);
	}

}
