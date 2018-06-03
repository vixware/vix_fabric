/**
 * 
 */
package com.vix.inventory.batch.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.inventory.batch.entity.BaseCoder;
import com.vix.inventory.batch.entity.ItemToBaseCoder;
import com.vix.inventory.batch.service.IBatchService;
import com.vix.system.code.entity.EncodingRulesTableInTheMiddle;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("batchDomain")
@Transactional
public class BatchDomain extends BaseDomain{

	@Autowired
	private IBatchService batchService;

	public BaseCoder merge(BaseCoder baseCoder) throws Exception {
		return batchService.merge(baseCoder);
	}

	public EncodingRulesTableInTheMiddle mergeEncodingRulesTableInTheMiddle(EncodingRulesTableInTheMiddle encodingRulesTableInTheMiddle) throws Exception {
		return batchService.merge(encodingRulesTableInTheMiddle);
	}

	public ItemToBaseCoder mergeItemToBaseCoder(ItemToBaseCoder itemToBaseCoder) throws Exception {
		return batchService.merge(itemToBaseCoder);
	}

}
