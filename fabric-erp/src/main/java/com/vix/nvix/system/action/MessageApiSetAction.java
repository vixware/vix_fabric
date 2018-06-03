package com.vix.nvix.system.action;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.share.entity.EcMessageConfig;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.nvix.common.base.action.VixntBaseAction;

@Controller
@Scope("prototype")
public class MessageApiSetAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;

	private EcMessageConfig ecMessageConfig;

	private String id;

	public void goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			String msgType = getDecodeRequestParameter("msgType");
			if (StringUtils.isNotEmpty(msgType)) {
				params.put("msgType," + SearchCondition.ANYLIKE, msgType);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, EcMessageConfig.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				ecMessageConfig = vixntBaseService.findEntityById(EcMessageConfig.class, id);
			}
			if (ecMessageConfig != null) {
			} else {
				ecMessageConfig = new EcMessageConfig();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdate";
	}

	public void saveOrUpdate() {

		boolean isSave = true;
		try {
			initEntityBaseController.initEntityBaseAttribute(ecMessageConfig);
			ecMessageConfig = vixntBaseService.merge(ecMessageConfig);
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
	}

	public void deleteById() {
		try {
			EcMessageConfig ecMessageConfig = vixntBaseService.findEntityById(EcMessageConfig.class, id);
			if (null != ecMessageConfig) {
				vixntBaseService.deleteByEntity(ecMessageConfig);
				renderText(DELETE_SUCCESS);
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

	public EcMessageConfig getEcMessageConfig() {
		return ecMessageConfig;
	}

	public void setEcMessageConfig(EcMessageConfig ecMessageConfig) {
		this.ecMessageConfig = ecMessageConfig;
	}

}