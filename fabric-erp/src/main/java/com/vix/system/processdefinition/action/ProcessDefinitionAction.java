package com.vix.system.processdefinition.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.system.processdefinition.controller.ProcessDefinitionController;
import com.vix.system.processdefinition.entity.Procedure;

@Controller
@Scope("prototype")
public class ProcessDefinitionAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private ProcessDefinitionController processDefinitionController;

	private String id;
	private String ids;
	private String parentId;
	private String pageNo;
	private String type;
	private String treeType;
	private Procedure procedure;
	private List<Procedure> procedureList;

	/**
	 * 跳转到数据列表页面
	 */
	@Override
	public String goList() {
		try {
			procedureList = processDefinitionController.doListProcedureList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			if (null == id || id.equals("0")) {
			} else {
				params.put("billsCategoryId", id);
			}
			Pager pager = processDefinitionController.doListBillsType(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if (null != id && !"".equals(id)) {
				procedure = processDefinitionController.doListEntityById(id);
			} else {
				procedure = new Procedure();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdate";
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != procedure.getId()) {
				isSave = false;
			}

			processDefinitionController.doSaveProcedure(procedure);
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getTreeType() {
		return treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	public Procedure getProcedure() {
		return procedure;
	}

	public void setProcedure(Procedure procedure) {
		this.procedure = procedure;
	}

	public List<Procedure> getProcedureList() {
		return procedureList;
	}

	public void setProcedureList(List<Procedure> procedureList) {
		this.procedureList = procedureList;
	}

}
