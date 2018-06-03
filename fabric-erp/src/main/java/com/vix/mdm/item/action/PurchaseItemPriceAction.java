package com.vix.mdm.item.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.SortSet;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.item.action.transport.PriceFixEntity;
import com.vix.mdm.item.action.transport.PriceFixGift;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.item.entity.PriceCondition;
import com.vix.mdm.item.entity.PriceConditionCountArea;
import com.vix.mdm.item.entity.PriceConditionGift;
import com.vix.mdm.item.entity.PriceConditionPriceArea;
import com.vix.mdm.item.service.IItemService;
import com.vix.mdm.item.service.IPriceConditionService;
import com.vix.mdm.srm.share.entity.Supplier;
import com.vix.nvix.common.base.action.VixntBaseAction;

/**
 * 物料定价action，提供物料定价页面内物料的分页与检索
 * 
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class PurchaseItemPriceAction extends VixntBaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private IItemService itemService;

	@Autowired
	private IPriceConditionService priceConditionService;

	private String id;
	private String categoryId;
	private String name;
	private Item item;
	private Double count;
	private String billCreateDate;
	private String regionalId;
	private String supplierId;

	/** 获取列表数据 */
	public String goListContent() {
		try {
			Map<String, Object> params = getParams();
			if (null != name && !"".equals(name)) {
				name = decode(name, "UTF-8");
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			params.put("itemClass," + SearchCondition.IN, "goods,fixedassets,material,purchasepart,purchasebackuppart,machiningpart,semifinished,assemblypart,equipment,officesupply,afterservice,maintenanceservice,outsideservice");
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("id");
				getPager().setOrderBy("desc");
			}
			getPager().setPageSize(8);
			if (null != categoryId && !"".equals(categoryId)) {
				Pager pager = itemService.findPagerByItemCatalogId(getPager(), categoryId, params);
				setPager(pager);
			} else {
				itemService.findPagerByHqlConditions(getPager(), Item.class, params);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String type = getRequestParameter("type");
		if (null != type && "choose".equals(type)) {
			return "goListContentChoose";
		}
		return "goListContent";
	}

	public String goFixedPrice() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				item = itemService.findEntityById(Item.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "fixedPrice";
	}

	/** 定价 */
	private List<PriceFixEntity> priceFixEntityList = new ArrayList<PriceFixEntity>();

	/** 必传参数：物料id:id，物料数量:count,单据创建时间：billCreateDate */
	/** 选传参数：地域id：regionalId,供应商id：supplierId */
	public String fixedPrice() {
		try {
			item = priceConditionService.findEntityById(Item.class, id);
			Employee baseEmp = getEmployee();
			if (null != baseEmp && null != item && !item.getId().equals("") && !item.getId().equals("0") && count > 0 && null != billCreateDate) {
				Employee emp = priceConditionService.findEntityById(Employee.class, baseEmp.getId());
				if (null != emp) {
					if (null != emp.getOrganizationUnit() && null != emp.getOrganizationUnit().getId() && !emp.getOrganizationUnit().getId().equals("") && !emp.getOrganizationUnit().equals("0")) {
						Map<String, Object> params = new HashMap<String, Object>();
						params.put("saleOrg.id," + SearchCondition.EQUAL, emp.getOrganizationUnit().getId());
						params.put("isTemp," + SearchCondition.NOEQUAL, "1");
						params.put("isDeleted," + SearchCondition.NOEQUAL, "1");
						params.put("priceConditionType," + SearchCondition.EQUAL, "purchase");
						List<PriceCondition> pcList = priceConditionService.findAllByConditions(PriceCondition.class, params);
						for (PriceCondition pc : pcList) {
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
							if (null != pc.getStartEffectiveTime() && sdf.parse(billCreateDate).after(pc.getStartEffectiveTime()) && null != pc.getEndEffectiveTime() && sdf.parse(billCreateDate).before(pc.getEndEffectiveTime())) {
								if (null != pc.getPriceConditionCountAreas() && pc.getPriceConditionCountAreas().size() > 0) {
									for (PriceConditionCountArea pcca : pc.getPriceConditionCountAreas()) {
										if (null != pcca.getConditionType() && !"".equals(pcca.getConditionType())) {
											if ("itemCategory".equals(pcca.getConditionType())) {
												if (null != item.getItemCatalogs() && item.getItemCatalogs().size() > 0) {
													String[] icids = item.getItemCatalogIds().split(",");
													boolean has = false;
													for (String icid : icids) {
														if (null != icid && !"".equals(icid) && icid.equals(pcca.getItemCatalog().getId().toString())) {
															has = true;
															break;
														}
													}
													if (has) {
														if (null != pcca.getRegional() && null != pcca.getRegional().getId() && !pcca.getRegional().getId().equals("") && !pcca.getRegional().getId().equals("0")) {
															if (regionalId == pcca.getRegional().getId()) {
																if (count >= pcca.getStartCount() && count <= pcca.getEndCount()) {
																	generatePriceResult(pcca, "itemCategory");
																}
															}
														} else {
															if (count >= pcca.getStartCount() && count <= pcca.getEndCount()) {
																generatePriceResult(pcca, "itemCategory");
															}
														}
													}
												}
											} else if ("itemGroup".equals(pcca.getConditionType())) {
												if (null != item.getItemGroup() && !item.getItemGroup().getId().equals("") && !item.getItemGroup().getId().equals("0")) {
													if (item.getItemGroup().getId() == pcca.getItemGroup().getId()) {
														if (null != pcca.getRegional() && null != pcca.getRegional().getId() && !pcca.getRegional().getId().equals("") && !pcca.getRegional().getId().equals("0")) {
															if (regionalId == pcca.getRegional().getId()) {
																if (count >= pcca.getStartCount() && count <= pcca.getEndCount()) {
																	generatePriceResult(pcca, "itemGroup");
																}
															}
														} else {
															if (count >= pcca.getStartCount() && count <= pcca.getEndCount()) {
																generatePriceResult(pcca, "itemGroup");
															}
														}
													}
												}
											} else if ("item".equals(pcca.getConditionType())) {
												if (item.getId() == pcca.getItem().getId()) {
													if (null != pcca.getRegional() && null != pcca.getRegional().getId() && !pcca.getRegional().getId().equals("") && !pcca.getRegional().getId().equals("0")) {
														if (regionalId == pcca.getRegional().getId()) {
															if (count >= pcca.getStartCount() && count <= pcca.getEndCount()) {
																generatePriceResult(pcca, "item");
															}
														}
													} else {
														if (count >= pcca.getStartCount() && count <= pcca.getEndCount()) {
															generatePriceResult(pcca, "item");
														}
													}
												}
											} else if ("purchaseFrameworkAgreement".equals(pcca.getConditionType())) {
												if (StringUtils.isNotEmpty(supplierId) && !supplierId.equals("0")) {
													Supplier s = itemService.findEntityById(Supplier.class, supplierId);
													if (null != s && s.getId() == pcca.getSupplier().getId()) {
														if (null != pcca.getRegional() && null != pcca.getRegional().getId() && !pcca.getRegional().getId().equals("") && !pcca.getRegional().getId().equals("0")) {
															if (regionalId == pcca.getRegional().getId()) {
																if (count >= pcca.getStartCount() && count <= pcca.getEndCount()) {
																	generatePriceResult(pcca, "purchaseFrameworkAgreement");
																}
															}
														} else {
															if (count >= pcca.getStartCount() && count <= pcca.getEndCount()) {
																generatePriceResult(pcca, "purchaseFrameworkAgreement");
															}
														}
													}
												}
											} else if ("supplier".equals(pcca.getConditionType())) {
												if (StringUtils.isNotEmpty(supplierId) && !supplierId.equals("0")) {
													Supplier s = itemService.findEntityById(Supplier.class, supplierId);
													if (null != s && s.getId() == pcca.getSupplier().getId()) {
														if (null != pcca.getRegional() && null != pcca.getRegional().getId() && !pcca.getRegional().getId().equals("") && !pcca.getRegional().getId().equals("0")) {
															if (regionalId == pcca.getRegional().getId()) {
																if (count >= pcca.getStartCount() && count <= pcca.getEndCount()) {
																	generatePriceResult(pcca, "supplier");
																}
															}
														} else {
															if (count >= pcca.getStartCount() && count <= pcca.getEndCount()) {
																generatePriceResult(pcca, "supplier");
															}
														}
													}
												}
											}
										}
									}
								}
								if (null != pc.getPriceConditionPriceAreas() && pc.getPriceConditionPriceAreas().size() > 0) {
									for (PriceConditionPriceArea pcpa : pc.getPriceConditionPriceAreas()) {
										if (null != pcpa.getConditionType() && !"".equals(pcpa.getConditionType())) {
											if ("itemCategory".equals(pcpa.getConditionType())) {
												if (null != item.getItemCatalogs() && item.getItemCatalogs().size() > 0) {
													String[] icids = item.getItemCatalogIds().split(",");
													boolean has = false;
													for (String icid : icids) {
														if (null != icid && !"".equals(icid) && icid.equals(pcpa.getItemCatalog().getId().toString())) {
															has = true;
															break;
														}
													}
													if (has) {
														if (null != pcpa.getRegional() && null != pcpa.getRegional().getId() && !pcpa.getRegional().getId().equals("") && !pcpa.getRegional().getId().equals("0")) {
															if (regionalId == pcpa.getRegional().getId()) {
																if (count * item.getPrice() >= pcpa.getStartTotalAmount() && count * item.getPrice() <= pcpa.getEndTotalAmount()) {
																	generatePriceResult(pcpa, "itemCategory");
																}
															}
														} else {
															if (count * item.getPrice() >= pcpa.getStartTotalAmount() && count * item.getPrice() <= pcpa.getEndTotalAmount()) {
																generatePriceResult(pcpa, "itemCategory");
															}
														}
													}
												}
											} else if ("itemGroup".equals(pcpa.getConditionType())) {
												if (null != item.getItemGroup() && !item.getItemGroup().getId().equals("") && !item.getItemGroup().getId().equals("0")) {
													if (item.getItemGroup().getId() == pcpa.getItemGroup().getId()) {
														if (null != pcpa.getRegional() && null != pcpa.getRegional().getId() && !pcpa.getRegional().getId().equals("") && !pcpa.getRegional().getId().equals("0")) {
															if (regionalId == pcpa.getRegional().getId()) {
																if (count * item.getPrice() >= pcpa.getStartTotalAmount() && count * item.getPrice() <= pcpa.getEndTotalAmount()) {
																	generatePriceResult(pcpa, "itemGroup");
																}
															}
														} else {
															if (count * item.getPrice() >= pcpa.getStartTotalAmount() && count * item.getPrice() <= pcpa.getEndTotalAmount()) {
																generatePriceResult(pcpa, "itemGroup");
															}
														}
													}
												}
											} else if ("item".equals(pcpa.getConditionType())) {
												if (item.getId() == pcpa.getItem().getId()) {
													if (null != pcpa.getRegional() && null != pcpa.getRegional().getId() && !pcpa.getRegional().getId().equals("") && !pcpa.getRegional().getId().equals("0")) {
														if (regionalId == pcpa.getRegional().getId()) {
															if (count * item.getPrice() >= pcpa.getStartTotalAmount() && count * item.getPrice() <= pcpa.getEndTotalAmount()) {
																generatePriceResult(pcpa, "item");
															}
														}
													} else {
														if (count * item.getPrice() >= pcpa.getStartTotalAmount() && count * item.getPrice() <= pcpa.getEndTotalAmount()) {
															generatePriceResult(pcpa, "item");
														}
													}
												}
											} else if ("purchaseFrameworkAgreement".equals(pcpa.getConditionType())) {
												if (StringUtils.isNotEmpty(supplierId) && !supplierId.equals("0")) {
													Supplier s = itemService.findEntityById(Supplier.class, supplierId);
													if (null != s && s.getId() == pcpa.getSupplier().getId()) {
														if (null != pcpa.getRegional() && null != pcpa.getRegional().getId() && !pcpa.getRegional().getId().equals("") && !pcpa.getRegional().getId().equals("0")) {
															if (regionalId == pcpa.getRegional().getId()) {
																if (count * item.getPrice() >= pcpa.getStartTotalAmount() && count * item.getPrice() <= pcpa.getEndTotalAmount()) {
																	generatePriceResult(pcpa, "purchaseFrameworkAgreement");
																}
															}
														} else {
															if (count * item.getPrice() >= pcpa.getStartTotalAmount() && count * item.getPrice() <= pcpa.getEndTotalAmount()) {
																generatePriceResult(pcpa, "purchaseFrameworkAgreement");
															}
														}
													}
												}
											} else if ("supplier".equals(pcpa.getConditionType())) {
												if (StringUtils.isNotEmpty(supplierId) && !supplierId.equals("0")) {
													Supplier s = itemService.findEntityById(Supplier.class, supplierId);
													if (null != s && s.getId() == pcpa.getSupplier().getId()) {
														if (null != pcpa.getRegional() && null != pcpa.getRegional().getId() && !pcpa.getRegional().getId().equals("") && !pcpa.getRegional().getId().equals("0")) {
															if (regionalId == pcpa.getRegional().getId()) {
																if (count * item.getPrice() >= pcpa.getStartTotalAmount() && count * item.getPrice() <= pcpa.getEndTotalAmount()) {
																	generatePriceResult(pcpa, "supplier");
																}
															}
														} else {
															if (count * item.getPrice() >= pcpa.getStartTotalAmount() && count * item.getPrice() <= pcpa.getEndTotalAmount()) {
																generatePriceResult(pcpa, "supplier");
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			if (null != priceFixEntityList && priceFixEntityList.size() <= 0 && null != item) {
				PriceFixEntity pfe = new PriceFixEntity();
				pfe.setName(item.getName());
				pfe.setType("");
				pfe.setPrice(item.getPrice());
				pfe.setDisCountPrice(item.getPrice());
				priceFixEntityList.add(pfe);
			} else {
				// 对集合排序
				SortSet scm = new SortSet("disCountPrice");
				Collections.sort(priceFixEntityList, scm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "fixedPrice_content";
	}

	private void generatePriceResult(PriceConditionCountArea pcca, String type) {
		String typeString = getText("mdm_" + type);
		if (type.equals("customerAccountAndItem") || type.equals("item")) {
			if ("all".equals(pcca.getAreaPriceType())) {
				generateByPrice(pcca, typeString);
				generateByDiscount(pcca, typeString);
			} else if ("discount".equals(pcca.getAreaPriceType())) {
				generateByDiscount(pcca, typeString);
			} else if ("price".equals(pcca.getAreaPriceType())) {
				generateByPrice(pcca, typeString);
			}
		} else {
			generateByDiscount(pcca, typeString);
		}
	}

	/** 根据价格生成定价结果 */
	private void generateByPrice(PriceConditionCountArea pcca, String typeString) {
		if (null != pcca.getPrice() && pcca.getPrice() > 0) {
			PriceFixEntity pfe = new PriceFixEntity();
			pfe.setName(item.getName());
			pfe.setType(typeString);
			pfe.setDiscountType("价格");
			pfe.setPrice(item.getPrice());
			pfe.setDisCountPrice(pcca.getPrice());
			generateGiftJson(pcca, pfe);
			priceFixEntityList.add(pfe);
		}
	}

	/** 根据折扣生成定价结果 */
	private void generateByDiscount(PriceConditionCountArea pcca, String typeString) {
		if (null != pcca.getDiscount() && pcca.getDiscount() > 0 && pcca.getDiscount() < 1) {
			PriceFixEntity pfe = new PriceFixEntity();
			pfe.setName(item.getName());
			pfe.setType(typeString);
			pfe.setDiscountType("折扣");
			pfe.setPrice(item.getPrice());
			pfe.setDisCountPrice(pcca.getDiscount() * item.getPrice());
			pfe.setDisCount(pcca.getDiscount());
			generateGiftJson(pcca, pfe);
			priceFixEntityList.add(pfe);
		}
	}

	/** 生成赠品json */
	private void generateGiftJson(PriceConditionCountArea pcca, PriceFixEntity pfe) {
		if (null != pcca.getPriceConditionGifts() && pcca.getPriceConditionGifts().size() > 0) {
			for (PriceConditionGift pcg : pcca.getPriceConditionGifts()) {
				if (null != pcg && null != pcg.getItem() && null != pcg.getItem().getId() && null != pcg.getCount() && pcg.getCount() > 0 && null != pcg.getPrice() && pcg.getPrice() >= 0) {
					PriceFixGift pfg = new PriceFixGift();
					pfg.setId(pcg.getItem().getId());
					pfg.setName(pcg.getItem().getName());
					pfg.setSpecification(pcg.getSpecification());
					pfg.setCount(pcg.getCount());
					pfg.setPrice(pcg.getPrice());
					pfe.getPfgList().add(pfg);
				}
			}
		}
	}

	private void generatePriceResult(PriceConditionPriceArea pcpa, String type) {
		String typeString = getText("mdm_" + type);
		if (type.equals("customerAccountAndItem") || type.equals("item")) {
			if ("all".equals(pcpa.getAreaPriceType())) {
				generateByDiscount(pcpa, typeString);
				generateByRefund(pcpa, typeString);
			} else if ("discount".equals(pcpa.getAreaPriceType())) {
				generateByDiscount(pcpa, typeString);
			} else if ("refund".equals(pcpa.getAreaPriceType())) {
				generateByRefund(pcpa, typeString);
			}
		} else {
			generateByDiscount(pcpa, typeString);
		}
	}

	/** 根据返款生成价格结果 */
	private void generateByRefund(PriceConditionPriceArea pcpa, String typeString) {
		if (null != pcpa.getRefund() && pcpa.getRefund() > 0 && pcpa.getRefund() < item.getPrice()) {
			PriceFixEntity pfe = new PriceFixEntity();
			pfe.setName(item.getName());
			pfe.setType(typeString);
			pfe.setDiscountType("返款");
			pfe.setPrice(item.getPrice());
			pfe.setDisCountPrice(item.getPrice() - pcpa.getRefund());
			generateGiftJson(pcpa, pfe);
			priceFixEntityList.add(pfe);
		}
	}

	/** 根据折扣生成价格结果 */
	private void generateByDiscount(PriceConditionPriceArea pcpa, String typeString) {
		if (null != pcpa.getDiscount() && pcpa.getDiscount() > 0 && pcpa.getDiscount() < 1) {
			PriceFixEntity pfe = new PriceFixEntity();
			pfe.setName(item.getName());
			pfe.setType(typeString);
			pfe.setDiscountType("折扣");
			pfe.setPrice(item.getPrice());
			pfe.setDisCountPrice(pcpa.getDiscount() * item.getPrice());
			pfe.setDisCount(pcpa.getDiscount());
			generateGiftJson(pcpa, pfe);
			priceFixEntityList.add(pfe);
		}
	}

	/** 生成赠品json */
	private void generateGiftJson(PriceConditionPriceArea pcpa, PriceFixEntity pfe) {
		if (null != pcpa.getPriceConditionGifts() && pcpa.getPriceConditionGifts().size() > 0) {
			for (PriceConditionGift pcg : pcpa.getPriceConditionGifts()) {
				if (null != pcg && null != pcg.getItem() && null != pcg.getItem().getId() && null != pcg.getCount() && pcg.getCount() > 0 && null != pcg.getPrice() && pcg.getPrice() >= 0) {
					PriceFixGift pfg = new PriceFixGift();
					pfg.setId(pcg.getItem().getId());
					pfg.setName(pcg.getItem().getName());
					pfg.setSpecification(pcg.getSpecification());
					pfg.setCount(pcg.getCount());
					pfg.setPrice(pcg.getPrice());
					pfe.getPfgList().add(pfg);
				}
			}
		}
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Double getCount() {
		if (null == count) {
			return 0d;
		}
		return count;
	}

	public void setCount(Double count) {
		this.count = count;
	}

	public String getBillCreateDate() {
		return billCreateDate;
	}

	public void setBillCreateDate(String billCreateDate) {
		this.billCreateDate = billCreateDate;
	}

	public String getRegionalId() {
		if (null == regionalId) {
			return "0";
		}
		return regionalId;
	}

	public void setRegionalId(String regionalId) {
		this.regionalId = regionalId;
	}

	public String getSupplierId() {
		if (null == supplierId) {
			return null;
		}
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public List<PriceFixEntity> getPriceFixEntityList() {
		return priceFixEntityList;
	}

	public void setPriceFixEntityList(List<PriceFixEntity> priceFixEntityList) {
		this.priceFixEntityList = priceFixEntityList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
