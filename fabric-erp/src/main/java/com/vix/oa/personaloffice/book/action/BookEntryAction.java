package com.vix.oa.personaloffice.book.action;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.oa.personaloffice.book.controller.BookEntryController;
import com.vix.oa.personaloffice.book.entity.BookCategory;
import com.vix.oa.personaloffice.book.entity.BookEntry;
import com.vix.oa.personaloffice.wab.controller.WabController;

/**
 * 
 * @ClassName: BookEntryAction
 * @Description: 图书录入
 * @author chenzhengwen
 * @author w_a_533@163.com
 * @date 2014-5-24 下午10:24:18
 */
@Controller
@Scope("prototype")
public class BookEntryAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Logger logger = Logger.getLogger(WabController.class);
	@Autowired
	private BookEntryController bookEntryController;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	private BookEntry bookEntry;
	private List<BookEntry> bookEntryList;
	/** 图书分类 */
	private BookCategory bookCategory;
	private String id;
	private String parentId;
	private String pageNo;
	private Date updateTime;
	private String bookName;

	/** 跳转到列表页面 */
	@Override
	public String goList() {
		try {
			bookEntryList = bookEntryController.doListSalesOrderIndex();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			String code = getRequestParameter("code");
			if (null != code && !"".equals(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
			}
			if (parentId != null) {
				params.put("bookCategory.id," + SearchCondition.EQUAL, parentId);
			}
			// 按最近使用
			String startTime = getRequestParameter("startTime");
			if (startTime != null && !"".equals(startTime)) {
				params.put("createTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(startTime));
			}
			Pager pager = bookEntryController.doSubSingleList(params, getPager());
			logger.info("获取图书列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 获取列表数据 */
	public String goListContent() {
		try {
			Map<String, Object> params = getParams();
			String code = getRequestParameter("code");
			if (null != code && !"".equals(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
			}
			if (null != bookName && !"".equals(bookName)) {
				bookName = decode(bookName, "UTF-8");
				params.put("bookName," + SearchCondition.ANYLIKE, bookName);
			}
			if (null != parentId && !"".equals(parentId)) {
				params.put("bookCategory.id," + SearchCondition.EQUAL, parentId);
			} else {
				baseHibernateService.findPagerByHqlConditions(getPager(), BookEntry.class, params);
			}
			// 按最近使用
			String startTime = getRequestParameter("startTime");
			if (startTime != null && !"".equals(startTime)) {
				params.put("createTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(startTime));
			}
			Pager pager = bookEntryController.doSubSingleList(params, getPager());
			logger.info("获取图书列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goListContent";
	}

	public String goSearch() {
		return "goSearch";
	}

	/** 获取搜索列表数据 */
	public String goSearchList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = null;
			String i = getRequestParameter("i");
			// 编号
			String bookCode = getRequestParameter("bookCode");
			if (null != bookCode && !"".equals(bookCode)) {
				bookCode = URLDecoder.decode(bookCode, "utf-8");
			}
			// 书名
			String bookName = getRequestParameter("bookName");
			if (null != bookName && !"".equals(bookName)) {
				bookName = URLDecoder.decode(bookName, "utf-8");
			}
			// ISBN号
			String ISBN = getRequestParameter("ISBN");
			if (null != ISBN && !"".equals(ISBN)) {
				ISBN = URLDecoder.decode(ISBN, "utf-8");
			}
			// 作者
			String author = getRequestParameter("author");
			if (null != author && !"".equals(author)) {
				author = URLDecoder.decode(author, "utf-8");
			}
			// 出版社
			String press = getRequestParameter("press");
			if (null != press && !"".equals(press)) {
				press = URLDecoder.decode(press, "utf-8");
			}
			// 开本
			String folio = getRequestParameter("folio");
			if (null != folio && !"".equals(folio)) {
				folio = URLDecoder.decode(folio, "utf-8");
			}
			// 版次
			String rev = getRequestParameter("rev");
			if (null != rev && !"".equals(rev)) {
				rev = URLDecoder.decode(rev, "utf-8");
			}
			// 印次
			String impression = getRequestParameter("impression");
			if (null != impression && !"".equals(impression)) {
				impression = URLDecoder.decode(impression, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("bookName," + SearchCondition.ANYLIKE, bookName);
				pager = bookEntryController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != bookCode && !"".equals(bookCode)) {
					params.put("bookCode," + SearchCondition.ANYLIKE, bookCode);
				}
				if (null != bookName && !"".equals(bookName)) {
					params.put("bookName," + SearchCondition.ANYLIKE, bookName);
				}
				if (null != ISBN && !"".equals(ISBN)) {
					params.put("ISBN," + SearchCondition.ANYLIKE, ISBN);
				}
				if (null != author && !"".equals(author)) {
					params.put("author," + SearchCondition.ANYLIKE, author);
				}
				if (null != press && !"".equals(press)) {
					params.put("press," + SearchCondition.ANYLIKE, press);
				}
				if (null != folio && !"".equals(folio)) {
					params.put("folio," + SearchCondition.ANYLIKE, folio);
				}
				if (null != rev && !"".equals(rev)) {
					params.put("rev," + SearchCondition.ANYLIKE, rev);
				}
				if (null != impression && !"".equals(impression)) {
					params.put("impression," + SearchCondition.ANYLIKE, impression);
				}
				pager = bookEntryController.goBookRegister(params, getPager());
			}
			logger.info("获取图书索列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/**
	 * 新增图书分类
	 * 
	 * @return
	 */
	public String goSaveOrUpdateCategory() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				bookCategory = baseHibernateService.findEntityById(BookCategory.class, id);
			} else {
				bookCategory = new BookCategory();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateCategory";
	}

	/**
	 * 保存图书分类
	 * 
	 * @return
	 */
	public String saveOrUpdateCategory() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(bookCategory.getId()) && !"".equals(bookCategory.getId())) {
				isSave = false;
			}
			if (bookCategory.getParentBookCategory().getId() == null || "".equals(bookCategory.getParentBookCategory().getId())) {
				bookCategory.setParentBookCategory(null);
			}
			baseHibernateService.merge(bookCategory);
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
	 * 获取图书分类的树形结构
	 */
	public void findBookCategoryTree() {
		try {
			List<BookCategory> bookCategoryList = new ArrayList<BookCategory>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (null != id && !"".equals(id)) {
				bookCategoryList = baseHibernateService.findAllSubEntity(BookCategory.class, "parentBookCategory.id", id, params);
			} else {
				bookCategoryList = baseHibernateService.findAllSubEntity(BookCategory.class, "parentBookCategory.id", null, params);
			}
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			int count = bookCategoryList.size();
			for (int i = 0; i < count; i++) {
				BookCategory bookCategory = bookCategoryList.get(i);
				if (bookCategory.getSubbookCategory().size() > 0) {
					strSb.append("{id:\"");
					strSb.append(bookCategory.getId());
					strSb.append("\",name:\"");
					strSb.append(bookCategory.getName());
					strSb.append("\",open:false,isParent:true}");
				} else {
					strSb.append("{id:\"");
					strSb.append(bookCategory.getId());
					strSb.append("\",name:\"");
					strSb.append(bookCategory.getName());
					strSb.append("\",open:false,isParent:false}");
				}
				if (i < count - 1) {
					strSb.append(",");
				}
			}
			strSb.append("]");
			renderHtml(strSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 选择分类
	 * 
	 * @return
	 */
	public String goBookCategory() {
		return "goBookCategory";
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				bookEntry = baseHibernateService.findEntityById(BookEntry.class, id);
			}
			// 如果带出parentId,就无法修改
			/*
			 * else { bookEntry = new BookEntry(); if (null != parentId &&
			 * parentId.longValue() > 0) { BookCategory bookCategory =
			 * baseHibernateService.findEntityById(BookCategory.class,parentId); if
			 * (bookCategory != null) { bookEntry.setBookCategory(bookCategory); } } }
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdate";
	}

	/** 处理新增修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(bookEntry.getId()) && !"".equals(bookEntry.getId())) {
				isSave = false;
			}
			/** 索引 */
			String bookName = bookEntry.getBookName();
			String py = ChnToPinYin.getPYString(bookName);
			bookEntry.setChineseFirstLetter(py.toUpperCase());
			if (bookEntry.getBookCategory().getId() == null || "".equals(bookEntry.getBookCategory().getId())) {
				bookEntry.setBookCategory(null);
			}
			initEntityBaseController.initEntityBaseAttribute(bookEntry);
			bookEntry = bookEntryController.doSaveSalesOrder(bookEntry);
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

	/** 处理删除操作 */
	public String deleteById() {
		try {
			BookEntry pb = bookEntryController.findEntityById(id);
			if (null != pb) {
				bookEntryController.doDeleteByEntity(pb);
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

	public String goChooseCategory() {
		return "goChooseCategory";
	}

	public String getId() {
		return id;
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

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public BookEntry getBookEntry() {
		return bookEntry;
	}

	public void setBookEntry(BookEntry bookEntry) {
		this.bookEntry = bookEntry;
	}

	public List<BookEntry> getBookEntryList() {
		return bookEntryList;
	}

	public void setBookEntryList(List<BookEntry> bookEntryList) {
		this.bookEntryList = bookEntryList;
	}

	public BookCategory getBookCategory() {
		return bookCategory;
	}

	public void setBookCategory(BookCategory bookCategory) {
		this.bookCategory = bookCategory;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

}
