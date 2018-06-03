package com.vix.sales.quotes.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.billtype.BillType;
import com.vix.common.id.VixUUID;
import com.vix.common.share.entity.BillGroupCode;
import com.vix.common.share.entity.Regional;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.sales.quotes.entity.ProjectQuotationScheme;
import com.vix.sales.quotes.entity.ProjectQuotationSchemeItem;
import com.vix.sales.quotes.entity.ProjectQuotationTemplate;
import com.vix.sales.quotes.entity.ProjectQuotationTemplateItem;
import com.vix.system.billTypeManagement.entity.BillsType;

@Controller
@Scope("prototype")
public class ProjectQuotationSchemeAction extends BaseAction {

	private static final long serialVersionUID = 1L;


	private String id;
	private String ids;
	private ProjectQuotationScheme projectQuotationScheme;
	private ProjectQuotationSchemeItem projectQuotationSchemeItem;

	/** 获取列表数据 */
	public String goListContent() {
		try {
			Map<String, Object> params = getParams();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			params.put("isDeleted," + SearchCondition.NOEQUAL, "1");
			String quoteSubject = getRequestParameter("quoteSubject");
			if (null != quoteSubject && !"".equals(quoteSubject)) {
				quoteSubject = decode(quoteSubject, "UTF-8");
				params.put("quoteSubject," + SearchCondition.ANYLIKE,
						quoteSubject);
			}
			Pager pager = baseHibernateService
					.findPagerByHqlConditions(getPager(),
							ProjectQuotationScheme.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goListContent";
	}

	/** 跳转至用户修改页面 */
	private BillGroupCode billGroupCode = new BillGroupCode();// 成组码
	private List<Regional> regionalList;
	private List<BillsType> billsTypeList;

	public String goSaveOrUpdate() {
		try {
			regionalList = baseHibernateService
					.findAllByEntityClass(Regional.class);
			billsTypeList = baseHibernateService
					.findAllByEntityClassAndAttribute(BillsType.class,
							"billsProperty.propertyCode", "SA_QUOTATION");

			if (null != id && !"".equals(id)) {
				projectQuotationScheme = baseHibernateService
						.findEntityById(ProjectQuotationScheme.class, id);
			} else {
				billGroupCode.setBillType(BillType.SAL_QUOTATION);
				billGroupCode.setGroupCode(UUID.randomUUID().toString());
				loadCommonData(billGroupCode);
				billGroupCode = baseHibernateService
						.merge(billGroupCode);
				projectQuotationScheme = new ProjectQuotationScheme();
				projectQuotationScheme.setIsTemp("1");
				projectQuotationScheme.setIsDeleted("0");
				projectQuotationScheme.setCreateTime(new Date());
				loadCommonData(projectQuotationScheme);
				projectQuotationScheme.setGroupCode(billGroupCode
						.getGroupCode());
				projectQuotationScheme.setBillDate(new Date());
				Employee baseEmp = getEmployee();
				if (null != baseEmp) {
					Employee emp = baseHibernateService
							.findEntityById(Employee.class, baseEmp.getId());
					projectQuotationScheme.setSalesMan(emp);
					if (null != emp) {
						projectQuotationScheme.setOrganizationUnit(emp
								.getOrganizationUnit());
						projectQuotationScheme.setSalesOrg(emp
								.getOrganizationUnit());
					}
				}
				projectQuotationScheme = baseHibernateService
						.merge(projectQuotationScheme);
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
			if (null != projectQuotationScheme.getId()) {
				isSave = false;
			} else {
				projectQuotationScheme.setCreateTime(new Date());
				loadCommonData(projectQuotationScheme);
			}
			dealBillGroupCode();
			if(null == projectQuotationScheme.getCustomerAccount() || null == projectQuotationScheme.getCustomerAccount().getId() || "".equals(projectQuotationScheme.getCustomerAccount().getId())){
				projectQuotationScheme.setCustomerAccount(null);
			}
			if (null == projectQuotationScheme.getGroupCode() || "".equals(projectQuotationScheme.getGroupCode())) {
				projectQuotationScheme.setGroupCode(VixUUID.getUUID());
			}
			if (null == projectQuotationScheme.getSalesMan() || null == projectQuotationScheme.getSalesMan().getId() || projectQuotationScheme.getSalesMan().getId().equals("")) {
				projectQuotationScheme.setSalesMan(null);
			}
			if (null == projectQuotationScheme.getOrganizationUnit() || null == projectQuotationScheme.getOrganizationUnit().getId() || projectQuotationScheme.getOrganizationUnit().getId().equals("")) {
				projectQuotationScheme.setOrganizationUnit(null);
			}
			if(null == projectQuotationScheme.getSalesOrg() || null == projectQuotationScheme.getSalesOrg().getId() || "".equals(projectQuotationScheme.getSalesOrg().getId())){
				projectQuotationScheme.setSalesOrg(null);
			}
			if(null == projectQuotationScheme.getRegional() || null == projectQuotationScheme.getRegional().getId() || "".equals(projectQuotationScheme.getRegional().getId()) ){
				projectQuotationScheme.setRegional(null);
			}
			projectQuotationScheme.setIsTemp("");
			baseHibernateService.merge(projectQuotationScheme);
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
			ProjectQuotationScheme p = baseHibernateService
					.findEntityById(ProjectQuotationScheme.class, id);
			if (null != p) {
				p.setIsDeleted("1");
				baseHibernateService.merge(p);
				renderText(DELETE_SUCCESS);
			} else {
				renderText(DELETE_FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	private void dealBillGroupCode() throws Exception {
		if (null != billGroupCode.getId() && !"".equals(billGroupCode.getId())) {
			billGroupCode = baseHibernateService.findEntityById(
					BillGroupCode.class, billGroupCode.getId());
			if (null != billGroupCode) {
				if (null == billGroupCode.getBillCode()
						|| "".equals(billGroupCode.getBillCode())) {
					if (null != projectQuotationScheme
							&& null != projectQuotationScheme.getId()
							&& !projectQuotationScheme.getId().equals("")
							&& !projectQuotationScheme.getId().equals("0")) {
						billGroupCode.setBillCode(projectQuotationScheme
								.getId().toString());
						baseHibernateService.merge(billGroupCode);
					}
				}
			}
		}
	}

	public String convertProjectQuotationTemplateToProjectQuotationScheme() {
		try {
			projectQuotationScheme = new ProjectQuotationScheme();
			billGroupCode.setBillType(BillType.SAL_QUOTATION);
			billGroupCode.setGroupCode(UUID.randomUUID().toString());
			loadCommonData(billGroupCode);
			billGroupCode = baseHibernateService.merge(billGroupCode);
			dealBillGroupCode();
			String projectQuotationTemplateIds = getRequestParameter("projectQuotationTemplateIds");
			if (null != projectQuotationTemplateIds
					&& !"".equals(projectQuotationTemplateIds)) {
				String[] pqtStr = projectQuotationTemplateIds.split(",");
				Set<String> pqtIdSet = new HashSet<String>();
				for (String sqId : pqtStr) {
					if (null != sqId && !"".equals(sqId)) {
						pqtIdSet.add(sqId);
					}
				}
				projectQuotationScheme.setCreateTime(new Date());
				loadCommonData(projectQuotationScheme);
				projectQuotationScheme.setGroupCode(billGroupCode
						.getGroupCode());
				projectQuotationScheme = baseHibernateService
						.merge(projectQuotationScheme);
				for (String pqtId : pqtIdSet) {
					ProjectQuotationTemplate pqt = baseHibernateService
							.findEntityById(ProjectQuotationTemplate.class,
									pqtId);
					projectQuotationScheme.setBizType(pqt.getBizType());
					projectQuotationScheme.setFormType(pqt.getFormType());
					if (null != pqt
							&& pqt.getProjectQuotationTemplateItems().size() > 0) {
						dealProjectQuotationTemplateItem(
								null,
								new ArrayList<ProjectQuotationTemplateItem>(pqt
										.getProjectQuotationTemplateItems()));
					}
				}
				projectQuotationScheme = baseHibernateService
						.merge(projectQuotationScheme);
			} else {
				projectQuotationScheme.setCreateTime(new Date());
				loadCommonData(projectQuotationScheme);
				projectQuotationScheme = baseHibernateService
						.merge(projectQuotationScheme);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	private void dealProjectQuotationTemplateItem(
			ProjectQuotationSchemeItem parentPqti,
			List<ProjectQuotationTemplateItem> listProjectQuotationTemplateItem)
			throws Exception {
		for (int i = 0; i < listProjectQuotationTemplateItem.size(); i++) {
			ProjectQuotationTemplateItem pqti = listProjectQuotationTemplateItem
					.get(i);
			if (pqti.getSubProjectQuotationTemplateItems().size() > 0) {
				ProjectQuotationSchemeItem pqsi = new ProjectQuotationSchemeItem();
				pqsi.setProjectQuotationScheme(projectQuotationScheme);
				pqsi.setParentProjectQuotationSchemeItem(parentPqti);
				if (null != pqti.getItem()) {
					pqsi.setItem(pqti.getItem());
				}
				pqsi.setName(pqti.getName());
				pqsi.setAmount(pqti.getAmount());
				pqsi.setIsTemp("0");
				loadCommonData(pqsi);
				pqsi = baseHibernateService.merge(pqsi);
				dealProjectQuotationTemplateItem(
						pqsi,
						new ArrayList<ProjectQuotationTemplateItem>(pqti
								.getSubProjectQuotationTemplateItems()));
			} else {
				ProjectQuotationSchemeItem pqsi = new ProjectQuotationSchemeItem();
				pqsi.setProjectQuotationScheme(projectQuotationScheme);
				pqsi.setParentProjectQuotationSchemeItem(parentPqti);
				if (null != pqti.getItem()) {
					pqsi.setItem(pqti.getItem());
				}
				pqsi.setName(pqti.getName());
				pqsi.setAmount(pqti.getAmount());
				pqsi.setIsTemp("0");
				loadCommonData(pqsi);
				baseHibernateService.merge(pqsi);
			}
		}
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

	public ProjectQuotationScheme getProjectQuotationScheme() {
		return projectQuotationScheme;
	}

	public void setProjectQuotationScheme(
			ProjectQuotationScheme projectQuotationScheme) {
		this.projectQuotationScheme = projectQuotationScheme;
	}

	public BillGroupCode getBillGroupCode() {
		return billGroupCode;
	}

	public void setBillGroupCode(BillGroupCode billGroupCode) {
		this.billGroupCode = billGroupCode;
	}

	public ProjectQuotationSchemeItem getProjectQuotationSchemeItem() {
		return projectQuotationSchemeItem;
	}

	public void setProjectQuotationSchemeItem(
			ProjectQuotationSchemeItem projectQuotationSchemeItem) {
		this.projectQuotationSchemeItem = projectQuotationSchemeItem;
	}

	public List<Regional> getRegionalList() {
		return regionalList;
	}

	public void setRegionalList(List<Regional> regionalList) {
		this.regionalList = regionalList;
	}

	public List<BillsType> getBillsTypeList() {
		return billsTypeList;
	}

	public void setBillsTypeList(List<BillsType> billsTypeList) {
		this.billsTypeList = billsTypeList;
	}
}