package com.vix.nvix.sales.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.billtype.BillType;
import com.vix.common.security.util.DaysUtils;
import com.vix.common.share.entity.BaseEntity;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.ebusiness.order.orderProcess.controller.OrderProcessController;
import com.vix.nvix.common.base.action.VixntBaseAction;
/**
 * 销售退货
 * @author jackie
 *
 */
import com.vix.sales.delivery.entity.SaleReturnForm;
import com.vix.sales.delivery.entity.SaleReturnFormItem;

import net.sf.json.JSONObject;
@Controller
@Scope("prototype")
public class NvixntSaleReturnFormAction extends VixntBaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private OrderProcessController orderProcessController;
	private String id;
	private SaleReturnForm saleReturnForm;
	private SaleReturnFormItem saleReturnFormItem;
	private String isAllowAudit;// 是否允许提交审批 1：是 0：否
	private List<BaseEntity> baseEntityList;
	private String str;
	public void goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			String name = getDecodeRequestParameter("name");
			if(StringUtils.isNotEmpty(name)) {
				params.put("name,"+SearchCondition.ANYLIKE, name);
			}
			String code = getDecodeRequestParameter("code");
			if(StringUtils.isNotEmpty(code)) {
				params.put("code,"+SearchCondition.ANYLIKE, code);
			}
			String customerId = getDecodeRequestParameter("customerId");
			if(StringUtils.isNotEmpty(customerId)) {
				params.put("customerAccount.id,"+SearchCondition.EQUAL, customerId);
			}
			String createTime = getDecodeRequestParameter("createTime");
			if(StringUtils.isNotEmpty(createTime)){
				params.put("returnTime," + SearchCondition.BETWEENAND, DaysUtils.getBeginDay(dateFormat.parse(createTime.trim())) + "!" + DaysUtils.getEndDay(dateFormat.parse(createTime.trim())));
			}
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("returnTime");
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, SaleReturnForm.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String goSaveOrUpdate() {
		try {
			isAllowAudit = isAllowAudit(BillType.SA_RETURN);
			if(StringUtils.isNotEmpty(id)) {
				saleReturnForm = vixntBaseService.findEntityById(SaleReturnForm.class, id);
			}else {
				saleReturnForm = new SaleReturnForm();
				saleReturnForm.setCode(autoCreateCode.getBillNO(BillType.SA_RETURN));
				saleReturnForm.setReturnTime(new Date());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	
	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if(StringUtils.isNotEmpty(saleReturnForm.getId())) {
				isSave = false;
				saleReturnForm.setUpdateTime(new Date());
			}else {
				saleReturnForm.setCreateTime(new Date());
			}
			saleReturnForm.setStatus("1");//待退货
			initEntityBaseController.initEntityBaseAttribute(saleReturnForm);
			saleReturnForm = vixntBaseService.merge(saleReturnForm);
			if(isSave) {
				renderText("0:"+SAVE_SUCCESS+":"+saleReturnForm.getId());
			}else {
				renderText("0:"+UPDATE_SUCCESS+":"+saleReturnForm.getId());
			}
		} catch (Exception e) {
			if(isSave) {
				renderText("1:"+SAVE_FAIL);
			}else {
				renderText("1:"+UPDATE_FAIL);
			}
			e.printStackTrace();
		}
	}
	public void audit() {
		try {
			saleReturnForm.setStatus("1");//待退货
			initEntityBaseController.initEntityBaseAttribute(saleReturnForm);
			saleReturnForm = vixntBaseService.merge(saleReturnForm);
			if ("1".equals(isAllowAudit(BillType.SA_ORDER))) {
				String response = dealStartAndSubmitByBillsCode(BillType.SA_RETURN, saleReturnForm);
				if(StringUtils.isNotEmpty(response)){
					JSONObject json = JSONObject.fromObject(response);
					if (json.has("status")) {
						if("1".equals(json.getString("status"))){
							saleReturnForm.setApprovalStatus("1");
							saleReturnForm = vixntBaseService.merge(saleReturnForm);
							renderText(saleReturnForm.getId()+":提交成功!");
						}
					}
				}
			} else {
				renderText("提交失败!未绑定审批流程!");
			}
		} catch (Exception e) {
			renderText("提交失败!");
			e.printStackTrace();
		}
	}
	
	public void deleteById() {
		try {
			if(StringUtils.isNotEmpty(id)) {
				saleReturnForm = vixntBaseService.findEntityById(SaleReturnForm.class, id);
				if(saleReturnForm != null) {
					vixntBaseService.deleteByEntity(saleReturnForm);
					renderText(DELETE_SUCCESS);
				}else {
					renderText("未找到对应的单据!");
				}
			}
		} catch (Exception e) {
			renderText(DELETE_FAIL);
			e.printStackTrace();
		}
	}
	
	public void goSingleSaleReturnFormItemList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			String name = getDecodeRequestParameter("name");
			if(StringUtils.isNotEmpty(name)) {
				params.put("item.name,"+SearchCondition.ANYLIKE, name);
			}
			if(StringUtils.isNotEmpty(id)) {
				params.put("saleReturnForm.id,"+SearchCondition.EQUAL, id);
				pager = vixntBaseService.findPagerByHqlConditions(pager, SaleReturnFormItem.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String goSaveOrUpdateSaleReturnFormItem() {
		try {
			if(StringUtils.isNotEmpty(id)) {
				saleReturnFormItem = vixntBaseService.findEntityById(SaleReturnFormItem.class, id);
			}else {
				saleReturnFormItem = new SaleReturnFormItem();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateSaleReturnFormItem";
	}
	
	public void saveOrUpdateSaleReturnFormItem() {
		boolean isSave = true;
		try {
			if(StringUtils.isNotEmpty(saleReturnFormItem.getId())) {
				isSave = false;
			}
			if(saleReturnFormItem.getSaleReturnForm() != null) {
				List<SaleReturnFormItem> saleReturnFormItems = vixntBaseService.findAllByEntityClassAndAttribute(SaleReturnFormItem.class, "saleReturnForm.id", saleReturnFormItem.getSaleReturnForm().getId());
				if(saleReturnFormItems != null && saleReturnFormItems.size() > 0) {
					for (SaleReturnFormItem srm : saleReturnFormItems) {
						if(null != saleReturnFormItem.getItem() && srm.getItem() != null) {
							if(srm.getItem().getId().equals(saleReturnFormItem.getItem().getId())) {
								srm.setCount(saleReturnFormItem.getCount()+srm.getCount());
								srm.setNetTotal(srm.getPrice() * srm.getCount());
								srm = vixntBaseService.merge(srm);
							}else {
								saleReturnFormItem.setItemCode(saleReturnFormItem.getItem().getCode());
								saleReturnFormItem.setItemName(saleReturnFormItem.getItem().getName());
								if(null != saleReturnFormItem.getItem().getItemCatalog()) {
									saleReturnFormItem.setItemType(saleReturnFormItem.getItem().getItemCatalog().getName());
								}
								if(null != saleReturnFormItem.getPrice() && null != saleReturnFormItem.getCount()) {
									saleReturnFormItem.setNetTotal(saleReturnFormItem.getPrice() * saleReturnFormItem.getCount());
								}
								initEntityBaseController.initEntityBaseAttribute(saleReturnFormItem);
								saleReturnFormItem =vixntBaseService.merge(saleReturnFormItem);
							}
						}
					}
				}else {
					if(null != saleReturnFormItem.getItem()) {
						saleReturnFormItem.setItemCode(saleReturnFormItem.getItem().getCode());
						saleReturnFormItem.setItemName(saleReturnFormItem.getItem().getName());
						if(null != saleReturnFormItem.getItem().getItemCatalog()) {
							saleReturnFormItem.setItemType(saleReturnFormItem.getItem().getItemCatalog().getName());
						}
					}
					if(null != saleReturnFormItem.getPrice() && null != saleReturnFormItem.getCount()) {
						saleReturnFormItem.setNetTotal(saleReturnFormItem.getPrice() * saleReturnFormItem.getCount());
					}
					initEntityBaseController.initEntityBaseAttribute(saleReturnFormItem);
					saleReturnFormItem =vixntBaseService.merge(saleReturnFormItem);
				}
			}
			
			if(isSave) {
				renderText("0:"+SAVE_SUCCESS);
			}else {
				renderText("0:"+UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(isSave) {
				renderText("1:"+SAVE_FAIL);
			}else {
				renderText("1:"+UPDATE_FAIL);
			}
		}
	}
	public void deleteBySaleReturnItemId() {
		try {
			if(StringUtils.isNotEmpty(id)) {
				saleReturnFormItem = vixntBaseService.findEntityById(SaleReturnFormItem.class, id);
				if(saleReturnFormItem != null) {
					vixntBaseService.deleteByEntity(saleReturnFormItem);
					renderText(DELETE_SUCCESS);
				}else {
					renderText("未找到对应的单据!");
				}
			}
		} catch (Exception e) {
			renderText(DELETE_FAIL);
			e.printStackTrace();
		}
	}
	public String goShowBeforeAndAfter() {
		try {
			if (StrUtils.isNotEmpty(id) && !"0".equals(id)) {
				Map<String, Object> params = new HashMap<String, Object>();
				saleReturnForm = baseHibernateService.findEntityById(SaleReturnForm.class,id);
				if (saleReturnForm != null && saleReturnForm.getCreateTime() != null) {
					params.put("createTime", formatDateToTimeString(saleReturnForm.getCreateTime()));
					params.put("isTemp", "0");
					params.put("isDelete", "0");
					if (StrUtils.isNotEmpty(str)) {
						if ("before".equals(str)) {
							saleReturnForm = (SaleReturnForm) orderProcessController.doListBeforeAndAfterEntity(formatDateToTimeString(saleReturnForm.getCreateTime()), params, saleReturnForm, "before");
						} else if ("after".equals(str)) {
							saleReturnForm = (SaleReturnForm) orderProcessController.doListBeforeAndAfterEntity(formatDateToTimeString(saleReturnForm.getCreateTime()), params, saleReturnForm, "after");
						}
					}
					if (saleReturnForm == null || StrUtils.isEmpty(saleReturnForm.getId())) {
						saleReturnForm = baseHibernateService.findEntityById(SaleReturnForm.class,id);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "show";
	}
	public String show() {
		try {
			if(StringUtils.isNotEmpty(id)) {
				saleReturnForm = vixntBaseService.findEntityById(SaleReturnForm.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "show";
	}
	public String goAudit() {
		try {
			if(StringUtils.isNotEmpty(id)) {
				saleReturnForm = vixntBaseService.findEntityById(SaleReturnForm.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goAudit";
	}
	public String printSaleReturnForm() {
		try {
			if(StringUtils.isNotEmpty(id)) {
				saleReturnForm = vixntBaseService.findEntityById(SaleReturnForm.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "printSaleReturnForm";
	}
	public String goSourceList() {
		try {
			if(StringUtils.isNotEmpty(id)) {
				saleReturnForm = vixntBaseService.findEntityById(SaleReturnForm.class, id);
				if(saleReturnForm != null) {
					baseEntityList = new ArrayList<>();
					baseEntityList.add(saleReturnForm);
					if(StringUtils.isNotEmpty(saleReturnForm.getSourceBillCode())&&StringUtils.isNotEmpty(saleReturnForm.getSourceClassName())) {
						getSourceBaseEntity(baseEntityList, saleReturnForm);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSourceList";
	}
	@Override
	public String getId() {
		return id;
	}
	@Override
	public void setId(String id) {
		this.id = id;
	}
	public SaleReturnForm getSaleReturnForm() {
		return saleReturnForm;
	}
	public void setSaleReturnForm(SaleReturnForm saleReturnForm) {
		this.saleReturnForm = saleReturnForm;
	}
	public SaleReturnFormItem getSaleReturnFormItem() {
		return saleReturnFormItem;
	}
	public void setSaleReturnFormItem(SaleReturnFormItem saleReturnFormItem) {
		this.saleReturnFormItem = saleReturnFormItem;
	}
	public String getIsAllowAudit() {
		return isAllowAudit;
	}
	public void setIsAllowAudit(String isAllowAudit) {
		this.isAllowAudit = isAllowAudit;
	}

	public List<BaseEntity> getBaseEntityList() {
		return baseEntityList;
	}

	public void setBaseEntityList(List<BaseEntity> baseEntityList) {
		this.baseEntityList = baseEntityList;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}
}
