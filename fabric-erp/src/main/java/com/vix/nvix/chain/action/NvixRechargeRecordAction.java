package com.vix.nvix.chain.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.id.VixUUID;
import com.vix.common.message.constant.MessageTemplateConstant;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.NumberUtil;
import com.vix.core.web.Pager;
import com.vix.crm.member.entity.MemberLevel;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.channel.entity.ChannelDistributorMessageSet;
import com.vix.drp.channel.entity.SMSCostRules;
import com.vix.drp.integralRulesSet.entity.IntegralRules;
import com.vix.drp.pointRecord.entity.PointRecord;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.mdm.crm.entity.CustomerAccountClip;
import com.vix.mdm.crm.entity.CustomerAccountClipDetail;
import com.vix.mdm.crm.entity.RechargeRecord;
import com.vix.mdm.crm.entity.RechargeRecordDetail;
import com.vix.mdm.crm.entity.StoredValueRuleSet;
import com.vix.mdm.crm.entity.StoredValueRuleSetDetail;
import com.vix.nvix.common.base.action.VixntBaseAction;
/**
 * 充值记录
 * @author jackie
 *
 */
@Controller
@Scope("prototype")
public class NvixRechargeRecordAction extends VixntBaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String payType;
	private RechargeRecord rechargeRecord;
	private CustomerAccountClip customerAccountClip;
	private StoredValueRuleSet storedValueRuleSet;
	private CustomerAccount customerAccount;
	private RechargeRecordDetail rechargeRecordDetail;
	private ChannelDistributor channelDistributor;
	private String storedValueRuleSetId;
	private String customerAccountClipId;
	private String customerAccountId;
	private String rechargeRecordId;
	private MemberLevel memberLevel;
	private String cardType;
	public void goSingleList(){
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("payDate");
			params.put("isTemp,"+SearchCondition.NOEQUAL, "1");
			pager = vixntBaseService.findPagerByHqlConditions(pager, RechargeRecord.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void goSingleListByCustomerAccountId(){
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("payDate");
			params.put("isTemp,"+SearchCondition.NOEQUAL, "1");
			if(StringUtils.isNotEmpty(customerAccountClipId)){
				params.put("customerAccountClip.id,"+SearchCondition.EQUAL, customerAccountClipId);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, RechargeRecord.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 会员充值
	 */
	public void saveOrUpdate(){
		try {
			if(StringUtils.isNotEmpty(id) && StringUtils.isNotEmpty(payType)&&StringUtils.isNotEmpty(customerAccountClipId)){
				rechargeRecord = vixntBaseService.findEntityById(RechargeRecord.class, id);
				customerAccountClip = vixntBaseService.findEntityById(CustomerAccountClip.class, customerAccountClipId);
				if(rechargeRecord.getStoredValueRuleSet() != null){
					storedValueRuleSet = rechargeRecord.getStoredValueRuleSet();
				}
				Employee employee = getEmployee();
				if(employee != null){
					channelDistributor = vixntBaseService.findEntityById(ChannelDistributor.class, employee.getChannelDistributor().getId());
				}
				if(null != customerAccountClip){
					customerAccount = vixntBaseService.findEntityById(CustomerAccount.class, customerAccountClip.getCustomerAccount().getId());
					if("1".equals(customerAccountClip.getCard().getType())){
						if(null != rechargeRecord && null != customerAccount){
							if(null != customerAccount){
								if(null != storedValueRuleSet){
									customerAccount.setMoney(customerAccount.getMoney()+storedValueRuleSet.getAmount()+storedValueRuleSet.getGiftAmount());
									customerAccountClip.setMoney(customerAccountClip.getMoney()+storedValueRuleSet.getAmount()+storedValueRuleSet.getGiftAmount());
									Map<String, Object> params = new HashMap<String, Object>();
									List<IntegralRules> integralRuleList = vixntBaseService.findAllDataByConditions(IntegralRules.class, params);
									if(integralRuleList != null && integralRuleList.size() > 0){
										IntegralRules integralRules = integralRuleList.get(0);
										Double point = rechargeRecord.getPayMoney()* integralRules.getConversiorate();
										point = Math.floor(point);
										if(customerAccount.getPoint() != null && customerAccount.getPoint() > 0){
											customerAccount.setPoint(customerAccount.getPoint() + point+storedValueRuleSet.getGiftPoints());
										}else{
											customerAccount.setPoint(point+storedValueRuleSet.getGiftPoints());
										}
										if(customerAccount.getAmountPoint() != null && customerAccount.getAmountPoint() > 0){
											customerAccount.setAmountPoint(customerAccount.getAmountPoint() + point+storedValueRuleSet.getGiftPoints());
										}else{
											customerAccount.setAmountPoint(point+storedValueRuleSet.getGiftPoints());
										}
										PointRecord pointRecord = new PointRecord();
										pointRecord.setCreateTime(new Date());
										pointRecord.setUpdateTime(new Date());
										pointRecord.setPointSource("充值赠送积分");
										pointRecord.setOperation("充值");
										pointRecord.setPointType("1");
										pointRecord.setPointValue(point+storedValueRuleSet.getGiftPoints());
										pointRecord.setCustomerAccount(customerAccount);
										initEntityBaseController.initEntityBaseAttribute(pointRecord);
										pointRecord = vixntBaseService.merge(pointRecord);
									}
								}else{
									customerAccount.setMoney(customerAccount.getMoney()+rechargeRecord.getPayMoney());
									customerAccountClip.setMoney(customerAccountClip.getMoney()+rechargeRecord.getPayMoney());
									Map<String, Object> params = new HashMap<String, Object>();
									List<IntegralRules> integralRuleList = vixntBaseService.findAllDataByConditions(IntegralRules.class, params);
									if(integralRuleList != null && integralRuleList.size() > 0){
										IntegralRules integralRules = integralRuleList.get(0);
										Double point = rechargeRecord.getPayMoney()* integralRules.getConversiorate();
										point = Math.floor(point);
										if(customerAccount.getPoint() != null && customerAccount.getPoint() > 0){
											customerAccount.setPoint(customerAccount.getPoint() + point);
										}else{
											customerAccount.setPoint(point);
										}
										if(customerAccount.getAmountPoint() != null && customerAccount.getAmountPoint() > 0){
											customerAccount.setAmountPoint(customerAccount.getAmountPoint() + point);
										}else{
											customerAccount.setAmountPoint(point);
										}
										PointRecord pointRecord = new PointRecord();
										pointRecord.setCreateTime(new Date());
										pointRecord.setUpdateTime(new Date());
										pointRecord.setPointSource("充值赠送积分");
										pointRecord.setOperation("充值");
										pointRecord.setPointType("1");
										pointRecord.setPointValue(point);
										pointRecord.setCustomerAccount(customerAccount);
										initEntityBaseController.initEntityBaseAttribute(pointRecord);
										pointRecord = vixntBaseService.merge(pointRecord);
									}
								}
							}
							customerAccountClip.setUpdateTime(new Date());
							customerAccount.setUpdateTime(new Date());
							initEntityBaseController.initEntityBaseAttribute(customerAccount);
							customerAccount = vixntBaseService.merge(customerAccount);
							checkMemberLevel(customerAccount);
							customerAccountClip.setPointValue(customerAccount.getPoint());
							initEntityBaseController.initEntityBaseAttribute(customerAccountClip);
							customerAccountClip = vixntBaseService.merge(customerAccountClip);
							rechargeRecord.setCode(VixUUID.createCode(12));
							rechargeRecord.setIsTemp("");
							rechargeRecord.setPayDate(new Date());
							rechargeRecord.setPayType(payType);
							if(channelDistributor != null){
								rechargeRecord.setChannelDistributor(channelDistributor);
							}
							rechargeRecord.setCustomerAccountClip(customerAccountClip);
							rechargeRecord.setStoredValueRuleSet(storedValueRuleSet);
							initEntityBaseController.initEntityBaseAttribute(rechargeRecord);
							rechargeRecord = vixntBaseService.merge(rechargeRecord);
							if(employee != null){
								channelDistributor = vixntBaseService.findEntityById(ChannelDistributor.class, employee.getChannelDistributor().getId());
								if(channelDistributor != null){
									ChannelDistributorMessageSet channelDistributorMessageSet = vixntBaseService.findEntityByAttribute(ChannelDistributorMessageSet.class,"channelDistributor.id", channelDistributor.getId());
									if(channelDistributorMessageSet != null && "0".equals(channelDistributorMessageSet.getIsEnable()) && "0".equals(channelDistributorMessageSet.getSalesInform())){
										Map<String, Object> params = getParams();
										List<SMSCostRules> sMSCostRulesList = vixntBaseService.findAllByConditions(SMSCostRules.class, params);
										if(null != sMSCostRulesList && sMSCostRulesList.size() >0){
											SMSCostRules smsCostRules = sMSCostRulesList.get(0);
											Double price = smsCostRules.getPrice();
											if(channelDistributorMessageSet.getAmount() != null && channelDistributorMessageSet.getAmount() > 0 && channelDistributorMessageSet.getAmount() > price){
												Map<String, String> orderStatusMap = new HashMap<String, String>();
												orderStatusMap.put(MessageTemplateConstant.username, customerAccount.getName());
												orderStatusMap.put(MessageTemplateConstant.cardNo, customerAccountClip.getName());
												String resp = sendMessage("2", customerAccount.getMobilePhone(), orderStatusMap);
												if(resp != null && resp.contains("|")){
													saveMessageSendRecord(2,"2","充值通知",customerAccount.getMobilePhone(),channelDistributor,employee);
													if(StringUtils.isNotEmpty(String.valueOf(channelDistributorMessageSet.getNum()))&& null !=channelDistributorMessageSet.getNum()){
														channelDistributorMessageSet.setNum(channelDistributorMessageSet.getNum()+1);
													}else{
														channelDistributorMessageSet.setNum(1d);
													}
													channelDistributorMessageSet.setAmount(channelDistributorMessageSet.getAmount() - price);
													initEntityBaseController.initEntityBaseAttribute(channelDistributorMessageSet);
													channelDistributorMessageSet = vixntBaseService.merge(channelDistributorMessageSet);
												}
												System.out.println(resp);
											}
										}
									}
								}
							}
							renderText("1:充值成功!");
						}
					}else if("2".equals(customerAccountClip.getCard().getType())){
						if(null != storedValueRuleSet){
							Map<String, Object> params1 = getParams();
							params1.put("storedValueRuleSet.id,"+SearchCondition.EQUAL, storedValueRuleSet.getId());
							List<StoredValueRuleSetDetail> list = vixntBaseService.findAllByConditions(StoredValueRuleSetDetail.class, params1);
							if(null != list && list.size() > 0){
								for (StoredValueRuleSetDetail storedValueRuleSetDetail : list) {
									if(null != storedValueRuleSetDetail){
										Map<String, Object> params = getParams();
										params.put("item.id,"+SearchCondition.EQUAL, storedValueRuleSetDetail.getItem().getId());
										params.put("customerAccountClip.id,"+SearchCondition.EQUAL, customerAccountClip.getId());
										List<CustomerAccountClipDetail> clipDetailList = vixntBaseService.findAllByConditions(CustomerAccountClipDetail.class, params);
										if(clipDetailList != null && clipDetailList.size() > 0 && clipDetailList.size() == 1){
											CustomerAccountClipDetail customerAccountClipDetail = clipDetailList.get(0);
											customerAccountClipDetail.setNumber(customerAccountClipDetail.getNumber()+storedValueRuleSetDetail.getNum());
											customerAccountClipDetail = vixntBaseService.merge(customerAccountClipDetail);
										}else{
											CustomerAccountClipDetail customerAccountClipDetail = new CustomerAccountClipDetail();
											customerAccountClipDetail.setCustomerAccount(customerAccount);
											customerAccountClipDetail.setCustomerAccountClip(customerAccountClip);
											customerAccountClipDetail.setItem(storedValueRuleSetDetail.getItem());
											customerAccountClipDetail.setNumber(storedValueRuleSetDetail.getNum());
											initEntityBaseController.initEntityBaseAttribute(customerAccountClipDetail);
											customerAccountClipDetail = vixntBaseService.merge(customerAccountClipDetail);
										}
									}
								}
							}
							Map<String, Object> params3 = new HashMap<String, Object>();
							List<IntegralRules> integralRuleList = vixntBaseService.findAllDataByConditions(IntegralRules.class, params3);
							if(integralRuleList != null && integralRuleList.size() > 0){
								IntegralRules integralRules = integralRuleList.get(0);
								Double point = rechargeRecord.getPayMoney()* integralRules.getConversiorate();
								point = Math.floor(point);
								if(customerAccount.getPoint() != null && customerAccount.getPoint() > 0){
									customerAccount.setPoint(customerAccount.getPoint() + point +storedValueRuleSet.getGiftPoints());
								}else{
									customerAccount.setPoint(point+storedValueRuleSet.getGiftPoints());
								}
								if(customerAccount.getAmountPoint() != null && customerAccount.getAmountPoint() > 0){
									customerAccount.setAmountPoint(customerAccount.getAmountPoint() + point+storedValueRuleSet.getGiftPoints());
								}else{
									customerAccount.setAmountPoint(point+storedValueRuleSet.getGiftPoints());
								}
								PointRecord pointRecord = new PointRecord();
								pointRecord.setCreateTime(new Date());
								pointRecord.setUpdateTime(new Date());
								pointRecord.setPointSource("充值赠送积分");
								pointRecord.setOperation("充值");
								pointRecord.setPointType("1");
								pointRecord.setPointValue(point+storedValueRuleSet.getGiftPoints());
								pointRecord.setCustomerAccount(customerAccount);
								initEntityBaseController.initEntityBaseAttribute(pointRecord);
								pointRecord = vixntBaseService.merge(pointRecord);
							}
							customerAccountClip.setUpdateTime(new Date());
							customerAccount.setUpdateTime(new Date());
							initEntityBaseController.initEntityBaseAttribute(customerAccount);
							customerAccount = vixntBaseService.merge(customerAccount);
							checkMemberLevel(customerAccount);
							customerAccountClip.setPointValue(customerAccount.getPoint());
							initEntityBaseController.initEntityBaseAttribute(customerAccountClip);
							customerAccountClip = vixntBaseService.merge(customerAccountClip);
							rechargeRecord.setCode(VixUUID.createCode(12));
							rechargeRecord.setIsTemp("");
							rechargeRecord.setPayDate(new Date());
							rechargeRecord.setPayType(payType);
							if(channelDistributor != null){
								rechargeRecord.setChannelDistributor(channelDistributor);
							}
							rechargeRecord.setCustomerAccountClip(customerAccountClip);
							rechargeRecord.setStoredValueRuleSet(storedValueRuleSet);
							initEntityBaseController.initEntityBaseAttribute(rechargeRecord);
							rechargeRecord = vixntBaseService.merge(rechargeRecord);
							if(employee != null){
								channelDistributor = vixntBaseService.findEntityById(ChannelDistributor.class, employee.getChannelDistributor().getId());
								if(channelDistributor != null){
									ChannelDistributorMessageSet channelDistributorMessageSet = vixntBaseService.findEntityByAttribute(ChannelDistributorMessageSet.class,"channelDistributor.id", channelDistributor.getId());
									if(channelDistributorMessageSet != null && "0".equals(channelDistributorMessageSet.getIsEnable()) && "0".equals(channelDistributorMessageSet.getSalesInform())){
										Map<String, Object> params = getParams();
										List<SMSCostRules> sMSCostRulesList = vixntBaseService.findAllByConditions(SMSCostRules.class, params);
										if(null != sMSCostRulesList && sMSCostRulesList.size() >0){
											SMSCostRules smsCostRules = sMSCostRulesList.get(0);
											Double price = smsCostRules.getPrice();
											if(channelDistributorMessageSet.getAmount() > 0 && channelDistributorMessageSet.getAmount() > price){
												Map<String, String> orderStatusMap = new HashMap<String, String>();
												orderStatusMap.put(MessageTemplateConstant.username, customerAccount.getName());
												orderStatusMap.put(MessageTemplateConstant.cardNo, customerAccountClip.getName());
												String resp = sendMessage("2", customerAccount.getMobilePhone(), orderStatusMap);
												if(resp != null && resp.contains("|")){
													saveMessageSendRecord(2,"2","充值通知",customerAccount.getMobilePhone(),channelDistributor,employee);
													if(StringUtils.isNotEmpty(String.valueOf(channelDistributorMessageSet.getNum()))&& null !=channelDistributorMessageSet.getNum()){
														channelDistributorMessageSet.setNum(channelDistributorMessageSet.getNum()+1);
													}else{
														channelDistributorMessageSet.setNum(1d);
													}
													channelDistributorMessageSet.setAmount(channelDistributorMessageSet.getAmount() - price);
													initEntityBaseController.initEntityBaseAttribute(channelDistributorMessageSet);
													channelDistributorMessageSet = vixntBaseService.merge(channelDistributorMessageSet);
												}
												System.out.println(resp);
											}
										}
									}
								}
							}
							renderText("1:充值成功!");
						}
					}else{
						renderText("0:充值失败!");
					}
				}else{
					renderText("0:充值失败!");
				}
			}else{
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText("0:充值失败!");
		}
	}
	
	/**
	 * 跳转到支付页面
	 * @return
	 */
	public String goSaveOrUpdate(){
		try {
			if(StringUtils.isNotEmpty(id)){
				rechargeRecord = vixntBaseService.findEntityById(RechargeRecord.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	public void saveRechargeRecord(){
		try {
			if(rechargeRecord != null){
				if(StringUtils.isNotEmpty(storedValueRuleSetId)){
					storedValueRuleSet = vixntBaseService.findEntityById(StoredValueRuleSet.class, storedValueRuleSetId);
					if(null != storedValueRuleSet){
						rechargeRecord.setStoredValueRuleSet(storedValueRuleSet);
						rechargeRecord.setPayMoney(storedValueRuleSet.getAmount());
					}
				}
				rechargeRecord.setIsTemp("1");
				rechargeRecord.setStatus("0");
				rechargeRecord.setCreateTime(new Date());
				rechargeRecord.setCode(VixUUID.createCode(15));
				initEntityBaseController.initEntityBaseAttribute(rechargeRecord);
				rechargeRecord = vixntBaseService.merge(rechargeRecord);
				renderText("1:"+rechargeRecord.getId());
			}else{
				renderText("0:保存失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 选择充值规则
	 * @return
	 */
	public String goChooseReChargeRecord(){
		try {
			if(StringUtils.isNotEmpty(id)&&!"undefined".equals(id)){
				customerAccountClip = vixntBaseService.findEntityById(CustomerAccountClip.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goChooseReChargeRecord";
	}
	/**
	 * 选择会员卡
	 * @return
	 */
	public String goChooseCustomerAccountClip(){
		return "goChooseCustomerAccountClip";
	}
	public void getCustomerAccountClipList(){
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			params.put("isTemp,"+SearchCondition.NOEQUAL, "1");
			String phone = getDecodeRequestParameter("phone");
			if(StringUtils.isNotEmpty(phone)){
				params.put("customerAccount.mobilePhone,"+SearchCondition.ANYLIKE, phone);
			}
			String isReport = getDecodeRequestParameter("isReport");
			if(StringUtils.isNotEmpty(isReport)){
				params.put("isReport,"+SearchCondition.NOEQUAL, isReport);
			}
			String selectName = getDecodeRequestParameter("selectName");
			if(StringUtils.isNotEmpty(selectName)){
				params.put("name,"+SearchCondition.ANYLIKE, selectName);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, CustomerAccountClip.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void goCustomerAccountClipByClipId(){
		try {
			if(StringUtils.isNotEmpty(customerAccountClipId)){
				customerAccountClip = vixntBaseService.findEntityById(CustomerAccountClip.class, customerAccountClipId);
				if(null != customerAccountClip){
					customerAccount = vixntBaseService.findEntityById(CustomerAccount.class, customerAccountClip.getCustomerAccount().getId());
					if(null != customerAccount){
						renderText("1:"+customerAccount.getName()+":"+customerAccount.getMoney()+":"+customerAccount.getMobilePhone()+":"+customerAccountClip.getCardType());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getRechargeDetail(){
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			String selectName = getDecodeRequestParameter("selectName");
			if(StringUtils.isNotEmpty(selectName)){
				params.put("item.name,"+SearchCondition.ANYLIKE, selectName);
			}
			if(StringUtils.isNotEmpty(rechargeRecordId)){
				params.put("rechargeRecord.id,"+SearchCondition.EQUAL, rechargeRecordId);
				pager = vixntBaseService.findPagerByHqlConditions(pager, RechargeRecordDetail.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 删除充值记录明细
	 */
	public void deleteRechargeDetailById(){
		try {
			if(StringUtils.isNotEmpty(id)){
				rechargeRecordDetail = vixntBaseService.findEntityById(RechargeRecordDetail.class, id);
				if(null != rechargeRecordDetail){
					vixntBaseService.deleteByEntity(rechargeRecordDetail);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 跳转到添加充值明细页面
	 * @return
	 */
	public String goSaveOrUpdateRechargeDetail(){
		try {
			if(StringUtils.isNotEmpty(id)&&!"undefined".equals(id)){
				rechargeRecordDetail = vixntBaseService.findEntityById(RechargeRecordDetail.class, id);
			}else{
				rechargeRecordDetail = new RechargeRecordDetail();
				if(StringUtils.isNotEmpty(rechargeRecordId)){
					rechargeRecord = vixntBaseService.findEntityById(RechargeRecord.class, rechargeRecordId);
					if(null != rechargeRecord){
						rechargeRecordDetail.setRechargeRecord(rechargeRecord);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateRechargeDetail";
	}
	public void saveOrUpdateRechargeDetail(){
		try {
			rechargeRecordDetail.setCode(VixUUID.createCode(15));
			rechargeRecordDetail.setCreateTime(new Date());
			initEntityBaseController.initEntityBaseAttribute(rechargeRecordDetail);
			rechargeRecordDetail = vixntBaseService.merge(rechargeRecordDetail);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String goPrintOrder(){
		try {
			if(StringUtils.isNotEmpty(rechargeRecordId)){
				rechargeRecord = vixntBaseService.findEntityById(RechargeRecord.class, rechargeRecordId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goPrintOrder";
	}
	
	public String goSendMassage(){
		return "goSendMassage";
	}
	public void sendMessage(){
		try {
			String message = NumberUtil.getRandomNumber(6);
			Employee employee = getEmployee();
			if(employee != null){
				channelDistributor = vixntBaseService.findEntityById(ChannelDistributor.class, employee.getChannelDistributor().getId());
				if(channelDistributor != null){
					ChannelDistributorMessageSet channelDistributorMessageSet = vixntBaseService.findEntityByAttribute(ChannelDistributorMessageSet.class,"channelDistributor.id", channelDistributor.getId());
					if(channelDistributorMessageSet != null && "0".equals(channelDistributorMessageSet.getIsEnable())){
						Employee employee2 = vixntBaseService.findEntityById(Employee.class, channelDistributor.getEmployeeId());
						Map<String, Object> params = getParams();
						List<SMSCostRules> sMSCostRulesList = vixntBaseService.findAllByConditions(SMSCostRules.class, params);
						if(null != sMSCostRulesList && sMSCostRulesList.size() >0){
							SMSCostRules smsCostRules = sMSCostRulesList.get(0);
							Double price = smsCostRules.getPrice();
							if(channelDistributorMessageSet.getAmount() != null &&   channelDistributorMessageSet.getAmount() > 0 && channelDistributorMessageSet.getAmount() > price){
								Map<String, String> orderStatusMap = new HashMap<String, String>();
								orderStatusMap.put(MessageTemplateConstant.username, employee2.getName());
								orderStatusMap.put(MessageTemplateConstant.commentcontent, message);
								String resp = sendMessage("14", employee2.getTelephone(), orderStatusMap);
								if(resp != null && resp.contains("|")){
									saveMessageSendRecord(0,"14","授权验证码",employee2.getTelephone(),channelDistributor,employee);
									if(StringUtils.isNotEmpty(String.valueOf(channelDistributorMessageSet.getNum()))&& null !=channelDistributorMessageSet.getNum()){
										channelDistributorMessageSet.setNum(channelDistributorMessageSet.getNum()+1);
									}else{
										channelDistributorMessageSet.setNum(1d);
									}
									channelDistributorMessageSet.setAmount(channelDistributorMessageSet.getAmount() - price);
									initEntityBaseController.initEntityBaseAttribute(channelDistributorMessageSet);
									channelDistributorMessageSet = vixntBaseService.merge(channelDistributorMessageSet);
								}
								System.out.println(resp);
								renderText("1:"+message);
							}
						}
					}
					
				}else{
					renderText("0:短信发送失败!");
				}
			}else{
				renderText("0:短信发送失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText("0:短信发送失败!");
		}
	}
	public void checkMemberLevel(CustomerAccount customerAccount){
		try {
			Map<String, Object> params = getParams();
			List<MemberLevel> list = vixntBaseService.findAllByConditions(MemberLevel.class, params);
			if(null != list && list.size() >0){
				for (MemberLevel memberLevel2 : list) {
					if(memberLevel2.getFromPoints() <= customerAccount.getPoint() && memberLevel2.getToPoints() >= customerAccount.getPoint()){
						customerAccount.setMemberLevel(memberLevel2);
						initEntityBaseController.initEntityBaseAttribute(customerAccount);
						customerAccount = vixntBaseService.merge(customerAccount);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public String getId() {
		return id;
	}
	@Override
	public void setId(String id) {
		this.id = id;
	}
	public RechargeRecord getRechargeRecord() {
		return rechargeRecord;
	}
	public void setRechargeRecord(RechargeRecord rechargeRecord) {
		this.rechargeRecord = rechargeRecord;
	}
	public CustomerAccountClip getCustomerAccountClip() {
		return customerAccountClip;
	}
	public void setCustomerAccountClip(CustomerAccountClip customerAccountClip) {
		this.customerAccountClip = customerAccountClip;
	}
	public StoredValueRuleSet getStoredValueRuleSet() {
		return storedValueRuleSet;
	}
	public void setStoredValueRuleSet(StoredValueRuleSet storedValueRuleSet) {
		this.storedValueRuleSet = storedValueRuleSet;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getStoredValueRuleSetId() {
		return storedValueRuleSetId;
	}
	public void setStoredValueRuleSetId(String storedValueRuleSetId) {
		this.storedValueRuleSetId = storedValueRuleSetId;
	}
	public String getCustomerAccountClipId() {
		return customerAccountClipId;
	}
	public void setCustomerAccountClipId(String customerAccountClipId) {
		this.customerAccountClipId = customerAccountClipId;
	}
	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}
	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}
	public String getCustomerAccountId() {
		return customerAccountId;
	}
	public void setCustomerAccountId(String customerAccountId) {
		this.customerAccountId = customerAccountId;
	}
	public RechargeRecordDetail getRechargeRecordDetail() {
		return rechargeRecordDetail;
	}
	public void setRechargeRecordDetail(RechargeRecordDetail rechargeRecordDetail) {
		this.rechargeRecordDetail = rechargeRecordDetail;
	}
	public String getRechargeRecordId() {
		return rechargeRecordId;
	}
	public void setRechargeRecordId(String rechargeRecordId) {
		this.rechargeRecordId = rechargeRecordId;
	}
	public ChannelDistributor getChannelDistributor() {
		return channelDistributor;
	}
	public void setChannelDistributor(ChannelDistributor channelDistributor) {
		this.channelDistributor = channelDistributor;
	}
	public MemberLevel getMemberLevel() {
		return memberLevel;
	}
	public void setMemberLevel(MemberLevel memberLevel) {
		this.memberLevel = memberLevel;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
}
