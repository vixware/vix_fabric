package com.vix.sales.quotes.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.share.entity.CurrencyType;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.mdm.crm.entity.ContactPerson;
import com.vix.sales.quotes.entity.CustomerizeProduct;
import com.vix.sales.quotes.entity.CustomerizeProductItem;

@Controller
@Scope("prototype")
public class CustomerizeProductAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String id;
	private String customerId;
	private String ids;
	private CustomerizeProduct customerizeProduct;
	private CustomerizeProductItem customerizeProductItem;

	/** 获取列表数据 */
	public String goListContent() {
		try {
			Map<String, Object> params = getParams();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			String projectQuotationSchemeId = getRequestParameter("projectQuotationSchemeId");
			if (null != projectQuotationSchemeId && !"".equals(projectQuotationSchemeId)) {
				params.put("projectQuotationScheme.id," + SearchCondition.EQUAL, Long.parseLong(projectQuotationSchemeId));
			}
			String customerName = getRequestParameter("customerName");
			if (null != customerName && !"".equals(customerName)) {
				customerName = decode(customerName, "UTF-8");
				params.put("customerAccount.name," + SearchCondition.ANYLIKE, customerName);
			}
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), CustomerizeProduct.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goListContent";
	}

	/** 跳转至用户修改页面 */
	private List<CurrencyType> currencyTypeList;

	public String goSaveOrUpdate() {
		try {
			currencyTypeList = baseHibernateService.findAllByEntityClass(CurrencyType.class);
			if (null != id && !"".equals(id)) {
				customerizeProduct = baseHibernateService.findEntityById(CustomerizeProduct.class, id);
			} else {
				customerizeProduct = new CustomerizeProduct();
				customerizeProduct.setIsTemp("1");
				customerizeProduct.setBillDate(new Date());
				customerizeProduct = baseHibernateService.merge(customerizeProduct);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != customerizeProduct.getId()) {
				isSave = false;
			} else {
				customerizeProduct.setCreateTime(new Date());
				loadCommonData(customerizeProduct);
			}
			customerizeProduct.setIsTemp("");
			if (null == customerizeProduct.getSalePerson() || null == customerizeProduct.getSalePerson().getId() || !customerizeProduct.getSalePerson().getId().equals("") || !customerizeProduct.getSalePerson().getId().equals("0")) {
				customerizeProduct.setSalePerson(null);
			}
			baseHibernateService.merge(customerizeProduct);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			CustomerizeProduct p = baseHibernateService.findEntityById(CustomerizeProduct.class, id);
			if (null != p) {
				baseHibernateService.deleteByEntity(p);
				renderText(DELETE_SUCCESS);
			} else {
				renderText(DELETE_FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteCustomerizeProductItemById() {
		try {
			CustomerizeProductItem p = baseHibernateService.findEntityById(CustomerizeProductItem.class, id);
			if (null != p) {
				baseHibernateService.deleteByEntity(p);
				renderText(DELETE_SUCCESS);
			} else {
				renderText(DELETE_FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	private List<ContactPerson> contactPersonList;

	public String loadCustomerContactPerson() {
		try {
			if (null != customerId && !customerId.equals("")) {
				contactPersonList = baseHibernateService.findAllByEntityClassAndAttribute(ContactPerson.class, "customerAccount.id", customerId);
			}
			if (null != id && !"".equals(id)) {
				customerizeProduct = baseHibernateService.findEntityById(CustomerizeProduct.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return "loadCustomerContactPerson";
	}

	public String saveOrUpdateCustomerizeProductItem() {
		boolean isSave = true;
		try {
			if (null != customerizeProductItem.getId()) {
				isSave = false;
			}
			if (null == customerizeProductItem.getItem() || null == customerizeProductItem.getItem().getId() || !customerizeProductItem.getItem().getId().equals("") || !customerizeProductItem.getItem().getId().equals("0")) {
				customerizeProductItem.setItem(null);
			}
			baseHibernateService.merge(customerizeProductItem);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	public String goSaveOrUpdateCustomerizeProductItem() {
		try {
			if (null != id && !"".equals(id)) {
				customerizeProductItem = baseHibernateService.findEntityById(CustomerizeProductItem.class, id);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goSaveOrUpdateCustomerizeProductItem";
	}

	public void getCustomerizeProductItemJson() {
		try {
			String json = "";
			if (null != id && !"".equals(id)) {
				CustomerizeProduct cp = baseHibernateService.findEntityById(CustomerizeProduct.class, id);
				if (null != cp) {
					json = convertListToJson(new ArrayList<CustomerizeProductItem>(cp.getCustomerizeProductItems()), cp.getCustomerizeProductItems().size(), "customerizeProduct");
				}
			}
			if (!"".equals(json)) {
				renderJson(json);
			} else {
				renderJson("{\"total\":0,\"rows\":[]}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<CurrencyType> getCurrencyTypeList() {
		return currencyTypeList;
	}

	public void setCurrencyTypeList(List<CurrencyType> currencyTypeList) {
		this.currencyTypeList = currencyTypeList;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public List<ContactPerson> getContactPersonList() {
		return contactPersonList;
	}

	public void setContactPersonList(List<ContactPerson> contactPersonList) {
		this.contactPersonList = contactPersonList;
	}

	public CustomerizeProduct getCustomerizeProduct() {
		return customerizeProduct;
	}

	public void setCustomerizeProduct(CustomerizeProduct customerizeProduct) {
		this.customerizeProduct = customerizeProduct;
	}

	public CustomerizeProductItem getCustomerizeProductItem() {
		return customerizeProductItem;
	}

	public void setCustomerizeProductItem(CustomerizeProductItem customerizeProductItem) {
		this.customerizeProductItem = customerizeProductItem;
	}
}