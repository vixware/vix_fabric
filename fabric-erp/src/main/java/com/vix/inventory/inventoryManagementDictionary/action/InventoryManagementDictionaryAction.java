package com.vix.inventory.inventoryManagementDictionary.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.inventory.inventoryManagementDictionary.entity.InvAttribute;
import com.vix.inventory.inventoryManagementDictionary.entity.InvAttributeValue;

import flexjson.JSONDeserializer;
import flexjson.transformer.DateTransformer;

@Controller
@Scope("prototype")
public class InventoryManagementDictionaryAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(InventoryManagementDictionaryAction.class);
	private String id;
	private String ids;
	private String pageNo;
	/**
	 * 单据分类
	 */
	private InvAttribute invAttribute;
	private List<InvAttribute> invAttributeList;

	@Override
	public String goList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			invAttributeList = baseHibernateService.findAllByConditions(InvAttribute.class, params);
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
			Pager pager = getPager();
			//排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			// code 编码
			String code = getRequestParameter("code");
			if (null != code && !"".equals(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
			}
			if (null != pageNo && !"".equals(pageNo)) {
				pager.setPageNo(Integer.parseInt(pageNo));
			}
			// status 状态
			String status = getRequestParameter("status");
			if (status != null && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			pager = baseHibernateService.findPagerByHqlConditions(pager, InvAttribute.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				invAttribute = baseHibernateService.findEntityById(InvAttribute.class, id);
			} else {
				invAttribute = new InvAttribute();
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
			if (null != invAttribute.getId() && !"".equals(invAttribute.getId())) {
				isSave = false;
			}
			String dlJson = getRequestParameter("dlJson");
			List<InvAttributeValue> invAttributeValueList = new ArrayList<InvAttributeValue>();
			if (dlJson != null && !"".equals(dlJson)) {
				DateTransformer dateTransformer = new DateTransformer("yyyy-MM-dd");
				invAttributeValueList = new JSONDeserializer<List<InvAttributeValue>>().use(Date.class, dateTransformer).use("values", InvAttributeValue.class).deserialize(dlJson);
			}
			invAttribute = baseHibernateService.merge(invAttribute);
			for (InvAttributeValue invAttributeValue : invAttributeValueList) {
				invAttributeValue.setInvAttribute(invAttribute);
				invAttributeValue = baseHibernateService.merge(invAttributeValue);
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

	/** 处理删除操作 */
	public String deleteById() {
		try {
			InvAttribute pb = baseHibernateService.findEntityById(InvAttribute.class, id);
			if (null != pb) {
				baseHibernateService.deleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 获取明细的json数据 */

	public void getBillsPropertyDictionaryJson() {
		try {
			String json = "";
			if (null != id && !"".equals(id)) {
				InvAttribute invAttribute = baseHibernateService.findEntityById(InvAttribute.class, id);
				if (invAttribute != null) {
					json = convertListToJson(new ArrayList<InvAttributeValue>(invAttribute.getInvAttributeValues()), invAttribute.getInvAttributeValues().size());
				}
			}
			if (!"".equals(json)) {
				renderJson(json);
			} else {
				renderJson("{\"total\":0,\"rows\":[]}");
			}
			logger.info("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 批量处理删除操作 */
	public String deleteByIds() {
		try {
			if (null != ids && !"".equals(ids)) {
				List<String> delIds = new ArrayList<String>();
				for (String idStr : ids.split(",")) {
					if (null != idStr && !"".equals(idStr) && !"undefined".equals(idStr)) {
						delIds.add(idStr);
					}
				}
				baseHibernateService.batchDelete(InvAttribute.class, delIds);
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

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	/**
	 * @return the invAttribute
	 */
	public InvAttribute getInvAttribute() {
		return invAttribute;
	}

	/**
	 * @param invAttribute
	 *            the invAttribute to set
	 */
	public void setInvAttribute(InvAttribute invAttribute) {
		this.invAttribute = invAttribute;
	}

	/**
	 * @return the invAttributeList
	 */
	public List<InvAttribute> getInvAttributeList() {
		return invAttributeList;
	}

	/**
	 * @param invAttributeList
	 *            the invAttributeList to set
	 */
	public void setInvAttributeList(List<InvAttribute> invAttributeList) {
		this.invAttributeList = invAttributeList;
	}

}
