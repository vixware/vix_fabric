package com.vix.nvix.fabric.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 票据背书历史
 * 
 * @author zhanghaibing
 * 
 * @类全名 com.vix.nvix.fabric.entity.FabricHistoryItem
 *
 * @date 2018年3月8日
 */
public class FabricHistoryItem extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String txId;
	private MockupUsers fabricBill;

	public FabricHistoryItem() {
	}

	/**
	 * @return the txId
	 */
	public String getTxId() {
		return txId;
	}

	/**
	 * @param txId
	 *            the txId to set
	 */
	public void setTxId(String txId) {
		this.txId = txId;
	}

	/**
	 * @return the fabricBill
	 */
	public MockupUsers getFabricBill() {
		return fabricBill;
	}

	/**
	 * @param fabricBill
	 *            the fabricBill to set
	 */
	public void setFabricBill(MockupUsers fabricBill) {
		this.fabricBill = fabricBill;
	}

}
