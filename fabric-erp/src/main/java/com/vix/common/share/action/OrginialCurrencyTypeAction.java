package com.vix.common.share.action;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.orginialMeta.entity.OrginialCurrencyType;
import com.vix.core.web.Pager;

@Controller
@Scope("prototype")
public class OrginialCurrencyTypeAction extends BaseAction {
	private static final long serialVersionUID = 1L;


	private String id;
	private OrginialCurrencyType orginialCurrencyType;

	/** 获取列表数据 */
	public String goListContent() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), OrginialCurrencyType.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){//if (null != id && id.longValue() > 0) {
				orginialCurrencyType = baseHibernateService.findEntityById(OrginialCurrencyType.class, id);
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
			if (null != orginialCurrencyType.getId()) {
				isSave = false;
			}
			orginialCurrencyType = baseHibernateService.mergeOriginal(orginialCurrencyType);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(SAVE_SUCCESS);
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
			OrginialCurrencyType pb = baseHibernateService.findEntityById(OrginialCurrencyType.class, id);
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

	public OrginialCurrencyType getOrginialCurrencyType() {
		return orginialCurrencyType;
	}

	public void setOrginialCurrencyType(OrginialCurrencyType orginialCurrencyType) {
		this.orginialCurrencyType = orginialCurrencyType;
	}
}
