package com.vix.nvix.chain.action;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.id.VixUUID;
import com.vix.common.message.constant.MessageTemplateConstant;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.DateUtil;
import com.vix.core.web.Pager;
import com.vix.crm.member.entity.MemberLevel;
import com.vix.crm.member.entity.MemberTag;
import com.vix.drp.accountmanagement.service.IAccountManagementService;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.channel.entity.ChannelDistributorMessageSet;
import com.vix.drp.channel.entity.SMSCostRules;
import com.vix.drp.integralRulesSet.entity.IntegralRules;
import com.vix.drp.pointRecord.entity.PointRecord;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.CardEntity;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.mdm.crm.entity.CustomerAccountClip;
import com.vix.mdm.crm.entity.CustomerAccountClipDetail;
import com.vix.mdm.crm.entity.RechargeRecord;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.purchase.order.entity.RequireGoodsOrder;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.wx.util.MD5;
import com.vix.oa.currentexpense.entity.ExpenseRecord;
import com.vix.oa.personaloffice.service.IQueryDataService;

/**
 * 会员卡管理
 * @author jackie
 *
 */
@Controller
@Scope("prototype")
public class NvixCustomerAccountClipAction extends VixntBaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private IQueryDataService queryDataService;
	private String id;
	@Autowired
	private IAccountManagementService accountManagementService;
	private String customerAccountId;
	private String customerAccountClipId;
	private CustomerAccountClip customerAccountClip;
	private CustomerAccount customerAccount;
	private CustomerAccountClipDetail customerAccountClipDetail;
	private String customerAccountClipDetailId;
	private String customerAccountClipDetailItemId;
	private List<ExpenseRecord> expenseRecordList;
	private RechargeRecord rechargeRecord;
	private String payType;
	private String clipId;
	private String rechargeRecordId;
	private String payMoney;
	private MemberLevel memberLevel;
	private List<MemberTag> memberTagList;
	private List<MemberLevel> memberLevelList;
	private IntegralRules integralRules;
	private String clipName;
	private String dateTime;
	private ChannelDistributor channelDistributor;
	private DecimalFormat    df   = new DecimalFormat("######0.00");
	public void goSingleList(){
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			params.put("isTemp,"+SearchCondition.NOEQUAL, "1");
			String phone = getDecodeRequestParameter("phone");
			if(StringUtils.isNotEmpty(phone)){
				params.put("customerAccount.mobilePhone,"+SearchCondition.ANYLIKE,phone);
			}
			String number = getDecodeRequestParameter("number");
			if(StringUtils.isNotEmpty(number)){
				params.put("name,"+SearchCondition.ANYLIKE,number);
			}
			String selectName = getDecodeRequestParameter("selectName");
			if(StringUtils.isNotEmpty(selectName)){
				params.put("customerAccount.name,"+SearchCondition.ANYLIKE,selectName);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, CustomerAccountClip.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 跳转到修改或新增页面
	 * @return
	 */
	public String goSaveOrUpdate(){
		try {
			if(StringUtils.isNotEmpty(customerAccountId)){
				customerAccount = vixntBaseService.findEntityById(CustomerAccount.class, customerAccountId);
				if(customerAccount != null){
					customerAccountClip = vixntBaseService.findEntityByAttribute(CustomerAccountClip.class, "customerAccount.id", customerAccount.getId());
					if(customerAccountClip == null){
						customerAccountClip = new CustomerAccountClip();
						customerAccountClip.setCode(VixUUID.createCode(12));
						customerAccountClip.setCustomerAccount(customerAccount);
					}
				}
			}
			if(StringUtils.isNotEmpty(id)){
				customerAccountClip = vixntBaseService.findEntityById(CustomerAccountClip.class, id);
				if(customerAccountClip == null){
					customerAccountClip = new CustomerAccountClip();
					customerAccountClip.setCode(VixUUID.createCode(12));
					customerAccountClip.setCustomerAccount(customerAccount);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	/**
	 * 办理会员卡
	 */
	public void applayCard(){
		try {
			if(StringUtils.isNotEmpty(clipName)&&StringUtils.isNotEmpty(customerAccountClipId)){
				if(!checkClipNumber(clipName)){
					renderText("0:该会员卡号已存在!");
					return;
				}
				customerAccountClip = vixntBaseService.findEntityById(CustomerAccountClip.class,customerAccountClipId);
				if(customerAccountClip != null){
					if(customerAccountClip.getCustomerAccount() != null){
						customerAccount = vixntBaseService.findEntityById(CustomerAccount.class, customerAccountClip.getCustomerAccount().getId());
					}else{
						renderText("0:该会员已补卡");
						return;
					}
					if(customerAccountClip.getCard() != null && "1".equals(customerAccountClip.getCard().getType())){
						CustomerAccountClip accountClip = new CustomerAccountClip();
						accountClip.setCode(VixUUID.createCode(12));
						accountClip.setCreateTime(new Date());
						accountClip.setUpdateTime(new Date());
						accountClip.setPointValue(customerAccountClip.getPointValue());
						accountClip.setIsUse("Y");
						accountClip.setIsTemp("");
						accountClip.setIsReport("N");
						accountClip.setExpiryDate(customerAccountClip.getExpiryDate());
						accountClip.setName(clipName);
						accountClip.setMoney(customerAccountClip.getMoney());
						accountClip.setCard(customerAccountClip.getCard());
						accountClip.setCustomerAccount(customerAccount);
						initEntityBaseController.initEntityBaseAttribute(accountClip);
						accountClip = vixntBaseService.merge(accountClip);
						if(accountClip != null){
							Map<String, Object> params = getParams();
							params.put("isTemp,"+SearchCondition.NOEQUAL, "1");
							params.put("customerAccountClip.id,"+SearchCondition.EQUAL, customerAccountClip.getId());
							List<RechargeRecord> list = vixntBaseService.findAllByConditions(RechargeRecord.class, params);
							if(list != null && list.size() > 0 ){
								for (RechargeRecord rechargeRecord : list) {
									rechargeRecord.setCustomerAccountClip(accountClip);
									vixntBaseService.merge(rechargeRecord);
								}
							}
							customerAccountClip.setIsTemp("1");
							customerAccountClip.setCustomerAccount(null);
							customerAccountClip = vixntBaseService.merge(customerAccountClip);
							customerAccount.setIsReport("N");
							customerAccount.setClipNumber(clipName);
							customerAccount = vixntBaseService.merge(customerAccount);
							renderText("1:补卡成功!");
						}
					}else if(customerAccountClip.getCard() != null && "2".equals(customerAccountClip.getCard().getType())){
						CustomerAccountClip accountClip = new CustomerAccountClip();
						accountClip.setCode(VixUUID.createCode(12));
						accountClip.setCreateTime(new Date());
						accountClip.setUpdateTime(new Date());
						accountClip.setPointValue(customerAccountClip.getPointValue());
						accountClip.setIsUse("Y");
						accountClip.setIsTemp("");
						accountClip.setIsReport("N");
						accountClip.setExpiryDate(customerAccountClip.getExpiryDate());
						accountClip.setName(clipName);
						accountClip.setCard(customerAccountClip.getCard());
						if(null != customerAccountClip.getCustomerAccount()){
							accountClip.setCustomerAccount(customerAccountClip.getCustomerAccount());
						}
						initEntityBaseController.initEntityBaseAttribute(accountClip);
						accountClip = vixntBaseService.merge(accountClip);
						if(accountClip != null){
							Map<String, Object> params = getParams();
							params.put("customerAccountClip.id,"+SearchCondition.EQUAL, customerAccountClip.getId());
							List<CustomerAccountClipDetail> list = vixntBaseService.findAllByConditions(CustomerAccountClipDetail.class, params);
							if(null != list && list.size() > 0 ){
								for (CustomerAccountClipDetail customerAccountClipDetail : list) {
									customerAccountClipDetail.setCustomerAccountClip(accountClip);
									customerAccountClipDetail = vixntBaseService.merge(customerAccountClipDetail);
								}
							}
							Map<String, Object> params1 = getParams();
							params1.put("isTemp,"+SearchCondition.NOEQUAL, "1");
							params1.put("customerAccountClip.id,"+SearchCondition.EQUAL, customerAccountClip.getId());
							List<RechargeRecord> listRechargeRecord = vixntBaseService.findAllByConditions(RechargeRecord.class, params1);
							if(list != null && list.size() > 0 ){
								for (RechargeRecord rechargeRecord : listRechargeRecord) {
									rechargeRecord.setCustomerAccountClip(accountClip);
									vixntBaseService.merge(rechargeRecord);
								}
							}
							customerAccountClip.setIsTemp("1");
							customerAccountClip.setCustomerAccount(null);
							customerAccountClip = vixntBaseService.merge(customerAccountClip);
							customerAccount.setIsReport("N");
							customerAccount.setClipNumber(clipName);
							customerAccount = vixntBaseService.merge(customerAccount);
							renderText("1:补卡成功!");
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText("0:补卡失败!");
		}
	}
	
	/**
	 * 一天的消费金额
	 */
	private Double dayAmount = 0d;
	/**
	 * 累计消费金额
	 */
	private Double allAmount = 0d;
	/**
	 * 累计消费次数
	 */
	private Long rechargeAmount = 0l;
	/**
	 * 平均消费金额
	 */
	private Double averageAmount = 0d;
	/**
	 * 查看
	 */
	public String show(){
		try {
			if(StringUtils.isNotEmpty(id)){
				customerAccountClip = vixntBaseService.findEntityById(CustomerAccountClip.class, id);
				if(customerAccountClip != null){
					customerAccount = vixntBaseService.findEntityById(CustomerAccount.class, customerAccountClip.getCustomerAccount().getId());
				}
				if(customerAccount != null){
					Map<String, Object> params1 = getParams();
					if(null != customerAccount.getMemberTag()){
						params1.put("id,"+SearchCondition.IN, customerAccount.getMemberTag());
						memberTagList = vixntBaseService.findAllByConditions(MemberTag.class, params1);
					}
				}
				Map<String, Object> params = getParams();
				memberLevelList = vixntBaseService.findAllByConditions(MemberLevel.class, params);
				//customerAccountClipTypeList = vixntBaseService.findAllByConditions(CustomerAccountClipType.class, params);
				Map<String, Object> params1 = getParams();
				params1.put("customerAccount.id,"+SearchCondition.EQUAL, customerAccount.getId());
				params1.put("isTemp,"+SearchCondition.NOEQUAL, "1");
				params1.put("type,"+SearchCondition.EQUAL, "1");
				List<RequireGoodsOrder> requireGoodsOrderList = vixntBaseService.findAllByConditions(RequireGoodsOrder.class, params1);
				for (RequireGoodsOrder requireGoodsOrder : requireGoodsOrderList) {
					if(null != requireGoodsOrder && requireGoodsOrder.getAmount() >= 0){
						allAmount += requireGoodsOrder.getPayAmount();
						rechargeAmount ++;
					}
				}
				if(allAmount >0 && rechargeAmount >0){
					allAmount = Double.valueOf(df.format(allAmount));
					averageAmount = Double.valueOf(df.format(allAmount/rechargeAmount));
				}
				String nowDate = DateUtil.format(new Date(), "yyyy-MM-dd");
				Map<String, Object> params2 = getParams();
				params2.put("customerAccount.id,"+SearchCondition.EQUAL, customerAccount.getId());
				params2.put("isTemp,"+SearchCondition.NOEQUAL, "1");
				params2.put("createTime,"+SearchCondition.BETWEENAND, nowDate +" 00:00:01!"+ nowDate +" 23:59:59");
				params2.put("type,"+SearchCondition.EQUAL, "1");
				List<RequireGoodsOrder> requireGoodsOrders = vixntBaseService.findAllByConditions(RequireGoodsOrder.class, params2);
				for (RequireGoodsOrder requireGoodsOrder : requireGoodsOrders) {
					if(null != requireGoodsOrder && requireGoodsOrder.getAmount() >= 0){
						dayAmount += requireGoodsOrder.getPayAmount();
					}
				}
				dayAmount = Double.valueOf(df.format(dayAmount));
			}
			getCustomerAllExpense();
			getCustomerExpense();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "show";
	}
	/**
	 * 挂失
	 */
	public void report(){
		try {
			if(StringUtils.isNotEmpty(customerAccountId)){
				CustomerAccount customerAccount = vixntBaseService.findEntityById(CustomerAccount.class, customerAccountId);
				if(customerAccount != null){
					customerAccountClip = vixntBaseService.findEntityByAttribute(CustomerAccountClip.class, "customerAccount.id", customerAccount.getId());
					customerAccountClip.setIsReport("Y");
					initEntityBaseController.initEntityBaseAttribute(customerAccountClip);
					customerAccountClip = vixntBaseService.merge(customerAccountClip);
					customerAccount.setIsReport("Y");
					initEntityBaseController.initEntityBaseAttribute(customerAccount);
					customerAccount = vixntBaseService.merge(customerAccount);
				}
			}
			if(StringUtils.isNotEmpty(customerAccountClipId)){
				customerAccountClip = vixntBaseService.findEntityById(CustomerAccountClip.class, customerAccountClipId);
				customerAccountClip.setIsReport("Y");
				initEntityBaseController.initEntityBaseAttribute(customerAccountClip);
				customerAccountClip = vixntBaseService.merge(customerAccountClip);
				customerAccount = vixntBaseService.findEntityById(CustomerAccount.class, customerAccountClip.getCustomerAccount().getId());
				customerAccount.setIsReport("Y");
				initEntityBaseController.initEntityBaseAttribute(customerAccount);
				customerAccount = vixntBaseService.merge(customerAccount);
			}
			renderText("1:挂失成功!");
		} catch (Exception e) {
			e.printStackTrace();
			renderText("0:挂失失败!");
		}
	}
	public String swivel(){
		try {
			if(StringUtils.isNotEmpty(customerAccountId)){
				CustomerAccount customerAccount = vixntBaseService.findEntityById(CustomerAccount.class, customerAccountId);
				if(customerAccount != null){
					customerAccountClip = vixntBaseService.findEntityByAttribute(CustomerAccountClip.class, "customerAccount.id", customerAccount.getId());
				}
			}
			if(StringUtils.isNotEmpty(customerAccountClipId)){
				customerAccountClip = vixntBaseService.findEntityById(CustomerAccountClip.class, customerAccountClipId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "swivel";
	}
	public void swivelCard(){
		try {
			if(StringUtils.isNotEmpty(customerAccount.getId())){
				customerAccount = vixntBaseService.findEntityById(CustomerAccount.class, customerAccount.getId());
				if(StringUtils.isNotEmpty(customerAccountClip.getId())){
					customerAccountClip = vixntBaseService.findEntityById(CustomerAccountClip.class, customerAccountClip.getId());
					if(customerAccountClip != null){
						CustomerAccount customerAccount1 = vixntBaseService.findEntityById(CustomerAccount.class, customerAccountClip.getCustomerAccount().getId());
						customerAccountClip.setIsTemp("");
						customerAccountClip.setCustomerAccount(customerAccount);
						initEntityBaseController.initEntityBaseAttribute(customerAccountClip);
						customerAccountClip = vixntBaseService.merge(customerAccountClip);
						
						if(null != customerAccountClip.getCard()){
							customerAccount.setCustomerClipType(customerAccountClip.getCardType());
						}
						customerAccount.setClipNumber(customerAccountClip.getName());
						if(null != customerAccount1){
							customerAccount.setChannelDistributor(customerAccount1.getChannelDistributor());
						}
						initEntityBaseController.initEntityBaseAttribute(customerAccount);
						customerAccount = vixntBaseService.merge(customerAccount);
						customerAccount1.setClipNumber(null);
						customerAccount1.setCustomerClipType(null);
						customerAccount1.setStatus("1");
						initEntityBaseController.initEntityBaseAttribute(customerAccount1);
						customerAccount1 = vixntBaseService.merge(customerAccount1);
						renderText("转卡成功!");
					}
				}else{
					renderText("转卡失败!");
				}
			}else{
				renderText("转卡失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String goChooseCustomer(){
		return "goChooseCustomer";
	}
	
	public String goSaveOrUpdateClipDetail(){
		try {
			if(StringUtils.isNotEmpty(id)){
				customerAccountClip = vixntBaseService.findEntityById(CustomerAccountClip.class, id);
			}
			if(StringUtils.isNotEmpty(customerAccountClipDetailId)&&!"undefined".equals(customerAccountClipDetailId)){
				customerAccountClipDetail = vixntBaseService.findEntityById(CustomerAccountClipDetail.class, customerAccountClipDetailId);
			}else{
				customerAccountClipDetail = new CustomerAccountClipDetail();
				if(customerAccountClip != null){
					customerAccountClipDetail.setCustomerAccountClip(customerAccountClip);
					customerAccount = vixntBaseService.findEntityById(CustomerAccount.class, customerAccountClip.getCustomerAccount().getId());
					if(customerAccount != null){
						customerAccountClipDetail.setCustomerAccount(customerAccount);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateClipDetail";
	}
	public void saveOrUpdateClipDetail(){
		try {
			customerAccountClipDetail.setCode(VixUUID.createCode(12));
			customerAccountClipDetail.setCreateTime(new Date());
			initEntityBaseController.initEntityBaseAttribute(customerAccountClipDetail);
			customerAccountClipDetail = vixntBaseService.merge(customerAccountClipDetail);
//			if(null != customerAccountClipDetail){
//				renderText("1:"+customerAccountClipDetail.getId());
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void deleteClipDetail(){
		try {
			if(StringUtils.isNotEmpty(id)){
				vixntBaseService.deleteById(CustomerAccountClipDetail.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void getItemIsServiceItemList(){
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			params.put("isServiceItem,"+SearchCondition.EQUAL, "Y");
			params.put("isTemp,"+SearchCondition.EQUAL, "0");
			String selectName = getDecodeRequestParameter("selectName");
			if(StringUtils.isNotEmpty(selectName)){
				params.put("name,"+SearchCondition.ANYLIKE, selectName);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, Item.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 选择支付方式
	 * @return
	 */
	public String goCustomerCilpPay(){
		try {
			if(StringUtils.isNotEmpty(clipId)){
				customerAccountClip = vixntBaseService.findEntityById(CustomerAccountClip.class, clipId);
				if(customerAccountClip != null){
					rechargeRecord = new RechargeRecord();
					rechargeRecord.setCode(VixUUID.createCode(15));
					rechargeRecord.setCustomerAccountClip(customerAccountClip);
					rechargeRecord.setIsTemp("1");
					initEntityBaseController.initEntityBaseAttribute(rechargeRecord);
					rechargeRecord = vixntBaseService.merge(rechargeRecord);
				}else{
					renderText("0:会员卡不存在!");
				}
			}else{
				renderText("0:会员卡不存在!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goCustomerCilpPay";
	}
	/**
	 * 选择服务项目
	 * @return
	 */
	public String goChooseItem(){
		return "goChooseItem";
	}
	/**
	 * 办卡支付
	 */
	public void customerClipPay(){
		try {
			if(StringUtils.isNotEmpty(payType)&&StringUtils.isNotEmpty(clipId)&&StringUtils.isNotEmpty(rechargeRecordId)
					&&StringUtils.isNotEmpty(payMoney)){
				Employee employee = getEmployee();
				if(null != employee){
					channelDistributor = vixntBaseService.findEntityById(ChannelDistributor.class, employee.getChannelDistributor().getId());
				}
				customerAccountClip = vixntBaseService.findEntityById(CustomerAccountClip.class, clipId);
				rechargeRecord = vixntBaseService.findEntityById(RechargeRecord.class, rechargeRecordId);
				if(customerAccountClip != null && rechargeRecord != null){
					customerAccount = vixntBaseService.findEntityById(CustomerAccount.class,customerAccountClip.getCustomerAccount().getId());
					if(customerAccount != null){
						rechargeRecord.setCode(VixUUID.createCode(12));
						rechargeRecord.setPayType(payType);
						rechargeRecord.setPayDate(new Date());
						rechargeRecord.setPayMoney(Double.valueOf(payMoney));
						rechargeRecord.setIsTemp("");
						if(null != channelDistributor){
							rechargeRecord.setChannelDistributor(channelDistributor);
						}
						initEntityBaseController.initEntityBaseAttribute(rechargeRecord);
						rechargeRecord = vixntBaseService.merge(rechargeRecord);
						if(rechargeRecord != null){
							customerAccount.setStatus("2");
							initEntityBaseController.initEntityBaseAttribute(customerAccount);
							customerAccount.setUpdateTime(new Date());
							customerAccount.setAmountPoint(customerAccount.getPoint());
							customerAccount = vixntBaseService.merge(customerAccount);
							checkMemberLevel(customerAccount);
							if(customerAccount.getPoint() >0){
								PointRecord pointRecord = new PointRecord();
								pointRecord.setCreateTime(new Date());
								pointRecord.setUpdateTime(new Date());
								pointRecord.setPointSource("办卡赠送积分");
								pointRecord.setOperation("办卡");
								pointRecord.setPointType("1");
								pointRecord.setPointValue(customerAccount.getPoint());
								pointRecord.setCustomerAccount(customerAccount);
								initEntityBaseController.initEntityBaseAttribute(pointRecord);
								pointRecord = vixntBaseService.merge(pointRecord);
							}
							if("2".equals(customerAccount.getStatus())){
								accountManagementService.saveOrUpdateUserAccount(customerAccount.getMobilePhone(), MD5.MD5Encode("123456"), customerAccount.getId(), "1");
								if(null != channelDistributor){
									ChannelDistributorMessageSet channelDistributorMessageSet = vixntBaseService.findEntityByAttribute(ChannelDistributorMessageSet.class,"channelDistributor.id", channelDistributor.getId());
									if(channelDistributorMessageSet != null && "0".equals(channelDistributorMessageSet.getIsEnable()) && "0".equals(channelDistributorMessageSet.getSalesInform())){
										Map<String, Object> params = getParams();
										List<SMSCostRules> sMSCostRulesList = vixntBaseService.findAllByConditions(SMSCostRules.class, params);
										if(null != sMSCostRulesList && sMSCostRulesList.size() >0){
											SMSCostRules smsCostRules = sMSCostRulesList.get(0);
											Double price = smsCostRules.getPrice();
											if(channelDistributorMessageSet.getAmount() > 0 && channelDistributorMessageSet.getAmount() > price){
												Map<String, String> orderStatusMap = new HashMap<String, String>();
												orderStatusMap.put(MessageTemplateConstant.cardNo, customerAccount.getClipNumber());
												orderStatusMap.put(MessageTemplateConstant.username, customerAccount.getName());
												orderStatusMap.put(MessageTemplateConstant.password, "123456");
												String resp = sendMessage("1", customerAccount.getMobilePhone(), orderStatusMap);
												System.out.println(resp);
												if(resp != null && resp.contains("|")){
													if(StringUtils.isNotEmpty(String.valueOf(channelDistributorMessageSet.getNum()))&& null != channelDistributorMessageSet.getNum()){
														channelDistributorMessageSet.setNum(channelDistributorMessageSet.getNum()+1);
													}else{
														channelDistributorMessageSet.setNum(1d);
													}
													channelDistributorMessageSet.setAmount(channelDistributorMessageSet.getAmount() - price);
													initEntityBaseController.initEntityBaseAttribute(channelDistributorMessageSet);
													channelDistributorMessageSet = vixntBaseService.merge(channelDistributorMessageSet);
													saveMessageSendRecord(2,"1","开卡通知",customerAccount.getMobilePhone(),channelDistributor,employee);
												}
											}
										}
									}
								}
							}
							renderText("1:支付成功!");
						}else{
							renderText("0:办卡支付失败!");
						}
					}else{
						renderText("0:办卡支付失败!");
					}
				}else{
					renderText("0:办卡支付失败!");
				}
			}else{
				renderText("0:办卡支付失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText("0:办卡支付失败!");
		}
	}
	/**
	 * 查看会员卡的服务项目
	 * @return
	 */
	public String showItem(){
		try {
			if(StringUtils.isNotEmpty(clipId)){
				customerAccountClip = vixntBaseService.findEntityById(CustomerAccountClip.class,clipId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "showItem";
	}
	/**
	 * 选择会员卡类型
	 * @return
	 */
	public String goChooseCustomerClipType(){
		return "goChooseCustomerClipType";
	}
	
	public void getCustomerAccountClipTyoeList(){
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			String selectName = getDecodeRequestParameter("selectName");
			if(StringUtils.isNotEmpty(selectName)){
				params.put("name,"+SearchCondition.ANYLIKE, selectName);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, CardEntity.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void getCustomerAccountClipDetailById(){
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			if(StringUtils.isNotEmpty(customerAccountClipId)){
				params.put("customerAccountClip.id,"+SearchCondition.EQUAL, customerAccountClipId);
			}
			String selectName = getDecodeRequestParameter("selectName");
			if(StringUtils.isNotEmpty(selectName)){
				params.put("item.name,"+SearchCondition.ANYLIKE, selectName);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, CustomerAccountClipDetail.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void countPoint(){
		try {
			if(StringUtils.isNotEmpty(payMoney)){
				Map<String, Object> params = new HashMap<String, Object>();
				List<IntegralRules> integralRuleList = vixntBaseService.findAllDataByConditions(IntegralRules.class, params);
				if (integralRuleList != null && integralRuleList.size() > 0) {
					IntegralRules integralRules = integralRuleList.get(0);
					Double point = Double.valueOf(payMoney)* integralRules.getConversiorate();
					point = Math.floor(point);
					renderText("1:"+payMoney);
				}else{
					renderText("0:0");
				}
			}else{
				renderText("0:0");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 购买商品名字
	 */
	private String itemNames;
	
	/**
	 * 购买商品数量
	 */
	private String amounts;
	/**
	 * 获取会员的消费商品数量Top5
	 */
	public void getCustomerExpense(){
		try {
			if(StringUtils.isNotEmpty(id)){
				customerAccountClip = vixntBaseService.findEntityById(CustomerAccountClip.class, id);
				Map<String, Object> params = getParams();
				params.put("customerAccountId", customerAccountClip.getCustomerAccount().getId());
				List<RequireGoodsOrderVo> requireGoodsOrderVos = queryDataService.getCustomerExpense(params);
				if(null != requireGoodsOrderVos && requireGoodsOrderVos.size() >0){
					itemNames = "";
					amounts = "";
					for (RequireGoodsOrderVo requireGoodsOrderVo : requireGoodsOrderVos) {
						if(requireGoodsOrderVo != null){
							itemNames += "\"" + requireGoodsOrderVo.getItemName() + "\"" + ",";
							amounts += requireGoodsOrderVo.getAmount()+ ",";
						}
					}
					itemNames = itemNames.substring(0, itemNames.length()-1);
					amounts = amounts.substring(0, amounts.length()-1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 购买商品数量
	 */
	private String itemAmounts;
	/**
	 * 商品名称
	 */
	private String goodsNames;
	public void getCustomerAllExpense(){
		try {
			if(StringUtils.isNotEmpty(id)){
				customerAccountClip = vixntBaseService.findEntityById(CustomerAccountClip.class, id);
				Map<String, Object> params = getParams();
				params.put("customerAccountId", customerAccountClip.getCustomerAccount().getId());
				List<RequireGoodsOrderVo> requireGoodsOrderVos = queryDataService.getCustomerAllExpense(params);
				if(null != requireGoodsOrderVos && requireGoodsOrderVos.size() >0){
					itemAmounts = "";
					goodsNames = "";
					for (RequireGoodsOrderVo requireGoodsOrderVo : requireGoodsOrderVos) {
						itemAmounts += "{ value:"+ requireGoodsOrderVo.getAmount() + ",name:'"+requireGoodsOrderVo.getItemName()+"'},";
						goodsNames += "\"" + requireGoodsOrderVo.getItemName() + "\"" + ",";
					}
					itemAmounts = itemAmounts.substring(0, itemAmounts.length()-1);
					goodsNames = goodsNames.substring(0, goodsNames.length()-1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
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
	public String integralChange(){
		try {
			if(StringUtils.isNotEmpty(id)){
				customerAccountClip = vixntBaseService.findEntityById(CustomerAccountClip.class, id);
				if(customerAccountClip != null){
					customerAccount = vixntBaseService.findEntityById(CustomerAccount.class, customerAccountClip.getCustomerAccount().getId());
				}
			}
			Map<String, Object> params = getParams();
			List<IntegralRules> integralRuleList = vixntBaseService.findAllDataByConditions(IntegralRules.class, params);
			if(integralRuleList != null && integralRuleList.size() > 0){
				integralRules = integralRuleList.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "integralChange";
	}
	public boolean checkClipNumber(String number){
		boolean tag = true;
		try {
			if(StringUtils.isNotEmpty(number)){
				Map<String, Object> params = getParams();
				params.put("name,"+SearchCondition.EQUAL, number);
				List<CustomerAccountClip> list = vixntBaseService.findAllDataByConditions(CustomerAccountClip.class, params);
				if(list.size()>0){
					tag = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tag;
	}
	/**
	 * 跳转到续卡页面
	 * @return
	 */
	public String goSaveOrUpdateGoOnClip(){
		try {
			if(StringUtils.isNotEmpty(customerAccountId)){
				customerAccount = vixntBaseService.findEntityById(CustomerAccount.class, customerAccountId);
				if(customerAccount != null){
					customerAccountClip = vixntBaseService.findEntityByAttribute(CustomerAccountClip.class, "customerAccount.id", customerAccount.getId());
					if(customerAccountClip == null){
						customerAccountClip = new CustomerAccountClip();
						customerAccountClip.setCode(VixUUID.createCode(12));
						customerAccountClip.setCustomerAccount(customerAccount);
					}
				}
			}
			if(StringUtils.isNotEmpty(id)){
				customerAccountClip = vixntBaseService.findEntityById(CustomerAccountClip.class, id);
				if(customerAccountClip == null){
					customerAccountClip = new CustomerAccountClip();
					customerAccountClip.setCode(VixUUID.createCode(12));
					customerAccountClip.setCustomerAccount(customerAccount);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateGoOnClip";
	}
	/**
	 * 续卡
	 */
	public void goOnCard(){
		try {
			if(StringUtils.isNotEmpty(customerAccountClipId)&&StringUtils.isNotEmpty(dateTime)){
				customerAccountClip = vixntBaseService.findEntityById(CustomerAccountClip.class, customerAccountClipId);
				if(customerAccountClip != null){
					customerAccount = vixntBaseService.findEntityById(CustomerAccount.class, customerAccountClip.getCustomerAccount().getId());
					customerAccountClip.setExpiryDate(java.sql.Date.valueOf(dateTime));
					customerAccountClip = vixntBaseService.merge(customerAccountClip);
					if(customerAccount != null){
						customerAccount.setExpiryDate(java.sql.Date.valueOf(dateTime));
						customerAccount.setUpdateTime(new Date());
						customerAccount = vixntBaseService.merge(customerAccount);
						renderText("1:续卡成功!");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText("0:续卡失败!");
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
	public String getCustomerAccountId() {
		return customerAccountId;
	}
	public void setCustomerAccountId(String customerAccountId) {
		this.customerAccountId = customerAccountId;
	}
	public CustomerAccountClip getCustomerAccountClip() {
		return customerAccountClip;
	}
	public void setCustomerAccountClip(CustomerAccountClip customerAccountClip) {
		this.customerAccountClip = customerAccountClip;
	}
	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}
	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}
	public List<ExpenseRecord> getExpenseRecordList() {
		return expenseRecordList;
	}
	public void setExpenseRecordList(List<ExpenseRecord> expenseRecordList) {
		this.expenseRecordList = expenseRecordList;
	}
	public CustomerAccountClipDetail getCustomerAccountClipDetail() {
		return customerAccountClipDetail;
	}
	public void setCustomerAccountClipDetail(CustomerAccountClipDetail customerAccountClipDetail) {
		this.customerAccountClipDetail = customerAccountClipDetail;
	}
	public String getCustomerAccountClipDetailId() {
		return customerAccountClipDetailId;
	}
	public void setCustomerAccountClipDetailId(String customerAccountClipDetailId) {
		this.customerAccountClipDetailId = customerAccountClipDetailId;
	}
	public String getCustomerAccountClipId() {
		return customerAccountClipId;
	}
	public void setCustomerAccountClipId(String customerAccountClipId) {
		this.customerAccountClipId = customerAccountClipId;
	}
	public String getCustomerAccountClipDetailItemId() {
		return customerAccountClipDetailItemId;
	}
	public void setCustomerAccountClipDetailItemId(String customerAccountClipDetailItemId) {
		this.customerAccountClipDetailItemId = customerAccountClipDetailItemId;
	}
	public RechargeRecord getRechargeRecord() {
		return rechargeRecord;
	}
	public void setRechargeRecord(RechargeRecord rechargeRecord) {
		this.rechargeRecord = rechargeRecord;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getClipId() {
		return clipId;
	}
	public void setClipId(String clipId) {
		this.clipId = clipId;
	}
	public String getRechargeRecordId() {
		return rechargeRecordId;
	}
	public void setRechargeRecordId(String rechargeRecordId) {
		this.rechargeRecordId = rechargeRecordId;
	}
	
	public String getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(String payMoney) {
		this.payMoney = payMoney;
	}
	public List<MemberTag> getMemberTagList() {
		return memberTagList;
	}
	public void setMemberTagList(List<MemberTag> memberTagList) {
		this.memberTagList = memberTagList;
	}
	public List<MemberLevel> getMemberLevelList() {
		return memberLevelList;
	}
	public void setMemberLevelList(List<MemberLevel> memberLevelList) {
		this.memberLevelList = memberLevelList;
	}
	public Double getDayAmount() {
		return dayAmount;
	}
	public void setDayAmount(Double dayAmount) {
		this.dayAmount = dayAmount;
	}
	public Double getAllAmount() {
		return allAmount;
	}
	public void setAllAmount(Double allAmount) {
		this.allAmount = allAmount;
	}
	public Long getRechargeAmount() {
		return rechargeAmount;
	}
	public void setRechargeAmount(Long rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}
	public Double getAverageAmount() {
		return averageAmount;
	}
	public void setAverageAmount(Double averageAmount) {
		this.averageAmount = averageAmount;
	}
	public String getItemNames() {
		return itemNames;
	}
	public void setItemNames(String itemNames) {
		this.itemNames = itemNames;
	}
	public String getAmounts() {
		return amounts;
	}
	public void setAmounts(String amounts) {
		this.amounts = amounts;
	}
	public String getItemAmounts() {
		return itemAmounts;
	}
	public void setItemAmounts(String itemAmounts) {
		this.itemAmounts = itemAmounts;
	}
	public String getGoodsNames() {
		return goodsNames;
	}
	public void setGoodsNames(String goodsNames) {
		this.goodsNames = goodsNames;
	}
	public MemberLevel getMemberLevel() {
		return memberLevel;
	}
	public void setMemberLevel(MemberLevel memberLevel) {
		this.memberLevel = memberLevel;
	}
	public IntegralRules getIntegralRules() {
		return integralRules;
	}
	public void setIntegralRules(IntegralRules integralRules) {
		this.integralRules = integralRules;
	}
	public String getClipName() {
		return clipName;
	}
	public void setClipName(String clipName) {
		this.clipName = clipName;
	}
	public ChannelDistributor getChannelDistributor() {
		return channelDistributor;
	}
	public void setChannelDistributor(ChannelDistributor channelDistributor) {
		this.channelDistributor = channelDistributor;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
}
