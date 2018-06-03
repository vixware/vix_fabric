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
import com.vix.sales.delivery.entity.DeliveryDocument;
import com.vix.sales.delivery.entity.DeliveryDocumentItem;

import net.sf.json.JSONObject;
/**
 * 销售发货
 * @author jackie
 *
 */
@Controller
@Scope("prototype")
public class NvixntDeliveryDocumentAction extends VixntBaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private OrderProcessController orderProcessController;
	private String id;
	private DeliveryDocument deliveryDocument;
	private DeliveryDocumentItem deliveryDocumentItem;
	private String isAllowAudit;
	private List<BaseEntity> baseEntityList;
	private String str;
	
	
	@SuppressWarnings("unchecked")
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
				params.put("shippedDate," + SearchCondition.BETWEENAND, DaysUtils.getBeginDay(dateFormat.parse(createTime.trim())) + "!" + DaysUtils.getEndDay(dateFormat.parse(createTime.trim())));
			}
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("billDate");
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, DeliveryDocument.class, params);
			if(pager.getResultList().size() < 10) {
				int count = pager.getPageSize() - pager.getResultList().size();
				for (int i = 0; i < count; i++) {
					pager.getResultList().add(new DeliveryDocument());
				}
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String goSaveOrUpdate() {
		try {
			isAllowAudit = isAllowAudit(BillType.SA_DELIVERY);
			if(StringUtils.isNotEmpty(id)) {
				deliveryDocument = vixntBaseService.findEntityById(DeliveryDocument.class, id);
			}else {
				deliveryDocument = new DeliveryDocument();
				deliveryDocument.setCode(autoCreateCode.getBillNO(BillType.SA_DELIVERY));
				deliveryDocument.setBillDate(new Date());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	
	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if(StringUtils.isNotEmpty(deliveryDocument.getId())) {
				isSave = false;
				deliveryDocument.setUpdateTime(new Date());
			}else {
				deliveryDocument.setCreateTime(new Date());
			}
			deliveryDocument.setBillDate(new Date());
			deliveryDocument.setDeliveryStatus("1");//待收货
			initEntityBaseController.initEntityBaseAttribute(deliveryDocument);
			deliveryDocument = vixntBaseService.merge(deliveryDocument);
			if(isSave) {
				renderText("0:"+SAVE_SUCCESS+":"+deliveryDocument.getId());
			}else {
				renderText("0:"+UPDATE_SUCCESS+":"+deliveryDocument.getId());
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
			deliveryDocument.setUpdateTime(new Date());
			deliveryDocument.setCreateTime(new Date());
			deliveryDocument.setBillDate(new Date());
			deliveryDocument.setDeliveryStatus("1");//待收货
			initEntityBaseController.initEntityBaseAttribute(deliveryDocument);
			deliveryDocument = vixntBaseService.merge(deliveryDocument);
			if ("1".equals(isAllowAudit(BillType.SA_DELIVERY))) {
				String response = dealStartAndSubmitByBillsCode(BillType.SA_DELIVERY, deliveryDocument);
				if(StringUtils.isNotEmpty(response)){
					JSONObject json = JSONObject.fromObject(response);
					if (json.has("status")) {
						if("1".equals(json.getString("status"))){
							deliveryDocument.setStatus("1");
							deliveryDocument = vixntBaseService.merge(deliveryDocument);
							renderText(deliveryDocument.getId()+":提交成功!");
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
				deliveryDocument = vixntBaseService.findEntityById(DeliveryDocument.class, id);
				if(deliveryDocument != null) {
					vixntBaseService.deleteByEntity(deliveryDocument);
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
	
	public void goSingleDeliveryDocumentItemList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			String name = getDecodeRequestParameter("name");
			if(StringUtils.isNotEmpty(name)) {
				params.put("item.name,"+SearchCondition.ANYLIKE, name);
			}
			if(StringUtils.isNotEmpty(id)) {
				params.put("deliveryDocument.id,"+SearchCondition.EQUAL, id);
				pager = vixntBaseService.findPagerByHqlConditions(pager, DeliveryDocumentItem.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String goSaveOrUpdateDeliveryDocumentItem() {
		try {
			if(StringUtils.isNotEmpty(id)) {
				deliveryDocumentItem = vixntBaseService.findEntityById(DeliveryDocumentItem.class, id);
			}else {
				deliveryDocumentItem = new DeliveryDocumentItem();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateDeliveryDocumentItem";
	}
	public void saveOrUpdateDeliveryDocumentItem() {
		boolean isSave = true;
		try {
			if(StringUtils.isNotEmpty(deliveryDocumentItem.getId())) {
				isSave = false;
			}
			if(deliveryDocumentItem.getDeliveryDocument() != null) {
				List<DeliveryDocumentItem> deliveryDocumentItems = vixntBaseService.findAllByEntityClassAndAttribute(DeliveryDocumentItem.class, "deliveryDocument.id", deliveryDocumentItem.getDeliveryDocument().getId());
				if(deliveryDocumentItems != null && deliveryDocumentItems.size() > 0) {
					for (DeliveryDocumentItem ddi : deliveryDocumentItems) {
						if(deliveryDocumentItem.getItem().getId().equals(ddi.getItem().getId())) {
							ddi.setCount(ddi.getCount()+deliveryDocumentItem.getCount());
							ddi = vixntBaseService.merge(ddi);
						}else {
							deliveryDocumentItem.setItemCode(deliveryDocumentItem.getItem().getCode());
							deliveryDocumentItem.setItemName(deliveryDocumentItem.getItem().getName());
							if(deliveryDocumentItem.getItem().getItemCatalog() != null) {
								deliveryDocumentItem.setItemType(deliveryDocumentItem.getItem().getItemCatalog().getName());
							}
							deliveryDocumentItem.setTaxAmount(deliveryDocumentItem.getCount() * deliveryDocumentItem.getPrice());
							deliveryDocumentItem = vixntBaseService.merge(deliveryDocumentItem);
						}
					}
				}else {
					deliveryDocumentItem.setItemCode(deliveryDocumentItem.getItem().getCode());
					deliveryDocumentItem.setItemName(deliveryDocumentItem.getItem().getName());
					if(deliveryDocumentItem.getItem().getItemCatalog() != null) {
						deliveryDocumentItem.setItemType(deliveryDocumentItem.getItem().getItemCatalog().getName());
					}
					deliveryDocumentItem.setTaxTotal(deliveryDocumentItem.getCount() * deliveryDocumentItem.getPrice());
					deliveryDocumentItem = vixntBaseService.merge(deliveryDocumentItem);
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
	public void deleteDeliveryDocumentItemById() {
		try {
			if(StringUtils.isNotEmpty(id)) {
				DeliveryDocumentItem deliveryDocumentItem = vixntBaseService.findEntityById(DeliveryDocumentItem.class, id);
				if(deliveryDocumentItem != null) {
					vixntBaseService.deleteByEntity(deliveryDocumentItem);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			renderText(DELETE_FAIL);
			e.printStackTrace();
		}
	}
	
	public void calculateAmount() {
		try {
			Double amount = 0.0d;
			if(StringUtils.isNotEmpty(id)) {
				List<DeliveryDocumentItem> deliveryDocumentItems = vixntBaseService.findAllByEntityClassAndAttribute(DeliveryDocumentItem.class, "deliveryDocument.id", id);
				if(deliveryDocumentItems != null && deliveryDocumentItems.size() > 0) {
					for (DeliveryDocumentItem deliveryDocumentItem : deliveryDocumentItems) {
						amount += deliveryDocumentItem.getTaxTotal();
					}
				}
			}
			renderText(amount.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String goShowBeforeAndAfter() {
		try {
			if (StrUtils.isNotEmpty(id) && !"0".equals(id)) {
				Map<String, Object> params = new HashMap<String, Object>();
				deliveryDocument = baseHibernateService.findEntityById(DeliveryDocument.class,id);
				if (deliveryDocument != null && deliveryDocument.getCreateTime() != null) {
					params.put("createTime", formatDateToTimeString(deliveryDocument.getCreateTime()));
					params.put("isTemp", "0");
					params.put("isDelete", "0");
					if (StrUtils.isNotEmpty(str)) {
						if ("before".equals(str)) {
							deliveryDocument = (DeliveryDocument) orderProcessController.doListBeforeAndAfterEntity(formatDateToTimeString(deliveryDocument.getCreateTime()), params, deliveryDocument, "before");
						} else if ("after".equals(str)) {
							deliveryDocument = (DeliveryDocument) orderProcessController.doListBeforeAndAfterEntity(formatDateToTimeString(deliveryDocument.getCreateTime()), params, deliveryDocument, "after");
						}
					}
					if (deliveryDocument == null || StrUtils.isEmpty(deliveryDocument.getId())) {
						deliveryDocument = baseHibernateService.findEntityById(DeliveryDocument.class,id);
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
				deliveryDocument = vixntBaseService.findEntityById(DeliveryDocument.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "show";
	}
	public String goAudit() {
		try {
			if(StringUtils.isNotEmpty(id)) {
				deliveryDocument = vixntBaseService.findEntityById(DeliveryDocument.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goAudit";
	}
	public String printDeliveryDocument() {
		try {
			if(StringUtils.isNotEmpty(id)) {
				deliveryDocument = vixntBaseService.findEntityById(DeliveryDocument.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "printDeliveryDocument";
	}
	public String goSourceList() {
		try {
			if(StringUtils.isNotEmpty(id)) {
				deliveryDocument = vixntBaseService.findEntityById(DeliveryDocument.class, id);
				if(deliveryDocument != null) {
					baseEntityList = new ArrayList<>();
					baseEntityList.add(deliveryDocument);
					if(StringUtils.isNotEmpty(deliveryDocument.getSourceBillCode())&&StringUtils.isNotEmpty(deliveryDocument.getSourceClassName())) {
						getSourceBaseEntity(baseEntityList, deliveryDocument);
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
	public DeliveryDocument getDeliveryDocument() {
		return deliveryDocument;
	}
	public void setDeliveryDocument(DeliveryDocument deliveryDocument) {
		this.deliveryDocument = deliveryDocument;
	}
	public DeliveryDocumentItem getDeliveryDocumentItem() {
		return deliveryDocumentItem;
	}
	public void setDeliveryDocumentItem(DeliveryDocumentItem deliveryDocumentItem) {
		this.deliveryDocumentItem = deliveryDocumentItem;
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
