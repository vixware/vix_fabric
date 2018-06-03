package com.vix.sales.quotes.action;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.billtype.BillType;
import com.vix.common.id.VixUUID;
import com.vix.common.share.entity.BillGroupCode;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.sales.quotes.entity.ProjectQuotationScheme;
import com.vix.sales.quotes.entity.ProjectQuotationSchemeItem;

@Controller
@Scope("prototype")
public class ProjectQuotationSchemeItemAction extends BaseAction {

	private static final long serialVersionUID = 1L;


	private String id;
	private Long projectQuotationSchemeId;
	private ProjectQuotationSchemeItem projectQuotationSchemeItem;

	/** 获取列表数据 */
	public String goListContent() {
		try {
			Map<String, Object> params = getParams();
			String projectQuotationSchemeId = getRequestParameter("projectQuotationSchemeId");
			if (null != projectQuotationSchemeId && !"".equals(projectQuotationSchemeId)) {
				params.put("projectQuotationScheme.id," + SearchCondition.EQUAL,Long.parseLong(projectQuotationSchemeId));
			}
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(),ProjectQuotationSchemeItem.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goListContent";
	}

	/** 跳转至用户修改页面 */
	private BillGroupCode billGroupCode = new BillGroupCode();// 成组码

	public String goSaveOrUpdate() {
		try {
			if (null != id && !"".equals(id)) {
				projectQuotationSchemeItem = baseHibernateService.findEntityById(ProjectQuotationSchemeItem.class, id);
			} else {
				billGroupCode.setBillType(BillType.SAL_QUOTATION);
				billGroupCode.setGroupCode(UUID.randomUUID().toString());
				loadCommonData(billGroupCode);
				billGroupCode = baseHibernateService.merge(billGroupCode);
				projectQuotationSchemeItem = new ProjectQuotationSchemeItem();
				projectQuotationSchemeItem.setIsTemp("1");
				projectQuotationSchemeItem.setCreateTime(new Date());
				loadCommonData(projectQuotationSchemeItem);
				projectQuotationSchemeItem.setGroupCode(billGroupCode.getGroupCode());
				projectQuotationSchemeItem = baseHibernateService.merge(projectQuotationSchemeItem);
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
			if (null != projectQuotationSchemeItem.getId()) {
				isSave = false;
			} else {
				projectQuotationSchemeItem.setCreateTime(new Date());
				loadCommonData(projectQuotationSchemeItem);
			}
			dealBillGroupCode();
			if (null == projectQuotationSchemeItem.getGroupCode() || "".equals(projectQuotationSchemeItem.getGroupCode())) {
				projectQuotationSchemeItem.setGroupCode(VixUUID.getUUID());
			}
			if (null == projectQuotationSchemeItem.getQuotationCharger() || null == projectQuotationSchemeItem.getQuotationCharger().getId() ||
					"".equals(projectQuotationSchemeItem.getQuotationCharger().getId())) {
				projectQuotationSchemeItem.setQuotationCharger(null);
			}
			if (null == projectQuotationSchemeItem.getParentProjectQuotationSchemeItem()
					|| null == projectQuotationSchemeItem.getParentProjectQuotationSchemeItem().getId() || 
					"".equals(projectQuotationSchemeItem.getParentProjectQuotationSchemeItem().getId())) {
				projectQuotationSchemeItem.setParentProjectQuotationSchemeItem(null);
			}
			if(null == projectQuotationSchemeItem.getProjectQuotationScheme() || null == projectQuotationSchemeItem.getProjectQuotationScheme().getId() || "".equals(projectQuotationSchemeItem.getProjectQuotationScheme().getId())){
				projectQuotationSchemeItem.setProjectQuotationScheme(null);
			}
			projectQuotationSchemeItem.setIsTemp("0");
			baseHibernateService.merge(projectQuotationSchemeItem);
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
			ProjectQuotationSchemeItem p = baseHibernateService
					.findEntityById(ProjectQuotationSchemeItem.class, id);
			if (null != p) {
				baseHibernateService.deleteByEntity(p);
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

	public String goChooseQuotationSchemeItem() {
		return "goChooseQuotationSchemeItem";
	}

	/** 订单子项价格汇总 */
	public void getProjectQuotationSchemeItemItemTotal() {
		try {
			if (null != id && !"".equals(id)) {
				ProjectQuotationScheme sq = baseHibernateService
						.findEntityById(ProjectQuotationScheme.class, id);
				if (null != sq) {
					Double totalAmount = 0d;
					for (ProjectQuotationSchemeItem sqi : sq
							.getProjectQuotationSchemeItems()) {
						totalAmount += sqi.getPrice() * sqi.getAmount();
					}
					DecimalFormat df = new DecimalFormat(".##");
					String st = df.format(totalAmount);
					renderJson(st);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 树形结构JSON */
	public void findTreeToJson() {
		try {
			List<ProjectQuotationSchemeItem> listProjectQuotationSchemeItem = new ArrayList<ProjectQuotationSchemeItem>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			if (null != projectQuotationSchemeId && !"".equals(projectQuotationSchemeId)) {
				params.put("projectQuotationScheme.id," + SearchCondition.EQUAL,projectQuotationSchemeId);
			} else {
				params.put("projectQuotationScheme.id," + SearchCondition.EQUAL,0l);
			}
			if (null != id && !"".equals(id)) {
				listProjectQuotationSchemeItem = baseHibernateService.findAllSubEntity(ProjectQuotationSchemeItem.class,
						"parentProjectQuotationSchemeItem.id", id,params);
			} else {
				listProjectQuotationSchemeItem = baseHibernateService
						.findAllSubEntity(ProjectQuotationSchemeItem.class,"parentProjectQuotationSchemeItem.id", null,params);
			}
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			strSb = loadAllProjectQuotationSchemeItem(strSb,listProjectQuotationSchemeItem);
			strSb.append("]");
			renderHtml(strSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private StringBuilder loadAllProjectQuotationSchemeItem(
			StringBuilder strSb,
			List<ProjectQuotationSchemeItem> listProjectQuotationSchemeItem)
			throws Exception {
		for (int i = 0; i < listProjectQuotationSchemeItem.size(); i++) {
			ProjectQuotationSchemeItem pqsi = listProjectQuotationSchemeItem.get(i);
			if (pqsi.getSubProjectQuotationSchemeItems().size() > 0) {
				strSb.append("{id:");
				strSb.append(pqsi.getId());
				strSb.append(",name:\"");
				if (null != pqsi.getItem() && null != pqsi.getItem().getName()) {
					strSb.append(pqsi.getItem().getName());
				} else {
					strSb.append(pqsi.getName());
				}
				strSb.append("\",open:true,isParent:true,children:[");
				loadAllProjectQuotationSchemeItem(strSb,new ArrayList<ProjectQuotationSchemeItem>(pqsi.getSubProjectQuotationSchemeItems()));
				strSb.append("]}");
			} else {
				strSb.append("{id:");
				strSb.append(pqsi.getId());
				strSb.append(",name:\"");
				if (null != pqsi.getItem() && null != pqsi.getItem().getName()) {
					strSb.append(pqsi.getItem().getName());
				} else {
					strSb.append(pqsi.getName());
				}
				strSb.append("\",open:false,isParent:false}");
			}
			if (i < listProjectQuotationSchemeItem.size() - 1) {
				strSb.append(",");
			}
		}
		return strSb;
	}

	private void dealBillGroupCode() throws Exception {
		if (null != billGroupCode.getId() && !"".equals(billGroupCode.getId())) {
			billGroupCode = baseHibernateService.findEntityById(
					BillGroupCode.class, billGroupCode.getId());
			if (null != billGroupCode) {
				if (null == billGroupCode.getBillCode()
						|| "".equals(billGroupCode.getBillCode())) {
					billGroupCode.setBillCode(projectQuotationSchemeItem
							.getId().toString());
					baseHibernateService.merge(billGroupCode);
				}
			}
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getProjectQuotationSchemeId() {
		return projectQuotationSchemeId;
	}

	public void setProjectQuotationSchemeId(Long projectQuotationSchemeId) {
		this.projectQuotationSchemeId = projectQuotationSchemeId;
	}

	public ProjectQuotationSchemeItem getProjectQuotationSchemeItem() {
		return projectQuotationSchemeItem;
	}

	public void setProjectQuotationSchemeItem(
			ProjectQuotationSchemeItem projectQuotationSchemeItem) {
		this.projectQuotationSchemeItem = projectQuotationSchemeItem;
	}

	public BillGroupCode getBillGroupCode() {
		return billGroupCode;
	}

	public void setBillGroupCode(BillGroupCode billGroupCode) {
		this.billGroupCode = billGroupCode;
	}
}