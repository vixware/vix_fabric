package com.vix.nvix.coupon;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.SortSet;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.crm.business.entity.Coupon;
import com.vix.crm.business.entity.CouponDetail;
import com.vix.crm.business.entity.CrmMember;
import com.vix.crm.business.entity.PromotionRule;
import com.vix.crm.member.entity.MemberLevel;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.integralRulesSet.entity.IntegralActivity;
import com.vix.drp.integralRulesSet.entity.IntegralRules;
import com.vix.drp.pointRecord.entity.PointRecord;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.mdm.crm.entity.CustomerAccountClip;
import com.vix.mdm.crm.entity.CustomerAccountClipDetail;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.item.entity.ItemCatalog;
import com.vix.mdm.item.entity.ItemGift;
import com.vix.mdm.purchase.order.entity.PaymentRecord;
import com.vix.mdm.purchase.order.entity.PendingOrder;
import com.vix.mdm.purchase.order.entity.RequireGoodsOrder;
import com.vix.mdm.purchase.order.entity.RequireGoodsOrderItem;
import com.vix.nvix.common.base.action.VixntBaseAction;

@Controller
@Scope("prototype")
public class VixntPosAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;

	private String id;
	private String type;
	private String itemId;
	private String orderId;
	private String channelId;
	private String customerAccountId;
	private String couponId;
	private Integer pageNo;
	private String code;
	private String mobilePhone;
	private String count;
	private String payType;
	private String amount;
	private String payAmount;
	private String reduceAmount;
	private List<ItemCatalog> itemCatalogList;
	private List<ItemCatalog> secondItemCatalogList;
	private List<Item> itemList;
	private List<ItemGift> itemGiftList;
	private Long orderCount;
	private Double orderTotalFee;
	private RequireGoodsOrder requireGoodsOrder;
	private CustomerAccount customerAccount;
	private ChannelDistributor channelDistributor;
	private List<RequireGoodsOrder> requireGoodsOrderList;
	private List<RequireGoodsOrderItem> requireGoodsOrderItemList;
	private Map<String, List<RequireGoodsOrderItem>> requireGoodsOrderItemMap = new HashMap<String, List<RequireGoodsOrderItem>>();
	private List<CouponDetail> couponList;
	private CustomerAccountClip customerAccountClip;

	@SuppressWarnings("unchecked")
	@Override
	public String goList() {
		try {
			SortSet ss = new SortSet("codeRule");
			Map<String, Object> params = getParams();
			itemCatalogList = vixntBaseService.findAllSubEntity(ItemCatalog.class, "parentItemCatalog.id", null, params);
			Collections.sort(itemCatalogList, ss);
			if (itemCatalogList != null && itemCatalogList.size() > 0) {
				ItemCatalog itemCatalog = itemCatalogList.get(0);
				if (itemCatalog != null) {
					Map<String, Object> secondParams = new HashMap<String, Object>();
					secondParams.put("parentItemCatalog.id," + SearchCondition.EQUAL, itemCatalog.getId());
					secondItemCatalogList = vixntBaseService.findAllByConditions(ItemCatalog.class, secondParams);
					Collections.sort(secondItemCatalogList, ss);
					if (secondItemCatalogList != null && secondItemCatalogList.size() > 0) {
						ItemCatalog catalog = secondItemCatalogList.get(0);
						Map<String, Object> itemParams = new HashMap<String, Object>();
						itemParams.put("itemCatalog.id," + SearchCondition.EQUAL, catalog.getId());
						Pager pager = getPager();
						pager.setPageNo(1);
						pager.setPageSize(10);
						pager.setOrderField("code");
						pager.setOrderBy("asc");
						pager = vixntBaseService.findPagerByHqlConditions(pager, Item.class, itemParams);
						if (pager != null && pager.getResultList() != null && pager.getResultList().size() > 0) {
							itemList = pager.getResultList();
							getRequest().setAttribute("pager", pager);
						}
					}
				}
			}
			// 获取门店信息
			Employee employee = getEmployee();
			if (employee != null && employee.getChannelDistributor() != null) {
				channelDistributor = vixntBaseService.findEntityById(ChannelDistributor.class, employee.getChannelDistributor().getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/**
	 * 选择商品
	 */
	public void addItem() {
		try {
			if (StrUtils.isNotEmpty(itemId)) {
				Item item = vixntBaseService.findEntityById(Item.class, itemId);
				if (item != null) {
					RequireGoodsOrder requireGoodsOrder = null;
					RequireGoodsOrderItem requireGoodsOrderItem = null;
					if (StrUtils.isNotEmpty(orderId)) {
						requireGoodsOrder = vixntBaseService.findEntityById(RequireGoodsOrder.class, orderId);
						Map<String, Object> orderItemParams = new HashMap<String, Object>();
						orderItemParams.put("item.id," + SearchCondition.EQUAL, item.getId());
						orderItemParams.put("requireGoodsOrder.isTemp," + SearchCondition.EQUAL, "1");
						orderItemParams.put("requireGoodsOrder.id," + SearchCondition.EQUAL, requireGoodsOrder.getId());
						List<RequireGoodsOrderItem> requireGoodsOrderItems = vixntBaseService.findAllDataByConditions(RequireGoodsOrderItem.class, orderItemParams);
						if (requireGoodsOrderItems != null && requireGoodsOrderItems.size() > 0) {
							requireGoodsOrderItem = requireGoodsOrderItems.get(0);
						}
					}
					if (requireGoodsOrder == null) {
						requireGoodsOrder = new RequireGoodsOrder();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
						requireGoodsOrder.setCode(sdf.format(new Date()));
						requireGoodsOrder.setName("POS订单" + sdf.format(new Date()));
						requireGoodsOrder.setCreateTime(new Date());
						requireGoodsOrder.setUpdateTime(new Date());
						requireGoodsOrder.setIsTemp("1");
						requireGoodsOrder.setIsSettlement("0");
						requireGoodsOrder.setType("1");
						if (StrUtils.isNotEmpty(channelId)) {
							ChannelDistributor channelDistributor = vixntBaseService.findEntityById(ChannelDistributor.class, channelId);
							requireGoodsOrder.setChannelDistributor(channelDistributor);
						}
						requireGoodsOrder = vixntBaseService.merge(requireGoodsOrder);
					}
					if (requireGoodsOrderItem == null || StrUtils.isEmpty(orderId)) {
						requireGoodsOrderItem = new RequireGoodsOrderItem();
						requireGoodsOrderItem.setCreateTime(new Date());
						requireGoodsOrderItem.setUpdateTime(new Date());
						SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
						requireGoodsOrderItem.setCode(sdf.format(new Date()));
						requireGoodsOrderItem.setPrice(item.getPrice());
						requireGoodsOrderItem.setNum(1L);
						requireGoodsOrderItem.setTitle(item.getName());
						requireGoodsOrderItem.setRequireGoodsOrder(requireGoodsOrder);
						requireGoodsOrderItem.setItem(item);
						requireGoodsOrderItem.setItemCode(item.getCode());
						requireGoodsOrderItem.setSpecification(item.getSpecification());
						requireGoodsOrderItem.setUnit(item.getSaleUnit());
						requireGoodsOrderItem.setNetTotal(requireGoodsOrderItem.getPrice() * requireGoodsOrderItem.getNum());
					} else {
						requireGoodsOrderItem.setUpdateTime(new Date());
						requireGoodsOrderItem.setNum(requireGoodsOrderItem.getNum() + 1);
						requireGoodsOrderItem.setNetTotal(requireGoodsOrderItem.getPrice() * requireGoodsOrderItem.getNum());
					}
					requireGoodsOrderItem = vixntBaseService.merge(requireGoodsOrderItem);
					renderText("1:" + requireGoodsOrder.getId() + ":" + requireGoodsOrderItem.getItemCode());
				} else {
					renderText("0:商品不存在");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText("0:保存失败");
		}
	}

	public String goDropDownList() {
		return "goDropDownList";
	}

	/**
	 * 加载二级分类
	 */
	public String loadItemCatalog() {
		try {
			if (StrUtils.isNotEmpty(id)) {
				ItemCatalog itemCatalog = vixntBaseService.findEntityById(ItemCatalog.class, id);
				if (itemCatalog != null) {
					Map<String, Object> secondParams = new HashMap<String, Object>();
					secondParams.put("parentItemCatalog.id," + SearchCondition.EQUAL, itemCatalog.getId());
					secondItemCatalogList = vixntBaseService.findAllByConditions(ItemCatalog.class, secondParams);
					SortSet ss = new SortSet("codeRule");
					Collections.sort(secondItemCatalogList, ss);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "loadItemCatalog";
	}

	/**
	 * 加载商品
	 */
	@SuppressWarnings("unchecked")
	public String loadItem() {
		try {
			if (StrUtils.isNotEmpty(id)) {
				if ("first".equals(type)) {
					ItemCatalog itemCatalog = vixntBaseService.findEntityById(ItemCatalog.class, id);
					if (itemCatalog != null) {
						Map<String, Object> secondParams = new HashMap<String, Object>();
						secondParams.put("parentItemCatalog.id," + SearchCondition.EQUAL, itemCatalog.getId());
						secondItemCatalogList = vixntBaseService.findAllByConditions(ItemCatalog.class, secondParams);
						SortSet ss = new SortSet("codeRule");
						Collections.sort(secondItemCatalogList, ss);
						if (secondItemCatalogList != null && secondItemCatalogList.size() > 0) {
							ItemCatalog catalog = secondItemCatalogList.get(0);
							Map<String, Object> itemParams = new HashMap<String, Object>();
							itemParams.put("itemCatalog.id," + SearchCondition.EQUAL, catalog.getId());
							Pager pager = getPager();
							pager.setPageNo(1);
							pager.setPageSize(10);
							pager.setOrderField("code");
							pager.setOrderBy("asc");
							pager = vixntBaseService.findPagerByHqlConditions(pager, Item.class, itemParams);
							if (pager != null && pager.getResultList() != null && pager.getResultList().size() > 0) {
								itemList = pager.getResultList();
								getRequest().setAttribute("pager", pager);
							}
						}
					}
				} else if ("second".equals(type)) {
					ItemCatalog itemCatalog = vixntBaseService.findEntityById(ItemCatalog.class, id);
					if (itemCatalog != null) {
						Map<String, Object> itemParams = new HashMap<String, Object>();
						itemParams.put("itemCatalog.id," + SearchCondition.EQUAL, itemCatalog.getId());
						Pager pager = getPager();
						pager.setPageNo(1);
						pager.setPageSize(10);
						pager.setOrderField("code");
						pager.setOrderBy("asc");
						pager = vixntBaseService.findPagerByHqlConditions(pager, Item.class, itemParams);
						if (pager != null && pager.getResultList() != null && pager.getResultList().size() > 0) {
							itemList = pager.getResultList();
							getRequest().setAttribute("pager", pager);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "loadItem";
	}

	@SuppressWarnings("unchecked")
	public String loadItemByPage() {
		try {
			if (StrUtils.isNotEmpty(id)) {
				ItemCatalog itemCatalog = vixntBaseService.findEntityById(ItemCatalog.class, id);
				if (itemCatalog != null) {
					Map<String, Object> itemParams = new HashMap<String, Object>();
					itemParams.put("itemCatalog.id," + SearchCondition.EQUAL, itemCatalog.getId());
					Pager pager = getPager();
					pager.setPageNo(pageNo);
					pager.setPageSize(10);
					pager.setOrderField("code");
					pager.setOrderBy("asc");
					pager = vixntBaseService.findPagerByHqlConditions(pager, Item.class, itemParams);
					if (pager != null && pager.getResultList() != null && pager.getResultList().size() > 0) {
						itemList = pager.getResultList();
						getRequest().setAttribute("pager", pager);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "loadItem";
	}

	/**
	 * 更新商品数量
	 */
	public void updateItemCount() {
		try {
			if (StrUtils.isNotEmpty(itemId) && StrUtils.isNotEmpty(type)) {
				RequireGoodsOrderItem requireGoodsOrderItem = vixntBaseService.findEntityById(RequireGoodsOrderItem.class, itemId);
				if (requireGoodsOrderItem != null) {
					if ("reduce".equals(type) && requireGoodsOrderItem.getNum() < 2) {
						renderText("0:商品数量至少1个");
					} else {
						if ("reduce".equals(type)) {
							requireGoodsOrderItem.setNum(requireGoodsOrderItem.getNum() - 1);
						} else if ("add".equals(type)) {
							requireGoodsOrderItem.setNum(requireGoodsOrderItem.getNum() + 1);
						} else if ("count".equals(type)) {
							requireGoodsOrderItem.setNum(Long.parseLong(count));
						}
						requireGoodsOrderItem.setNetTotal(requireGoodsOrderItem.getPrice() * requireGoodsOrderItem.getNum());
						requireGoodsOrderItem = vixntBaseService.merge(requireGoodsOrderItem);
						renderText("1:" + requireGoodsOrderItem.getRequireGoodsOrder().getId());
					}
				} else {
					renderText("0:商品不存在");
				}
			} else {
				renderText("0:更新商品数量失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText("0:更新商品数量失败");
		}
	}

	/** 加载订单 */
	public String loadOrder() {
		try {
			if (StrUtils.isNotEmpty(orderId)) {
				requireGoodsOrder = vixntBaseService.findEntityById(RequireGoodsOrder.class, orderId);
				if (requireGoodsOrder != null) {
					Long amount = 0l;
					Double totalFee = 0d;
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("requireGoodsOrder.id," + SearchCondition.EQUAL, requireGoodsOrder.getId());
					requireGoodsOrderItemList = vixntBaseService.findAllDataByConditions(RequireGoodsOrderItem.class, params);
					if (requireGoodsOrderItemList != null && requireGoodsOrderItemList.size() > 0) {
						SortSet ss = new SortSet("createTime");
						Collections.sort(requireGoodsOrderItemList, ss);
						for (RequireGoodsOrderItem requireGoodsOrderItem : requireGoodsOrderItemList) {
							amount += requireGoodsOrderItem.getNum();
							totalFee += requireGoodsOrderItem.getNetTotal();
						}
						requireGoodsOrder.setNum(amount);
						DecimalFormat df = new DecimalFormat("#.00");
						requireGoodsOrder.setAmount(Double.parseDouble(df.format(totalFee)));
						requireGoodsOrder = vixntBaseService.merge(requireGoodsOrder);
					} /*
						 * else {
						 * vixntBaseService.deleteByEntity(requireGoodsOrder); }
						 */
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "loadOrder";
	}

	/**
	 * 加载订单数量和价格
	 */
	public String loadOrderCountAndPrice() {
		try {
			orderCount = 0l;
			orderTotalFee = 0d;
			if (StrUtils.isNotEmpty(orderId)) {
				requireGoodsOrder = vixntBaseService.findEntityById(RequireGoodsOrder.class, orderId);
				if (requireGoodsOrder != null) {
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("requireGoodsOrder.id," + SearchCondition.EQUAL, requireGoodsOrder.getId());
					requireGoodsOrderItemList = vixntBaseService.findAllDataByConditions(RequireGoodsOrderItem.class, params);
					if (requireGoodsOrderItemList != null && requireGoodsOrderItemList.size() > 0) {
						SortSet ss = new SortSet("createTime");
						Collections.sort(requireGoodsOrderItemList, ss);
						for (RequireGoodsOrderItem requireGoodsOrderItem : requireGoodsOrderItemList) {
							orderCount += requireGoodsOrderItem.getNum();
							orderTotalFee += requireGoodsOrderItem.getNetTotal();
						}
						DecimalFormat df = new DecimalFormat("#.00");
						orderTotalFee = Double.parseDouble(df.format(orderTotalFee));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "loadOrderCountAndPrice";
	}

	/** 删除商品 */
	public void deleteItem() {
		try {
			if (StrUtils.isNotEmpty(itemId) && StrUtils.isNotEmpty(orderId)) {
				requireGoodsOrder = vixntBaseService.findEntityById(RequireGoodsOrder.class, orderId);
				RequireGoodsOrderItem requireGoodsOrderItem = vixntBaseService.findEntityById(RequireGoodsOrderItem.class, itemId);
				if (requireGoodsOrderItem != null) {
					vixntBaseService.deleteByEntity(requireGoodsOrderItem);
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("requireGoodsOrder.id," + SearchCondition.EQUAL, requireGoodsOrder.getId());
					requireGoodsOrderItemList = vixntBaseService.findAllDataByConditions(RequireGoodsOrderItem.class, params);
					if (requireGoodsOrderItemList != null && requireGoodsOrderItemList.size() > 0) {
						renderText("1:" + requireGoodsOrder.getId());
					} else {
						vixntBaseService.deleteByEntity(requireGoodsOrder);
						renderText("1:" + "");
					}
				} else {
					renderText("0:商品不存在");
				}
			} else {
				renderText("0:" + DELETE_FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText("0:" + DELETE_FAIL);
		}
	}

	/** 检查是否已挂单 */
	public void isPendingOrder() {
		try {
			if (StrUtils.isNotEmpty(orderId)) {
				requireGoodsOrder = vixntBaseService.findEntityById(RequireGoodsOrder.class, orderId);
				if (requireGoodsOrder != null) {
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("requireGoodsOrder.id," + SearchCondition.EQUAL, requireGoodsOrder.getId());
					List<PendingOrder> pendingOrders = vixntBaseService.findAllDataByConditions(PendingOrder.class, params);
					if (pendingOrders != null && pendingOrders.size() > 0) {
						renderText("0:该订单已挂单");
					} else {
						renderText("1:" + requireGoodsOrder.getId());
					}
				} else {
					renderText("0:订单不存在");
				}
			} else {
				renderText("0:订单不存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText("0:订单不存在");
		}
	}

	/** 挂单记录 */
	public String pendingOrder() {
		try {
			if (StrUtils.isNotEmpty(orderId)) {
				requireGoodsOrder = vixntBaseService.findEntityById(RequireGoodsOrder.class, orderId);
				if (requireGoodsOrder != null) {
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("requireGoodsOrder.id," + SearchCondition.EQUAL, requireGoodsOrder.getId());
					List<PendingOrder> pendingOrders = vixntBaseService.findAllDataByConditions(PendingOrder.class, params);
					if (pendingOrders == null || pendingOrders.size() <= 0) {
						PendingOrder pendingOrder = new PendingOrder();
						SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmmss");
						pendingOrder.setCode(sdf.format(new Date()));
						pendingOrder.setName("挂单" + sdf.format(new Date()));
						pendingOrder.setCreateTime(new Date());
						pendingOrder.setUpdateTime(new Date());
						pendingOrder.setRequireGoodsOrder(requireGoodsOrder);
						pendingOrder = vixntBaseService.merge(pendingOrder);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "pendingOrder";
	}

	public void goPendingOrderList() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = new HashMap<String, Object>();
			if (StrUtils.isNotEmpty(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, PendingOrder.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 挂单 */
	public void savePendingOrder() {
		try {
			if (StrUtils.isNotEmpty(orderId)) {
				requireGoodsOrder = vixntBaseService.findEntityById(RequireGoodsOrder.class, orderId);
				if (requireGoodsOrder != null) {
					PendingOrder pendingOrder = new PendingOrder();
					SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmmss");
					pendingOrder.setCode(sdf.format(new Date()));
					pendingOrder.setName("挂单" + sdf.format(new Date()));
					pendingOrder.setCreateTime(new Date());
					pendingOrder.setUpdateTime(new Date());
					pendingOrder.setRequireGoodsOrder(requireGoodsOrder);
					pendingOrder = vixntBaseService.merge(pendingOrder);
					renderText("1:" + SAVE_SUCCESS);
				} else {
					renderText("0:" + SAVE_FAIL);
				}
			} else {
				renderText("0:" + SAVE_FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText("0:" + SAVE_FAIL);
		}
	}

	/** 取出挂单 */
	public void checkPendingOrder() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				PendingOrder pendingOrder = vixntBaseService.findEntityById(PendingOrder.class, id);
				requireGoodsOrder = pendingOrder.getRequireGoodsOrder();
				if (null != pendingOrder && requireGoodsOrder != null) {
					vixntBaseService.deleteByEntity(pendingOrder);
					renderText("1:" + requireGoodsOrder.getId());
				} else {
					renderText("0:取出挂单失败");
				}
			} else {
				renderText("0:取出挂单失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText("0:取出挂单失败");
		}
	}

	/** 删除挂单 */
	public void deletePendingOrderById() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				PendingOrder pendingOrder = vixntBaseService.findEntityByAttribute(PendingOrder.class, "id", id);
				requireGoodsOrder = pendingOrder.getRequireGoodsOrder();
				if (null != pendingOrder && requireGoodsOrder != null) {
					vixntBaseService.deleteByEntity(pendingOrder);
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("requireGoodsOrder.id," + SearchCondition.EQUAL, requireGoodsOrder.getId());
					requireGoodsOrderItemList = vixntBaseService.findAllDataByConditions(RequireGoodsOrderItem.class, params);
					for (RequireGoodsOrderItem requireGoodsOrderItem : requireGoodsOrderItemList) {
						vixntBaseService.deleteByEntity(requireGoodsOrderItem);
					}
					vixntBaseService.deleteByEntity(requireGoodsOrder);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	/** 选择会员 */
	public String selectCustomer() {
		return "selectCustomer";
	}

	/** 会员是否存在 */
	public void checkCustomer() {
		try {
			if (StrUtils.isNotEmpty(code) && StrUtils.isNotEmpty(channelId)) {
				Map<String, Object> params = new HashMap<String, Object>();
				StringBuilder hql = new StringBuilder("select heneity from CustomerAccount heneity where heneity.channelDistributor.id ='" + channelId + "' and (heneity.mobilePhone ='" + code + "' or heneity.clipNumber ='" + code + "') ");
				List<CustomerAccount> customerAccounts = vixntBaseService.findAllEntityByHql(hql, params);
				if (customerAccounts != null && customerAccounts.size() > 0) {
					customerAccount = customerAccounts.get(0);
					CustomerAccountClip customerAccountClip = vixntBaseService.findEntityByAttribute(CustomerAccountClip.class, "customerAccount.id", customerAccount.getId());
					if (customerAccountClip != null) {
						Long expiryDate = customerAccountClip.getExpiryDate().getTime();
						Long currentDate = System.currentTimeMillis();
						if (currentDate > expiryDate) {
							renderText("0:会员卡已到期");
						} else if ("N".equals(customerAccountClip.getIsUse())) {
							renderText("0:会员卡已禁用");
						} else if ("Y".equals(customerAccountClip.getIsReport())) {
							renderText("0:会员卡已挂失");
						} else {
							renderText("1:" + customerAccount.getId());
						}
					} else {
						renderText("0:会员卡不存在");
					}
				} else {
					renderText("0:会员不存在");
				}
			} else {
				renderText("0:会员不存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText("0:会员不存在");
		}
	}

	/** 加载会员 */
	public String loadCustomerAccount() {
		try {
			if (StrUtils.isNotEmpty(id)) {
				customerAccount = vixntBaseService.findEntityById(CustomerAccount.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "loadCustomerAccount";
	}

	/** 订单结算 */
	public String goSubmitOrder() {
		try {
			if (StrUtils.isNotEmpty(orderId) && StrUtils.isNotEmpty(customerAccountId)) {
				requireGoodsOrder = vixntBaseService.findEntityById(RequireGoodsOrder.class, orderId);
				customerAccount = vixntBaseService.findEntityById(CustomerAccount.class, customerAccountId);
				if (customerAccount != null && requireGoodsOrder != null) {
					// 如果服务项目减去次数
					Double payAmount = 0d;
					Map<String, Object> orderParam = new HashMap<String, Object>();
					orderParam.put("requireGoodsOrder.id," + SearchCondition.EQUAL, requireGoodsOrder.getId());
					requireGoodsOrderItemList = vixntBaseService.findAllDataByConditions(RequireGoodsOrderItem.class, orderParam);
					for (RequireGoodsOrderItem requireGoodsOrderItem : requireGoodsOrderItemList) {
						Item item = vixntBaseService.findEntityById(Item.class, requireGoodsOrderItem.getItem().getId());
						if ("Y".equals(item.getIsServiceProduct())) {
							Map<String, Object> params = new HashMap<String, Object>();
							params.put("customerAccount.id," + SearchCondition.EQUAL, customerAccount.getId());
							params.put("item.id," + SearchCondition.EQUAL, item.getId());
							List<CustomerAccountClipDetail> customerAccountClipDetails = vixntBaseService.findAllDataByConditions(CustomerAccountClipDetail.class, params);
							if (customerAccountClipDetails != null && customerAccountClipDetails.size() > 0) {
								CustomerAccountClipDetail customerAccountClipDetail = customerAccountClipDetails.get(0);
								if (customerAccountClipDetail != null && customerAccountClipDetail.getNumber() >= requireGoodsOrderItem.getNum()) {
									// 减去应付金额,修改明细价格
									payAmount += requireGoodsOrderItem.getNetTotal();
								}
							}
						}
					}
					getRequest().setAttribute("payAmount", payAmount);
					// 会员折扣
					Double discount = 1d;
					MemberLevel memberLevel = customerAccount.getMemberLevel();
					if (memberLevel != null) {
						memberLevel = vixntBaseService.findEntityById(MemberLevel.class, memberLevel.getId());
						discount = memberLevel.getDiscount() / 100;
					}
					getRequest().setAttribute("discount", discount);
					// 增加满减规则
					Double reduceAmount = 0d;
					Map<String, Object> promotionParams = new HashMap<String, Object>();
					promotionParams.put("promotionType," + SearchCondition.EQUAL, PromotionTypeConstant.PROMOTION_FULLREDUCE);
					promotionParams.put("status," + SearchCondition.EQUAL, "1");
					promotionParams.put("startTime," + SearchCondition.LESSTHANANDEQUAL, new Date());
					promotionParams.put("endTime," + SearchCondition.MORETHANANDEQUAL, new Date());
					List<PromotionRule> promotionRules = vixntBaseService.findAllDataByConditions(PromotionRule.class, promotionParams);
					if (promotionRules != null && promotionRules.size() > 0) {
						PromotionRule promotionRule = promotionRules.get(0);
						if (promotionRule != null) {
							if ((requireGoodsOrder.getAmount() - payAmount) * discount >= promotionRule.getOrderTotalPrice()) {
								reduceAmount = promotionRule.getReducePrice();
							}
						}
					}
					getRequest().setAttribute("reduceAmount", reduceAmount);
					// requireGoodsOrder.setAmount(requireGoodsOrder.getAmount()
					// - payAmount);
					// requireGoodsOrder.setPayAmount(requireGoodsOrder.getAmount()
					// - payAmount);
					requireGoodsOrder.setDiscount(discount);
					requireGoodsOrder = vixntBaseService.merge(requireGoodsOrder);
					// 查询用户可用优惠券
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("customerAccount.id," + SearchCondition.EQUAL, customerAccount.getId());
					params.put("effectiveDate," + SearchCondition.LESSTHANANDEQUAL, new Date());
					params.put("cutOffDate," + SearchCondition.MORETHANANDEQUAL, new Date());
					// params.put("status," + SearchCondition.EQUAL, "2");
					params.put("isUsed," + SearchCondition.EQUAL, "0");
					params.put("amount," + SearchCondition.LESSTHAN, (requireGoodsOrder.getAmount() - payAmount) * discount - reduceAmount);
					List<CouponDetail> couponDetails = vixntBaseService.findAllDataByConditions(CouponDetail.class, params);
					if (couponDetails != null && couponDetails.size() > 0) {
						couponList = new ArrayList<CouponDetail>();
						for (CouponDetail couponDetail : couponDetails) {
							if ("0".equals(couponDetail.getUserule())) {
								couponList.add(couponDetail);
							} else if ("1".equals(couponDetail.getUserule()) && (requireGoodsOrder.getAmount() - payAmount) * discount - reduceAmount >= couponDetail.getExpenditure()) {
								couponList.add(couponDetail);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSubmitOrder";
	}

	public void submitOrder() {
		try {
			if (StrUtils.isNotEmpty(orderId) && StrUtils.isNotEmpty(customerAccountId) && StrUtils.isNotEmpty(payType) && StrUtils.isNotEmpty(payAmount) && StrUtils.isNotEmpty(amount) && StrUtils.isNotEmpty(reduceAmount)) {
				requireGoodsOrder = vixntBaseService.findEntityById(RequireGoodsOrder.class, orderId);
				customerAccount = vixntBaseService.findEntityById(CustomerAccount.class, customerAccountId);
				if (requireGoodsOrder != null && customerAccount != null) {
					// 如果服务项目减去次数
					Map<String, Object> orderParam = new HashMap<String, Object>();
					orderParam.put("requireGoodsOrder.id," + SearchCondition.EQUAL, requireGoodsOrder.getId());
					requireGoodsOrderItemList = vixntBaseService.findAllDataByConditions(RequireGoodsOrderItem.class, orderParam);
					for (RequireGoodsOrderItem requireGoodsOrderItem : requireGoodsOrderItemList) {
						Item item = vixntBaseService.findEntityById(Item.class, requireGoodsOrderItem.getItem().getId());
						if ("Y".equals(item.getIsServiceProduct())) {
							Map<String, Object> params = new HashMap<String, Object>();
							params.put("customerAccount.id," + SearchCondition.EQUAL, customerAccount.getId());
							params.put("item.id," + SearchCondition.EQUAL, item.getId());
							List<CustomerAccountClipDetail> customerAccountClipDetails = vixntBaseService.findAllDataByConditions(CustomerAccountClipDetail.class, params);
							if (customerAccountClipDetails != null && customerAccountClipDetails.size() > 0) {
								CustomerAccountClipDetail customerAccountClipDetail = customerAccountClipDetails.get(0);
								if (customerAccountClipDetail != null && customerAccountClipDetail.getNumber() >= requireGoodsOrderItem.getNum()) {
									customerAccountClipDetail.setNumber(customerAccountClipDetail.getNumber() - requireGoodsOrderItem.getNum());
									customerAccountClipDetail = vixntBaseService.merge(customerAccountClipDetail);
									requireGoodsOrderItem.setPrice(0d);
									requireGoodsOrderItem.setNetTotal(requireGoodsOrderItem.getPrice() * requireGoodsOrderItem.getNum());
									requireGoodsOrderItem = vixntBaseService.merge(requireGoodsOrderItem);
								}
							}
						}
						// 赠品减去数量
						ItemGift itemGift = vixntBaseService.findEntityByAttribute(ItemGift.class, "item.id", item.getId());
						if (itemGift != null) {
							itemGift.setGiftCount((int) (itemGift.getGiftCount() - requireGoodsOrderItem.getNum()));
							itemGift = vixntBaseService.merge(itemGift);
						}
					}
					requireGoodsOrder.setAmount(Double.parseDouble(amount));
					requireGoodsOrder.setPayAmount(Double.parseDouble(payAmount));
					requireGoodsOrder.setReduceAmount(Double.parseDouble(reduceAmount));
					requireGoodsOrder.setIsTemp("");
					requireGoodsOrder.setIsSettlement("1");
					requireGoodsOrder.setPayType(payType);
					requireGoodsOrder.setCustomerAccount(customerAccount);
					requireGoodsOrder.setPayTime(new Date());
					// 保存优惠券
					if (StrUtils.isNotEmpty(couponId)) {
						CouponDetail couponDetail = vixntBaseService.findEntityById(CouponDetail.class, couponId);
						if (couponDetail != null) {
							couponDetail.setIsUsed("1");
							couponDetail = vixntBaseService.merge(couponDetail);
							Coupon coupon = vixntBaseService.findEntityById(Coupon.class, couponDetail.getCoupon().getId());
							if (coupon != null) {
								if (coupon.getUseQuantity() != null && coupon.getUseQuantity() > 0) {
									coupon.setUseQuantity(coupon.getUseQuantity() + 1);
								} else {
									coupon.setUseQuantity(1L);
								}
								coupon = vixntBaseService.merge(coupon);
							}
							requireGoodsOrder.setCouponDetail(couponDetail);
							requireGoodsOrder.setDiscountFee(couponDetail.getAmount().toString());
						}
					}
					requireGoodsOrder = vixntBaseService.merge(requireGoodsOrder);
					// 如果是余额支付,修改会员余额
					if ("2".equals(payType)) {
						// 增加最近消费时间
						customerAccount.setUpdateTime(new Date());
						customerAccount.setMoney(customerAccount.getMoney() - requireGoodsOrder.getPayAmount());
						customerAccount = vixntBaseService.merge(customerAccount);
						CustomerAccountClip customerAccountClip = vixntBaseService.findEntityByAttribute(CustomerAccountClip.class, "customerAccount.id", customerAccount.getId());
						if (customerAccountClip != null) {
							customerAccountClip.setMoney(customerAccount.getMoney());
							customerAccountClip = vixntBaseService.merge(customerAccountClip);
						}
					} else {
						// 保存积分记录
						Map<String, Object> params = new HashMap<String, Object>();
						List<IntegralRules> integralRuleList = vixntBaseService.findAllDataByConditions(IntegralRules.class, params);
						// 会员生日积分赠送比率
						Double memberBirthdayRatio = 1d;
						if (integralRuleList != null && integralRuleList.size() > 0) {
							IntegralRules integralRules = integralRuleList.get(0);
							memberBirthdayRatio = integralRules.getMemberBirthdayRatio();
						}
						params.put("xsStartDate," + SearchCondition.LESSTHANANDEQUAL, new Date());
						params.put("xsEndDate," + SearchCondition.MORETHANANDEQUAL, new Date());
						List<IntegralActivity> integralActivityList = vixntBaseService.findAllDataByConditions(IntegralActivity.class, params);
						Double point = 0d;
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						if (integralActivityList != null && integralActivityList.size() > 0) {
							IntegralActivity integralActivity = integralActivityList.get(0);
							point = requireGoodsOrder.getPayAmount() * integralActivity.getConversiorate();
							point = Math.floor(point);
							if (customerAccount.getBirthday() != null) {
								if (sdf.format(new Date()).equals(sdf.format(customerAccount.getBirthday()))) {
									point = point * memberBirthdayRatio;
								}
							}
						} else if (integralRuleList != null && integralRuleList.size() > 0) {
							IntegralRules integralRules = integralRuleList.get(0);
							point = requireGoodsOrder.getPayAmount() * integralRules.getConversiorate();
							point = Math.floor(point);
							if (customerAccount.getBirthday() != null) {
								if (sdf.format(new Date()).equals(sdf.format(customerAccount.getBirthday()))) {
									point = point * memberBirthdayRatio;
								}
							}
						}
						if (customerAccount.getPoint() != null && customerAccount.getPoint() > 0) {
							customerAccount.setPoint(customerAccount.getPoint() + point);
						} else {
							customerAccount.setPoint(point);
						}
						if (customerAccount.getAmountPoint() != null && customerAccount.getAmountPoint() > 0) {
							customerAccount.setAmountPoint(customerAccount.getAmountPoint() + point);
						} else {
							customerAccount.setAmountPoint(point);
						}
						CustomerAccountClip customerAccountClip = vixntBaseService.findEntityByAttribute(CustomerAccountClip.class, "customerAccount.id", customerAccount.getId());
						if (customerAccountClip != null) {
							customerAccountClip.setPointValue(customerAccount.getPoint());
							customerAccountClip = vixntBaseService.merge(customerAccountClip);
						}
						PointRecord pointRecord = new PointRecord();
						pointRecord.setCreateTime(new Date());
						pointRecord.setUpdateTime(new Date());
						pointRecord.setPointSource("消费赠送积分");
						pointRecord.setOperation("POS订单");
						pointRecord.setPointType("1");
						pointRecord.setPointValue(point);
						pointRecord.setCustomerAccount(customerAccount);
						pointRecord.setRequireGoodsOrder(requireGoodsOrder);
						pointRecord = vixntBaseService.merge(pointRecord);
						// 增加最近消费时间
						customerAccount.setUpdateTime(new Date());
						customerAccount = vixntBaseService.merge(customerAccount);
					}
					// 保存支付记录
					PaymentRecord paymentRecord = new PaymentRecord();
					paymentRecord.setCreateTime(new Date());
					paymentRecord.setUpdateTime(new Date());
					paymentRecord.setPaymentType(payType);
					paymentRecord.setAmount(requireGoodsOrder.getPayAmount());
					paymentRecord.setRequireGoodsOrder(requireGoodsOrder);
					paymentRecord.setCustomerAccount(customerAccount);
					paymentRecord = vixntBaseService.merge(paymentRecord);

					CrmMember crmMember = vixntBaseController.saveOrUpdateCrmMember(customerAccount, requireGoodsOrder.getChannelDistributor().getId());
					vixntBaseController.updateCrmMember(crmMember, requireGoodsOrder);
					renderText("1:订单结算成功");
				} else {
					renderText("0:订单结算失败");
				}
			} else {
				renderText("0:订单结算失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText("0:订单结算失败");
		}
	}

	/** 选择赠品列表 */
	public String selectGift() {
		try {
			if (StrUtils.isNotEmpty(orderId)) {
				requireGoodsOrder = vixntBaseService.findEntityById(RequireGoodsOrder.class, orderId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "selectGift";
	}

	public void goItemList() {
		try {
			Pager pager = getPager();
			// 增加满赠规则
			Map<String, Object> promotionParams = new HashMap<String, Object>();
			promotionParams.put("promotionType," + SearchCondition.EQUAL, PromotionTypeConstant.PROMOTION_FULLGIFT);
			promotionParams.put("status," + SearchCondition.EQUAL, "1");
			promotionParams.put("startTime," + SearchCondition.LESSTHANANDEQUAL, new Date());
			promotionParams.put("endTime," + SearchCondition.MORETHANANDEQUAL, new Date());
			List<PromotionRule> promotionRules = vixntBaseService.findAllDataByConditions(PromotionRule.class, promotionParams);
			if (promotionRules != null && promotionRules.size() > 0) {
				PromotionRule promotionRule = promotionRules.get(0);
				if (promotionRule != null) {
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("promotionRule.id," + SearchCondition.EQUAL, promotionRule.getId());
					params.put("giftCount," + SearchCondition.MORETHAN, 0);
					if (StrUtils.isNotEmpty(code)) {
						params.put("item.code," + SearchCondition.ANYLIKE, code);
					}
					String name = getDecodeRequestParameter("name");
					if (StrUtils.isNotEmpty(name)) {
						params.put("item.name," + SearchCondition.ANYLIKE, name);
					}
					pager = vixntBaseService.findPagerByHqlConditions(pager, ItemGift.class, params);
					renderDataTable(pager);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 保存赠品 */
	public void saveGift() {
		try {
			if (StrUtils.isNotEmpty(itemId) && StrUtils.isNotEmpty(orderId)) {
				Item item = vixntBaseService.findEntityById(Item.class, itemId);
				requireGoodsOrder = vixntBaseService.findEntityById(RequireGoodsOrder.class, orderId);
				if (item != null) {
					RequireGoodsOrderItem requireGoodsOrderItem = new RequireGoodsOrderItem();
					requireGoodsOrderItem.setCreateTime(new Date());
					requireGoodsOrderItem.setUpdateTime(new Date());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
					requireGoodsOrderItem.setCode(sdf.format(new Date()));
					requireGoodsOrderItem.setPrice(0D);
					requireGoodsOrderItem.setNum(1L);
					requireGoodsOrderItem.setTitle(item.getName());
					requireGoodsOrderItem.setRequireGoodsOrder(requireGoodsOrder);
					requireGoodsOrderItem.setItem(item);
					requireGoodsOrderItem.setItemCode(item.getCode());
					requireGoodsOrderItem.setSpecification(item.getSpecification());
					requireGoodsOrderItem.setUnit(item.getSaleUnit());
					requireGoodsOrderItem.setNetTotal(requireGoodsOrderItem.getPrice() * requireGoodsOrderItem.getNum());
					/*
					 * Employee employee = getEmployee(); if (employee != null)
					 * { requireGoodsOrderItem.setCompanyCode(employee.
					 * getCompanyCode());
					 * requireGoodsOrderItem.setCompanyInnerCode(employee.
					 * getCompanyInnerCode());
					 * requireGoodsOrderItem.setTenantId(employee.getTenantId())
					 * ; requireGoodsOrderItem.setDepartmentCode(employee.
					 * getDepartmentCode());
					 * requireGoodsOrderItem.setCreator(employee.getName()); }
					 */
					requireGoodsOrderItem = vixntBaseService.merge(requireGoodsOrderItem);
					renderText("1:" + requireGoodsOrder.getId() + ":" + requireGoodsOrderItem.getItemCode());
				} else {
					renderText("0:商品不存在");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText("0:保存赠品失败");
		}
	}

	// 打印小票
	public String goPrintOrder() {
		try {
			if (StrUtils.isNotEmpty(orderId)) {
				requireGoodsOrder = vixntBaseService.findEntityById(RequireGoodsOrder.class, orderId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goPrintOrder";
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getCustomerAccountId() {
		return customerAccountId;
	}

	public void setCustomerAccountId(String customerAccountId) {
		this.customerAccountId = customerAccountId;
	}

	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	@Override
	public Integer getPageNo() {
		return pageNo;
	}

	@Override
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}

	public String getReduceAmount() {
		return reduceAmount;
	}

	public void setReduceAmount(String reduceAmount) {
		this.reduceAmount = reduceAmount;
	}

	public List<ItemCatalog> getItemCatalogList() {
		return itemCatalogList;
	}

	public void setItemCatalogList(List<ItemCatalog> itemCatalogList) {
		this.itemCatalogList = itemCatalogList;
	}

	public List<ItemCatalog> getSecondItemCatalogList() {
		return secondItemCatalogList;
	}

	public void setSecondItemCatalogList(List<ItemCatalog> secondItemCatalogList) {
		this.secondItemCatalogList = secondItemCatalogList;
	}

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	public List<ItemGift> getItemGiftList() {
		return itemGiftList;
	}

	public void setItemGiftList(List<ItemGift> itemGiftList) {
		this.itemGiftList = itemGiftList;
	}

	public Long getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(Long orderCount) {
		this.orderCount = orderCount;
	}

	public Double getOrderTotalFee() {
		return orderTotalFee;
	}

	public void setOrderTotalFee(Double orderTotalFee) {
		this.orderTotalFee = orderTotalFee;
	}

	public RequireGoodsOrder getRequireGoodsOrder() {
		return requireGoodsOrder;
	}

	public void setRequireGoodsOrder(RequireGoodsOrder requireGoodsOrder) {
		this.requireGoodsOrder = requireGoodsOrder;
	}

	public List<RequireGoodsOrder> getRequireGoodsOrderList() {
		return requireGoodsOrderList;
	}

	public void setRequireGoodsOrderList(List<RequireGoodsOrder> requireGoodsOrderList) {
		this.requireGoodsOrderList = requireGoodsOrderList;
	}

	public List<RequireGoodsOrderItem> getRequireGoodsOrderItemList() {
		return requireGoodsOrderItemList;
	}

	public void setRequireGoodsOrderItemList(List<RequireGoodsOrderItem> requireGoodsOrderItemList) {
		this.requireGoodsOrderItemList = requireGoodsOrderItemList;
	}

	public Map<String, List<RequireGoodsOrderItem>> getRequireGoodsOrderItemMap() {
		return requireGoodsOrderItemMap;
	}

	public void setRequireGoodsOrderItemMap(Map<String, List<RequireGoodsOrderItem>> requireGoodsOrderItemMap) {
		this.requireGoodsOrderItemMap = requireGoodsOrderItemMap;
	}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	public List<CouponDetail> getCouponList() {
		return couponList;
	}

	public void setCouponList(List<CouponDetail> couponList) {
		this.couponList = couponList;
	}

	public ChannelDistributor getChannelDistributor() {
		return channelDistributor;
	}

	public void setChannelDistributor(ChannelDistributor channelDistributor) {
		this.channelDistributor = channelDistributor;
	}

	public CustomerAccountClip getCustomerAccountClip() {
		return customerAccountClip;
	}

	public void setCustomerAccountClip(CustomerAccountClip customerAccountClip) {
		this.customerAccountClip = customerAccountClip;
	}

}
