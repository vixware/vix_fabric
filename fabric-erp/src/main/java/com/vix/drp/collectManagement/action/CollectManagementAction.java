package com.vix.drp.collectManagement.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.id.VixUUID;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.drp.collectManagement.controller.CollectManagementController;
import com.vix.drp.collectManagement.entity.CollectBill;

/**
 * 回款记录单管理
 * 
 * @author zhanghaibing
 * 
 * @date 2013-11-5
 */
@Controller
@Scope("prototype")
public class CollectManagementAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String id;
	private String ids;
	private String pageNo;
	/** 回款记录 */
	private CollectBill collectBill;

	private List<CollectBill> collectBillList;
	@Autowired
	private CollectManagementController collectManagementController;

	/**
	 * 跳转到数据列表页面
	 */
	@Override
	public String goList() {
		try {
			collectBillList = collectManagementController.doListCollectBillIndex();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			//排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			String name = getDecodeRequestParameter("name");
			if (name != null && !"".equals(name)) {
				params.put("salesOrder.outerId," + SearchCondition.ANYLIKE, name.trim());
			}
			// 根据状态查询
			String status = getRequestParameter("status");
			if (status != null && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 最近使用
			String days = getRequestParameter("days");
			if (days != null && !"".equals(days)) {
				params.put("updateTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(days));
			}
			pager = collectManagementController.doListCollectBill(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				collectBill = collectManagementController.doListCollectBillById(id);
			} else {
				collectBill = new CollectBill();
				collectBill.setCode(VixUUID.createCode(8));
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
			if (null != collectBill.getId() && !"".equals(collectBill.getId())) {
				isSave = false;
			}
			//处理修改留痕
			billMarkProcessController.processMark(collectBill, updateField);
			collectManagementController.doSaveCollectBill(collectBill);
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
			CollectBill cd = collectManagementController.doListCollectBillById(id);
			if (null != cd) {
				collectManagementController.doDeleteByEntity(cd);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 批量处理删除操作 */
	public String deleteByIds() {
		try {
			if (StringUtils.isNotEmpty(ids)) {
				List<String> delIds = new ArrayList<String>();
				for (String idStr : ids.split(",")) {
					if (null != idStr && !"".equals(idStr) && !"undefined".equals(idStr)) {
						delIds.add(idStr);
					}
				}
				collectManagementController.doDeleteByIds(delIds);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	// 跳转到选择会员卡页面
	public String goSalesOrder() {
		return "goSalesOrder";
	}

	public String goSalesOrderList() {
		try {
			Map<String, Object> params = getParams();
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			Pager pager = collectManagementController.doListSalesOrder(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSalesOrderList";
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the ids
	 */
	public String getIds() {
		return ids;
	}

	/**
	 * @param ids
	 *            the ids to set
	 */
	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public CollectBill getCollectBill() {
		return collectBill;
	}

	public void setCollectBill(CollectBill collectBill) {
		this.collectBill = collectBill;
	}

	public List<CollectBill> getCollectBillList() {
		return collectBillList;
	}

	public void setCollectBillList(List<CollectBill> collectBillList) {
		this.collectBillList = collectBillList;
	}

}
