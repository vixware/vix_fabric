package com.vix.oa.personaloffice.book.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * 图书分类
 * @author Thinkpad
 *
 */
public class BookCategory extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 子分类集合 */
	private Set<BookCategory> subbookCategory = new HashSet<BookCategory>();
	/**
	 * 个人文件柜
	 */
	private Set<BookEntry> bookEntrys = new HashSet<BookEntry>();
	/** 父分类 */
	private BookCategory parentBookCategory;

	public BookCategory() {
	}

	public Set<BookCategory> getSubbookCategory() {
		return subbookCategory;
	}

	public void setSubbookCategory(Set<BookCategory> subbookCategory) {
		this.subbookCategory = subbookCategory;
	}

	public Set<BookEntry> getBookEntrys() {
		return bookEntrys;
	}

	public void setBookEntrys(Set<BookEntry> bookEntrys) {
		this.bookEntrys = bookEntrys;
	}

	public BookCategory getParentBookCategory() {
		return parentBookCategory;
	}

	public void setParentBookCategory(BookCategory parentBookCategory) {
		this.parentBookCategory = parentBookCategory;
	}

}
