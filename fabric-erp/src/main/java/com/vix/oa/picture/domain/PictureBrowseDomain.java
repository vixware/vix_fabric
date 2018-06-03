package com.vix.oa.picture.domain;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.oa.picture.entity.PictureBrowse;

/**
 * 
 * @ClassName: PictureBrowseDomain
 * @Description: 公共文件柜
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2015-7-16 下午3:27:28
 */
@Component("pictureBrowseDomain")
@Transactional
public class PictureBrowseDomain extends BaseDomain{

	
	/** 获取搜索列表数据  */
	public Pager findPagerByOrHqlConditions(Map<String,Object> params,Pager pager) throws Exception{
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, PictureBrowse.class, params);
		return p;
	}
	
	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, PictureBrowse.class, params);
		return p;
	}
	
	/** 索引对象列表 */
	public List<PictureBrowse> findSalesOrderIndex() throws Exception {
		return baseHibernateService.findAllByConditions(PictureBrowse.class, null);
	}
	
	public PictureBrowse merge(PictureBrowse pictureBrowse) throws Exception {
		return baseHibernateService.merge(pictureBrowse);
	}
	
}
