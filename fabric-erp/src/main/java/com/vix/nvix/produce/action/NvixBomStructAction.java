package com.vix.nvix.produce.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.mdm.bom.entity.BomNode;
import com.vix.mdm.bom.entity.BomStruct;
import com.vix.mdm.item.entity.Item;
import com.vix.nvix.common.base.action.VixntBaseAction;

@Controller
@Scope("prototype")
public class NvixBomStructAction extends VixntBaseAction {
	private static final long serialVersionUID = 1L;


	private String id;
	private String ids;
	private BomStruct bomStruct;

	/** 获取列表数据 */
	public void goListContent() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			String searchName = getDecodeRequestParameter("searchName");
			if(StrUtils.isNotEmpty(searchName)){
				params.put("configItemBomName," + SearchCondition.ANYLIKE, searchName);
			}
			pager = baseHibernateService.findPagerByHqlConditions(pager, BomStruct.class, params);
			String[] excludes = { "" };
			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				bomStruct = baseHibernateService.findEntityById(BomStruct.class, id);
			}else{
				bomStruct = new BomStruct();
				bomStruct.setCreateTime(new Date());
				bomStruct.setVersionDate(new Date());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作 */
	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != bomStruct.getId()) {
				isSave = false;
			} else {
				bomStruct.setCreateTime(new Date());
			}
			bomStruct = baseHibernateService.merge(bomStruct);
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
			if(StrUtils.isNotEmpty(id)){
				BomStruct pb = baseHibernateService.findEntityById(BomStruct.class, id);
				if (null != pb) {
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("bomStruct.id," + SearchCondition.EQUAL, pb.getId());
					List<BomNode> bomNodes = baseHibernateService.findAllByConditions(BomNode.class, params);
					if (bomNodes != null && bomNodes.size() > 0) {
						renderText("含有子节点的结构不能删除!");
					} else {
						vixntBaseService.deleteByEntity(pb);
						renderText(DELETE_SUCCESS);
					}
				} else {
					renderText(DELETE_FAIL);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	public String goChooseBomStruct() {
		return "goChooseBomStruct";
	}
	
	/** 选择物料  */
	public String goChooseItem() {
		return "goChooseItem";
	}
	
	public void getChooseItemJson() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			params.put("isItem," + SearchCondition.NOEQUAL, "T");
			params.put("isDeleted," + SearchCondition.NOEQUAL, "1");
			params.put("itemType," + SearchCondition.EQUAL, "finishedgoods");
			String itemName = getRequestParameter("itemName");
			if (null != itemName && !"".equals(itemName)) {
				itemName = decode(itemName, "UTF-8");
				params.put("name," + SearchCondition.ANYLIKE, itemName);
			}
			String itemCode = getRequestParameter("itemCode");
			if (null != itemCode && !"".equals(itemCode)) {
				itemCode = decode(itemCode, "UTF-8");
				params.put("code," + SearchCondition.ANYLIKE, itemCode);
			}
			String categoryId = getRequestParameter("categoryId");
			if (null != categoryId && !"".equals(categoryId)) {
				params.put("itemCatalog.id," + SearchCondition.EQUAL, categoryId);
			} 
			baseHibernateService.findPagerByHqlConditions(getPager(), Item.class, params);
			renderDataTable(pager);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void goChooseBomStructListContent() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			String searchName = getDecodeRequestParameter("searchName");
			if(StrUtils.isNotEmpty(searchName)){
				params.put("configItemBomName," + SearchCondition.ANYLIKE, searchName);
			}
			pager = baseHibernateService.findPagerByHqlConditions(pager, BomStruct.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
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

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public BomStruct getBomStruct() {
		return bomStruct;
	}

	public void setBomStruct(BomStruct bomStruct) {
		this.bomStruct = bomStruct;
	}
}
