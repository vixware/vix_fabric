package com.vix.oa.personaloffice.book.domain;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.oa.personaloffice.book.entity.BookBorrow;
import com.vix.oa.personaloffice.book.entity.BookEntry;
import com.vix.oa.personaloffice.book.entity.BookRegister;

/**
 * 
 * @ClassName: BookEntryDomain
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-5-24 下午10:19:52
 */
@Component("bookEntryDomain")
@Transactional
public class BookEntryDomain extends BaseDomain{

	
	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params,Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager,BookEntry.class, params);
		return p;
	}
	
	/** 获取搜索列表数据  */
	public Pager findPagerByOrHqlConditions(Map<String,Object> params,Pager pager) throws Exception{
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, BookRegister.class, params);
		return p;
	}
	
	/** 获取搜索图书借用列表数据  */
	public Pager findBookLibrarian(Map<String,Object> params,Pager pager) throws Exception{
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, BookBorrow.class, params);
		return p;
	}
	
	/** 获取列表数据 */
	public Pager findWimStockrecordsPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, BookRegister.class, params);
		return p;
	}
	
	/** 获取借用图书列表数据 */
	public Pager findBookLibrarianPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, BookBorrow.class, params);
		return p;
	}
	
	/** 索引对象列表 */
	public List<BookEntry> findSalesOrderIndex() throws Exception {
		return baseHibernateService.findAllByConditions(BookEntry.class, null);
	}
	
	public BookEntry findEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(BookEntry.class, id);
	}
	
	public void deleteByEntity(BookEntry bookEntry) throws Exception {
		baseHibernateService.deleteByEntity(bookEntry);
	}
	
	public BookEntry merge(BookEntry bookEntry) throws Exception {
		return baseHibernateService.merge(bookEntry);
	}
	
	/** 索引对象列表 */
	public List<BookRegister> findWimStockrecordsIndex() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		// 过滤归还单
		/*params.put("flag," + SearchCondition.ANYLIKE, "2");
		params.put("isTemp," + SearchCondition.NOEQUAL, "1");*/
		return baseHibernateService.findAllByConditions(BookRegister.class, params);
	}
	
	public BookRegister findWimStockrecordsById(String id) throws Exception {
		return baseHibernateService.findEntityById(BookRegister.class, id);
	}
	
}
