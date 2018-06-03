package com.vix.nvix.chain.action;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.id.VixUUID;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.mdm.item.entity.ItemTag;
import com.vix.nvix.common.base.action.VixntBaseAction;

/**
 * 会员标签
 * 
 * @author jackie
 *
 */
@Controller
@Scope("prototype")
public class NvixItemTagAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;

	private String id;
	private ItemTag itemTag;

	public void goSingleList() {
		try {
			Map<String, Object> params = getParams();
			String searchName = getDecodeRequestParameter("searchName");
			if (StringUtils.isNotEmpty(searchName)) {
				params.put("name," + SearchCondition.ANYLIKE, searchName);
			}
			Pager pager = vixntBaseService.findPagerByHqlConditions(getPager(), ItemTag.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				itemTag = vixntBaseService.findEntityById(ItemTag.class, id);
			} else {
				itemTag = new ItemTag();
				itemTag.setCode(VixUUID.createCodeByNumber(10));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(itemTag.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(itemTag);
			itemTag = vixntBaseService.merge(itemTag);
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
			ItemTag itemTag = vixntBaseService.findEntityById(ItemTag.class, id);
			if (null != itemTag) {
				vixntBaseService.deleteByEntity(itemTag);
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

	public ItemTag getItemTag() {
		return itemTag;
	}

	public void setItemTag(ItemTag itemTag) {
		this.itemTag = itemTag;
	}

}
