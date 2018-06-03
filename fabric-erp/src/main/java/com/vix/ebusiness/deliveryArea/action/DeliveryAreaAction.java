package com.vix.ebusiness.deliveryArea.action;

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
import com.vix.ebusiness.expressFeeRules.entity.Provinces;

import flexjson.JSONDeserializer;

@Controller
@Scope("prototype")
public class DeliveryAreaAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private String id;
	private String ids;

	/**
	 * 区域
	 */
	private DeliveryArea deliveryArea;
	private List<DeliveryArea> deliveryAreaList;

	@Override
	public String goList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			deliveryAreaList = baseHibernateService.findAllByConditions(DeliveryArea.class, params);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return GO_LIST;
	}

	public String goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			//处理搜索条件
			String code = getRequestParameter("code");
			String name = getDecodeRequestParameter("name");
			if (code != null && !"".equals(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
			}
			if (name != null && !"".equals(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			Pager pager = getPager();
			//排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			//处理搜索条件
			pager = baseHibernateService.findPagerByHqlConditions(pager, DeliveryArea.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				deliveryArea = baseHibernateService.findEntityById(DeliveryArea.class, id);
			} else {
				deliveryArea = new DeliveryArea();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/**
	 * 保存区域
	 * 
	 * @return
	 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != deliveryArea.getId() && !"".equals(deliveryArea.getId())) {
				isSave = false;
			}
			String provincesJson = getRequestParameter("provincesJson");
			List<Provinces> provincesList = null;
			if (provincesJson != null && !"".equals(provincesJson)) {
				provincesList = new JSONDeserializer<List<Provinces>>().use("values", Provinces.class).deserialize(provincesJson);
			}
			//处理修改留痕
			billMarkProcessController.processMark(deliveryArea, updateField);
			deliveryArea = baseHibernateService.merge(deliveryArea);
			if (provincesList != null && provincesList.size() > 0) {
				for (Provinces provinces : provincesList) {
					if (provinces != null) {
						if (deliveryArea != null) {
							provinces.setDeliveryArea(deliveryArea);
							baseHibernateService.merge(provinces);
						}
					}
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

	public void getProvincesJson() {
		try {
			String json = "";
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				DeliveryArea deliveryArea = baseHibernateService.findEntityById(DeliveryArea.class, id);
				if (deliveryArea != null) {
					json = convertListToJson(new ArrayList<Provinces>(deliveryArea.getSubProvincess()), deliveryArea.getSubProvincess().size());
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

	public String deleteById() {
		try {
			DeliveryArea deliveryArea = baseHibernateService.findEntityById(DeliveryArea.class, id);
			if (null != deliveryArea) {
				baseHibernateService.deleteByEntity(deliveryArea);
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
				baseHibernateService.batchDelete(DeliveryArea.class, delIds);
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

	public DeliveryArea getDeliveryArea() {
		return deliveryArea;
	}

	public void setDeliveryArea(DeliveryArea deliveryArea) {
		this.deliveryArea = deliveryArea;
	}

	public List<DeliveryArea> getDeliveryAreaList() {
		return deliveryAreaList;
	}

	public void setDeliveryAreaList(List<DeliveryArea> deliveryAreaList) {
		this.deliveryAreaList = deliveryAreaList;
	}
}
