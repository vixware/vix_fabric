/**
 * 
 */
package com.vix.common.vixdata.dataappinterface;

/**
 * 提供不同业务的数据精度接口
 * 
 * @author zhanghaibing
 * 
 * @date 2013-8-21
 */
public interface IDataAccuracy {
	/**
	 * 存货数量
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public Double getStocksAmount(Double data) throws Exception;

	/**
	 * 存货单价
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public Double getStockPrice(Double data) throws Exception;

	/**
	 * 开票单价
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public Double getBillsPrice(Double data) throws Exception;

	/**
	 * 件数
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public Double getNumberOfUnits(Double data) throws Exception;

	/**
	 * 换算率
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public Double getConversionRate(Double data) throws Exception;

	/**
	 * 税率
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public Double getRateOfTax(Double data) throws Exception;

	/**
	 * 金额小数位
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public Double getAmountDecimal(Double data) throws Exception;

	/**
	 * 存货体积小数位
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public Double getStockDimensionDecimal(Double data) throws Exception;

	/**
	 * 存货重量小数位
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public Double getStockWeightDecimal(Double data) throws Exception;

}
