package com.vix.sales.common.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.sales.common.entity.ReturnGoodsType;
import com.vix.sales.common.entity.SalesBillType;
import com.vix.sales.common.entity.SalesBusinessType;
import com.vix.sales.common.entity.SalesPerformanceEvaluationMethod;

@Controller
@Scope("prototype")
public class SalesAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String id;
	private List<SalesBusinessType> salesBusinessTypeList;
	private List<SalesBillType> salesBillTypeList;

	public String goMenuContent() {
		return "goMenuContent";
	}

	// 跳转字典维护界面
	public String goDictionaryEdit() {
		return "dictionaryEdit";
	}

	/** 获取列表数据 */
	public String goListContent() {
		try {
			salesBusinessTypeList = baseHibernateService.findAllByEntityClass(SalesBusinessType.class);
			if (null != salesBusinessTypeList && salesBusinessTypeList.size() < 8) {
				int stepCount = 8 - salesBusinessTypeList.size();
				for (int i = 0; i < stepCount; i++) {
					salesBusinessTypeList.add(new SalesBusinessType());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			String data = getRequestParameter("data");
			if (null != data && !"".equals(data)) {
				String[] cs = data.split(",");
				for (String s : cs) {
					String[] csItem = s.split(":");
					SalesBusinessType salesBusinessType = new SalesBusinessType();
					if (!"Placeholder".equals(csItem[0]) && !"".equals(csItem[0])) {
						salesBusinessType.setId(csItem[0]);
						isSave = false;
					}
					salesBusinessType.setIsDefault(csItem[1]);
					salesBusinessType.setEnable(csItem[2]);
					if (!"Placeholder".equals(csItem[3]) && !"".equals(csItem[3])) {
						salesBusinessType.setName(csItem[3]);
					}
					if (!"Placeholder".equals(csItem[4]) && !"".equals(csItem[4])) {
						salesBusinessType.setMemo(csItem[4]);
					}
					loadCommonData(salesBusinessType);
					baseHibernateService.merge(salesBusinessType);
				}
			}
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

	public String goBillTypeList() {
		return "goBillTypeList";
	}

	/** 获取发货单类型列表数据 */
	public String billTypeListContent() {
		try {
			salesBillTypeList = baseHibernateService.findAllByEntityClass(SalesBillType.class);
			if (null != salesBillTypeList && salesBillTypeList.size() < 8) {
				int stepCount = 8 - salesBillTypeList.size();
				for (int i = 0; i < stepCount; i++) {
					salesBillTypeList.add(new SalesBillType());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goBillTypeListContent";
	}

	/** 处理发货单类型修改操作 */
	public String saveOrUpdateBillType() {
		boolean isSave = true;
		try {
			String data = getRequestParameter("data");
			if (null != data && !"".equals(data)) {
				String[] cs = data.split(",");
				for (String s : cs) {
					String[] csItem = s.split(":");
					SalesBillType salesBillType = new SalesBillType();
					if (!"Placeholder".equals(csItem[0]) && !"".equals(csItem[0])) {
						salesBillType.setId(csItem[0]);
						isSave = false;
					}
					salesBillType.setIsDefault(csItem[1]);
					salesBillType.setEnable(csItem[2]);
					if (!"Placeholder".equals(csItem[3]) && !"".equals(csItem[3])) {
						salesBillType.setName(csItem[3]);
					}
					if (!"Placeholder".equals(csItem[4]) && !"".equals(csItem[4])) {
						salesBillType.setMemo(csItem[4]);
					}
					loadCommonData(salesBillType);
					baseHibernateService.merge(salesBillType);
				}
			}
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

	/**
	 * 退换货单据类型
	 * 
	 */

	public String goReturnGoodsList() {
		return "goReturnGoodsList";
	}

	private List<ReturnGoodsType> returnGoodsTypeList;

	/** 获取退换货单类型列表数据 */
	public String returnGoodsTypeListContent() {
		try {
			returnGoodsTypeList = baseHibernateService.findAllByEntityClass(ReturnGoodsType.class);
			if (null != returnGoodsTypeList && returnGoodsTypeList.size() < 8) {
				int stepCount = 8 - returnGoodsTypeList.size();
				for (int i = 0; i < stepCount; i++) {
					returnGoodsTypeList.add(new ReturnGoodsType());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "returnGoodsTypeListContent";
	}

	/** 处理退换货单类型修改操作 */
	public String saveOrUpdateReturnGoodsType() {
		boolean isSave = true;
		try {
			String data = getRequestParameter("data");
			if (null != data && !"".equals(data)) {
				String[] cs = data.split(",");
				for (String s : cs) {
					String[] csItem = s.split(":");
					ReturnGoodsType returnGoodsType = new ReturnGoodsType();
					if (!"Placeholder".equals(csItem[0]) && !"".equals(csItem[0])) {
						returnGoodsType.setId(csItem[0]);
						isSave = false;
					}
					returnGoodsType.setIsDefault(csItem[1]);
					returnGoodsType.setEnable(csItem[2]);
					if (!"Placeholder".equals(csItem[3]) && !"".equals(csItem[3])) {
						returnGoodsType.setName(csItem[3]);
					}
					if (!"Placeholder".equals(csItem[4]) && !"".equals(csItem[4])) {
						returnGoodsType.setMemo(csItem[4]);
					}
					loadCommonData(returnGoodsType);
					baseHibernateService.merge(returnGoodsType);
				}
			}
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

	/**
	 * 销售业绩考评方式
	 * 
	 */

	public String goSalesPerformanceEvaluationMethodList() {
		return "goSalesPerformanceEvaluationMethodList";
	}

	private List<SalesPerformanceEvaluationMethod> salesPerformanceEvaluationMethodList;

	/** 获取退换货单类型列表数据 */
	public String salesPerformanceEvaluationMethodListContent() {
		try {
			salesPerformanceEvaluationMethodList = baseHibernateService.findAllByEntityClass(SalesPerformanceEvaluationMethod.class);
			if (null != salesPerformanceEvaluationMethodList && salesPerformanceEvaluationMethodList.size() < 8) {
				int stepCount = 8 - salesPerformanceEvaluationMethodList.size();
				for (int i = 0; i < stepCount; i++) {
					salesPerformanceEvaluationMethodList.add(new SalesPerformanceEvaluationMethod());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "salesPerformanceEvaluationMethodListContent";
	}

	/** 处理退换货单类型修改操作 */
	public String saveOrUpdateSalesPerformanceEvaluationMethod() {
		boolean isSave = true;
		try {
			String data = getRequestParameter("data");
			if (null != data && !"".equals(data)) {
				String[] cs = data.split(",");
				for (String s : cs) {
					String[] csItem = s.split(":");
					SalesPerformanceEvaluationMethod spem = new SalesPerformanceEvaluationMethod();
					if (!"Placeholder".equals(csItem[0]) && !"".equals(csItem[0])) {
						spem.setId(csItem[0]);
						isSave = false;
					}
					spem.setIsDefault(csItem[1]);
					spem.setEnable(csItem[2]);
					if (!"Placeholder".equals(csItem[3]) && !"".equals(csItem[3])) {
						spem.setName(csItem[3]);
					}
					if (!"Placeholder".equals(csItem[4]) && !"".equals(csItem[4])) {
						spem.setMemo(csItem[4]);
					}
					loadCommonData(spem);
					baseHibernateService.merge(spem);
				}
			}
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<SalesBusinessType> getSalesBusinessTypeList() {
		return salesBusinessTypeList;
	}

	public void setSalesBusinessTypeList(List<SalesBusinessType> salesBusinessTypeList) {
		this.salesBusinessTypeList = salesBusinessTypeList;
	}

	public List<SalesBillType> getSalesBillTypeList() {
		return salesBillTypeList;
	}

	public void setSalesBillTypeList(List<SalesBillType> salesBillTypeList) {
		this.salesBillTypeList = salesBillTypeList;
	}

	public List<ReturnGoodsType> getReturnGoodsTypeList() {
		return returnGoodsTypeList;
	}

	public void setReturnGoodsTypeList(List<ReturnGoodsType> returnGoodsTypeList) {
		this.returnGoodsTypeList = returnGoodsTypeList;
	}

	public List<SalesPerformanceEvaluationMethod> getSalesPerformanceEvaluationMethodList() {
		return salesPerformanceEvaluationMethodList;
	}

	public void setSalesPerformanceEvaluationMethodList(List<SalesPerformanceEvaluationMethod> salesPerformanceEvaluationMethodList) {
		this.salesPerformanceEvaluationMethodList = salesPerformanceEvaluationMethodList;
	}

}