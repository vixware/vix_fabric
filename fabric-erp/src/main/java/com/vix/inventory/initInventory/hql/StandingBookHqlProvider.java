package com.vix.inventory.initInventory.hql;

import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vix.core.persistence.hibernate.hql.HqlProvider;

@Component
@Scope("prototype")
public class StandingBookHqlProvider extends HqlProvider {

	@Override
	public String entityAsName() {
		return "hentity";
	}

	public StringBuilder findInventoryCurrentStockList(Map<String, Object> params) {

		String ename = entityAsName();
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename);
		hql.append(" from InventoryCurrentStock ").append(ename);
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("isTemp")) {
				Object isTemp = params.get("isTemp");
				if (isTemp == null) {
					hql.append(" and ").append(ename).append(".isTemp is null ");
					params.remove("isTemp");
				} else {
					hql.append(" and ").append(ename).append(".isTemp = :isTemp ");
				}
			}
			if (params.containsKey("isQualfied")) {
				Object isQualfied = params.get("isQualfied");
				if (isQualfied == null) {
					hql.append(" and ").append(ename).append(".isQualfied is null ");
					params.remove("isQualfied");
				} else {
					hql.append(" and ").append(ename).append(".isQualfied = :isQualfied ");
				}
			}
			if (params.containsKey("invWarehouse.id")) {
				Object invWarehouseId = params.get("invWarehouse.id");
				if (invWarehouseId == null) {
					hql.append(" and ").append(ename).append(".invWarehouse.id is null ");
					params.remove("invWarehouse.id");
				} else {
					hql.append(" and ").append(ename).append(".invWarehouse.id = :invWarehouse.id ");
				}
			}
			if (params.containsKey("tenantId")) {
				Object tenantId = params.get("tenantId");
				if (tenantId == null) {
					hql.append(" and ").append(ename).append(".tenantId is null ");
					params.remove("tenantId");
				} else {
					hql.append(" and ").append(ename).append(".tenantId = :tenantId ");
				}
			}
		} else {
			
		}
		return hql;
	}

	public StringBuilder findInventoryCurrentStockSql(Map<String, Object> params) {

		String ename = entityAsName();
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename);
		hql.append(" from InventoryCurrentStock ").append(ename);
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("isTemp")) {
				Object isTemp = params.get("isTemp");
				if (isTemp == null) {
					hql.append(" and ").append(ename).append(".isTemp is null ");
					params.remove("isTemp");
				} else {
					hql.append(" and ").append(ename).append(".isTemp = :isTemp ");
				}
			}
			if (params.containsKey("isQualfied")) {
				Object isQualfied = params.get("isQualfied");
				if (isQualfied == null) {
					hql.append(" and ").append(ename).append(".isQualfied is null ");
					params.remove("isQualfied");
				} else {
					hql.append(" and ").append(ename).append(".isQualfied = :isQualfied ");
				}
			}
			if (params.containsKey("invWarehouse.id")) {
				Object invWarehouseId = params.get("invWarehouse.id");
				if (invWarehouseId == null) {
					hql.append(" and ").append(ename).append(".invWarehouse.id is null ");
					params.remove("invWarehouse.id");
				} else {
					hql.append(" and ").append(ename).append(".invWarehouse.id = :invWarehouse.id ");
				}
			}
			if (params.containsKey("tenantId")) {
				Object tenantId = params.get("tenantId");
				if (tenantId == null) {
					hql.append(" and ").append(ename).append(".tenantId is null ");
					params.remove("tenantId");
				} else {
					hql.append(" and ").append(ename).append(".tenantId = :tenantId ");
				}
			}
		} else {
			
		}
		return hql;
	}

	/**
	 * 通过 sku编码查询
	 * 
	 * @param params
	 * @return
	 */
	public StringBuilder findInventoryCurrentStockBySkuCode(Map<String, Object> params) {

		String ename = entityAsName();
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename);
		hql.append(" from InventoryCurrentStock ").append(ename);
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("isTemp")) {
				Object isTemp = params.get("isTemp");
				if (isTemp == null) {
					hql.append(" and ").append(ename).append(".isTemp is null ");
					params.remove("isTemp");
				} else {
					hql.append(" and ").append(ename).append(".isTemp = :isTemp ");
				}
			}
			if (params.containsKey("isQualfied")) {
				Object isQualfied = params.get("isQualfied");
				if (isQualfied == null) {
					hql.append(" and ").append(ename).append(".isQualfied is null ");
					params.remove("isQualfied");
				} else {
					hql.append(" and ").append(ename).append(".isQualfied = :isQualfied ");
				}
			}

			if (params.containsKey("tenantId")) {
				Object tenantId = params.get("tenantId");
				if (tenantId == null) {
					hql.append(" and ").append(ename).append(".tenantId is null ");
					params.remove("tenantId");
				} else {
					hql.append(" and ").append(ename).append(".tenantId = :tenantId ");
				}
			}

			if (params.containsKey("invWarehouseId")) {
				Object invWarehouseId = params.get("invWarehouseId");
				if (invWarehouseId == null) {
					hql.append(" and ").append(ename).append(".invWarehouse.id is null ");
					params.remove("invWarehouseId");
				} else {
					hql.append(" and ").append(ename).append(".invWarehouse.id = :invWarehouseId ");
				}
			}
			if (params.containsKey("skuCode")) {
				Object skuCode = params.get("skuCode");
				if (skuCode == null) {
					hql.append(" and ").append(ename).append(".skuCode is null ");
					params.remove("skuCode");
				} else {
					hql.append(" and ").append(ename).append(".skuCode = :skuCode ");
				}
			}
			if (params.containsKey("itemcode")) {
				Object itemcode = params.get("itemcode");
				if (itemcode == null) {
					hql.append(" and ").append(ename).append(".itemcode is null ");
					params.remove("itemcode");
				} else {
					hql.append(" and ").append(ename).append(".itemcode = :itemcode ");
				}
			}
			if (params.containsKey("massunitEndTime")) {
				Object massunitEndTime = params.get("massunitEndTime");
				if (massunitEndTime == null) {
					hql.append(" and ").append(ename).append(".massunitEndTime is null ");
					params.remove("massunitEndTime");
				} else {
					hql.append(" and ").append(ename).append(".massunitEndTime = :massunitEndTime ");
				}
			}
			if (params.containsKey("batchcode")) {
				Object batchcode = params.get("batchcode");
				if (batchcode == null) {
					hql.append(" and ").append(ename).append(".batchcode is null ");
					params.remove("batchcode");
				} else {
					hql.append(" and ").append(ename).append(".batchcode = :batchcode ");
				}
			}
			if (params.containsKey("channelDistributorId")) {
				Object channelDistributorId = params.get("channelDistributorId");
				if (channelDistributorId == null) {
					hql.append(" and ").append(ename).append(".channelDistributor.id is null ");
					params.remove("channelDistributorId");
				} else {
					hql.append(" and ").append(ename).append(".channelDistributor.id = :channelDistributorId ");
				}
			}
		}
		return hql;
	}

	public StringBuilder findInventoryCurrentStockByItemcode(Map<String, Object> params) {

		String ename = entityAsName();
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename);
		hql.append(" from InventoryCurrentStock ").append(ename);
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("isQualfied")) {
				Object isQualfied = params.get("isQualfied");
				if (isQualfied == null) {
					hql.append(" and ").append(ename).append(".isQualfied is null ");
					params.remove("isQualfied");
				} else {
					hql.append(" and ").append(ename).append(".isQualfied = :isQualfied ");
				}
			}

			if (params.containsKey("invWarehouseId")) {
				Object invWarehouseId = params.get("invWarehouseId");
				if (invWarehouseId == null) {
					hql.append(" and ").append(ename).append(".invWarehouse.id is null ");
					params.remove("invWarehouseId");
				} else {
					hql.append(" and ").append(ename).append(".invWarehouse.id = :invWarehouseId ");
				}
			}
			if (params.containsKey("massunitEndTime")) {
				Object massunitEndTime = params.get("massunitEndTime");
				if (massunitEndTime == null) {
					hql.append(" and ").append(ename).append(".massunitEndTime is null ");
					params.remove("massunitEndTime");
				} else {
					hql.append(" and ").append(ename).append(".massunitEndTime = :massunitEndTime ");
				}
			}
			if (params.containsKey("itemcode")) {
				Object itemcode = params.get("itemcode");
				if (itemcode == null) {
					hql.append(" and ").append(ename).append(".itemcode is null ");
					params.remove("itemcode");
				} else {
					hql.append(" and ").append(ename).append(".itemcode = :itemcode ");
				}
			}
			if (params.containsKey("batchcode")) {
				Object batchcode = params.get("batchcode");
				if (batchcode == null) {
					hql.append(" and ").append(ename).append(".batchcode is null ");
					params.remove("batchcode");
				} else {
					hql.append(" and ").append(ename).append(".batchcode = :batchcode ");
				}
			}
			if (params.containsKey("skuCode")) {
				Object skuCode = params.get("skuCode");
				if (skuCode == null) {
					hql.append(" and ").append(ename).append(".skuCode is null ");
					params.remove("skuCode");
				} else {
					hql.append(" and ").append(ename).append(".skuCode = :skuCode ");
				}
			}
		}
		return hql;
	}

	public StringBuilder findInventoryCurrentStockByItemCode(Map<String, Object> params) {

		String ename = entityAsName();
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename);
		hql.append(" from MasterInventoryCurrentStock ").append(ename);
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("isTemp")) {
				Object isTemp = params.get("isTemp");
				if (isTemp == null) {
					hql.append(" and ").append(ename).append(".isTemp is null ");
					params.remove("isTemp");
				} else {
					hql.append(" and ").append(ename).append(".isTemp = :isTemp ");
				}
			}
			if (params.containsKey("tenantId")) {
				Object tenantId = params.get("tenantId");
				if (tenantId == null) {
					hql.append(" and ").append(ename).append(".tenantId is null ");
					params.remove("tenantId");
				} else {
					hql.append(" and ").append(ename).append(".tenantId = :tenantId ");
				}
			}
			if (params.containsKey("isQualfied")) {
				Object isQualfied = params.get("isQualfied");
				if (isQualfied == null) {
					hql.append(" and ").append(ename).append(".isQualfied is null ");
					params.remove("isQualfied");
				} else {
					hql.append(" and ").append(ename).append(".isQualfied = :isQualfied ");
				}
			}

			if (params.containsKey("invWarehouseId")) {
				Object invWarehouseId = params.get("invWarehouseId");
				if (invWarehouseId == null) {
					hql.append(" and ").append(ename).append(".invWarehouse.id is null ");
					params.remove("invWarehouseId");
				} else {
					hql.append(" and ").append(ename).append(".invWarehouse.id = :invWarehouseId ");
				}
			}
			if (params.containsKey("itemcode")) {
				Object itemcode = params.get("itemcode");
				if (itemcode == null) {
					hql.append(" and ").append(ename).append(".itemcode is null ");
					params.remove("itemcode");
				} else {
					hql.append(" and ").append(ename).append(".itemcode = :itemcode ");
				}
			}
		} else {
			
		}
		return hql;
	}

	public StringBuilder findStockRecordLinesByItemCode(Map<String, Object> params) {

		String ename = entityAsName();
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename);
		hql.append(" from StockRecordLines ").append(ename);
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("stockRecordsId")) {
				Object stockRecordsId = params.get("stockRecordsId");
				if (stockRecordsId == null) {
					hql.append(" and ").append(ename).append(".stockRecords.id is null ");
					params.remove("stockRecordsId");
				} else {
					hql.append(" and ").append(ename).append(".stockRecords.id = :stockRecordsId ");
				}
			}
			if (params.containsKey("itemcode")) {
				Object itemcode = params.get("itemcode");
				if (itemcode == null) {
					hql.append(" and ").append(ename).append(".itemcode is null ");
					params.remove("itemcode");
				} else {
					hql.append(" and ").append(ename).append(".itemcode = :itemcode ");
				}
			}
		}
		return hql;
	}

	public StringBuilder findMeasureUnitByName(Map<String, Object> params) {

		String ename = entityAsName();
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename);
		hql.append(" from MeasureUnit ").append(ename);
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("name")) {
				Object name = params.get("name");
				if (name == null) {
					hql.append(" and ").append(ename).append(".name is null ");
					params.remove("name");
				} else {
					hql.append(" and ").append(ename).append(".name = :name ");
				}
			}
			if (params.containsKey("itemcode")) {
				Object itemcode = params.get("itemcode");
				if (itemcode == null) {
					hql.append(" and ").append(ename).append(".itemcode is null ");
					params.remove("itemcode");
				} else {
					hql.append(" and ").append(ename).append(".itemcode = :itemcode ");
				}
			}
		}
		return hql;
	}

	public StringBuilder findOrderDetaiVo(Map<String, Object> params) {

		String ename = entityAsName();
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename);
		hql.append(" from OrderDetaiVo ").append(ename);
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("salesDate")) {
				Object salesDate = params.get("salesDate");
				if (salesDate == null) {
					hql.append(" and ").append(ename).append(".salesDate is null ");
					params.remove("salesDate");
				} else {
					hql.append(" and ").append(ename).append(".salesDate = :salesDate ");
				}
			}
			if (params.containsKey("itemcode")) {
				Object itemcode = params.get("itemcode");
				if (itemcode == null) {
					hql.append(" and ").append(ename).append(".itemcode is null ");
					params.remove("itemcode");
				} else {
					hql.append(" and ").append(ename).append(".itemcode = :itemcode ");
				}
			}
		}
		return hql;
	}

	public StringBuilder findStoreItemByItemCode(Map<String, Object> params) {
		String ename = entityAsName();
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename);
		hql.append(" from StoreItem ").append(ename);
		hql.append(" left outer join fetch ").append(ename).append(".subChannelDistributors e ");
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("channelDistributorId")) {
				Object channelDistributorId = params.get("channelDistributorId");
				if (channelDistributorId == null) {
					hql.append(" and ").append("e.id is null ");
					params.remove("channelDistributorId");
				} else {
					hql.append(" and ").append("e.id = :channelDistributorId ");
				}
			}
			if (params.containsKey("code")) {
				Object code = params.get("code");
				if (code == null) {
					hql.append(" and ").append(ename).append(".code is null ");
					params.remove("code");
				} else {
					hql.append(" and ").append(ename).append(".code = :code ");
				}
			}
			if (params.containsKey("supplierId")) {
				Object supplierId = params.get("supplierId");
				if (supplierId == null) {
					hql.append(" and ").append(ename).append(".supplier.id is null ");
					params.remove("supplierId");
				} else {
					hql.append(" and ").append(ename).append(".supplier.id = :supplierId ");
				}
			}
		}
		return hql;
	}

	public StringBuilder findPurchaseOrderLineItemByItemCode(Map<String, Object> params) {
		String ename = entityAsName();
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename);
		hql.append(" from PurchaseOrderLineItem ").append(ename);
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("itemCode")) {
				Object itemCode = params.get("itemCode");
				if (itemCode == null) {
					hql.append(" and ").append(ename).append(".itemCode is null ");
					params.remove("itemCode");
				} else {
					hql.append(" and ").append(ename).append(".itemCode = :itemCode ");
				}
			}
			if (params.containsKey("purchaseOrderId")) {
				Object purchaseOrderId = params.get("purchaseOrderId");
				if (purchaseOrderId == null) {
					hql.append(" and ").append(ename).append(".purchaseOrder.id is null ");
					params.remove("purchaseOrderId");
				} else {
					hql.append(" and ").append(ename).append(".purchaseOrder.id = :purchaseOrderId ");
				}
			}
		}
		return hql;
	}

	public StringBuilder findInvWarehouseHql(Map<String, Object> params) {
		String ename = entityAsName();
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename);
		hql.append(" from InvWarehouse ").append(ename);
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("type")) {
				Object type = params.get("type");
				if (type == null) {
					hql.append(" and ").append(ename).append(".type is null ");
					params.remove("type");
				} else {
					hql.append(" and ").append(ename).append(".type = :type ");
				}
			}
			if (params.containsKey("isDefault")) {
				Object isDefault = params.get("isDefault");
				if (isDefault == null) {
					hql.append(" and ").append(ename).append(".isDefault is null ");
					params.remove("isDefault");
				} else {
					hql.append(" and ").append(ename).append(".isDefault = :isDefault ");
				}
			}
		}
		return hql;
	}

	public StringBuilder findEncodingRulesTableInTheMiddleBillType(Map<String, Object> params) {
		String ename = entityAsName();
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename);
		hql.append(" from EncodingRulesTableInTheMiddle ").append(ename);
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("billType")) {
				Object billType = params.get("billType");
				if (billType == null) {
					hql.append(" and ").append(ename).append(".billType is null ");
					params.remove("billType");
				} else {
					hql.append(" and ").append(ename).append(".billType = :billType ");
				}
			}
			if (params.containsKey("tenantId")) {
				Object tenantId = params.get("tenantId");
				if (tenantId == null) {
					hql.append(" and ").append(ename).append(".tenantId is null ");
					params.remove("tenantId");
				} else {
					hql.append(" and ").append(ename).append(".tenantId = :tenantId ");
				}
			}
		}
		return hql;
	}

	public StringBuilder findItemByItemCode(Map<String, Object> params) {
		String ename = entityAsName();
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename);
		hql.append(" from Item ").append(ename);
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("itemCode")) {
				Object itemCode = params.get("itemCode");
				if (itemCode == null) {
					hql.append(" and ").append(ename).append(".code is null ");
					params.remove("itemCode");
				} else {
					hql.append(" and ").append(ename).append(".code = :itemCode ");
				}
			}
			if (params.containsKey("tenantId")) {
				Object tenantId = params.get("tenantId");
				if (tenantId == null) {
					hql.append(" and ").append(ename).append(".tenantId is null ");
					params.remove("tenantId");
				} else {
					hql.append(" and ").append(ename).append(".tenantId = :tenantId ");
				}
			}
		}
		return hql;
	}

	public StringBuilder findRequireGoodsOrderItemByItemCode(Map<String, Object> params) {
		String ename = entityAsName();
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename);
		hql.append(" from RequireGoodsOrderItem ").append(ename);
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("requireGoodsOrderId")) {
				Object requireGoodsOrderId = params.get("requireGoodsOrderId");
				if (requireGoodsOrderId == null) {
					hql.append(" and ").append(ename).append(".requireGoodsOrder.id is null ");
					params.remove("requireGoodsOrderId");
				} else {
					hql.append(" and ").append(ename).append(".requireGoodsOrder.id = :requireGoodsOrderId ");
				}
			}
			if (params.containsKey("itemCode")) {
				Object itemCode = params.get("itemCode");
				if (itemCode == null) {
					hql.append(" and ").append(ename).append(".itemCode is null ");
					params.remove("itemCode");
				} else {
					hql.append(" and ").append(ename).append(".itemCode = :itemCode ");
				}
			}
		}
		return hql;
	}

	public StringBuilder findOrderAndGoods(Map<String, Object> params) {

		String ename = entityAsName();
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename);
		hql.append(" from OrderAndGoods ").append(ename);
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("tenantId")) {
				Object tenantId = params.get("tenantId");
				if (tenantId == null) {
					hql.append(" and ").append(ename).append(".tenantId is null ");
					params.remove("tenantId");
				} else {
					hql.append(" and ").append(ename).append(".tenantId = :tenantId ");
				}
			}

			if (params.containsKey("orderDetailId")) {
				Object orderDetailId = params.get("orderDetailId");
				if (orderDetailId == null) {
					hql.append(" and ").append(ename).append(".orderDetailId is null ");
					params.remove("orderDetailId");
				} else {
					hql.append(" and ").append(ename).append(".orderDetailId = :orderDetailId ");
				}
			}
			if (params.containsKey("goodId")) {
				Object goodId = params.get("goodId");
				if (goodId == null) {
					hql.append(" and ").append(ename).append(".goodId is null ");
					params.remove("goodId");
				} else {
					hql.append(" and ").append(ename).append(".goodId = :goodId ");
				}
			}
		}
		return hql;
	}

}
