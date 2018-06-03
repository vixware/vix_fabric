package com.vix.system.operatingparameters.action;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.system.industrymanagement.entity.IndustryManagement;
import com.vix.system.operatingparameters.entity.OperatingParameters;

/**
 * 
 * com.vix.system.operatingparameters.action.OperatingParametersAction
 * 
 * @author zhanghaibing
 * 
 * @date 2014-6-12
 */
@Controller
@Scope("prototype")
public class OperatingParametersAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String id;

	private OperatingParameters operatingParameters;

	private List<OperatingParameters> operatingParametersList;

	@Override
	public String goList() {
		try {
			operatingParametersList = baseHibernateService.findAllByEntityClass(OperatingParameters.class);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), IndustryManagement.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if (null != id && !"".equals(id)) {
			} else {
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			baseHibernateService.deleteById(IndustryManagement.class, id);
			renderText(DELETE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public OperatingParameters getOperatingParameters() {
		return operatingParameters;
	}

	public void setOperatingParameters(OperatingParameters operatingParameters) {
		this.operatingParameters = operatingParameters;
	}

	public List<OperatingParameters> getOperatingParametersList() {
		return operatingParametersList;
	}

	public void setOperatingParametersList(List<OperatingParameters> operatingParametersList) {
		this.operatingParametersList = operatingParametersList;
	}

}
