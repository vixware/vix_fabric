package com.vix.system.action.globalvariable;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.utils.CollectionUtils;
import com.vix.system.entity.GlobalVariable;

@Controller
@Scope("prototype")
public class GlobalVariableAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private GlobalVariable globalVariable;

	public void executeDrools() throws Exception {

	}

	public void executeEsper() throws Exception {

	}

	public String goAddOrUpdate() {
		String id = getRequest().getParameter("id");
		if (null != id && !"".equals(id)) {
			try {
				globalVariable = baseHibernateService.findEntityById(GlobalVariable.class, String.valueOf(id));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return GO_SAVE_OR_UPDATE;
	}

	public String deleteById() {
		String id = getRequest().getParameter("id");
		if (null != id && !"".equals(id)) {
			try {
				baseHibernateService.deleteById(GlobalVariable.class, String.valueOf(id));

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return UPDATE;
	}

	public String deleteByIds() {
		String ids = getRequest().getParameter("ids");
		if (null != ids && !"".equals(ids)) {
			try {
				List<String> list = CollectionUtils.convertStringToLongList(ids, ",");
				baseHibernateService.batchDelete(GlobalVariable.class, list);

			} catch (Exception e) {
				e.printStackTrace();

			}
		}
		return UPDATE;
	}

	public String saveOrUpdate() {
		try {
			baseHibernateService.saveOrUpdate(globalVariable);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return UPDATE;
	}

	public GlobalVariable getGlobalVariable() {
		return globalVariable;
	}

	public void setGlobalVariable(GlobalVariable globalVariable) {
		this.globalVariable = globalVariable;
	}
}
