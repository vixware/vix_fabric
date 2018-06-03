package com.vix.ebusiness.expressFeeRules.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.ebusiness.expressFeeRules.entity.DeliveryArea;
import com.vix.ebusiness.expressFeeRules.entity.ExpressFeeRules;
import com.vix.ebusiness.option.entity.Logistics;

@Controller
@Scope("prototype")
public class ExpressFeeRulesAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String id;
	private String ids;
	private String parentId;

	/**
	 * 快递费用规则
	 */
	private ExpressFeeRules expressFeeRules;
	private List<ExpressFeeRules> expressFeeRulesList;
	/**
	 * 物流公司
	 */
	private List<Logistics> logisticsList;
	/**
	 * 区域
	 */
	private List<DeliveryArea> deliveryAreaList;

	@Override
	public String goList() {
		try {
			expressFeeRulesList = baseHibernateService.findAllByEntityClass(ExpressFeeRules.class);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return GO_LIST;
	}

	public String goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			if (StringUtils.isNotEmpty(parentId)) {
				params.put("logistics.id," + SearchCondition.EQUAL, parentId);
			}
			//处理搜索条件
			String logisticsName = getDecodeRequestParameter("logisticsName");
			String deliveryAreaName = getDecodeRequestParameter("deliveryAreaName");
			if (logisticsName != null && !"".equals(logisticsName)) {
				params.put("logistics.name," + SearchCondition.ANYLIKE, logisticsName);
			}
			if (deliveryAreaName != null && !"".equals(deliveryAreaName)) {
				params.put("deliveryArea.name," + SearchCondition.ANYLIKE, deliveryAreaName);
			}
			Pager pager = getPager();
			//排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			//处理搜索条件
			pager = baseHibernateService.findPagerByHqlConditions(pager, ExpressFeeRules.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			logisticsList = baseHibernateService.findAllByEntityClass(Logistics.class);
			deliveryAreaList = baseHibernateService.findAllByEntityClass(DeliveryArea.class);
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				expressFeeRules = baseHibernateService.findEntityById(ExpressFeeRules.class, id);
			} else {
				expressFeeRules = new ExpressFeeRules();
				if (parentId != null && !"".equals(parentId)) {
					Logistics logistics = baseHibernateService.findEntityById(Logistics.class, parentId);
					expressFeeRules.setLogistics(logistics);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/**
	 * 保存快递费用规则
	 * 
	 * @return
	 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != expressFeeRules.getId() && !"".equals(expressFeeRules.getId())) {
				isSave = false;
			}
			if (expressFeeRules.getLogistics() != null && expressFeeRules.getLogistics().getId() != null && !"".equals(expressFeeRules.getLogistics().getId()) && !"-1".equals(expressFeeRules.getLogistics().getId())) {

			} else {
				expressFeeRules.setLogistics(null);
			}

			if (expressFeeRules.getDeliveryArea() != null && expressFeeRules.getDeliveryArea().getId() != null && !"".equals(expressFeeRules.getDeliveryArea().getId()) && !"-1".equals(expressFeeRules.getDeliveryArea().getId())) {

			} else {
				expressFeeRules.setDeliveryArea(null);
			}

			//处理修改留痕
			billMarkProcessController.processMark(expressFeeRules, updateField);
			baseHibernateService.merge(expressFeeRules);
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
	 * 获取物流树
	 */
	public void findLogisticsTreeToJson() {
		try {
			List<Logistics> logisticsList = new ArrayList<Logistics>();
			logisticsList = baseHibernateService.findAllByEntityClass(Logistics.class);
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			int count = logisticsList.size();
			for (int i = 0; i < count; i++) {
				Logistics org = logisticsList.get(i);
				strSb.append("{id:\"");
				strSb.append(org.getId());
				strSb.append("\",treeType:\"L\"");
				strSb.append(",name:\"");
				strSb.append(org.getName());
				strSb.append("\",open:false,isParent:false}");
				if (i < count - 1) {
					strSb.append(",");
				}
			}
			strSb.append("]");
			renderHtml(strSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String deleteById() {
		try {
			ExpressFeeRules expressFeeRules = baseHibernateService.findEntityById(ExpressFeeRules.class, id);
			if (null != expressFeeRules) {
				baseHibernateService.deleteByEntity(expressFeeRules);
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
				baseHibernateService.batchDelete(ExpressFeeRules.class, delIds);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
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
	 * @return the parentId
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * @param parentId
	 *            the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public ExpressFeeRules getExpressFeeRules() {
		return expressFeeRules;
	}

	public void setExpressFeeRules(ExpressFeeRules expressFeeRules) {
		this.expressFeeRules = expressFeeRules;
	}

	public List<ExpressFeeRules> getExpressFeeRulesList() {
		return expressFeeRulesList;
	}

	public void setExpressFeeRulesList(List<ExpressFeeRules> expressFeeRulesList) {
		this.expressFeeRulesList = expressFeeRulesList;
	}

	public List<Logistics> getLogisticsList() {
		return logisticsList;
	}

	public void setLogisticsList(List<Logistics> logisticsList) {
		this.logisticsList = logisticsList;
	}

	public List<DeliveryArea> getDeliveryAreaList() {
		return deliveryAreaList;
	}

	public void setDeliveryAreaList(List<DeliveryArea> deliveryAreaList) {
		this.deliveryAreaList = deliveryAreaList;
	}

}
