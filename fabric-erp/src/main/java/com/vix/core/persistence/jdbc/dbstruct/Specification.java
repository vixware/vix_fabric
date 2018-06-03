package com.vix.core.persistence.jdbc.dbstruct;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.mdm.item.entity.ItemCatalog;
import com.vix.wechat.base.util.SortSet;

/**
 * 商品规格
 * 
 * @类全名 com.vix.core.persistence.jdbc.dbstruct.Specification
 * 
 * @author zhanghaibing
 *
 * @date 2017年12月13日
 */
public class Specification extends BaseEntity implements Comparable<Specification> {

	private static final long serialVersionUID = 1L;
	/** 规格表 */
	private String specificationTableName;
	/** 数据库列是否生成 0：否 1：是 */
	private String isColumnGenerate;
	/** 商品规格明细 */
	private Set<SpecificationDetail> specificationDetails = new HashSet<SpecificationDetail>();
	/** 分类 */
	private ItemCatalog itemCatalog;

	public Specification() {
	}

	public ItemCatalog getItemCatalog() {
		return itemCatalog;
	}

	public void setItemCatalog(ItemCatalog itemCatalog) {
		this.itemCatalog = itemCatalog;
	}

	public String getSpecificationTableName() {
		return specificationTableName;
	}

	public void setSpecificationTableName(String specificationTableName) {
		this.specificationTableName = specificationTableName;
	}

	public String getIsColumnGenerate() {
		return isColumnGenerate;
	}

	public void setIsColumnGenerate(String isColumnGenerate) {
		this.isColumnGenerate = isColumnGenerate;
	}

	public Set<SpecificationDetail> getSpecificationDetails() {
		return specificationDetails;
	}

	public void setSpecificationDetails(Set<SpecificationDetail> specificationDetails) {
		this.specificationDetails = specificationDetails;
	}

	public List<SpecificationDetail> getSortSpecificationDetails() {
		List<SpecificationDetail> sdList = new ArrayList<SpecificationDetail>(specificationDetails);
		/** 按id排序 */
		if (specificationDetails.size() > 0) {
			SortSet ss = new SortSet("id", "asc");
			Collections.sort(sdList, ss);
		}
		return sdList;
	}

	@Override
	public int compareTo(Specification s) {
		if (null != s && null != s.getOrderBy() && null != orderBy) {
			Long ct = orderBy - s.getOrderBy();
			return ct.intValue();
		}
		return 0;
	}
}
