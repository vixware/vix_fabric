package com.vix.oa.adminMg.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * @Description: 图书管理
 * @author ivan
 * @date 2013-12-5
 */
public class LibraryManagement extends BaseEntity {

	private static final long serialVersionUID = 4914589569016380020L;
	/** 图书类别 */
	private String category;
	/** 作者 */
	private String author;
	/** ISBN号 */
	private String ISBN;
	/** 出版社 */
	private String press;
	/** 出版日期 */
	private Date publicationDate;
	/** 存放地点 */
	private String address;
	/** 数量 */
	private Double amount;
	/** 价格 */
	private Double price;
	/** 内容简介 */
	private String introduction;
	/** 借阅范围 */
	private String ranges;
	/** 借书日期 */
	private Date borrowDate;
	/** 还书日期 */
	private Date repayDate;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getPress() {
		return press;
	}

	public void setPress(String press) {
		this.press = press;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
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

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getRanges() {
		return ranges;
	}

	public void setRanges(String ranges) {
		this.ranges = ranges;
	}

	public Date getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}

	public Date getRepayDate() {
		return repayDate;
	}

	public void setRepayDate(Date repayDate) {
		this.repayDate = repayDate;
	}

}
