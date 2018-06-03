package com.vix.mdm.item.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.share.entity.Regional;
import com.vix.core.constant.SearchCondition;
import com.vix.crm.customer.entity.CustomerAccountCategory;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.mdm.crm.entity.CustomerAccountGroup;
import com.vix.mdm.item.constant.PriceConditionConstantInitialization;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.item.entity.ItemCatalog;
import com.vix.mdm.item.entity.ItemGroup;
import com.vix.mdm.item.entity.PriceConditionGift;
import com.vix.mdm.item.entity.PriceConditionPriceArea;
import com.vix.mdm.srm.share.entity.Supplier;

@Controller
@Scope("prototype")
public class SalePriceConditionPriceAreaAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	
	private String id;
	private PriceConditionPriceArea priceConditionPriceArea;
	private Map<String,String> pcpaMap = PriceConditionConstantInitialization.getSalePriceConditionConstantMap();
	
	/** 获取列表数据  */
	private List<PriceConditionPriceArea> pcpaList;
	public String goListContent(){
		try {
			if(null != id && !id.equals("") && !id.equals("0")){
				pcpaList = baseHibernateService.findAllByEntityClassAndAttribute(PriceConditionPriceArea.class,"priceCondition.id",id);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "goListContent";
	}
	
	/** 跳转至用户修改页面 */
	private List<Regional> regionalList;
	private List<ItemGroup> itemGroupList;
	private List<CustomerAccountGroup> customerAccountGroupList;
	public String goSaveOrUpdate() {
		try {
			regionalList = baseHibernateService.findAllByEntityClass(Regional.class);
			Map<String,Object> params = getParams();
			params.put("isTemp,"+SearchCondition.NOEQUAL,"1");
			itemGroupList = baseHibernateService.findAllByConditions(ItemGroup.class, params);
			customerAccountGroupList = baseHibernateService.findAllByEntityClass(CustomerAccountGroup.class);
			if(null != id && !id.equals("") && !id.equals("0")){
				priceConditionPriceArea = baseHibernateService.findEntityById(PriceConditionPriceArea.class,id);
			}else{
				priceConditionPriceArea = new PriceConditionPriceArea();
				priceConditionPriceArea = baseHibernateService.merge(priceConditionPriceArea);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作  */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			String vd = getRequestParameter("variableData");
			if(null != vd && !"".equals(vd) && null != priceConditionPriceArea.getConditionType() && !"".equals(priceConditionPriceArea.getConditionType())){
				loadConditionDetail(vd);
				priceConditionPriceArea.setIsTemp("0");
				priceConditionPriceArea = baseHibernateService.merge(priceConditionPriceArea);
				if(isSave){
					renderText(SAVE_SUCCESS);
				}else{
					renderText(UPDATE_SUCCESS);
				}
			}else{
				setMessage("定价类型为获取到或定价数据未配置!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(isSave){
				renderText(SAVE_FAIL);
			}else{
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			PriceConditionPriceArea pc = baseHibernateService.findEntityById(PriceConditionPriceArea.class,id);
			if(null != pc){
				if(null != pc.getPriceConditionGifts() && pc.getPriceConditionGifts().size() > 0){
					for(PriceConditionGift pcg : pc.getPriceConditionGifts()){
						if(null != pcg && null != pcg.getId() && !pcg.getId().equals("") && !pcg.getId().equals("0")){
							baseHibernateService.deleteByEntity(pcg);
						}
					}
				}
				baseHibernateService.deleteByEntity(pc);
				renderText(DELETE_SUCCESS);
			}else{
				setMessage(getText("mdm_priceConditionPriceAreaNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	private void loadConditionDetail(String vd){
		Regional regional = new Regional();
		Supplier supplier = new Supplier();
		Item item = new Item();
		CustomerAccount customerAccount = new CustomerAccount();
		CustomerAccountCategory customerAccountCategory = new CustomerAccountCategory();
		ItemCatalog itemCatalog = new ItemCatalog();
		ItemGroup itemGroup = new ItemGroup();
		CustomerAccountGroup customerAccountGroup = new CustomerAccountGroup();
		ChannelDistributor channelDistributor = new ChannelDistributor();
		String ct = priceConditionPriceArea.getConditionType();
		if(ct.equals("purchaseFrameworkAgreement")){
			String[] vds = vd.split(",");
			String[] vdd1 = vds[0].split(":");
			if(vdd1.length == 2){
				supplier.setId(vdd1[1]);
				priceConditionPriceArea.setSupplier(supplier);
			}
			String[] vdd2 = vds[1].split(":");
			if(vdd2.length == 2){
				item.setId(vdd2[1]);
				priceConditionPriceArea.setItem(item);
			}
		}else if(ct.equals("saleFrameworkAgreement")){
			String[] vds = vd.split(",");
			String[] vdd1 = vds[0].split(":");
			if(vdd1.length == 2){
				customerAccount.setId(vdd1[1]);
				priceConditionPriceArea.setCustomerAccount(customerAccount);
			}
			String[] vdd2 = vds[1].split(":");
			if(vdd2.length == 2){
				item.setId(vdd2[1]);
				priceConditionPriceArea.setItem(item);
			}
		}else if(ct.equals("customerAccountCategory")){
			String[] vds = vd.split(",");
			String[] vdd1 = vds[0].split(":");
			if(vdd1.length == 2){
				if(StringUtils.isNotEmpty(vdd1[1]) && !vdd1[1].equals("0")){//if(Long.parseLong(vdd1[1]) > 0){
					regional.setId(vdd1[1]);
					priceConditionPriceArea.setRegional(regional);
				}
			}
			String[] vdd2 = vds[1].split(":");
			if(vdd2.length == 2){
				customerAccountCategory.setId(vdd2[1]);
				priceConditionPriceArea.setCustomerAccountCategory(customerAccountCategory);
			}
			String[] vdd3 = vds[2].split(":");
			if(vdd3.length == 2){
				item.setId(vdd3[1]);
				priceConditionPriceArea.setItem(item);
			}
		}else if(ct.equals("itemCategory")){
			String[] vds = vd.split(",");
			String[] vdd1 = vds[0].split(":");
			if(vdd1.length == 2){
				if(StringUtils.isNotEmpty(vdd1[1]) && !vdd1[1].equals("0")){
					regional.setId(vdd1[1]);
					priceConditionPriceArea.setRegional(regional);
				}
			}
			String[] vdd2 = vds[1].split(":");
			if(vdd2.length == 2){
				itemCatalog.setId(vdd2[1]);
				priceConditionPriceArea.setItemCatalog(itemCatalog);
			}
		}else if(ct.equals("itemGroup")){
			String[] vds = vd.split(",");
			String[] vdd1 = vds[0].split(":");
			if(vdd1.length == 2){
				if(StringUtils.isNotEmpty(vdd1[1]) && !vdd1[1].equals("0")){
					regional.setId(vdd1[1]);
					priceConditionPriceArea.setRegional(regional);
				}
			}
			String[] vdd2 = vds[1].split(":");
			if(vdd2.length == 2){
				itemGroup.setId(vdd2[1]);
				priceConditionPriceArea.setItemGroup(itemGroup);
			}
		}else if(ct.equals("customerAccountGroup")){
			String[] vds = vd.split(",");
			String[] vdd1 = vds[0].split(":");
			if(vdd1.length == 2){
				if(StringUtils.isNotEmpty(vdd1[1]) && !vdd1[1].equals("0")){
					regional.setId(vdd1[1]);
					priceConditionPriceArea.setRegional(regional);
				}
			}
			String[] vdd2 = vds[1].split(":");
			if(vdd2.length == 2){
				customerAccountGroup.setId(vdd2[1]);
				priceConditionPriceArea.setCustomerAccountGroup(customerAccountGroup);
			}
		}else if(ct.equals("channelDistributor")){
			String[] vds = vd.split(",");
			String[] vdd1 = vds[0].split(":");
			if(vdd1.length == 2){
				if(StringUtils.isNotEmpty(vdd1[1]) && !vdd1[1].equals("0")){
					regional.setId(vdd1[1]);
					priceConditionPriceArea.setRegional(regional);
				}			
			}
			String[] vdd2 = vds[1].split(":");
			if(vdd2.length == 2){
				channelDistributor.setId(vdd2[1]);
				priceConditionPriceArea.setChannelDistributor(channelDistributor);
			}
			String[] vdd3 = vds[2].split(":");
			if(vdd3.length == 2){
				item.setId(vdd3[1]);
				priceConditionPriceArea.setItem(item);
			}
		}else if(ct.equals("supplier")){
			String[] vds = vd.split(",");
			String[] vdd1 = vds[0].split(":");
			if(vdd1.length == 2){
				if(StringUtils.isNotEmpty(vdd1[1]) && !vdd1[1].equals("0")){
					regional.setId(vdd1[1]);
					priceConditionPriceArea.setRegional(regional);
				}
			}
			String[] vdd2 = vds[1].split(":");
			if(vdd2.length == 2){
				supplier.setId(vdd2[1]);
				priceConditionPriceArea.setSupplier(supplier);
			}
			String[] vdd3 = vds[2].split(":");
			if(vdd3.length == 2){
				item.setId(vdd3[1]);
				priceConditionPriceArea.setItem(item);
			}
		}else if(ct.equals("customerAccount")){
			String[] vds = vd.split(",");
			String[] vdd1 = vds[0].split(":");
			if(vdd1.length == 2){
				if(StringUtils.isNotEmpty(vdd1[1]) && !vdd1[1].equals("0")){
					regional.setId(vdd1[1]);
					priceConditionPriceArea.setRegional(regional);
				}
			}
			String[] vdd2 = vds[1].split(":");
			if(vdd2.length == 2){
				customerAccount.setId(vdd2[1]);
				priceConditionPriceArea.setCustomerAccount(customerAccount);
			}
		}else if(ct.equals("item")){
			String[] vds = vd.split(",");
			String[] vdd1 = vds[0].split(":");
			if(vdd1.length == 2){
				if(StringUtils.isNotEmpty(vdd1[1]) && !vdd1[1].equals("0")){
					regional.setId(vdd1[1]);
					priceConditionPriceArea.setRegional(regional);
				}
			}
			String[] vdd2 = vds[1].split(":");
			if(vdd2.length == 2){
				item.setId(vdd2[1]);
				priceConditionPriceArea.setItem(item);
			}
		}else if(ct.equals("customerAccountAndItem")){
			String[] vds = vd.split(",");
			String[] vdd1 = vds[0].split(":");
			if(vdd1.length == 2){
				if(StringUtils.isNotEmpty(vdd1[1]) && !vdd1[1].equals("0")){
					regional.setId(vdd1[1]);
					priceConditionPriceArea.setRegional(regional);
				}
			}
			String[] vdd2 = vds[1].split(":");
			if(vdd2.length == 2){
				customerAccount.setId(vdd2[1]);
				priceConditionPriceArea.setCustomerAccount(customerAccount);
			}
			String[] vdd3 = vds[2].split(":");
			if(vdd3.length == 2){
				item.setId(vdd3[1]);
				priceConditionPriceArea.setItem(item);
			}
		}
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<PriceConditionPriceArea> getPcpaList() {
		return pcpaList;
	}

	public void setPcpaList(List<PriceConditionPriceArea> pcpaList) {
		this.pcpaList = pcpaList;
	}

	public PriceConditionPriceArea getPriceConditionPriceArea() {
		return priceConditionPriceArea;
	}

	public void setPriceConditionPriceArea(
			PriceConditionPriceArea priceConditionPriceArea) {
		this.priceConditionPriceArea = priceConditionPriceArea;
	}

	public Map<String, String> getPcpaMap() {
		return pcpaMap;
	}

	public void setPcpaMap(Map<String, String> pcpaMap) {
		this.pcpaMap = pcpaMap;
	}

	public List<Regional> getRegionalList() {
		return regionalList;
	}

	public void setRegionalList(List<Regional> regionalList) {
		this.regionalList = regionalList;
	}

	public List<ItemGroup> getItemGroupList() {
		return itemGroupList;
	}

	public void setItemGroupList(List<ItemGroup> itemGroupList) {
		this.itemGroupList = itemGroupList;
	}

	public List<CustomerAccountGroup> getCustomerAccountGroupList() {
		return customerAccountGroupList;
	}

	public void setCustomerAccountGroupList(
			List<CustomerAccountGroup> customerAccountGroupList) {
		this.customerAccountGroupList = customerAccountGroupList;
	}
}
