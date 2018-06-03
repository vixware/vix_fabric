package com.vix.oa.personaloffice.book.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @ClassName: BookEntry
 * @Description: 图书导入
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-5-24 下午10:05:51
 */
public class BookEntry extends BaseEntity {

	private static final long serialVersionUID = 87031862813442337L;
	
	/** 图书类别 */
	private String bookType;
	/** 图书编号 */
	private String bookCode;
	/** 作者 */
	private String author;
	/** 书名 */
	private String bookName;
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
	/** 借阅状态 0 未借 1 已借 2 归还 */
	public Integer borrowingStatus;
	/** 当前库存 */
	public Double currentInventory;
	/** 中文首字母 */
	private String chineseFirstLetter;
	/** 开本*/
	private String folio;
	/** 印张 */
	private Double sheet;
	/** 版次*/
	private String rev;
	/** 印次*/
	private String impression;
	/** 印数*/
	private String printing;
	/**图书分类*/
	private BookCategory bookCategory;
	/** 领用、借用、归还单 */
	private BookRegister bookRegister;
	

	public BookEntry() {
		super();
	}

	public BookEntry(String id) {
		super();
		setId(id);
	}

	public String getBookType() {
		return bookType;
	}

	public void setBookType(String bookType) {
		this.bookType = bookType;
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

	public BookCategory getBookCategory() {
		return bookCategory;
	}

	public void setBookCategory(BookCategory bookCategory) {
		this.bookCategory = bookCategory;
	}

	public String getBookCode() {
		return bookCode;
	}

	public void setBookCode(String bookCode) {
		this.bookCode = bookCode;
	}

	public Integer getBorrowingStatus() {
		return borrowingStatus;
	}

	public void setBorrowingStatus(Integer borrowingStatus) {
		this.borrowingStatus = borrowingStatus;
	}

	public BookRegister getBookRegister() {
		return bookRegister;
	}

	public void setBookRegister(BookRegister bookRegister) {
		this.bookRegister = bookRegister;
	}

	public Double getCurrentInventory() {
		return currentInventory;
	}

	public void setCurrentInventory(Double currentInventory) {
		this.currentInventory = currentInventory;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getChineseFirstLetter() {
		return chineseFirstLetter;
	}

	public void setChineseFirstLetter(String chineseFirstLetter) {
		this.chineseFirstLetter = chineseFirstLetter;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public Double getSheet() {
		return sheet;
	}

	public void setSheet(Double sheet) {
		this.sheet = sheet;
	}

	public String getRev() {
		return rev;
	}

	public void setRev(String rev) {
		this.rev = rev;
	}

	public String getImpression() {
		return impression;
	}

	public void setImpression(String impression) {
		this.impression = impression;
	}

	public String getPrinting() {
		return printing;
	}

	public void setPrinting(String printing) {
		this.printing = printing;
	}

}
