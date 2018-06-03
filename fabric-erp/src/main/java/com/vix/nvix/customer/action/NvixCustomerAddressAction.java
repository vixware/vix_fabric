package com.vix.nvix.customer.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.crm.base.entity.CustomerAddress;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.nvix.common.base.entity.AddressInfo;
import com.vix.nvix.common.base.service.IVixntBaseService;

@Controller
@Scope("prototype")
public class NvixCustomerAddressAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired 
	private IVixntBaseService vixntBaseService;
	
	private String id;
	private String name;
	private CustomerAddress customerAddress;
	
	/** 获取列表数据  */
	public void goListContent(){
		try {
			Map<String,Object> params = getParams();
			Pager pager = getPager();
			if(null != name && !"".equals(name)){
				name = decode(name, "UTF-8");
				params.put("customerAccount.name,"+SearchCondition.ANYLIKE, name);
			}
			String customerId = getRequestParameter("customerId");
			if(StrUtils.isNotEmpty(customerId)){
				params.put("customerAccount.id,"+SearchCondition.EQUAL, customerId);
				pager = vixntBaseService.findPagerByHqlConditions(pager, CustomerAddress.class, params);
			}
			String[] excludes = { " " };
			renderDataTable(pager, excludes);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private List<AddressInfo> provinceList;
	private List<AddressInfo> cityList;
	private List<AddressInfo> districtList;
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			Map<String, Object> p = getParams();
			p.put("parentAddressInfo.id," + SearchCondition.IS, null);
			provinceList = baseHibernateService.findAllByConditions(AddressInfo.class,p);
			if(StrUtils.isNotEmpty(id) && !id.equals("0")){
				customerAddress = baseHibernateService.findEntityById(CustomerAddress.class,id);
				if(customerAddress != null && customerAddress.getProvince() != null && StrUtils.isNotEmpty(customerAddress.getProvince().getId())){
					cityList = baseHibernateService.findAllByEntityClassAndAttribute(AddressInfo.class, "parentAddressInfo.id", customerAddress.getProvince().getId());
				}
				if(customerAddress != null && customerAddress.getCity() != null && StrUtils.isNotEmpty(customerAddress.getCity().getId())){
					districtList = baseHibernateService.findAllByEntityClassAndAttribute(AddressInfo.class, "parentAddressInfo.id", customerAddress.getCity().getId());
				}
			}else{
				customerAddress = new CustomerAddress();
				String customerId = getRequestParameter("customerId");
				if(StrUtils.isNotEmpty(customerId)){
					CustomerAccount customerAccount = baseHibernateService.findEntityById(CustomerAccount.class, customerId);
					if(customerAccount != null){
						customerAddress.setCustomerAccount(customerAccount);
					}
				}
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
			if(StrUtils.isNotEmpty(customerAddress.getId())){
				isSave = false;
				customerAddress.setUpdateTime(new Date());
			}else{
				customerAddress.setCreateTime(new Date());
				customerAddress.setUpdateTime(new Date());
			}
			customerAddress = vixntBaseService.merge(customerAddress);
			if("1".equals(customerAddress.getIsDefault())){
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("isDefault," + SearchCondition.EQUAL, "1");
				params.put("customerAccount.id," + SearchCondition.EQUAL, customerAddress.getCustomerAccount().getId());
				params.put("id," + SearchCondition.NOEQUAL, customerAddress.getId());
				List<CustomerAddress> customerAddresss = vixntBaseService.findAllDataByConditions(CustomerAddress.class, params);
				if(customerAddresss != null && customerAddresss.size() > 0){
					for (CustomerAddress ht : customerAddresss) {
						ht.setIsDefault("0");
						ht = vixntBaseService.merge(ht);
					}
				}
			}
			if(isSave){
				renderText(SAVE_SUCCESS);
			}else{
				renderText(UPDATE_SUCCESS);
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
			customerAddress = vixntBaseService.findEntityById(CustomerAddress.class, id);
			if(null != customerAddress){
				vixntBaseService.deleteByEntity(customerAddress);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public CustomerAddress getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(CustomerAddress customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AddressInfo> getProvinceList() {
		return provinceList;
	}

	public void setProvinceList(List<AddressInfo> provinceList) {
		this.provinceList = provinceList;
	}

	public List<AddressInfo> getCityList() {
		return cityList;
	}

	public void setCityList(List<AddressInfo> cityList) {
		this.cityList = cityList;
	}

	public List<AddressInfo> getDistrictList() {
		return districtList;
	}

	public void setDistrictList(List<AddressInfo> districtList) {
		this.districtList = districtList;
	}
}
