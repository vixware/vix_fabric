package com.vix.mdm.item.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.mdm.item.service.IPriceConditionService;

@Service("priceConditionService")
public class PriceConditionServiceImpl extends BaseHibernateServiceImpl implements IPriceConditionService {

	private static final long serialVersionUID = 1L;

	@Override
	public boolean addPriceCondition(String itemId, Double minCount,
			Double maxCount,Double price, Double discount, Date startDate, Date endDate)
			throws Exception {
		boolean saveTag = false;
//		Item item = this.findEntityById(Item.class, itemId);
//		if(null != item){
//			PriceCondition pc = new PriceCondition();
//			//pc.setItem(item);
//			pc.setPriceType("count");
//			pc = this.merge(pc);
//			PriceConditionRule pcr = new PriceConditionRule();
//			pcr.setPriceCondition(pc);;
//			pcr.setFrom(minCount);
//			pcr.setTo(maxCount);
//			pcr.setDiscount(discount);
//			pcr.setPrice(price);
//			pcr.setStartTime(startDate);
//			pcr.setEndTime(endDate);
//			this.merge(pcr);
//			saveTag = true;
//		}
		return saveTag;
	}
}