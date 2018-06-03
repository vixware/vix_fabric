package com.vix.nvix.produce.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
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
import com.vix.mdm.bom.util.BomUnit;
import com.vix.mdm.item.entity.Item;
import com.vix.nvix.common.base.action.VixntBaseAction;

@Controller
@Scope("prototype")
public class NvixBomNodeAction extends VixntBaseAction {
	private static final long serialVersionUID = 1L;

	private String id;
	private String bomStructName;
	private String subject;
	private String parentId;
	private String treeType;
	private BomStruct bomStruct;
	private BomNode bomNode;

	/** 获取列表数据 */
	public void goListContent() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			if(StringUtils.isNotEmpty(bomStructName) && !bomStructName.equals("0")){
				bomStructName = decode(bomStructName, "UTF-8");
				params.put("bomStruct.configItemBomName," + SearchCondition.ANYLIKE, bomStructName);
			}
			if(StringUtils.isNotEmpty(subject) && !subject.equals("0")){
				subject = decode(subject, "UTF-8");
				params.put("subject," + SearchCondition.ANYLIKE, subject);
			}
			if (null != parentId && !"".equals(parentId) && !"undefined".equals(parentId)) {
				if ("S".equals(treeType)) {
					params.put("bomStruct.id," + SearchCondition.EQUAL, parentId);
				} else if ("N".equals(treeType)) {
					params.put("parentBomNode.id," + SearchCondition.EQUAL, parentId);
				}
			} else {
				params.put("parentBomNode.id," + SearchCondition.IS, null);
			}
			pager = baseHibernateService.findPagerByHqlConditions(pager, BomNode.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				bomNode = vixntBaseService.findEntityById(BomNode.class, id);
			} else {
				if (StringUtils.isNotEmpty(parentId) && StrUtils.isNotEmpty(treeType)) {
					bomNode = new BomNode();
					if("S".equals(treeType)){
						BomStruct ic = vixntBaseService.findEntityById(BomStruct.class, parentId);
						bomNode.setBomStruct(ic);
					}else if("N".equals(treeType)){
						BomNode ic = vixntBaseService.findEntityById(BomNode.class, parentId);
						bomNode.setParentBomNode(ic);
					}
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
			if (StrUtils.isNotEmpty(bomNode.getId())) {
				isSave = false;
			}
			if (null == bomNode.getBomStruct() || StrUtils.isEmpty(bomNode.getBomStruct().getId())) {
				bomNode.setBomStruct(null);
			}
			if (null == bomNode.getParentBomNode() || StrUtils.isEmpty(bomNode.getParentBomNode().getId())) {
				bomNode.setParentBomNode(null);
			}
			if (null == bomNode.getItem() || StrUtils.isEmpty(bomNode.getItem().getId())) {
				bomNode.setItem(null);
			}
			bomNode = baseHibernateService.merge(bomNode);
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
			BomNode pb = vixntBaseService.findEntityById(BomNode.class, id);
			if (null != pb) {
				if (pb.getSubBomNodes() != null && pb.getSubBomNodes().size() > 0) {
					renderText("含有子节点的节点不能删除!");
				} else {
					vixntBaseService.deleteByEntity(pb);
					renderText(DELETE_SUCCESS);
				}
			}else{
				renderText(DELETE_FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
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
			params.put("itemType," + SearchCondition.NOEQUAL, "finishedgoods");
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
	
	/**
	 * 获取BOM结构树
	 */
	public void findBomTreeToJson() {
		try {
			loadBom(id, treeType);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 获取BOM树
	 * 
	 * @param nodeId
	 * @param nodeTreeType
	 */
	public void loadBom(String nodeId, String nodeTreeType) {
		try {
			List<BomUnit> orgUnitList = null;
			List<BomStruct> orgList = null;
			if (null != nodeId && !"".equals(nodeId)) {
				if (StringUtils.isNotEmpty(nodeTreeType)) {
					if (nodeTreeType.equals("S") || nodeTreeType.equals("N")) {
						orgUnitList = vixntBaseService.findBomUnitList(nodeTreeType, nodeId);
					}
				}
			} else {
				Map<String, Object> params = new HashMap<String, Object>();
				orgList = vixntBaseService.findAllDataByConditions(BomStruct.class, params);
			}
			if (orgUnitList == null) {
				orgUnitList = new LinkedList<BomUnit>();
			}
			if (orgList != null) {
				for (BomStruct bomStruct : orgList) {
					BomUnit ou1 = new BomUnit(bomStruct.getId(), "S", bomStruct.getConfigItemBomName(), bomStruct.getVersion());
					List<BomNode> bcList = vixntBaseService.findBomNodeList(bomStruct.getId());
					if (bcList != null && bcList.size() > 0) {
						List<BomUnit> bomUnitList = new LinkedList<BomUnit>();
						for (BomNode bomNode : bcList) {
							if (bomNode != null) {
								BomUnit ou2 = new BomUnit(bomNode.getId(), "N", bomNode.getSubject(), bomNode.getLevel().toString());
								bomUnitList.add(ou2);
								List<BomUnit> pList = new LinkedList<BomUnit>();
								if (bomNode.getSubBomNodes() != null && bomNode.getSubBomNodes().size() > 0) {
									for (BomNode node : bomNode.getSubBomNodes()) {
										pList.add(new BomUnit(node.getId(), "N", node.getSubject(), node.getLevel().toString()));
									}
								}
								ou2.setSubBomUnits(pList);
							}
						}
						ou1.setSubBomUnits(bomUnitList);
					}
					orgUnitList.add(ou1);
				}
			}

			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			int count = orgUnitList.size();
			for (int i = 0; i < count; i++) {
				BomUnit bomUnit = orgUnitList.get(i);
				if (bomUnit.getSubBomUnits() != null && bomUnit.getSubBomUnits().size() > 0) {
					strSb.append("{\"id\":\"");
					strSb.append(bomUnit.getId());
					strSb.append("\",\"treeType\":\"");
					strSb.append(bomUnit.getTreeType());
					strSb.append("\",\"name\":\"");
					strSb.append(bomUnit.getTreeName());
					// strSb.append("\",open:false,isParent:true}");
					// strSb.append("\",open:false,isParent:true,nocheck:true,icon:\"../resources/common/css/zTreeStyle/img/diy/1_open.png\"}");
					if ("S".equals(bomUnit.getTreeType())) {
						strSb.append("\",open:false,isParent:true,icon:\"../vixntcommon/base/js/ztree/img/diy/pm_project.png\"}");
					} else {
						strSb.append("\",open:false,isParent:true,icon:\"../vixntcommon/base/js/ztree/img/diy/project_task.png\"}");
					}

				} else {
					strSb.append("{\"id\":\"");
					strSb.append(bomUnit.getId());
					strSb.append("\",\"treeType\":\"");
					strSb.append(bomUnit.getTreeType());
					strSb.append("\",\"name\":\"");
					strSb.append(bomUnit.getTreeName());
					// strSb.append("\",open:false,isParent:false}");
					if ("S".equals(bomUnit.getTreeType())) {
						strSb.append("\",open:false,isParent:false,icon:\"../vixntcommon/base/js/ztree/img/diy/pm_project.png\"}");
					} else {
						strSb.append("\",open:false,isParent:false,icon:\"../vixntcommon/base/js/ztree/img/diy/project_task.png\"}");
					}
				}
				if (i < count - 1) {
					strSb.append(",");
				}
			}
			strSb.append("]");
			renderHtml(strSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 树形结构JSON */
	public void findTreeToJson() {
		try {
			List<BomNode> listBomNode = new ArrayList<BomNode>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			/*if(StringUtils.isNotEmpty(bomStructId) && !bomStructId.equals("0")){
				params.put("bomStruct.id," + SearchCondition.EQUAL, bomStructId);
			}*/
			if (null != id && !"".equals(id)) {
				listBomNode = baseHibernateService.findAllSubEntity(BomNode.class, "parentBomNode.id", id, params);
			} else {
				listBomNode = baseHibernateService.findAllSubEntity(BomNode.class, "parentBomNode.id", null, params);
			}
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			strSb = loadAllBomNode(strSb, listBomNode);
			strSb.append("]");
			renderHtml(strSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private StringBuilder loadAllBomNode(StringBuilder strSb, List<BomNode> listBomNode) throws Exception {
		for (int i = 0; i < listBomNode.size(); i++) {
			BomNode ic = listBomNode.get(i);
			if (ic.getSubBomNodes().size() > 0) {
				strSb.append("{id:\"");
				strSb.append(ic.getId());
				strSb.append("\",name:\"");
				strSb.append(ic.getSubject());
				strSb.append("\",open:true,isParent:true,children:[");
				loadAllBomNode(strSb, new ArrayList<BomNode>(ic.getSubBomNodes()));
				strSb.append("]}");
			} else {
				strSb.append("{id:\"");
				strSb.append(ic.getId());
				strSb.append("\",name:\"");
				strSb.append(ic.getSubject());
				strSb.append("\",open:false,isParent:false}");
			}
			if (i < listBomNode.size() - 1) {
				strSb.append(",");
			}
		}
		return strSb;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getBomStructName() {
		return bomStructName;
	}

	public void setBomStructName(String bomStructName) {
		this.bomStructName = bomStructName;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public BomStruct getBomStruct() {
		return bomStruct;
	}

	public void setBomStruct(BomStruct bomStruct) {
		this.bomStruct = bomStruct;
	}

	public BomNode getBomNode() {
		return bomNode;
	}

	public void setBomNode(BomNode bomNode) {
		this.bomNode = bomNode;
	}

	public String getTreeType() {
		return treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}
	
}