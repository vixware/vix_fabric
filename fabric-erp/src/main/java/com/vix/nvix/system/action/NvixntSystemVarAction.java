package com.vix.nvix.system.action;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.security.util.SecurityUtil;
import com.vix.common.tag.util.VixVarTenantUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.system.systemVar.entity.SystemVar;
import com.vix.system.systemVar.service.ISystemVarService;

/**
 * 任务类型设置
 * 
 * @author Administrator
 *
 */
@Controller
@Scope("request")
public class NvixntSystemVarAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private ISystemVarService systemVarService;

	private String varCode;
	private String defaultValue;
	private String id;

	private SystemVar systemVar;

	/** 跳转到列表页面 */
	@Override
	public String goList() {
		return GO_LIST;
	}

	/** 获取列表数据 */
	@SuppressWarnings("unchecked")
	public void getSystemVarJson() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			if (StrUtils.isNotEmpty(defaultValue)) {
				params.put("defaultValue," + SearchCondition.ANYLIKE, decode(defaultValue, "UTF-8"));
			}
			systemVarService.findPagerByHqlConditions(pager, SystemVar.class, params);
			if (pager.getResultList().size() < 10) {
				int listSize = pager.getResultList().size();
				for (int i = 0; i < 10 - listSize; i++) {
					pager.getResultList().add(new SystemVar());
				}
			}
			String[] excludes = {""};
			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				systemVar = systemVarService.findEntityById(SystemVar.class, id);
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
			if (systemVarService.isEntityExist(SystemVar.class, systemVar.getId(), "varCode", systemVar.getVarCode())) {
				setMessage("变量名已经存在！");
				return "update";
			}
			if (null != systemVar.getId()) {
				isSave = false;
			}

			systemVarService.saveSystemVar(systemVar);
			// 保存
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
			systemVarService.deleteById(SystemVar.class, id);
			VixVarTenantUtil.refreshVixTenantVar(SecurityUtil.getCurrentUserTenantId());
			renderText(DELETE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public String getVarCode() {
		return varCode;
	}

	public void setVarCode(String varCode) {
		this.varCode = varCode;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public SystemVar getSystemVar() {
		return systemVar;
	}

	public void setSystemVar(SystemVar systemVar) {
		this.systemVar = systemVar;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
}