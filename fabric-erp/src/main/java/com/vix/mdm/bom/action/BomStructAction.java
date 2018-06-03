package com.vix.mdm.bom.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.org.entity.Organization;
import com.vix.common.share.entity.CurrencyType;
import com.vix.core.constant.SearchCondition;
import com.vix.mdm.bom.entity.BomStruct;

@Controller
@Scope("prototype")
public class BomStructAction extends BaseAction {
	private static final long serialVersionUID = 1L;


	private String id;
	private String ids;
	private BomStruct bomStruct;
	private String pageNo;

	/** 获取列表数据 */
	public String goListContent() {
		try {
			Map<String, Object> params = getParams();
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			String companyId = getRequestParameter("companyId");
			if (null != companyId && !"".equals(companyId)) {
				Organization org = baseHibernateService.findEntityById(Organization.class, companyId);
				if (null != org && null != org.getCompanyInnerCode() && !"".equals(org.getCompanyInnerCode())) {
					params.put("companyInnerCode," + SearchCondition.EQUAL, org.getCompanyInnerCode());
					baseHibernateService.findPagerByHqlConditions(getPager(), BomStruct.class, params);
				}
			}
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
			String companyId = getRequestParameter("companyId");
			if (null != companyId && !"".equals(companyId)) {
				Organization org = baseHibernateService.findEntityById(Organization.class, companyId);
				if (null != org && null != org.getCompanyInnerCode() && !"".equals(org.getCompanyInnerCode())) {
					params.put("companyInnerCode," + SearchCondition.EQUAL, org.getCompanyInnerCode());
					baseHibernateService.findPagerByHqlConditions(getPager(), BomStruct.class, params);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SUB_SINGLE_LIST;
	}

	private List<CurrencyType> currencyTypeList;

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			currencyTypeList = baseHibernateService.findAllByEntityClass(CurrencyType.class);
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				bomStruct = baseHibernateService.findEntityById(BomStruct.class, id);
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
			if (null != bomStruct.getId()) {
				isSave = false;
			} else {
				bomStruct.setCreateTime(new Date());
			}
			String companyId = getRequestParameter("companyId");
			if (null != companyId && !"".equals(companyId)) {
				Organization org = baseHibernateService.findEntityById(Organization.class, companyId);
				if (null != org && null != org.getCompanyInnerCode() && !"".equals(org.getCompanyInnerCode())) {
					bomStruct.setCompanyInnerCode(org.getCompanyInnerCode());
					bomStruct = baseHibernateService.merge(bomStruct);
					if (isSave) {
						renderText(SAVE_SUCCESS);
					} else {
						renderText(UPDATE_SUCCESS);
					}
				}
			}
			if (null == getMessage() || "".equals(getMessage())) {
				setMessage("请选择公司!");
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
			BomStruct pb = baseHibernateService.findEntityById(BomStruct.class, id);
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

	public String goChooseBomStruct() {
		return "goChooseBomStruct";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public BomStruct getBomStruct() {
		return bomStruct;
	}

	public void setBomStruct(BomStruct bomStruct) {
		this.bomStruct = bomStruct;
	}

	public List<CurrencyType> getCurrencyTypeList() {
		return currencyTypeList;
	}

	public void setCurrencyTypeList(List<CurrencyType> currencyTypeList) {
		this.currencyTypeList = currencyTypeList;
	}
}
