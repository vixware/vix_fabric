package com.vix.WebService.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vix.WebService.hql.StoreManagementHqlProvider;
import com.vix.WebService.response.impl.CustomerAccountListResponse;
import com.vix.WebService.response.impl.CustomerAccountResponse;
import com.vix.WebService.response.impl.InventoryCurrentStockResponse;
import com.vix.WebService.response.impl.MemberShipCardResponse;
import com.vix.WebService.response.impl.UpLoadResponse;
import com.vix.WebService.service.IStoreDownLoadService;
import com.vix.WebService.service.IStoreWebService;
import com.vix.WebService.vo.CustomerAccountVo;
import com.vix.WebService.vo.InventoryCurrentStockVo;
import com.vix.WebService.vo.MemberShipCardTypeVo;
import com.vix.WebService.vo.MemberShipCardVo;
import com.vix.WebService.vo.SalesOrderItemVo;
import com.vix.WebService.vo.SalesOrderVo;
import com.vix.WebService.vo.TranLogVo;
import com.vix.WebService.vo.ZoCardBillPaymentVo;
import com.vix.WebService.vo.ZoVipCardLogVo;
import com.vix.drp.MembershipCardmanagement.entity.MemberShipCard;
import com.vix.drp.MembershipCardmanagement.entity.MemberShipCardType;
import com.vix.drp.MembershipCardmanagement.entity.ZoCardBillPayment;
import com.vix.drp.MembershipCardmanagement.entity.ZoVipCardLog;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.rides.entity.TranLog;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.sales.order.entity.SaleOrderItem;
import com.vix.sales.order.entity.SalesOrder;
import com.vix.system.latestOperate.controller.OperateLog;

import net.sf.cglib.beans.BeanCopier;

/**
 * 
 * com.vix.WebService.impl.StoreDownLoadImpl
 *
 * @author zhanghaibing
 *
 * @date 2014年10月29日
 */
@Component(value = "store")
@WebService(endpointInterface = "com.vix.WebService.impl.IStoreDownLoadService", targetNamespace = "http://218.24.94.213/cxfservices/")
public class StoreDownLoadImpl implements IStoreDownLoadService {

	@Resource(name = "storeWebService")
	private IStoreWebService storeWebService;
	@Resource(name = "storeManagementHqlProvider")
	private StoreManagementHqlProvider storeManagementHqlProvider;
	@Autowired
	private OperateLog latestOperateController;

	/*@Override
	public String sayHi(String text) {
		return "Hello " + text;
	}

	public String sayHiToUser(CxfUser user) {
		return "Hello " + user.getId() + " " + user.getUserName();
	}

	public List<AuthorityImpVo> findTestAuthority() {
		List<AuthorityImpVo> list = new ArrayList<AuthorityImpVo>();
		try {
			for (int i = 0; i < 3; i++) {
				AuthorityImpVo v = new AuthorityImpVo();
				v.setBizType("biztype" + i);
				v.setName("name" + i);
				v.setViewPos("pos" + i);
				list.add(v);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}*/
	@Override
	public MemberShipCardResponse downLoadMemberShipCard(String storeCode) {
		MemberShipCardResponse downLoadMemberShipCardResponse = new MemberShipCardResponse();
		String code = "0";
		String msg = "下载成功!";
		List<MemberShipCardVo> memberShipCardVoList = new ArrayList<MemberShipCardVo>();
		ChannelDistributor channelDistributor = null;
		try {
			channelDistributor = storeWebService.findEntityByAttribute(ChannelDistributor.class, "code", storeCode);
			if (channelDistributor == null) {
				code = "Invalid store code";
				msg = "门店编码不正确!";
			}
		} catch (Exception e) {
			//e.printStackTrace();
			code = "error";
			msg = e.getMessage() + "查询门店出错";
		}
		if (channelDistributor != null) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("storecode", storeCode);
			params.put("tenantId", channelDistributor.getTenantId());
			StringBuilder hql = storeManagementHqlProvider.findMemberShipCardList(params);
			List<MemberShipCard> memberShipCardList = null;
			try {
				memberShipCardList = storeWebService.findEntityList(hql.toString(), params);
				if (memberShipCardList == null) {
					code = "Invalid memberShipCardCode";
					msg = "会员卡不存在!";
				}
			} catch (Exception e) {
				//e.printStackTrace();
				code = "error";
				msg = e.getMessage() + "查询会员卡出错";
			}
			BeanCopier copier = BeanCopier.create(MemberShipCard.class, MemberShipCardVo.class, false);
			if (memberShipCardList != null && memberShipCardList.size() > 0) {
				for (MemberShipCard memberShipCard : memberShipCardList) {
					MemberShipCardVo memberShipCardVo = new MemberShipCardVo();
					copier.copy(memberShipCard, memberShipCardVo, null);
					memberShipCard.setIsDownLoad("2");
					memberShipCard.setIsOpenCard("2");
					try {
						storeWebService.saveEntity(memberShipCard);
						memberShipCardVoList.add(memberShipCardVo);
					} catch (Exception e) {
						//e.printStackTrace();
						code = "error";
						msg = e.getMessage() + "更新会员卡状态出错";
					}
				}
			}
		}

