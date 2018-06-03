package com.vix.eam.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.eam.common.action.EamBaseAction;
import com.vix.eam.entity.EqCategory;

@Controller
@Scope("prototype")
public class EqCategoryAction extends EamBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 分类名称 */
	private String catName;
	/** 实体类 */
	private EqCategory eqCategory;

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public EqCategory getEqCategory() {
		return eqCategory;
	}

	public void setEqCategory(EqCategory eqCategory) {
		this.eqCategory = eqCategory;
	}

}
