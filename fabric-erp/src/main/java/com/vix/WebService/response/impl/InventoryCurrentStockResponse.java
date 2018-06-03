package com.vix.WebService.response.impl;

import java.util.List;

import com.vix.WebService.response.StoreResponse;
import com.vix.WebService.vo.InventoryCurrentStockVo;

/**
 * 
 * com.vix.WebService.response.CustomerAccountListResponse
 *
 * @author zhanghaibing
 *
 * @date 2014年10月12日
 */
public class InventoryCurrentStockResponse extends StoreResponse {
	private static final long serialVersionUID = 5961975119861386569L;

	private List<InventoryCurrentStockVo> inventoryCurrentStockVoList;

	private Long totalResults;

	public Long getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(Long totalResults) {
		this.totalResults = totalResults;
	}

	public List<InventoryCurrentStockVo> getInventoryCurrentStockVoList() {
		return inventoryCurrentStockVoList;
	}

	public void setInventoryCurrentStockVoList(List<InventoryCurrentStockVo> inventoryCurrentStockVoList) {
		this.inventoryCurrentStockVoList = inventoryCurrentStockVoList;
	}

}
