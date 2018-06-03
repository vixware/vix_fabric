package com.vix.crm.activity.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.share.entity.CurrencyType;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.crm.activity.entity.Activity;
import com.vix.crm.base.entity.SaleActivity;
import com.vix.hr.organization.entity.Employee;

@Controller
@Scope("prototype")
public class ActivityAction extends BaseAction {
	private static final long serialVersionUID = 1L;


	private String id;
	private String ids;
	private String title;
	private String companyCode;
	private Activity activity;
	private String pageNo;

	private List<String> startTimeList = new ArrayList<String>();// 开始时间
	private List<String> endTimeList = new ArrayList<String>();// 结束时间

	public ActivityAction() {
		StringBuilder t = new StringBuilder();
		for (int i = 0; i < 24; i++) {
			if (i < 10) {
				t.append("0");
				t.append(i);
			} else {
				t.append(i);
			}
			t.append(":");
			t.append("00");
			startTimeList.add(t.toString());
			endTimeList.add(t.toString());
			t.delete(0, t.length());
			if (i < 10) {
				t.append("0");
				t.append(i);
			} else {
				t.append(i);
			}
			t.append(":");
			t.append("30");
			startTimeList.add(t.toString());
			endTimeList.add(t.toString());
			t.delete(0, t.length());
		}
	}

	private List<Activity> indexList;

	@Override
	@SuppressWarnings("unchecked")
	public String goList() {
		try {
			Map<String, Object> params = getParams();
			getPager().setPageSize(20);
			getPager().setOrderField("id");
			getPager().setOrderBy("desc");
			baseHibernateService.findPagerByHqlConditions(getPager(),Activity.class, params);
			indexList = getPager().getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goList";
	}

	/** 获取列表数据 */
	public String goListContent() {
		try {
			Map<String, Object> params = getParams();
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			if (null != title && !"".equals(title)) {
				title = decode(title, "UTF-8");
				params.put("title," + SearchCondition.ANYLIKE, title);
			}
			if (null != companyCode && !"".equals(companyCode)) {
				params.put("companyCode," + SearchCondition.EQUAL, companyCode);
			}
			params.put("isDeleted," + SearchCondition.NOEQUAL, "1");
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(),
					Activity.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	private List<CurrencyType> currencyTypeList;
	private List<SaleActivity> saleActivityList;

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			currencyTypeList = baseHibernateService.findAllByEntityClass(CurrencyType.class);
			saleActivityList = baseHibernateService.findAllByEntityClass(SaleActivity.class);
			if (null != id && !"".equals(id)) {
				activity = baseHibernateService.findEntityById(Activity.class, id);
				String pageNo = getRequestParameter("pageNo");
				getRequest().setAttribute("pageNo", pageNo);
			} else {
				activity = new Activity();
				activity.setDate(new Date());
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
			if (StrUtils.objectIsNotNull(activity.getId())) {
				isSave = false;
			} else {
				activity.setCreateTime(new Date());
				activity.setIsDeleted("0");
				loadCommonData(activity);
			}

			if (null == activity.getCreated_by() || null == activity.getCreated_by().getId() || !"".equals(activity.getCreated_by().getId()) || !activity.getCreated_by().getId().equals("0")) {
				Employee bemp = getEmployee();
				if (null != bemp) {
					Employee emp = baseHibernateService.findEntityById(
							Employee.class, bemp.getId());
					if (null != emp) {
						activity.setCreated_by(emp);
					}
				}
			}

			String[] attrArray = { "customerAccount", "currencyType", "personInCharge" , "saleActivity"};
			checkEntityNullValue(activity, attrArray);

			String name = activity.getTitle();
			String py = ChnToPinYin.getPYString(name);
			activity.setChineseFirstLetter(py.toUpperCase());
			activity = baseHibernateService.merge(activity);
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
			Activity pb = baseHibernateService.findEntityById(Activity.class, id);
			if (null != pb) {
				pb.setIsDeleted("1");
				baseHibernateService.merge(pb);
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

	/** 处理删除操作 */
	public String deleteByIds() {
		try {
			if (null != ids && !"".equals(ids)) {
				List<String> delIds = new ArrayList<String>();
				for (String idStr : ids.split(",")) {
					if (null != idStr && !"".equals(idStr)
							&& !"undefined".equals(idStr)) {
						delIds.add(new String(idStr));
					}
				}
				baseHibernateService.batchDelete(Activity.class, delIds);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotChoose"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public String goChooseType() {
		return "goChooseType";
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

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public List<CurrencyType> getCurrencyTypeList() {
		return currencyTypeList;
	}

	public void setCurrencyTypeList(List<CurrencyType> currencyTypeList) {
		this.currencyTypeList = currencyTypeList;
	}

	public List<String> getStartTimeList() {
		return startTimeList;
	}

	public void setStartTimeList(List<String> startTimeList) {
		this.startTimeList = startTimeList;
	}

	public List<String> getEndTimeList() {
		return endTimeList;
	}

	public void setEndTimeList(List<String> endTimeList) {
		this.endTimeList = endTimeList;
	}

	public List<Activity> getIndexList() {
		return indexList;
	}

	public void setIndexList(List<Activity> indexList) {
		this.indexList = indexList;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public List<SaleActivity> getSaleActivityList() {
		return saleActivityList;
	}

	public void setSaleActivityList(List<SaleActivity> saleActivityList) {
		this.saleActivityList = saleActivityList;
	}

}
