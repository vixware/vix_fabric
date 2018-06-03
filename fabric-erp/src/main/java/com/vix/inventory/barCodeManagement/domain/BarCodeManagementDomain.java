/**
 * 
 */
package com.vix.inventory.barCodeManagement.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.inventory.barCodeManagement.service.IBarCodeManagementService;
import com.vix.inventory.batch.entity.BaseCoder;
import com.vix.inventory.batch.entity.ItemToBaseCoder;
import com.vix.system.code.entity.EncodingRulesTableInTheMiddle;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("barCodeManagementDomain")
@Transactional
public class BarCodeManagementDomain extends BaseDomain{

	@Autowired
	private IBarCodeManagementService barCodeManagementService;

	public BaseCoder merge(BaseCoder baseCoder) throws Exception {
		return barCodeManagementService.merge(baseCoder);
	}

	public EncodingRulesTableInTheMiddle mergeEncodingRulesTableInTheMiddle(EncodingRulesTableInTheMiddle encodingRulesTableInTheMiddle) throws Exception {
		return barCodeManagementService.merge(encodingRulesTableInTheMiddle);
	}

	public ItemToBaseCoder mergeItemToBaseCoder(ItemToBaseCoder itemToBaseCoder) throws Exception {
		return barCodeManagementService.merge(itemToBaseCoder);
	}

}
