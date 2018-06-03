package com.vix.nvix.oa.action;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.web.Pager;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.oa.claimsmanagement.areaLevel.entity.AreaLevel;

@Controller
@Scope("prototype")
public class NvixAreaLevelAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;

	private String id;
	private AreaLevel areaLevel;

	public void goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = vixntBaseService.findPagerByHqlConditions(getPager(), AreaLevel.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				areaLevel = vixntBaseService.findEntityById(AreaLevel.class, id);
			} else {
				areaLevel = new AreaLevel();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(areaLevel.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(areaLevel);
			areaLevel = vixntBaseService.merge(areaLevel);
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

	/** 处理删除操作 */
	public void deleteById() {
		try {
			AreaLevel areaLevel = vixntBaseService.findEntityById(AreaLevel.class, id);
			if (null != areaLevel) {
				vixntBaseService.deleteByEntity(areaLevel);
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

	public AreaLevel getAreaLevel() {
		return areaLevel;
	}

	public void setAreaLevel(AreaLevel areaLevel) {
		this.areaLevel = areaLevel;
	}

}
