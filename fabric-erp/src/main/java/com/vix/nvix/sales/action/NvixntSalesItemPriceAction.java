package com.vix.nvix.sales.action;

import java.text.DecimalFormat;
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
import com.vix.nvix.common.base.action.VixntBaseAction;

/**
 * 物料定价action，提供物料定价页面内物料的分页与检索
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class NvixntSalesItemPriceAction extends VixntBaseAction {
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
	private String customerId;

	public String goFixedPrice(){
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
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
	public void fixedPrice(){
		try {
			item = priceConditionService.findEntityById(Item.class, id);
			Employee baseEmp = getEmployee();
			if(null != baseEmp && null != item && !item.getId().equals("") && !item.getId().equals("0") && count > 0 && null != customerId){
				Employee emp = priceConditionService.findEntityById(Employee.class, baseEmp.getId());
				if(null != emp){
					if(null != emp.getOrganizationUnit() && null != emp.getOrganizationUnit().getId() && !emp.getOrganizationUnit().getId().equals("") && !emp.getOrganizationUnit().equals("0")){
						Map<String,Object> params = new HashMap<String,Object>();
						//params.put("saleOrg.id,"+SearchCondition.EQUAL, emp.getOrganizationUnit().getId());
						params.put("isTemp,"+SearchCondition.NOEQUAL,"1");
						params.put("isDeleted,"+SearchCondition.NOEQUAL,"1");
						params.put("enable,"+SearchCondition.EQUAL,"1");
						params.put("priceConditionType,"+SearchCondition.EQUAL,"sale");
						List<PriceCondition> pcList = priceConditionService.findAllByConditions(PriceCondition.class, params);
						for(PriceCondition pc : pcList){
							if(null != pc.getPriceConditionCountAreas() && pc.getPriceConditionCountAreas().size()>0){
								for(PriceConditionCountArea pcca : pc.getPriceConditionCountAreas()){
									if(pcca.getItem() != null && item.getId() == pcca.getItem().getId()){
										if(regionalId != null && null != pcca.getRegional() && null != pcca.getRegional().getId() && !pcca.getRegional().getId().equals("") && !pcca.getRegional().getId().equals("0")){
											if(regionalId == pcca.getRegional().getId()){
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
								}
							}
							if(null != pc.getPriceConditionPriceAreas() && pc.getPriceConditionPriceAreas().size() > 0){
								for(PriceConditionPriceArea pcpa : pc.getPriceConditionPriceAreas()){
									if(pcpa.getItem() != null && item.getId() == pcpa.getItem().getId()){
										if(regionalId != null && null != pcpa.getRegional() && null != pcpa.getRegional().getId() && !pcpa.getRegional().getId().equals("") && !pcpa.getRegional().getId().equals("0")){
											if(regionalId == pcpa.getRegional().getId()){
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
			}
			SortSet ss = new SortSet("disCountPrice");
			Collections.sort(priceFixEntityList, ss);
			Pager pager = getPager();
			pager.setOrderBy("asc");
			pager.setOrderField("disCountPrice");
			pager.setResultList(priceFixEntityList);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		if(null != pcca.getDiscount() && pcca.getDiscount() > 0 && pcca.getDiscount() < 100){
			PriceFixEntity pfe = new PriceFixEntity();
			pfe.setName(item.getName());
			pfe.setType(typeString);
			pfe.setDiscountType("折扣");
			pfe.setPrice(item.getPrice());
			DecimalFormat df = new DecimalFormat("#.00");
			pfe.setDisCountPrice(Double.parseDouble(df.format(pcca.getDiscount()/100 * item.getPrice())));
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
				generateByDiscount(pcpa,typeString);
				generateByRefund(pcpa,typeString);
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
		if(null != pcpa.getDiscount() && pcpa.getDiscount() > 0 && pcpa.getDiscount() < 100){
			PriceFixEntity pfe = new PriceFixEntity();
			pfe.setName(item.getName());
			pfe.setType(typeString);
			pfe.setDiscountType("折扣");
			pfe.setPrice(item.getPrice());
			DecimalFormat df = new DecimalFormat("#.00");
			pfe.setDisCountPrice(Double.parseDouble(df.format(pcpa.getDiscount()/100 * item.getPrice())));
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

	public String getRegionalId() {
		if(null == regionalId){
			return "0";
		}
		return regionalId;
	}

	public void setRegionalId(String regionalId) {
		this.regionalId = regionalId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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
