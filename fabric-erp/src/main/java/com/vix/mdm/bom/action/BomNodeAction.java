package com.vix.mdm.bom.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.mdm.bom.entity.BomNode;
import com.vix.mdm.bom.entity.BomStruct;

@Controller
@Scope("prototype")
public class BomNodeAction extends BaseAction {
	private static final long serialVersionUID = 1L;


	private String id;
	private String bomStructId;
	private BomStruct bomStruct;
	private BomNode bomNode;
	private String pageNo;

	/** 获取列表数据 */
	public String goListContent() {
		try {
			Map<String, Object> params = getParams();
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			if(StringUtils.isNotEmpty(bomStructId) && !bomStructId.equals("0")){
				bomStruct = baseHibernateService.findEntityById(BomStruct.class, bomStructId);
			}
			String idStr = getRequestParameter("parentId");
			if (null != idStr && !"".equals(idStr) && !"undefined".equals(idStr)) {
				params.put("parentBomNode.id," + SearchCondition.EQUAL, idStr);
			} else {
				params.put("parentBomNode.id," + SearchCondition.IS, null);
			}
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), BomNode.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSubSingleList() {
		try {
			Map<String, Object> params = getParams();
			getPager().setPageSize(6);
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), BomNode.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SUB_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (null != id) {
				bomNode = baseHibernateService.findEntityById(BomNode.class, id);
				String pageNo = getRequestParameter("pageNo");
				getRequest().setAttribute("pageNo", pageNo);
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
			if (null != bomNode.getId()) {
				isSave = false;
			}
			if (null == bomNode.getBomStruct() || null == bomNode.getBomStruct().getId()) {
				bomNode.setBomStruct(null);
			}
			if (null == bomNode.getParentBomNode() || null == bomNode.getParentBomNode().getId()) {
				bomNode.setParentBomNode(null);
			}
			if (null == bomNode.getItem() || null == bomNode.getItem().getId()) {
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
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			BomNode pb = baseHibernateService.findEntityById(BomNode.class, id);
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

	/** 树形结构JSON */
	public void findTreeToJson() {
		try {
			List<BomNode> listBomNode = new ArrayList<BomNode>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if(StringUtils.isNotEmpty(bomStructId) && !bomStructId.equals("0")){
				params.put("bomStruct.id," + SearchCondition.EQUAL, bomStructId);
			}
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
				strSb.append("{id:");
				strSb.append(ic.getId());
				strSb.append(",name:\"");
				strSb.append(ic.getSubject());
				strSb.append("\",open:true,isParent:true,children:[");
				loadAllBomNode(strSb, new ArrayList<BomNode>(ic.getSubBomNodes()));
				strSb.append("]}");
			} else {
				strSb.append("{id:");
				strSb.append(ic.getId());
				strSb.append(",name:\"");
				strSb.append(ic.getSubject());
				strSb.append("\",open:false,isParent:false}");
			}
			if (i < listBomNode.size() - 1) {
				strSb.append(",");
			}
		}
		return strSb;
	}

	public String goChooseParentBomNode() {
		return "goChooseParentBomNode";
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public BomNode getBomNode() {
		return bomNode;
	}

	public void setBomNode(BomNode bomNode) {
		this.bomNode = bomNode;
	}

	public String getBomStructId() {
		return bomStructId;
	}

	public void setBomStructId(String bomStructId) {
		this.bomStructId = bomStructId;
	}

	public BomStruct getBomStruct() {
		return bomStruct;
	}

	public void setBomStruct(BomStruct bomStruct) {
		this.bomStruct = bomStruct;
	}
}
