package com.vix.system.expand.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.common.vixdata.util.NumberUtil;
import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.core.persistence.jdbc.ISpecificationDBService;
import com.vix.core.persistence.jdbc.impl.SpecificationDBService;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.item.entity.ItemCatalog;
import com.vix.nvix.wx.util.StrUtils;
import com.vix.system.expand.dao.ISpecificationDao;

@Repository("specificationDao")
public class SpecificationDaoImpl extends BaseHibernateDaoImpl implements ISpecificationDao {

	private static final long serialVersionUID = -1L;
	
	private ISpecificationDBService sdb = new SpecificationDBService();
	
	@Override
	public boolean createTable(String dbType,ItemCatalog itemCatalog,String tableName) throws Exception {
		return sdb.createTable(dbType, itemCatalog, tableName);
	}
	
	/** 检查表是否存在 */
	@Override
	public boolean checkTableExist(String dbType,String tableName) throws Exception{
		return sdb.checkTableExist(dbType, tableName);
	}
	
	/** 检查表中列是否存在 */
	@Override
	public boolean checkColumnExist(String dbType,String tableName,String columnName) throws Exception{
		return sdb.checkColumnExist(dbType, tableName, columnName);
	}
	
	/** 删除规格扩展表  */
	@Override
	public boolean dropTable(String dbType,String tableName) throws Exception{
		return sdb.dropTable(dbType, tableName);
	}
	
	/** 添加商品扩展表字段  */
	@Override
	public boolean addTableField(String dbType,String tableName,String columnName) throws Exception{
		return sdb.addTableField(dbType, tableName, columnName);
	}
	
	/** 删除商品扩展表字段  */
	@Override
	public boolean deleteTableField(String dbType,String tableName,String columnName) throws Exception{
		return sdb.deleteTableField(dbType, tableName, columnName);
	}

	@Override
	public String updateEcProductSpecData(Item ep, String productCategoryIdStr, String specTableName, String specData)
			throws Exception {
		if(null == ep){
			return null;
		}
		if(StrUtils.objectIsNull(specTableName)){
			return null;
		}
		/** 最高价格，以最高销售价格的一组价格为准 */
	/*	//String maxSku = "";
		double maxPurchasePrice = 0d;  *//** 规格组合对应商品的最高采购价格 *//*
		double maxPrice = 0d;          *//** 规格组合对应商品的最高PC端销售价格 *//*
		double maxMobilePrice = 0d;    *//** 规格组合对应商品的最高移动端价格 *//*
		double maxAppPrice = 0d;       *//** 规格组合对应商品的最高APP端价格 *//*
//		double maxMarketPrice = 0d;    /** 规格组合对应商品的最高市场价格 */
//		double maxListedPrice = 0d;    /** 规格组合对应商品的最高挂牌价格 */
		//long maxIntegralExchange = 0l;    /** 规格组合对应的最高的兑换价格 */
		StringBuilder skuGroup = new StringBuilder("");
 
		String[] sds = specData.split(":");
		for(String sd : sds){
			if(null != sd && !"".equals(sd)){
				String id = null;            /** 规格组合id */
				String sku = null;           /** 规格组合中的sku */
				//double purchasePrice = 0d;   /** 规格组合对应商品的采购价格 */
				double price = 0d;           /** 规格组合对应商品的销售价格 */
//				double mobilePrice = 0d;     /** 移动端价格 */
//				double appPrice = 0d;        /** APP端价格 */
//				double marketPrice = 0d;     /** 规格组合对应商品的市场价格 */
//				double listedPrice = 0d;     /** 挂牌价 */
//				long integralExchange = 0l;/** 规格组合对应商品的积分兑换 */
				String[] specDetail = sd.split("!");
				if(specDetail.length >= 9){
					if(StrUtils.objectIsNotNull(specDetail[0]) && !"PlaceHolder".equals(specDetail[0])){
						id = specDetail[0].trim();
					}else{
						continue;
					}
					if(StrUtils.objectIsNotNull(specDetail[1]) && !"PlaceHolder".equals(specDetail[1])){
						sku = specDetail[1].trim();
					}else{
						continue;
					}
//					if(!"PlaceHolder".equals(specDetail[2]) && NumberUtil.isDoubleNumeric(specDetail[2])){
//						purchasePrice = Double.parseDouble(specDetail[2]);
//					}
					if(!"PlaceHolder".equals(specDetail[2]) && NumberUtil.isDoubleNumeric(specDetail[2])){
						price = Double.parseDouble(specDetail[2]);
					}
//					if(!"PlaceHolder".equals(specDetail[4]) && NumberUtil.isDoubleNumeric(specDetail[4])){
//						mobilePrice = Double.parseDouble(specDetail[4]);
//					}
//					if(!"PlaceHolder".equals(specDetail[5]) && NumberUtil.isDoubleNumeric(specDetail[5])){
//						appPrice = Double.parseDouble(specDetail[5]);
//					}
//					if(!"PlaceHolder".equals(specDetail[6]) && NumberUtil.isDoubleNumeric(specDetail[6])){
//						marketPrice = Double.parseDouble(specDetail[6]);
//					}
//					if(!"PlaceHolder".equals(specDetail[7]) && NumberUtil.isDoubleNumeric(specDetail[7])){
//						listedPrice = Double.parseDouble(specDetail[7]);
//					}
//					if(!"PlaceHolder".equals(specDetail[8]) && NumberUtil.isNumeric(specDetail[8])){
//						integralExchange = Long.parseLong(specDetail[8]);
//					}
					/** 统计最高价格 */
//					if(price > maxPrice){
//						//maxSku = sku;
//						maxPrice = price;
//						maxMobilePrice = mobilePrice;
//						maxAppPrice = appPrice;
//						maxPurchasePrice = purchasePrice;
////						maxMarketPrice = marketPrice;
////						maxListedPrice = listedPrice;
//						maxIntegralExchange = integralExchange;
//					}
					StringBuilder sqlBuilder = new StringBuilder("update ");
					sqlBuilder.append(specTableName);
					sqlBuilder.append(" set SKU = '");
					sqlBuilder.append(sku);
					sqlBuilder.append("',INVENTORY_COUNT = ");
					sqlBuilder.append(price);
					sqlBuilder.append(" where id = '");
					sqlBuilder.append(id);
					sqlBuilder.append("'");
					this.executeSql(sqlBuilder.toString(), null);
					skuGroup.append(sku);
					skuGroup.append(",");
				}
			}
		}
		return skuGroup.toString();
	}
}
