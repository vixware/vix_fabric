package com.vix.WebService.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.vix.WebService.response.impl.CustomerAccountListResponse;
import com.vix.WebService.response.impl.CustomerAccountResponse;
import com.vix.WebService.response.impl.InventoryCurrentStockResponse;
import com.vix.WebService.response.impl.MemberShipCardResponse;
import com.vix.WebService.response.impl.UpLoadResponse;
import com.vix.WebService.vo.CustomerAccountVo;
import com.vix.WebService.vo.InventoryCurrentStockVo;
import com.vix.WebService.vo.MemberShipCardTypeVo;
import com.vix.WebService.vo.MemberShipCardVo;
import com.vix.WebService.vo.SalesOrderItemVo;
import com.vix.WebService.vo.SalesOrderVo;
import com.vix.WebService.vo.TranLogVo;
import com.vix.WebService.vo.ZoCardBillPaymentVo;
import com.vix.WebService.vo.ZoVipCardLogVo;

/**
 * 
 * com.vix.WebService.IStoreDownLoadService
 *
 * @author zhanghaibing
 *
 * @date 2014年10月29日
 */
@WebService
public interface IStoreDownLoadService {

	/*String sayHi(@WebParam(name = "userName") String text);

	String sayHiToUser(@WebParam(name = "cxfUserParam") CxfUser user);

	List<AuthorityImpVo> findTestAuthority();*/

	/**
	 * 门店会员下载
	 * 
	 * @param storeCode
	 * @return
	 * @throws Exception
	 */
	@WebResult(name = "customerAccountListVoResponse")
	public CustomerAccountListResponse downLoadStoreZoVipList(@WebParam(name = "storeCode") String storeCode);

	/**
	 * 查询会员
	 * 
	 * @param vipcode
	 * @return
	 * @throws Exception
	 */
	@WebResult(name = "customerAccountVoResponse")
	public CustomerAccountResponse downLoadStoreZoVip(@WebParam(name = "vipCode") String vipCode);

	/**
	 * 总部商品编码下载（新增或变更时）
	 * 
	 * @return
	 * @throws Exception
	 */
	@WebResult(name = "downLoadInventoryCurrentStockVoResponse")
	public InventoryCurrentStockResponse downLoadInventoryCurrentStock(@WebParam(name = "storeCode") String storeCode);

	/**
	 * 批量开卡数据下载
	 * 
	 * @param storeCode
	 * @return
	 * @throws Exception
	 */
	@WebResult(name = "downLoadMemberShipCardVoResponse")
	public MemberShipCardResponse downLoadMemberShipCard(@WebParam(name = "storeCode") String storeCode);

	/**
	 * 门店会员信息上报（新增或变更时）
	 * 
	 * @param customerAccountList
	 * @throws Exception
	 */
	@WebMethod
	@WebResult(name = "upLoadResponse")
	public UpLoadResponse upLoadZoVipList(@WebParam(name = "customerAccountList") List<CustomerAccountVo> customerAccountVoList, @WebParam(name = "storeCode") String storeCode);

	/**
	 * 上传会员卡信息
	 * 
	 * @param memberShipCardVo
	 * @param storeCode
	 * @return
	 * @throws Exception
	 */
	@WebMethod
	@WebResult(name = "upLoadResponse")
	public UpLoadResponse upLoadMemberShipCardList(@WebParam(name = "memberShipCardList") List<MemberShipCardVo> memberShipCardVoList, @WebParam(name = "storeCode") String storeCode);

	/**
	 * 门店库存上报
	 * 
	 * @param storecode
	 * @param inventoryCurrentStockList
	 * @return
	 * @throws Exception
	 */
	@WebMethod
	@WebResult(name = "upLoadResponse")
	public UpLoadResponse upLoadInventoryCurrentStock(@WebParam(name = "storeCode") String storeCode, @WebParam(name = "inventoryCurrentStockList") List<InventoryCurrentStockVo> inventoryCurrentStockList);

	/**
	 * 上传会员卡消费记录
	 * 
	 * @param zoVipCardLogList
	 * @throws Exception
	 */
	@WebMethod
	@WebResult(name = "upLoadResponse")
	public UpLoadResponse upLoadZoVipCardLog(@WebParam(name = "zoVipCardLogList") List<ZoVipCardLogVo> zoVipCardLogVoList, @WebParam(name = "storeCode") String storeCode);

	/**
	 * 上传机台交易信息
	 * 
	 * @param tranLogVoList
	 * @param storeCode
	 * @return
	 * @throws Exception
	 */
	@WebMethod
	@WebResult(name = "upLoadResponse")
	public UpLoadResponse upLoadTranLog(@WebParam(name = "tranLogList") List<TranLogVo> tranLogVoList, @WebParam(name = "storeCode") String storeCode);

	/**
	 * 上传会员卡类型
	 * 
	 * @param memberShipCardTypeVoList
	 * @param storeCode
	 * @return
	 */
	@WebMethod
	@WebResult(name = "upLoadResponse")
	public UpLoadResponse upMemberShipCardType(@WebParam(name = "memberShipCardTypeVoList") List<MemberShipCardTypeVo> memberShipCardTypeVoList, @WebParam(name = "storeCode") String storeCode);

	/**
	 * 保存会员卡账单信息
	 * 
	 * @param salesOrderList
	 * @throws Exception
	 */
	@WebMethod
	@WebResult(name = "upLoadResponse")
	public UpLoadResponse upLoadZoCardBill(@WebParam(name = "salesOrderList") List<SalesOrderVo> salesOrderVoList, @WebParam(name = "salesOrderItemList") List<SalesOrderItemVo> salesOrderItemVoList, @WebParam(name = "zoCardBillPaymentList") List<ZoCardBillPaymentVo> zoCardBillPaymentVoList, @WebParam(name = "storeCode") String storeCode);

}
