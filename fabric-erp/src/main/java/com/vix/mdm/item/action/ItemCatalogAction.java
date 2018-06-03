package com.vix.mdm.item.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.mdm.item.entity.ItemCatalog;
import com.vix.mdm.item.service.IItemCatalogService;

@Controller
@Scope("prototype")
public class ItemCatalogAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private IItemCatalogService itemCatalogService;
	private String id;
	private String parentId;
	private String name;
	private ItemCatalog itemCatalog;
	private String pageNo;

	/** 获取列表数据 */
	public String goListContent() {
		try {
			Map<String, Object> params = getParams();
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			if (null != name && !"".equals(name)) {
				name = decode(name, "UTF-8");
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			if (StringUtils.isNotEmpty(parentId) && !parentId.equals("0")) {
				params.put("parentItemCatalog.id," + SearchCondition.EQUAL, parentId);
			} else {
				params.put("parentItemCatalog.id," + SearchCondition.IS, "NULL");
			}
			itemCatalogService.findPagerByHqlConditions(getPager(), ItemCatalog.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goListContent";
	}

	public String goSubSingleList() {
		try {
			Map<String, Object> params = getParams();
			getPager().setPageSize(6);
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			Pager pager = itemCatalogService.findPagerByHqlConditions(getPager(), ItemCatalog.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SUB_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				itemCatalog = itemCatalogService.findEntityById(ItemCatalog.class, id);
				String pageNo = getRequestParameter("pageNo");
				getRequest().setAttribute("pageNo", pageNo);
			} else {
				if (StringUtils.isNotEmpty(parentId) && !parentId.equals("0")) {
					itemCatalog = new ItemCatalog();
					ItemCatalog ic = itemCatalogService.findEntityById(ItemCatalog.class, parentId);
					itemCatalog.setParentItemCatalog(ic);
				}
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
			if (null != itemCatalog.getId()) {
				isSave = false;
			}
			if (null == itemCatalog.getParentItemCatalog() || null == itemCatalog.getParentItemCatalog().getId() || "".equals(itemCatalog.getParentItemCatalog().getId())) {
				itemCatalog.setParentItemCatalog(null);
			}
			itemCatalog = itemCatalogService.merge(itemCatalog);
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
			ItemCatalog pb = itemCatalogService.findEntityById(ItemCatalog.class, id);
			if (null != pb) {
				itemCatalogService.deleteByEntity(pb);
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

	/** 树形结构JSON */
	public void findTreeToJson() {
		try {
			List<ItemCatalog> listItemCatalog = new ArrayList<ItemCatalog>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (null != id && !"".equals(id)) {
				listItemCatalog = itemCatalogService.findAllSubEntity(ItemCatalog.class, "parentItemCatalog.id", id, params);
			} else {
				listItemCatalog = itemCatalogService.findAllSubEntity(ItemCatalog.class, "parentItemCatalog.id", null, params);
			}
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			strSb = loadAllItemCatalog(strSb, listItemCatalog);
			strSb.append("]");
			renderHtml(strSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private StringBuilder loadAllItemCatalog(StringBuilder strSb, List<ItemCatalog> listItemCatalog) throws Exception {
		for (int i = 0; i < listItemCatalog.size(); i++) {
			ItemCatalog ic = listItemCatalog.get(i);
			if (ic.getSubItemCatalogs().size() > 0) {
				strSb.append("{id:\"");
				strSb.append(ic.getId());
				strSb.append("\",name:\"");
				strSb.append(ic.getName());
				strSb.append("\",open:true,isParent:true,children:[");
				loadAllItemCatalog(strSb, new ArrayList<ItemCatalog>(ic.getSubItemCatalogs()));
				strSb.append("]}");
			} else {
				strSb.append("{id:\"");
				strSb.append(ic.getId());
				strSb.append("\",name:\"");
				strSb.append(ic.getName());
				strSb.append("\",open:false,isParent:false}");
			}
			if (i < listItemCatalog.size() - 1) {
				strSb.append(",");
			}
		}
		return strSb;
	}

	/** 选择导入物料的文件 */
	public String chooseItemCatalogFile() {
		return "goChooseItemCatalogFile";
	}

	/** 物料分组导入 */
	public String importItemCatalogFile() {
		try {
			if (null != fileToUpload) {
				int count = itemCatalogService.importProductCategory(fileToUpload, fileToUploadFileName, SecurityUtil.getCurrentUserTenantId());
				if (count > 0) {
					renderJson("物料分组导入更新成功!");
				} else {
					renderJson("未导入物料分组数据!");
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			renderJson("物料分组导入失败!");
		}
		return UPDATE;
	}

	public String goChooseItemCatalog() {
		return "goChooseItemCatalog";
	}

	public String goChooseMultiItemCatalog() {
		return "goChooseMultiItemCatalog";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public ItemCatalog getItemCatalog() {
		return itemCatalog;
	}

	public void setItemCatalog(ItemCatalog itemCatalog) {
		this.itemCatalog = itemCatalog;
	}
}
