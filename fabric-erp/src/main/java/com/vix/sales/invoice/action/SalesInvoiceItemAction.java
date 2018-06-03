package com.vix.sales.invoice.action;

import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.share.entity.CurrencyType;
import com.vix.sales.invoice.entity.SalesInvoiceItem;

@Controller
@Scope("prototype")
public class SalesInvoiceItemAction extends BaseAction {
	private static final long serialVersionUID = 1L;


	private String id;
	private SalesInvoiceItem salesInvoiceItem;
	private String pageNo;

	/** 跳转至用户修改页面 */
	private List<CurrencyType> currencyTypeList;

	public String goSaveOrUpdate() {
		try {
			currencyTypeList = baseHibernateService
					.findAllByEntityClass(CurrencyType.class);
			if (null != id && !"".equals(id)) {
				salesInvoiceItem = baseHibernateService.findEntityById(
						SalesInvoiceItem.class, id);
				String pageNo = getRequestParameter("pageNo");
				getRequest().setAttribute("pageNo", pageNo);
			} else {
				salesInvoiceItem = new SalesInvoiceItem();
				salesInvoiceItem.setIsTemp("1");
				salesInvoiceItem.setCreateTime(new Date());
				loadCommonData(salesInvoiceItem);
				salesInvoiceItem = baseHibernateService
						.merge(salesInvoiceItem);
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
			if (null != salesInvoiceItem.getId()) {
				isSave = false;
			} else {
				salesInvoiceItem.setCreateTime(new Date());
				loadCommonData(salesInvoiceItem);
			}
			salesInvoiceItem.setIsTemp("0");
			baseHibernateService.merge(salesInvoiceItem);
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
			SalesInvoiceItem pb = baseHibernateService.findEntityById(
					SalesInvoiceItem.class, id);
			if (null != pb) {
				baseHibernateService.deleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
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

	public List<CurrencyType> getCurrencyTypeList() {
		return currencyTypeList;
	}

	public void setCurrencyTypeList(List<CurrencyType> currencyTypeList) {
		this.currencyTypeList = currencyTypeList;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public SalesInvoiceItem getSalesInvoiceItem() {
		return salesInvoiceItem;
	}

	public void setSalesInvoiceItem(SalesInvoiceItem salesInvoiceItem) {
		this.salesInvoiceItem = salesInvoiceItem;
	}
}