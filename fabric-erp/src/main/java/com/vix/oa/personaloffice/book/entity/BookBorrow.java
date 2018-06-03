package com.vix.oa.personaloffice.book.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @ClassName: BookBorrow
 * @Description: 图书借用
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-5-28 上午9:50:10
 */
public class BookBorrow extends BaseEntity {
	private static final long serialVersionUID = -8891289921069733348L;
	/** 计量单位 */
	public String prickle;
	/** 图书名称 */
	public String bookName;
	/**借用数量 */
	public Double borrowNumber;
	/**借用总数量 */
	public Double totalNumber;
	/**归还数量 */
	public Double returnNumber;
	/** ISBN号 */
	private String ISBN;
	/** 作者 */
	private String author;
	/** 出版社 */
	private String press;
	/** 存放地点 */
	private String address;
	/** 存放数量 */
	private Double amount;
	/** 价格 */
	private Double price;
	/** 图书类别 */
	private String bookType;
	
	public Integer isDeduction;
	/** 图书品领用、借用、归还基本數據*/
	private BookRegister bookRegister;
	/**图书 */
	private BookEntry bookEntry;
	/** 图书借用日誌明细*/
	private Set<BorrowedBooksList> borrowedBooksList = new HashSet<BorrowedBooksList>();

	public String getPrickle() {
		return prickle;
	}

	public void setPrickle(String prickle) {
		this.prickle = prickle;
	}

	public Double getBorrowNumber() {
		return borrowNumber;
	}

	public void setBorrowNumber(Double borrowNumber) {
		this.borrowNumber = borrowNumber;
	}

	public BookRegister getBookRegister() {
		return bookRegister;
	}

	public void setBookRegister(BookRegister bookRegister) {
		this.bookRegister = bookRegister;
	}

	public BookEntry getBookEntry() {
		return bookEntry;
	}

	public void setBookEntry(BookEntry bookEntry) {
		this.bookEntry = bookEntry;
	}

	public Integer getIsDeduction() {
		return isDeduction;
	}

	public void setIsDeduction(Integer isDeduction) {
		this.isDeduction = isDeduction;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public Double getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(Double totalNumber) {
		this.totalNumber = totalNumber;
	}

	public Double getReturnNumber() {
		return returnNumber;
	}

	public void setReturnNumber(Double returnNumber) {
		this.returnNumber = returnNumber;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPress() {
		return press;
	}

	public void setPress(String press) {
		this.press = press;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getBookType() {
		return bookType;
	}

	public void setBookType(String bookType) {
		this.bookType = bookType;
	}

	public Set<BorrowedBooksList> getBorrowedBooksList() {
		return borrowedBooksList;
	}

	public void setBorrowedBooksList(Set<BorrowedBooksList> borrowedBooksList) {
		this.borrowedBooksList = borrowedBooksList;
	}

}
