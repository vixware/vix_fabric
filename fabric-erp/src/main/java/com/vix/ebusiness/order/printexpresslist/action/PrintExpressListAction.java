package com.vix.ebusiness.order.printexpresslist.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.ebusiness.entity.Order;
import com.vix.ebusiness.entity.OrderBatch;
import com.vix.ebusiness.entity.Shipping;
import com.vix.ebusiness.option.entity.Logistics;
import com.vix.ebusiness.order.printexpresslist.controller.PrintExpressListController;
import com.vix.ebusiness.order.printexpresslist.hql.PrintExpressListHqlProvider;
import com.vix.logistics.numbers.LogisticsNumbers;
import com.vix.logistics.numbers.LogisticsNumbersGenerator;

import flexjson.JSONSerializer;

/**
 * 打印快递单 com.vix.E_business.order.printexpresslist.action.PrintExpressListAction
 *
 * @author bjitzhang
 *
 * @date 2014年8月21日
 */
@Controller
@Scope("prototype")
public class PrintExpressListAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(PrintExpressListAction.class);
	@Autowired
	private PrintExpressListController printExpressListController;
	private String id;
	private String orderId;
	private Integer printIndex;
	private String ids;
	private String pageNo;
	/* 销售订单 */
	private Shipping shipping;
	private List<Shipping> shippingList;

	private String orderBatchId;
	private String businussOrderId;
	private String logisticsId;
	//快递公司
	private List<Logistics> logisticsList;
	//outSid 起始 快递单号
	private String outSid;
	@Resource(name = "printExpressListHqlProvider")
	private PrintExpressListHqlProvider printExpressListHqlProvider;

	@Override
	public String goList() {
		try {
			Map<String, Object> params = getParams();
			shippingList = printExpressListController.doListShipping(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/**
	 * 根据条件查询入库单信息
	 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			params.put("orderBatch.id," + SearchCondition.EQUAL, orderBatchId);
			//获取未打印的快递单  isPrint 值为0
			Pager pager = getPager();
			//排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			params.put("isPrint," + SearchCondition.NOEQUAL, 1);
			if (null != pageNo && !"".equals(pageNo)) {
				pager.setPageNo(Integer.parseInt(pageNo));
			}
			pager = printExpressListController.doListShipping(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goPrintList() {

		return "goPrintList";
	}

	/**
	 * 回写快递单号 ,更改快递单打印状态
	 * 
	 * @return
	 */
	/*	public String goUpdateOutSid() {
			try {
				Shipping shipping = printExpressListController.doListShippingById(id);
				if (shipping != null) {
					Map<String, String> logisticsMap = new HashMap<String, String>();
					if (getSession().getAttribute("logistics") != null) {
						logisticsMap = (Map<String, String>) getSession().getAttribute("logistics");
						//回写快递单号到快递单
						if (logisticsMap.containsKey(shipping.getId())) {
							shipping.setOutSid(logisticsMap.get(shipping.getId()));
							//快递单已打印
							shipping.setIsPrint(1);
							shipping = printExpressListController.doSaveShipping(shipping);
							Order order = shipping.getOrder();
							if (order != null) {
								//回写快递单号到订单  订单状态
								order.setShippingNo(logisticsMap.get(shipping.getId()));
								order = printExpressListController.doSaveOrder(order);
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return GO_SINGLE_LIST;
		}*/
	/*填写打印顺序号*/
	public String goUpdateShippingPrintIndex() {
		try {
			Shipping shipping = printExpressListController.doListShippingById(id);
			if (printIndex != null && shipping != null) {
				shipping.setPrintPage(printIndex);
				shipping.setIsPrint(1);
				shipping = printExpressListController.doSaveShipping(shipping);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/**
	 * 更改订单批次状态
	 * 
	 * @return
	 */
	public String goUpdateOrderBatchState() {
		try {
			boolean check = true;
			OrderBatch orderBatch = printExpressListController.doListOrderBatchById(id);
			if (orderBatch != null) {
				if (orderBatch.getSubShppings() != null) {
					for (Shipping shipping : orderBatch.getSubShppings()) {
						if (shipping != null) {
							if (shipping.getIsPrint() != 1) {
								check = false;
							}
						}
					}
				}
				if (check) {
					orderBatch.setStatus("2");
					orderBatch = printExpressListController.doSaveOrderBatch(orderBatch);
					getSession().removeAttribute("logistics");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	//跳转到填写快递单号界面
	public String goUpdateLogisticsNumbers() {
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			logisticsList = printExpressListController.doListLogistics(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goUpdateLogisticsNumbers";
	}

	//回填快递单号
	public String goUpdateShippingOutSid() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			if (StringUtils.isNotEmpty(orderBatchId)) {
				params.put("orderBatch.id," + SearchCondition.EQUAL, orderBatchId);
			}
			List<Shipping> shippingList = baseHibernateService.findAllByConditions(Shipping.class, params);
			if (shippingList != null && shippingList.size() > 0) {
				if (StringUtils.isNotEmpty(logisticsId)) {
					Logistics logistics = printExpressListController.doListLogisticsById(logisticsId);
					if (logistics != null) {
						String logisticsCode = logistics.getCode();
						LogisticsNumbers logisticsNumbers = LogisticsNumbersGenerator.getGenerator(logisticsCode);
						List<String> logisticsCodeList = logisticsNumbers.createNumners(outSid, shippingList.size(), true);
						for (int i = 0; i < logisticsCodeList.size(); i++) {
							for (Shipping shipping : shippingList) {
								if (shipping != null) {
									if (shipping.getPrintIndex() != null && i == (shipping.getPrintIndex() - 1)) {
										String shippingNo = logisticsCodeList.get(i);
										shipping.setOutSid(shippingNo);
										shipping = printExpressListController.doSaveShipping(shipping);
										//更新订单的快递单号
										Order order = printExpressListController.doListOrderById(shipping.getOrder().getId());
										order.setShippingNo(shippingNo);
										order = printExpressListController.doSaveOrder(order);
										setMessage("填写完成");
									}
								}
							}
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return UPDATE;
	}

	//批量打印快递单
	public void goPrintExpressList() {
		try {
			String json = "";
			Map<String, Object> params = new HashMap<String, Object>();
			if (StringUtils.isNotEmpty(orderBatchId)) {
				params.put("orderBatch.id," + SearchCondition.EQUAL, orderBatchId);
			}
			List<Shipping> shippingList = printExpressListController.doListShipping(params);
			json = convertListToJson(shippingList, shippingList.size());
			if (!"".equals(json)) {
				renderHtml(json);
			} else {
				renderHtml("{\"total\":0,\"rows\":[]}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goPrintSingleExpressList() {
		try {
			Shipping shipping = printExpressListController.doListShippingById(orderId);
			String json = new JSONSerializer().exclude("class").serialize(shipping);
			renderJson(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*	public void goPrintExpressList() {
			try {
				Map<String, String> logisticsMap = new HashMap<String, String>();
				String json = "";
				Map<String, Object> params = new HashMap<String, Object>();
				if (ids != null && !"".equals(ids)) {
					params.put("id," + SearchCondition.IN, ids);
				} else if (orderBatchId != null && !"".equals(orderBatchId)) {
					params.put("orderBatch.id," + SearchCondition.EQUAL, orderBatchId);
				}
				List<Shipping> shippingList = printExpressListController.doListShipping(params);
				List<Shipping> sList = new ArrayList<Shipping>();
				if (shippingList != null && shippingList.size() > 0) {
					if (logisticsId != null && !"".equals(logisticsId)) {
						Logistics logistics = printExpressListController.doListLogisticsById(logisticsId);
						if (logistics != null) {
							String logisticsCode = logistics.getCode();
							LogisticsNumbers logisticsNumbers = LogisticsNumbersGenerator.getGenerator(logisticsCode);
							List<String> logisticsCodeList = logisticsNumbers.createNumners(outSid, shippingList.size(), true);
							if (logisticsCodeList != null && logisticsCodeList.size() > 0) {
								for (int i = 0; i < logisticsCodeList.size(); i++) {
									logisticsMap.put(shippingList.get(i).getId(), logisticsCodeList.get(i));
								}
							}
							//保存快递公司编码到物流单
							for (Shipping shipping : shippingList) {
								if (shipping != null) {
									shipping.setLogisticCode(logisticsCode);
									sList.add(shipping);
								}
							}
						}
						
					}
				}
				getSession().setAttribute("logistics", logisticsMap);
				json = convertListToJson(sList, sList.size());
				if (!"".equals(json)) {
					renderHtml(json);
				} else {
					renderHtml("{\"total\":0,\"rows\":[]}");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	*/
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getOrderBatchId() {
		return orderBatchId;
	}

	public void setOrderBatchId(String orderBatchId) {
		this.orderBatchId = orderBatchId;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getBusinussOrderId() {
		return businussOrderId;
	}

	public void setBusinussOrderId(String businussOrderId) {
		this.businussOrderId = businussOrderId;
	}

	public Shipping getShipping() {
		return shipping;
	}

	public void setShipping(Shipping shipping) {
		this.shipping = shipping;
	}

	public List<Shipping> getShippingList() {
		return shippingList;
	}

	public void setShippingList(List<Shipping> shippingList) {
		this.shippingList = shippingList;
	}

	public String getOutSid() {
		return outSid;
	}

	public void setOutSid(String outSid) {
		this.outSid = outSid;
	}

	public List<Logistics> getLogisticsList() {
		return logisticsList;
	}

	public void setLogisticsList(List<Logistics> logisticsList) {
		this.logisticsList = logisticsList;
	}

	public String getLogisticsId() {
		return logisticsId;
	}

	public void setLogisticsId(String logisticsId) {
		this.logisticsId = logisticsId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Integer getPrintIndex() {
		return printIndex;
	}

	public void setPrintIndex(Integer printIndex) {
		this.printIndex = printIndex;
	}

}
