package com.vix.sales.common.action;

import java.util.Date;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.sales.common.entity.SalePersonCategory;

@Controller
@Scope("prototype")
public class SalePersonCategoryAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private String id;
	private SalePersonCategory salePersonCategory;

	/** 获取列表数据 */
	public String goListContent() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), SalePersonCategory.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (null != id && !"".equals(id)) {
				salePersonCategory = baseHibernateService.findEntityById(SalePersonCategory.class, id);
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
			if (null != salePersonCategory.getId()) {
				isSave = false;
			} else {
				salePersonCategory.setCreateTime(new Date());
				loadCommonData(salePersonCategory);
			}
			salePersonCategory = baseHibernateService.merge(salePersonCategory);
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
			SalePersonCategory pb = baseHibernateService.findEntityById(SalePersonCategory.class, id);
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

	public SalePersonCategory getSalePersonCategory() {
		return salePersonCategory;
	}

	public void setSalePersonCategory(SalePersonCategory salePersonCategory) {
		this.salePersonCategory = salePersonCategory;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}