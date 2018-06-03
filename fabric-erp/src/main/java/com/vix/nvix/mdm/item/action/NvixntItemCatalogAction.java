package com.vix.nvix.mdm.item.action;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.IOUtils;
import org.jxls.reader.ReaderBuilder;
import org.jxls.reader.XLSReader;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.mdm.item.entity.ItemCatalog;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.common.base.template.ExcelTemplate;
import com.vix.system.code.entity.EncodingRulesTableInTheMiddle;

@Controller
@Scope("prototype")
public class NvixntItemCatalogAction extends VixntBaseAction {
	private static final long serialVersionUID = 1L;

	private String searchName;
	private String parentId;
	private String name;
	private String catalogCode;
	private ItemCatalog itemCatalog;

	/** 获取列表数据 */
	@SuppressWarnings("unchecked")
	public void getItemCatalogJson() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			if (StrUtils.isNotEmpty(searchName)) {
				params.put("name," + SearchCondition.ANYLIKE, decode(searchName, "UTF-8"));
			}
			if (StrUtils.isNotEmpty(catalogCode)) {
				params.put("codeRule," + SearchCondition.EQUAL, catalogCode);
			}
			if (StringUtils.isNotEmpty(parentId) && !parentId.equals("0")) {
				params.put("parentItemCatalog.id," + SearchCondition.EQUAL, parentId);
			} else {
				params.put("parentItemCatalog.id," + SearchCondition.IS, "NULL");
			}
			vixntBaseService.findPagerByHqlConditions(pager, ItemCatalog.class, params);
			if (pager.getResultList().size() < 10) {
				int listSize = pager.getResultList().size();
				for (int i = 0; i < 10 - listSize; i++) {
					pager.getResultList().add(new ItemCatalog());
				}
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				itemCatalog = vixntBaseService.findEntityById(ItemCatalog.class, id);
				String pageNo = getRequestParameter("pageNo");
				getRequest().setAttribute("pageNo", pageNo);
			} else {
				if (StringUtils.isNotEmpty(parentId)) {
					itemCatalog = new ItemCatalog();
					ItemCatalog ic = vixntBaseService.findEntityById(ItemCatalog.class, parentId);
					itemCatalog.setParentItemCatalog(ic);
				}
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
			if (null != itemCatalog.getId()) {
				isSave = false;
			}
			if (null == itemCatalog.getParentItemCatalog() || null == itemCatalog.getParentItemCatalog().getId() || "".equals(itemCatalog.getParentItemCatalog().getId())) {
				itemCatalog.setParentItemCatalog(null);
			} else {
				itemCatalog.setParentCode(itemCatalog.getParentItemCatalog().getCodeRule());
			}
			itemCatalog.setFlag("0");
			itemCatalog = vixntBaseService.merge(itemCatalog);
			if (!"1".equals(itemCatalog.getFlag())) {
				List<ItemCatalog> itemCatalogList = new ArrayList<ItemCatalog>();
				itemCatalogList.add(itemCatalog);
				// uploadItemCatalog(itemCatalogList, "A");
			}

			updateEncod(itemCatalog);

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

	/**
	 * 
	 */

	/** 处理删除操作 */
	public void deleteById() {
		try {
			ItemCatalog pb = vixntBaseService.findEntityById(ItemCatalog.class, id);
			if (null != pb) {
				if (pb.getSubItemCatalogs() != null && pb.getSubItemCatalogs().size() > 0) {
					renderText("含有子分类的分类不能删除!");
				} else if (pb.getItems() != null && pb.getItems().size() > 0) {
					renderText("含有商品的分类不能删除!");
				} else {
					vixntBaseService.deleteByEntity(pb);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	/** 树形结构JSON */
	public void findTreeToJson() {
		try {
			List<ItemCatalog> listItemCatalog = new ArrayList<ItemCatalog>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (null != id && !"".equals(id)) {
				listItemCatalog = vixntBaseService.findAllSubEntity(ItemCatalog.class, "parentItemCatalog.id", id, params);
			} else {
				listItemCatalog = vixntBaseService.findAllSubEntity(ItemCatalog.class, "parentItemCatalog.id", null, params);
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
				strSb.append("\",open:false,isParent:true");
				// loadAllItemCatalog(strSb, new
				// ArrayList<ItemCatalog>(ic.getSubItemCatalogs()));
				strSb.append("}");
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

	public void importFile() {
		FileInputStream fis = null;
		try {
			if (fileToUpload == null) {
			} else {
				try (InputStream xmlInputStream = ExcelTemplate.class.getResourceAsStream("itemcatalog_template.xml")) {
					XLSReader reader = ReaderBuilder.buildFromXML(xmlInputStream);
					try (InputStream xlsInputStream = new FileInputStream(fileToUpload)) {
						List<ItemCatalog> itemCatalogList = new ArrayList<ItemCatalog>();
						Map<String, Object> beans = new HashMap<String, Object>();
						beans.put("itemCatalogList", itemCatalogList);
						reader.read(xlsInputStream, beans);
						List<ItemCatalog> catalogList = new ArrayList<ItemCatalog>();
						if (itemCatalogList != null && itemCatalogList.size() > 0) {
							for (ItemCatalog itemCatalog : itemCatalogList) {
								ItemCatalog catalog = vixntBaseService.findEntityByAttribute(ItemCatalog.class, "codeRule", itemCatalog.getCodeRule());
								if (catalog != null) {
									catalogList.add(catalog);
								} else {
									if (StringUtils.isNotEmpty(itemCatalog.getParentCode())) {
										ItemCatalog i = vixntBaseService.findEntityByAttribute(ItemCatalog.class, "codeRule", itemCatalog.getParentCode());
										itemCatalog.setParentItemCatalog(i);
									}
									initEntityBaseController.initEntityBaseAttribute(itemCatalog);
									itemCatalog = vixntBaseService.merge(itemCatalog);
									updateEncod(itemCatalog);
									catalogList.add(itemCatalog);
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				IOUtils.closeQuietly(fis);
			}
		}
	}

	/**
	 * 自动生成商品编码规则
	 * 
	 * @param itemCatalog
	 * @throws Exception
	 */
	private void updateEncod(ItemCatalog itemCatalog) throws Exception {
		EncodingRulesTableInTheMiddle encodingRulesTableInTheMiddle = new EncodingRulesTableInTheMiddle();
		initEntityBaseController.initEntityBaseAttribute(encodingRulesTableInTheMiddle);
		encodingRulesTableInTheMiddle.setBillType(itemCatalog.getCodeRule());
		encodingRulesTableInTheMiddle.setCodeLength(10);
		encodingRulesTableInTheMiddle.setEnableSeries(2);
		encodingRulesTableInTheMiddle.setCodeType("C");
		encodingRulesTableInTheMiddle.setSerialNumberBegin(1l);
		encodingRulesTableInTheMiddle.setSequenceID(itemCatalog.getCodeRule());
		encodingRulesTableInTheMiddle.setSerialNumberEnd(999999999L);
		encodingRulesTableInTheMiddle.setSerialNumberStep(1);
		vixntBaseService.merge(encodingRulesTableInTheMiddle);
	}

	public void importFile1() {
		FileInputStream fis = null;
		try {
			if (fileToUpload == null) {
				return;
			}
			try (InputStream xmlInputStream = ExcelTemplate.class.getResourceAsStream("itemcatalog_template.xml")) {
				XLSReader reader = ReaderBuilder.buildFromXML(xmlInputStream);
				try (InputStream xlsInputStream = new FileInputStream(fileToUpload)) {
					List<ItemCatalog> itemCatalogList = new ArrayList<ItemCatalog>();
					Map<String, Object> beans = new HashMap<String, Object>();
					beans.put("itemCatalogList", itemCatalogList);
					reader.read(xlsInputStream, beans);
					List<ItemCatalog> catalogList = new ArrayList<ItemCatalog>();
					if (itemCatalogList != null && itemCatalogList.size() > 0) {
						Map<String, String> pmap1 = new HashMap<String, String>();
						Map<String, String> pmap2 = new HashMap<String, String>();
						Map<String, String> pmap3 = new HashMap<String, String>();
						for (int i = 0; i < itemCatalogList.size(); i++) {
							pmap1.put(itemCatalogList.get(i).getParentCode(), itemCatalogList.get(i).getParentCode());
							pmap2.put(itemCatalogList.get(i).getCodeRule(), itemCatalogList.get(i).getParentCode());
							pmap3.put(itemCatalogList.get(i).getName(), itemCatalogList.get(i).getCodeRule());
						}
						List<String> plist = new ArrayList<String>();
						for (Map.Entry<String, String> e : pmap1.entrySet()) {
							plist.add(e.getKey());
						}
						for (int i = 0; i < plist.size(); i++) {
							ItemCatalog catalog = vixntBaseService.findEntityByAttribute(ItemCatalog.class, "name", plist.get(i));
							if (catalog != null) {
								catalogList.add(catalog);
								updateEncod(catalog);
							} else {
								catalog = new ItemCatalog();
								catalog.setParentCode("");
								catalog.setName(plist.get(i));
								catalog.setCodeRule(String.format("%02d", i + 1)); //
								initEntityBaseController.initEntityBaseAttribute(catalog); //
								catalog = vixntBaseService.merge(catalog);
								updateEncod(catalog);
								catalogList.add(catalog);
							}
						}
						Map<String, List<String>> cataLogMap = new HashMap<String, List<String>>();
						cataLogMap.putAll(dealMap(pmap1, pmap2));
						dealCataLogMap(cataLogMap, catalogList);

						Map<String, List<String>> cataLogMap1 = new HashMap<String, List<String>>();
						cataLogMap1.putAll(dealMap(pmap2, pmap3));
						dealCataLogMap(cataLogMap1, catalogList);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				IOUtils.closeQuietly(fis);
			}
		}
	}

	/**
	 * @param catalogList
	 * @param cataLogMap
	 * @throws Exception
	 */
	private void dealCataLogMap(Map<String, List<String>> cataLogMap, List<ItemCatalog> catalogList) throws Exception {
		for (Map.Entry<String, List<String>> e : cataLogMap.entrySet()) {
			List<String> eList = e.getValue();
			for (int i = 0; i < eList.size(); i++) {
				System.out.println(e.getKey() + ":" + eList.get(i) + ":" + String.format("%02d", i + 1));
				ItemCatalog catalog = vixntBaseService.findEntityByAttribute(ItemCatalog.class, "name", eList.get(i));
				if (catalog != null) {
					catalogList.add(catalog);
					updateEncod(catalog);
				} else {
					catalog = new ItemCatalog();
					ItemCatalog c = vixntBaseService.findEntityByAttribute(ItemCatalog.class, "name", e.getKey());
					if (c != null) {
						catalog.setParentItemCatalog(c);
						catalog.setParentCode(c.getCodeRule());
					}
					catalog.setName(eList.get(i));
					catalog.setCodeRule(c.getCodeRule() + String.format("%02d", i + 1));
					initEntityBaseController.initEntityBaseAttribute(catalog);
					catalog = vixntBaseService.merge(catalog);
					updateEncod(catalog);
					catalogList.add(catalog);
				}
			}
		}
	}

	public Map<String, List<String>> dealMap(Map<String, String> pmap1, Map<String, String> pmap2) {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		for (Map.Entry<String, String> entry : pmap1.entrySet()) {
			List<String> sList = new ArrayList<String>();
			for (Map.Entry<String, String> entry1 : pmap2.entrySet()) {
				if (entry.getKey() == entry1.getValue()) {
					sList.add(entry1.getKey());
				}
			}
			map.put(entry.getKey(), sList);
		}
		return map;
	}

	/**
	 * 下载商品分类模板
	 * 
	 * @return
	 */
	public String downloadTemplate() {
		try {
			String fileName = "itemcatalog_template.xlsx";
			setOriFileName(fileName);
			InputStream xmlInputStream = ExcelTemplate.class.getResourceAsStream(fileName);
			setInputStream(xmlInputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "downloadTemplate";
	}

	public String goChooseItemCatalog() {
		return "goChooseItemCatalog";
	}

	public String goChooseMultiItemCatalog() {
		return "goChooseMultiItemCatalog";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getCatalogCode() {
		return catalogCode;
	}

	public void setCatalogCode(String catalogCode) {
		this.catalogCode = catalogCode;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
}