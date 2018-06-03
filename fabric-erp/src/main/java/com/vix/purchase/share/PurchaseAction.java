package com.vix.purchase.share;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vix.common.base.action.BaseAction;
import com.vix.common.id.VixUUID;
import com.vix.common.share.entity.BaseEntity;
import com.vix.common.share.entity.BillGroupCode;
import com.vix.common.share.entity.MeasureUnit;
import com.vix.core.utils.BeanCopyUtils;
import com.vix.core.utils.StrUtils;
import com.vix.inventory.warehouse.entity.InvWarehouse;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.purchase.apply.entity.PurchaseApply;
import com.vix.mdm.purchase.apply.entity.PurchaseApplyDetails;
import com.vix.mdm.purchase.arrivalmgr.entity.PurchaseArrival;
import com.vix.mdm.purchase.arrivalmgr.entity.PurchaseArrivalItems;
import com.vix.mdm.purchase.inbound.entity.PurchaseInbound;
import com.vix.mdm.purchase.inbound.entity.PurchaseInboundItems;
import com.vix.mdm.purchase.inquire.entity.PurchaseInquire;
import com.vix.mdm.purchase.inquire.entity.PurchaseInquiryDetail;
import com.vix.mdm.purchase.order.entity.PurchaseOrder;
import com.vix.mdm.purchase.order.entity.PurchaseOrderLineItem;
import com.vix.mdm.purchase.pursreturn.entity.PurchaseReturn;
import com.vix.mdm.purchase.pursreturn.entity.PurchaseReturnItems;

