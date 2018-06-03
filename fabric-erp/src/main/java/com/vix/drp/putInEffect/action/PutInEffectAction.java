package com.vix.drp.putInEffect.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.drp.putInEffect.controller.PutInEffectController;
import com.vix.drp.putInEffect.entity.PutInEffect;

@Controller
@Scope("prototype")
public class PutInEffectAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private PutInEffectController putInEffectController;
	private String id;
	private PutInEffect putInEffect;
	private List<PutInEffect> putInEffectList;

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			Pager pager = putInEffectController.findPagerByHqlConditions(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				putInEffect = putInEffectController.doListPutInEffectById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/**
	 * 
	 * @return
	 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != putInEffect.getId() && !"".equals(putInEffect.getId())) {
				isSave = false;
			}
			putInEffect.setIsTemp("");
			putInEffect = putInEffectController.doSavePutInEffect(putInEffect);
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
			PutInEffect putInEffect = putInEffectController.doListPutInEffectById(id);
			if (null != putInEffect) {
				putInEffectController.doDeleteByEntity(putInEffect);
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

	public PutInEffect getPutInEffect() {
		return putInEffect;
	}

	public void setPutInEffect(PutInEffect putInEffect) {
		this.putInEffect = putInEffect;
	}

	public List<PutInEffect> getPutInEffectList() {
		return putInEffectList;
	}

	public void setPutInEffectList(List<PutInEffect> putInEffectList) {
		this.putInEffectList = putInEffectList;
	}

}
