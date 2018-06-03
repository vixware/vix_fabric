package com.vix.oa.adminMg.officeSupplies.action;
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
import com.vix.oa.adminMg.officeSupplies.controller.OfficeSuppliesRegisterController;
import com.vix.oa.adminMg.officeSupplies.entity.OfficeList;
import com.vix.oa.adminMg.officeSupplies.entity.OfficeSupplies;
import com.vix.oa.adminMg.officeSupplies.entity.OfficeSuppliesBorrow;
import com.vix.oa.adminMg.officeSupplies.entity.OfficeSuppliesRegister;
import com.vix.oa.adminMg.officeSupplies.entity.OfficeSuppliesRegisterItem;

@Controller
@Scope("prototype")
public class OfficeSuppliesRegisterAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Logger logger = Logger.getLogger(OfficeSuppliesRegisterAction.class);

	@Autowired
	private OfficeSuppliesRegisterController officeSuppliesRegisterController;
	private String id;
	private String ids;
	private String pageNo;
	/** 基础信息*/
	private OfficeSuppliesRegister officeSuppliesRegister;
	private List<OfficeSuppliesRegister> officeSuppliesRegisterList;
	/** 领用 */
	private OfficeSuppliesRegisterItem officeSuppliesRegisterItem;
	/** 借用 */
	private OfficeSuppliesBorrow officeSuppliesBorrow;
	/** 库存 */
	private OfficeSupplies officeSupplies;
	/**明细*/
	private OfficeList officeList;

	@Override
	public String goList() {
		try {
			officeSuppliesRegisterList = officeSuppliesRegisterController.doListWimStockrecordsIndex();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}
	
	/** 获取办公用品列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			//状态
			String isTemp = getRequestParameter("isTemp");
			if (null != isTemp && !"".equals(isTemp)) {
				params.put("isTemp," + SearchCondition.EQUAL, isTemp);
			}			
			// 按最近使用
			String createTime = getRequestParameter("createTime");
			if (createTime != null && !"".equals(createTime)) {
				params.put("createTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(createTime));
			}
			Pager pager = officeSuppliesRegisterController.doListWimStockrecords(params,getPager());
			logger.info("获取办公用品列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	public String goSearch() {
		return "goSearch";
	}
	
	/** 获取办公用品搜索列表数据 */
	public String goSearchList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = null;
			String i = getRequestParameter("i");
			// 编码
			String encoding = getRequestParameter("encoding");
			if (null != encoding && !"".equals(encoding)) {
				encoding = URLDecoder.decode(encoding, "utf-8");
			}
			// 主题
			String theme = getRequestParameter("theme");
			if (null != theme && !"".equals(theme)) {
				theme = URLDecoder.decode(theme, "utf-8");
			}
			// 领用借用归还人
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
				params.put("theme," + SearchCondition.ANYLIKE, theme);
				pager = officeSuppliesRegisterController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != encoding && !"".equals(encoding)) {
					params.put("encoding," + SearchCondition.ANYLIKE, encoding);
				}
				if (null != theme && !"".equals(theme)) {
					params.put("theme," + SearchCondition.ANYLIKE, theme);
				}
				if (null != recipientsWho && !"".equals(recipientsWho)) {
					params.put("recipientsWho," + SearchCondition.ANYLIKE, recipientsWho);
				}
				if (null != uploadPersonName && !"".equals(uploadPersonName)) {
					params.put("uploadPersonName," + SearchCondition.ANYLIKE, uploadPersonName);
				}
				pager = officeSuppliesRegisterController.goSingleList(params, getPager());
			}
			logger.info("获取办公用品搜索列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	/** 跳转办公领用 */
	public String goSaveOrUpdate() {
		try {
			String pageNo = getRequestParameter("pageNo");
			if(null != pageNo){
				getRequest().setAttribute("pageNo", pageNo);
			}
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				officeSuppliesRegister = officeSuppliesRegisterController.doListEntityById(id);
				officeSupplies = officeSuppliesRegisterController.findEntityById(id);
			}else {
				OfficeSuppliesRegister c = new OfficeSuppliesRegister();
				c.setIsTemp("1");
				officeSuppliesRegister = baseHibernateService.merge(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
			setError("办公用品获取失败!");
		}
		return GO_SAVE_OR_UPDATE;
	}

	/**
	 * 领用减库存
	 * @return
	 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(officeSuppliesRegister.getId()) && !"0".equals(officeSuppliesRegister.getId())) {
				isSave = false;
			}
			/**索引 */
			String theme = officeSuppliesRegister.getTheme();
			String py = ChnToPinYin.getPYString(theme);
			officeSuppliesRegister.setChineseFirstLetter(py.toUpperCase());
			
			this.officeSuppliesRegister.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.officeSuppliesRegister.setUploadPerson(SecurityUtil.getCurrentUserName());
			/**拿到当前用户的姓名，保存*/
			officeSuppliesRegister.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			this.saveBaseEntity(this.officeSuppliesRegister);
			officeSuppliesRegister = baseHibernateService.merge(officeSuppliesRegister);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("officeSuppliesRegister.id," + SearchCondition.EQUAL, officeSuppliesRegister.getId());
			List<OfficeSuppliesRegisterItem> officeSuppliesRegisterItemList = baseHibernateService.findAllByConditions(OfficeSuppliesRegisterItem.class, params);
			if (officeSuppliesRegisterItemList != null && officeSuppliesRegisterItemList.size() > 0) {
				for (OfficeSuppliesRegisterItem officeSuppliesRegisterItem : officeSuppliesRegisterItemList) {
					if (officeSuppliesRegisterItem != null && officeSuppliesRegisterItem.getIsDeduction() != 1) {
						OfficeSupplies officeSupplies = officeSuppliesRegisterItem.getOfficeSupplies();
						officeSupplies.setCurrentInventory(officeSupplies.getCurrentInventory() - officeSuppliesRegisterItem.getNumberOfRecipients());
						officeSupplies = baseHibernateService.mergeOriginal(officeSupplies);
						if (officeSupplies != null) {
							officeSuppliesRegisterItem.setIsDeduction(1);
							baseHibernateService.mergeOriginal(officeSuppliesRegisterItem);
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
	
	/**
	 * 保存领用明细
	 * @return
	 */
	public String saveOrUpdateOSRI() {
		boolean isSave = true;
		try {
			officeSuppliesRegisterItem.setIsDeduction(0);
			officeSuppliesRegisterItem.setReturnNumber(0.0);
			officeSuppliesRegisterItem.setNumberOfRecipients(officeSuppliesRegisterItem.getBorrowNumber());
			baseHibernateService.save(officeSuppliesRegisterItem);
			/** 办公用品每次领用归还明细 */
			OfficeList bBList = new OfficeList();
			bBList.setModel(officeSuppliesRegisterItem.getModel());
			bBList.setOfficeName(officeSuppliesRegisterItem.getOfficeName());
			bBList.setPrickle(officeSuppliesRegisterItem.getPrickle());
			bBList.setSupplier(officeSuppliesRegisterItem.getSupplier());
			bBList.setLowestVigilance(officeSuppliesRegisterItem.getLowestVigilance());
			bBList.setNumberOfRecipients(officeSuppliesRegisterItem.getNumberOfRecipients());
			bBList.setBorrowNumber(officeSuppliesRegisterItem.getBorrowNumber());
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
	 * 领用明细
	 */
	public void getORIJ() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				OfficeSuppliesRegister officeSuppliesRegister = officeSuppliesRegisterController.doListEntityById(id);
				if (null != officeSuppliesRegister) {
					json = convertListToJson(new ArrayList<OfficeSuppliesRegisterItem>(officeSuppliesRegister.getOfficeSuppliesRegisterItem()), officeSuppliesRegister.getOfficeSuppliesRegisterItem().size());
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
	
	/** 跳转到添加办公用品领用页面 */
	public String goAddOSItem() {
		
		return "goAddOSItem";
	}
	
	/** 跳转到添加办公用品领用页面明细选择商品 */
	public String goChooseMerchandise() {
		return "goChooseMerchandise";
	}
	
	
	///////////////////////
	/** 跳转办公借用 */
	public String goSaveOrUpdateBorrow() {
		try {
			String pageNo = getRequestParameter("pageNo");
			if(null != pageNo){
				getRequest().setAttribute("pageNo", pageNo);
			}
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				officeSuppliesRegister = officeSuppliesRegisterController.doListEntityById(id);
				officeSupplies = officeSuppliesRegisterController.findEntityById(id);
			}else {
				OfficeSuppliesRegister c = new OfficeSuppliesRegister();
				c.setIsTemp("2");
				officeSuppliesRegister = baseHibernateService.merge(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
			setError("办公用品获取失败!");
		}
		return "goSaveOrUpdateBorrow";
	}
	
	/**
	 * 保存借用办公用品明细
	 * @return
	 */
	public String saveOrUpdateOSB() {
		boolean isSave = true;
		try {
			officeSuppliesBorrow.setIsDeduction(0);
			officeSuppliesBorrow.setReturnNumber(0.0);
			officeSuppliesBorrow.setNumberOfRecipients(officeSuppliesBorrow.getBorrowNumber());
			baseHibernateService.save(officeSuppliesBorrow);
			/** 办公用品每次借用归还明细 */
			OfficeList bBList = new OfficeList();
			bBList.setModel(officeSuppliesRegisterItem.getModel());
			bBList.setOfficeName(officeSuppliesRegisterItem.getOfficeName());
			bBList.setPrickle(officeSuppliesRegisterItem.getPrickle());
			bBList.setSupplier(officeSuppliesRegisterItem.getSupplier());
			bBList.setLowestVigilance(officeSuppliesRegisterItem.getLowestVigilance());
			bBList.setNumberOfRecipients(officeSuppliesRegisterItem.getNumberOfRecipients());
			bBList.setBorrowNumber(officeSuppliesRegisterItem.numberOfRecipients - officeSuppliesRegisterItem.getBorrowNumber());
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
	public String saveOrUpdateBorrow() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(officeSuppliesRegister.getId()) && !"0".equals(officeSuppliesRegister.getId())) {
				isSave = false;
			}
			/**索引 */
			String theme = officeSuppliesRegister.getTheme();
			String py = ChnToPinYin.getPYString(theme);
			officeSuppliesRegister.setChineseFirstLetter(py.toUpperCase());
			
			this.officeSuppliesRegister.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.officeSuppliesRegister.setUploadPerson(SecurityUtil.getCurrentUserName());
			/**拿到当前用户的姓名，保存*/
			officeSuppliesRegister.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			this.saveBaseEntity(this.officeSuppliesRegister);
			officeSuppliesRegister = baseHibernateService.merge(officeSuppliesRegister);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("officeSuppliesRegister.id," + SearchCondition.EQUAL, officeSuppliesRegister.getId());
			List<OfficeSuppliesBorrow> officeBorrowList = baseHibernateService.findAllByConditions(OfficeSuppliesBorrow.class, params);
			if (officeBorrowList != null && officeBorrowList.size() > 0) {
				for (OfficeSuppliesBorrow officeSuppliesBorrow : officeBorrowList) {
					if (officeSuppliesBorrow != null &&  officeSuppliesBorrow.getIsDeduction() != 1) {
						OfficeSupplies officeSupplies = officeSuppliesBorrow.getOfficeSupplies();
						officeSupplies.setCurrentInventory(officeSupplies.getCurrentInventory() - officeSuppliesBorrow.getBorrowNumber());
						officeSupplies = baseHibernateService.mergeOriginal(officeSupplies);
						if (officeSupplies != null) {
							officeSuppliesBorrow.setIsDeduction(1);
							baseHibernateService.mergeOriginal(officeSuppliesBorrow);
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
	

	/**
	 * 借用页面
	 */
	public void getOSBorrowJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				OfficeSuppliesRegister officeSuppliesRegister = officeSuppliesRegisterController.doListEntityById(id);
				if (null != officeSuppliesRegister) {
					json = convertListToJson(new ArrayList<OfficeSuppliesBorrow>(officeSuppliesRegister.getOfficeSuppliesBorrow()), officeSuppliesRegister.getOfficeSuppliesBorrow().size());
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
	
	
	/** 跳转到添加办公用品借用页面 */
	public String goAddOSBorrow() {
		
		return "goAddOSBorrow";
	}
	
	
	
	///////////
	/** 跳转办公归还 */
	public String goSaveOrUpdateBorrowList() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				officeSuppliesBorrow = baseHibernateService.findEntityById(OfficeSuppliesBorrow.class,id);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateBorrowList";
	}
	
	/** 处理图书归还明细修改操作  */
	public String saveOrUpdateBorrowList() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(officeSuppliesBorrow.getId()) && !"0".equals(officeSuppliesBorrow.getId())) {
				isSave = false;
			}
			System.out.println(officeSuppliesBorrow.getId()+"--------------------");
			OfficeSuppliesBorrow officeSuppliesBorrows = baseHibernateService.findEntityById(OfficeSuppliesBorrow.class, officeSuppliesBorrow.getId());
			System.out.println(officeSuppliesBorrows.getBorrowNumber()+"============================");
			/**借用总数量减去归还数量*/
			officeSuppliesBorrows.setBorrowNumber(officeSuppliesBorrows.getBorrowNumber() - officeSuppliesBorrow.getReturnNumber());
			/**多次归还数量相加*/
			officeSuppliesBorrows.setReturnNumber(officeSuppliesBorrows.returnNumber + officeSuppliesBorrow.getReturnNumber());
			baseHibernateService.merge(officeSuppliesBorrows);
			/**OfficeSupplies(库存)里的CurrentInventory(库存数量)加上,officeSuppliesBorrows里的ReturnNumber(归还数量)*/
			OfficeSupplies officeSupplies = officeSuppliesBorrows.getOfficeSupplies();
			officeSupplies.setCurrentInventory(officeSupplies.getCurrentInventory() + officeSuppliesBorrow.getReturnNumber());
			officeSupplies = baseHibernateService.mergeOriginal(officeSupplies);
			
			/** 办公用品每次领用归还明细 */
			OfficeList bBList = new OfficeList();
			bBList.setModel(officeSuppliesBorrow.getModel());
			bBList.setOfficeName(officeSuppliesBorrow.getOfficeName());
			bBList.setPrickle(officeSuppliesBorrow.getPrickle());
			bBList.setSupplier(officeSuppliesBorrow.getSupplier());
			bBList.setLowestVigilance(officeSuppliesBorrow.getLowestVigilance());
			bBList.setNumberOfRecipients(officeSuppliesBorrow.getNumberOfRecipients());
			bBList.setBorrowNumber(officeSuppliesBorrow.borrowNumber - officeSuppliesBorrow.getReturnNumber());
			bBList.setReturnNumber(officeSuppliesBorrow.getReturnNumber());
			OfficeSuppliesRegister officeSuppliesRegister = officeSuppliesBorrows.getOfficeSuppliesRegister();
			bBList.setCode(officeSuppliesRegister.getEncoding());
			bBList.setName(officeSuppliesRegister.getRecipientsWho());
			bBList.setOfficeSuppliesRegister(officeSuppliesRegister);
			baseHibernateService.save(bBList);
			System.out.println(officeSuppliesBorrows.getBorrowNumber()+"-"+officeSuppliesBorrow.getReturnNumber());
			/**officeSuppliesBorrows里的BorrowNumber等于0时,对OfficeSuppliesRegister里的IsTemp保存为3(3为归还) */
			if(officeSuppliesBorrows.getBorrowNumber()==0.0){
				OfficeSuppliesRegister officeSuppliesRegisters = officeSuppliesBorrows.getOfficeSuppliesRegister();
				officeSuppliesRegisters.setIsTemp("3");
				baseHibernateService.merge(officeSuppliesRegisters);
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
	
	/** 加载归还明细*/
	public String goBorrowList() {
		try {
			Map<String, Object> params = getParams();
			// 过滤临时数据
			/*params.put("isTemp," + SearchCondition.NOEQUAL, "1");*/
			String isTemp = getRequestParameter("isTemp");
			if (null != isTemp && !"".equals(isTemp)) {
				params.put("isTemp," + SearchCondition.EQUAL, isTemp);
			}
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("createTime");
				getPager().setOrderBy("desc");
			}
			//判断借用人和借用号不为空是才调用
			// 工号号
			String encoding = getRequestParameter("encoding");
			if (null != encoding && !"".equals(encoding)) {
				params.put("officeSuppliesRegister.encoding,"+SearchCondition.ANYLIKE, encoding);
			}
			// 借用人
			String recipientsWho = getRequestParameter("recipientsWho");
			if (null != recipientsWho && !"".equals(recipientsWho)) {
				recipientsWho = URLDecoder.decode(recipientsWho, "utf-8");
				params.put("officeSuppliesRegister.recipientsWho,"+SearchCondition.ANYLIKE, recipientsWho);
			}
			// 办公用品编码
			String model = getRequestParameter("model");
			if (null != model && !"".equals("model")) {
				params.put("model,"+SearchCondition.ANYLIKE, model);
			}
			Pager pager = null;
			if(params!=null)
			 pager = officeSuppliesRegisterController.doBorrowList(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goBorrowList";
	}
	
	/** 跳转办公用品归还明细*/
	public String goSuppliesReturnList() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				officeSuppliesBorrow = baseHibernateService.findEntityById(OfficeSuppliesBorrow.class,id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSuppliesReturnList";
	}
	
	/**
	 * 获取到借还办公用品明细
	 * @author chenzhengwen
	 * @date 2014-11-12上午10:38:12
	 * @return
	 */
	public String goSeenoticenotice() {
		try {
			officeSuppliesRegister = baseHibernateService.findEntityById(OfficeSuppliesRegister.class, id);
			officeSuppliesBorrow = baseHibernateService.findEntityById(OfficeSuppliesBorrow.class, id);
			logger.info("获取列表数据");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSeenoticenotice";
	}	
	
	/** 处理办公用品查看操作 */
	public String popNews() {
		return "popNews";
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

	public OfficeSuppliesRegister getOfficeSuppliesRegisters() {
		return officeSuppliesRegister;
	}

	public void setOfficeSuppliesRegisters(OfficeSuppliesRegister officeSuppliesRegisters) {
		this.officeSuppliesRegister = officeSuppliesRegisters;
	}

	public List<OfficeSuppliesRegister> getOfficeSuppliesRegisterList() {
		return officeSuppliesRegisterList;
	}

	public void setOfficeSuppliesRegisterList(List<OfficeSuppliesRegister> officeSuppliesRegisterList) {
		this.officeSuppliesRegisterList = officeSuppliesRegisterList;
	}

	public OfficeSuppliesRegister getOfficeSuppliesRegister() {
		return officeSuppliesRegister;
	}

	public void setOfficeSuppliesRegister(OfficeSuppliesRegister officeSuppliesRegister) {
		this.officeSuppliesRegister = officeSuppliesRegister;
	}

	public OfficeSuppliesRegisterItem getOfficeSuppliesRegisterItem() {
		return officeSuppliesRegisterItem;
	}

	public void setOfficeSuppliesRegisterItem(OfficeSuppliesRegisterItem officeSuppliesRegisterItem) {
		this.officeSuppliesRegisterItem = officeSuppliesRegisterItem;
	}

	public OfficeSupplies getOfficeSupplies() {
		return officeSupplies;
	}

	public void setOfficeSupplies(OfficeSupplies officeSupplies) {
		this.officeSupplies = officeSupplies;
	}

	public OfficeSuppliesBorrow getOfficeSuppliesBorrow() {
		return officeSuppliesBorrow;
	}

	public void setOfficeSuppliesBorrow(OfficeSuppliesBorrow officeSuppliesBorrow) {
		this.officeSuppliesBorrow = officeSuppliesBorrow;
	}

	public OfficeList getOfficeList() {
		return officeList;
	}

	public void setOfficeList(OfficeList officeList) {
		this.officeList = officeList;
	}

}
