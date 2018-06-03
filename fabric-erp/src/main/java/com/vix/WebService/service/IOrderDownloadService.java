package com.vix.WebService.service;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.vix.WebService.vo.TradeVo;

/**
 * 订单下载处理
 * 
 * @author zhanghaibing
 * 
 * @date 2014-3-21
 */
@WebService
public interface IOrderDownloadService {
	@WebResult(name = "order")
	public com.vix.ebusiness.entity.Order dealOrders(TradeVo tradeVo, @WebParam(name = "channelDistributorCode") String channelDistributorCode) throws Exception;
}
