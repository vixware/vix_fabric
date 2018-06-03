package com.vix.oa.personaloffice.book.action;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.oa.personaloffice.book.controller.BookEntryController;
import com.vix.oa.personaloffice.book.entity.BookBorrow;
import com.vix.oa.personaloffice.book.entity.BookEntry;
import com.vix.oa.personaloffice.book.entity.BookRegister;
import com.vix.oa.personaloffice.book.entity.BorrowedBooksList;
@Controller
@Scope("prototype")
public class BookLibrarianAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(BookLibrarianAction.class);

	@Autowired
	private BookEntryController bookEntryController;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private String id;
	private String ids;
	private String pageNo;
	/** 图书、借用、归还 基本信息*/
	private BookRegister bookRegister;
	/** 借用基本信息 */
	private List<BookRegister> bookRegisterList;
	/** 借用 */
	private BookBorrow bookBorrow;
	/** 借用归还明细*/
	private BorrowedBooksList borrowedBooksList;
	/** 图书库存 */
	private BookEntry bookEntry;
	private List<BookEntry> bookEntryList;

	@Override
	public String goList() {
		try {
			bookRegisterList = bookEntryController.doListWimStockrecordsIndex();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goBookRegister() {
		try {
			Map<String, Object> params = getParams();
			// 过滤临时数据
			/*params.put("isTemp," + SearchCondition.NOEQUAL, "1");*/
			String isTemp = getRequestParameter("isTemp");
			if (null != isTemp && !"".equals(isTemp)) {
				params.put("isTemp," + SearchCondition.EQUAL, isTemp);
			}
			// 按最近使用
			String createTime = getRequestParameter("createTime");
			if (createTime != null && !"".equals(createTime)) {
				params.put("createTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(createTime));
			}
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("createTime");
				getPager().setOrderBy("desc");
			}
			Pager pager = bookEntryController.doListWimStockrecords(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goBookRegister";
	}
	
	public String goSearch() {
		return "goSearch";
	}
	
	/** 获取图书借用搜索列表数据 */
	public String goSearchList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = null;
			String i = getRequestParameter("i");
			// 编码
			String bookCodes = getRequestParameter("bookCodes");
			if (null != bookCodes && !"".equals(bookCodes)) {
				bookCodes = URLDecoder.decode(bookCodes, "utf-8");
			}
			// 借书证号
			String bookNumber = getRequestParameter("bookNumber");
			if (null != bookNumber && !"".equals(bookNumber)) {
				bookNumber = URLDecoder.decode(bookNumber, "utf-8");
			}
			// 书名
			String bookNames = getRequestParameter("bookNames");
			if (null != bookNames && !"".equals(bookNames)) {
				bookNames = URLDecoder.decode(bookNames, "utf-8");
			}
			// 借用归还人
			String recipientsWho = getRequestParameter("recipientsWho");
			if (null != recipientsWho && !"".equals(recipientsWho)) {
				recipientsWho = URLDecoder.decode(recipientsWho, "utf-8");
			}
			// 经办人
			String uploadPersonName = getRequestParameter("uploadPersonName");
			if (null != uploadPersonName && !"".equals(uploadPersonName)) {
				uploadPersonName = URLDecoder.decode(uploadPersonName, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("bookNames," + SearchCondition.ANYLIKE, bookNames);
				pager = bookEntryController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != bookCodes && !"".equals(bookCodes)) {
					params.put("bookCodes," + SearchCondition.ANYLIKE, bookCodes);
				}
				if (null != bookNumber && !"".equals(bookNumber)) {
					params.put("bookNumber," + SearchCondition.ANYLIKE, bookNumber);
				}
				if (null != bookNames && !"".equals(bookNames)) {
					params.put("bookNames," + SearchCondition.ANYLIKE, bookNames);
				}
				if (null != recipientsWho && !"".equals(recipientsWho)) {
					params.put("recipientsWho," + SearchCondition.ANYLIKE, recipientsWho);
				}
				if (null != uploadPersonName && !"".equals(uploadPersonName)) {
					params.put("uploadPersonName," + SearchCondition.ANYLIKE, uploadPersonName);
				}
				pager = bookEntryController.goBookRegister(params, getPager());
			}
			logger.info("获取图书借用索列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goBookRegister";
	}
	

	/** 跳转图书借用 */
	public String goSaveOrUpdate() {
		try {
			String pageNo = getRequestParameter("pageNo");
			if(null != pageNo){
				getRequest().setAttribute("pageNo", pageNo);
			}
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				bookRegister = bookEntryController.doListBookEntityById(id);
				bookEntry = bookEntryController.findEntityById(id);
			}else {
				BookRegister c = new BookRegister();
				c.setIsTemp("1");
				bookRegister = baseHibernateService.merge(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
			setError("图书获取失败!");
		}
		return GO_SAVE_OR_UPDATE;
	}
	
	/** 图书明细 */
	public String saveOrUpdateBookBorrow() {
		boolean isSave = true;
		try {
			bookBorrow.setIsDeduction(0);
			bookBorrow.setReturnNumber(0.0);
			bookBorrow.setTotalNumber(bookBorrow.getBorrowNumber());
			baseHibernateService.save(bookBorrow);
			/** 图书每次借用归还明细 */
			BorrowedBooksList bBList = new BorrowedBooksList();
			bBList.setCode(bookBorrow.getCode());
			bBList.setBookName(bookBorrow.getBookName());
			bBList.setBorrowNumber(bookBorrow.getBorrowNumber());
			bBList.setISBN(bookBorrow.getISBN());
			bBList.setBookType(bookBorrow.getBookType());
			bBList.setAuthor(bookBorrow.getAuthor());
			bBList.setPress(bookBorrow.getPress());
			bBList.setAmount(bookBorrow.getAmount());
			bBList.setBookBorrow(bookBorrow);
			bBList.setTotalNumber(bookBorrow.getBorrowNumber());
			baseHibernateService.save(bBList);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	/**
	 * 借用减库存
	 * @return
	 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(bookRegister.getId()) && !"".equals(bookRegister.getId())) {
				isSave = false;
			}
			/**索引 */
			String bookNames = bookRegister.getBookNames();
			String py = ChnToPinYin.getPYString(bookNames);
			bookRegister.setChineseFirstLetter(py.toUpperCase());
			
			this.bookRegister.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.bookRegister.setUploadPerson(SecurityUtil.getCurrentUserName());
			/**拿到当前用户的姓名，保存*/
			bookRegister.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			this.saveBaseEntity(this.bookRegister);
			bookRegister = baseHibernateService.merge(bookRegister);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("bookRegister.id," + SearchCondition.EQUAL, bookRegister.getId());
			List<BookBorrow> bookBorrowList = baseHibernateService.findAllByConditions(BookBorrow.class, params);
			if (bookBorrowList != null && bookBorrowList.size() > 0) {
				for (BookBorrow bookBorrow : bookBorrowList) {
					if (bookBorrow != null &&  bookBorrow.getIsDeduction() != 1) {
						BookEntry bookEntry = bookBorrow.getBookEntry();
						bookEntry.setAmount(bookEntry.getAmount() - bookBorrow.getBorrowNumber());
						bookEntry = baseHibernateService.mergeOriginal(bookEntry);
						if (bookEntry != null) {
							bookBorrow.setIsDeduction(1);
							baseHibernateService.mergeOriginal(bookBorrow);
						}
					}
				}
			}
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
	
	public void getBookBorrowJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				BookRegister bookRegister = bookEntryController.doListBookRegisterId(id);
				if (null != bookRegister) {
					json = convertListToJson(new ArrayList<BookBorrow>(bookRegister.getBookBorrow()), bookRegister.getBookBorrow().size());
				}
			}
			if (!"".equals(json)) {
				renderJson(json);
			} else {
				renderJson("{\"total\":0,\"rows\":[]}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 跳转到添加图书借用页面 */
	public String goAddbookBorrowing() {
		return "goAddbookBorrowing";
	}
	
	/** 跳转到添加图书借用页面明细选择图书 */
	public String goChoosebookBorrowing() {
		return "goChoosebookBorrowing";
	}
	
	
	/**
	 * 获取到借还书明细
	 * @author chenzhengwen
	 * @date 2014-11-12上午10:38:12
	 * @return
	 */
	public String goSeenoticenotice() {
		try {
			bookRegister = baseHibernateService.findEntityById(BookRegister.class, id);
			bookBorrow = baseHibernateService.findEntityById(BookBorrow.class, id);
			logger.info("获取列表数据");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSeenoticenotice";
	}	
	
	/** 处理图书查看操作 */
	public String popNews() {
		return "popNews";
	}
	
	
	/////////归还
	/** 加载归还明细*/
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			// 过滤临时数据
			/*params.put("isTemp," + SearchCondition.NOEQUAL, "1");*/
			String isTemp = getRequestParameter("isTemp");
			if (null != isTemp && !"".equals(isTemp)) {
				params.put("isTemp," + SearchCondition.EQUAL, isTemp);
			}
			// 按最近使用
			String createTime = getRequestParameter("createTime");
			if (createTime != null && !"".equals(createTime)) {
				params.put("createTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(createTime));
			}
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("createTime");
				getPager().setOrderBy("desc");
			}
			//判断借书人和借书号不为空是才调用
			// 借书证号
			String bookNumber = getRequestParameter("bookNumber");
			if (null != bookNumber && !"".equals("bookNumber")) {
				params.put("bookRegister.bookNumber,"+SearchCondition.ANYLIKE, bookNumber);
			}
			// 借书人
			String recipientsWho = getRequestParameter("recipientsWho");
			if (null != recipientsWho && !"".equals(recipientsWho)) {
				recipientsWho = URLDecoder.decode(recipientsWho, "utf-8");
				params.put("bookRegister.recipientsWho,"+SearchCondition.ANYLIKE, recipientsWho);
			}
			// 借书证号
			String code = getRequestParameter("code");
			if (null != code && !"".equals("code")) {
				params.put("code,"+SearchCondition.ANYLIKE, code);
			}
			Pager pager = null;
			if(params!=null)
			 pager = bookEntryController.doListBookLibrarian(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	/** 跳转图书归还明细*/
	public String goSaveOrUpdateReturn() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				bookBorrow = baseHibernateService.findEntityById(BookBorrow.class,id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateReturn";
	}
	
	/** 处理图书归还明细修改操作  */
	public String saveOrUpdateReturn() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(bookBorrow.getId()) && !"".equals(bookBorrow.getId())) {
				isSave = false;
			}
			System.out.println(bookBorrow.getId()+"--------------------");
			BookBorrow bookBorrows = baseHibernateService.findEntityById(BookBorrow.class, bookBorrow.getId());
			System.out.println(bookBorrows.getBorrowNumber()+"============================");
			/**借用总数量减去归还数量*/
			bookBorrows.setBorrowNumber(bookBorrows.getBorrowNumber() - bookBorrow.getReturnNumber());
			/**多次归还数量相加*/
			bookBorrows.setReturnNumber(bookBorrows.returnNumber +  bookBorrow.getReturnNumber());
			baseHibernateService.merge(bookBorrows);
			/**bookEntry(库存)里的Amount(库存数量)加上,bookBorrow里的ReturnNumber(归还数量)*/
			BookEntry bookEntry = bookBorrows.getBookEntry();
			bookEntry.setAmount(bookEntry.getAmount() + bookBorrow.getReturnNumber());
			bookEntry = baseHibernateService.mergeOriginal(bookEntry);
			
			BorrowedBooksList bBList = new BorrowedBooksList();
				//图书编码
				bBList.setCode(bookBorrow.getCode());
				//图书名称
				bBList.setBookName(bookBorrow.getBookName());
				bBList.setStartTime(bookBorrow.getStartTime());
				bBList.setBorrowNumber(bookBorrow.borrowNumber - bookBorrow.getReturnNumber());
				bBList.setReturnNumber(bookBorrow.getReturnNumber());
				BookRegister bookRegister = bookBorrows.getBookRegister();
				bBList.setBookNumber(bookRegister.getBookNumber());
				bBList.setRecipientsWho(bookRegister.getRecipientsWho());
				bBList.setBookRegister(bookRegister);
				baseHibernateService.save(bBList);
			System.out.println(bookBorrows.getBorrowNumber()+"-"+bookBorrow.getReturnNumber());
			/**bookBorrows里的BorrowNumber等于0时,对bookRegisterss里的IsTemp保存为1(1为归还) */
			if(bookBorrows.getBorrowNumber()==0.0){
				BookRegister bookRegisterss = bookBorrows.getBookRegister();
				bookRegisterss.setIsTemp("1");
				baseHibernateService.merge(bookRegisterss);
			}
			if(isSave){
				renderText(SAVE_SUCCESS);
			}else{
				renderText(UPDATE_SUCCESS);
			}
			 if(isSave){
				renderText(SAVE_SUCCESS);
			}else{
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(isSave){
				renderText(SAVE_FAIL);
			}else{
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}
	
	/** 跳转图书归还 */
	public String goSaveOrUpdateBook() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				bookRegister = bookEntryController.doListBookEntityById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateBook";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public SimpleDateFormat getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(SimpleDateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}

	public BookRegister getBookRegister() {
		return bookRegister;
	}

	public void setBookRegister(BookRegister bookRegister) {
		this.bookRegister = bookRegister;
	}

	public List<BookRegister> getBookRegisterList() {
		return bookRegisterList;
	}

	public void setBookRegisterList(List<BookRegister> bookRegisterList) {
		this.bookRegisterList = bookRegisterList;
	}

	public BookBorrow getBookBorrow() {
		return bookBorrow;
	}

	public void setBookBorrow(BookBorrow bookBorrow) {
		this.bookBorrow = bookBorrow;
	}

	public BookEntry getBookEntry() {
		return bookEntry;
	}

	public void setBookEntry(BookEntry bookEntry) {
		this.bookEntry = bookEntry;
	}

	public BorrowedBooksList getBorrowedBooksList() {
		return borrowedBooksList;
	}

	public void setBorrowedBooksList(BorrowedBooksList borrowedBooksList) {
		this.borrowedBooksList = borrowedBooksList;
	}

	public List<BookEntry> getBookEntryList() {
		return bookEntryList;
	}

	public void setBookEntryList(List<BookEntry> bookEntryList) {
		this.bookEntryList = bookEntryList;
	}
}