		List<CustomerAccountVo> customerAccountVoList = null;
		BeanCopier copier = BeanCopier.create(CustomerAccount.class, CustomerAccountVo.class, false);
		customerAccountVoList = new ArrayList<CustomerAccountVo>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("storecode", storeCode);
		if (channelDistributor != null) {
			params.put("tenantId", channelDistributor.getTenantId());
		}
		StringBuilder hql = storeManagementHqlProvider.findCustomerAccountList(params);
		try {
			List<CustomerAccount> customerAccountList = storeWebService.findEntityList(hql.toString(), params);
			if (customerAccountList != null && customerAccountList.size() > 0) {
				for (CustomerAccount customerAccount : customerAccountList) {
					if (customerAccount != null) {
						CustomerAccountVo customerAccountVo = new CustomerAccountVo();
						copier.copy(customerAccount, customerAccountVo, null);
						customerAccountVoList.add(customerAccountVo);
						latestOperateController.saveOperateLog("", customerAccount.getCode(), "", storeCode, customerAccount.getTenantId(), storeCode, null, customerAccount.getCompanyCode(), channelDistributor.getCompanyInnerCode());
					}
				}
			}
		} catch (Exception e) {
			//e.printStackTrace();
			code = "error";
			msg = e.getMessage() + "查询会员信息出错";
		}
		downLoadMemberShipCardResponse.setCode(code);
		downLoadMemberShipCardResponse.setMsg(msg);
		downLoadMemberShipCardResponse.setMemberShipCardVoList(memberShipCardVoList);
		downLoadMemberShipCardResponse.setCustomerAccountVoList(customerAccountVoList);
		return downLoadMemberShipCardResponse;
	}

	@Override
	public CustomerAccountListResponse downLoadStoreZoVipList(String storeCode) {
		CustomerAccountListResponse customerAccountResponse = new CustomerAccountListResponse();
		List<CustomerAccountVo> customerAccountVoList = null;
		String code = "0";
		String msg = "下载成功!";
		Long totalResults = 0l;
		ChannelDistributor channelDistributor = null;
		try {
			channelDistributor = storeWebService.findEntityByAttribute(ChannelDistributor.class, "code", storeCode);
			if (channelDistributor == null) {
				code = "Invalid store code";
				msg = "门店编码不正确!";
			}
		} catch (Exception e) {
			//e.printStackTrace();
			code = "error";
			msg = e.getMessage() + "查询门店出错";
		}
		BeanCopier copier = BeanCopier.create(CustomerAccount.class, CustomerAccountVo.class, false);
		customerAccountVoList = new ArrayList<CustomerAccountVo>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("storecode", storeCode);
		if (channelDistributor != null) {
			params.put("tenantId", channelDistributor.getTenantId());
		}
		StringBuilder hql = storeManagementHqlProvider.findCustomerAccountList(params);
		try {
			List<CustomerAccount> customerAccountList = storeWebService.findEntityList(hql.toString(), params);
			if (customerAccountList != null && customerAccountList.size() > 0) {
				for (CustomerAccount customerAccount : customerAccountList) {
					if (customerAccount != null) {
						CustomerAccountVo customerAccountVo = new CustomerAccountVo();
						copier.copy(customerAccount, customerAccountVo, null);
						if (channelDistributor != null) {
							List<MemberShipCardVo> memberShipCardVoList = new ArrayList<MemberShipCardVo>();
							Map<String, Object> params1 = new HashMap<String, Object>();
							params1.put("storecode", storeCode);
							params1.put("tenantId", channelDistributor.getTenantId());
							params1.put("customerAccountId", customerAccount.getId());
							StringBuilder hql1 = storeManagementHqlProvider.findMemberShipCardList(params1);
							List<MemberShipCard> memberShipCardList = null;
							try {
								memberShipCardList = storeWebService.findEntityList(hql1.toString(), params1);
								if (memberShipCardList == null) {
									code = "Invalid memberShipCardCode";
									msg = "会员卡不存在!";
								}
							} catch (Exception e) {
								//e.printStackTrace();
								code = "error";
								msg = e.getMessage() + "查询会员卡信息出错";
							}
							BeanCopier copier1 = BeanCopier.create(MemberShipCard.class, MemberShipCardVo.class, false);
							if (memberShipCardList != null && memberShipCardList.size() > 0) {
								for (MemberShipCard memberShipCard : memberShipCardList) {
									MemberShipCardVo memberShipCardVo = new MemberShipCardVo();
									copier1.copy(memberShipCard, memberShipCardVo, null);
									memberShipCard.setIsDownLoad("2");
									try {
										storeWebService.saveEntity(memberShipCard);
										memberShipCardVoList.add(memberShipCardVo);
									} catch (Exception e) {
										//e.printStackTrace();
										code = "error";
										msg = e.getMessage() + "更新会员卡信息出错";
									}
								}
							}
							customerAccountVo.setMemberShipCardVoList(memberShipCardVoList);
							customerAccount.setIsDownLoad("2");
							storeWebService.saveEntity(customerAccount);
						}

						customerAccountVoList.add(customerAccountVo);
						totalResults++;
						latestOperateController.saveOperateLog("", customerAccount.getCode(), "", storeCode, customerAccount.getTenantId(), storeCode, null, customerAccount.getCompanyCode(), channelDistributor.getCompanyInnerCode());
					}
				}
			}
		} catch (Exception e) {
			//e.printStackTrace();
			code = "error";
			msg = e.getMessage() + "批量查询会员信息出错";
		}
		customerAccountResponse.setCode(code);
		customerAccountResponse.setMsg(msg);
		customerAccountResponse.setTotalResults(totalResults);
		customerAccountResponse.setCustomerAccountVoList(customerAccountVoList);
		return customerAccountResponse;
	}

	@Override
	public CustomerAccountResponse downLoadStoreZoVip(String vipcode) {
		CustomerAccountResponse customerAccountResponse = new CustomerAccountResponse();
		CustomerAccountVo customerAccountVo = new CustomerAccountVo();
		String code = "0";
		String msg = "下载成功!";
		try {
			BeanCopier copier = BeanCopier.create(CustomerAccount.class, CustomerAccountVo.class, false);
			CustomerAccount customerAccount = storeWebService.findEntityByAttribute(CustomerAccount.class, "code", vipcode);
			if (customerAccount != null) {
				copier.copy(customerAccount, customerAccountVo, null);
			} else {
				code = "Invalid member cardno";
				msg = "会员编码错误!";
			}
		} catch (Exception e) {
			//e.printStackTrace();
			code = "error";
			msg = e.getMessage() + "下载会员信息出错";
		}
		customerAccountResponse.setCode(code);
		customerAccountResponse.setMsg(msg);
		customerAccountResponse.setCustomerAccountVo(customerAccountVo);
		return customerAccountResponse;
	}

	@Override
	public InventoryCurrentStockResponse downLoadInventoryCurrentStock(String storeCode) {
		InventoryCurrentStockResponse downLoadInventoryCurrentStockResponse = new InventoryCurrentStockResponse();
		String code = "0";
		String msg = "下载成功!";
		List<InventoryCurrentStockVo> inventoryCurrentStockVoList = new ArrayList<InventoryCurrentStockVo>();
		ChannelDistributor channelDistributor = null;
		try {
			channelDistributor = storeWebService.findEntityByAttribute(ChannelDistributor.class, "code", storeCode);
			if (channelDistributor == null) {
				code = "Invalid store code";
				msg = "门店不存在!";
			}

		} catch (Exception e) {
			/*e.printStackTrace();*/
			code = "error";
			msg = e.getMessage() + "查询门店信息出错";
		}
		if (channelDistributor != null) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("storecode", storeCode);
			params.put("tenantId", channelDistributor.getTenantId());
			StringBuilder hql = storeManagementHqlProvider.findInventoryCurrentStockList(params);
			List<InventoryCurrentStock> inventoryCurrentStockList;
			try {
				inventoryCurrentStockList = storeWebService.findEntityList(hql.toString(), params);
				BeanCopier copier = BeanCopier.create(InventoryCurrentStock.class, InventoryCurrentStockVo.class, false);
				if (inventoryCurrentStockList != null && inventoryCurrentStockList.size() > 0) {
					for (InventoryCurrentStock inventoryCurrentStock : inventoryCurrentStockList) {
						InventoryCurrentStockVo inventoryCurrentStockVo = new InventoryCurrentStockVo();
						copier.copy(inventoryCurrentStock, inventoryCurrentStockVo, null);
						inventoryCurrentStockVoList.add(inventoryCurrentStockVo);
					}
				}
			} catch (Exception e) {
				//e.printStackTrace();
				code = "error";
				msg = e.getMessage() + "下载库存出错";
			}
		} else {
		}
		downLoadInventoryCurrentStockResponse.setCode(code);
		downLoadInventoryCurrentStockResponse.setMsg(msg);
		downLoadInventoryCurrentStockResponse.setInventoryCurrentStockVoList(inventoryCurrentStockVoList);
		return downLoadInventoryCurrentStockResponse;
	}

	@Override
	public UpLoadResponse upLoadInventoryCurrentStock(String storeCode, List<InventoryCurrentStockVo> inventoryCurrentStockVoList) {
		ChannelDistributor channelDistributor = null;
		UpLoadResponse upLoadResponse = new UpLoadResponse();
		String code = "0";
		String msg = "上传成功!";
		try {
			channelDistributor = storeWebService.findEntityByAttribute(ChannelDistributor.class, "code", storeCode);
			if (channelDistributor == null) {
				code = "Invalid store code";
				msg = "门店不存在!";
			}
		} catch (Exception e) {
			//e.printStackTrace();
			code = "error";
			msg = e.getMessage() + "查询门店出错";
		}
		List<InventoryCurrentStock> inventoryCurrentStockList = new ArrayList<InventoryCurrentStock>();
		BeanCopier copier = BeanCopier.create(InventoryCurrentStockVo.class, InventoryCurrentStock.class, false);
		if (inventoryCurrentStockVoList != null && inventoryCurrentStockVoList.size() > 0) {
			for (InventoryCurrentStockVo inventoryCurrentStockVo : inventoryCurrentStockVoList) {
				InventoryCurrentStock inventoryCurrentStock = new InventoryCurrentStock();
				copier.copy(inventoryCurrentStockVo, inventoryCurrentStock, null);
				if (channelDistributor != null) {
					inventoryCurrentStock.setChannelDistributor(channelDistributor);
					inventoryCurrentStock.setTenantId(channelDistributor.getTenantId());
					inventoryCurrentStock.setCompanyCode(channelDistributor.getCompanyCode());
					inventoryCurrentStock.setCompanyInnerCode(channelDistributor.getCompanyInnerCode());
					try {
						latestOperateController.saveOperateLog("", "", "", "上传商品信息", channelDistributor.getTenantId(), storeCode, "门店", channelDistributor.getCompanyCode(), channelDistributor.getCompanyInnerCode());
					} catch (Exception e) {
						//e.printStackTrace();
						code = "error";
						msg = e.getMessage() + "查询库存信息出错";
					}
				}
				inventoryCurrentStock.setIsTemp("");
				inventoryCurrentStock.setIsQualfied(1);
				inventoryCurrentStockList.add(inventoryCurrentStock);
			}
		}
		if (inventoryCurrentStockList != null && inventoryCurrentStockList.size() > 0) {
			for (InventoryCurrentStock inventoryCurrentStock : inventoryCurrentStockList) {
				InventoryCurrentStock inc;
				try {
					inc = storeWebService.findEntityByAttribute(InventoryCurrentStock.class, "code", inventoryCurrentStock.getCode());
					if (inc != null) {
						inventoryCurrentStock.setId(inc.getId());
						BeanCopier b = BeanCopier.create(InventoryCurrentStock.class, InventoryCurrentStock.class, false);
						b.copy(inventoryCurrentStock, inc, null);
						storeWebService.saveEntity(inc);
					} else {
						storeWebService.saveEntity(inventoryCurrentStock);
					}
				} catch (Exception e) {
					//e.printStackTrace();
					code = "error";
					msg = e.getMessage() + "更新库存出错";
				}
			}
		}
		upLoadResponse.setCode(code);
		upLoadResponse.setMsg(msg);
		return upLoadResponse;
	}

	@Override
	public UpLoadResponse upLoadZoVipList(List<CustomerAccountVo> customerAccountVoList, String storeCode) {
		UpLoadResponse upLoadResponse = new UpLoadResponse();
		ChannelDistributor channelDistributor = null;
		String code = "0";
		String msg = "上传成功!";

		try {
			channelDistributor = storeWebService.findEntityByAttribute(ChannelDistributor.class, "code", storeCode);
			if (channelDistributor == null) {
				code = "Invalid store code";
				msg = "门店不存在!";
			} else {
				List<CustomerAccount> customerAccountList = new ArrayList<CustomerAccount>();
				BeanCopier copier = BeanCopier.create(CustomerAccountVo.class, CustomerAccount.class, false);

				if (customerAccountVoList != null && customerAccountVoList.size() > 0) {
					for (CustomerAccountVo customerAccountVo : customerAccountVoList) {
						CustomerAccount customerAccount = new CustomerAccount();
						copier.copy(customerAccountVo, customerAccount, null);
						customerAccount.setIsTemp("");
						customerAccount.setType("personal");
						if (channelDistributor != null) {
							customerAccount.setChannelDistributor(channelDistributor);
							customerAccount.setTenantId(channelDistributor.getTenantId());
							customerAccount.setCompanyCode(channelDistributor.getCompanyCode());
							customerAccount.setCompanyInnerCode(channelDistributor.getCompanyInnerCode());
							if (customerAccount.getBirthday() != null) {
								customerAccount.setAge(getAge(customerAccount.getBirthday()));
							}
							try {
								latestOperateController.saveOperateLog("", "", "", "上传会员信息", channelDistributor.getTenantId(), storeCode, "门店", channelDistributor.getCompanyCode(), channelDistributor.getCompanyInnerCode());
							} catch (Exception e) {
								//e.printStackTrace();
								code = "error";
								msg = e.getMessage() + "保存会员信息日志出错";
							}
						}
						customerAccountList.add(customerAccount);
					}
				}

				if (customerAccountList != null && customerAccountList.size() > 0) {
					for (CustomerAccount customerAccount : customerAccountList) {
						if (customerAccount != null) {
							CustomerAccount c;
							try {
								c = storeWebService.findEntityByAttributeNoTenantId(CustomerAccount.class, "code", customerAccount.getCode());
								if (c != null) {
									customerAccount.setId(c.getId());
									storeWebService.saveEntity(customerAccount);
								} else {
									storeWebService.saveEntity(customerAccount);
								}
							} catch (Exception e) {
								//e.printStackTrace();
								code = "error";
								msg = e.getMessage() + "保存会员信息出错";
							}
						}
					}
				}
			}

		} catch (Exception e) {
			//e.printStackTrace();
			code = "error";
			msg = e.getMessage() + "查询门店出错";
		}

		upLoadResponse.setCode(code);
		upLoadResponse.setMsg(msg);
		return upLoadResponse;
	}

	/** 计算年龄 */
	public static Integer getAge(Date birthDay) throws Exception {
		Calendar cal = Calendar.getInstance();

		if (cal.before(birthDay)) {
			throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
		}

		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

		cal.setTime(birthDay);
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				}
			} else {
				age--;
			}
		}

		return age;
	}

	@Override
	public UpLoadResponse upLoadZoVipCardLog(List<ZoVipCardLogVo> zoVipCardLogVoList, String storeCode) {
		ChannelDistributor channelDistributor = null;
		UpLoadResponse upLoadResponse = new UpLoadResponse();
		String code = "0";
		String msg = "上传成功!";
		try {
			channelDistributor = storeWebService.findEntityByAttribute(ChannelDistributor.class, "code", storeCode);
			if (channelDistributor == null) {
				code = "Invalid store code";
				msg = "门店不存在!";
			} else {
				BeanCopier copier = BeanCopier.create(ZoVipCardLogVo.class, ZoVipCardLog.class, false);
				List<ZoVipCardLog> zoVipCardLogList = new ArrayList<ZoVipCardLog>();
				if (zoVipCardLogVoList != null && zoVipCardLogVoList.size() > 0) {
					for (ZoVipCardLogVo zoVipCardLogVo : zoVipCardLogVoList) {
						if (zoVipCardLogVo != null) {
							try {
								MemberShipCard memberShipCard = storeWebService.findEntityByAttributeNoTenantId(MemberShipCard.class, "vipcardid", zoVipCardLogVo.getVipcardid());
								ZoVipCardLog zoVipCardLog = new ZoVipCardLog();
								copier.copy(zoVipCardLogVo, zoVipCardLog, null);
								if (channelDistributor != null) {
									zoVipCardLog.setChannelDistributor(channelDistributor);
									zoVipCardLog.setTenantId(channelDistributor.getTenantId());
									zoVipCardLog.setCompanyCode(channelDistributor.getCompanyCode());
									zoVipCardLog.setCompanyInnerCode(channelDistributor.getCompanyInnerCode());
									latestOperateController.saveOperateLog("", "", "", "上传会员卡日志信息", channelDistributor.getTenantId(), storeCode, "门店", channelDistributor.getCompanyCode(), channelDistributor.getCompanyInnerCode());
								}
								zoVipCardLog.setIsTemp("");
								if (memberShipCard != null) {
									zoVipCardLog.setMemberShipCard(memberShipCard);
								} else {
									code = "Invalid member cardno";
									msg = "会员不存在!";
								}
								zoVipCardLogList.add(zoVipCardLog);
							} catch (Exception e) {
								//e.printStackTrace();
								code = "error";
								msg = e.getMessage() + "查询会员卡信息出错";
							}
						}
					}
				}
				if (zoVipCardLogList != null && zoVipCardLogList.size() > 0) {
					for (ZoVipCardLog zoVipCardLog : zoVipCardLogList) {
						if (zoVipCardLog != null) {
							try {
								ZoVipCardLog z = storeWebService.findEntityByAttributeNoTenantId(ZoVipCardLog.class, "logid", zoVipCardLog.getLogid());
								if (z != null) {
									zoVipCardLog.setId(z.getId());
									BeanCopier b = BeanCopier.create(ZoVipCardLog.class, ZoVipCardLog.class, false);
									b.copy(zoVipCardLog, z, null);
									storeWebService.saveEntity(z);
								} else {
									storeWebService.saveEntity(zoVipCardLog);
								}
							} catch (Exception e) {
								//e.printStackTrace();
								code = "error";
								msg = e.getMessage() + "查询会员卡日志信息出错";
							}
						}
					}
				}
			}
		} catch (Exception e) {
			//e.printStackTrace();
			code = "error";
			msg = e.getMessage() + "查询门店信息出错";
		}

		upLoadResponse.setCode(code);
		upLoadResponse.setMsg(msg);
		return upLoadResponse;
	}

	@Override
	public UpLoadResponse upLoadMemberShipCardList(List<MemberShipCardVo> memberShipCardVoList, String storeCode) {
		ChannelDistributor channelDistributor = null;
		UpLoadResponse upLoadResponse = new UpLoadResponse();
		String code = "0";
		String msg = "上传成功!";
		try {
			channelDistributor = storeWebService.findEntityByAttribute(ChannelDistributor.class, "code", storeCode);
			if (channelDistributor == null) {
				code = "Invalid store code";
				msg = "门店不存在!";
			}
		} catch (Exception e) {
			//e.printStackTrace();
			code = "error";
			msg = e.getMessage() + "查询门店信息出错";
		}
		BeanCopier copier1 = BeanCopier.create(MemberShipCardVo.class, MemberShipCard.class, false);
		List<MemberShipCard> memberShipCardList = new ArrayList<MemberShipCard>();
		if (memberShipCardVoList != null && memberShipCardVoList.size() > 0) {
			for (MemberShipCardVo memberShipCardVo : memberShipCardVoList) {
				if (memberShipCardVo != null) {
					try {
						MemberShipCard memberShipCard = new MemberShipCard();
						copier1.copy(memberShipCardVo, memberShipCard, null);
						CustomerAccount customerAccount = storeWebService.findEntityByAttributeNoTenantId(CustomerAccount.class, "code", memberShipCard.getVipid());
						if (customerAccount != null) {
							memberShipCard.setCustomerAccount(customerAccount);
							memberShipCard.setCompanyCode(customerAccount.getCompanyCode());
							memberShipCard.setCompanyInnerCode(customerAccount.getCompanyInnerCode());
						} else {
							code = "Invalid member cardno";
							msg = "会员不存在!";
						}
						memberShipCard.setIsTemp("");
						memberShipCard.setIsstop("2");
						memberShipCard.setIsdestruct("2");
						MemberShipCardType memberShipCardType = storeWebService.findEntityByAttributeNoTenantId(MemberShipCardType.class, "vipTypeId", memberShipCard.getViptypeid());
						if (memberShipCardType != null) {
							memberShipCard.setVipTypeName(memberShipCardType.getVipTypeName());
						}
						if (channelDistributor != null) {
							memberShipCard.setTenantId(channelDistributor.getTenantId());
							memberShipCard.setChannelDistributor(channelDistributor);
							latestOperateController.saveOperateLog("", "", "", "上传会员卡信息", channelDistributor.getTenantId(), storeCode, "门店", channelDistributor.getCompanyCode(), channelDistributor.getCompanyInnerCode());
						}
						memberShipCardList.add(memberShipCard);
					} catch (Exception e) {
						//e.printStackTrace();
						code = "error";
						msg = e.getMessage() + "查询会员信息出错";
					}
				}
			}
		}
		if (memberShipCardList != null && memberShipCardList.size() > 0) {
			for (MemberShipCard memberShipCard : memberShipCardList) {
				if (memberShipCard != null) {
					try {
						MemberShipCard m = storeWebService.findEntityByAttributeNoTenantId(MemberShipCard.class, "vipcardid", memberShipCard.getVipcardid());
						if (m != null) {
							memberShipCard.setId(m.getId());
							BeanCopier b = BeanCopier.create(MemberShipCard.class, MemberShipCard.class, false);
							b.copy(memberShipCard, m, null);
							storeWebService.saveEntity(m);
						} else {
							storeWebService.saveEntity(memberShipCard);
						}
					} catch (Exception e) {
						//e.printStackTrace();
						code = "error";
						msg = e.getMessage() + "查询会员卡信息出错";
					}
				}
			}
		}
		upLoadResponse.setCode(code);
		upLoadResponse.setMsg(msg);
		return upLoadResponse;
	}

	@Override
	public UpLoadResponse upLoadZoCardBill(List<SalesOrderVo> salesOrderVoList, List<SalesOrderItemVo> salesOrderItemVoList, List<ZoCardBillPaymentVo> zoCardBillPaymentVoList, String storeCode) {

		ChannelDistributor channelDistributor = null;
		UpLoadResponse upLoadResponse = new UpLoadResponse();
		String code = "0";
		String msg = "上传成功!";
		try {
			channelDistributor = storeWebService.findEntityByAttribute(ChannelDistributor.class, "code", storeCode);
			if (channelDistributor == null) {
				code = "Invalid store code";
				msg = "门店不存在!";
			}
		} catch (Exception e) {
			//e.printStackTrace();
			code = "error";
			msg = e.getMessage();
		}
		BeanCopier copier = BeanCopier.create(SalesOrderVo.class, SalesOrder.class, false);
		List<SalesOrder> salesOrderList = new ArrayList<SalesOrder>();
		if (salesOrderVoList != null && salesOrderVoList.size() > 0) {
			for (SalesOrderVo salesOrderVo : salesOrderVoList) {
				SalesOrder salesOrder = new SalesOrder();
				copier.copy(salesOrderVo, salesOrder, null);
				if (channelDistributor != null) {
					salesOrder.setChannelDistributor(channelDistributor);
					salesOrder.setTenantId(channelDistributor.getTenantId());
					salesOrder.setCompanyCode(channelDistributor.getCompanyCode());
					salesOrder.setCompanyInnerCode(channelDistributor.getCompanyInnerCode());
					try {
						latestOperateController.saveOperateLog("", "", "", "上传会员消费信息", channelDistributor.getTenantId(), storeCode, "门店", channelDistributor.getCompanyCode(), channelDistributor.getCompanyInnerCode());
					} catch (Exception e) {
						//e.printStackTrace();
						code = "error";
						msg = e.getMessage() + "保存会员消费信息出错";
					}
				}
				salesOrder.setIsTemp("");
				salesOrder.setBizType("售币");
				salesOrder.setPayType("现金");
				salesOrderList.add(salesOrder);
			}
		}
		if (salesOrderList != null && salesOrderList.size() > 0) {
			for (SalesOrder salesOrder : salesOrderList) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("code", salesOrder.getCode());
				params.put("cashbatch", salesOrder.getCashbatch());
				StringBuilder hql = storeManagementHqlProvider.findSalesOrder(params);
				SalesOrder s;
				try {
					s = storeWebService.findEntity(hql.toString(), params);
					if (s != null) {
						salesOrder.setId(s.getId());
						BeanCopier b = BeanCopier.create(SalesOrder.class, SalesOrder.class, false);
						b.copy(salesOrder, s, null);
						storeWebService.saveEntity(s);
					} else {
						storeWebService.saveEntity(salesOrder);
					}
				} catch (Exception e) {
					//e.printStackTrace();
					code = "error";
					msg = e.getMessage() + "查询订单信息出错";
				}
			}
		}
		// 保存SaleOrderItem
		BeanCopier copier1 = BeanCopier.create(SalesOrderItemVo.class, SaleOrderItem.class, false);
		List<SaleOrderItem> saleOrderItemList = new ArrayList<SaleOrderItem>();
		if (salesOrderItemVoList != null && salesOrderItemVoList.size() > 0) {
			for (SalesOrderItemVo salesOrderItemVo : salesOrderItemVoList) {
				SaleOrderItem saleOrderItem = new SaleOrderItem();
				copier1.copy(salesOrderItemVo, saleOrderItem, null);
				SalesOrder salesOrder;
				try {
					salesOrder = storeWebService.findEntityByAttributeNoTenantId(SalesOrder.class, "code", saleOrderItem.getPoCode());
					saleOrderItem.setSalesOrder(salesOrder);
					saleOrderItemList.add(saleOrderItem);
				} catch (Exception e) {
					//e.printStackTrace();
					code = "error";
					msg = e.getMessage() + "根据code查询订单信息出错";
				}
			}
		}
		if (saleOrderItemList != null && saleOrderItemList.size() > 0) {
			for (SaleOrderItem saleOrderItem : saleOrderItemList) {
				SaleOrderItem s;
				try {
					s = storeWebService.findEntityByAttributeNoTenantId(SaleOrderItem.class, "code", saleOrderItem.getCode());
					if (s != null) {
						saleOrderItem.setId(s.getId());
					} else {
					}
					saleOrderItem.setTenantId(channelDistributor.getTenantId());
					saleOrderItem.setCompanyCode(channelDistributor.getCompanyCode());
					saleOrderItem.setCompanyInnerCode(channelDistributor.getCompanyInnerCode());
					storeWebService.saveEntity(saleOrderItem);
				} catch (Exception e) {
					//e.printStackTrace();
					code = "error";
					msg = e.getMessage() + "查询订单明细出错";
				}
			}
		}
		// 保存ZoCardBillPayment
		BeanCopier copier2 = BeanCopier.create(ZoCardBillPaymentVo.class, ZoCardBillPayment.class, false);
		List<ZoCardBillPayment> zoCardBillPaymentList = new ArrayList<ZoCardBillPayment>();
		if (zoCardBillPaymentVoList != null && zoCardBillPaymentVoList.size() > 0) {
			for (ZoCardBillPaymentVo zoCardBillPaymentVo : zoCardBillPaymentVoList) {
				ZoCardBillPayment zoCardBillPayment = new ZoCardBillPayment();
				copier2.copy(zoCardBillPaymentVo, zoCardBillPayment, null);
				zoCardBillPaymentList.add(zoCardBillPayment);
			}
		}
		if (zoCardBillPaymentList != null && zoCardBillPaymentList.size() > 0) {
			for (ZoCardBillPayment zoCardBillPayment : zoCardBillPaymentList) {
				ZoCardBillPayment s;
				try {
					s = storeWebService.findEntityByAttributeNoTenantId(ZoCardBillPayment.class, "subiectid", zoCardBillPayment.getSubiectid());
					if (s != null) {
						zoCardBillPayment.setId(s.getId());
						BeanCopier b = BeanCopier.create(ZoCardBillPayment.class, ZoCardBillPayment.class, false);
						b.copy(zoCardBillPayment, s, null);
						storeWebService.saveEntity(s);
					} else {
						zoCardBillPayment.setTenantId(channelDistributor.getTenantId());
						zoCardBillPayment.setCompanyCode(channelDistributor.getCompanyCode());
						zoCardBillPayment.setCompanyInnerCode(channelDistributor.getCompanyInnerCode());
						storeWebService.saveEntity(zoCardBillPayment);
					}
				} catch (Exception e) {
					//e.printStackTrace();
					code = "error";
					msg = e.getMessage() + "查询支付方式出错";
				}
			}
		}
		upLoadResponse.setCode(code);
		upLoadResponse.setMsg(msg);
		return upLoadResponse;
	}

	@Override
	public UpLoadResponse upLoadTranLog(List<TranLogVo> tranLogVoList, String storeCode) {
		ChannelDistributor channelDistributor = null;
		UpLoadResponse upLoadResponse = new UpLoadResponse();
		String code = "0";
		String msg = "上传成功!";
		try {
			channelDistributor = storeWebService.findEntityByAttribute(ChannelDistributor.class, "code", storeCode);
			if (channelDistributor == null) {
				code = "Invalid store code";
				msg = "门店编码不正确!";
			}
		} catch (Exception e) {
			//e.printStackTrace();
			code = "error";
			msg = e.getMessage() + "查询门店出错";
		}

		BeanCopier copier1 = BeanCopier.create(TranLogVo.class, TranLog.class, false);
		List<TranLog> tranLogList = new ArrayList<TranLog>();
		if (tranLogVoList != null && tranLogVoList.size() > 0) {
			for (TranLogVo tranLogVo : tranLogVoList) {
				TranLog tranLog = new TranLog();
				copier1.copy(tranLogVo, tranLog, null);
				if (channelDistributor != null) {
					tranLog.setCompanyCode(channelDistributor.getCompanyCode());
					tranLog.setChannelDistributor(channelDistributor);
					tranLog.setTenantId(channelDistributor.getTenantId());
					tranLog.setCompanyInnerCode(channelDistributor.getCompanyInnerCode());
					try {
						latestOperateController.saveOperateLog("", "", "", "上传机台消费日志" + tranLogVo.getVipCardId(), channelDistributor.getTenantId(), storeCode, "门店", channelDistributor.getCompanyCode(), channelDistributor.getCompanyInnerCode());
					} catch (Exception e) {
						//e.printStackTrace();
						code = "error";
						msg = e.getMessage() + "保存机台消费日志出错";
					}
				}
				tranLogList.add(tranLog);
			}
		}
		if (tranLogList != null && tranLogList.size() > 0) {
			for (TranLog tranLog : tranLogList) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("vipCardId", tranLog.getVipCardId());
				params.put("macId", tranLog.getMacId());
				params.put("msgguid", tranLog.getMsgguid());
				StringBuilder hql = storeManagementHqlProvider.findTranLog(params);
				TranLog t;
				try {
					t = storeWebService.findEntity(hql.toString(), params);
					if (t != null) {
						tranLog.setId(t.getId());
						BeanCopier b = BeanCopier.create(TranLog.class, TranLog.class, false);
						b.copy(tranLog, t, null);
						storeWebService.saveEntity(t);
					} else {
						storeWebService.saveEntity(tranLog);
					}
				} catch (Exception e) {
					//e.printStackTrace();
					code = "error";
					msg = e.getMessage() + "保存机台日志出错";
				}
			}
		}
		upLoadResponse.setCode(code);
		upLoadResponse.setMsg(msg);
		return upLoadResponse;
	}

	@Override
	public UpLoadResponse upMemberShipCardType(List<MemberShipCardTypeVo> memberShipCardTypeVoList, String storeCode) {
		ChannelDistributor channelDistributor = null;
		UpLoadResponse upLoadResponse = new UpLoadResponse();
		String code = "0";
		String msg = "上传成功!";
		try {
			channelDistributor = storeWebService.findEntityByAttribute(ChannelDistributor.class, "code", storeCode);
			if (channelDistributor == null) {
				code = "Invalid store code";
				msg = "门店不存在!";
			}
		} catch (Exception e) {
			//			e.printStackTrace();
			code = "error";
			msg = e.getMessage() + "查询门店信息出错";
		}
		List<MemberShipCardType> memberShipCardTypeList = new ArrayList<MemberShipCardType>();
		BeanCopier copier = BeanCopier.create(MemberShipCardTypeVo.class, MemberShipCardType.class, false);
		if (memberShipCardTypeVoList != null && memberShipCardTypeVoList.size() > 0) {
			for (MemberShipCardTypeVo memberShipCardTypeVo : memberShipCardTypeVoList) {
				MemberShipCardType memberShipCardType = new MemberShipCardType();
				copier.copy(memberShipCardTypeVo, memberShipCardType, null);
				if (channelDistributor != null) {
					memberShipCardType.setChannelDistributor(channelDistributor);
					memberShipCardType.setTenantId(channelDistributor.getTenantId());
					memberShipCardType.setCompanyCode(channelDistributor.getCompanyCode());
					memberShipCardType.setCompanyInnerCode(channelDistributor.getCompanyInnerCode());
					try {
						latestOperateController.saveOperateLog("", "", "", "上传会员卡信息", channelDistributor.getTenantId(), storeCode, "门店", channelDistributor.getCompanyCode(), channelDistributor.getCompanyInnerCode());
					} catch (Exception e) {
						//e.printStackTrace();
						code = "error";
						msg = e.getMessage() + "保存会员信息出错";
					}
				}
				memberShipCardTypeList.add(memberShipCardType);
			}
		}
		if (memberShipCardTypeList != null && memberShipCardTypeList.size() > 0) {
			for (MemberShipCardType memberShipCardType : memberShipCardTypeList) {
				MemberShipCardType inc;
				try {
					inc = storeWebService.findEntityByAttributeNoTenantId(MemberShipCardType.class, "vipTypeId", memberShipCardType.getVipTypeId());
					if (inc != null) {
						memberShipCardType.setId(inc.getId());
						BeanCopier b = BeanCopier.create(MemberShipCardType.class, MemberShipCardType.class, false);
						b.copy(memberShipCardType, inc, null);
						storeWebService.saveEntity(inc);
					} else {
						storeWebService.saveEntity(memberShipCardType);
					}
				} catch (Exception e) {
					//					e.printStackTrace();
					code = "error";
					msg = e.getMessage() + "保存会员卡类型出错";
				}
			}
		}
		upLoadResponse.setCode(code);
		upLoadResponse.setMsg(msg);
		return upLoadResponse;
	}

	public static void main(String args[]) {
		try {
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println(getAge(s.parse("1984-02-09")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
