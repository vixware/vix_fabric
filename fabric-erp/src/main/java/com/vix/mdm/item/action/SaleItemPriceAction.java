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
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.CustomerAccount;
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
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class SaleItemPriceAction extends VixntBaseAction {
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
	private String customerAccountId;
	private String regionalId;
	private String supplierId;
	private String channelDistributorId;

	/** 获取列表数据  */
	public String goListContent(){
		try {
			Map<String,Object> params = getParams();
			if(null != name && !"".equals(name)){
				name = decode(name, "UTF-8");
				params.put("name,"+SearchCondition.ANYLIKE, name);
			}
			params.put("isTemp,"+SearchCondition.NOEQUAL, "1");
			params.put("itemClass,"+SearchCondition.IN, "goods,finishedgoods");
			if(null == getPager().getOrderField() || "".equals(getPager().getOrderField())){
				getPager().setOrderField("id");
				getPager().setOrderBy("desc");
			}
			getPager().setPageSize(8);
			if(null != categoryId && !"".equals(categoryId)){
				Pager pager = itemService.findPagerByItemCatalogId(getPager(),categoryId,params);
				setPager(pager);
			}else{
				itemService.findPagerByHqlConditions(getPager(),Item.class,params);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		String type = getRequestParameter("type");
		if(null != type && "choose".equals(type)){
			return "goListContentChoose";
		}
		return "goListContent";
	}
	
	public void getListJson(){
		try {
			Pager pager = getPager();
			
			Map<String,Object> params = getParams();
			params.put("isTemp,"+SearchCondition.NOEQUAL, "1");
			params.put("itemClass,"+SearchCondition.IN, "goods,finishedgoods");
			
			if(null != name && !"".equals(name)){
				name = decode(name, "UTF-8");
				params.put("name,"+SearchCondition.ANYLIKE, name);
			}
			
			if(null != categoryId && !"".equals(categoryId)){
				itemService.findPagerByItemCatalogId(pager,categoryId,params);
			}else{
				itemService.findPagerByHqlConditions(pager,Item.class,params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goFixedPrice(){
		try {
			if(null != id && !id.equals("") && !id.equals("0")){
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
	/** 选传参数：客户id：customerAccountId，地域id：regionalId,分销商id：channelDistributorId ，供应商id：supplierId */
	public String fixedPrice(){
		try {
			item = priceConditionService.findEntityById(Item.class, id);
			Employee baseEmp = getEmployee();
			if(null != baseEmp && null != item && StrUtils.objectIsNotNull(item.getId()) && !"0".equals(item.getId()) && count > 0 && null != billCreateDate && billCreateDate.length() >= 10){
				Employee emp = priceConditionService.findEntityById(Employee.class, baseEmp.getId());
			 	billCreateDate = billCreateDate.substring(0, 10);
				if(null != emp){
					if(null != emp.getOrganizationUnit() && StrUtils.objectIsNotNull(emp.getOrganizationUnit().getId()) && !"0".equals(emp.getOrganizationUnit().getId())){
						Map<String,Object> params = new HashMap<String,Object>();
						params.put("saleOrg.id,"+SearchCondition.EQUAL, emp.getOrganizationUnit().getId());
						params.put("isTemp,"+SearchCondition.NOEQUAL,"1");
						params.put("isDeleted,"+SearchCondition.NOEQUAL,"1");
						params.put("priceConditionType,"+SearchCondition.EQUAL,"sale");
						List<PriceCondition> pcList = priceConditionService.findAllByConditions(PriceCondition.class, params);
						if(null != pcList && pcList.size() <= 0){
							return "fixedPrice_content";
						}
						for(PriceCondition pc : pcList){
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							if(null != pc.getStartEffectiveTime() && sdf.parse(billCreateDate).after(pc.getStartEffectiveTime()) && null != pc.getEndEffectiveTime() && sdf.parse(billCreateDate).before(pc.getEndEffectiveTime())){
								if(null != pc.getPriceConditionCountAreas() && pc.getPriceConditionCountAreas().size()>0){
									for(PriceConditionCountArea pcca : pc.getPriceConditionCountAreas()){
										if(null != pcca.getConditionType() && !"".equals(pcca.getConditionType())){
											if("saleFrameworkAgreement".equals(pcca.getConditionType())){
												if(null != pcca.getCustomerAccount() && null != pcca.getCustomerAccount().getId() &&customerAccountId.equals(pcca.getCustomerAccount().getId()) &&
														null != pcca.getItem() && null != pcca.getItem().getId() &&
														id.equals(pcca.getItem().getId())){
													if(count >= pcca.getStartCount() && count <= pcca.getEndCount()){
														generatePriceResult(pcca,"saleFrameworkAgreement");
													}
												}
											}else if("customerAccountCategory".equals(pcca.getConditionType())){
												if(!customerAccountId.equals("") && !customerAccountId.equals("0")){
													CustomerAccount ca = itemService.findEntityById(CustomerAccount.class, customerAccountId);
													if(null != ca && null != ca.getCustomerAccountCategory() && StrUtils.objectIsNotNull(ca.getCustomerAccountCategory().getId()) && !"0".equals(ca.getCustomerAccountCategory().getId())){
														if(null != pcca.getRegional() && StrUtils.objectIsNotNull(pcca.getRegional().getId()) && !"0".equals(pcca.getRegional().getId())){
															if(regionalId.equals(pcca.getRegional().getId())){
																if(ca.getCustomerAccountCategory().getId().equals(pcca.getCustomerAccountCategory().getId())){
																	if(count >= pcca.getStartCount() && count <= pcca.getEndCount()){
																		generatePriceResult(pcca,"customerAccountCategory");
																	}
																}
															}
														}else{
															if(ca.getCustomerAccountCategory().getId() == pcca.getCustomerAccountCategory().getId()){
																if(count >= pcca.getStartCount() && count <= pcca.getEndCount()){
																	generatePriceResult(pcca,"customerAccountCategory");
																}
															}
														}
													}
												}
											}else if("itemCategory".equals(pcca.getConditionType())){
												if(null != item.getItemCatalogs() && item.getItemCatalogs().size() > 0){
													String[] icids = item.getItemCatalogIds().split(",");
													boolean has = false;
													for(String icid : icids){
														if(null != icid && !"".equals(icid) && icid.equals(pcca.getItemCatalog().getId().toString())){
															has = true;
															break;
														}
													}
													if(has){
														if(null != pcca.getRegional() && StrUtils.objectIsNotNull(pcca.getRegional().getId()) && !"0".equals(pcca.getRegional().getId())){
															if(regionalId.equals(pcca.getRegional().getId())){
																if(count >= pcca.getStartCount() && count <= pcca.getEndCount()){
																	generatePriceResult(pcca,"itemCategory");
																}
															}
														}else{
															if(count >= pcca.getStartCount() && count <= pcca.getEndCount()){
																generatePriceResult(pcca,"itemCategory");
															}
														}
													}
												}
											}else if("itemGroup".equals(pcca.getConditionType())){
												if(null != item.getItemGroup() && StrUtils.objectIsNotNull(item.getItemGroup().getId()) && !"0".equals(item.getItemGroup().getId() )){
													if(item.getItemGroup().getId().equals(pcca.getItemGroup().getId())){
														if(null != pcca.getRegional() && null != pcca.getRegional().getId() && !pcca.getRegional().getId().equals("") && !pcca.getRegional().getId().equals("0")){
															if(regionalId.equals(pcca.getRegional().getId())){
																if(count >= pcca.getStartCount() && count <= pcca.getEndCount()){
																	generatePriceResult(pcca,"itemGroup");
																}
															}
														}else{
															if(count >= pcca.getStartCount() && count <= pcca.getEndCount()){
																generatePriceResult(pcca,"itemGroup");
															}
														}
													}
												}
											}else if("customerAccountGroup".equals(pcca.getConditionType())){
												if(!customerAccountId.equals("") && !customerAccountId.equals("0")){
													CustomerAccount ca = itemService.findEntityById(CustomerAccount.class, customerAccountId);
													if(null != ca && null != ca.getCustomerAccountGroup() && !ca.getCustomerAccountGroup().getId().equals("") && !ca.getCustomerAccountGroup().getId().equals("0")){
														if(ca.getCustomerAccountGroup().getId().equals(pcca.getCustomerAccountGroup().getId())){
															if(null != pcca.getRegional() && null != pcca.getRegional().getId() && !pcca.getRegional().getId().equals("") && !pcca.getRegional().getId().equals("0")){
																if(regionalId.equals(pcca.getRegional().getId())){
																	if(count >= pcca.getStartCount() && count <= pcca.getEndCount()){
																		generatePriceResult(pcca,"customerAccountGroup");
																	}
																}
															}else{
																if(count >= pcca.getStartCount() && count <= pcca.getEndCount()){
																	generatePriceResult(pcca,"customerAccountGroup");
																}
															}
														}
													}
												}
											}else if("customerAccount".equals(pcca.getConditionType())){
												if(!customerAccountId.equals("") && !customerAccountId.equals("0")){
													CustomerAccount ca = itemService.findEntityById(CustomerAccount.class, customerAccountId);
													if(null != ca && ca.getId().equals(pcca.getCustomerAccount().getId())){
														if(null != pcca.getRegional() && null != pcca.getRegional().getId() && !pcca.getRegional().getId().equals("") && !pcca.getRegional().getId().equals("0")){
															if(regionalId.equals(pcca.getRegional().getId())){
																if(count >= pcca.getStartCount() && count <= pcca.getEndCount()){
																	generatePriceResult(pcca,"customerAccount");
																}
															}
														}else{
															if(count >= pcca.getStartCount() && count <= pcca.getEndCount()){
																generatePriceResult(pcca,"customerAccount");
															}
														}
													}
												}
											}else if("item".equals(pcca.getConditionType())){
												if(item.getId().equals(pcca.getItem().getId())){
													if(null != pcca.getRegional() && null != pcca.getRegional().getId() && !pcca.getRegional().getId().equals("") && !pcca.getRegional().getId().equals("0")){
														if(regionalId.equals(pcca.getRegional().getId())){
															if(count >= pcca.getStartCount() && count <= pcca.getEndCount()){
																generatePriceResult(pcca,"item");
															}
														}
													}else{
														if(count >= pcca.getStartCount() && count <= pcca.getEndCount()){
															generatePriceResult(pcca,"item");
														}
													}
												}
											}else if("customerAccountAndItem".equals(pcca.getConditionType())){
												if(!customerAccountId.equals("") && !customerAccountId.equals("0")){
													CustomerAccount ca = itemService.findEntityById(CustomerAccount.class, customerAccountId);
													if(null != ca && ca.getId().equals(pcca.getCustomerAccount().getId())){
														if(null != pcca.getRegional() && null != pcca.getRegional().getId() && !pcca.getRegional().getId().equals("") && !pcca.getRegional().getId().equals("0")){
															if(regionalId.equals(pcca.getRegional().getId())){
																if(item.getId().equals(pcca.getItem().getId())){
																	if(count >= pcca.getStartCount() && count <= pcca.getEndCount()){
																		generatePriceResult(pcca,"customerAccountAndItem");
																	}
																}
															}
														}else{
															if(item.getId().equals(pcca.getItem().getId())){
																if(count >= pcca.getStartCount() && count <= pcca.getEndCount()){
																	generatePriceResult(pcca,"customerAccountAndItem");
																}
															}
														}
													}
												}
											}else if("purchaseFrameworkAgreement".equals(pcca.getConditionType())){
												if(StringUtils.isNotEmpty(supplierId) && !supplierId.equals("0")){
													Supplier s = itemService.findEntityById(Supplier.class, supplierId);
													if(null != s && s.getId().equals(pcca.getSupplier().getId())){
														if(null != pcca.getRegional() && null != pcca.getRegional().getId() && !pcca.getRegional().getId().equals("") && !pcca.getRegional().getId().equals("0")){
															if(regionalId.equals(pcca.getRegional().getId())){
																if(count >= pcca.getStartCount() && count <= pcca.getEndCount()){
																	generatePriceResult(pcca,"purchaseFrameworkAgreement");
																}
															}
														}else{
															if(count >= pcca.getStartCount() && count <= pcca.getEndCount()){
																generatePriceResult(pcca,"purchaseFrameworkAgreement");
															}
														}
													}
												}
											}else if("supplier".equals(pcca.getConditionType())){
												if(StringUtils.isNotEmpty(supplierId) && !supplierId.equals("0")){
													Supplier s = itemService.findEntityById(Supplier.class, supplierId);
													if(null != s && s.getId().equals(pcca.getSupplier().getId())){
														if(null != pcca.getRegional() && null != pcca.getRegional().getId() && !pcca.getRegional().getId().equals("") && !pcca.getRegional().getId().equals("0")){
															if(regionalId.equals(pcca.getRegional().getId())){
																if(count >= pcca.getStartCount() && count <= pcca.getEndCount()){
																	generatePriceResult(pcca,"supplier");
																}
															}
														}else{
															if(count >= pcca.getStartCount() && count <= pcca.getEndCount()){
																generatePriceResult(pcca,"supplier");
															}
														}
													}
												}
											}else if("channelDistributor".equals(pcca.getConditionType())){
												if(StringUtils.isNotEmpty(channelDistributorId) && !channelDistributorId.equals("0")){
													ChannelDistributor cd = itemService.findEntityById(ChannelDistributor.class, channelDistributorId);
													if(null != cd && cd.getId().equals(pcca.getChannelDistributor().getId())){
														if(null != pcca.getRegional() && null != pcca.getRegional().getId() && !pcca.getRegional().getId().equals("") && !pcca.getRegional().getId().equals("0")){
															if(regionalId.equals(pcca.getRegional().getId())){
																if(count >= pcca.getStartCount() && count <= pcca.getEndCount()){
																	generatePriceResult(pcca,"channelDistributor");
																}
															}
														}else{
															if(count >= pcca.getStartCount() && count <= pcca.getEndCount()){
																generatePriceResult(pcca,"channelDistributor");
															}
														}
													}
												}
											}
										}
										
									}
								}
							}
							if(null != pc.getPriceConditionPriceAreas() && pc.getPriceConditionPriceAreas().size() > 0){
								for(PriceConditionPriceArea pcpa : pc.getPriceConditionPriceAreas()){
									if(null != pcpa.getConditionType() && !"".equals(pcpa.getConditionType())){
										if("saleFrameworkAgreement".equals(pcpa.getConditionType())){
											if(null != pcpa.getCustomerAccount() && null != pcpa.getCustomerAccount().getId() 
													&& customerAccountId.equals(pcpa.getCustomerAccount().getId()) &&
													null != pcpa.getItem() && null != pcpa.getItem().getId() && id.equals(pcpa.getItem().getId())){
												if(count*item.getPrice() >= pcpa.getStartTotalAmount() && count*item.getPrice() <= pcpa.getEndTotalAmount()){
													generatePriceResult(pcpa,"saleFrameworkAgreement");
												}
											}
										}else if("customerAccountCategory".equals(pcpa.getConditionType())){
											if(!customerAccountId.equals("") && !customerAccountId.equals("0")){
												CustomerAccount ca = itemService.findEntityById(CustomerAccount.class, customerAccountId);
												if(null != ca && null != ca.getCustomerAccountCategory() && !ca.getCustomerAccountCategory().getId().equals("") && !ca.getCustomerAccountCategory().getId().equals("0")){
													if(null != pcpa.getRegional() && null != pcpa.getRegional().getId() && !pcpa.getRegional().getId().equals("") && !pcpa.getRegional().getId().equals("0")){
														if(regionalId.equals(pcpa.getRegional().getId())){
															if(ca.getCustomerAccountCategory().getId().equals(pcpa.getCustomerAccountCategory().getId())){
																if(count*item.getPrice() >= pcpa.getStartTotalAmount() && count*item.getPrice() <= pcpa.getEndTotalAmount()){
																	generatePriceResult(pcpa,"customerAccountCategory");
																}
															}
														}
													}else{
														if(ca.getCustomerAccountCategory().getId().equals(pcpa.getCustomerAccountCategory().getId())){
															if(count*item.getPrice() >= pcpa.getStartTotalAmount() && count*item.getPrice() <= pcpa.getEndTotalAmount()){
																generatePriceResult(pcpa,"customerAccountCategory");
															}
														}
													}
												}
											}
										}else if("itemCategory".equals(pcpa.getConditionType())){
											if(null != item.getItemCatalogs() && item.getItemCatalogs().size() > 0){
												String[] icids = item.getItemCatalogIds().split(",");
												boolean has = false;
												for(String icid : icids){
													if(null != icid && !"".equals(icid) && icid.equals(pcpa.getItemCatalog().getId().toString())){
														has = true;
														break;
													}
												}
												if(has){
													if(null != pcpa.getRegional() && null != pcpa.getRegional().getId() && !pcpa.getRegional().getId().equals("") && !pcpa.getRegional().getId().equals("0")){
														if(regionalId.equals(pcpa.getRegional().getId())){
															if(count*item.getPrice() >= pcpa.getStartTotalAmount() && count*item.getPrice() <= pcpa.getEndTotalAmount()){
																generatePriceResult(pcpa,"itemCategory");
															}
														}
													}else{
														if(count*item.getPrice() >= pcpa.getStartTotalAmount() && count*item.getPrice() <= pcpa.getEndTotalAmount()){
															generatePriceResult(pcpa,"itemCategory");
														}
													}
												}
											}
										}else if("itemGroup".equals(pcpa.getConditionType())){
											if(null != item.getItemGroup() && !item.getItemGroup().getId().equals("") && !item.getItemGroup().getId().equals("0")){
												if(item.getItemGroup().getId().equals(pcpa.getItemGroup().getId())){
													if(null != pcpa.getRegional() && null != pcpa.getRegional().getId() && !pcpa.getRegional().getId().equals("") && !pcpa.getRegional().getId().equals("0")){
														if(regionalId.equals(pcpa.getRegional().getId())){
															if(count*item.getPrice() >= pcpa.getStartTotalAmount() && count*item.getPrice() <= pcpa.getEndTotalAmount()){
																generatePriceResult(pcpa,"itemGroup");
															}
														}
													}else{
														if(count*item.getPrice() >= pcpa.getStartTotalAmount() && count*item.getPrice() <= pcpa.getEndTotalAmount()){
															generatePriceResult(pcpa,"itemGroup");
														}
													}
												}
											}
										}else if("customerAccountGroup".equals(pcpa.getConditionType())){
											if(!customerAccountId.equals("") && !customerAccountId.equals("0")){
												CustomerAccount ca = itemService.findEntityById(CustomerAccount.class, customerAccountId);
												if(null != ca && null != ca.getCustomerAccountGroup() && !ca.getCustomerAccountGroup().getId().equals("") && !ca.getCustomerAccountGroup().getId().equals("0")){
													if(ca.getCustomerAccountGroup().getId().equals(pcpa.getCustomerAccountGroup().getId())){
														if(null != pcpa.getRegional() && null != pcpa.getRegional().getId() && !pcpa.getRegional().getId().equals("") && !pcpa.getRegional().getId().equals("0")){
															if(regionalId.equals(pcpa.getRegional().getId())){
																if(count*item.getPrice() >= pcpa.getStartTotalAmount() && count*item.getPrice() <= pcpa.getEndTotalAmount()){
																	generatePriceResult(pcpa,"customerAccountGroup");
																}
															}
														}else{
															if(count*item.getPrice() >= pcpa.getStartTotalAmount() && count*item.getPrice() <= pcpa.getEndTotalAmount()){
																generatePriceResult(pcpa,"customerAccountGroup");
															}
														}
													}
												}
											}
										}else if("customerAccount".equals(pcpa.getConditionType())){
											if(!customerAccountId.equals("") && !customerAccountId.equals("0")){
												CustomerAccount ca = itemService.findEntityById(CustomerAccount.class, customerAccountId);
												if(null != ca && ca.getId().equals(pcpa.getCustomerAccount().getId())){
													if(null != pcpa.getRegional() && null != pcpa.getRegional().getId() && !pcpa.getRegional().getId().equals("") && !pcpa.getRegional().getId().equals("0")){
														if(regionalId.equals(pcpa.getRegional().getId())){
															if(count*item.getPrice() >= pcpa.getStartTotalAmount() && count*item.getPrice() <= pcpa.getEndTotalAmount()){
																generatePriceResult(pcpa,"customerAccount");
															}
														}
													}else{
														if(count*item.getPrice() >= pcpa.getStartTotalAmount() && count*item.getPrice() <= pcpa.getEndTotalAmount()){
															generatePriceResult(pcpa,"customerAccount");
														}
													}
												}
											}
										}else if("item".equals(pcpa.getConditionType())){
											if(item.getId().equals(pcpa.getItem().getId())){
												if(null != pcpa.getRegional() && null != pcpa.getRegional().getId() && !pcpa.getRegional().getId().equals("") && !pcpa.getRegional().getId().equals("0")){
													if(regionalId.equals(pcpa.getRegional().getId())){
														if(count*item.getPrice() >= pcpa.getStartTotalAmount() && count*item.getPrice() <= pcpa.getEndTotalAmount()){
															generatePriceResult(pcpa,"item");
														}
													}
												}else{
													if(count*item.getPrice() >= pcpa.getStartTotalAmount() && count*item.getPrice() <= pcpa.getEndTotalAmount()){
														generatePriceResult(pcpa,"item");
													}
												}
											}
										}else if("customerAccountAndItem".equals(pcpa.getConditionType())){
											if(!customerAccountId.equals("") && !customerAccountId.equals("0")){
												CustomerAccount ca = itemService.findEntityById(CustomerAccount.class, customerAccountId);
												if(null != ca && ca.getId().equals(pcpa.getCustomerAccount().getId())){
													if(null != pcpa.getRegional() && null != pcpa.getRegional().getId() && !pcpa.getRegional().getId().equals("") && !pcpa.getRegional().getId().equals("0")){
														if(regionalId.equals(pcpa.getRegional().getId())){
															if(item.getId().equals(pcpa.getItem().getId())){
																if(count*item.getPrice() >= pcpa.getStartTotalAmount() && count*item.getPrice() <= pcpa.getEndTotalAmount()){
																	generatePriceResult(pcpa,"customerAccountAndItem");
																}
															}
														}
													}else{
														if(item.getId().equals(pcpa.getItem().getId())){
															if(count*item.getPrice() >= pcpa.getStartTotalAmount() && count*item.getPrice() <= pcpa.getEndTotalAmount()){
																generatePriceResult(pcpa,"customerAccountAndItem");
															}
														}
													}
												}
											}
										}else if("purchaseFrameworkAgreement".equals(pcpa.getConditionType())){
											if(StringUtils.isNotEmpty(supplierId) && !supplierId.equals("0")){
												Supplier s = itemService.findEntityById(Supplier.class, supplierId);
												if(null != s && s.getId().equals(pcpa.getSupplier().getId())){
													if(null != pcpa.getRegional() && null != pcpa.getRegional().getId() && !pcpa.getRegional().getId().equals("") && !pcpa.getRegional().getId().equals("0")){
														if(regionalId.equals(pcpa.getRegional().getId())){
															if(count*item.getPrice() >= pcpa.getStartTotalAmount() && count*item.getPrice() <= pcpa.getEndTotalAmount()){
																generatePriceResult(pcpa,"purchaseFrameworkAgreement");
															}
														}
													}else{
														if(count*item.getPrice() >= pcpa.getStartTotalAmount() && count*item.getPrice() <= pcpa.getEndTotalAmount()){
															generatePriceResult(pcpa,"purchaseFrameworkAgreement");
														}
													}
												}
											}
										}else if("supplier".equals(pcpa.getConditionType())){
											if(StringUtils.isNotEmpty(supplierId) && !supplierId.equals("0")){
												Supplier s = itemService.findEntityById(Supplier.class, supplierId);
												if(null != s && s.getId().equals(pcpa.getSupplier().getId())){
													if(null != pcpa.getRegional() && null != pcpa.getRegional().getId() && !pcpa.getRegional().getId().equals("") && !pcpa.getRegional().getId().equals("0")){
														if(regionalId.equals(pcpa.getRegional().getId())){
															if(count*item.getPrice() >= pcpa.getStartTotalAmount() && count*item.getPrice() <= pcpa.getEndTotalAmount()){
																generatePriceResult(pcpa,"supplier");
															}
														}
													}else{
														if(count*item.getPrice() >= pcpa.getStartTotalAmount() && count*item.getPrice() <= pcpa.getEndTotalAmount()){
															generatePriceResult(pcpa,"supplier");
														}
													}
												}
											}
										}else if("channelDistributor".equals(pcpa.getConditionType())){
											if(StringUtils.isNotEmpty(channelDistributorId) && !channelDistributorId.equals("0")){
												ChannelDistributor cd = itemService.findEntityById(ChannelDistributor.class, channelDistributorId);
												if(null != cd && cd.getId().equals(pcpa.getChannelDistributor().getId())){
													if(null != pcpa.getRegional() && null != pcpa.getRegional().getId() && !pcpa.getRegional().getId().equals("") && !pcpa.getRegional().getId().equals("0")){
														if(regionalId.equals(pcpa.getRegional().getId())){
															if(count*item.getPrice() >= pcpa.getStartTotalAmount() && count*item.getPrice() <= pcpa.getEndTotalAmount()){
																generatePriceResult(pcpa,"channelDistributor");
															}
														}
													}else{
														if(count*item.getPrice() >= pcpa.getStartTotalAmount() && count*item.getPrice() <= pcpa.getEndTotalAmount()){
															generatePriceResult(pcpa,"channelDistributor");
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
			if(null != priceFixEntityList && priceFixEntityList.size() <= 0 && null != item){
				PriceFixEntity pfe = new PriceFixEntity();
				pfe.setName(item.getName());
				pfe.setType("");
				pfe.setPrice(item.getPrice());
				pfe.setDisCountPrice(item.getPrice());
				priceFixEntityList.add(pfe);
			}else{
				//对集合排序 
				SortSet scm = new SortSet("disCountPrice");
				Collections.sort(priceFixEntityList, scm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "fixedPrice_content";
	}
	
	public StringBuilder findMemberShipCardList(Map<String, Object> params) {
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append("p");
		hql.append(" from PriceCondition ").append("p");
		hql.append(" left join fetch ");
		hql.append("p.priceConditionCountAreas , p.priceConditionPriceAreas");
		hql.append(" where 1=1 and ");
		if (params != null) {
			if (params.containsKey("priceConditionCountAreas")) {
				Object priceConditionCountAreas = params.get("priceConditionCountAreas");
				if (priceConditionCountAreas == null) {
					hql.append(" and ").append("p").append(".priceConditionCountAreas is null");
					params.remove("priceConditionCountAreas");
				} else {
					hql.append(" and ").append("p").append(".priceConditionCountAreas ");
				}
			}
			if (params.containsKey("priceConditionPriceAreas")) {
				Object priceConditionPriceAreas = params.get("priceConditionPriceAreas");
				if (priceConditionPriceAreas == null) {
					hql.append(" and ").append("p").append(".priceConditionPriceAreas ");
					params.remove("priceConditionPriceAreas");
				} else {
					hql.append(" and ").append("p").append(".priceConditionPriceAreas ");
				}
			}
			if (params.containsKey("item")) {
				Object tenantId = params.get("item");
				if (tenantId == null) {
					hql.append(" and ").append("p").append(".item is null ");
					params.remove("tenantId");
				} else {
					hql.append(" and ").append("p").append(".item.id = :"+item.getId()+'"');
				}
			}
			
		} else {
			
		}
		return hql;
	}
	
	private void generatePriceResult(PriceConditionCountArea pcca,String type){
		String typeString = getText("mdm_"+type);
		if(type.equals("customerAccountAndItem") || type.equals("item")){
			if("all".equals(pcca.getAreaPriceType())){
				generateByPrice(pcca,typeString);
				generateByDiscount(pcca,typeString);
			}else if("discount".equals(pcca.getAreaPriceType())){
				generateByDiscount(pcca,typeString);
			}else if("price".equals(pcca.getAreaPriceType())){
				generateByPrice(pcca,typeString);
			}
		}else{
			generateByDiscount(pcca,typeString);
		}
	}
	
	/** 根据价格生成定价结果 */
	private void generateByPrice(PriceConditionCountArea pcca,String typeString){
		if(null != pcca.getPrice() && pcca.getPrice() > 0){
			PriceFixEntity pfe = new PriceFixEntity();
			pfe.setName(item.getName());
			pfe.setType(typeString);
			pfe.setDiscountType("价格");
			pfe.setPrice(item.getPrice());
			pfe.setDisCountPrice(pcca.getPrice());
			generateGiftJson(pcca,pfe);
			priceFixEntityList.add(pfe);
		}
	}
	
	/** 根据折扣生成定价结果 */
	private void generateByDiscount(PriceConditionCountArea pcca,String typeString){
		if(null != pcca.getDiscount() && pcca.getDiscount() > 0 && pcca.getDiscount() < 1){
			PriceFixEntity pfe = new PriceFixEntity();
			pfe.setName(item.getName());
			pfe.setType(typeString);
			pfe.setDiscountType("折扣");
			pfe.setPrice(item.getPrice());
			pfe.setDisCountPrice(pcca.getDiscount() * item.getPrice());
			pfe.setDisCount(pcca.getDiscount());
			generateGiftJson(pcca,pfe);
			priceFixEntityList.add(pfe);
		}
	}
	
	/** 生成赠品json */
	private void generateGiftJson(PriceConditionCountArea pcca,PriceFixEntity pfe){
		if(null != pcca.getPriceConditionGifts() && pcca.getPriceConditionGifts().size() > 0){
			for(PriceConditionGift pcg : pcca.getPriceConditionGifts()){
				if(null != pcg && null !=pcg.getItem() && null != pcg.getItem().getId() && null != pcg.getCount() && pcg.getCount() > 0 && null != pcg.getPrice() && pcg.getPrice() >=0){
					PriceFixGift  pfg = new PriceFixGift();
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
	
	private void generatePriceResult(PriceConditionPriceArea pcpa,String type){
		String typeString = getText("mdm_"+type);
		if(type.equals("customerAccountAndItem") || type.equals("item")){
			if("all".equals(pcpa.getAreaPriceType())){
				generateByRefund(pcpa,typeString);
				generateByDiscount(pcpa,typeString);
			}else if("discount".equals(pcpa.getAreaPriceType())){
				generateByDiscount(pcpa,typeString);
			}else if("refund".equals(pcpa.getAreaPriceType())){
				generateByRefund(pcpa,typeString);
			}
		}else{
			generateByDiscount(pcpa,typeString);
		}
	}
	
	/** 根据返款生成价格结果 */
	private void generateByRefund(PriceConditionPriceArea pcpa,String typeString){
		if(null != pcpa.getRefund() && pcpa.getRefund() > 0 && pcpa.getRefund() < item.getPrice()){
			PriceFixEntity pfe = new PriceFixEntity();
			pfe.setName(item.getName());
			pfe.setType(typeString);
			pfe.setDiscountType("返款");
			pfe.setPrice(item.getPrice());
			pfe.setDisCountPrice(item.getPrice() - pcpa.getRefund());
			generateGiftJson(pcpa,pfe);
			priceFixEntityList.add(pfe);
		}
	}
	
	/** 根据折扣生成价格结果 */
	private void generateByDiscount(PriceConditionPriceArea pcpa,String typeString){
		if(null != pcpa.getDiscount() && pcpa.getDiscount() > 0 && pcpa.getDiscount() < 1){
			PriceFixEntity pfe = new PriceFixEntity();
			pfe.setName(item.getName());
			pfe.setType(typeString);
			pfe.setDiscountType("折扣");
			pfe.setPrice(item.getPrice());
			pfe.setDisCountPrice(pcpa.getDiscount() * item.getPrice());
			pfe.setDisCount(pcpa.getDiscount());
			generateGiftJson(pcpa,pfe);
			priceFixEntityList.add(pfe);
		}
	}

	/** 生成赠品json */
	private void generateGiftJson(PriceConditionPriceArea pcpa,PriceFixEntity pfe){
		if(null != pcpa.getPriceConditionGifts() && pcpa.getPriceConditionGifts().size() > 0){
			for(PriceConditionGift pcg : pcpa.getPriceConditionGifts()){
				if(null != pcg && null !=pcg.getItem() && null != pcg.getItem().getId() && null != pcg.getCount() && pcg.getCount() > 0 && null != pcg.getPrice() && pcg.getPrice() >=0){
					PriceFixGift  pfg = new PriceFixGift();
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
	

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Double getCount() {
		if(null == count){
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

	public String getCustomerAccountId() {
		if(null == customerAccountId){
			return "0";
		}
		return customerAccountId;
	}

	public void setCustomerAccountId(String customerAccountId) {
		this.customerAccountId = customerAccountId;
	}

	public String getRegionalId() {
		if(null == regionalId){
			return "0";
		}
		return regionalId;
	}

	public void setRegionalId(String regionalId) {
		this.regionalId = regionalId;
	}

	public String getSupplierId() {
		if(null == supplierId){
			return null;
		}
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getChannelDistributorId() {
		if(null == channelDistributorId){
			return null;
		}
		return channelDistributorId;
	}

	public void setChannelDistributorId(String channelDistributorId) {
		this.channelDistributorId = channelDistributorId;
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
