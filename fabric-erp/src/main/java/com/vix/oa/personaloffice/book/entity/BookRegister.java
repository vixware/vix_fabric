package com.vix.oa.personaloffice.book.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @ClassName: BookRegister
 * @Description: 图书、借用、归还 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-5-28 上午9:50:36
 */
public class BookRegister extends BaseEntity {

	private static final long serialVersionUID = 87031862813442337L;

	/**借用、归还标志 */
	private String flag;
	/**借书人*/
	private String recipientsWho;
	/** 经办人id */
	private String uploadPersonId;	
	/** id经办人 */
	private String uploadPerson;
	/**经办人*/
	private String uploadPersonName;
	/**书名*/
	private String bookNames;
	/**借书证号*/
	private String bookNumber;
	/**图书编码*/
	private String bookCodes;
	/** ISBN号 */
	private String ISBN;
	/** 中文首字母 */
	private String chineseFirstLetter;

	/** 图书借用明细*/
	private Set<BookBorrow> bookBorrow = new HashSet<BookBorrow>();
	/** 图书借用日誌明细*/
	private Set<BorrowedBooksList> borrowedBooksList = new HashSet<BorrowedBooksList>();

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Set<BookBorrow> getBookBorrow() {
		return bookBorrow;
	}

	public void setBookBorrow(Set<BookBorrow> bookBorrow) {
		this.bookBorrow = bookBorrow;
	}

	public String getRecipientsWho() {
		return recipientsWho;
	}

	public void setRecipientsWho(String recipientsWho) {
		this.recipientsWho = recipientsWho;
	}

	public String getUploadPersonId() {
		return uploadPersonId;
	}

	public void setUploadPersonId(String uploadPersonId) {
		this.uploadPersonId = uploadPersonId;
	}

	public String getUploadPerson() {
		return uploadPerson;
	}

	public void setUploadPerson(String uploadPerson) {
		this.uploadPerson = uploadPerson;
	}

	public String getUploadPersonName() {
		return uploadPersonName;
	}

	public void setUploadPersonName(String uploadPersonName) {
		this.uploadPersonName = uploadPersonName;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getBookNames() {
		return bookNames;
	}

	public void setBookNames(String bookNames) {
		this.bookNames = bookNames;
	}

	public String getBookNumber() {
		return bookNumber;
	}

	public void setBookNumber(String bookNumber) {
		this.bookNumber = bookNumber;
	}

	public String getBookCodes() {
		return bookCodes;
	}

	public void setBookCodes(String bookCodes) {
		this.bookCodes = bookCodes;
	}

	public String getChineseFirstLetter() {
		return chineseFirstLetter;
	}

	public void setChineseFirstLetter(String chineseFirstLetter) {
		this.chineseFirstLetter = chineseFirstLetter;
	}

	public Set<BorrowedBooksList> getBorrowedBooksList() {
		return borrowedBooksList;
	}

	public void setBorrowedBooksList(Set<BorrowedBooksList> borrowedBooksList) {
		this.borrowedBooksList = borrowedBooksList;
	}
}