public class PurchaseAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected List<InvWarehouse> invWarehouseList;

	protected List<?> indexEntityList;

	String copyTo;
	String itemDetails;

	int viewOnly;

	public Map<String, Object> loadItemInfoMap(String id) {
		Map<String, Object> info = new HashMap<String, Object>();
		try {
			if (id != null) {
				Item item = this.baseHibernateService.findEntityById(Item.class, id);

				info.put("id", item.getId());
				info.put("code", item.getCode());
				info.put("name", item.getName());
				info.put("price", item.getPrice());
				MeasureUnit mu = item.getMeasureUnit();
				if (mu != null) {
					info.put("unit", mu.getName());
				}
				info.put("skuCode", item.getSkuCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return info;
	}

	public String genNewGroupCode(String billType, String billCode, String groupCode) {
		BillGroupCode bgc = new BillGroupCode();
		/** 单据类型 */
		bgc.setBillType(billType);
		/** 单据编码 */
		bgc.setBillCode(billCode);

		/** 成组码 */
		;
		if (StrUtils.isEmpty(groupCode))
			groupCode = VixUUID.getUUID();
		bgc.setGroupCode(groupCode);

		try {
			this.baseHibernateService.save(bgc);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return groupCode;
	}

	public void setEntityDeleteValue(BaseEntity entity) {
		if (entity != null) {
			entity.setEndTime(new Date());
			//entity.setIsDeleted("1");
		}
	}

	/*public void addQueryDeleteParam(Map<String,Object> params)
	{
		params.put("isDeleted," + SearchCondition.NOEQUAL, "1");
	}*/

	public List<InvWarehouse> loadInvWarehouseList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			return this.baseHibernateService.findAllByConditions(InvWarehouse.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public BaseEntity copyPurchaseEntity(String sourceType, String sourceId, String targetType) {
		BaseEntity targetEntity = this.genNewPurchaseEntity(targetType);

		BaseEntity sourceEntity = this.loadSourceEntityById(sourceType, sourceId);
		if (sourceEntity != null && targetEntity != null) {
			try {
				//BeanCopyUtils.getBean(targetEntity, sourceEntity);
				BeanCopyUtils.Copy(sourceEntity, targetEntity);

				targetEntity.setId(null);
				this.baseHibernateService.save(targetEntity);

				this.copyPurchaseItemDetails(sourceType, sourceEntity, targetType, targetEntity);
			} catch (Exception ce) {
				ce.printStackTrace();
			}
		}

		return targetEntity;
	}

	private void copyPurchaseItemDetails(String sourceType, BaseEntity sourceEntity, String targetType, BaseEntity targetEntity) {
		//itemDetails是处理那些item需要复制，amount是多少，后期增加，所以没有从参数传进来
		Map<String, Double> itemMap = null;
		if (StrUtils.isNotEmpty(this.itemDetails)) {
			itemMap = new HashMap<String, Double>();
			String[] strvals = this.itemDetails.split(",");
			for (String str : strvals) {
				String[] idAndAmount = str.split("_");
				if (idAndAmount.length > 1) {
					try {
						String itemId = String.valueOf(idAndAmount[0]);
						Double amount = Double.parseDouble(idAndAmount[1]);

						itemMap.put(itemId, amount);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

		if ("apply".equals(sourceType)) {
			PurchaseApply sEntity = (PurchaseApply) sourceEntity;
			for (PurchaseApplyDetails sourceItem : sEntity.getPurchaseApplyDetails()) {
				if (itemMap == null || !itemMap.containsKey(sourceItem.getId())) {
					continue;
				}

				BaseEntity newItem = this.addNewPurchaseItem(targetType, targetEntity);
				try {
					BeanCopyUtils.getBean(newItem, sourceItem);
					newItem.setId(null);
					if (itemMap != null) {
						//newItem.setAmount(itemMap.get(sourceItem.getId()));
					}
					this.baseHibernateService.save(newItem);
				} catch (Exception ce) {
					ce.printStackTrace();
					continue;
				}
			}

		} else if ("inquire".equals(sourceType)) {
			PurchaseInquire sEntity = (PurchaseInquire) sourceEntity;
			for (PurchaseInquiryDetail sourceItem : sEntity.getPurchaseInquiryDetails()) {
				if (itemMap == null || !itemMap.containsKey(sourceItem.getId())) {
					continue;
				}

				BaseEntity newItem = this.addNewPurchaseItem(targetType, targetEntity);
				try {
					BeanCopyUtils.getBean(newItem, sourceItem);
					newItem.setId(null);
					if (itemMap != null) {
						//newItem.setAmount(itemMap.get(sourceItem.getId()));
					}
					this.baseHibernateService.save(newItem);
				} catch (Exception ce) {
					ce.printStackTrace();
					continue;
				}
			}
		} else if ("order".equals(sourceType)) {
			PurchaseOrder sEntity = (PurchaseOrder) sourceEntity;
			for (PurchaseOrderLineItem sourceItem : sEntity.getPurchaseOrderLineItems()) {
				if (itemMap == null || !itemMap.containsKey(sourceItem.getId())) {
					continue;
				}

				BaseEntity newItem = this.addNewPurchaseItem(targetType, targetEntity);
				try {
					BeanCopyUtils.getBean(newItem, sourceItem);
					newItem.setId(null);
					if (itemMap != null) {
						//newItem.setAmount(itemMap.get(sourceItem.getId()));
					}
					this.baseHibernateService.save(newItem);
				} catch (Exception ce) {
					ce.printStackTrace();
					continue;
				}
			}
		} else if ("arrival".equals(sourceType)) {
			PurchaseArrival sEntity = (PurchaseArrival) sourceEntity;
			for (PurchaseArrivalItems sourceItem : sEntity.getPurchaseArrivalItems()) {
				if (itemMap == null || !itemMap.containsKey(sourceItem.getId())) {
					continue;
				}

				BaseEntity newItem = this.addNewPurchaseItem(targetType, targetEntity);
				try {
					BeanCopyUtils.getBean(newItem, sourceItem);
					newItem.setId(null);
					if (itemMap != null) {
						//newItem.setAmount(itemMap.get(sourceItem.getId()));
					}
					this.baseHibernateService.save(newItem);
				} catch (Exception ce) {
					ce.printStackTrace();
					continue;
				}
			}
		} else if ("inbound".equals(sourceType)) {
			PurchaseInbound sEntity = (PurchaseInbound) sourceEntity;
			for (PurchaseInboundItems sourceItem : sEntity.getPurchaseInboundItems()) {
				if (itemMap == null || !itemMap.containsKey(sourceItem.getId())) {
					continue;
				}

				BaseEntity newItem = this.addNewPurchaseItem(targetType, targetEntity);
				try {
					BeanCopyUtils.getBean(newItem, sourceItem);
					newItem.setId(null);
					if (itemMap != null) {
						//newItem.setAmount(itemMap.get(sourceItem.getId()));
					}
					this.baseHibernateService.save(newItem);
				} catch (Exception ce) {
					ce.printStackTrace();
					continue;
				}
			}
		} else if ("return".equals(sourceType)) {
			PurchaseReturn sEntity = (PurchaseReturn) sourceEntity;
			for (PurchaseReturnItems sourceItem : sEntity.getPurchaseReturnItems()) {
				if (itemMap == null || !itemMap.containsKey(sourceItem.getId())) {
					continue;
				}

				BaseEntity newItem = this.addNewPurchaseItem(targetType, targetEntity);
				try {
					BeanCopyUtils.getBean(newItem, sourceItem);
					newItem.setId(null);
					if (itemMap != null) {
						//newItem.setAmount(itemMap.get(sourceItem.getId()));
					}
					this.baseHibernateService.save(newItem);
				} catch (Exception ce) {
					ce.printStackTrace();
					continue;
				}
			}
		}
	}

	private BaseEntity addNewPurchaseItem(String purchaseType, BaseEntity purchaseEntity) {
		if ("apply".equals(purchaseType)) {
			PurchaseApplyDetails item = new PurchaseApplyDetails();
			item.setPurchaseApply((PurchaseApply) purchaseEntity);
			return item;
		} else if ("inquire".equals(purchaseType)) {
			PurchaseInquiryDetail item = new PurchaseInquiryDetail();
			item.setPurchaseInquire((PurchaseInquire) purchaseEntity);
			return item;
		} else if ("order".equals(purchaseType)) {
			PurchaseOrderLineItem item = new PurchaseOrderLineItem();
			item.setPurchaseOrder((PurchaseOrder) purchaseEntity);
			return item;
		} else if ("arrival".equals(purchaseType)) {
			PurchaseArrivalItems item = new PurchaseArrivalItems();
			item.setPurchaseArrival((PurchaseArrival) purchaseEntity);
			return item;
		} else if ("inbound".equals(purchaseType)) {
			PurchaseInboundItems item = new PurchaseInboundItems();
			item.setPurchaseInbound((PurchaseInbound) purchaseEntity);
			return item;
		} else if ("return".equals(purchaseType)) {
			PurchaseReturnItems item = new PurchaseReturnItems();
			item.setPurchaseReturn((PurchaseReturn) purchaseEntity);
			return item;
		}

		return null;
	}

	private BaseEntity loadSourceEntityById(String purchaseType, String purchaseId) {
		BaseEntity entity = null;
		try {
			if ("apply".equals(purchaseType)) {
				entity = this.baseHibernateService.findEntityById(PurchaseApply.class, purchaseId);
			} else if ("inquire".equals(purchaseType)) {
				entity = this.baseHibernateService.findEntityById(PurchaseInquire.class, purchaseId);
			} else if ("order".equals(purchaseType)) {
				entity = this.baseHibernateService.findEntityById(PurchaseOrder.class, purchaseId);
			} else if ("arrival".equals(purchaseType)) {
				entity = this.baseHibernateService.findEntityById(PurchaseArrival.class, purchaseId);
			} else if ("inbound".equals(purchaseType)) {
				entity = this.baseHibernateService.findEntityById(PurchaseInbound.class, purchaseId);
			} else if ("return".equals(purchaseType)) {
				entity = this.baseHibernateService.findEntityById(PurchaseReturn.class, purchaseId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}

	private BaseEntity genNewPurchaseEntity(String purchaseType) {
		BaseEntity entity = null;
		if ("apply".equals(purchaseType)) {
			entity = new PurchaseApply();
		} else if ("inquire".equals(purchaseType)) {
			entity = new PurchaseInquire();
		} else if ("order".equals(purchaseType)) {
			entity = new PurchaseOrder();
		} else if ("arrival".equals(purchaseType)) {
			entity = new PurchaseArrival();
		} else if ("inbound".equals(purchaseType)) {
			entity = new PurchaseInbound();
		} else if ("return".equals(purchaseType)) {
			entity = new PurchaseReturn();
		}
		return entity;
	}

	public List<InvWarehouse> getInvWarehouseList() {
		return invWarehouseList;
	}

	public void setInvWarehouseList(List<InvWarehouse> invWarehouseList) {
		this.invWarehouseList = invWarehouseList;
	}

	public List<?> getIndexEntityList() {
		return indexEntityList;
	}

	public void setIndexEntityList(List<?> indexEntityList) {
		this.indexEntityList = indexEntityList;
	}

	public String getCopyTo() {
		return copyTo;
	}

	public void setCopyTo(String copyTo) {
		this.copyTo = copyTo;
	}

	public String getItemDetails() {
		return itemDetails;
	}

	public void setItemDetails(String itemDetails) {
		this.itemDetails = itemDetails;
	}

	public int getViewOnly() {
		return viewOnly;
	}

	public void setViewOnly(int viewOnly) {
		this.viewOnly = viewOnly;
	}

}
