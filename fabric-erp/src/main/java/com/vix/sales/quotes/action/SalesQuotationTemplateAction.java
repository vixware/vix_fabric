package com.vix.sales.quotes.action;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.sales.quotes.entity.SalesQuotationTemplate;
import com.vix.sales.quotes.entity.SalesQuotationTemplateItem;

@Controller
@Scope("prototype")
public class SalesQuotationTemplateAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private String id;
	private String quoteSubject;
	private SalesQuotationTemplate salesQuotationTemplate;
	private SalesQuotationTemplateItem salesQuotationTemplateItem;

	/** 获取列表数据 */
	public String goListContent() {
		try {
			Map<String, Object> params = getParams();
			if (null != quoteSubject && !"".equals(quoteSubject)) {
				quoteSubject = decode(quoteSubject, "UTF-8");
				params.put("quoteSubject," + SearchCondition.ANYLIKE, quoteSubject);
			}
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			baseHibernateService.findPagerByHqlConditions(getPager(), SalesQuotationTemplate.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 获取列表数据 */
	public String goSubListContent() {
		try {
			Map<String, Object> params = getParams();
			if (null != quoteSubject && !"".equals(quoteSubject)) {
				quoteSubject = decode(quoteSubject, "UTF-8");
				params.put("quoteSubject," + SearchCondition.ANYLIKE, quoteSubject);
			}
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			getPager().setPageSize(6);
			baseHibernateService.findPagerByHqlConditions(getPager(), SalesQuotationTemplate.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSubListContent";
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (null != id && !"".equals(id) && !"0".equals(id)) {
				salesQuotationTemplate = baseHibernateService.findEntityById(SalesQuotationTemplate.class, id);
			} else {
				salesQuotationTemplate = new SalesQuotationTemplate();
				salesQuotationTemplate.setCreateTime(new Date());
				loadCommonData(salesQuotationTemplate);// 载入数据公司码
				salesQuotationTemplate = baseHibernateService.merge(salesQuotationTemplate);
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
			if (StrUtils.objectIsNotNull(salesQuotationTemplate.getId())) {
				isSave = false;
				salesQuotationTemplate.setUpdateTime(new Date());
			} else {
				salesQuotationTemplate.setCreateTime(new Date());
				loadCommonData(salesQuotationTemplate);
			}
			salesQuotationTemplate.setIsTemp("0");
			salesQuotationTemplate = baseHibernateService.merge(salesQuotationTemplate);
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
			SalesQuotationTemplate pb = baseHibernateService.findEntityById(SalesQuotationTemplate.class, id);
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

	public void getSalesQuotationTemplateItemJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				SalesQuotationTemplate sq = baseHibernateService.findEntityById(SalesQuotationTemplate.class, id);
				if (null != sq) {
					json = convertListToJson(new ArrayList<SalesQuotationTemplateItem>(sq.getSalesQuotationTemplateItems()), sq.getSalesQuotationTemplateItems().size(), "salesQuotationTemplate");
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

	public String saveOrUpdateSalesQuotationTemplateItem() {
		boolean isSave = true;
		try {
			if (null != salesQuotationTemplateItem.getId()) {
				isSave = false;
			}
			baseHibernateService.merge(salesQuotationTemplateItem);
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

	public String goSaveOrUpdateSalesQuotationTemplateItem() {
		try {
			if (null != id && !"".equals(id)) {
				salesQuotationTemplateItem = baseHibernateService.findEntityById(SalesQuotationTemplateItem.class, id);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goSaveOrUpdateSalesQuotationTemplateItem";
	}

	/** 订单子项价格汇总 */
	public void getSalesQuotationTemplateItemTotal() {
		try {
			if (null != id && !"".equals(id)) {
				SalesQuotationTemplate sq = baseHibernateService.findEntityById(SalesQuotationTemplate.class, id);
				if (null != sq) {
					Double totalAmount = 0d;
					for (SalesQuotationTemplateItem sqi : sq.getSalesQuotationTemplateItems()) {
						totalAmount += sqi.getPrice() * sqi.getAmount();
					}
					DecimalFormat df = new DecimalFormat(".##");
					String st = df.format(totalAmount);
					renderJson(st);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goChooseSalesQuotationTemplate() {
		return "goChooseSalesQuotationTemplate";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQuoteSubject() {
		return quoteSubject;
	}

	public void setQuoteSubject(String quoteSubject) {
		this.quoteSubject = quoteSubject;
	}

	public SalesQuotationTemplate getSalesQuotationTemplate() {
		return salesQuotationTemplate;
	}

	public void setSalesQuotationTemplate(SalesQuotationTemplate salesQuotationTemplate) {
		this.salesQuotationTemplate = salesQuotationTemplate;
	}

	public SalesQuotationTemplateItem getSalesQuotationTemplateItem() {
		return salesQuotationTemplateItem;
	}

	public void setSalesQuotationTemplateItem(SalesQuotationTemplateItem salesQuotationTemplateItem) {
		this.salesQuotationTemplateItem = salesQuotationTemplateItem;
	}
}