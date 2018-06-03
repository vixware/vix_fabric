package com.vix.nvix.customer.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.id.VixUUID;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.mdm.crm.entity.StoredValueRuleSet;
import com.vix.mdm.crm.entity.StoredValueRuleSetDetail;
import com.vix.mdm.item.entity.Item;
import com.vix.nvix.common.base.action.VixntBaseAction;

/**
 * 存值规则设定
 * 
 * @类全名 com.vix.nvix.customer.action.StoredValueRuleSetAction
 *
 * @author zhanghaibing
 *
 * @date 2017年10月17日
 */
@Controller
@Scope("prototype")
public class StoredValueRuleSetAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	private String id;
	private String storedValueRuleSetId;
	private StoredValueRuleSet storedValueRuleSet;
	private StoredValueRuleSetDetail storedValueRuleSetDetail;

	/** 获取列表数据 */
	public void goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			String cardType = getDecodeRequestParameter("cardType");
			if(StringUtils.isNotEmpty(cardType)){
				params.put("type,"+SearchCondition.EQUAL, cardType);
			}
			String searchName = getDecodeRequestParameter("searchName");
			if (StringUtils.isNotEmpty(searchName)) {
				params.put("name," + SearchCondition.ANYLIKE, searchName);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, StoredValueRuleSet.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				storedValueRuleSet = vixntBaseService.findEntityById(StoredValueRuleSet.class, id);
			} else {
				storedValueRuleSet = new StoredValueRuleSet();
				storedValueRuleSet.setCode(VixUUID.getUUID());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if (storedValueRuleSet != null && StringUtils.isNotEmpty(storedValueRuleSet.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(storedValueRuleSet);
			storedValueRuleSet = vixntBaseService.merge(storedValueRuleSet);
			if (isSave) {
				renderText("1:"+SAVE_SUCCESS+":"+storedValueRuleSet.getId());
			} else {
				renderText("1:"+"更新成功!"+":"+storedValueRuleSet.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText("0:"+SAVE_FAIL);
			} else {
				renderText("0:"+"更新失败");
			}
		}
	}

	public void deleteStoredValueRuleSetId() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				storedValueRuleSet = vixntBaseService.findEntityById(StoredValueRuleSet.class, id);
				if (null != storedValueRuleSet) {
					vixntBaseService.deleteByEntity(storedValueRuleSet);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	public String goSaveOrUpdateItem() {
		try {
			if (StringUtils.isNotEmpty(id) && !"undefined".equals(id)) {
				storedValueRuleSetDetail = vixntBaseService.findEntityById(StoredValueRuleSetDetail.class, id);
			} else {
				storedValueRuleSetDetail = new StoredValueRuleSetDetail();
				if(StringUtils.isNotEmpty(storedValueRuleSetId)){
					storedValueRuleSet = vixntBaseService.findEntityById(StoredValueRuleSet.class, storedValueRuleSetId);
					storedValueRuleSetDetail.setStoredValueRuleSet(storedValueRuleSet);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateItem";
	}

	public void goStoredValueRuleSetDetailContent() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = getParams();
			// 处理搜索条件
			String selectName = getDecodeRequestParameter("selectName");
			if (StringUtils.isNotEmpty(selectName)) {
				params.put("name," + SearchCondition.ANYLIKE, selectName);
			}
			String storedValueRuleSetId = getDecodeRequestParameter("storedValueRuleSetId");
			if(StringUtils.isNotEmpty(storedValueRuleSetId)){
				params.put("storedValueRuleSet.id,"+SearchCondition.EQUAL, storedValueRuleSetId);
				pager = vixntBaseService.findPagerByHqlConditions(pager, StoredValueRuleSetDetail.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goItemContent() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = getParams();
			// 处理搜索条件
			String selectName = getDecodeRequestParameter("selectName");
			if (StringUtils.isNotEmpty(selectName)) {
				params.put("name," + SearchCondition.ANYLIKE, selectName);
			}
			params.put("isServiceItem,"+SearchCondition.EQUAL, "Y");
			pager = vixntBaseService.findPagerByHqlConditions(pager, Item.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateStoredValueRuleSetDetail() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(storedValueRuleSetDetail.getId())) {
				isSave = false;
			}
			if (StringUtils.isNotEmpty(id)) {
				storedValueRuleSet = vixntBaseService.findEntityById(StoredValueRuleSet.class, id);
				if (storedValueRuleSet != null) {
					storedValueRuleSetDetail.setStoredValueRuleSet(storedValueRuleSet);
				}
				storedValueRuleSetDetail = vixntBaseService.merge(storedValueRuleSetDetail);
			}
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
			e.printStackTrace();
		}
	}
	public void deleteById(){
		try {
			if(StringUtils.isNotEmpty(id)){
				storedValueRuleSetDetail = vixntBaseService.findEntityById(StoredValueRuleSetDetail.class, id);
				if(null != storedValueRuleSetDetail){
					vixntBaseService.deleteByEntity(storedValueRuleSetDetail);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public StoredValueRuleSet getStoredValueRuleSet() {
		return storedValueRuleSet;
	}

	public void setStoredValueRuleSet(StoredValueRuleSet storedValueRuleSet) {
		this.storedValueRuleSet = storedValueRuleSet;
	}

	public String getStoredValueRuleSetId() {
		return storedValueRuleSetId;
	}

	public void setStoredValueRuleSetId(String storedValueRuleSetId) {
		this.storedValueRuleSetId = storedValueRuleSetId;
	}

	public StoredValueRuleSetDetail getStoredValueRuleSetDetail() {
		return storedValueRuleSetDetail;
	}

	public void setStoredValueRuleSetDetail(StoredValueRuleSetDetail storedValueRuleSetDetail) {
		this.storedValueRuleSetDetail = storedValueRuleSetDetail;
	}

}
